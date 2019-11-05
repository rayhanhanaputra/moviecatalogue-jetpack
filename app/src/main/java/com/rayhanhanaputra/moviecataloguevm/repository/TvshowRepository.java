package com.rayhanhanaputra.moviecataloguevm.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.data.TvshowResponse;
import com.rayhanhanaputra.moviecataloguevm.network.ApiClient;
import com.rayhanhanaputra.moviecataloguevm.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvshowRepository {
    private volatile static TvshowRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;
    private ArrayList<Tvshow> listTv;

    public TvshowRepository(@NonNull RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static TvshowRepository getInstance(RemoteRepository remoteData) {
        if (INSTANCE == null) {
            synchronized (TvshowRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvshowRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    public void getAllTv(MutableLiveData<ArrayList<Tvshow>> lists) {
        ArrayList<Tvshow> list = new ArrayList<>();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TvshowResponse> call = mApiInterface.getTv();

        call.enqueue(new Callback<TvshowResponse>() {
            @Override
            public void onResponse(Call<TvshowResponse> call, Response<TvshowResponse> response) {
                try {
                    List<Tvshow> listOfTv = response.body().getResults();
                    for (int i = 0; i < listOfTv.size(); i++) {

                        Tvshow tvshow = new Tvshow();
                        tvshow.setId(listOfTv.get(i).getId());
                        tvshow.setTitle(listOfTv.get(i).getTitle());
                        tvshow.setOverview(listOfTv.get(i).getOverview());
                        tvshow.setReleaseDate(listOfTv.get(i).getReleaseDate());
                        tvshow.setPhotoLink(listOfTv.get(i).getPhotoLink());
                        list.add(tvshow);
                    }
                    lists.postValue(list);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<TvshowResponse> call, Throwable t) {
            }

        });
    }

    public MutableLiveData<ArrayList<Tvshow>> getListTvMutable() {
        MutableLiveData<ArrayList<Tvshow>> mutableLiveData = null;
        ArrayList<Tvshow> list = new ArrayList<>();
        ApiInterface mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<TvshowResponse> call = mApiInterface.getTv();

        call.enqueue(new Callback<TvshowResponse>() {
            @Override
            public void onResponse(Call<TvshowResponse> call, Response<TvshowResponse> response) {
                try {
                    List<Tvshow> listOfTv = response.body().getResults();
                    for (int i = 0; i < listOfTv.size(); i++) {

                        Tvshow tvshow = new Tvshow();
                        tvshow.setId(listOfTv.get(i).getId());
                        tvshow.setTitle(listOfTv.get(i).getTitle());
                        tvshow.setOverview(listOfTv.get(i).getOverview());
                        tvshow.setReleaseDate(listOfTv.get(i).getReleaseDate());
                        tvshow.setPhotoLink(listOfTv.get(i).getPhotoLink());
                        list.add(tvshow);
                    }
                    mutableLiveData.postValue(list);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<TvshowResponse> call, Throwable t) {
            }

        });

        return mutableLiveData;
    }

}
