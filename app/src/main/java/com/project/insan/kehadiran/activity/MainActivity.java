package com.project.insan.kehadiran.activity;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.SettingsActivity;
import com.project.insan.kehadiran.adapter.TabAdapter;
import com.project.insan.kehadiran.fragment.FavoriteFragment;
import com.project.insan.kehadiran.fragment.movie.MovieFragment;
import com.project.insan.kehadiran.fragment.Tv.TvFragment;

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
    Fragment active= movie_fragment;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);


        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        loadFragment(movie_fragment);
        bottomNavigationView =  findViewById(R.id.bottom_nav); bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.movie:
                        active = movie_fragment;
                        break;
                    case R.id.tv:
                        active = tv_fragment;
                        break;
                    case R.id.favorite:
                        active = new FavoriteFragment();
                        break;
                }
                return loadFragment(active);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.settings)
                {
//                    Intent intent = new Intent(MainActivity.this, Language.class);
                    Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                }else if(menuItem.getItemId()==R.id.search){
                    SearchManager searchManager = (SearchManager) getSystemService(MainActivity.this.SEARCH_SERVICE);

                    if (searchManager != null) {
                        SearchView searchView = (SearchView) menuItem.getActionView();
                        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                        searchView.setQueryHint("Search");

                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, SearchResultActivity.class);
                                intent.putExtra("query", query);
                                startActivity(intent);
                                return true;
                            }
                            @Override
                            public boolean onQueryTextChange(String newText) {
                                return false;
                            }
                        });
                    }
                }
                return false;
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
