package com.rayhanhanaputra.moviecataloguevm.activity.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.room.DatabaseRepository;

public class PageViewModel extends ViewModel {

    private DatabaseRepository databaseRepository;

    public PageViewModel(Application application) {
        databaseRepository = new DatabaseRepository(application);
    }

    LiveData<PagedList<Movie>> getAllMovies() {
        return new LivePagedListBuilder<>(databaseRepository.getAllMovies(), 5).build();
    }

    public void insert(Movie movie) {
        databaseRepository.insert(movie);
    }

    public void delete(Movie movie) {
        databaseRepository.delete(movie);
    }

    public void update(Movie movie) {
        databaseRepository.update(movie);
    }

}