/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author Rui Liu, rui.liu09@gmail.com University of Waterloo 2013-04-17
 *         PhyscialDesign
 */
public class PathNode extends Node {
	private PathEdge outEdge;
	/**
	 * @param name
	 */
	public PathNode(String name) {
		super(name);
	}

	public PathEdge getOutEdge() {
		return outEdge;
	}
	
	public void addAdj(String edgeName, Node node) {
		outEdge = new PathEdge(edgeName, this, node);
		putAdjNode(outEdge, node);
		node.putAdjNode(outEdge, this);
	}

	@Override
	public PathEdge getEdge(String edgeName) {
		if (edgeName.equals(this.name)) 
			return outEdge;
		return null;
	}

	@Override
	public HashMap<String, Edge> getOutEdges() {
		ConcurrentHashMap<String, Edge> m = new ConcurrentHashMap<>();
		m.put(name, outEdge);
		return null;
	}

	
	public void addOutEdge(PathEdge arc) {
		addAdj(arc);
		
		
	}

	@Override
	public void addAdj(Edge edge) {
		this.outEdge = (PathEdge) edge;
		
		
		PathNode endNode = (outEdge.getNode1() == this)? outEdge.getNode2() : outEdge.getNode1();
		putAdjNode(edge, endNode);
		endNode.putAdjNode(outEdge, this);
		
	}
	public PathNode getAdjNode(Edge edge) {
		return (PathNode) edgeNodeMap.get(edge);
	}
}
