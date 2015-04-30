/**
 * 
 */
package org.app.ds.graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.app.ds.applications.menumanager.Menu;
import org.app.ds.applications.menumanager.MenuCommand;
import org.app.ds.applications.menumanager.MenuManager;
import org.app.ds.stack.IStack;

/**
 * @author anandm
 * 
 */
public class Graph {

	private Map<String, Set<String>> treeMap = new TreeMap<String, Set<String>>();

	private int vertexCount;
	private int edgeCount;

	public Graph() {
		super();
	}

	public Graph(InputStream inputStream, String delimeter) throws IOException {
		super();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] tokens = line.split(delimeter);

			for (int i = 1; i < tokens.length; i++) {
				addEdge(tokens[0], tokens[i]);
			}
		}
	}

	private void addVertex(String v) {
		treeMap.put(v, new TreeSet<String>());
		vertexCount = vertexCount + 1;
	}

	public void addEdge(String v, String w) {
		if (!hasVertex(v)) {
			addVertex(v);
		}

		if (!hasVertex(w)) {
			addVertex(w);
		}
		if (!hasEdge(v, w)) {
			treeMap.get(v).add(w);
			treeMap.get(w).add(v);
			edgeCount++;
		}
	}

	public int V() {
		return vertexCount;
	}

	public int E() {

		return edgeCount;
	}

	public Iterable<String> vertices() {
		return treeMap.keySet();
	}

	public Iterable<String> adjacentTo(String v) {
		return treeMap.get(v);
	}

	public int degree(String v) {

		return treeMap.get(v).size();
	}

	public boolean hasVertex(String v) {
		return treeMap.containsKey(v);
	}

	public boolean hasEdge(String v, String w) {
		Set<String> edges = treeMap.get(v);

		return edges != null && edges.contains(w);
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException {

		final Graph graph = new Graph(new FileInputStream(args[0]), args[1]);

		final Scanner scanner = new Scanner(System.in);

		Menu getCastOfMovieMenu = new Menu(1, "Get cast of movie",
				new MenuCommand() {

					@Override
					public boolean execute() {

						System.out.println("Enter Movie Name");
						String movieName = scanner.nextLine();
						for (String cast : graph.adjacentTo(movieName)) {
							System.out.println(cast);
						}

						return true;
					}
				});

		Menu getMoviesOfCastMenu = new Menu(2, "Get Movies of cast",
				new MenuCommand() {

					@Override
					public boolean execute() {
						System.out.println("Enter cast Name");
						String castName = scanner.nextLine();
						for (String movie : graph.adjacentTo(castName)) {
							System.out.println(movie);
						}
						return true;
					}
				});

		Menu baconDistanceMenu = new Menu(3, "Bacon Distance",
				new MenuCommand() {

					@Override
					public boolean execute() {
						System.out.println("Enter source actor");
						String source = scanner.nextLine();
						System.out.println("Enter destination actor");
						String destination = scanner.nextLine();

						DFSPathFinder pathFinder = new DFSPathFinder(graph,
								source);

						IStack<String> stack = pathFinder.pathTo(destination);

						while (!stack.isEmpty()) {
							System.out.println(stack.pop());
						}

						return true;
					}
				});

		MenuManager menuManager = new MenuManager();

		menuManager.addMenu(getCastOfMovieMenu);
		menuManager.addMenu(getMoviesOfCastMenu);
		menuManager.addMenu(baconDistanceMenu);

		menuManager.start();

	}
}
