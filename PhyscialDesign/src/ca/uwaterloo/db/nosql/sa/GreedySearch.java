/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.nosql.sa.operator.GroupOperator;
import ca.uwaterloo.db.nosql.sa.operator.InlineOperator;
import ca.uwaterloo.db.nosql.sa.operator.MergeOperator;
import ca.uwaterloo.db.nosql.sa.operator.Operator;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-01
 * PhyscialDesign
 */
public class GreedySearch extends Search {
	
	public GreedySearch(SolutionGraph sg){
		this.solutionGraph = sg;
		operaterSet.add(new InlineOperator());
		operaterSet.add(new MergeOperator());
		operaterSet.add(new GroupOperator());
	}

	@Override
	public SolutionGraph doSearch() {
		final SolutionGraph s = solutionGraph;
		Move mv;
		
		do{
			mv = getBestMove(s, operaterSet);
			
			//System.out.println("Operator: ");
			System.out.println(mv);
			if (mv.costGain > 0)
				s.apply(mv);
			
			Util.output(s);
		}while (mv.costGain > 0);
		
		
		
		return s;
		
	}

	private Move getBestMove(SolutionGraph s, Set<Operator> ops) {
		double maxGain = 0, maxCost = 0, maxDiskSpace = 0;
		Edge maxEdge0 = null, maxEdge1 = null;
		Operator maxOp = null;
		
		//for each operator
		for (Operator op : ops){
			
			// for each possible application of the operator op
			// that is usually two eligible edges
			for (Edge e0 : s.getEdges()){
				
				
				Set<Edge> eSet = null;
				if (op.isApplyOnSiblingEdges()){
					Node from = e0.getFrom();
					eSet = new HashSet<Edge>(from.getOutEdges());
					eSet.remove(e0);
				}else if (op.isApplyOnConsecutiveEdges()){
					Node to = e0.getTo();
					eSet = to.getOutEdges();
				}
				
				if (eSet == null || eSet.size() == 0) continue;
				
				for (Edge e1 : eSet){
					
					if ( !op.isElligible(e0, e1) ) continue;
					
					double cost = op.getCostGain(e0, e1);
					double diskSpace = op.getDiskSpaceChange(e0, e1);
					double costGain = cost/(1+diskSpace);
					if (costGain > maxGain){
						maxGain = costGain;
						maxCost = cost;
						maxDiskSpace= diskSpace;
						maxEdge0 = e0;
						maxEdge1 = e1;
						maxOp = op;
					}
					
				}// end of for e1
			}// end of for e0
		}// end of for ops
		
		
		return new Move(maxGain, maxCost, maxDiskSpace, maxOp, maxEdge0, maxEdge1);
	}

	public static void main(String[] args) {
		SolutionGraph sg = InitSolutionBuilder.buildSolutionGraph();
		if (Search.DEBUG)
			Util.output(sg);
		GreedySearch gs = new GreedySearch(sg);
		SolutionGraph s = gs.doSearch();
		Util.output(s);
	
	}
}
