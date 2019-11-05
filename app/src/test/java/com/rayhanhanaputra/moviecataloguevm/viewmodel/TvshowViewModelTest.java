package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.repository.TvshowRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvshowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private TvshowViewModel viewModel;
    private TvshowRepository tvshowRepository = mock(TvshowRepository.class);
    private ArrayList<Tvshow> dummyTv = new ArrayList<>();
    private Tvshow tv;

    @Before
    public void setUp() {
        viewModel = new TvshowViewModel(tvshowRepository);
        dummyTv.add(tv = new Tvshow(62286, "Fear the Walking Dead", "Fear the Walking Dead", "6.3", "2015-08-23", "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.", "/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg", "tv"));
        dummyTv.add(tv = new Tvshow(1412, "Arrow", "Arrow", "5.8", "2012-10-10", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg", "tv"));
    }

    @Test
    public void getTv() {
        MutableLiveData<ArrayList<Tvshow>> tvshow = new MutableLiveData<>();
        tvshow.setValue(dummyTv);
        when(tvshowRepository.getListTvMutable()).thenReturn(tvshow);
        Observer<ArrayList<Tvshow>> observer = mock(Observer.class);
        observer.onChanged(dummyTv);
        viewModel.getTv().observeForever(observer);
        verify(observer).onChanged(dummyTv);
        assertNotNull(tvshow);
    }
}