package com.example.android.popularmovies;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.domain.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by calin on 09/02/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {

	private MovieListClickHandler movieListClickHandler;
	private ArrayList<Movie> movies = new ArrayList<>();

	private ViewGroup parent;

	private String imageUrlBase;
	private String imageUrlSize;

	public MovieListAdapter(MovieListClickHandler movieListClickHandler) {
		this.movieListClickHandler = movieListClickHandler;
	}

	@Override
	public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		this.parent = parent;
		Context context = parent.getContext();

		imageUrlBase = parent.getResources().getString(R.string.movie_db_img_url_base);
		imageUrlSize = parent.getResources().getString(R.string.movie_db_img_url_size);

		int layoutIdForListItem = R.layout.part_movie_list_item;
		LayoutInflater inflater = LayoutInflater.from(context);
		boolean shouldAttachToParentImmediately = false;

		View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
		return new MovieListViewHolder(view, this);
	}


	@Override
	public void onBindViewHolder(MovieListViewHolder holder, int position) {
		Movie movie = movies.get(position);

		Picasso.with(parent.getContext())
				.load(Uri.parse(imageUrlBase + imageUrlSize + movie.getPosterPath()))
				.into(holder.getMovieThumb());

	}

	@Override
	public int getItemCount() {
		return movies == null ? 0 : movies.size();
	}


	public void add(List<Movie> moreMovies) {
		movies.addAll(moreMovies);
		notifyDataSetChanged();
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
		notifyDataSetChanged();
	}

	public void setMovies(List<Movie> movies) {
		this.movies.clear();
		this.movies.addAll(movies);
		notifyDataSetChanged();
	}

	public MovieListClickHandler getMovieListClickHandler() {
		return movieListClickHandler;
	}
}
