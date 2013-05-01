/**
 * 
 */
package ca.uwaterloo.db.design.graph;


/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-18
 * PhyscialDesign
 */
public class PathEdge extends Edge {

	

	/**
	 * @param name
	 * @param from
	 * @param to
	 */
	public PathEdge(String name, PathNode from, PathNode to) {
		this.name = name;
		setFrom(from);
		setTo(to);
	}

	

	
	public Node match(Edge pEdge) {
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
	public PathNode match(GraphNode gNode, PathNode pNode) {
		throw new UnsupportedOperationException();
	}



}
