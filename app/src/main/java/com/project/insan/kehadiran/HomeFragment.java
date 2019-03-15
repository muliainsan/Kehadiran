package com.project.insan.kehadiran;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.insan.kehadiran.adapter.MoviesAdapter;
import com.project.insan.kehadiran.services.ApiClient;
import com.project.insan.kehadiran.services.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private final static String API_Key = "b22f426e41174091c4c6bfa16086e1db";

    //    LinearLayoutManager manager = new LinearLayoutManager(this);
    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        SwipeRefreshLayout pullToRefresh;
//        final MoviesAdapter madapter = new MoviesAdapter;
//        final RecyclerView rv = new RecyclerView;


        final View rootView;

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Log.e("1q1q", "asdf");

        final Spinner spinner = (Spinner) rootView.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Now Playing");
        categories.add("Up Coming");
        categories.add("Top Rated");

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, categories);
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
                            RecyclerView rv = rootView.findViewById(R.id.rv_movie);
                            int statusCode = response.code();
                            List<Movie.ResultsBean> movies = response.body().getResults();
                            Log.e("1q1q", String.valueOf(statusCode));
                            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                            MoviesAdapter madapter = new MoviesAdapter(movies, getContext());
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
                            RecyclerView rv = rootView.findViewById(R.id.rv_movie);
                            int statusCode = response.code();
                            List<Movie.ResultsBean> movies = response.body().getResults();
                            Log.e("1q1q", String.valueOf(statusCode));
                            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                            MoviesAdapter madapter = new MoviesAdapter(movies, getContext());
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
                            RecyclerView rv = rootView.findViewById(R.id.rv_movie);
                            int statusCode = response.code();
                            List<Movie.ResultsBean> movies = response.body().getResults();
                            Log.e("1q1q", String.valueOf(statusCode));
                            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                            MoviesAdapter madapter = new MoviesAdapter(movies, getContext());
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




//        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//        Call<Movie> call = apiService.getNowPlaying(API_Key);
//        call.enqueue(new Callback<Movie>() {
//            @Override
//            public void onResponse(Call<Movie> call, Response<Movie> response) {
//                RecyclerView rv = rootView.findViewById(R.id.rv_movie);
//                int statusCode = response.code();
//                List<Movie.ResultsBean> movies = response.body().getResults();
//                Log.e("1q1q", String.valueOf(statusCode));
//                rv.setLayoutManager(new LinearLayoutManager(getActivity()));
//                MoviesAdapter madapter = new MoviesAdapter(movies, getContext());
//                rv.setAdapter(madapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<Movie> call, Throwable t) {
//                Log.e("xxx", t.toString());
//            }
//        });





        return rootView;
    }


}
