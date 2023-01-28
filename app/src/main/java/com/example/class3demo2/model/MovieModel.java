package com.example.class3demo2.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieModel {
    final public static MovieModel instance = new MovieModel();

    final String BASE_URL = "https://www.omdbapi.com/";
    Retrofit retrofit;
    MovieApi movieApi;

    private MovieModel(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        movieApi = retrofit.create(MovieApi.class);
    }

    public LiveData<List<Movie>> searchMoviesByTitle(String title){
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        Call<MovieSearchResult> call = movieApi.searchMovieByTitle(title);
        call.enqueue(new Callback<MovieSearchResult>() {
            @Override
            public void onResponse(Call<MovieSearchResult> call, Response<MovieSearchResult> response) {
                if (response.isSuccessful()){
                    MovieSearchResult res = response.body();
                    data.setValue(res.getSearch());
                }else{
                    Log.d("TAG","----- searchMoviesByTitle response error");
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResult> call, Throwable t) {
                Log.d("TAG","----- searchMoviesByTitle fail");
            }
        });
        return data;
    }
    
    
}
