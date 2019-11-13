package com.project.insan.kehadiran;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.insan.kehadiran.adapter.TabAdapter;
import com.project.insan.kehadiran.fragment.MovieFragment;
import com.project.insan.kehadiran.fragment.TvFragment;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity{
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private ActionBar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieFragment(), "Tab 1");
        adapter.addFragment(new TvFragment(), "Tab 2");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



//        Log.e("1q1q", "asdf");
//        swipeLayout = findViewById(R.id.swipe_container);
//        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
//        List<String> categories = new ArrayList<String>();
//        categories.add("Now Playing");
//        categories.add("Up Coming");
//        categories.add("Top Rated");
//
//        final ArrayAdapter<String> dataAdapter ;
//        dataAdapter = new ArrayAdapter<String>( this,android.R.layout.simple_spinner_item, categories);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        spinner.setAdapter(dataAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(dataAdapter.getItem(position)=="Now Playing") {
//                    final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//                    Call<Movie> call = apiService.getNowPlaying(API_Key);
//                    call.enqueue(new Callback<Movie>() {
//                        @Override
//                        public void onResponse(Call<Movie> call, Response<Movie> response) {
//                            RecyclerView rv = findViewById(R.id.rv_movie);
//                            int statusCode = response.code();
//                            List<Movie.ResultsBean> movies = response.body().getResults();
//                            Log.e("1q1q", String.valueOf(statusCode));
//                            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                            MoviesAdapter madapter = new MoviesAdapter(movies, MainActivity.this);
//                            rv.setAdapter(madapter);
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Movie> call, Throwable t) {
//                            Log.e("xxx", t.toString());
//                        }
//                    });
//
//                }else if(dataAdapter.getItem(position)=="Up Coming"){
//                    final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//                    Call<Movie> call = apiService.getUpComing(API_Key);
//                    call.enqueue(new Callback<Movie>() {
//                        @Override
//                        public void onResponse(Call<Movie> call, Response<Movie> response) {
//                            RecyclerView rv = findViewById(R.id.rv_movie);
//                            int statusCode = response.code();
//                            List<Movie.ResultsBean> movies = response.body().getResults();
//                            Log.e("1q1q", String.valueOf(statusCode));
//
//                            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                            MoviesAdapter madapter = new MoviesAdapter(movies, getApplicationContext());
//                            rv.setAdapter(madapter);
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Movie> call, Throwable t) {
//                            Log.e("xxx", t.toString());
//                        }
//                    });
//                }else if(dataAdapter.getItem(position)=="Top Rated"){
//                    final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//                    Call<Movie> call = apiService.getTopRated(API_Key);
//                    call.enqueue(new Callback<Movie>() {
//                        @Override
//                        public void onResponse(Call<Movie> call, Response<Movie> response) {
//                            RecyclerView rv = findViewById(R.id.rv_movie);
//                            int statusCode = response.code();
//                            List<Movie.ResultsBean> movies = response.body().getResults();
//                            Log.e("1q1q", String.valueOf(statusCode));
//                            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                            MoviesAdapter madapter = new MoviesAdapter(movies, MainActivity.this);
//                            rv.setAdapter(madapter);
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Movie> call, Throwable t) {
//                            Log.e("xxx", t.toString());
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//
//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
////                    setupViewPager(viewPager);
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });
    }


}
