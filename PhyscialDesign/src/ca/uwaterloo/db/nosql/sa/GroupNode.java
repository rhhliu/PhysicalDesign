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
	

	public GroupNode(Edge e1) {
		Node n0 = e1.getFrom();
		Node n1 = e1.getTo();
		
		if (n0 instanceof GroupNode)
			edgeList.addAll(((GroupNode) n0).getEdgeList());
		
		if (n1 instanceof GroupNode)
			edgeList.addAll(((GroupNode) n1).getEdgeList());
		
		
	}

	// consecutive edges
	private List<Edge> edgeList = new ArrayList<>();

	/**
	 * @return the edgeList
	 */
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	
	
	
	
}
