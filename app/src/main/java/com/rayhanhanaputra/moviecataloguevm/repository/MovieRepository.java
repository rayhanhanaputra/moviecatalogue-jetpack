package com.rayhanhanaputra.moviecataloguevm.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.MovieResponse;
import com.rayhanhanaputra.moviecataloguevm.network.ApiClient;
import com.rayhanhanaputra.moviecataloguevm.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository implements MovieDataSource {
    private volatile static MovieRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;
    MutableLiveData<ArrayList<Movie>> listTesting;
    private ArrayList<Movie> listMovie;

    public MovieRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static MovieRepository getInstance(RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    public void getAllMovie(MutableLiveData<ArrayList<Movie>> lists) {
        ArrayList<Movie> list = new ArrayList<>();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = mApiInterface.getMovie();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                try {
                    List<Movie> listOfMovie = response.body().getResults();
                    for (int i = 0; i < listOfMovie.size(); i++) {

                        Movie movie = new Movie();
                        movie.setId(listOfMovie.get(i).getId());
                        movie.setTitle(listOfMovie.get(i).getTitle());
                        movie.setOverview(listOfMovie.get(i).getOverview());
                        movie.setReleaseDate(listOfMovie.get(i).getReleaseDate());
                        movie.setPhotoLink(listOfMovie.get(i).getPhotoLink());
                        list.add(movie);
                    }
                    lists.postValue(list);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
            }

        });
    }

    public MutableLiveData<ArrayList<Movie>> getListMovieMutable() {
        MutableLiveData<ArrayList<Movie>> mutableLiveData = null;
        ArrayList<Movie> list = new ArrayList<>();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = mApiInterface.getMovie();

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                try {
                    List<Movie> listOfMovie = response.body().getResults();
                    for (int i = 0; i < listOfMovie.size(); i++) {

                        Movie movie = new Movie();
                        movie.setId(listOfMovie.get(i).getId());
                        movie.setTitle(listOfMovie.get(i).getTitle());
                        movie.setOverview(listOfMovie.get(i).getOverview());
                        movie.setReleaseDate(listOfMovie.get(i).getReleaseDate());
                        movie.setPhotoLink(listOfMovie.get(i).getPhotoLink());
                        list.add(movie);
                    }
                    mutableLiveData.postValue(list);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
            }

        });

        return mutableLiveData;
    }

}
