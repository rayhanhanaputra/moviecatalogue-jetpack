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

import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;

import java.util.List;

@Dao
public interface TvshowDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Tvshow tv);

    @Update()
    void update(Tvshow tv);

    @Delete()
    void delete(Tvshow tv);

    @WorkerThread
    @Query("SELECT * from tvshow WHERE tvshowId = :id")
    LiveData<List<Tvshow>> checkTvshow(int id);

    @Query("SELECT * from tvshow ORDER BY tvshowName ASC")
    DataSource.Factory<Integer, Tvshow> getAllTvshows();
}