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
import com.rayhanhanaputra.moviecataloguevm.adapter.TvshowPagedAdapter;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;

public class Placeholder2Fragment extends Fragment {
    private PageViewModel2 tvViewModel;
    private RecyclerView rvTv;
    private TvshowPagedAdapter tvshowAdapter;
    private final Observer<PagedList<Tvshow>> tvObserver = new Observer<PagedList<Tvshow>>() {
        @Override
        public void onChanged(@Nullable PagedList<Tvshow> movies) {
            if (movies != null) {
                tvshowAdapter.submitList(movies);
                rvTv.setAdapter(tvshowAdapter);
            }
        }
    };

    public Placeholder2Fragment() {

    }

    public static Fragment newInstance() {
        return new Placeholder2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tvshow_fragment, container, false);
        rvTv = view.findViewById(R.id.rv_tvshow);
        rvTv.setHasFixedSize(true);
        rvTv.setLayoutManager(new LinearLayoutManager(getContext()));

        ViewModelFactory1 factory = ViewModelFactory1.getInstance(getActivity().getApplication());

        PageViewModel2 model = ViewModelProviders.of(this, factory).get(PageViewModel2.class);

        model.getAllTvshows().observe(this, tvObserver);

        tvshowAdapter = new TvshowPagedAdapter(getActivity());
        return view;
    }
}
