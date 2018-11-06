package com.example.rtwm38.movieapp.viewcontroller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.rtwm38.movieapp.R;

public class MovieViewHolder1 extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public MovieViewHolder1(View itemView)
    {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
    }
}
