/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

import java.util.Set;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public class Graph {
	protected Set<Edge> edges;
	protected Set<Node> nodes;
	/**
	 * @return the edges
	 */
	public Set<Edge> getEdges() {
		return edges;
	}
	/**
	 * @param edges the edges to set
	 */
	public void setEdges(Set<Edge> edges) {
		this.edges = edges;
	}
	/**
	 * @return the nodes
	 */
	public Set<Node> getNodes() {
		return nodes;
	}
	/**
	 * @param nodes the nodes to set
	 */
	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}
	
	

}
