package org.app.ds.others.bookrecommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recommender {

	private Map<String, Object> userBookRatings;
	private Map<String, Book> books;
	private Map<String, User> users;

	/**
	 * 
	 */
	public Recommender() {
		super();

		userBookRatings = new HashMap<String, Object>(200000);
		books = new HashMap<String, Book>(300000);
		users = new HashMap<String, User>(300000);
	}

	private void loadBooks(InputStreamReader inputStreamReader)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			String[] tokens = line.split(";");
			books.put(tokens[0], new Book(tokens[0], tokens[1], tokens[2],
					tokens[3], tokens[4]));
		}

		bufferedReader.close();

	}

	private void loadUsers(InputStreamReader inputStreamReader)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			String[] tokens = line.split(";");
			if (tokens.length > 2) {
				users.put(tokens[0], new User(tokens[0], tokens[1], tokens[2]));
			} else {
				users.put(tokens[0], new User(tokens[0], tokens[1], "N/A"));
			}

		}
		bufferedReader.close();
	}

	private void loadUserBookRatings(InputStreamReader inputStreamReader)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			String[] tokens = line.split(";");

			Map<String, Double> obj = (Map<String, Double>) userBookRatings
					.get(tokens[0]);

			if (obj == null) {
				obj = new HashMap<String, Double>();
			}

			obj.put(tokens[1],
					Double.parseDouble(tokens[2].substring(1,
							tokens[2].length()).trim()));

			userBookRatings.put(tokens[0], obj);
		}

	}

	public void loadBookDB(String basePath) throws IOException {

		loadBooks(new FileReader(new File(basePath + File.separator
				+ "BX-Books.csv")));

		loadUsers(new FileReader(new File(basePath + File.separator
				+ "BX-Users.csv")));

		loadUserBookRatings(new FileReader(new File(basePath + File.separator
				+ "BX-Book-Ratings.csv")));
	}

	public List<Book> recommendationsForUser(String userId,
			int maxRecommendations) {

		return null;
	}

	public static void main(String[] args) throws IOException {
		Recommender recommender = new Recommender();
		recommender
				.loadBookDB("E:\\Downloads\\Guide To Data Mining Codes\\BX-Dump");
	}

}
