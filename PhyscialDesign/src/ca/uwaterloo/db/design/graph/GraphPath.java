/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ca.uwaterloo.db.design.graphIf.PathNodeIf;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-21
 * PhyscialDesign
 */
public class GraphPath extends AbstractPath {

	
	private LinkedList<GraphNode> nodeList = new LinkedList<>();
	
	/**
	 * Construct GraphPath by clone every node. For each node only the edge
	 * belonging to the path is cloned.
	 * 
	 * @param minCostPath A list of GraphNodes represents the path matched on 
	 * Graph. The GraphNodes in this list reference the node on the graph; and 
	 * will be cloned in this constructor.
	 */
	public GraphPath(List<GraphNode> minCostPath) {
		//nodeList.addAll(minCostPath);
		Iterator<GraphNode> it = minCostPath.iterator();
		
		GraphNode cur = null, nxt = null;
		if (it.hasNext()) 
			cur = it.next();
		
		if (it.hasNext())
			nxt = it.next();
		
		GraphEdge preEdge = null, curEdge = null; 
		
		do{
			GraphNode n = GraphNode.cloneGraphNode(cur);
			GraphEdge e = cur.findEdgeByNode(nxt);
			
			preEdge = curEdge;
			curEdge = GraphEdge.cloneEdge(e);
			curEdge.setFrom(n);
			if (preEdge != null) 
				preEdge.setTo(n);
			
			nodeList.add(n);
			
			cur = nxt;
			nxt = it.next();
		}while (it.hasNext());
		
		
		GraphNode n = GraphNode.cloneGraphNode(cur);
		GraphEdge e = cur.findEdgeByNode(nxt);
		
		preEdge = curEdge;
		curEdge = GraphEdge.cloneEdge(e);
		curEdge.setFrom(n);
		if (preEdge != null) 
			preEdge.setTo(n);
		
		nodeList.add(n);
		n = GraphNode.cloneGraphNode(nxt);
		nodeList.add(n);
		
		
	}
	@Override
	public Iterator<? extends PathNodeIf> nodeIterator() {
		return nodeList.iterator();
	}
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
