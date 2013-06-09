/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.InLineEdge;
import ca.uwaterloo.db.nosql.sa.MergedEdge;
import ca.uwaterloo.db.nosql.sa.Node;

import ca.uwaterloo.db.nosql.sa.QueryPath;
import ca.uwaterloo.db.nosql.sa.SolutionGraph;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public class InlineOperator extends ConsecutiveEdgeOperator {


	@Override
	public boolean isApplyOnSiblingEdges() {
		return false;
	}

	@Override
	public boolean isApplyOnConsecutiveEdges() {
		return true;
	}
	
	@Override
	public double getDiskSpaceChange(Edge e0, Edge e1) {
		
		// disk space increase
		Node a = e0.getFrom();
		Node b = e0.getTo();
		Node c = e1.getTo();
		int incrementAttNum = a.getAttributes().size() + c.getAttributes().size();
	
		
		// disk space decrease
		
		int decremetnAttNum = 0;
		
		
		Set<QueryPath> commonPathSet = getCommonPath(e0, e1);
		
		if (RETAIN_ORIGINAL ){
			if (!e0.isOriginal() && removeable(e0, commonPathSet))
				decremetnAttNum += a.getAttributes().size() + b.getAttributes().size();
			
			if (!e1.isOriginal() && removeable(e1, commonPathSet))
				decremetnAttNum += b.getAttributes().size() + c.getAttributes().size();
			
		}else{
			if (removeable(e0, commonPathSet))
				decremetnAttNum += a.getAttributes().size() + b.getAttributes().size();
			if (removeable(e1, commonPathSet))
				decremetnAttNum += b.getAttributes().size() + c.getAttributes().size();
		}
		
		return incrementAttNum - decremetnAttNum;
		
		
	}

	private boolean removeable(Edge e0, Set<QueryPath> commonPathSet) {
		Set<QueryPath> qps = e0.getQueryPaths();
		if (qps.size() - commonPathSet.size() == 0)
			return true;
		return false;
	}

//
//	@Override
//	public double getCostGain(Edge e0, Edge e1) {
//		if (! isElligible(e0, e1)) return 0;
//		Set<QueryPath> commonPathSet = getCommonPath(e0, e1);
//		int n  = getDistQueryNumber(commonPathSet);
//		return n;
//	}

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
		Node a = e0.getFrom();
		Node b = e0.getTo();
		Node c = e1.getTo();
		 
		 InLineEdge ie = new InLineEdge(e0, e1);
		 ie.setFrom(a);
		 ie.setTo(c);	
		 
		 // move the query path from e0 and e1 to Inlined edge ie
		 Set<QueryPath> commonPathSet = getCommonPath(e0, e1);
		 ie.addQueryPaths(commonPathSet);
		 e0.removeQueryPaths(commonPathSet);
		 e1.removeQueryPaths(commonPathSet);
		 
		 // add ie to solution grapph.
		 sg.addEdges(ie);

		 
		// if e0 and e1 have the same set of queries, remove e0.
		 
		 boolean e0Removed = false, e1Removed = false;
		
		 if (e0.getQueryPaths().size() == 0 ){
			 // remove e0
			 e0.removeFrom();
			 e0.removeTo();
			 sg.removeEdge(e0);
			 e0Removed = true;
		 }
		 
		 if (e1.getQueryPaths().size() == 0){
			 // remove e0
			 e1.removeFrom();
			 e1.removeTo();
			 sg.removeEdge(e1);
			 e1Removed = true;
		 }
		 
		 if (e0Removed && e1Removed)
			 sg.removeNode(b);
		 

		 super.apply(sg, e0, e1);
		
	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		if (! super.isElligible(e0, e1)) 
			return false;
		// e0 and e1 should be consecutive edges
		
		if (e0 instanceof MergedEdge) return false;
		
		Node b = e0.getTo();
		
		
		if (!e0.getTo().getOutEdges().contains(e1))
			return false;
		
				
		return true;
	}



}
