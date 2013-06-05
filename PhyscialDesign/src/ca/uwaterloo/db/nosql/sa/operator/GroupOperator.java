/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.GroupNode;
import ca.uwaterloo.db.nosql.sa.MergedNode;
import ca.uwaterloo.db.nosql.sa.Node;
import ca.uwaterloo.db.nosql.sa.Query;
import ca.uwaterloo.db.nosql.sa.SolutionGraph;

/**
 * @author Rui Liu, rui.liu09@gmail.com 
 * 	University of Waterloo 2013-06-01
 *         PhyscialDesign
 */
public class GroupOperator extends Operator {

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
		int c = 0;
		for (Query q : s) {
			if (q.getEdgeLevel(e0.getLastSubEdge()) +1 == q.getEdgeLevel(e1.getFirstSubEdge()))
				c++;
		}
		
		Node b = e0.getTo();
		if (b.getOutEdges().size()>1) 
			return 0;
		
		return c;
	}

	/**
	 * Group is applied on two nodes, e0.getTo() and e1.getTo().
	 * For example, a-(e0)->b-(e1)->c 
	 * will be grouped to a-(e0)->( new group node from b and c  )
	 */
	@Override
	public void apply(SolutionGraph sg, Edge e0, Edge e1) {
		
		// assert e0 and e1 are consecutive edges.
		assert(e0.getTo() == e1.getFrom());
		
		if (e0.getTo() instanceof MergedNode || e1.getTo() instanceof MergedNode) 
			throw new RuntimeException("MergedNode cannot be grouped!");
		
		// we do not use e0, but it must exist.
		GroupNode gNode = new GroupNode(e1);
		
		// set e0 to be the in-edge of gNode
		e0.setTo(gNode);
		
		//set all out-edges of c (e1.getTo()) 
		// to be the out-edges of gNode
		Node c = e1.getTo();
		for (Edge e : c.getOutEdges()) {
			e.setFrom(gNode);
		}
		
		sg.addNodes(gNode);
		
		sg.removeNode(e1.getFrom());
		sg.removeNode(e1.getTo());
		sg.removeEdge(e1);
	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		// e0 and e1 should be consecutive edges
		if ( !(e0.getTo() instanceof MergedNode ) && !(e1.getTo()instanceof MergedNode) 
				&& e0.getTo().getOutEdges().contains(e1))
			return true;
		else
			return false;
	}

}
