/**
 * 
 */
package org.app.ds.graph;

import java.util.Map;
import java.util.TreeMap;

import org.app.ds.stack.IStack;
import org.app.ds.stack.Stack;

/**
 * @author anandm
 * 
 */
public class DFSPathFinder {

	private Map<String, String> prev = new TreeMap<String, String>();
	private Map<String, Integer> dist = new TreeMap<String, Integer>();

	public DFSPathFinder(Graph graph, String source) {
		IStack<String> stack = new Stack<String>();
		stack.push(source);
		dist.put(source, 0);
		while (!stack.isEmpty()) {
			String v = stack.pop();
			for (String edge : graph.adjacentTo(v)) {
				if (!dist.containsKey(edge)) {
					stack.push(edge);
					dist.put(edge, 1 + dist.get(v));
					prev.put(edge, v);
				}
			}
		}

	}

	public boolean hasPathTo(String v) {
		return dist.containsKey(v);
	}

	public int distanceTo(String v) {
		if (hasPathTo(v)) {
			return dist.get(v);
		}
		return Integer.MAX_VALUE;
	}

	public IStack<String> pathTo(String v) {
		IStack<String> path = new Stack<String>();
		while (v != null && dist.containsKey(v)) {
			path.push(v);
			v = prev.get(v);
		}
		return path;
	}
}
