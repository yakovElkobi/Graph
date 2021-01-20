package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * Graph_DS implements the graph interface and represents the Graph structure
 * all nodes in the graph are stores in HashMap and the edges represents by the neighbors of each node
 * the edgeSize is counting within add\remove edges methods
 * The modCount is increase on each method that changes something in the graph
 */
public class Graph_DS implements graph {
	private HashMap<Integer, node_data> nodes;
	private int edgeSize, modeCount;
	
	public Graph_DS() { // O(1)
		nodes = new HashMap<Integer, node_data>();
		edgeSize = 0;
		modeCount = 0;
	}
	
	@Override
	public node_data getNode(int key) { // O(1)
		return nodes.get(key);
	}

	/**
	 * checks if the nodes is not null and one node is neighbor of the second one
	 */
	@Override
	public boolean hasEdge(int node1, int node2) { // O(1)
		node_data n1 = getNode(node1);
		node_data n2 = getNode(node2);
		return n1 != null && n2 != null && n1.hasNi(node2);
	}

	@Override
	public void addNode(node_data n) { // O(1)
		if(n != null) {
			nodes.put(n.getKey(), n);
			modeCount++;
		}
	}

	/**
	 * checks if the both nodes are in graph (otherwise one of them will be null),
	 * checks that the nodes are different and checks that the node are not connected yet 
	 * if all checks are passed then add each one to other's neighbors (undirected graph)
	 */
	@Override
	public void connect(int node1, int node2) { // O(1)
		node_data n1 = getNode(node1);
		node_data n2 = getNode(node2);
		if(n1 != null && n2 != null && node1 != node2 && !n1.hasNi(node2)) {
			n1.addNi(n2);
			n2.addNi(n1);
			edgeSize++;
			modeCount++;
		}
	}

	@Override
	public Collection<node_data> getV() { // O(1)
		return nodes.values();
	}

	@Override
	public Collection<node_data> getV(int node_id) { // O(1)
		node_data n = getNode(node_id);
		if(n == null) return null;
		return n.getNi();
	}

	/**
	 * get the given node by key
	 * loop over all neighbors of given node and remove it from their neighbors
	 * then clear all given node's neighbors and update the edges size
	 * finally, remove given node from the graph
	 * all this method counting as only 1 change (modeCount)
	 */
	@Override
	public node_data removeNode(int key) { // O(n)
		node_data n = getNode(key);
		if(n != null) {
			Collection<node_data> ni = n.getNi();
			for(node_data m : ni) {
				m.removeNode(n);
			}
			edgeSize -= ni.size();
			ni.clear();
			nodes.remove(key);
			modeCount++;
		}
		return n;
	}

	/**
	 * checks if the both nodes are in graph (otherwise one of them will be null),
	 * checks that the nodes are different and checks that the node are connected 
	 * remove each node from other's neighbors (undirected grpah)
	 */
	@Override
	public void removeEdge(int node1, int node2) { // O(1)
		node_data n1 = getNode(node1);
		node_data n2 = getNode(node2);
		if(n1 != null && n2 != null && node1 != node2 && n1.hasNi(node2)) {
			n1.removeNode(n2);
			n2.removeNode(n1);
			edgeSize--;
			modeCount++;
		}
	}

	@Override
	public int nodeSize() { // O(1)
		return nodes.size();
	}

	@Override
	public int edgeSize() { // O(1)
		return edgeSize;
	}

	@Override
	public int getMC() { // O(1)
		return modeCount;
	}

}
