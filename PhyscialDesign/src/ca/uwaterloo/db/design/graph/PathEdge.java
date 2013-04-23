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

	@Override
	public PathNode getFrom() {
		return (PathNode)super.getFrom();
	}

	@Override
	public PathNode getTo() {
		return (PathNode)super.getTo();
	}

	
	public Node match(Edge pEdge) {
		// TODO Auto-generated method stub
		return null;
	}

	public PathEdge getNextEdge() {
		return getTo().getOutEdge();
	}

	@Override
	public Node match(PathEdge pEdge) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
