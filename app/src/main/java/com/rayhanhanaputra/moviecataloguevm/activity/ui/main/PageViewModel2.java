package com.rayhanhanaputra.moviecataloguevm.activity.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.room.DatabaseRepository;

public class PageViewModel2 extends ViewModel {
    private DatabaseRepository databaseRepository;

    public PageViewModel2(Application application) {
        databaseRepository = new DatabaseRepository(application);
    }

    LiveData<PagedList<Tvshow>> getAllTvshows() {
        return new LivePagedListBuilder<>(databaseRepository.getAllTvshows(), 5).build();
    }

    public void insert2(Tvshow tvshow) {
        databaseRepository.insert2(tvshow);
    }

    public void delete2(Tvshow tvshow) {
        databaseRepository.delete2(tvshow);
    }

    public void update2(Tvshow tvshow) {
        databaseRepository.update2(tvshow);
    }
}
