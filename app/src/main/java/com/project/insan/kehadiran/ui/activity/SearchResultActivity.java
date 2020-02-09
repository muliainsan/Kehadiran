package com.project.insan.kehadiran.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.adapter.MoviesAdapter;
import com.project.insan.kehadiran.java.Movie;
import com.project.insan.kehadiran.services.ApiClient;
import com.project.insan.kehadiran.services.ApiInterface;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends AppCompatActivity {
    RecyclerView rv;
    List<Movie.ResultsBean> movies = new ArrayList<>();

    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        rv = findViewById(R.id.rv_movie);



        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Movie> call = apiService.getSearchMovie(API_Key,query);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                int statusCode = response.code();
                movies = response.body().getResults();
                Log.e("1q1q", String.valueOf(statusCode));
                rv.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
                MoviesAdapter madapter = new MoviesAdapter(movies, SearchResultActivity.this);
                rv.setAdapter(madapter);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("xxx", t.toString());
                Toast.makeText(SearchResultActivity.this, "No Internet Access", Toast.LENGTH_SHORT).show();
                if (savedInstanceState != null) {

                    movies =  savedInstanceState.getParcelableArrayList("list3");

                    LinearLayoutManager layoutManager = new LinearLayoutManager(SearchResultActivity.this);
                    rv.setLayoutManager(layoutManager);
                    MoviesAdapter madapter = new MoviesAdapter(movies, SearchResultActivity.this);
                    rv.setAdapter(madapter);
                }
            }
        });




    }

}
