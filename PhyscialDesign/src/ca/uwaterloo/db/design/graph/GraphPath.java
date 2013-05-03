/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ca.uwaterloo.db.design.graphIf.PathNodeIf;

/**
 * @author Rui Liu, rui.liu09@gmail.com University of Waterloo 2013-04-21
 *         PhyscialDesign
 */
public class GraphPath extends AbstractPath {

	private LinkedList<GraphNode> nodeList = new LinkedList<>();

	/**
	 * Construct GraphPath by clone every node. For each node only the edge
	 * belonging to the path is cloned.
	 * 
	 * @param minCostPath
	 *            A list of GraphNodes represents the path matched on Graph. The
	 *            GraphNodes in this list reference the node on the graph; and
	 *            will be cloned in this constructor.
	 */
	private GraphPath() {

	}

	@Override
	public Iterator<? extends PathNodeIf> nodeIterator() {
		return nodeList.iterator();
	}

	public int getCost() {
		return nodeList.size() - 1;
	}

	public static GraphPath buildGraphPath(List<GraphNode> minCostNodeList,
			List<GraphEdge> minCostEdgeList) {

		assert (minCostEdgeList.size() == minCostNodeList.size() - 1);

		GraphPath gp = new GraphPath();

		Iterator<GraphNode> it = minCostNodeList.iterator();
		Iterator<GraphEdge> edgeIt = minCostEdgeList.iterator();

		GraphNode curNode = null, preNode = null;

		if (it.hasNext()) {
			preNode = it.next();
			preNode = GraphNode.cloneGraphNode(preNode);
			gp.nodeList.add(preNode);
		}

		while (it.hasNext() && edgeIt.hasNext()) {
			curNode = it.next();
			GraphEdge e = edgeIt.next();
			
			
			curNode = GraphNode.cloneGraphNode(curNode);
			GraphEdge edge = GraphEdge.cloneEdge(e);;
			
			edge.setFrom(preNode);
			edge.setTo(curNode);
			gp.nodeList.add(curNode);
			
			
			preNode = curNode;
		}

		return gp;
	}

}
