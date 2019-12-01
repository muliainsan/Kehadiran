package com.project.insan.kehadiran.fragment.Tv;

import android.os.Bundle;
import android.os.Handler;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.insan.kehadiran.adapter.MoviesAdapter;
import com.project.insan.kehadiran.adapter.TvAdapter;
import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.java.Tv;
import com.project.insan.kehadiran.services.ApiClient;
import com.project.insan.kehadiran.services.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TvFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private ActionBar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private ProgressBar pBar;
    private static final String STATE_RESULT = "state_result";
    List<Tv.ResultsBean> tvs= new ArrayList<>();
    List<Tv.ResultsBean> tvs2 = new ArrayList<>();
    private RecyclerView rv;
    public TvFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        Parcelable p = movies;
        outState.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) tvs);
        outState.putParcelableArrayList("list2", (ArrayList<? extends Parcelable>) tvs2);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tv, container, false);
        Log.e("1q1q", "asdf");
        swipeLayout = view.findViewById(R.id.swipe_container);
        pBar = view.findViewById(R.id.pBar);
        rv = view.findViewById(R.id.rv_movie);
        final Spinner spinner =  view.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                pBar.setVisibility(view.GONE);
                swipeLayout.setRefreshing(false);
            }
        }, 1000);

        categories.add(getResources().getString(R.string.airing_today));
        categories.add(getResources().getString(R.string.popular));

        final ArrayAdapter<String> dataAdapter ;
        dataAdapter = new ArrayAdapter<String>(  getActivity(),android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
               getdataTV(position,savedInstanceState);
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
                    getdataTV(spinner.getSelectedItemPosition(),savedInstanceState);
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });


        return view;
    }


    void getdataTV(int position, final Bundle savedInstanceState){
        if(position==0) {
            final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<Tv> call = apiService.getTVairingToday(API_Key);
            call.enqueue(new Callback<Tv>() {
                @Override
                public void onResponse(Call<Tv> call, Response<Tv> response) {
                    int statusCode = response.code();
                    tvs = response.body().getResults();
                    Log.e("1q1q", String.valueOf(statusCode));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(layoutManager);
                    TvAdapter madapter = new TvAdapter(tvs,  getActivity());
                    rv.setAdapter(madapter);

                }

                @Override
                public void onFailure(Call<Tv> call, Throwable t) {
                    Log.e("xxx", t.toString());
                    Toast.makeText(getActivity(), "No Internet Access", Toast.LENGTH_SHORT).show();
                    if (savedInstanceState != null) {
                        tvs =  savedInstanceState.getParcelableArrayList("list");
                        Log.i("ffffggg", String.valueOf(tvs.size()));
//                        if(tvs.size()!=0) {
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            rv.setLayoutManager(layoutManager);
                            TvAdapter madapter = new TvAdapter(tvs, getActivity());
                            rv.setAdapter(madapter);
//                        }
                    }
                }
            });

        }else if(position==1){
            final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<Tv> call = apiService.getTVPopular(API_Key);
            call.enqueue(new Callback<Tv>() {
                @Override
                public void onResponse(Call<Tv> call, Response<Tv> response) {
                    int statusCode = response.code();
                    tvs = response.body().getResults();
                    Log.e("1q1q", String.valueOf(statusCode));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(layoutManager);
                    TvAdapter madapter = new TvAdapter(tvs,  getActivity());
                    rv.setAdapter(madapter);

                }

                @Override
                public void onFailure(Call<Tv> call, Throwable t) {
                    Log.e("xxx", t.toString());
                    Toast.makeText(getActivity(), "No Internet Access", Toast.LENGTH_SHORT).show();
                    if (savedInstanceState != null) {
                        tvs =  savedInstanceState.getParcelableArrayList("list");
                        Log.i("ffffggg", String.valueOf(tvs.size()));
//                        if(tvs.size()!=0) {
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            rv.setLayoutManager(layoutManager);
                            TvAdapter madapter = new TvAdapter(tvs, getActivity());
                            rv.setAdapter(madapter);
//                        }
                    }
                }
            });

        }
    }
}
