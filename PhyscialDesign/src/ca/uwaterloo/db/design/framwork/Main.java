package ca.uwaterloo.db.design.framwork;

import ca.uwaterloo.db.design.graph.Graph;
import ca.uwaterloo.db.design.graph.GraphBuilder;
import ca.uwaterloo.db.design.graph.GraphPath;
import ca.uwaterloo.db.design.graph.NoPathFoundException;
import ca.uwaterloo.db.design.graph.SimplePath;
import ca.uwaterloo.db.design.graph.Path;

/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com University of Waterloo 2013-03-27
 *         PhyscialDesign
 */
public class Main {
	public static void main(String[] args) {
		Graph graph = GraphBuilder.buildInitGraph();
		WorkLoad workLoad = new WorkLoad();

		SimplePath p1 = new SimplePath("uid:following:uid:tweet:tid:body:body");
		SimplePath p2 = new SimplePath("uid:following:uid:tweet:tid:ts:ts");
		workLoad.addPath(p1);

		GraphPath mp1 = null, mp2 = null;
		try {
			mp1 = graph.getMinCostPath(p1, graph);
			mp2 = graph.getMinCostPath(p2, graph);
			
		} catch (NoPathFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(mp1);
		System.out.println(mp2);

	}
}
