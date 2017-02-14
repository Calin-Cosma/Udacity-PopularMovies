package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

public class MovieDetailActivity extends AppCompatActivity {

	private String mdbUrlBase;
	private String mdbApiKey;
	private String mdbApiKeyParam;
	private String mdbUrlDetails;

	private String imageUrlBase;
	private String imageUrlSize;

	private String dateFormat;

	private String movieId;

	private ImageView imageBackdrop;
	private ImageView imagePoster;
	private TextView tvTitle;
	private TextView tvDate;
	private TextView tvRating;
	private TextView tvSynopsis;
	private TextView tvNetworkProblem;

	private ScrollView llMovieDetail;

	private ProgressBar progressBar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);


//		imageBackdrop = (ImageView)findViewById(R.id.i_details_backdrop);
		imagePoster = (ImageView)findViewById(R.id.i_details_poster);
		tvTitle = (TextView)findViewById(R.id.tv_details_title);
		tvDate = (TextView)findViewById(R.id.tv_details_date);
		tvRating = (TextView)findViewById(R.id.tv_details_rating);
		tvSynopsis = (TextView)findViewById(R.id.tv_details_synopsis);
		tvNetworkProblem = (TextView)findViewById(R.id.tv_movie_details_network_problem);

		progressBar = (ProgressBar)findViewById(R.id.pb_movie_details);

		llMovieDetail = (ScrollView)findViewById(R.id.sw_movie_detail);

		mdbApiKey = getResources().getString(R.string.movie_db_api_key);
		mdbUrlBase = getResources().getString(R.string.movie_db_url_base);
		mdbApiKeyParam = getResources().getString(R.string.movie_db_param_api_key);
		mdbUrlDetails = getResources().getString(R.string.movie_db_url_movie_details);

		imageUrlBase = getResources().getString(R.string.movie_db_img_url_base);
		imageUrlSize = getResources().getString(R.string.movie_db_img_url_size);

		dateFormat = getResources().getString(R.string.date_format);

		Intent intent = getIntent();

		if (intent != null) {
			if (intent.hasExtra(Intent.EXTRA_TEXT)) {
				movieId = intent.getStringExtra(Intent.EXTRA_TEXT);

				progressBar.setVisibility(View.VISIBLE);
				new MovieDetailTask(this).execute(movieId);
			}
		}
	}

	public String getMdbUrlBase() {
		return mdbUrlBase;
	}

	public String getMdbApiKey() {
		return mdbApiKey;
	}

	public String getMdbApiKeyParam() {
		return mdbApiKeyParam;
	}

	public String getMdbUrlDetails() {
		return mdbUrlDetails;
	}

	public String getImageUrlBase() {
		return imageUrlBase;
	}

	public String getImageUrlSize() {
		return imageUrlSize;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public String getMovieId() {
		return movieId;
	}

	public ImageView getImageBackdrop() {
		return imageBackdrop;
	}

	public ImageView getImagePoster() {
		return imagePoster;
	}

	public TextView getTvTitle() {
		return tvTitle;
	}

	public TextView getTvDate() {
		return tvDate;
	}

	public TextView getTvRating() {
		return tvRating;
	}

	public TextView getTvSynopsis() {
		return tvSynopsis;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public TextView getTvNetworkProblem() {
		return tvNetworkProblem;
	}

	public ScrollView getLlMovieDetail() {
		return llMovieDetail;
	}
}
