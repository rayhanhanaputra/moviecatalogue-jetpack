package com.rayhanhanaputra.moviecataloguevm.repository;

import androidx.lifecycle.MutableLiveData;

import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;

import java.util.ArrayList;

public interface TvshowDataSource {
    void getAllTvshow(MutableLiveData<ArrayList<Tvshow>> lists);
}
