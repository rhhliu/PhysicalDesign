package ca.uwaterloo.db.nosql.sa;

import java.util.Set;

public class Node {
	Set<Edge> outEdges, inEdges;

	/**
	 * @return the outEdges
	 */
	public Set<Edge> getOutEdges() {
		return outEdges;
	}

	/**
	 * @param outEdges the outEdges to set
	 */
	public void setOutEdges(Set<Edge> outEdges) {
		this.outEdges = outEdges;
	}

	/**
	 * @return the inEdges
	 */
	public Set<Edge> getInEdges() {
		return inEdges;
	}

	/**
	 * @param inEdges the inEdges to set
	 */
	public void setInEdges(Set<Edge> inEdges) {
		this.inEdges = inEdges;
	}
	
}
