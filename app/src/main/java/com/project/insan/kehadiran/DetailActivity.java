package com.project.insan.kehadiran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.insan.kehadiran.java.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView Title, Rate, Release, Detail;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Movie.ResultsBean movie= intent.getParcelableExtra("value");

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
        picasso.load("http://image.tmdb.org/t/p/w500/"+movie.getPoster_path()) //Load the image
////                    .placeholder(R.drawable.ic_placeholder) //Image resource that act as placeholder
////                    .error(R.drawable.ic_error) //Image resource for error
////                    .resize(300, 500)  // Post processing - Resizing the image
                .into(image); // View where image is loaded.


    }

}
