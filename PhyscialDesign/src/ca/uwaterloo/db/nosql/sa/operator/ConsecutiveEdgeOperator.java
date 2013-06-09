/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.Query2;
import ca.uwaterloo.db.nosql.sa.QueryPath;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-09
 * PhyscialDesign
 */
abstract public class ConsecutiveEdgeOperator extends Operator {


	protected Set<QueryPath> getCommonPath(Edge e0, Edge e1) {
		Set<QueryPath> commonpathSet = new HashSet<>();
		Set<QueryPath> qpSet = e0.getQueryPaths();
		 
		for (QueryPath qp : qpSet) {
			if (e1.getQueryPaths().contains(qp)  && qp.isNeighbor(e0, e1)){
				commonpathSet.add(qp);
			}
		}
		return commonpathSet;
	}

	protected int getDistQueryNumber(Set<QueryPath> commonPathSet) {
		HashSet<Query2> qSet = new HashSet<>();
		
		int count = 0;
		for (QueryPath qp : commonPathSet) {
			Query2 query = qp.getQuery();
			if (!qSet.contains(query)){
				qSet.add(query);
				count ++;
			}
		}
		return count;
	}
	
	public double getCostGain(Edge e0, Edge e1) {
		
		if (! isElligible(e0, e1)) return 0;
		Set<QueryPath> commonPathSet = getCommonPath(e0, e1);
		int n  = getDistQueryNumber(commonPathSet);
		return n;
	}

}
