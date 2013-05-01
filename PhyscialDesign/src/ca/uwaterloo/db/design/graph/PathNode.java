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
	
	protected void addAdj(String edgeName, Node node) {
		outEdge = new PathEdge(edgeName, this, (PathNode)node);
		//putAdjNode(outEdge, node);
		//node.putAdjNode(outEdge, this);
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

	
//	public void addOutEdge(PathEdge arc) {
//		addAdj(arc);
//		
//		
//	}

	@Override
	protected void addAdj(Edge edge) {
		this.outEdge = (PathEdge) edge;
		//putAdjNode(edge, outEdge.getTo());

		
	}
	public PathNode getAdjNode(Edge edge) {
		return (PathNode) edge.getTo();
	}
}
