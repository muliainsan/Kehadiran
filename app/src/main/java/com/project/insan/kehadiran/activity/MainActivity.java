package com.project.insan.kehadiran.activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.adapter.TabAdapter;
import com.project.insan.kehadiran.fragment.FavoriteFragment;
import com.project.insan.kehadiran.fragment.MovieFragment;
import com.project.insan.kehadiran.fragment.TvFragment;

public class MainActivity extends AppCompatActivity{
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MovieFragment movie_fragment = new MovieFragment();
    private TvFragment tv_fragment =new TvFragment();
    private FavoriteFragment favorite_fragement =new FavoriteFragment();
    private BottomNavigationView bottomNavigationView;
    private FragmentManager manager;
    Fragment active= movie_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);

        bottomNavigationView =  findViewById(R.id.bottom_nav);
//        viewPager = findViewById(R.id.viewPager);
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        adapter = new TabAdapter(getSupportFragmentManager());
//        manager = getSupportFragmentManager();
//        adapter.addFragment(movie_fragment, getResources().getString(R.string.tab_movie));
//        adapter.addFragment(tv_fragment, getResources().getString(R.string.tab_tv));
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                if(item.getItemId()==R.id.english)
//                {
//                    Intent intent = new Intent(MainActivity.this, Language.class);
//                    startActivity(intent);
//                }
//                return false;
//            }
//        });

//        manager.beginTransaction().add(R.id.fragment, movie_fragment, "Movie").hide(movie_fragment).commit();
//        manager.beginTransaction().add(R.id.fragment, tv_fragment, "Tv").hide(tv_fragment).commit();
//        manager.beginTransaction().add(R.id.fragment, movie_fragment, "Favorit").hide(movie_fragment).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.movie:
//                        manager.beginTransaction().hide(active).show(movie_fragment).commit();
                        active = movie_fragment;
                        break;
                    case R.id.tv:
//                        manager.beginTransaction().hide(active).show(tv_fragment).commit();
                        active = tv_fragment;
                        break;
                    case R.id.favorite:
                        active = favorite_fragement;
                        break;
                }
                return loadFragment(active);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
