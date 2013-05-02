/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import ca.uwaterloo.db.design.graphIf.EdgeIf;
import ca.uwaterloo.db.design.graphIf.NodeIf;
import ca.uwaterloo.db.design.graphIf.PathNodeIf;


/**
 * @author Rui Liu, rui.liu09@gmail.com University of Waterloo 2013-04-17
 *         PhyscialDesign
 */
public class PathNode extends Node implements PathNodeIf {
	private PathEdge outEdge;
	private String attName;
	private Type attType;
	/**
	 * @param name
	 */
	public PathNode(String attName) {
		this.attName = attName;
		this.attType = Type.TEXT;
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.PathNodeIF#getOutEdge()
	 */
	@Override
	public PathEdge getOutEdge() {
		return outEdge;
	}
	
	protected void addAdj(String edgeName, NodeIf node) {
		outEdge = new PathEdge(edgeName);
		outEdge.setFrom(this);
		outEdge.setTo(node);
		//putAdjNode(outEdge, node);
		//node.putAdjNode(outEdge, this);
	}

	@Override
	public PathEdge getEdge(String edgeName) {
		if (edgeName.equals(this.getName())) 
			return outEdge;
		return null;
	}

	@Override
	public HashMap<String, Edge> getOutEdges() {
		ConcurrentHashMap<String, Edge> m = new ConcurrentHashMap<>();
		m.put(getName(), outEdge);
		return null;
	}

	
	@Override
	protected void addAdj(EdgeIf edge) {
		this.outEdge = (PathEdge) edge;
		//putAdjNode(edge, outEdge.getTo());

		
	}
	public PathNode getAdjNode(EdgeIf edge) {
		return (PathNode) edge.getTo();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return attName;
	}
	
	
}
