package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.rayhanhanaputra.moviecataloguevm.repository.TvshowRepository;
import com.rayhanhanaputra.moviecataloguevm.utils.Injection;

public class ViewModelFactory2 extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory2 INSTANCE;
    private final TvshowRepository tvshowRepository;

    private ViewModelFactory2(TvshowRepository tvshowRepository) {
        this.tvshowRepository = tvshowRepository;
    }

    public static ViewModelFactory2 getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory2(Injection.provideRepository2(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TvshowViewModel.class)) {
            //noinspection unchecked
            return (T) new TvshowViewModel(tvshowRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
