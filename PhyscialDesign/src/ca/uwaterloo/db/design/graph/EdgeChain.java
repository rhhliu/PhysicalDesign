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
 * 2013-04-18
 * PhyscialDesign
 */
public class EdgeChain {
	private List<String> lables = new LinkedList<String>();
	private Node endNode;
	
	public EdgeChain(List<String> names, Node endNode){
		this.lables = names;
		this.endNode = endNode;
	}


	/**
	 * @return the names
	 */
	public List<String> getNames() {
		return lables;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(List<String> names) {
		this.lables = names;
	}

	/**
	 * @return the endNode
	 */
	public Node getEndNode() {
		return endNode;
	}

	/**
	 * @param endNode the endNode to set
	 */
	public void setEndNode(Node endNode) {
		this.endNode = endNode;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = lables.iterator();
		while (it.hasNext()){
			sb.append(it.next() + "_");
		}
		return sb.toString();
	}


	public Iterator<String> getLableIterator() {
		return lables.iterator();
	}
	
	
	
}
