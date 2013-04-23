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
public class WorkLoad {
	private List<SimplePath> pathList = new ArrayList<>();

	public void addPath(SimplePath p) {
		pathList.add(p);
	}
	
	
}
