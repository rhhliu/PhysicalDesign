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

	@Override
	public double getCostGain(Edge e0, Edge e1) {
		HashSet<Query> s = new HashSet<Query>(e0.getQueries());
		s.retainAll(e1.getQueries());
		return s.size();
	}

	@Override
	public void apply(Edge e0, Edge e1) {
		MergedEdge me = new MergedEdge(e0, e1);
		Node from = e0.getFrom();
		me.setFrom(from);
		Node to = new MergedNode(e0.getTo(), e1.getTo());
		me.setTo(to );
		
		
		//move the query reference to the new merged edge
		Set<Query> qs0 = new HashSet<>(e0.getQueries());
		Set<Query> qs1 = e1.getQueries();
		//intersection
		qs0.retainAll(qs1);
		me.setQueries(qs0);
		
	
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
