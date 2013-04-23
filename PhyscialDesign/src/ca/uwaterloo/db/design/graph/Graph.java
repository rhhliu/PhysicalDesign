package ca.uwaterloo.db.design.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com University of Waterloo 2013-03-27
 *         PhyscialDesign
 */
public class Graph {
	private HashMap<String, Node> nodeMap = new HashMap<>();

	public void add(Node node) {
		nodeMap.put(node.getName(), node);
	}

	public Node lookupNode(String nodeName) {
		return nodeMap.get(nodeName);
	}
	
	/**
	 * Augment the graphy by applying the three operations.
	 * Details is in graph_model.pdf
	 */
	public void augment() {
		
	}

	/**
	 * 
	 * @param p
	 * @param graph
	 * @return
	 * @throws NoPathFoundException
	 */
	public GraphPath getMinCostPath(SimplePath p, Graph graph)
			throws NoPathFoundException {
		Iterator<PathNode> it = p.nodeIterator();

		// Locate the first node of the path in graph
		GraphNode current = null;
		PathNode pNode = null;
		if (it.hasNext()) {
			pNode = it.next();
			current = graph.matchNode(pNode);
		}

		if (current == null)
			throw new NoPathFoundException(
					"No matching node found in graph for " + pNode);

		// Match each edge on the path
		// DFS match
		minCost = Integer.MAX_VALUE;
		int depth = 0;
		List<GraphNode> path = new LinkedList<>();

		// dfs to find the match path for the path started at pNode
		// the result is in the minCost and minCostPath,
		// both of which are member variables.
		dfsMatch(current, pNode, depth, path);

		return new GraphPath(minCostPath);
	}

	private int minCost;
	private List<GraphNode> minCostPath;

	/**
	 * Use DFS to find the minimal cost match for path started at pNode. The
	 * current node of graph is the stating node in the graph to be matched.
	 * 
	 * @param depth
	 *            records the level of recursion, which is used as cost for now
	 * @param path
	 *            records the current path from the 'current' to the latest
	 *            matched graph node.
	 */
	private void dfsMatch(GraphNode gNode, PathNode pNode, int depth,
			List<GraphNode> path) {

		if (!gNode.equals(pNode))
			return;
		else if (pNode.getOutEdge() == null) {
			// the path is matched matched
			if (depth < minCost) {
				minCost = depth;
				path.add(gNode);
				minCostPath = new LinkedList<>(path);
				path.remove(path.size() - 1);

			}
		} else {

			Iterator<GraphEdge> eit = gNode.getOutEdges().values().iterator();
			while (eit.hasNext()) {
				GraphEdge e = eit.next();

				PathNode pn = matchEdge(e, pNode.getOutEdge());
				if (pn != null) {
					path.add(gNode);
					dfsMatch(e.getTo(), pn, depth + 1, path);
					path.remove(path.size() - 1);
				}
			}
		}

	}

	private PathNode matchEdge(Edge e, PathEdge pEdge) {
		return (PathNode) e.match(pEdge);
	}

	private GraphNode matchNode(Node pNode) {
		return (GraphNode) nodeMap.get(pNode.getName());
	}



	/**
	 * 1. Merge two sibling nodes, a and b, as a new node containing a and b
	 * 
	 * 2. Add a new edge from the parent to the new node. The new edge is named
	 * “edgeNameA|edgeNameB”
	 */
	private void merge() {

	}

}
