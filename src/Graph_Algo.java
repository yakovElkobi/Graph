package ex0;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

public class Graph_Algo implements graph_algorithms {
	private graph g;
	
	@Override
	public void init(graph g) { // O(1) 
		this.g = g;
	}

	/**
	 * create new Graph instance
	 * loop over the given graph nodes, create new copy of the node with the same info,tag (without the neighbors)
	 * and stores the (old key, new key) in hashMap 
	 * then loop over the graph nodes again and loop over each neighbors of each node in the graph and connect between 
	 * the new nodes with the matching key according the HashMap
	 */
	@Override
	public graph copy() { // O(|V|+|E|)
		HashMap<Integer, Integer> keysCopy = new HashMap<Integer, Integer>();
		graph copy = new Graph_DS();
		for(node_data n : g.getV()) {
			node_data copy_n = new NodeData(n.getInfo(), n.getTag());
			keysCopy.put(n.getKey(), copy_n.getKey());
			copy.addNode(copy_n);
		}
		for(node_data n : g.getV()) {
			for(node_data m : g.getV(n.getKey())) {
				copy.connect(keysCopy.get(n.getKey()), keysCopy.get(m.getKey()));
			}
		}
		return copy;
	}

	/**
	 * using the BFS algorithm with the first node in graph, then checks if there is a node the BFS search does not visited
	 * (the tag is -1) and if its true return false (not connected), else return true (BFS visit all nodes)
	 */
	@Override
	public boolean isConnected() { // O(|V|+|E|)
		if(g == null || g.nodeSize() == 0) return true;
		bfs(g.getV().iterator().next().getKey());
		for(node_data n : g.getV()) {
			if(n.getTag() == -1) return false;
		}
		return true;
	}

	/**
	 * using shortestPath that return the list with the shortestPath and the size of the list-1 is the length of the shortestPath
	 */
	@Override
	public int shortestPathDist(int src, int dest) { // O(|V|+|E|)
		List<node_data> p = shortestPath(src, dest);
		if(p == null) return -1;
		else return p.size()-1;
	}

	/**
	 * using the BFS algorithm from src that put the parent of each node in his tag and after the algorithm, 
	 * start from dest and insert the parent to the list until we get back to src
	 */
	@Override
	public List<node_data> shortestPath(int src, int dest) { // O(|V|+|E|)
		if(g == null || g.nodeSize() == 0) return null;
		if(g.getNode(src) != null && g.getNode(dest) != null) {
			bfs(src);
			if(g.getNode(dest).getTag() == -1) return null;
			int n = dest;
			List<node_data> path = new LinkedList<node_data>();
			path.add(0, g.getNode(dest));
			while(n != src) {
				n = g.getNode(n).getTag();
				path.add(0, g.getNode(n));
			}
			return path;
		}
		else return null;
	}
	
	private void bfs(int src) { // O(|V|+|E|)
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(g.nodeSize());
		for(node_data n : g.getV()) {
			n.setTag(-1);
		}
		q.add(src);
		g.getNode(src).setTag(0);
		while(!q.isEmpty()) {
			int v = q.poll();
			for(node_data n : g.getV(v)) {
				if(n.getTag() == -1) {
					n.setTag(v);
					q.add(n.getKey());
				}
			}
		}
	}
	

}
