package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.popularmovies.domain.Movie;

public class MovieListActivity extends AppCompatActivity implements MovieListClickHandler {

	private String mdbUrlBase;
	private String mdbApiKeyParam;
	private String mdbApiKey;
	private String mdbUrlTopRated;
	private String mdbUrlPopular;
	private String mdbUrlPageParam;
	private String mdbUrlLanguageParam;

	private LinearLayout tvNoApiKey;
	private RecyclerView rvMovies;
	private TextView tvTest;
	private TextView tvNetworkProblem;

	private ProgressBar progressBar;

	private MovieListAdapter movieListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_list);

		tvNoApiKey = (LinearLayout)findViewById(R.id.ll_no_api_key);
		rvMovies = (RecyclerView)findViewById(R.id.rv_movies);
		tvNetworkProblem = (TextView)findViewById(R.id.tv_movie_list_network_problem);

		progressBar = (ProgressBar)findViewById(R.id.pb_movie_list);

		mdbUrlBase = getResources().getString(R.string.movie_db_url_base);
		mdbApiKeyParam = getResources().getString(R.string.movie_db_param_api_key);
		mdbApiKey = getResources().getString(R.string.movie_db_api_key);
		mdbUrlTopRated = getResources().getString(R.string.movie_db_url_top_rated);
		mdbUrlPopular = getResources().getString(R.string.movie_db_url_popular);
		mdbUrlPageParam = getResources().getString(R.string.movie_db_param_page);
		mdbUrlLanguageParam = getResources().getString(R.string.movie_db_param_language);

		if (mdbApiKey == null || mdbApiKey.trim().length() == 0) {
			tvNoApiKey.setVisibility(View.VISIBLE);
			rvMovies.setVisibility(View.INVISIBLE);
			return;
		}


		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
		rvMovies.setLayoutManager(layoutManager);
		rvMovies.setHasFixedSize(true);
		movieListAdapter = new MovieListAdapter(this);
		rvMovies.setAdapter(movieListAdapter);



		getPopularMovies();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_movie_list_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.menu_popular) {
			getPopularMovies();
			return true;
		}

		if (id == R.id.menu_top_rated) {
			getTopRatedMovies();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	private void getPopularMovies() {
		progressBar.setVisibility(View.VISIBLE);
		new MovieListTask(this, movieListAdapter).execute(new MovieListTaskOptions(mdbUrlPopular, null, false));
	}

	private void getTopRatedMovies() {
		progressBar.setVisibility(View.VISIBLE);
		new MovieListTask(this, movieListAdapter).execute(new MovieListTaskOptions(mdbUrlTopRated, null, false));
	}



	@Override
	public void onClick(Movie movie) {
		Intent intentMovieDetail = new Intent(this, MovieDetailActivity.class);
		intentMovieDetail.putExtra(Intent.EXTRA_TEXT, String.valueOf(movie.getId()));
		startActivity(intentMovieDetail);
	}

	public String getMdbUrlBase() {
		return mdbUrlBase;
	}

	public String getMdbApiKeyParam() {
		return mdbApiKeyParam;
	}

	public String getMdbApiKey() {
		return mdbApiKey;
	}

	public String getMdbUrlTopRated() {
		return mdbUrlTopRated;
	}

	public String getMdbUrlPopular() {
		return mdbUrlPopular;
	}

	public LinearLayout getTvNoApiKey() {
		return tvNoApiKey;
	}

	public RecyclerView getRvMovies() {
		return rvMovies;
	}

	public TextView getTvTest() {
		return tvTest;
	}

	public String getMdbUrlPageParam() {
		return mdbUrlPageParam;
	}

	public String getMdbUrlLanguageParam() {
		return mdbUrlLanguageParam;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public TextView getTvNetworkProblem() {
		return tvNetworkProblem;
	}
}
