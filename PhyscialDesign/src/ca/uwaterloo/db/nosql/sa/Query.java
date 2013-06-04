/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-02
 * PhyscialDesign
 */
public class Query extends Graph{
	
	public static AtomicInteger counter = new AtomicInteger(0);
	private Node inputNode;
	private int id = counter.incrementAndGet();
	
	

	/**
	 * @param inputNode the inputNode to set
	 */
	public void setInputNode(Node inputNode) {
		this.inputNode = inputNode;
	}
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "X";
		
	}
	
}
