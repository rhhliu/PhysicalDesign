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
	 * Construct a new GraphEdge with specified simple name.
	 * @param name simple name, which has only one name label.
	 */
	public GraphEdge(String name, Node from, Node to) {
		super(name, from, to);
				
		List<String> list = new LinkedList<>();
		list.add(name);
		EdgeChain chain = new EdgeChain(list, to);
		chainSet.add(chain);
	}
	
	
	
	public GraphEdge(GraphEdge e) {
		chainSet.addAll(e.chainSet);
		
	}

	public GraphEdge(String name) {
		super(name, null, null);
	}
	

	public GraphEdge() {
	}

	public GraphNode getFrom() {
		return (GraphNode) from;
	}
	
	public GraphNode getTo() {
		return (GraphNode) to;
	}

	@Override
	public String getName() {
		Iterator<EdgeChain> pit = chainSet.iterator();
		StringBuilder psb = new StringBuilder();
		while (pit.hasNext()){
			EdgeChain chain = pit.next();
			psb.append(chain.toString());
			psb.append('|');
		}
		return psb.toString();
	}
	
	
	
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
		
		// 1. create new edge
		GraphEdge newEdge = new GraphEdge();
		
		
		// 2. construct the chainset of new edge
		newEdge.chainSet.addAll(e1.chainSet);
		newEdge.chainSet.addAll(e2.chainSet);
		
		// 3. connect the new edge with endpoints
		
		return newEdge;
	}

	
	/**
	 * Construct a new graph edge by concatenating e1 and e2. The new edge
	 * is from startNode to endNode.
	 * 
	 * The internal chainSet of new created graph edge is concatenation of all
	 * combination of e1's and e2's chinaSet.
	 * 
	 * @param e1
	 * @param e2
	 */
	public static GraphEdge concantinate(GraphEdge e1, GraphEdge e2) {
		
		// 1. create new edge
		GraphEdge newEdge = new GraphEdge();
		
		// 2. construct the new chainSet, which is concatenation of all combination of
		//    e1's and e2's chain set
		for (EdgeChain ec1 : e1.chainSet){
			for(EdgeChain ec2: e2.chainSet){
				EdgeChain newChain = ec1.concate(ec2);
				newEdge.chainSet.add(newChain);
			}
		}
				
		return newEdge;
	}

	
	
	
}
