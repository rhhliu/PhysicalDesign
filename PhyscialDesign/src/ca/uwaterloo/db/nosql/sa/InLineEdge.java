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
	private List<Edge> edgeList = new ArrayList<>();
	
	public InLineEdge(Edge e0, Edge e1) {
		
		assert (e0.getTo() == e1.getFrom());
		
		List<Edge> eList0 = e0.getEdgeList();
		List<Edge> eList1 = e1.getEdgeList();
		
		edgeList.addAll(eList0);
		edgeList.addAll(eList1);
		

		//update query reference
		Set<Query> qs0 = new HashSet<>(e0.getQueries());
		Set<Query> qs1 = e1.getQueries();
		//intersection
		qs0.retainAll(qs1);
		setQueries(qs0);
		
	}

	private InLineEdge() {
		
	}
	
	

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getEdgeList()
	 */
	@Override
	public List<Edge> getEdgeList() {
		return edgeList;
	}
	
	

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getName()
	 */
	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder();
		for (Edge e : edgeList) {
			sb.append(e.getName()).append("_");
		}
		
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
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
	
	
	
}
