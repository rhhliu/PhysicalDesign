package ca.uwaterloo.db.design.graph;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import ca.uwaterloo.db.design.graphIf.EdgeIf;
import ca.uwaterloo.db.design.graphIf.NodeIf;


/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-03-27
 * PhyscialDesign
 */
public abstract class Node implements NodeIf {
	//protected String name;
	//protected HashMap<Edge, Node> edgeNodeMap = new HashMap<>();
	

	
	
	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.NodeIF#getName()
	 */
	@Override
	public String getName() {
		return toString();
	}





	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.NodeIF#getOutEdges()
	 */
	@Override
	public abstract HashMap<String, ? extends Edge> getOutEdges() ;


//	public void setOutEdges(ConcurrentHashMap<String, Edge> outEdges) {
//		this.outEdges = outEdges;
//	}


	/**
	 * Add a edge from this node the specified node. The edge will have the specified 
	 * edgeName.
	 * @param edgeName
	 * @param node
	 */
	protected abstract void addAdj(String edgeName, NodeIf node) ;
	protected abstract void addAdj(EdgeIf edge);


	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.NodeIF#getEdge(java.lang.String)
	 */
	@Override
	public abstract EdgeIf getEdge(String edgeName) ;


//	public abstract void addOutEdge(Edge arc);
	
	public boolean equals(Object n){
		
		if (n != null && ((NodeIf)n).getName().equals(this.getName()))
			return true;
		else 
			return false;
	}
	
	


	protected NodeIf getAdjNode(EdgeIf edge) {
		return edge.getTo();
	}


	

}
