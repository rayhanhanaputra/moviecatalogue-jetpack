package com.rayhanhanaputra.moviecataloguevm.utils;

import android.app.Application;

import com.rayhanhanaputra.moviecataloguevm.repository.DetailRepository;
import com.rayhanhanaputra.moviecataloguevm.repository.MovieRepository;
import com.rayhanhanaputra.moviecataloguevm.repository.RemoteRepository;
import com.rayhanhanaputra.moviecataloguevm.repository.TvshowRepository;

public class Injection {
    public static MovieRepository provideRepository(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        return MovieRepository.getInstance(remoteRepository);
    }

    public static TvshowRepository provideRepository2(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        return TvshowRepository.getInstance(remoteRepository);
    }

    public static DetailRepository provideRepository3(Application application) {

        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));

        return DetailRepository.getInstance(remoteRepository);
    }

}
