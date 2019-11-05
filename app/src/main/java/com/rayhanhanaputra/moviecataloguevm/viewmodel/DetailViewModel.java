package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.repository.DetailRepository;
import com.rayhanhanaputra.moviecataloguevm.room.DatabaseRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<Movie> movie;
    public MutableLiveData<Tvshow> tvshow;
    private DetailRepository detailRepository;
    private DatabaseRepository databaseRepository;

    public DetailViewModel(DetailRepository detailRepository, DatabaseRepository databaseRepository) {
        this.detailRepository = detailRepository;
        this.databaseRepository = databaseRepository;
    }

    public LiveData<Movie> getDetail(String type, String id) {
        if (movie == null) {
            movie = new MutableLiveData<Movie>();
            loadDetail(type, id);
        }
        return movie;
    }

    public LiveData<Tvshow> getDetail2(String type, String id) {
        if (tvshow == null) {
            tvshow = new MutableLiveData<Tvshow>();
            loadDetail2(type, id);
        }
        return tvshow;
    }

    public LiveData<List<Movie>> checkMovie(int id) {
        return databaseRepository.checkMovie(id);
    }

    public LiveData<List<Tvshow>> checkTvshow(int id) {
        return databaseRepository.checkTvshow(id);
    }


    public void loadDetail(String type, String id) {
        detailRepository.getMovieDetail(id, movie);
    }

    public Movie loadMovieDetail(String id) {
        return detailRepository.getMovieTesting(id);
    }

    public void loadDetail2(String type, String id) {
        detailRepository.getTvDetail(id, tvshow);
    }

    public Tvshow loadTvDetail(String id) {
        return detailRepository.getTvTesting(id);
    }
}
