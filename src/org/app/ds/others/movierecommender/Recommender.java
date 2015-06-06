package org.app.ds.others.movierecommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Recommender {

	private Map<String, Movie> movies;

	private Map<String, User> users;

	private Map<String, Object[]> averageRatings;

	private Map<String, List<Object[]>> movieRatings;

	/**
	 * 
	 */
	public Recommender() {
		super();

		movies = new HashMap<String, Movie>();

		users = new HashMap<String, User>();

		movieRatings = new HashMap<String, List<Object[]>>();

		averageRatings = new HashMap<String, Object[]>();

	}

	private void loadMovies(String moviesFilePath) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(moviesFilePath)));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			String[] tokens = line.split(Pattern.quote("|"));
			String id = tokens[0].trim();
			String title = tokens[1].trim();

			movies.put(id, new Movie(id, title));

		}

		bufferedReader.close();
	}

	private void loadUsers(String usersFilePath) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(usersFilePath)));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			String[] tokens = line.split(Pattern.quote("|"));
			String id = tokens[0].trim();

			users.put(id, new User(id));
		}

		bufferedReader.close();
	}

	private void loadRatings(String ratingsFilePath) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(
				new File(ratingsFilePath)));

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			String[] tokens = line.split("\\s");
			String userId = tokens[0].trim();
			String movieId = tokens[1].trim();
			String rating = tokens[2].trim();

			List<Object[]> movieRatingsList = movieRatings.get(movieId);

			if (movieRatingsList == null) {
				movieRatingsList = new ArrayList<Object[]>();
			}

			movieRatingsList
					.add(new Object[] { userId, Double.valueOf(rating) });

			movieRatings.put(movieId, movieRatingsList);

			Object[] average = averageRatings.get(userId);

			if (average == null) {
				average = new Object[] { Double.valueOf(0), Double.valueOf(0) };
			}

			average[0] = (Double) average[0] + Double.valueOf(rating);
			average[1] = (Double) average[1] + 1;

			averageRatings.put(userId, average);
		}

		bufferedReader.close();
	}

	public void loadMovieDB(String basePath) throws IOException {
		loadMovies(basePath + File.separator + "u.item");
		loadUsers(basePath + File.separator + "u.user");
		loadRatings(basePath + File.separator + "u.data");
	}

	private void computeAverages() {
		for (Entry<String, Object[]> entry : averageRatings.entrySet()) {
			entry.getValue()[1] = (Double) entry.getValue()[1]
					/ (Double) entry.getValue()[0];
		}
	}

	public void initialize() {

		//
		computeAverages();
	}

	public static void main(String[] args) throws IOException {
		Recommender recommender = new Recommender();
		recommender
				.loadMovieDB("E:\\Downloads\\Guide To Data Mining Codes\\Gallery-2.7.3\\ml-100k");

		recommender.initialize();
	}
}
