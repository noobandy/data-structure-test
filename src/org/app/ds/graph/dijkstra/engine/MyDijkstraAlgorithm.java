/**
 * 
 */
package org.app.ds.graph.dijkstra.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.app.ds.graph.dijkstra.model.Edge;
import org.app.ds.graph.dijkstra.model.Graph;
import org.app.ds.graph.dijkstra.model.Vertex;

/**
 * @author anandm
 * 
 */
public class MyDijkstraAlgorithm {

    private final List<Edge> edges;

    private Set<Vertex> settledNodes = new HashSet<Vertex>();

    private Map<Vertex, Vertex> predecessors = new HashMap<Vertex, Vertex>();

    private Map<Vertex, Integer> distance = new HashMap<Vertex, Integer>();

    public MyDijkstraAlgorithm(Graph graph) {
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Vertex source) {
        Queue<Edge> edges = new PriorityQueue<Edge>(10, new Comparator<Edge>() {

            @Override
            public int compare(Edge o1, Edge o2) {

                return o1.getWeight() - o2.getWeight();
            }
        });

        settledNodes.add(source);
        distance.put(source, 0);

        for (Edge edge : getNeighbors(source)) {
            predecessors.put(edge.getDestination(), source);
            distance.put(edge.getDestination(), edge.getWeight());

            edges.add(edge);
        }

        while (!edges.isEmpty()) {
            Edge edge = edges.remove();

            List<Edge> neighbours = getNeighbors(edge.getDestination());

            for (Edge neighbour : neighbours) {
                if (!settledNodes.contains(neighbour.getDestination())) {
                    if (distance.get(neighbour.getDestination()) == null) {
                        distance.put(
                                neighbour.getDestination(), edge.getWeight());
                        predecessors.put(
                                neighbour.getDestination(), edge.getSource());
                    }
                    else {
                        if (distance.get(neighbour.getDestination())
                                + edge.getWeight() < distance.get(neighbour
                                .getDestination())) {
                            distance.put(
                                    neighbour.getDestination(),
                                    distance.get(neighbour.getDestination())
                                            + edge.getWeight());
                            predecessors.put(
                                    neighbour.getDestination(),
                                    edge.getSource());
                        }
                    }
                }
                settledNodes.add(edge.getSource());
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
        List<Vertex> vertexs = new ArrayList<Vertex>();
        Vertex s = new Vertex("s", "s");

        Vertex a = new Vertex("a", "a");
        Vertex b = new Vertex("b", "b");
        Vertex c = new Vertex("c", "c");
        Vertex d = new Vertex("d", "d");
        Vertex e = new Vertex("e", "e");
        Vertex f = new Vertex("f", "f");
        Vertex g = new Vertex("g", "g");

        vertexs.add(s);
        vertexs.add(a);
        vertexs.add(b);
        vertexs.add(c);
        vertexs.add(d);
        vertexs.add(e);
        vertexs.add(f);
        vertexs.add(g);

        List<Edge> edges = new ArrayList<Edge>();

        Edge sa = new Edge("sa", s, a, 5);
        Edge sb = new Edge("sb", s, b, 10);
        Edge sc = new Edge("sc", s, c, 4);
        Edge sd = new Edge("sd", s, d, 5);

        Edge as = new Edge("as", a, s, 5);
        Edge bs = new Edge("bs", b, s, 10);
        Edge cs = new Edge("cs", c, s, 4);
        Edge ds = new Edge("ds", d, s, 5);

        Edge ae = new Edge("ae", a, e, 5);
        Edge af = new Edge("af", a, f, 10);
        Edge ab = new Edge("ab", a, b, 4);

        Edge ea = new Edge("ea", e, a, 5);
        Edge fa = new Edge("fa", f, a, 10);
        Edge ba = new Edge("ba", b, a, 4);

        Edge dc = new Edge("dc", d, c, 4);
        Edge dg = new Edge("dg", d, g, 10);

        Edge cd = new Edge("cd", c, d, 4);
        Edge gd = new Edge("gd", g, g, 10);

        Edge ef = new Edge("ef", e, f, 6);

        Edge fe = new Edge("fe", f, e, 6);

        edges.add(fe);
        edges.add(ef);
        edges.add(ea);
        edges.add(ae);
        edges.add(gd);
        edges.add(cd);
        edges.add(dg);
        edges.add(dc);
        edges.add(ba);
        edges.add(fa);
        edges.add(ab);
        edges.add(af);
        edges.add(ds);
        edges.add(cs);
        edges.add(bs);
        edges.add(as);
        edges.add(sd);
        edges.add(sc);
        edges.add(sb);
        edges.add(sa);

        Graph graph = new Graph(vertexs, edges);

        MyDijkstraAlgorithm algorithm = new MyDijkstraAlgorithm(graph);

        algorithm.execute(s);

        List<Vertex> path = algorithm.getPath(b);

        for (Vertex vertex : path) {
            System.out.println(vertex.getId());
        }

    }
}
