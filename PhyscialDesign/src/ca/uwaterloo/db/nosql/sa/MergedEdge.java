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
	//private Set<List<Edge>> edgeSet = new HashSet<>();
	private Edge e0,e1;
	
	public MergedEdge(Edge e0, Edge e1) {
		this.e0 = e0;
		this.e1 = e1;
		
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getName()
	 */
	@Override
	public String getName() {
		return e0.getName() + "|" + e1.getName();
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#cloneEdge()
	 */
	@Override
	public Edge cloneEdge() {
		throw new RuntimeException("Not supported yet!");
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getQueryRefCount(ca.uwaterloo.db.nosql.sa.Query)
	 */
	@Override
	public int getQueryRefCount(Query q) {
		int r0 = e0.getQueryRefCount(q);
		int r1 = e1.getQueryRefCount(q);
		return Math.max(r0 , r1);
	}
	
	public Edge getFirstSubEdge() {
		
		return e0.getFirstSubEdge();
	}
	public Edge getLastSubEdge() {
		return e0.getLastSubEdge();
	}
}
