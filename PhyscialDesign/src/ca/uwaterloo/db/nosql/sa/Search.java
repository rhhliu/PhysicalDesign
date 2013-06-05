/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

import java.util.LinkedHashSet;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.operator.Operator;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public abstract class Search {
	public final static boolean DEBUG = true;
	protected SolutionGraph solutionGraph;
	protected Set<Operator> operaterSet = new LinkedHashSet<>();
	
	public void setInitialSolution(SolutionGraph sg){
		this.solutionGraph = sg;
	}
	
	
	
	abstract SolutionGraph doSearch();
	
	public SolutionGraph getResult(){
		return solutionGraph;
	}
}
