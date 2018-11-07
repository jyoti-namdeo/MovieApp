package com.example.rtwm38.movieapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.rtwm38.movieapp.R;
import com.example.rtwm38.movieapp.model.Movie;
import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";

    private Movie mMovie;
    private ImageView poster;
    private TextView title;
    private TextView description;

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieDetailActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            LOGGER.error("Movie parcel is not received");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(mMovie.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        title = findViewById(R.id.showTitle);
        description = findViewById(R.id.movie_description);
        poster = findViewById(R.id.movie_poster);
        description.setText(mMovie.getDescription());
        Picasso.with(this)
                .load(mMovie.getPoster())
                .into(poster);

    }

}
