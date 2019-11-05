package com.rayhanhanaputra.moviecataloguevm.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.network.ApiClient;
import com.rayhanhanaputra.moviecataloguevm.network.ApiInterface;
import com.rayhanhanaputra.moviecataloguevm.utils.EspressoIdlingResource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository implements DetailDataSource {
    private volatile static DetailRepository INSTANCE = null;

    private final RemoteRepository remoteRepository;

    public DetailRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static DetailRepository getInstance(RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (DetailRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetailRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getMovieDetail(String id, MutableLiveData<Movie> movieDetail) {
        EspressoIdlingResource.increment();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Movie> call = null;
        call = mApiInterface.getDetail(id);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movieresponse = new Movie();
                movieresponse.setTitle(response.body().getOriginalTitle());
                movieresponse.setOverview(response.body().getOverview());
                movieresponse.setReleaseDate(response.body().getReleaseDate());
                movieresponse.setRating(response.body().getRating());
                movieresponse.setPhotoLink(response.body().getPhotoLink());
                movieDetail.postValue(movieresponse);
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("Retrofit", "get response failed");
                EspressoIdlingResource.decrement();
            }
        });

    }

    @Override
    public void getTvDetail(String id, MutableLiveData<Tvshow> tvshow) {
        EspressoIdlingResource.increment();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Tvshow> call = null;
        call = mApiInterface.getDetail2(id);
        call.enqueue(new Callback<Tvshow>() {
            @Override
            public void onResponse(Call<Tvshow> call, Response<Tvshow> response) {
                Tvshow movieresponse = new Tvshow();
                movieresponse.setTitle(response.body().getOriginalTitle());
                movieresponse.setOverview(response.body().getOverview());
                movieresponse.setReleaseDate(response.body().getReleaseDate());
                movieresponse.setRating(response.body().getRating());
                movieresponse.setPhotoLink(response.body().getPhotoLink());
                tvshow.postValue(movieresponse);
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<Tvshow> call, Throwable t) {
                Log.d("Retrofit", "get response failed");
                EspressoIdlingResource.decrement();
            }
        });
    }

    public Movie getMovieTesting(String id) {
        Movie movie = new Movie();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Movie> call = null;
        call = mApiInterface.getDetail(id);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                movie.setTitle(response.body().getOriginalTitle());
                movie.setOverview(response.body().getOverview());
                movie.setReleaseDate(response.body().getReleaseDate());
                movie.setRating(response.body().getRating());
                movie.setPhotoLink(response.body().getPhotoLink());
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("Retrofit", "get response failed");
            }
        });
        return movie;
    }

    public Tvshow getTvTesting(String id) {
        Tvshow tv = new Tvshow();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Tvshow> call = null;
        call = mApiInterface.getDetail2(id);
        call.enqueue(new Callback<Tvshow>() {
            @Override
            public void onResponse(Call<Tvshow> call, Response<Tvshow> response) {
                tv.setTitle(response.body().getOriginalTitle());
                tv.setOverview(response.body().getOverview());
                tv.setReleaseDate(response.body().getReleaseDate());
                tv.setRating(response.body().getRating());
                tv.setPhotoLink(response.body().getPhotoLink());
            }

            @Override
            public void onFailure(Call<Tvshow> call, Throwable t) {
                Log.d("Retrofit", "get response failed");
            }
        });
        return tv;
    }
}
