/**
 * 
 */
package ca.uwaterloo.db.design.graph;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-17
 * PhyscialDesign
 */
public class NoPathFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6911279258239010014L;

	public NoPathFoundException(String msg) {
		super(msg);
	}
	
}
