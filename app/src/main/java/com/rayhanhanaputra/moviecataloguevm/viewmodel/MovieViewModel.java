package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.repository.MovieRepository;

import java.util.ArrayList;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> movieList;
    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<ArrayList<Movie>> getMovie() {
        if (movieList == null) {
            movieList = new MutableLiveData<ArrayList<Movie>>();
            loadMovie();
        }
        return movieList;
    }

    public void loadMovie() {
        movieRepository.getAllMovie(movieList);
    }

}