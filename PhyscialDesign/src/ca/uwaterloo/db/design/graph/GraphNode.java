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
	
	/**
	 * Copy constructor
	 * @param gnode
	 */
	public GraphNode(GraphNode gnode) {
		super(gnode.name);
		// String, include name, are not duplicated. Only node and edge is duplicated.
		
		
	}
	public void addAdj(String edgeName, Node node) {
		GraphEdge edge = new GraphEdge(edgeName, this, node);
		outEdges.put(edgeName, edge);
		putAdjNode(edge, node);
		
	}
	
	public void addAdj(Edge edge){
		GraphEdge graphEdge = (GraphEdge) edge;
		outEdges.put(graphEdge.getName(), graphEdge);
		GraphNode endNode = (graphEdge.getNode1() == this)? graphEdge.getNode2() : graphEdge.getNode1();
		putAdjNode(edge, endNode);
		endNode.putAdjNode(graphEdge, this);
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
	
	public static GraphNode mergeNodes(GraphNode n1, GraphNode n2){
		
		GraphNode newNode = new GraphNode(n1.getName() + "," + n2.getName());
		
		newNode.outEdges.putAll(n1.getOutEdges());
		newNode.outEdges.putAll(n2.getOutEdges());
		
		
		
		return newNode;
	}
	
	public void putAdjNode(GraphEdge edge, GraphNode node){
		edgeNodeMap.put(edge, node);
	}
	public GraphNode getAdjNode(GraphEdge edge) {
		return (GraphNode) edgeNodeMap.get(edge);
	}
	
}
