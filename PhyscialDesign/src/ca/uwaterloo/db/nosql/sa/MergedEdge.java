/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-02
 * PhyscialDesign
 */
public class MergedEdge extends Edge{
	private Set<List<Edge>> edgeSet = new HashSet<>(); 
	
	public MergedEdge(Edge e0, Edge e1) {
		List<Edge> edgeList0 = e0.getEdgeList();
		List<Edge> edgeList1 = e1.getEdgeList();
		
		edgeSet.add(edgeList0);
		edgeSet.add(edgeList1);
		
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getName()
	 */
	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder();
		
		for (List<Edge> edgeList : edgeSet) {
			for (Edge edge : edgeList) {
				sb.append(edge).append('_');
			}
			sb.setCharAt(sb.length()-1, '|');
		}
		
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#cloneEdge()
	 */
	@Override
	public Edge cloneEdge() {
		throw new RuntimeException("Not supported yet!");
	}
	
	
	
}
