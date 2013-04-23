package ca.uwaterloo.db.design.framwork;

import java.util.ArrayList;
import java.util.List;

import ca.uwaterloo.db.design.graph.SimplePath;

/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-03-27
 * PhyscialDesign
 */
public class Query {
	private List<SimplePath> pathList = new ArrayList<>();
	private double weight;

	public Query(double weight) {

		this.weight = weight;
	}

	public void addPath(SimplePath p) {
		pathList.add(p);
	}

	public void setWeight(double weight) {
		this.weight = weight;
		
	}
	
	public List<SimplePath> getPathList(){
		return pathList;
	}
	
	
}
