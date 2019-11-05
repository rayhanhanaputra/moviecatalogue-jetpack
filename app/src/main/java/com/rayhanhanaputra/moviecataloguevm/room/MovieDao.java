package com.rayhanhanaputra.moviecataloguevm.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie movie);

    @Update()
    void update(Movie movie);

    @Delete()
    void delete(Movie movie);

    @WorkerThread
    @Query("SELECT * from movie WHERE movieId = :id")
    LiveData<List<Movie>> checkMovie(int id);

    @Query("SELECT * from movie ORDER BY movieId ASC")
    DataSource.Factory<Integer, Movie> getAllMovies();
}