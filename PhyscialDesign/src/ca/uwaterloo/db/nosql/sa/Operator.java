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
public abstract class Operator {
	abstract public void apply(SolutionGraph s);
}
