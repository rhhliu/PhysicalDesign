package ca.uwaterloo.db.design.graph;

import java.util.Iterator;

import ca.uwaterloo.db.design.graphIf.PathNodeIf;

public interface Path {
	public final String seperator = ":";
	public abstract Iterator<? extends PathNodeIf> nodeIterator();

}