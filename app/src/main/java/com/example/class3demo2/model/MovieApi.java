package com.example.class3demo2.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("/?apikey=51fec535")
    Call<MovieSearchResult> searchMovieByTitle(@Query("s") String title);

    @GET("/?apikey=51fec535")
    Call<Movie> getMovieByTitle(@Query("t") String title);
}
