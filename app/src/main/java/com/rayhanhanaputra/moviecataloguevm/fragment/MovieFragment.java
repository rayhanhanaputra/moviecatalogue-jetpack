package com.rayhanhanaputra.moviecataloguevm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rayhanhanaputra.moviecataloguevm.R;
import com.rayhanhanaputra.moviecataloguevm.adapter.MovieAdapter;
import com.rayhanhanaputra.moviecataloguevm.utils.EspressoIdlingResource;
import com.rayhanhanaputra.moviecataloguevm.viewmodel.MovieViewModel;
import com.rayhanhanaputra.moviecataloguevm.viewmodel.ViewModelFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class MovieFragment extends Fragment {

    private MovieViewModel movieViewModel;
    private RecyclerView rvMovie;
    private ProgressBar progressBarMovie;
    private MovieAdapter movieAdapter;

    public MovieFragment() {

    }

    public static Fragment newInstance() {
        return new MovieFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);
        progressBarMovie = view.findViewById(R.id.progress_bar_movie);
        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        showLoading(true);

        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity().getApplication());

        MovieViewModel model = ViewModelProviders.of(this, factory).get(MovieViewModel.class);

        EspressoIdlingResource.increment();
        model.getMovie().observe(this, movieList -> {
            movieAdapter = new MovieAdapter(getContext(), movieList, null);
            rvMovie.setAdapter(movieAdapter);
            showLoading(false);
            EspressoIdlingResource.decrement();
        });
        return view;
    }

    private void showLoading(Boolean state) {
        if (!state) {
            progressBarMovie.setVisibility(View.GONE);
        } else {
            progressBarMovie.setVisibility(View.VISIBLE);
        }
    }

}