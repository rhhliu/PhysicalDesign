/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.Node;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-02
 * PhyscialDesign
 */
public class DropOperator extends Operator {

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Operator#isApplyOnSiblingEdges()
	 */
	@Override
	public boolean isApplyOnSiblingEdges() {
		return true;
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Operator#isApplyOnConsecutiveEdges()
	 */
	@Override
	public boolean isApplyOnConsecutiveEdges() {
		return false;
	}

	/* Applicable ot sibling edges. One is Merged Edge, the other is a simple edge.
	 * @see ca.uwaterloo.db.nosql.sa.Operator#getCostGain(ca.uwaterloo.db.nosql.sa.Edge, ca.uwaterloo.db.nosql.sa.Edge)
	 */
	@Override
	public double getCostGain(Edge e0, Edge e1) {
		if (!isElligible(e0, e1)) 
			return -1;
		
		Node n = e1.getTo();
		int numOfEdge = n.getOutEdges().size() + 1; //all out-edges plus the in-edge
		
		return w * numOfEdge;
	}

	/* Move all queries on e1 to e0.
	 * e0 is merged from e1 and another edge. 
	 * After the movement of queries, drop e1 and the e1.To node as well as out-edges from e1.To.
	 * Therefore, the cost gain is 1+ num of out-edges of e1.To
	 * @see ca.uwaterloo.db.nosql.sa.Operator#apply(ca.uwaterloo.db.nosql.sa.Edge, ca.uwaterloo.db.nosql.sa.Edge)
	 */
	@Override
	public void apply(Edge e0, Edge e1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		
		if (e0.getFrom() == e1.getFrom() 
				&&
				e0.getTo().getAttributes().containsAll(e1.getTo().getAttributes())
				&&
				e0.getQueries().containsAll(e1.getQueries())
				)
			return true;
		else
			return false;
	}

}
