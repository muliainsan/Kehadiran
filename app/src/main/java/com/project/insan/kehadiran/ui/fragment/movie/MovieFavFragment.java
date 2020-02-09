package com.project.insan.kehadiran.ui.fragment.movie;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.adapter.MoviesAdapter;
import com.project.insan.kehadiran.java.Movie;
import com.project.insan.kehadiran.services.DataHelper;

import java.util.ArrayList;
import java.util.List;


public class MovieFavFragment extends Fragment implements View.OnClickListener {
    public static Object SavedState;
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private ActionBar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private ProgressBar pBar;
    private Movie.ResultsBean mm;
    List<Movie.ResultsBean> movies = new ArrayList<>();
    private RecyclerView rv;
    private DataHelper db;
    public MovieFavFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_moviefav, container, false);
//        Log.e("1q1q", "asdf");

        db = new DataHelper(getActivity());
        swipeLayout = view.findViewById(R.id.swipe_container);
        rv = view.findViewById(R.id.rv_movie);
        pBar = view.findViewById(R.id.pBar);
        pBar.setVisibility(view.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                pBar.setVisibility(view.GONE);
                swipeLayout.setRefreshing(false);
            }
        }, 1000);
        movies = new ArrayList<>();
        getData();

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });


        return view;
    }

void getData(){
    movies = db.getAllRecordMovie();
    Log.i("ffffggg", String.valueOf(movies.size()));
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    rv.setLayoutManager(layoutManager);
    MoviesAdapter madapter = new MoviesAdapter(movies, getActivity());
    rv.setAdapter(madapter);
}

    @Override
    public void onClick(View v) {

    }
}
