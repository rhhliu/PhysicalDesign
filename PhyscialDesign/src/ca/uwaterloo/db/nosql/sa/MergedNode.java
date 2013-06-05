package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.List;

import ca.uwaterloo.db.design.graph.Attribute0;

public class MergedNode extends Node{
	
	//private List<Attribute> attList=new ArrayList<>();
	Node n0, n1;

	public MergedNode(Node n0, Node n1) {
		this.n0 = n0;
		this.n1 = n1;
	}


	public String toString() {
		return n0 + "; " + n1;
	}
	
}
