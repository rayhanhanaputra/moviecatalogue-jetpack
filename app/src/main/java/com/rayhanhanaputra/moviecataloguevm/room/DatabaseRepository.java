package com.rayhanhanaputra.moviecataloguevm.room;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseRepository {

    private MovieDao movieDao;
    private TvshowDao tvshowDao;
    private ExecutorService executorService;

    public DatabaseRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();

        MovieDatabase db = MovieDatabase.getDatabase(application.getApplicationContext());
        movieDao = db.movieDao();
        tvshowDao = db.tvshowDao();
    }

    public DataSource.Factory<Integer, Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public LiveData<List<Movie>> checkMovie(int id) {
        return movieDao.checkMovie(id);
    }

    public void insert(final Movie movie) {
        executorService.execute(() -> movieDao.insert(movie));
    }

    public void delete(final Movie movie) {
        executorService.execute(() -> movieDao.delete(movie));
    }

    public void update(final Movie movie) {
        executorService.execute(() -> movieDao.update(movie));
    }


    public DataSource.Factory<Integer, Tvshow> getAllTvshows() {
        return tvshowDao.getAllTvshows();
    }

    public LiveData<List<Tvshow>> checkTvshow(int id) {
        return tvshowDao.checkTvshow(id);
    }

    public void insert2(final Tvshow tvshow) {
        executorService.execute(() -> tvshowDao.insert(tvshow));
    }

    public void delete2(final Tvshow tvshow) {
        executorService.execute(() -> tvshowDao.delete(tvshow));
    }

    public void update2(final Tvshow tvshow) {
        executorService.execute(() -> tvshowDao.update(tvshow));
    }
}
