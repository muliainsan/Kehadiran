package com.project.insan.kehadiran.activity;
import android.content.Intent;

import androidx.annotation.RequiresApi;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;


import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.adapter.TabAdapter;
import com.project.insan.kehadiran.fragment.MovieFragment;
import com.project.insan.kehadiran.fragment.TvFragment;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity{
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MovieFragment movie_fragment = new MovieFragment();
    private TvFragment tv_fragment =new TvFragment();
    FragmentManager manager;
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putBoolean("SAVED", true);
//        FragmentManager manager = getSupportFragmentManager();
//        manager.putFragment(savedInstanceState, "MOVIE_FRAGMENT", movie_fragment);
//        manager.putFragment(savedInstanceState, "TV_FRAGMENT", tv_fragment);
//        super.onSaveInstanceState(savedInstanceState);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        manager = getSupportFragmentManager();
        Log.i("cvcv","luar");
//        if (savedInstanceState != null) {
//            Log.i("cvcv","masuk");
//            //Restore the fragment's instance
//            movie_fragment = (MovieFragment) manager.getFragment(savedInstanceState,"MOVIE_FRAGMENT");
//            tv_fragment = (TvFragment) manager.getFragment(savedInstanceState,"TV_FRAGMENT");
//        }

        adapter.addFragment(movie_fragment, getResources().getString(R.string.tab_movie));
        adapter.addFragment(tv_fragment, getResources().getString(R.string.tab_tv));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.english)
                {
                    Intent intent = new Intent(MainActivity.this, Language.class);
                    startActivity(intent);
                }
                return false;
            }
        });

    }


}
