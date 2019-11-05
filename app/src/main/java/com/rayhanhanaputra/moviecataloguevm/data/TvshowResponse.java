package com.rayhanhanaputra.moviecataloguevm.data;

import java.util.List;

public class TvshowResponse {
    private List<Tvshow> results;

    public List<Tvshow> getResults() {
        return results;
    }

    public void setResults(List<Tvshow> results) {
        this.results = results;
    }
}
