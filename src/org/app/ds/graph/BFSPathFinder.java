/**
 * 
 */
package org.app.ds.graph;

import java.util.Map;
import java.util.TreeMap;

import org.app.ds.queue.IQueue;
import org.app.ds.queue.Queue;
import org.app.ds.stack.IStack;
import org.app.ds.stack.Stack;

/**
 * @author anandm
 * 
 */
public class BFSPathFinder {

	private Map<String, String> prev = new TreeMap<String, String>();
	private Map<String, Integer> dist = new TreeMap<String, Integer>();

	public BFSPathFinder(Graph graph, String source) {
		IQueue<String> queue = new Queue<String>();
		queue.enqueue(source);
		dist.put(source, 0);
		while (!queue.isEmpty()) {
			String v = queue.dequeue();
			for (String edge : graph.adjacentTo(v)) {
				if (!dist.containsKey(edge)) {
					queue.enqueue(edge);
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
	
	
	public static void main(String[] args) {
		
	}
}
