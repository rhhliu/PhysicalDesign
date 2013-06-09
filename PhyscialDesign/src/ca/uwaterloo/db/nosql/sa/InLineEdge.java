package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.Collection;
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
	}
	
	

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#cloneEdge()
	 */
	@Override
	public InLineEdge cloneEdge() {
		InLineEdge ie = new InLineEdge(e0, e1);
		return ie;
	}



	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.nosql.sa.Edge#getName()
	 */
	@Override
	public String getName() {
		if (e1 instanceof MergedEdge)
			return e0.getName() + "_(" + e1.getName() + ")";
		else
			return e0.getName() + "_" + e1.getName();
	}

	
	public Edge getFirstSubEdge() {
		
		return e0.getFirstSubEdge();
	}
	
	public Edge getLastSubEdge() {
		return e1.getLastSubEdge();
	}



	public void addQueryPaths(Collection<QueryPath> commonPathSet) {
		this.queryPathSet.addAll(commonPathSet);
		
	}
	
}
