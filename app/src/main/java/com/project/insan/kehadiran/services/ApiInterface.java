package com.project.insan.kehadiran.services;

import com.project.insan.kehadiran.java.Movie;
import com.project.insan.kehadiran.java.Tv;

import retrofit2.Call;
import retrofit2.http.GET;
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


    @GET("tv/popular")
    Call<Tv> getTVPopular(
            @Query("api_key") String apiKey
    );
    @GET("tv/airing_today")
    Call<Tv> getTVairingToday(
            @Query("api_key") String apiKey
    );

    @GET("search/movie/")
    Call<Movie> getSearchMovie(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("search/tv/")
    Call<Movie> getSearchTv(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );
}
