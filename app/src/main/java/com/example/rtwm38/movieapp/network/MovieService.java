package com.example.rtwm38.movieapp.network;

import com.example.rtwm38.movieapp.model.Movie;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit2.Call;

public interface MovieService {
    @GET("/movie/popular")
    void getMoviesNowPlaying(Callback<Movie.MovieResult> callback);

    @GET("/search/movie")
    Call<Movie.MovieResult> searchMovies(@Query("query") String query);
}
