package com.example.rtwm38.movieapp.network;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit2.Call;

import com.example.rtwm38.movieapp.model.Movie;

public interface MovieServiceApi {
    @GET("/movie/popular")
    void getMoviesNowPlaying(Callback<Movie.MovieResult> callback);

    @GET("/search/movie")
    Call<Movie.MovieResult> searchMovies(@Query("query") String query);
}
