package org.app.ds.others.bookrecommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.app.ds.applications.menumanager.Menu;
import org.app.ds.applications.menumanager.MenuCommand;
import org.app.ds.applications.menumanager.MenuManager;
import org.app.ds.others.DataMiningUtility;

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
			books.put(strip(tokens[0]), new Book(strip(tokens[0]),
					strip(tokens[1]), strip(tokens[2]), strip(tokens[3]),
					strip(tokens[4])));
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
				users.put(strip(tokens[0]), new User(strip(tokens[0]),
						strip(tokens[1]), strip(tokens[2])));
			} else {
				users.put(strip(tokens[0]), new User(strip(tokens[0]),
						strip(tokens[1]), "N/A"));
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
					.get(strip(tokens[0]));

			if (obj == null) {
				obj = new HashMap<String, Double>();
			}

			obj.put(strip(tokens[1]), Double.parseDouble(strip(tokens[2])));

			userBookRatings.put(strip(tokens[0]), obj);
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
			int maxRecommendations, int k) {

		List<Object[]> distances = new ArrayList<Object[]>();

		Map<String, Double> usersRatings = (Map<String, Double>) userBookRatings
				.get(userId);

		for (Entry<String, Object> entry : userBookRatings.entrySet()) {
			if (!entry.getKey().equals(userId)) {
				Map<String, Double> otherUserRatings = (Map<String, Double>) entry
						.getValue();

				List<Double> uRatings = new ArrayList<Double>();

				List<Double> otherURatings = new ArrayList<Double>();

				for (Entry<String, Double> rating : otherUserRatings.entrySet()) {
					if (usersRatings.containsKey(rating.getKey())) {
						uRatings.add(usersRatings.get(rating.getKey()));
						otherURatings.add(rating.getValue());
					}
				}

				if (otherURatings.size() > 0) {
					distances.add(new Object[] {
							entry.getKey(),
							DataMiningUtility.pearsonCorrelationCoefficient(
									listToPrimitiveArray(uRatings),
									listToPrimitiveArray(otherURatings)) });
				}

			}
		}

		Collections.sort(distances, new Comparator<Object[]>() {

			@Override
			public int compare(Object[] o1, Object[] o2) {
				double distanceOne = (Double) o1[1];
				double distanceTwo = (Double) o2[1];

				return Double.compare(distanceTwo, distanceOne);
			}
		});

		Map<String, Double> recommendations = new HashMap<String, Double>();

		double totalDistance = 0;

		for (int i = 0; i < k; i++) {
			totalDistance = totalDistance + (Double) distances.get(i)[1];
		}

		for (int i = 0; i < k; i++) {

			Object[] userDistance = distances.get(i);

			String name = (String) userDistance[0];

			double weight = (Double) userDistance[1] / totalDistance;

			Map<String, Double> neighborUserRatings = (Map<String, Double>) userBookRatings
					.get(name);

			for (Entry<String, Double> neighborUserRating : neighborUserRatings
					.entrySet()) {

				if (!usersRatings.containsKey(neighborUserRating.getKey())) {

					if (!recommendations.containsKey(neighborUserRating
							.getKey())) {

						recommendations.put(neighborUserRating.getKey(),
								neighborUserRating.getValue() * weight);
					} else {
						recommendations
								.put(neighborUserRating.getKey(),
										recommendations.get(neighborUserRating
												.getKey())
												+ (neighborUserRating
														.getValue() * weight));
					}
				}
			}
		}

		List<Object[]> recommendationList = mapToArrayListOfTuple(recommendations);

		Collections.sort(recommendationList, new Comparator<Object[]>() {

			@Override
			public int compare(Object[] o1, Object[] o2) {
				double projectedRatingOne = (Double) o1[1];
				double projectedRatingTwo = (Double) o2[1];
				return Double.compare(projectedRatingTwo, projectedRatingOne);
			}
		});

		List<Book> recommendedBooks = new ArrayList<Book>();

		for (int i = 0; i < maxRecommendations; i++) {
			if (i >= recommendationList.size()) {
				break;
			} else {
				recommendedBooks.add(books.get(recommendationList.get(i)[0]));
			}
		}

		return recommendedBooks;
	}

	private List<Object[]> mapToArrayListOfTuple(Map<String, Double> map) {
		List<Object[]> list = new ArrayList<Object[]>(map.size());

		for (Entry<String, Double> entry : map.entrySet()) {
			list.add(new Object[] { entry.getKey(), entry.getValue() });
		}

		return list;
	}

	private double[] listToPrimitiveArray(List<Double> doubles) {
		double[] values = new double[doubles.size()];

		for (int i = 0; i < doubles.size(); i++) {
			values[i] = doubles.get(i);
		}

		return values;

	}

	private String strip(String token) {
		String trimmed = token.trim();
		if (trimmed.length() > 1) {
			return trimmed.substring(1, trimmed.length() - 1);
		} else {
			return trimmed;
		}

	}

	public static void main(String[] args) throws IOException {
		final Recommender recommender = new Recommender();

		long initStartTime = System.nanoTime();
		recommender
				.loadBookDB("E:\\Downloads\\Guide To Data Mining Codes\\BX-Dump");
		long initEndTime = System.nanoTime();

		System.out.println("Init Time : " + (initEndTime - initStartTime)
				/ Math.pow(10, -9) + " seconds");

		final Scanner scanner = new Scanner(System.in);

		MenuManager menuManager = new MenuManager();
		menuManager.addMenu(new Menu(1, "Get Recommendations",
				new MenuCommand() {

					@Override
					public boolean execute() {
						System.out.println("Enter User Id");

						String userId = scanner.nextLine();

						System.out
								.println("Enter Maximum no. of Recommendations you want");

						int maxRecommendations = scanner.nextInt();

						System.out
								.println("Enter the value of K you want to use");

						int k = scanner.nextInt();

						long queryStartTime = System.nanoTime();

						List<Book> books = recommender.recommendationsForUser(
								userId, maxRecommendations, k);
						long queryEndTime = System.nanoTime();
						System.out
								.println("---Recommendations for you are ()----");

						for (Book book : books) {
							System.out.println(book.getTitle() + " written by "
									+ book.getAuthor());
						}

						System.out.println("Query time : "
								+ (queryEndTime - queryStartTime)
								/ Math.pow(10, -9) + " seconds");
						return true;
					}
				}));

		menuManager.start();
	}
}
