/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.InLineEdge;
import ca.uwaterloo.db.nosql.sa.MergedEdge;
import ca.uwaterloo.db.nosql.sa.Node;
import ca.uwaterloo.db.nosql.sa.Query;

import ca.uwaterloo.db.nosql.sa.QueryPath;
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
	public double getDiskSpaceChange(Edge e0, Edge e1) {
		
		// disk space increase
		Node a = e0.getFrom();
		Node b = e0.getTo();
		int incr = 0;
		Set<Edge> eSet = getInlineEdges(e0, b);
		
		for (Edge e: eSet){
			Node c = e.getTo();
			incr = a.getAttributes().size() + c.getAttributes().size();
		}
	
		
		// disk space decrease
		
		int decr = 0;
		int removeCount = 0;
		int minCommonPathSetSize = Integer.MAX_VALUE;
		for (Edge e: eSet){
			
			Set<QueryPath> commonPathSet = getCommonPath(e0, e);
			
			minCommonPathSetSize = Math.min(minCommonPathSetSize, commonPathSet.size());
			Node c = e.getTo();
			if (RETAIN_ORIGINAL ){
								
				if (!e1.isOriginal() && removeable(e1, commonPathSet)){
					decr += b.getAttributes().size() + c.getAttributes().size();
					removeCount ++;
				}
				
			}else{
				
				if (removeable(e1, commonPathSet)){
					decr += b.getAttributes().size() + c.getAttributes().size();
					removeCount ++;
				}
			}
		}
		
		
		// try to remove e0
		if (RETAIN_ORIGINAL ){
			if (!e0.isOriginal() 
					//&& removeCount == b.getOutEdges().size()
					&& e0.getQueryPaths().size() == minCommonPathSetSize)
				decr += a.getAttributes().size() + b.getAttributes().size();
			
			
			
		}else{
			if (e0.getQueryPaths().size() == minCommonPathSetSize)
				decr += a.getAttributes().size() + b.getAttributes().size();
			
		}
		
		return incr - decr;
		
		
	}

	private Set<Edge> getInlineEdges(Edge e0, Node b) {

		Set<Edge> eSet = new HashSet<>();
		
		//Node b = e0.getTo();
		
		Set<QueryPath> ps0 = e0.getQueryPaths();
		for (QueryPath queryPath : ps0) {
			
			Query q = queryPath.getQuery();
			for (Edge e: b.getOutEdges()){
				QueryPath eqp = e.getQueryPath(q);
				if (eqp == null){
					continue;
				}
				
				if (eqp.isNeighbor(e0, e)){
					eSet.add(e);
				}
				
			}
		}
		
		return eSet;
		
		
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
		
		int removeCount = 0;
		
		int edgeNum = b.getOutEdges().size();
		
		for(Edge e: b.getOutEdges()){
			super.apply(sg, e0, e);
		}
		
		Set<Edge> eSet = getInlineEdges(e0, b);
		//Set<Edge> edgeSet = new HashSet<>( b.getOutEdges());
		for (Edge e : eSet){
			Node c = e.getTo();
			
			InLineEdge ie = new InLineEdge(e0, e);
			ie.setFrom(a);
			ie.setTo(c);	
			
			// move the query path from e0 and e to Inlined edge ie
			Set<QueryPath> commonPathSet = getCommonPath(e0, e);
			ie.addQueryPaths(commonPathSet);
			e0.removeQueryPaths(getQueryOnlySubSet(commonPathSet));
			e.removeQueryPaths(getQueryOnlySubSet(commonPathSet));
			
			// add ie to solution grapph.
			sg.addEdges(ie);
			
			
			// remove e, if possible
			if (e.getQueryOnlyPaths().size() == 0
					&& ie.getUpdatePaths().containsAll(e.getUpdatePaths())){
				
				e.removeFrom();
				e.removeTo();
				sg.removeEdge(e);
				removeCount ++;
			}
			
			
		}
		 
		if (removeCount == edgeNum && e0.getQueryPaths().size() == 0){
			// remove e0
			e0.removeFrom();
			e0.removeTo();
			sg.removeEdge(e0);
			
			sg.removeNode(b);
		}

		
	}

	private Set<QueryPath> getQueryOnlySubSet(Set<QueryPath> commonPathSet) {
		Set<QueryPath> qoSet = new HashSet<>();
		Iterator<QueryPath> it = commonPathSet.iterator();
		while (it.hasNext()){
			QueryPath qp = it.next();
			if(!qp.getQuery().isUpdate())
				qoSet.add(qp);
		}
		
		return qoSet;
	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		if (! super.isElligible(e0, e1)) 
			return false;
		// e0 and e1 should be consecutive edges
		
		//if (e0 instanceof MergedEdge) return false;
		Node a = e0.getFrom();
		Node b = e0.getTo();
		Node c = e1.getTo();
		
		
		
		if (!e0.getTo().getOutEdges().contains(e1))
			return false;
		
		
		Set<QueryPath> ps0 = e0.getQueryPaths();
		for (QueryPath queryPath : ps0) {
			
			Query q = queryPath.getQuery();
			boolean f = false;
			for (Edge e: b.getOutEdges()){
				QueryPath eqp = e.getQueryPath(q);
				if (eqp == null){
					continue;
				}
				
				if (eqp.isNeighbor(e0, e)){
					f = true;
					break;
				}
				
			}
			if (f) return true;
		}
		
		return false;
	}

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
		HashSet<Query> qSet = new HashSet<>();
		
		int count = 0;
		for (QueryPath qp : commonPathSet) {
			Query query = qp.getQuery();
			if (!qSet.contains(query)){
				qSet.add(query);
				count ++;
			}
		}
		return count;
	}
	
	public double getCostGain(Edge e0, Edge e1) {
		
		if (! isElligible(e0, e1)) return 0;
		
		int c = 0;
		Node b = e1.getFrom();
		
		for (Edge e: b.getOutEdges()){
			Set<QueryPath> commonPathSet = getCommonPath(e0, e);
			int n  = getDistQueryNumber(commonPathSet);
			
			c+=n;
			
		}
		
	
		return c;
	}

}
