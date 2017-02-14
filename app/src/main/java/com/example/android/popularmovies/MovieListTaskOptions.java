package com.example.android.popularmovies;

/**
 * Created by calin on 10/02/2017.
 */

public class MovieListTaskOptions {

	private String urlPart;
	private Integer page;
	/**
	 * true for neverending scroll
	 */
	private boolean addResults;

	public MovieListTaskOptions(String urlPart, Integer page, boolean addResults) {
		this.urlPart = urlPart;
		this.page = page;
		this.addResults = addResults;
	}

	public String getUrlPart() {
		return urlPart;
	}

	public void setUrlPart(String urlPart) {
		this.urlPart = urlPart;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public boolean isAddResults() {
		return addResults;
	}

	public void setAddResults(boolean addResults) {
		this.addResults = addResults;
	}
}
