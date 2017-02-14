package com.example.android.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.View;

import com.example.android.popularmovies.domain.Results;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.web.client.RestTemplate;


/**
 * Created by calin on 09/02/2017.
 */

public class MovieListTask extends AsyncTask<MovieListTaskOptions, Void, Results> {

	private MovieListActivity activity;
	private MovieListAdapter adapter;
	private boolean add;

	public MovieListTask(MovieListActivity activity, MovieListAdapter adapter) {
		this.activity = activity;
		this.adapter = adapter;
	}

	@Override
	protected Results doInBackground(MovieListTaskOptions... optionsArray) {
		if (optionsArray.length == 0 || optionsArray[0] == null) {
			return null;
		}

		if (!NetworkUtils.isOnline(activity))
			return null;

		MovieListTaskOptions options = optionsArray[0];
		this.add = options.isAddResults();

		String url = activity.getMdbUrlBase() + options.getUrlPart() +"?" + activity.getMdbApiKeyParam() + "=" + activity.getMdbApiKey();
		if (options.getPage() != null)
			url += "&" + activity.getMdbUrlPageParam() + "=" + options.getPage();

		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.getForObject(url, String.class);
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();
		Results results = gson.fromJson(json, Results.class);

		return results;
	}

	@Override
	protected void onPostExecute(Results results) {

		if (results == null) {
			activity.getProgressBar().setVisibility(View.INVISIBLE);
			activity.getTvNetworkProblem().setVisibility(View.VISIBLE);
			activity.getRvMovies().setVisibility(View.INVISIBLE);
			return;
		}
		activity.getTvNetworkProblem().setVisibility(View.INVISIBLE);
		activity.getRvMovies().setVisibility(View.VISIBLE);

		if (add)
			adapter.add(results.getResults());
		else
			adapter.setMovies(results.getResults());

		activity.getProgressBar().setVisibility(View.INVISIBLE);
	}

}
