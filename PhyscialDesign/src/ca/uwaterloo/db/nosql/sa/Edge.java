package ca.uwaterloo.db.nosql.sa;

public class Edge {
	Node from, to;

	/**
	 * @return the from
	 */
	public Node getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Node from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public Node getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(Node to) {
		this.to = to;
	}
	
}
