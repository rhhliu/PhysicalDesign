/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.HashSet;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.GroupNode;
import ca.uwaterloo.db.nosql.sa.MergedNode;
import ca.uwaterloo.db.nosql.sa.Query;

/**
 * @author Rui Liu, rui.liu09@gmail.com 
 * 	University of Waterloo 2013-06-01
 *         PhyscialDesign
 */
public class GroupOperator extends Operator {

	@Override
	public boolean isApplyOnSiblingEdges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isApplyOnConsecutiveEdges() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getCostGain(Edge e0, Edge e1) {
		HashSet<Query> s = new HashSet<Query>(e0.getQueries());
		s.retainAll(e1.getQueries());
		return s.size();
	}

	/**
	 * Group is applied on two nodes, e0.getTo() and e1.getTo().
	 * For example, a-(e0)->b-(e1)->c 
	 * will be grouped to a-(e0)->( new group node from b and c  )
	 */
	@Override
	public void apply(Edge e0, Edge e1) {
		
		// assert e0 and e1 are consecutive edges.
		assert(e0.getTo() == e1.getFrom());
		
		if (e0.getTo() instanceof MergedNode || e1.getTo() instanceof MergedNode) 
			throw new RuntimeException("MergedNode cannot be grouped!");
		
		// we do not use e0, but it must exist.
		GroupNode gNode = new GroupNode(e1);
		
		Edge ee = e0.cloneEdge();
		
		

	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		// e0 and e1 should be consecutive edges
		if (! (e0.getTo() instanceof MergedNode ) && ! (e1.getTo()instanceof MergedNode)  && e0.getTo().getOutEdges().contains(e1))
			return true;
		else
			return false;
	}

}
