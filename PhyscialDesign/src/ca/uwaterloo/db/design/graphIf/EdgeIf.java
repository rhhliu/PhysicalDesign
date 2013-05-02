package ca.uwaterloo.db.design.graphIf;

public interface EdgeIf {

	/**
	 * @return the node1
	 */
	public abstract NodeIf getFrom();

	/**
	 * @return the node2
	 */
	public abstract NodeIf getTo();

	public abstract String getName();

}