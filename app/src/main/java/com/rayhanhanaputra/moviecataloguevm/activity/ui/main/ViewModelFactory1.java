package com.rayhanhanaputra.moviecataloguevm.activity.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory1 extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory1 INSTANCE;

    private final Application mApplication;

    private ViewModelFactory1(Application application) {
        mApplication = application;
    }

    public static ViewModelFactory1 getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory1.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory1(application);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PageViewModel.class)) {
            return (T) new PageViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(PageViewModel2.class)) {
            return (T) new PageViewModel2(mApplication);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
