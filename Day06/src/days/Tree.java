package days;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Tree<E> {
	private Map<E, Node<E>> map = new HashMap<E, Node<E>>();
	private Node<E> rootNode;
	
	public Tree() {}
	
	public Tree(E root) {
		rootNode = new Node<E>(root, null);
		map.put(root, rootNode);
	}
	
	public void put(E obj, E root) {
		if(obj == null || root == null) {
			throw new IllegalArgumentException("obj / root cant be null");
		}
		
		Node<E> rootNode = map.get(root);
		if(rootNode == null) {
			rootNode = new Node<E>(root, null);
			map.put(root, rootNode);
		}
		
		Node<E> objNode = map.get(obj);
		if(objNode == null) {
			objNode = new Node<E>(obj, rootNode);
			map.put(obj, objNode);
		} else {
			objNode.setRoot(rootNode);
		}
	}
	
	public boolean isAllConnected() {
		Collection<E> foundNodes = new LinkedList<E>();
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		Node<E> currentNode = rootNode;
		do {
			foundNodes.add(currentNode.get());
			List<Node<E>> leafs = currentNode.getLeafs();
			for(Node<E> leaf : leafs) {
				if(leaf.getRoot() != currentNode) {
					leaf.setRoot(currentNode);
					System.out.println("Leaf " + leaf + " is not connected to " + currentNode);
				}
			}
			queue.addAll(leafs);
			
			if(foundNodes.size() > map.size()) {
				throw new RuntimeException("loop detected in the Tree.");
			}
		} while((currentNode = queue.poll()) != null);
		
		return foundNodes.size() == map.size();
	}
	
	public List<Node<E>> getPath(E from, E to){
		Queue<List<Node<E>>> queue = new LinkedList<List<Node<E>>>();
		List<Node<E>> curentPath = new ArrayList<Node<E>>();
		curentPath.add(map.get(from));
		
		do {
			Node<E> lastNode = curentPath.get(curentPath.size()-1);
			List<Node<E>> connections = new ArrayList<Node<E>>(lastNode.getAllConnections());
			if(curentPath.size() >= 2) {
				connections.remove(curentPath.get(curentPath.size() - 2));
			}
			for(Node<E> conect : connections) {
				List<Node<E>> newPath = new ArrayList<Node<E>>(curentPath);
				newPath.add(conect);
				if(conect.get().equals(to)) {
					return newPath;
				} else {
					queue.add(newPath);
				}
			}
		} while((curentPath = queue.poll()) != null);
		
		throw new RuntimeException("Cant find path from \"" + from + "\" to \"" + to + "\"");
	}
}
