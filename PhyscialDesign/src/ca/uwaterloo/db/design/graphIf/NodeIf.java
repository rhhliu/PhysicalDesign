package ca.uwaterloo.db.design.graphIf;

import java.util.HashMap;

import ca.uwaterloo.db.design.graph.Edge;

public interface NodeIf {

	public abstract String getName();

	public abstract HashMap<String, ? extends Edge> getOutEdges();

	public abstract EdgeIf getEdge(String edgeName);

}