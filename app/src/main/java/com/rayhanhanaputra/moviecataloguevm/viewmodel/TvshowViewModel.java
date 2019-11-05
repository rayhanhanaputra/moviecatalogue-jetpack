package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.repository.TvshowRepository;

import java.util.ArrayList;

public class TvshowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Tvshow>> tvList;
    private TvshowRepository tvshowRepository;

    public TvshowViewModel(TvshowRepository tvshowRepository) {
        this.tvshowRepository = tvshowRepository;
    }

    public LiveData<ArrayList<Tvshow>> getTv() {
        if (tvList == null) {
            tvList = new MutableLiveData<ArrayList<Tvshow>>();
            loadTv();
        }
        return tvList;
    }

    private void loadTv() {
        tvshowRepository.getAllTv(tvList);
    }

}
