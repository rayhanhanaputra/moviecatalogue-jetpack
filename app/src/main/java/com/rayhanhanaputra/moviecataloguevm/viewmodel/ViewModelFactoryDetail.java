package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rayhanhanaputra.moviecataloguevm.repository.DetailRepository;
import com.rayhanhanaputra.moviecataloguevm.room.DatabaseRepository;
import com.rayhanhanaputra.moviecataloguevm.utils.Injection;

public class ViewModelFactoryDetail extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactoryDetail INSTANCE;
    private final DetailRepository detailRepository;
    private final DatabaseRepository databaseRepository;

    private ViewModelFactoryDetail(DetailRepository detailRepository, DatabaseRepository databaseRepository) {
        this.detailRepository = detailRepository;
        this.databaseRepository = databaseRepository;
    }

    public static ViewModelFactoryDetail getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactoryDetail.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactoryDetail(Injection.provideRepository3(application), new DatabaseRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailViewModel(detailRepository, databaseRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
