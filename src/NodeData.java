package ex0;

import java.util.Collection;
import java.util.HashMap;

/**
 * NodeData implements the node_data interface and represents node in graph
 * The key is unique for each node and always increase
 * the neighbors are stores in HashMap such that the key is the neighbor's key and the value is the neighbor itself
 * all methods in this class is O(1) complexity 
 */
public class NodeData implements node_data {
	private HashMap<Integer, node_data> ni;
	private int key;
	private String info;
	private int tag;
	private static int g_key = 0;
	
	public NodeData() {
		key = ++g_key;
		ni = new HashMap<Integer, node_data>();
	}
	
	public NodeData(String info, int tag) {
		this();
		this.info = info;
		this.tag = tag;
	}
	
	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Collection<node_data> getNi() {
		return ni.values();
	}

	@Override
	public boolean hasNi(int key) {
		return ni.containsKey(key);
	}

	@Override
	public void addNi(node_data t) {
		if(t != null && !hasNi(t.getKey())) {
			ni.put(t.getKey(), t);
		}
	}

	@Override
	public void removeNode(node_data node) {
		if(node != null && hasNi(node.getKey())) {
			ni.remove(node.getKey());
		}
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		info = s;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		tag = t;
	}
}
