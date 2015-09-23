/**
 * 
 */
package org.app.ds.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anandm
 * 
 */
public class BST<T extends Comparable<T>> {

    private Node<T> root;

    /**
     * 
     */
    public BST() {
        super();
    }

    public void insert(T value) {
        // first node insertion
        if (root == null) {
            root = new Node<T>(null, value);
        }
        else {
            insert(root, value);
        }

    }

    private void insert(Node<T> parent, T value) {
        if (parent.value.compareTo(value) <= 0) {
            // insert in right sub tree
            if (parent.right == null) {
                parent.right = new Node<T>(parent, value);
            }
            else {
                insert(parent.right, value);
            }
        }
        else {
            // insert in left sub tree
            if (parent.left == null) {
                parent.left = new Node<T>(parent, value);
            }
            else {
                insert(parent.left, value);
            }
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean search(T value) {
        Node<T> foundNode = _search(root, value);

        return foundNode != null;
    }

    private Node<T> _search(Node<T> parent, T value) {

        if (parent == null) {
            return null;
        }

        if (value.compareTo(parent.value) == 0) {
            return parent;
        }

        if (value.compareTo(parent.value) < 0) {
            // search in left sub tree
            return _search(parent.left, value);
        }
        else {
            // search in right sub tree
            return _search(parent.right, value);
        }
    }

    public int size() {
        return _size(root);
    }

    private int _size(Node<T> parent) {
        // base case
        if (parent == null) {
            return 0;
        }
        else {
            // root 1 + left sub tree size + right sub tree size
            return 1 + _size(parent.left) + _size(parent.right);
        }
    }

    public int maxDepth() {
        return _maxDepth(root);
    }

    private int _maxDepth(Node<T> parent) {
        // base case
        if (parent == null) {
            return 0;
        }
        else {
            // root 1 + max(_maxdepth(left subtree) , _maxdepth(right subtree))
            int leftSubTreeMaxDepth = _maxDepth(parent.left);

            int rightSubTreeMaxDepth = _maxDepth(parent.right);

            int maxDepth = leftSubTreeMaxDepth > rightSubTreeMaxDepth ? leftSubTreeMaxDepth
                    : rightSubTreeMaxDepth;

            return 1 + maxDepth;
        }
    }

    private T min() {
        if (root == null) {
            return null;
        }
        else {
            Node<T> minNode = root;
            while (minNode.left != null) {
                minNode = minNode.left;
            }

            return minNode.value;
        }

    }

    private T max() {
        if (root == null) {
            return null;
        }
        else {
            Node<T> maxNode = root;
            while (maxNode.right != null) {
                maxNode = maxNode.right;
            }

            return maxNode.value;
        }
    }

    public List<T> preOrder() {

        List<T> nodeData = new ArrayList<T>(size());

        return _preOrder(root, nodeData);

    }

    private List<T> _preOrder(Node<T> parent, List<T> nodeData) {

        // base condition
        if (parent == null) {
            return nodeData;
        }
        else {
            // root
            nodeData.add(parent.value);
            // left sub tree
            _preOrder(parent.left, nodeData);
            // right subtree
            _preOrder(parent.right, nodeData);

            return nodeData;
        }
    }

    public List<T> inOrder() {

        List<T> nodeData = new ArrayList<T>(size());

        return _inOrder(root, nodeData);

    }

    private List<T> _inOrder(Node<T> parent, List<T> nodeData) {

        if (parent == null) {
            return nodeData;
        }
        else {
            // left sub tree
            _inOrder(parent.left, nodeData);
            // root
            nodeData.add(parent.value);
            // right sub tree
            _inOrder(parent.right, nodeData);

            return nodeData;
        }
    }

    public List<T> postOrder() {

        List<T> nodeData = new ArrayList<T>(size());

        return _postOrder(root, nodeData);

    }

    private List<T> _postOrder(Node<T> parent, List<T> nodeData) {

        if (parent == null) {
            return nodeData;
        }
        else {

            // left sub tree
            _postOrder(parent.left, nodeData);
            // right sub tree
            _postOrder(parent.right, nodeData);
            // root
            nodeData.add(parent.value);

            return nodeData;
        }
    }

    public boolean isSameAs(BST<T> bst) {
        Node<T> node1 = root;
        Node<T> node2 = bst.root;

        return _isSame(node1, node2);

    }

    private boolean _isSame(Node<T> node1, Node<T> node2) {

        if (node1 != null && node2 != null) {
            // value is same and left and right sub tree is also same the tree
            // is same
            return node1.value.equals(node2.value)
                    && _isSame(node1.left, node2.left)
                    && _isSame(node1.right, node2.right);
        }
        else if (node1 == null && node2 == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public static final <E extends Comparable<E>> boolean isBST(Node<E> root) {

        // also implement count of structure and double
        return false;
    }

    public void remove(T value) {

        Node<T> foundNode = _search(root, value);

        // element is at leaf
        if (foundNode.left == null && foundNode.right == null) {

        }

        // element has only one child

        // element has more than one child

    }

    public static class Node<T extends Comparable<T>> {

        private T value;

        private Node<T> parent;

        private Node<T> left;

        private Node<T> right;

        /**
         * @param value
         * @param left
         * @param right
         */
        public Node(Node<T> parent, T value) {
            super();
            this.value = value;

        }

    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<Integer>();

        bst.insert(5);
        bst.insert(3);
        bst.insert(1);
        bst.insert(4);
        bst.insert(9);
        bst.insert(6);
        /*
         * bst.insert(7); bst.insert(8);
         */

        System.out.println(BST.isBST(bst.root));

        BST<Integer> notBst = new BST<Integer>();

        notBst.insert(5);
        notBst.insert(3);
        notBst.insert(1);
        notBst.insert(4);
        notBst.insert(9);
        notBst.insert(6);

        notBst.root.left.left.value = 10;

        System.out.println(BST.isBST(notBst.root));

        BST<Integer> sameBst = new BST<Integer>();

        sameBst.insert(5);
        sameBst.insert(3);
        sameBst.insert(1);
        sameBst.insert(4);
        sameBst.insert(9);
        sameBst.insert(6);
        // sameBst.insert(7);

        System.out.println(bst.isSameAs(sameBst));

        System.out.println(bst.size());

        System.out.println(bst.maxDepth());

        System.out.println(bst.min());

        System.out.println(bst.max());

        System.out.println(Arrays.toString(bst.preOrder().toArray()));

        System.out.println(Arrays.toString(bst.inOrder().toArray()));

        System.out.println(Arrays.toString(bst.postOrder().toArray()));

    }
}
