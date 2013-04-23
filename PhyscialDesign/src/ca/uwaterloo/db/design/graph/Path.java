package ca.uwaterloo.db.design.graph;

import java.util.Iterator;

public interface Path {
	public final String seperator = ":";
	public abstract Iterator<? extends Node> nodeIterator();

}