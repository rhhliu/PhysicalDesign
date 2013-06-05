package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-02
 * PhyscialDesign
 */
public class InLineEdge extends Edge {

	
	// consecutive edges
	//private List<Edge> edgeList = new ArrayList<>();
	Edge e0, e1;
	
	public InLineEdge(Edge e0, Edge e1) {
		
		assert (e0.getTo() == e1.getFrom());
		
		this.e0 = e0;
		this.e1 = e1;
		

		//update query reference
		// qs1 is a subset of qs0
		Set<Query> qs1 = new HashSet<>(e1.getQueries());
		//Set<Query> qs1 = e1.getQueries();
		//intersection
		//qs0.retainAll(qs1);
		setQueries(qs1);
		
	}

	private InLineEdge() {
		
	}
	
	

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getName()
	 */
	@Override
	public String getName() {
		return e0.getName() + "_" + e1.getName();
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#cloneEdge()
	 */
	@Override
	public Edge cloneEdge() {
		InLineEdge e = new InLineEdge();
		
		e.setQueries(this.getQueries());
		return super.cloneEdge();
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getQueryRefCount(ca.uwaterloo.db.nosql.sa.Query)
	 */
	@Override
	public int getQueryRefCount(Query q) {
		int r0 = e0.getQueryRefCount(q);
		int r1 = e1.getQueryRefCount(q);
		if (r0 == 0 || r1 == 0 ) return 0;
		
		return r0 + r1;
	}
	
	public Edge getFirstSubEdge() {
		
		return e0.getFirstSubEdge();
	}
	
	public Edge getLastSubEdge() {
		return e1.getLastSubEdge();
	}
	
}
