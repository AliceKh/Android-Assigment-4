package com.example.class3demo2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResult {
    @SerializedName("Search")
    List<Movie> search;

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
        this.search = search;
    }
}
