package org.app.ds.graph.bellmanford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.app.ds.graph.dijkstra.model.Edge;
import org.app.ds.graph.dijkstra.model.Graph;
import org.app.ds.graph.dijkstra.model.Vertex;

/**
 * Single source shortest path algorithm for graphs with negative weights but no
 * negative weight cycle
 * 
 * @author anandm
 * @date Sep 22, 2015 3:48:23 PM
 */
public class BellManFordAlgorithm {

    private List<Edge> edges;

    private List<Vertex> vertexs;

    private Map<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();

    private Map<Vertex, Integer> distance = new HashMap<Vertex, Integer>();

    /**
     * 
     */
    public BellManFordAlgorithm(Graph graph) {
        super();
        this.vertexs = graph.getVertexes();
        this.edges = graph.getEdges();

    }

    public void execute(Vertex source) {

        distance.put(source, 0);
        predecessors.put(source, null);

        boolean last = false;
        for (int i = 0; i < vertexs.size(); i++) {
            if (i == vertexs.size() - 1) {
                last = true;
            }

            _execute(last);
        }
    }

    private void _execute(boolean last) {

        boolean distanceChanged = false;

        for (Edge edge : edges) {
            if (distance.get(edge.getDestination()) == null) {
                distance.put(
                        edge.getDestination(), distance.get(edge.getSource())
                                + edge.getWeight());
                predecessors.put(edge.getDestination(), edge.getSource());
            }
            else {
                if (distance.get(edge.getDestination()) > distance.get(edge
                        .getSource()) + edge.getWeight()) {
                    distance.put(
                            edge.getDestination(),
                            distance.get(edge.getSource()) + edge.getWeight());
                    predecessors.put(edge.getDestination(), edge.getSource());

                    distanceChanged = true;
                }
            }
        }

        if (distanceChanged && last) {
            throw new IllegalArgumentException("Negative weight cycle dected");
        }
    }

    public List<Vertex> path(Vertex destination) {

        List<Vertex> path = new ArrayList<Vertex>();

        path.add(destination);

        Vertex last = predecessors.get(destination);

        if (last == null) {
            return null;
        }
        else {
            while (last != null) {
                path.add(last);
                last = predecessors.get(last);
            }
        }

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
        Edge AD = new Edge("AD", A, D, -10);
        Edge AE = new Edge("AE", A, E, 16);

        Edge BA = new Edge("BA", B, A, 5);
        Edge CA = new Edge("CA", C, A, 8);
        // Edge DA = new Edge("DA", D, A, 10);
        Edge EA = new Edge("EA", E, A, 16);

        // Edge BD = new Edge("BD", B, D, 6);
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
        // edges.add(BA);
        // edges.add(BD);
        // edges.add(CA);
        edges.add(CE);
        // edges.add(DA);
        edges.add(DB);
        edges.add(DE);
        // edges.add(EA);
        // edges.add(EC);
        // edges.add(ED);

        Graph graph = new Graph(vertexes, edges);

        BellManFordAlgorithm algorithm = new BellManFordAlgorithm(graph);

        algorithm.execute(A);

        List<Vertex> path = algorithm.path(E);

        for (Vertex v : path) {
            System.out.println(v.getName());
        }

    }
}
