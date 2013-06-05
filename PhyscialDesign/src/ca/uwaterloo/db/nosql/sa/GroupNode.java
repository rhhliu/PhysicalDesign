package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com 
 * University of Waterloo 
 * 2013-06-02
 * PhyscialDesign
 */
public class GroupNode extends Node {
	
	// internally it is n0--(e)--> n1
	private Node n0, n1;
	private Edge e;

	public GroupNode(Edge e) {
		n0 = e.getFrom();
		n1 = e.getTo();
		this.e = e;
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Node#toString()
	 */
	@Override
	public String toString() {
		
		return n0 + "-[" + e + "]->" + n1; 
	}
	
	

}
