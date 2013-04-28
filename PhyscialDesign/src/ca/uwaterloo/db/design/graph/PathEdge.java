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
	public PathEdge(String name, Node from, Node to) {
		super(name, from, to);
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
	public void setTo(Node to) {
		this.node2 = to;
	}

	


	@Override
	public PathNode match(GraphNode gNode, PathNode pNode) {
		throw new UnsupportedOperationException();
	}



}
