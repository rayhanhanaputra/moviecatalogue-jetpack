package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.data.Tvshow;
import com.rayhanhanaputra.moviecataloguevm.repository.DetailRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailViewModel viewModel;
    private DetailRepository detailRepository = mock(DetailRepository.class);

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(detailRepository, null);
    }

    @Test
    public void getMovieDetail() {
        Movie dummyMovie = new Movie(475557, "Joker", "Joker", "8.6", "2019-10-04", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg", "movie");
        MutableLiveData<Movie> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);
        when(detailRepository.getMovieTesting("475557")).thenReturn(dummyMovie);
        Observer<Movie> observer = mock(Observer.class);
        observer.onChanged(dummyMovie);
        viewModel.getDetail("movie", "475557").observeForever(observer);
        verify(observer).onChanged(dummyMovie);
        assertNotNull(movies);
        assertEquals("Joker", dummyMovie.getTitle());
        assertEquals("2019-10-04", dummyMovie.getReleaseDate());


    }

    @Test
    public void getTvDetail() {
        Tvshow dummyTv = new Tvshow(1412, "Arrow", "Arrow", "5.8", "2012-10-10", "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.", "/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg", "tv");
        MutableLiveData<Tvshow> tv = new MutableLiveData<>();
        tv.setValue(dummyTv);
        when(detailRepository.getTvTesting("1412")).thenReturn(dummyTv);
        Observer<Tvshow> observer2 = mock(Observer.class);
        observer2.onChanged(dummyTv);
        viewModel.getDetail2("tv", "1412").observeForever(observer2);
        verify(observer2).onChanged(dummyTv);
        assertNotNull(tv);
        assertEquals("Arrow", dummyTv.getTitle());
        assertEquals("/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg", dummyTv.getPhotoLink());
    }


}