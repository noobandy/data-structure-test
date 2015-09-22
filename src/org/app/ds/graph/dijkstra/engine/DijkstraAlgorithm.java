/**
 * 
 */
package org.app.ds.graph.dijkstra.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.app.ds.graph.dijkstra.model.Edge;
import org.app.ds.graph.dijkstra.model.Graph;
import org.app.ds.graph.dijkstra.model.Vertex;

/**
 * @author anandm
 * 
 */
public class DijkstraAlgorithm {

    private final List<Edge> edges;

    private Set<Vertex> settledNodes;

    private Set<Vertex> unSettledNodes;

    private Map<Vertex, Vertex> predecessors;

    private Map<Vertex, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Vertex source) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node) {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(
                        target,
                        getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            }
            else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        }
        else {
            return d;
        }
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

        Graph graph = new Graph(vertexes, edges);

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(graph);

        algorithm.execute(A);

        List<Vertex> path = algorithm.getPath(E);

        for (Vertex v : path) {
            System.out.println(v.getName());
        }

    }

}