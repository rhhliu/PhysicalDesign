package ca.uwaterloo.db.design.graph;

import java.util.HashMap;
import java.util.Iterator;

public abstract class AbstractPath implements Path {

	public AbstractPath() {
		super();
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		Iterator<? extends Node> it = nodeIterator();
		while (it.hasNext()) {
			Node n = it.next();
			sb.append(n.toString()).append("--");

			HashMap<String, ? extends Edge> outEdges = n.getOutEdges();
			if (outEdges != null && outEdges.size() > 0) {
				Edge outEdge = outEdges.values().iterator().next();
				sb.append(outEdge.toString()).append("-->");
			}
		}
		return sb.toString();
	}

	@Override
	public abstract Iterator<? extends Node> nodeIterator();
}