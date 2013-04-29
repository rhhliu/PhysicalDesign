package ca.uwaterloo.db.design.framwork;

import java.util.HashSet;
import java.util.Set;

import ca.uwaterloo.db.design.graph.Graph;
import ca.uwaterloo.db.design.graph.GraphBuilder;
import ca.uwaterloo.db.design.graph.GraphPath;
import ca.uwaterloo.db.design.graph.NoPathFoundException;
import ca.uwaterloo.db.design.graph.SimplePath;

/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com University of Waterloo 2013-03-27
 *         PhyscialDesign
 */
public class Main {
	public static boolean DEBUG = true;
	
	public static void main(String[] args) {
		Graph graph = GraphBuilder.buildInitGraph();
		Set<Query> querySet = WorkLoadBuilder.buildWorkLoads();

		Set<GraphPath> gpSet = new HashSet<>(), resultSet = null;

		int minTotalCost = Integer.MAX_VALUE;

		try {
			int count = 0;
			while (true) {
				for (Query query : querySet) {
					for (SimplePath p : query.getPathList()) {
						GraphPath minCostGP = graph.getMinCostPath(p, graph);
						gpSet.add(minCostGP);
					}
				}

				// calculate total cost
				int totalCost = 0;
				for (GraphPath gp : gpSet) {
					totalCost += gp.getCost();
				}

				if (totalCost >= minTotalCost || count++ > 0)
					break;

				minTotalCost = totalCost;
				resultSet = new HashSet<>(gpSet);
				gpSet.clear();
				
				graph.augment();
			}

		} catch (NoPathFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//output
		if (resultSet != null){
			for (GraphPath gp : resultSet) {
				System.out.println(gp);
			}
		}

	}
}
