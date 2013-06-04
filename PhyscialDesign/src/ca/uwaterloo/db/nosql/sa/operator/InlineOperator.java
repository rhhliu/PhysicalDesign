/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.InLineEdge;
import ca.uwaterloo.db.nosql.sa.Query;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public class InlineOperator extends Operator {


	@Override
	public boolean isApplyOnSiblingEdges() {
		return false;
	}

	@Override
	public boolean isApplyOnConsecutiveEdges() {
		return true;
	}

	@Override
	public double getCostGain(Edge e0, Edge e1) {
		HashSet<Query> s = new HashSet<Query>(e0.getQueries());
		s.retainAll(e1.getQueries());
		return s.size();
	}

	@Override
	public void apply(Edge e0, Edge e1) {
		InLineEdge ie = new InLineEdge(e0, e1);
		ie.setFrom(e0.getFrom());
		ie.setTo(e1.getTo());
		
		
	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		// e0 and e1 should be consecutive edges
		if (e0.getTo().getOutEdges().contains(e1))
			return true;
		else
			return false;
	}

}
