package com.project.insan.kehadiran.services;

import com.project.insan.kehadiran.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<Movie> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming")
    Call<Movie> getUpComing(
            @Query("api_key") String apiKey
    );

    @GET("movie/top_rated")
    Call<Movie> getTopRated(
            @Query("api_key") String apiKey
    );
//    @GET("movie/{id}")
//    Call<MovieDetails> getMovieDetails(
//            @Path("id") int id,
//            @Query("api_key") String apiKey
//    );

}
