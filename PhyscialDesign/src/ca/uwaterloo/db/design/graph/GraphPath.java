/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-21
 * PhyscialDesign
 */
public class GraphPath extends AbstractPath {

	
	private LinkedList<GraphNode> nodeList = new LinkedList<>();
	public GraphPath(List<GraphNode> minCostPath) {
		nodeList.addAll(minCostPath);
	}
	@Override
	public Iterator<GraphNode> nodeIterator() {
		return nodeList.iterator();
	}
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
