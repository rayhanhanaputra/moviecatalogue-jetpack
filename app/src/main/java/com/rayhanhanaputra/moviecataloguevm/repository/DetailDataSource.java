package com.rayhanhanaputra.moviecataloguevm.repository;

import androidx.lifecycle.MutableLiveData;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;

public interface DetailDataSource {
    void getMovieDetail(String id, MutableLiveData<Movie> movie);

    void getTvDetail(String id, MutableLiveData<Tvshow> tvshow);
}
