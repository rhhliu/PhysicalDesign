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

	
	public PathNode getFrom() {
		return (PathNode) node1;
	}
	public void setFrom(PathNode from) {
		this.node1 = from;
	}
	public PathNode getTo() {
		return (PathNode) node2;
	}
	public void setTo(PathNode to) {
		this.node2 = to;
	}

	/**
	 * @return the node1
	 */
	public PathNode getNode1() {
		return (PathNode) node1;
	}
	
	/**
	 * @return the node2
	 */
	public PathNode getNode2() {
		return (PathNode) node2;
	}


	@Override
	public PathNode match(GraphNode gNode, PathNode pNode) {
		throw new UnsupportedOperationException();
	}



}
