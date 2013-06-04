package ca.uwaterloo.db.design.graph;

import java.util.Iterator;

import ca.uwaterloo.db.design.graphIf.EdgeIf;
import ca.uwaterloo.db.design.graphIf.PathNodeIf;

public abstract class AbstractPath implements Path {

	public AbstractPath() {
		super();
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();
		Iterator<? extends PathNodeIf> it = nodeIterator();
		while (it.hasNext()) {
			PathNodeIf n = it.next();
			sb.append('(').append(n.toString()).append(')');

			
			EdgeIf outEdge = n.getOutEdge();
			if (outEdge != null)
				sb.append(" --[").append(outEdge.toString()).append("]--> ");
			
		}
		return sb.toString();
	}

	@Override
	public abstract Iterator<? extends PathNodeIf> nodeIterator();
}