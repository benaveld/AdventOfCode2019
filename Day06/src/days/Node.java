package days;

import java.util.ArrayList;
import java.util.List;

public class Node<E> {
	private E obj;
	private Node<E> root;
	private List<Node<E>> leafs = new ArrayList<Node<E>>();

	public Node(E obj, Node<E> root) {
		this.obj = obj;
		this.root = root;
		if (root != null) {
			root.addLeaf(this);
		}
	}

	public E get() {
		return obj;
	}

	public void set(E obj) {
		this.obj = obj;
	}

	public void addLeaf(Node<E> leaf) {
		leafs.add(leaf);
	}

	public List<Node<E>> getLeafs() {
		return leafs;
	}

	public void setRoot(Node<E> root) {
		if (this.root == null && root != null) {
			this.root = root;
			root.addLeaf(this);
		} else {
			throw new RuntimeException();
		}
	}

	public Node<E> getRoot() {
		return root;
	}

	public List<Node<E>> getAllConnections() {
		List<Node<E>> list = new ArrayList<Node<E>>(leafs);
		if (root != null) {
			list.add(0, root);
		}
		return list;
	}

	@Override
	public String toString() {
		return "Node: " + obj.toString();
	}
}
