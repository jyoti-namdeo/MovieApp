package com.example.rtwm38.movieapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.rtwm38.movieapp.R;
import com.example.rtwm38.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";

    private Movie mMovie;
    ImageView backdrop;
    ImageView poster;
    TextView title;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(mMovie.getTitle());
        setSupportActionBar(toolbar);
        //CollapsingToolbarLayout toolbarLayout =  findViewById(R.id.toolbar_layout);
        //toolbarLayout.setTitle(mMovie.getTitle());

      //  backdrop = findViewById(R.id.backdrop);
        title = findViewById(R.id.showTitle);
        description = findViewById(R.id.movie_description);
        poster = findViewById(R.id.movie_poster);
        //title.setText(mMovie.getTitle());
        description.setText(mMovie.getDescription());
        Picasso.with(this)
                .load(mMovie.getPoster())
                .into(poster);

    }

}
