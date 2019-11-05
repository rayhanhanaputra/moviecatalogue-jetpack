package com.rayhanhanaputra.moviecataloguevm.activity.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rayhanhanaputra.moviecataloguevm.R;
import com.rayhanhanaputra.moviecataloguevm.adapter.MoviePagedAdapter;
import com.rayhanhanaputra.moviecataloguevm.data.Movie;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private PageViewModel movieViewModel;
    private RecyclerView rvMovie;
    private MoviePagedAdapter movieAdapter;
    private final Observer<PagedList<Movie>> movieObserver = new Observer<PagedList<Movie>>() {
        @Override
        public void onChanged(@Nullable PagedList<Movie> movies) {
            if (movies != null) {
                movieAdapter.submitList(movies);
                rvMovie.setAdapter(movieAdapter);
            }
        }
    };

    public PlaceholderFragment() {

    }

    public static Fragment newInstance() {
        return new PlaceholderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_fragment, container, false);
        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));

        ViewModelFactory1 factory = ViewModelFactory1.getInstance(getActivity().getApplication());

        PageViewModel model = ViewModelProviders.of(this, factory).get(PageViewModel.class);

        model.getAllMovies().observe(this, movieObserver);

        movieAdapter = new MoviePagedAdapter(getActivity());
        return view;
    }
}