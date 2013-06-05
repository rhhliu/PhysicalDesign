/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.MergedEdge;
import ca.uwaterloo.db.nosql.sa.MergedNode;
import ca.uwaterloo.db.nosql.sa.Node;
import ca.uwaterloo.db.nosql.sa.Query;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public class MergeOperator extends Operator {



	@Override
	public boolean isApplyOnSiblingEdges() {
		return true;
	}

	@Override
	public boolean isApplyOnConsecutiveEdges() {
		return false;
	}
	
	/**
	 * e0 and e1 should be sibling edges.
	 * To merge e0 and e1 to a new edge, the cost gain will be 
	 * the intersections of the queries on both of the edges.
	 */
	@Override
	public double getCostGain(Edge e0, Edge e1) {
		HashSet<Query> s = new HashSet<Query>(e0.getQueries());
		s.retainAll(e1.getQueries());
		return s.size();
	}

	/**
	 *       a
	 *   e0 / \ e1
	 *     b   c
	 *    /\   /\
	 *     
	 * After merge
	 * 		a
	 * 		| me
	 * 		n
	 *     /\/\
	 *     
	 *  e0, and e1 not exist.
	 */
	@Override
	public void apply(Edge e0, Edge e1) {
		MergedEdge me = new MergedEdge(e0, e1);
		Node a = e0.getFrom();
		me.setFrom(a);
		Node n = new MergedNode(e0.getTo(), e1.getTo());
		me.setTo(n );
		
		
		//move the query reference to the new merged edge
		
		me.getQueries().addAll(e0.getQueries());
		me.getQueries().addAll(e1.getQueries());
		
		
		
		//out-edges of new merged node 
		Node b = e0.getTo();
		
		for (Edge e : b.getOutEdges()){
			e.setFrom(n);
		}
		
		Node c = e1.getTo();
		for (Edge e : c.getOutEdges()){
			e.setFrom(n);
		}
		
		
		e0.removeFrom();
		e1.removeFrom();
	
	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		// e0 and e1 should be sibilings
		if (e0.getFrom() == e1.getFrom())
			return true;
		else
			return false;
	}

}
