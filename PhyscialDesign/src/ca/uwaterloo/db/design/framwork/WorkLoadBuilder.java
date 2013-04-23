/**
 * 
 */
package ca.uwaterloo.db.design.framwork;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.design.graph.SimplePath;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-22
 * PhyscialDesign
 */
public class WorkLoadBuilder {
	static Set<Query> buildWorkLoads(){
		HashSet<Query> workLoadSet = new HashSet<>(); 
		
		Query workLoad = new Query(0.8);

		SimplePath p1 = new SimplePath("uid:following:uid:tweet:tid:body:body");
		SimplePath p2 = new SimplePath("uid:following:uid:tweet:tid:ts:ts");
		workLoad.addPath(p1);
		workLoad.addPath(p2);
		workLoad.setWeight(0.8);
		return workLoadSet;
	}
}
