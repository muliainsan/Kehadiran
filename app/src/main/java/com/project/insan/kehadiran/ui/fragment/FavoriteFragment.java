package com.project.insan.kehadiran.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.adapter.TabAdapter;
import com.project.insan.kehadiran.ui.fragment.Tv.TvFavFragment;
import com.project.insan.kehadiran.ui.fragment.movie.MovieFavFragment;


public class FavoriteFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private MovieFavFragment movie_fragment = new MovieFavFragment();
    private TvFavFragment tv_fragment =new TvFavFragment();
    private FragmentManager manager;
    public FavoriteFragment() {
        // Required empty public constructor
    }
//
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getFragmentManager());
        manager = getFragmentManager() ;
        adapter.addFragment(movie_fragment, getResources().getString(R.string.tab_movie));
        adapter.addFragment(tv_fragment, getResources().getString(R.string.tab_tv));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
