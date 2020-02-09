package com.project.insan.kehadiran.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.insan.kehadiran.ui.activity.DetailtvActivity;
import com.project.insan.kehadiran.R;
import com.project.insan.kehadiran.java.Tv;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder>{
    private List<Tv.ResultsBean> list;
//    private int rowLayout;
    private Context context;

    public TvAdapter(List<Tv.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movies,viewGroup,false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, final int i) {
//        Log.e("ciacia",list.get(2).getTitle());
        holder.title.setText(list.get(i).getName());

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

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tv.ResultsBean list2 = list.get(i);
                Intent intent = new Intent(context, DetailtvActivity.class);
                intent.putExtra("value2", list2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class TvViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout TvsLayout;
        private  TextView title;
        private  TextView desc;
        private TextView rate;
        private ImageView image;
        private LinearLayout ll;
        public TvViewHolder(@NonNull View itemView) {
            super(itemView);

            TvsLayout = itemView.findViewById(R.id.movies_layout);
            title = itemView.findViewById(R.id.tv_judul);
            desc = itemView.findViewById(R.id.tv_desc);
            rate = itemView.findViewById(R.id.tv_rate);
            image = itemView.findViewById(R.id.iv_poster);
            ll = itemView.findViewById(R.id.movies_layout);

        }
    }
}
