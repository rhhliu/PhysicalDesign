package ca.uwaterloo.db.design.graph;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-03-27
 * PhyscialDesign
 */
public abstract class Node {
	protected String name;
	//protected HashMap<Edge, Node> edgeNodeMap = new HashMap<>();
	
	public Node(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


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
	protected abstract void addAdj(String edgeName, Node node) ;
	protected abstract void addAdj(Edge edge);


	public abstract Edge getEdge(String edgeName) ;


//	public abstract void addOutEdge(Edge arc);
	
	public boolean equals(Object n){
		
		if (n != null && ((Node)n).getName().equals(this.name))
			return true;
		else 
			return false;
	}
	
	public String toString(){
		return name;
	}


	protected Node getAdjNode(Edge edge) {
		return edge.getTo();
	}

}
