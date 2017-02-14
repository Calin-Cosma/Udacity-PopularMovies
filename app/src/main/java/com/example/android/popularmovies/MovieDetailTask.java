package com.example.android.popularmovies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.android.popularmovies.domain.Movie;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


/**
 * Created by calin on 09/02/2017.
 */

public class MovieDetailTask extends AsyncTask<String, Void, Movie> {


	private MovieDetailActivity activity;

	public MovieDetailTask(MovieDetailActivity activity) {
		this.activity = activity;
	}

	@Override
	protected Movie doInBackground(String... movieIds) {
		if (movieIds.length == 0 || movieIds[0] == null) {
			return null;
		}

		if (!NetworkUtils.isOnline(activity))
			return null;

		RestTemplate restTemplate = new RestTemplate();
		String url = activity.getMdbUrlBase() + activity.getMdbUrlDetails() +
				"/" + movieIds[0] +
				"?" + activity.getMdbApiKeyParam() + "=" + activity.getMdbApiKey();

		String json = restTemplate.getForObject(url, String.class);
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();
		Movie movie = gson.fromJson(json, Movie.class);

		return movie;
	}

	@Override
	protected void onPostExecute(Movie movie) {

		if (movie == null) {
			activity.getProgressBar().setVisibility(View.INVISIBLE);
			activity.getTvNetworkProblem().setVisibility(View.VISIBLE);
			activity.getLlMovieDetail().setVisibility(View.INVISIBLE);
			return;
		}

		activity.getTvNetworkProblem().setVisibility(View.INVISIBLE);

//		Picasso.with(activity)
//				.load(Uri.parse(imageUrlBase + imageUrlSize + movie.getBackdropPath()))
//				.into(imageBackdrop);
		Picasso.with(activity)
				.load(Uri.parse(activity.getImageUrlBase() + activity.getImageUrlSize() + movie.getPosterPath()))
				.into(activity.getImagePoster());
		activity.getTvTitle().setText(movie.getTitle());

		String date = "";
		try {
			date = new SimpleDateFormat(activity.getDateFormat()).format(movie.getReleaseDate());
		} catch (Exception e) {
			Log.w(MovieDetailTask.class.getName(), "There was a problem formatting the date", e);
		}
		activity.getTvDate().setText(date);
		if (movie.getVoteAverage() != null)
			activity.getTvRating().setText(new DecimalFormat("#.#").format(movie.getVoteAverage()));

		activity.getTvSynopsis().setText(movie.getOverview());

		activity.getProgressBar().setVisibility(View.INVISIBLE);
		activity.getLlMovieDetail().setVisibility(View.VISIBLE);
	}
}
