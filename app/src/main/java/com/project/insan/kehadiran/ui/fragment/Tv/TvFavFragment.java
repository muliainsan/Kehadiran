package com.project.insan.kehadiran.ui.fragment.Tv;

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
import com.project.insan.kehadiran.adapter.TvAdapter;
import com.project.insan.kehadiran.java.Tv;
import com.project.insan.kehadiran.services.DataHelper;

import java.util.ArrayList;
import java.util.List;


public class TvFavFragment extends Fragment implements View.OnClickListener {
    public static Object SavedState;
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private ActionBar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private ProgressBar pBar;
    private Tv.ResultsBean mm;
    List<Tv.ResultsBean> tvs = new ArrayList<>();
    private RecyclerView rv;
    private DataHelper db;
    public TvFavFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tvfav, container, false);
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
        tvs = new ArrayList<>();
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
    tvs = db.getAllRecord();
    Log.i("ffffggg", String.valueOf(tvs.size()));
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    rv.setLayoutManager(layoutManager);
    TvAdapter madapter = new TvAdapter(tvs, getActivity());
    rv.setAdapter(madapter);
}

    @Override
    public void onClick(View v) {

    }
}
