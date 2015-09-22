package org.app.ds.graph.bellmanford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    private Set<Vertex> visited = new HashSet<Vertex>();

    /**
     * 
     */
    public BellManFordAlgorithm(Graph graph) {
        super();
        this.vertexs = graph.getVertexes();
        this.edges = graph.getEdges();

    }

    public void execute(Vertex source) {
        boolean last = false;
        for (int i = 0; i < vertexs.size(); i++) {
            if (i == vertexs.size() - 1) {
                last = true;
            }

            _execute(source, last);
        }
    }

    private void _execute(Vertex source, boolean last) {
        boolean distanceChanged = false;

        List<Edge> edges = neighbours(source);

        if (distanceChanged && last) {
            throw new IllegalArgumentException("Negative weight cycle dected");
        }
    }

    public List<Vertex> path(Vertex destination) {

        List<Vertex> path = new ArrayList<Vertex>();

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

}
