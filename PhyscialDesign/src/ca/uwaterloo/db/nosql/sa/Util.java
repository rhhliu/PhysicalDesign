/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

import java.util.Set;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-02
 * PhyscialDesign
 */
public class Util {
	static public void output(Graph g){
		System.out.println();
		System.out.println("--------------------------------------------");
		Set<Node> nodes = g.getNodes();
		for (Node n : nodes){
			System.out.print(n + " : ");
			Set<Edge> outEdges = n.getOutEdges();
			for (Edge edge : outEdges) {
				System.out.print("-[" + edge + "]->"+edge.getTo() + ";   ");
			}
			System.out.println();
		}
		System.out.println("--------------------------------------------");
	}
}
