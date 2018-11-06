package com.example.rtwm38.movieapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;


import com.example.rtwm38.movieapp.R;
import com.example.rtwm38.movieapp.model.Movie;
import com.example.rtwm38.movieapp.viewcontroller.MovieViewHolder1;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> implements Filterable {
    private List<Movie> movieList;
    private List<Movie> movieListFiltered;

    private LayoutInflater mInflater;
    private Context mContext;
    private ContactsAdapterListener listener;

    public MovieAdapter(Context context, ContactsAdapterListener listener) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.listener = listener;
       // this.movieList = new ArrayList<>();
        //movieListFiltered = new ArrayList<>();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public MovieViewHolder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(movieListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.movie_row, viewGroup, false);
        final MovieViewHolder viewHolder = new MovieViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movieList.get(position));
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie movie = movieListFiltered.get(i);
        Picasso.with(mContext)
                .load(movie.getPoster())
                .placeholder(R.color.colorAccent)
                .into(movieViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return (movieList == null) ? 0 : movieListFiltered.size();
    }

    public void setMovieList(final List<Movie> movieList)
    {
/*        this.movieList.clear();
        this.movieList.addAll(movieList);
        this.movieListFiltered = movieList;
        notifyDataSetChanged();*/
        if (this.movieList == null) {
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            notifyItemChanged(0, movieListFiltered.size());
        }
        else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return MovieAdapter.this.movieList.size();
                }

                @Override
                public int getNewListSize() {
                    return movieList.size();
                }

                @Override
                public boolean areItemsTheSame(int i, int i1) {
                    return MovieAdapter.this.movieList.get(i).getTitle() == movieList.get(i1).getTitle();
                }

                @Override
                public boolean areContentsTheSame(int i, int i1) {
                    Movie newMovie = MovieAdapter.this.movieList.get(i);
                    Movie oldMovie = movieList.get(i1);
                    return newMovie.getTitle() == oldMovie.getTitle() ;
                }
            });
            this.movieList = movieList;
            this.movieListFiltered = movieList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    movieListFiltered = movieList;
                } else {
                    List<Movie> filteredList = new ArrayList<>();
                    for (Movie movie : movieList) {
                        if (movie.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            //Log.d("jyotitalkies", movie.getTitle()+"----"+charString);
                            filteredList.add(movie);
                        }
                    }
                    movieListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                //filterResults.count = filteredList.size();
                filterResults.values = movieListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                movieListFiltered = (ArrayList<Movie>) filterResults.values;
                Log.d("jyotitalkies", "result size is - "+filterResults.count);
                movieList = (ArrayList<Movie>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ContactsAdapterListener {
        void onContactSelected(Movie movie);
    }
}
