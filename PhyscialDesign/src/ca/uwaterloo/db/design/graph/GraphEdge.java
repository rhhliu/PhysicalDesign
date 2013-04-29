/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-20
 * PhyscialDesign
 */
public class GraphEdge extends Edge{
	private Set<EdgeChain> chainSet = new HashSet<>();
	/**
	 * 
	 */
	public GraphEdge(String name, Node node1, Node node2) {
		super(name, node1, node2);
		List<String> list = new LinkedList<>();
		list.add(name);
		EdgeChain chain = new EdgeChain(list, node2);
		chainSet.add(chain);
	}
	
	public GraphEdge(GraphEdge e) {
		super(e.getName(), null, null);
		
	}

	public GraphEdge(String name) {
		super(name, null, null);
	}
	
	public GraphNode getNode1() {
		return (GraphNode) node1;
	}
	
	public GraphNode getNode2() {
		return (GraphNode) node2;
	}

//	@Override
//	public String getName() {
//		Iterator<EdgeChain> pit = chainSet.iterator();
//		StringBuilder psb = new StringBuilder();
//		while (pit.hasNext()){
//			EdgeChain chain = pit.next();
//			psb.append(chain.toString());
//			psb.append('|');
//		}
//		return psb.toString();
//	}
	
	
	
//	public GraphNode getTo() {
//		return (GraphNode)node2;
//	}
	/**
	 * Match 
	 * @param gNode 
	 * @param pNode.getOutEdge()
	 * @return
	 */
	@Override
	public PathNode match(GraphNode gNode, PathNode pNode) {
		if (pNode.getOutEdge() == null) return null;
		
		PathNode lastMatchedPathNode = null;
		int maxLen = 0;
		Iterator<EdgeChain> csit = chainSet.iterator();
		
		while (csit.hasNext()){
			EdgeChain ed = csit.next();
			
			Iterator<String> lableItrator = ed.getLableIterator();
			PathEdge curEdge= pNode.getOutEdge();
			PathNode matchedToPNode = null;
			
			int len = 0;
			boolean matchFailed = false;
			while (lableItrator.hasNext()){
				String lable = lableItrator.next();
				if ( !lable.equals(curEdge.getName())){
					matchFailed = true;
					break;
				}
				len ++;
				matchedToPNode = pNode.getAdjNode(curEdge);//curEdge.getTo();
				curEdge = curEdge.getNextEdge();
			}
			
			if (!matchFailed){
				if (len > maxLen){
					maxLen = len;
					lastMatchedPathNode = matchedToPNode;
				}
			}
			
			
		}
		
		return lastMatchedPathNode;
	}

	public static GraphEdge mergeEdge(GraphEdge e1, GraphEdge e2) {
		GraphEdge newEdge = new GraphEdge(e1.getName() + "|" + e2.getName());
		newEdge.chainSet.addAll(e1.chainSet);
		newEdge.chainSet.addAll(e2.chainSet);
		return newEdge;
	}

	public static GraphEdge concantinate(GraphEdge e1, GraphEdge e2, GraphNode startNode, GraphNode endNode) {
		
		GraphEdge newEdge = new GraphEdge(e1.getName() + "_" + e2.getName(), startNode, endNode);
		return newEdge;
	}

	
	
	
}
