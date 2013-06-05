package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
	private Set<Edge> outEdges = new HashSet<>(), inEdges = new HashSet<>();
	
	private Attribute attribute;
	public Node(String attName) {
		this( new Attribute(attName, Type.TEXT));
	}
	
	public Node(String attName, Type type) {
		this( new Attribute(attName, type));
	}
	
	public Node(Attribute att){
		this.attribute = att;
	}
	
	protected Node(){}

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


	public String toString() {
		return attribute.getName();
	}

	public Attribute getAttribute() {
		return attribute;
	}
	

	public List<Attribute> getAttributes() {
		List<Attribute> attList = new ArrayList<>();
		attList.add(attribute);
		return attList;
	}
	
	public void setAttribute(Attribute attribute){
		this.attribute = attribute;
	}

	public void addOutEdge(Edge edge) {
		outEdges.add(edge);
	}
	
	public void addInEdge(Edge edge) {
		inEdges.add(edge);
	}


}
