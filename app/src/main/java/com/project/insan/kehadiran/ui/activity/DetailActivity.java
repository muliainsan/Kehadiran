package com.project.insan.kehadiran.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.project.insan.kehadiran.java.Movie;
import com.project.insan.kehadiran.services.DataHelper;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView Title, Rate, Release, Detail;
    private ImageView image;
    private Toolbar toolbar;
    private DataHelper db;
    private Movie.ResultsBean movie;
    private boolean saved = true;
    private Bitmap bitmap;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.i("asas", String.valueOf(saved(movie)));
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.favorit,menu);

        MenuItem favoriteItem = menu.findItem(R.id.action_favorite);
        if(saved(movie)){
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
        movie= intent.getParcelableExtra("value");
        db = new DataHelper(this);



        saved(movie);

        Log.i("asdf",movie.getTitle());
        Title = findViewById(R.id.tv_title);
        Rate = findViewById(R.id.tv_rate);
        Release = findViewById(R.id.tv_releasedate);
        Detail = findViewById(R.id.tv_detail);
        image = findViewById(R.id.imageView);

        Title.setText(movie.getTitle());
        Rate.setText(String.valueOf(movie.getVote_average()));
        Release.setText(movie.getRelease_date());
        Detail.setText(movie.getOverview());

        Picasso picasso = Picasso.with(DetailActivity.this);
        picasso.setIndicatorsEnabled(true);
        picasso.load("http://image.tmdb.org/t/p/w500/"+movie.getPoster_path())
                .into(image);

//        image.invalidate();
//        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
//        Bitmap bitmap = null;
//        if(drawable.getBitmap() != null) {
//            bitmap = drawable.getBitmap();
//        }

        final Bitmap finalBitmap = bitmap;
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.i("asasas", String.valueOf(menuItem.getItemId()));
                if(menuItem.getItemId()==R.id.action_favorite)
                {
                    if(saved(movie)){
                        Log.i("dddd", "ganti putih");
                        db.deleteMovie(movie);
                        menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_white_24dp));
                        saved=false;
                    }else{
                        Log.i("dddd", "ganti merah");
                        db.addRecordMovie(movie);
                        menuItem.setIcon(getResources().getDrawable(R.drawable.ic_favorite_red_24dp));
                        saved=true;
                    }

                }
                return false;
            }
        });


    }
    private boolean saved(Movie.ResultsBean movie){
//        this.deleteDatabase(db.DATABASE_NAME);
        boolean save=false;

        if(db.getMovie(movie.getId()).getId()==movie.getId()){
            save = true;
        }else{
            save = false;
        }
        return save;
    }

//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//    }

}
