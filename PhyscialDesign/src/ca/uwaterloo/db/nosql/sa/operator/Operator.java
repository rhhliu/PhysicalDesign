/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import ca.uwaterloo.db.nosql.sa.Edge;
import ca.uwaterloo.db.nosql.sa.SolutionGraph;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public abstract class Operator {
	
	public static final double w = 0.1;
	abstract public boolean isApplyOnSiblingEdges();
	abstract public boolean isApplyOnConsecutiveEdges();
	abstract public boolean isElligible(Edge e0, Edge e1);
	abstract public double getCostGain(Edge e0, Edge e1);

	abstract public void apply(SolutionGraph sg, Edge e0, Edge e1);
}
