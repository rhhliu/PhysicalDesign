package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class QueryPath {
	private List<Edge> edgeList = new ArrayList<>();
	private Query2 query;

	public void addEdges(Edge... edges) {

		edgeList.addAll(Arrays.asList(edges));
		
		for (Edge e : edges) {
			e.addQueryPath(this);
		}
	}

	public void setQuery(Query2 query) {
		this.query = query;
	}

	/**
	 * @return the query
	 */
	public Query2 getQuery() {
		return query;
	}

	public boolean isNeighbor(Edge e0, Edge e1) {
		Edge se0 = e0.getFirstSubEdge();
		int i0 = edgeList.indexOf(se0);
		Edge ee = e1.getFirstSubEdge();
		int i1 = edgeList.indexOf(ee);
		
		if (i0 == -1 || i1 == -1) 
			return false;
		
		if (Math.abs(i0-i1) == 1) 
			return true;
		else
			return false;
	}

	
	/**
	 * So far this method is onlyl used by Merge Operator
	 * @param e0
	 * @return
	 */
	public QueryPath prefix(Edge e0) {
		QueryPath qp = new QueryPath();
		int i = edgeList.indexOf(e0.getFirstSubEdge());
		qp.edgeList = edgeList.subList(0, i);
		return qp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		QueryPath qp = (QueryPath) obj;
		if (qp.edgeList.size() != this.edgeList.size() )
			return false;
		
		Iterator<Edge> it = edgeList.iterator();
		Iterator<Edge> ito = qp.edgeList.iterator();
		
		while (it.hasNext() && ito.hasNext()){
			if (it.next() != ito.next()) 
				return false;
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (Edge e : edgeList) {
			sb.append(e.toString()).append(":");
		}
		sb.deleteCharAt(sb.length() -1);
		return sb.toString();
	}
	
	
	
}
