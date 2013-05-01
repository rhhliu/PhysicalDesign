package ca.uwaterloo.db.design.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com University of Waterloo 2013-03-27
 *         PhyscialDesign
 */
public class Graph {
	private HashMap<String, GraphNode> nodeMap = new HashMap<>();

	// copy constructor
	public Graph(Graph graph) {
		Set<GraphNode> visited = new HashSet<>();
		for ( GraphNode n: graph.nodeMap.values()){
			dfsClone(n, visited);
		}
	}

	public Graph() {}


	private GraphNode dfsClone(GraphNode n, Set<GraphNode> visited) {
		
		
		if (visited.contains(n)){
			return nodeMap.get(n.getName());
		}else{
			GraphNode nn = new GraphNode(n);
			visited.add(n);
			nodeMap.put(nn.name, nn);
			for(GraphEdge e : n.getOutEdges().values()){
				GraphEdge ee = new GraphEdge(e);
				
				
				if (e.getFrom() == n){
					ee.setFrom(nn);
					GraphNode copiedNode2 = dfsClone(e.getTo(), visited);
					if (copiedNode2 != null) 
						ee.setTo(copiedNode2);
				}else{
					ee.setTo(nn);
					GraphNode copiedNode1 = dfsClone(e.getFrom(), visited);
					if (copiedNode1 != null) 
						ee.setFrom(copiedNode1);
				}
				
				
				
			}
			
			return nn;
		}
		
	}

	public void add(GraphNode node) {
		nodeMap.put(node.getName(), node);
	}

	public Node lookupNode(String nodeName) {
		return nodeMap.get(nodeName);
	}
	
	/**
	 * Augment the graph by applying the three operations.
	 * Details is in graph_model.pdf
	 */
	public void augment() {
		Graph snapshotGraph = this.cloneGraph();
		merge(snapshotGraph);
		inline(snapshotGraph);
	}

	
	// Deep clone of nodes and edges.
	private Graph cloneGraph() {
		return new Graph(this);
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

				PathNode pn = matchEdge(gNode, e, pNode);
				if (pn != null) {
					path.add(gNode);
					dfsMatch(gNode.getAdjNode(e), pn, depth + 1, path);
					path.remove(path.size() - 1);
				}
			}
		}

	}

	private PathNode matchEdge(GraphNode gNode, GraphEdge e, PathNode pNode) {
		return (PathNode) e.match(gNode, pNode);
	}

	private GraphNode matchNode(Node pNode) {
		return (GraphNode) nodeMap.get(pNode.getName());
	}

	
	/**
	 * Operation Merge
	 * 
	 * 1. Merge two sibling nodes, a and b, as a new node containing a and b
	 * 
	 * 2. Add a new edge from the parent to the new node. The new edge is named
	 * “edgeNameA|edgeNameB”
	 * @param g 
	 */
	private void merge(final Graph g) {
		for (GraphNode n : g.nodeMap.values()) {
			
			mergeEdges( n);
		}
	}

	private void mergeEdges(GraphNode n) {
		Collection<GraphEdge> ssEdges = (Collection<GraphEdge>) n.getOutEdges().values();
		
		for (GraphEdge ssEdge1 : ssEdges) {
			for (GraphEdge ssEdge2 : ssEdges) {
				if (ssEdge1 == ssEdge2) continue;
				
				
				
				// Generated new end node
				GraphNode ssNode1 = n.getAdjNode(ssEdge1);
				GraphNode ssNode2 = n.getAdjNode(ssEdge2);
				
				GraphNode fromNode = nodeMap.get(n.getName());
				GraphNode node1 = nodeMap.get(ssNode1.getName());
				GraphNode node2 = nodeMap.get(ssNode2.getName());
				
				GraphEdge e1 = fromNode.getEdge(ssEdge1.getName());
				GraphEdge e2 = fromNode.getEdge(ssEdge2.getName());
				
				GraphNode newNode = GraphNode.mergeNodes(node1, node2);
				
				// 2. generate the new edge
				GraphEdge newEdge = GraphEdge.mergeEdge(e1, e2);
				
				// 3. connect the new edge with the end nodes
				newEdge.setFrom(fromNode);
				newEdge.setTo(newNode);
				
				this.nodeMap.put(newNode.getName(), newNode);
				
			}
		}
	}

	
	
	/**
	 * Operation Inline
	 * 
	 * 1. For any edge(a,b) and edge(b,c) in E (edgeset), add a new edge (a,c) into E
	 * 2. The new edge is named as concatenation of the the name of edge(a,b) and edge(b,c)
	 * @param snapshotGraph 
	 */
	private void inline(Graph snapshotGraph){
		HashSet<GraphEdge> visitedEdge = new HashSet<>();
		
		// prefix 'ss' means snapshot
		// varibles prefixed by 'ss' is the edges and nodes from snapshot graph.
		
		for (GraphNode ssStartNode : snapshotGraph.nodeMap.values()) {
		
			Collection<GraphEdge> edges = (Collection<GraphEdge>) ssStartNode.getOutEdges().values();
			
			
			for (GraphEdge ssEdge1 : edges) {
				
				if (visitedEdge.contains(ssEdge1)) 
					continue;
				
				visitedEdge.add(ssEdge1);
				
				// get the other end of node other than n.
				GraphNode ssMidNode = ssStartNode.getAdjNode(ssEdge1);
				
				
				Collection<GraphEdge> edges2 = ssMidNode.outEdges.values();
				
				GraphNode startNode = (GraphNode) lookupNode(ssStartNode.getName());
				GraphNode midNode = (GraphNode) lookupNode(ssMidNode.getName());
				
				for(GraphEdge ssEdge2 : edges2){
					if (ssEdge1 == ssEdge2) continue;
					
					GraphNode ssEndNode = ssMidNode.getAdjNode(ssEdge2);
					GraphNode endNode = (GraphNode) lookupNode(ssEndNode.getName());
					
					GraphEdge edge1 = (GraphEdge) startNode.getEdge(ssEdge1.getName());
					GraphEdge edge2 = (GraphEdge) midNode.getEdge(ssEdge2.getName());
					
					// new edge is created and added into the Adj of startNode
					GraphEdge newEdge = GraphEdge.concantinate(edge1,edge2);
					
					// connect the new edge with the end points.
					newEdge.setFrom(startNode);
					newEdge.setTo(endNode);
				}
			}
		}
	}

}
