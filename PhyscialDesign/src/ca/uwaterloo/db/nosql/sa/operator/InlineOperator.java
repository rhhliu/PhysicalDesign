/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.InLineEdge;
import ca.uwaterloo.db.nosql.sa.MergedEdge;
import ca.uwaterloo.db.nosql.sa.Node;
import ca.uwaterloo.db.nosql.sa.Query;
import ca.uwaterloo.db.nosql.sa.SolutionGraph;

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
		if (! isElligible(e0, e1)) return 0;
		int cg = 0;
		boolean removable = true;
		 Node b = e0.getTo();
		 int qn = 0;
		for (Edge  e : b.getOutEdges()) {
			HashSet<Query> s = new HashSet<Query>(e0.getQueries());
			s.retainAll(e.getQueries());
			
			// If e0 and e has different queries, they cannot be inline.
			if (s.size() != e.getQueries().size() || s.size() != e0.getQueries().size())
				return 0;
			
			int c = 0;
			qn = s.size();
			for (Query q : s) {
				if (q.getEdgeLevel(e0) +1 == q.getEdgeLevel(e.getFirstSubEdge()))
					c++;
			}
			
			cg += c;
			
			if (c != s.size())
				 removable = false;
		}
			
			
		if (removable)
			return cg - qn + w * (b.getOutEdges().size() + 1);
		return 0;
	}

	/**
	 *    a
	 *    | e0
	 *    b
	 * e1/ \
	 *  c   d
	 *  
	 *  
	 *  Inline e0 with all siblings of e1
	 *  
	 *    a
	 *   / \
	 *  c   d
	 */
	@Override
	public void apply(SolutionGraph sg, Edge e0, Edge e1) {
		 Node b = e0.getTo();
		 for (Edge  e : b.getOutEdges()) {
			 InLineEdge ie = new InLineEdge(e0, e);
			 ie.setFrom(e0.getFrom());
			 ie.setTo(e.getTo());	
			 
			 sg.addEdges(ie);
		}
		 
		 
		 
		// if e0 and e1 have the same set of queries, remove e0.
		 
		
			e0.removeFrom();
			sg.removeEdge(e0);
			for (Edge  e : b.getOutEdges()) {
				e.removeTo();
				sg.removeEdge(e);
			}
			sg.removeNode(b);
		
	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		// e0 and e1 should be consecutive edges
		
		if (e0 instanceof MergedEdge) return false;
		
		Node b = e0.getTo();
		
		for (Edge e : b.getOutEdges()) {
			if (e instanceof MergedEdge)
				return false;
		}
		
		if (e0.getTo().getOutEdges().contains(e1))
			return true;
		else
			return false;
	}

}
