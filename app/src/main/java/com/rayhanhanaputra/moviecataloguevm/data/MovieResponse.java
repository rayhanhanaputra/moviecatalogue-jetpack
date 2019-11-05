package com.rayhanhanaputra.moviecataloguevm.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class MovieResponse {
    @Embedded
    public Movie movie;

    private List<Movie> results;

    @Relation(parentColumn = "movieId", entityColumn = "movieId")
    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
