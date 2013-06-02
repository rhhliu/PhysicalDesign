/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public class SolutionGraph extends Graph {
	
	
	/**
	 * Apply the specified operator on this solution graph
	 * @param operator
	 */
	public void applyOperator(Operator operator){
		operator.apply(this);
	}
	
	public int getCost(){
		//TODO : add penalty of space
		return edges.size();
	}
}


