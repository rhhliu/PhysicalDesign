/**
 * 
 */
package ca.uwaterloo.db.design.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Currently, This is implemented as a list of labels (String).
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-18
 * PhyscialDesign
 */
public class EdgeChain {
	private List<String> labels = new LinkedList<String>();
	private Node endNode;
	
	public EdgeChain(List<String> labels, Node endNode){
		this.labels = labels;
		this.endNode = endNode;
	}


	public EdgeChain(EdgeChain edgeChain) {
		this.labels.addAll(edgeChain.labels);
		this.endNode = edgeChain.endNode;
	}


	/**
	 * @return the names
	 */
	public List<String> getNames() {
		return labels;
	}

	/**
	 * @param names the names to set
	 */
	public void setNames(List<String> names) {
		this.labels = names;
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
		Iterator<String> it = labels.iterator();
		while (it.hasNext()){
			sb.append(it.next() + "_");
		}
		return sb.toString();
	}


	public Iterator<String> getLableIterator() {
		return labels.iterator();
	}

	/**
	 * Return a new EdgeChain instance with concatenation of labels from this chain 
	 * and the ec2's chain.
	 * @param ec2
	 * @return
	 */
	public EdgeChain concate(EdgeChain ec2) {
		EdgeChain ec = new EdgeChain(this);
		ec.labels.addAll(ec2.labels);
		return ec;
	}
	
	
	
}
