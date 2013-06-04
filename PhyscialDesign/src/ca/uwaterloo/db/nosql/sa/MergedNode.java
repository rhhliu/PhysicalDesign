package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.List;

import ca.uwaterloo.db.design.graph.Attribute0;

public class MergedNode extends Node{
	
	private List<Attribute> attList=new ArrayList<>();

	public MergedNode(Node n0, Node n1) {
		attList.addAll(n0.getAttributes());
		attList.addAll(n1.getAttributes());
	}


	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Attribute att: attList){
			sb.append(att).append(',');
		}
		return sb.toString();
	}
	

	public List<Attribute> getAttributes() {
		return attList;
	}
}
