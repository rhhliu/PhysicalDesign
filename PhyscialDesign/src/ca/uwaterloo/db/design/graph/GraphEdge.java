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
	public GraphEdge(String name, Node from, Node to) {
		super(name, from, to);
		List<String> list = new LinkedList<>();
		list.add(name);
		EdgeChain chain = new EdgeChain(list, to);
		chainSet.add(chain);
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
	
	public GraphNode getTo() {
		return (GraphNode)to;
	}
	/**
	 * Match 
	 * @param pEdge
	 * @return
	 */
	@Override
	public PathNode match(PathEdge pEdge) {
		if (pEdge == null) return null;
		
		PathNode lastMatchedPathNode = null;
		int maxLen = 0;
		Iterator<EdgeChain> csit = chainSet.iterator();
		
		while (csit.hasNext()){
			EdgeChain ed = csit.next();
			
			Iterator<String> lableItrator = ed.getLableIterator();
			PathEdge curEdge= pEdge;
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
				matchedToPNode = curEdge.getTo();
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

	
	
	
}
