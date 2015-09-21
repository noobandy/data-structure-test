/**
 * 
 */
package org.app.ds.graph.dijkstra.engine;

import org.app.ds.graph.dijkstra.model.Edge;
import org.app.ds.graph.dijkstra.model.Graph;
import org.app.ds.graph.dijkstra.model.Vertex;

import java.util.*;

/**
 * @author anandm
 * 
 */
public class MyDijkstraAlgorithm {

	private final List<Edge> edges;
	private Set<Vertex> visited = new HashSet<Vertex>();
	private Map<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();
	private Map<Vertex, Integer> distance = new HashMap<Vertex, Integer>();

	public MyDijkstraAlgorithm(Graph graph) {
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Vertex source) {
        Queue<Edge> queue = new PriorityQueue<Edge>(10,new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        visited.add(source);
        distance.put(source, 0);

        for(Edge edge : getNeighbors(source)) {
            predecessors.put(edge.getDestination(), source);
            distance.put(edge.getDestination(), edge.getWeight());
            queue.add(edge);
        }

        while(!queue.isEmpty()) {
            Edge edge = queue.remove();

            visited.add(edge.getDestination());

            for(Edge neighbour : getNeighbors(edge.getDestination())) {
                if(!visited.contains(neighbour.getDestination())) {
                    if(distance.get(neighbour.getDestination()) == null) {
                        distance.put(neighbour.getDestination(), distance.get(edge.getDestination()) + neighbour.getWeight());
                        predecessors.put(neighbour.getDestination(), edge.getDestination());
                    } else {
                        if(distance.get(neighbour.getDestination()) > distance.get(edge.getDestination()) + neighbour.getWeight()) {
                            distance.put(neighbour.getDestination(),  distance.get(edge.getDestination()) + neighbour.getWeight());
                            predecessors.put(neighbour.getDestination(), edge.getDestination());
                        }
                    }
                }
            }
        }
	}





	private List<Edge> getNeighbors(Vertex node) {
		List<Edge> neighbors = new ArrayList<Edge>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)) {
				neighbors.add(edge);
			}
		}
		return neighbors;
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

    public static void main(String[] args) {
        Vertex A = new Vertex("A", "A");
        Vertex B = new Vertex("B", "B");
        Vertex C = new Vertex("C", "C");
        Vertex D = new Vertex("D", "D");
        Vertex E = new Vertex("E", "E");

        List<Vertex> vertexes = new ArrayList<Vertex>();

        vertexes.add(A);
        vertexes.add(B);
        vertexes.add(C);
        vertexes.add(D);
        vertexes.add(E);

        Edge AB = new Edge("AB", A, B, 5);
        Edge AC = new Edge("AC", A, C, 8);
        Edge AD = new Edge("AD", A, D, 10);
        Edge AE = new Edge("AE", A, E, 16);

        Edge BA = new Edge("BA", B, A, 5);
        Edge CA = new Edge("CA", C, A, 8);
        Edge DA = new Edge("DA", D, A, 10);
        Edge EA = new Edge("EA", E, A, 16);

        Edge BD = new Edge("BD", B, D, 6);
        Edge DB = new Edge("DB", D, B, 6);

        Edge CE = new Edge("CE", C, E, 7);
        Edge EC = new Edge("EC", E, C, 7);

        Edge DE = new Edge("DE", D, E, 14);
        Edge ED = new Edge("ED", E, D, 14);

        List<Edge> edges = new ArrayList<Edge>();

        edges.add(AB);
        edges.add(AC);
        edges.add(AD);
        edges.add(AE);
        edges.add(BA);
        edges.add(BD);
        edges.add(CA);
        edges.add(CE);
        edges.add(DA);
        edges.add(DB);
        edges.add(DE);
        edges.add(EA);
        edges.add(EC);
        edges.add(ED);

        Graph graph = new Graph(vertexes,edges);

        MyDijkstraAlgorithm algorithm = new MyDijkstraAlgorithm(graph);

        algorithm.execute(D);

        List<Vertex> path =  algorithm.getPath(A);

        for (Vertex v : path) {
            System.out.println(v.getName());
        }









    }

}
