package com.project.insan.kehadiran;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.project.insan.kehadiran.adapter.MoviesAdapter;
import com.project.insan.kehadiran.services.ApiClient;
import com.project.insan.kehadiran.services.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity{
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private ActionBar toolbar;
    private SwipeRefreshLayout swipeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("1q1q", "asdf");
        swipeLayout = findViewById(R.id.swipe_container);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Now Playing");
        categories.add("Up Coming");
        categories.add("Top Rated");

        final ArrayAdapter<String> dataAdapter ;
        dataAdapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(dataAdapter.getItem(position)=="Now Playing") {
                    final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<Movie> call = apiService.getNowPlaying(API_Key);
                    call.enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                            RecyclerView rv = findViewById(R.id.rv_movie);
                            int statusCode = response.code();
                            List<Movie.ResultsBean> movies = response.body().getResults();
                            Log.e("1q1q", String.valueOf(statusCode));
                            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            MoviesAdapter madapter = new MoviesAdapter(movies, MainActivity.this);
                            rv.setAdapter(madapter);

                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {
                            Log.e("xxx", t.toString());
                        }
                    });

                }else if(dataAdapter.getItem(position)=="Up Coming"){
                    final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<Movie> call = apiService.getUpComing(API_Key);
                    call.enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                            RecyclerView rv = findViewById(R.id.rv_movie);
                            int statusCode = response.code();
                            List<Movie.ResultsBean> movies = response.body().getResults();
                            Log.e("1q1q", String.valueOf(statusCode));

                            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            MoviesAdapter madapter = new MoviesAdapter(movies, getApplicationContext());
                            rv.setAdapter(madapter);

                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {
                            Log.e("xxx", t.toString());
                        }
                    });
                }else if(dataAdapter.getItem(position)=="Top Rated"){
                    final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<Movie> call = apiService.getTopRated(API_Key);
                    call.enqueue(new Callback<Movie>() {
                        @Override
                        public void onResponse(Call<Movie> call, Response<Movie> response) {
                            RecyclerView rv = findViewById(R.id.rv_movie);
                            int statusCode = response.code();
                            List<Movie.ResultsBean> movies = response.body().getResults();
                            Log.e("1q1q", String.valueOf(statusCode));
                            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            MoviesAdapter madapter = new MoviesAdapter(movies, MainActivity.this);
                            rv.setAdapter(madapter);

                        }

                        @Override
                        public void onFailure(Call<Movie> call, Throwable t) {
                            Log.e("xxx", t.toString());
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                    setupViewPager(viewPager);
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }


}
