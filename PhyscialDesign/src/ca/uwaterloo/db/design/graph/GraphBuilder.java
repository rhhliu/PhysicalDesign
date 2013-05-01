package ca.uwaterloo.db.design.graph;


/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-03-27
 * PhyscialDesign
 */
public class GraphBuilder {
	
	static public Graph buildInitGraph(){
		GraphNode uid = new GraphNode("uid");
		
		GraphNode pass = new GraphNode("password");
		uid.addAdj("password", pass);
		
		GraphNode tid = new GraphNode("tid");
		uid.addAdj("tweet", tid);
		GraphNode bodyNode = new GraphNode("body");
		tid.addAdj("body", bodyNode);
		
		GraphNode tsNode = new GraphNode("ts");
		tid.addAdj("ts", tsNode);
		
		uid.addAdj("following", uid);
		uid.addAdj("follower", uid);
		
		Graph graph = new Graph();
		graph.add(uid);
		graph.add(pass);
		graph.add(tid);
		graph.add(bodyNode);
		graph.add(tsNode);
		
		return graph;
	}
}
