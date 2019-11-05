package com.rayhanhanaputra.moviecataloguevm.network;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.MovieResponse;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.data.TvshowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    String API_KEY = "0a1b5c5193c99d0728bc8b62a85ac8b7";
    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("discover/movie?api_key=0a1b5c5193c99d0728bc8b62a85ac8b7")
    Call<MovieResponse> getMovie();

    @GET("discover/tv?api_key=0a1b5c5193c99d0728bc8b62a85ac8b7")
    Call<TvshowResponse> getTv();

    @GET("movie/{id}?api_key=0a1b5c5193c99d0728bc8b62a85ac8b7")
    Call<Movie> getDetail(@Path("id") String id);

    @GET("tv/{id}?api_key=0a1b5c5193c99d0728bc8b62a85ac8b7")
    Call<Tvshow> getDetail2(@Path("id") String id);
}
