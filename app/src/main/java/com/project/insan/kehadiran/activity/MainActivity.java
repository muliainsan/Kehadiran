package com.project.insan.kehadiran.activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.adapter.TabAdapter;
import com.project.insan.kehadiran.fragment.MovieFragment;
import com.project.insan.kehadiran.fragment.TvFragment;

import java.util.Locale;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity{
    private static String API_Key = "b22f426e41174091c4c6bfa16086e1db";
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeLayout;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    DetailActivity detailActivity;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieFragment(), getResources().getString(R.string.tab_movie));
        adapter.addFragment(new TvFragment(), getResources().getString(R.string.tab_movie));
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
