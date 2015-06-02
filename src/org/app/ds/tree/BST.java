/**
 * 
 */
package org.app.ds.tree;

/**
 * @author anandm
 * 
 */
public class BST<T extends Comparable<T>> {

	private Node root;

	/**
	 * 
	 */
	public BST() {
		super();
	}

	public void insert(T value) {
		insert(root, value);
	}

	private Node insert(Node parent, T value) {
		if (parent == null) {
			return new Node(value);
		}

		if (value.compareTo(parent.value) == 0) {
			return parent;
		}

		if (value.compareTo(parent.value) < 0) {
			parent.left = insert(parent.left, value);
		} else {
			parent.right = insert(parent.right, value);
		}

		return parent;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean search(T value) {
		Node foundNode = _search(root, value);

		return foundNode != null;
	}

	private Node _search(Node parent, T value) {

		if (parent == null) {
			return parent;
		}

		if (value.compareTo(parent.value) == 0) {
			return parent;
		}

		if (value.compareTo(parent.value) < 0) {
			return _search(parent.left, value);
		} else {
			return _search(parent.right, value);
		}
	}
	
	private Node _remove(Node parant, T value) {
		
	}
	public void remove(T value) {
		
		Node foundNode = _search(root, value);
		
		// element is at leaf
		if(foundNode.left == null && foundNode.right == null) {
			
		}

		// element has only one child

		// element has more than one child

	}

	private class Node {
		private T value;
		private Node left;
		private Node right;

		/**
		 * @param value
		 * @param left
		 * @param right
		 */
		public Node(T value) {
			super();
			this.value = value;

		}

	}
}
