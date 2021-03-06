/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.MergedEdge;
import ca.uwaterloo.db.nosql.sa.MergedNode;
import ca.uwaterloo.db.nosql.sa.Node;
import ca.uwaterloo.db.nosql.sa.Query;
import ca.uwaterloo.db.nosql.sa.QueryPath;
import ca.uwaterloo.db.nosql.sa.Update;

import ca.uwaterloo.db.nosql.sa.SolutionGraph;

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
	public double getDiskSpaceChange(Edge e0, Edge e1) {
		if (! isElligible(e0, e1)) return 0;
		
		Node a = e0.getFrom();
		Node b = e0.getTo();
		Node c = e1.getTo();
		
		int incr = a.getAttributes().size() + b.getAttributes().size() + c.getAttributes().size();
		
		for (Edge e : b.getOutEdges()){
			incr += b.getAttributes().size() + e.getTo().getAttributes().size();
		}
		for (Edge e : c.getOutEdges()){
			incr += c.getAttributes().size() + e.getTo().getAttributes().size();
		}
		
		
		
		int decr = 0;
		
		Set<Query> commonQuerySet = getCommonQuerySet(e0, e1);
		
		if (removeable(e0, commonQuerySet) && ( ! RETAIN_ORIGINAL || !e0.isOriginal())){
			decr += a.getAttributes().size() + b.getAttributes().size();
			for (Edge e : b.getOutEdges()){
				decr += b.getAttributes().size() + e.getTo().getAttributes().size();
			}
		}
		
		if (removeable(e1, commonQuerySet) && ( ! RETAIN_ORIGINAL || !e1.isOriginal())){
			decr += a.getAttributes().size() + c.getAttributes().size();
			for (Edge e : c.getOutEdges()){
				decr += c.getAttributes().size() + e.getTo().getAttributes().size();
			}
		}
		
		return incr - decr;
	}
	
	private Set<Query> getCommonQuerySet(Edge e0, Edge e1) {
		Set<Query> commonSet = new HashSet<>();

		Set<QueryPath> qpSet0 = e0.getQueryPaths();
		Set<QueryPath> qpSet1 = e1.getQueryPaths();
		for (QueryPath qp0 : qpSet0) {
			for (QueryPath qp1 : qpSet1) {
				if (qp0.getQuery() == qp1.getQuery() && qp0.prefix(e0).equals(qp1.prefix(e1))){
					//if(!qp0.getQuery().isUpdate())
						commonSet.add(qp0.getQuery());
				}
			}
		}
		return commonSet;
	}

	private boolean removeable(Edge e0, Set<Query> commonQuerySet) {
		if (e0.getQueryPaths().size() - commonQuerySet.size() == 0)
			return true;
		else 
			return false;
	}

	/**
	 * e0 and e1 should be sibling edges.
	 * To merge e0 and e1 to a new edge, the cost gain will be 
	 * the intersections of the queries on both of the edges.
	 */
	@Override
	public double getCostGain(Edge e0, Edge e1) {
		
		if (! isElligible(e0, e1)) return 0;
		
		// common (intersection) queries on e0, and e1 will be moved to
		// the new merged edge, and therefore, the cost reduced.
		Set<Query> commonQuerySet = getCommonQuerySet(e0, e1);
		
		// update 
		
		return commonQuerySet.size();
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
	public void apply(SolutionGraph sg, Edge e0, Edge e1) {
		
		MergedEdge me = new MergedEdge(e0, e1);
		Node mn = new MergedNode(e0.getTo(), e1.getTo());
		
		Node a = e0.getFrom();
		Node b = e0.getTo();
		Node c = e1.getTo();
		
		me.setFrom(a);
		me.setTo(mn);
		
		//move the query path references to the new merged edge
		mergeQueryPaths(me, e0, e1);
	
		
		// add merged node and edge into the solution graph
		sg.addEdges(me);
		sg.addNodes(mn);

		
		//out-edges of new merged node 
		for (Edge e : b.getOutEdges()){
			Edge ee = e.cloneEdge();
			ee.setFrom(mn);
			ee.setTo(e.getTo());
		}
		
		for (Edge e : c.getOutEdges()){
			Edge ee = e.cloneEdge();
			ee.setFrom(mn);
			ee.setTo(e.getTo());
		}
		

		// See if we need to remove node b and c
		tryRemove(sg, e0, me);
		tryRemove(sg, e1, me);
		
		
		// update opIndex
		super.apply(sg, e0, e1);
	}

	private void tryRemove(SolutionGraph sg, Edge ee, MergedEdge me) {
		Node n = ee.getTo();
		
//		if (! RETAIN_ORIGINAL || !ee.isOriginal()){
			if (ee.getQueryOnlyPaths().size() == 0 
					&& me.getUpdatePaths().containsAll(ee.getUpdatePaths())){
				ee.removeFrom();
				ee.removeTo();
				sg.removeEdge(ee);
				
				Set<Edge> edgeToRemove = new HashSet<>(n.getOutEdges());
				for (Edge e : edgeToRemove){
					e.removeTo();
					e.removeFrom();
					sg.removeEdge(e);
				}
				
				sg.removeNode(n);
			}
//		}
	}
	
	

	private void mergeQueryPaths(Edge mergedEdge, Edge e0, Edge e1) {
		//HashSet<QueryPath> mergedQPath = new HashSet<>();
		
		Set<QueryPath> qpSet0 = e0.getQueryPaths();
		Set<QueryPath> qpSet1 = e1.getQueryPaths();
		Iterator<QueryPath> it0 = qpSet0.iterator();
		
		
		while (it0.hasNext()){
			QueryPath qp0 = it0.next();
			boolean removed = false;
			
			Iterator<QueryPath> it1 = qpSet1.iterator();
			while (it1.hasNext()){
				QueryPath qp1 = it1.next();
				if (qp0.getQuery() == qp1.getQuery() && qp0.prefix(e0).equals(qp1.prefix(e1))){
					
					if (! qp0.getQuery().isUpdate()){
						if (!removed) {
							
							it0.remove();
							removed = true;
						}
						it1.remove();
					}
					mergedEdge.addQueryPaths(qp0, qp1);
				}
			}
			
			
		}
		
//		for (QueryPath qp0 : qpSet0) {
//			for (QueryPath qp1 : qpSet1) {
//				if (qp0.getQuery() == qp1.getQuery() && qp0.prefix(e0).equals(qp1.prefix(e1))){
//					e0.removeQueryPath(qp0);
//					e1.removeQueryPath(qp1);
//					mergedEdge.addQueryPaths(qp0, qp1);
//				}
//			}
//		}
	}

	@Override
	public boolean isElligible(Edge e0, Edge e1) {
		
		if (! super.isElligible(e0, e1)) 
			return false;
		
		// e0 and e1 should be sibilings
		if (e0.getFrom() != e1.getFrom())
			return false;
		
		Set<QueryPath> qpSet0 = e0.getQueryPaths();
		Set<QueryPath> qpSet1 = e1.getQueryPaths();
		for (QueryPath qp0 : qpSet0) {
			for (QueryPath qp1 : qpSet1) {
				if (qp0.getQuery() == qp1.getQuery() && qp0.prefix(e0).equals(qp1.prefix(e1))){
					return true;
				}
			}
		}
		return false;
	}



}
