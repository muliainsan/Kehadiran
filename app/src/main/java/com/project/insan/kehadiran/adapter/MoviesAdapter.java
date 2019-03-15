package com.project.insan.kehadiran.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.insan.kehadiran.Movie;
import com.project.insan.kehadiran.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{
    private List<Movie.ResultsBean> list;
//    private int rowLayout;
    private Context context;

    public MoviesAdapter(List<Movie.ResultsBean> list, Context context) {
        this.list = list;
//        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movies,viewGroup,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
//        Log.e("ciacia",list.get(2).getTitle());
        holder.title.setText(list.get(i).getTitle());

        holder.desc.setText(list.get(i).getOverview());
        holder.rate.setText(String.valueOf(list.get(i).getVote_average()));
//        holder.image.setText(list.get(i).getTitle());
        Picasso picasso = Picasso.with(context);
        picasso.setIndicatorsEnabled(true);
        picasso.load("http://image.tmdb.org/t/p/w500/"+list.get(i).getPoster_path()) //Load the image
////                    .placeholder(R.drawable.ic_placeholder) //Image resource that act as placeholder
////                    .error(R.drawable.ic_error) //Image resource for error
////                    .resize(300, 500)  // Post processing - Resizing the image
                .into(holder.image); // View where image is loaded.


    }

    @Override
    public int getItemCount() {
        return list.size();
    }





    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout moviesLayout;
        private  TextView title;
        private  TextView desc;
        private TextView rate;
        private ImageView image;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            moviesLayout = itemView.findViewById(R.id.movies_layout);
            title = itemView.findViewById(R.id.tv_judul);
            desc = itemView.findViewById(R.id.tv_desc);
            rate = itemView.findViewById(R.id.tv_rate);
            image = itemView.findViewById(R.id.iv_poster);

        }
    }
}
