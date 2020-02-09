package com.project.insan.kehadiran.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.java.Tv;
import com.project.insan.kehadiran.services.DataHelper;
import com.squareup.picasso.Picasso;

public class DetailtvActivity extends AppCompatActivity {
    public TextView Title, Rate, Release, Detail;
    ImageView image;
    private DataHelper db;
    private Tv.ResultsBean tv;
    private Toolbar toolbar;
    private boolean saved = true;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorit,menu);

        MenuItem favoriteItem = menu.findItem(R.id.action_favorite);
        if(saved(tv)){
            favoriteItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
        }else{
            favoriteItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = findViewById(R.id.toolbar2);
        toolbar.inflateMenu(R.menu.favorit);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        tv= intent.getParcelableExtra("value2");
        db = new DataHelper(this);
        Log.i("asdf",tv.getName());
        Title = findViewById(R.id.tv_title);
        Rate = findViewById(R.id.tv_rate);
        Release = findViewById(R.id.tv_releasedate);
        Detail = findViewById(R.id.tv_detail);
        image = findViewById(R.id.imageView);

        Title.setText(tv.getName());
        Rate.setText(String.valueOf(tv.getVote_average()));
        Release.setText(tv.getFirst_air_date());
        Detail.setText(tv.getOverview());

        Picasso picasso = Picasso.with(DetailtvActivity.this);
        picasso.setIndicatorsEnabled(true);
        picasso.load("http://image.tmdb.org/t/p/w500/"+tv.getPoster_path()) //Load the image
////                    .placeholder(R.drawable.ic_placeholder) //Image resource that act as placeholder
////                    .error(R.drawable.ic_error) //Image resource for error
////                    .resize(300, 500)  // Post processing - Resizing the image
                .into(image); // View where image is loaded.


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.i("asasas", String.valueOf(menuItem.getItemId()));
                if(menuItem.getItemId()==R.id.action_favorite)
                {
                    if(saved(tv)){
                        Log.i("dddd", "ganti putih");
                        db.deleteTv(tv);
                        menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
                        saved=false;
                    }else{
                        Log.i("dddd", "ganti merah");
                        db.addRecordTv(tv);
                        menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
                        saved=true;
                    }

                }
                return false;
            }
        });


    }


    private boolean saved(Tv.ResultsBean tv){
        boolean save=false;

        if(db.getTv(tv.getId()).getId()==tv.getId()){
            save = true;
        }else{
            save = false;
        }
        return save;
    }

}
