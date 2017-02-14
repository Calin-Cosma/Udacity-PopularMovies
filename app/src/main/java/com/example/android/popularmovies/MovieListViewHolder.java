package com.example.android.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by calin on 09/02/2017.
 */

public class MovieListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

	private MovieListAdapter movieListAdapter;
	private ImageView movieThumb;

	public MovieListViewHolder(View itemView, MovieListAdapter movieListAdapter) {
		super(itemView);
		this.movieListAdapter = movieListAdapter;
		movieThumb = (ImageView)itemView.findViewById(R.id.i_movie_thumb);
		itemView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int adapterPosition = getAdapterPosition();

		movieListAdapter.getMovieListClickHandler().onClick(movieListAdapter.getMovies().get(adapterPosition));
	}

	public ImageView getMovieThumb() {
		return movieThumb;
	}

	public void setMovieThumb(ImageView movieThumb) {
		this.movieThumb = movieThumb;
	}
}
