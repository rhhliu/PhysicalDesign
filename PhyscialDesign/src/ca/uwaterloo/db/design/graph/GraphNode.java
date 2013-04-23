/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-20
 * PhyscialDesign
 */
public class GraphNode extends Node {
	protected HashMap<String, GraphEdge> outEdges = new HashMap<>();

	/**
	 * @param name
	 */
	public GraphNode(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	public void addAdj(String edgeName, Node node) {
		GraphEdge edge = new GraphEdge(edgeName, this, node);
		outEdges.put(edgeName, edge);
		
	}
	
	public HashMap<String, GraphEdge> getOutEdges() {
		return outEdges;
	}
	
	public Edge getEdge(String edgeName) {
		return outEdges.get(edgeName);
	}


	public void addOutEdge(GraphEdge arc) {
		outEdges.put(arc.getName(), arc);
		
	}
	
	
}
