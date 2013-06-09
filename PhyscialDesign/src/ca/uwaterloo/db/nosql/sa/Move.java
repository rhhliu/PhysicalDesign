package ca.uwaterloo.db.nosql.sa;

import ca.uwaterloo.db.nosql.sa.operator.Operator;

public class Move {
	
	double costGain;
	Operator operator;
	Edge e0, e1;
	private double maxCost;
	private double maxDiskSpace;
	
	public Move(double maxGain, Operator op, Edge e0, Edge e1) {
		this.costGain = maxGain;
		this.operator = op;
		this.e0 = e0;
		this.e1 = e1;
	}

	public Move(double maxGain, double maxCost, double maxDiskSpace,
			Operator op, Edge e0, Edge e1) {
		this.costGain = maxGain;
		this.maxCost = maxCost;
		this.maxDiskSpace = maxDiskSpace;
		this.operator = op;
		this.e0 = e0;
		this.e1 = e1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "Operator: " + operator + " ( " + e0 + " , " + e1 + " )  : CostGain = " + costGain;
	}
	
	
	
}
