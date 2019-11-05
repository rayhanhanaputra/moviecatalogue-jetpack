package com.rayhanhanaputra.moviecataloguevm.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.rayhanhanaputra.moviecataloguevm.data.Movie;
import com.rayhanhanaputra.moviecataloguevm.repository.MovieRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private ArrayList<Movie> dummyMovie = new ArrayList<>();
    private Movie movie;

    @Before
    public void setUp() {
        viewModel = new MovieViewModel(movieRepository);
        dummyMovie.add(movie = new Movie(475557, "Joker", "Joker", "8.6", "2019-10-04", "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.", "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg", "movie"));
        dummyMovie.add(movie = new Movie(420809, "Maleficent: Mistress of Evil", "Maleficent: Mistress of Evil", "7.2", "2019-10-18", "Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play.", "/tBuabjEqxzoUBHfbyNbd8ulgy5j.jpg", "movie"));
    }

    @Test
    public void getMovie() {
        MutableLiveData<ArrayList<Movie>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);
        when(movieRepository.getListMovieMutable()).thenReturn(movies);
        Observer<ArrayList<Movie>> observer = mock(Observer.class);
        observer.onChanged(dummyMovie);
        viewModel.getMovie().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
        assertNotNull(movies);
    }
}