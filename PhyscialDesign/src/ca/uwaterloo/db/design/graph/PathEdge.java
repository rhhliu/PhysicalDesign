/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import ca.uwaterloo.db.design.graphIf.EdgeIf;
import ca.uwaterloo.db.design.graphIf.NodeIf;
import ca.uwaterloo.db.design.graphIf.PathEdgeIf;
import ca.uwaterloo.db.design.graphIf.PathNodeIf;


/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-18
 * PhyscialDesign
 */
public class PathEdge extends Edge implements PathEdgeIf {

	/**
	 * @param label
	 */
	public PathEdge(String label) {
		this.name = label;
		
	}

	
	public NodeIf match(EdgeIf pEdge) {
		// TODO Auto-generated method stub
		return null;
	}

	public PathEdge getNextEdge() {
		return getTo().getOutEdge();
	}


	public void setFrom(PathNode from) {
		this.from = from;
		if (from != null) 
			from.addAdj(this);
	}
	public void setTo(PathNode to) {
		this.to = to;
	}

	/**
	 * @return the from
	 */
	public PathNode getFrom() {
		return (PathNode) from;
	}
	
	/**
	 * @return the to
	 */
	public PathNode getTo() {
		return (PathNode) to;
	}


	@Override
	public PathNodeIf match(GraphNode gNode, PathNode pNode) {
		throw new UnsupportedOperationException();
	}



}
