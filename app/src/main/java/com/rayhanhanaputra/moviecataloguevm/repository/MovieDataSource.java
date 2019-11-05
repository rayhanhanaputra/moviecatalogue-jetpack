package com.rayhanhanaputra.moviecataloguevm.repository;

import androidx.lifecycle.MutableLiveData;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;

import java.util.ArrayList;

public interface MovieDataSource {
    void getAllMovie(MutableLiveData<ArrayList<Movie>> lists);
}
