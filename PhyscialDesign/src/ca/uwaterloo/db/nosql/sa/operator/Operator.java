/**
 * 
 */
package ca.uwaterloo.db.nosql.sa.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public static final boolean RETAIN_ORIGINAL = true;
	abstract public boolean isApplyOnSiblingEdges();
	abstract public boolean isApplyOnConsecutiveEdges();
	public boolean isElligible(Edge e0, Edge e1){
		if (isApplied(e0, e1, this))
			return false;
		return true;
	}
	abstract public double getCostGain(Edge e0, Edge e1);
	abstract public double getDiskSpaceChange(Edge e0, Edge e1);

	public void apply(SolutionGraph sg, Edge e0, Edge e1){
		// update opIndex
		addOpIndex(e0, e1, this);
	}
	
	private Map<Pair<Edge,Edge>, List<Operator>> opIndex = new HashMap<>();
	public void addOpIndex(Edge e0, Edge e1, Operator op){
		Pair<Edge, Edge> edgePair = new Pair<Edge, Edge>(e0, e1);
		if (!opIndex.containsKey(edgePair)){
			opIndex.put(edgePair, new ArrayList<Operator>());
		}
		
		List<Operator> opList = opIndex.get(edgePair);
		opList.add(op);
	}
	
	public boolean isApplied(Edge e0, Edge e1, Operator op){
		Pair<Edge, Edge> edgePair = new Pair<>(e0, e1);
		List<Operator> opList = opIndex.get(edgePair);
		if (opList == null) return false;
		
		Iterator<Operator> it = opList.iterator();
		while (it.hasNext()){
			if (it.next() == op)
				return true;
		}
		
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}
	
	
}
