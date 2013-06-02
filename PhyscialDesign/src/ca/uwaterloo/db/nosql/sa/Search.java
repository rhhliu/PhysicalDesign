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
public abstract class Search {
	protected SolutionGraph solutionGraph;
	
	public void setInitialSolution(SolutionGraph sg){
		this.solutionGraph = sg;
	}
	
	abstract void search();
	
	public SolutionGraph getResult(){
		return solutionGraph;
	}
}
