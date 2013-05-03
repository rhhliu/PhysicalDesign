/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import ca.uwaterloo.db.design.graphIf.EdgeIf;
import ca.uwaterloo.db.design.graphIf.NodeIf;
import ca.uwaterloo.db.design.graphIf.PathNodeIf;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-20
 * PhyscialDesign
 */
public class GraphNode extends Node implements PathNodeIf{
	protected HashMap<String, GraphEdge> outEdges = new HashMap<>();
	protected List<Attribute> attList = new ArrayList<>(); 
	/**
	 * @param attName
	 * @param attType 
	 */
	public GraphNode(String attName, Type attType) {
		attList.add(new Attribute(attName, attType));
	}
	
	public GraphNode(String attName){
		this(attName, Type.TEXT);
	}
	
	/**
	 * Copy constructor
	 * @param gnode
	 */
	public static GraphNode cloneGraphNode(GraphNode gnode) {
		GraphNode gn = new GraphNode();
		gn.attList.addAll(gnode.attList);
		return gn;
	}
	
	private GraphNode() {
		
	}

	
	
	protected void addAdj(String edgeName, NodeIf node) {
		GraphEdge edge = new GraphEdge(edgeName, this, node);
		outEdges.put(edgeName, edge);
		edge.setFrom(this);
		edge.setTo(node);
		//putAdjNode(edge, node);
		
	}
	
	protected void addAdj(EdgeIf edge){
		GraphEdge graphEdge = (GraphEdge) edge;
		outEdges.put(graphEdge.getName(), graphEdge);
		//print(outEdges);
		//putAdjNode(edge, graphEdge.getTo());
	}
	
	private void print(HashMap<String, GraphEdge> outEdges) {
		for (Entry<String, GraphEdge> en : outEdges.entrySet()){
			System.out.println(en.getKey() + " => " +en.getValue() + ";   ");
		}
	}

	public HashMap<String, GraphEdge> getOutEdges() {
		return outEdges;
	}
	
	public GraphEdge getEdge(String edgeName) {
		return outEdges.get(edgeName);
	}


	public void addOutEdge(GraphEdge arc) {
		outEdges.put(arc.getName(), arc);
		
	}
	
	public static GraphNode mergeNodes(GraphNode gnode1, GraphNode gnode2){
		
		GraphNode gn = new GraphNode();
		
		gn.attList.addAll(gnode1.attList);
		gn.attList.addAll(gnode2.attList);
		
		
		gn.outEdges.putAll(gnode1.getOutEdges());
		gn.outEdges.putAll(gnode2.getOutEdges());
		
		
		
		return gn;
	}
	

	public GraphNode getAdjNode(GraphEdge edge) {
		return (GraphNode) edge.getTo();
	}

//	public GraphEdge findEdgeByNode(GraphNode gNode) {
//		//TODO : this is a slow operation.
//		
//		for (GraphEdge ge : outEdges.values()) {
//			if (ge.getTo() == gNode){
//				return ge;
//			}
//		}
//		
//		return null;
//		
//	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.Node#getName()
	 */
	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder();
		for (Attribute att : attList) {
			sb.append(att.getName()).append(';');
		}
		
		sb.deleteCharAt(sb.length()-1);
		
		return sb.toString();
	}

	@Override
	public GraphEdge getOutEdge() {
		
		Iterator<GraphEdge> it = outEdges.values().iterator();
		if (it.hasNext()) 
			return it.next();
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getName();
	}
	
	
	
}
