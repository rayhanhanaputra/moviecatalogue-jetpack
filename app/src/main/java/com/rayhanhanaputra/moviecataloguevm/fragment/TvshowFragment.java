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
import com.rayhanhanaputra.moviecataloguevm.adapter.TvshowAdapter;
import com.rayhanhanaputra.moviecataloguevm.utils.EspressoIdlingResource;
import com.rayhanhanaputra.moviecataloguevm.viewmodel.TvshowViewModel;
import com.rayhanhanaputra.moviecataloguevm.viewmodel.ViewModelFactory2;

public class TvshowFragment extends Fragment {

    private TvshowViewModel tvshowViewModel;
    private RecyclerView rvTvshow;
    private ProgressBar progressBarTvshow;
    private TvshowAdapter tvshowAdapter;

    public static TvshowFragment newInstance() {
        return new TvshowFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tvshow_fragment, container, false);
        progressBarTvshow = view.findViewById(R.id.progress_bar_tvshow);
        rvTvshow = view.findViewById(R.id.rv_tvshow);
        rvTvshow.setHasFixedSize(true);
        rvTvshow.setLayoutManager(new LinearLayoutManager(getContext()));
        showLoading(true);

        ViewModelFactory2 factory = ViewModelFactory2.getInstance(getActivity().getApplication());

        TvshowViewModel model = ViewModelProviders.of(this, factory).get(TvshowViewModel.class);

        EspressoIdlingResource.increment();
        model.getTv().observe(this, tvList -> {
            tvshowAdapter = new TvshowAdapter(getContext(), tvList, null);
            rvTvshow.setAdapter(tvshowAdapter);
            showLoading(false);
            EspressoIdlingResource.decrement();
        });
        return view;
    }

    private void showLoading(Boolean state) {
        if (!state) {
            progressBarTvshow.setVisibility(View.GONE);
        } else {
            progressBarTvshow.setVisibility(View.VISIBLE);
        }
    }
}
