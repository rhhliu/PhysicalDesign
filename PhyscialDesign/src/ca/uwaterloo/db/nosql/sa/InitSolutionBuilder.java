/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-04
 * PhyscialDesign
 */
public class InitSolutionBuilder {

	public static SolutionGraph buildSolutionGraph() {
		SolutionGraph sg = new SolutionGraph();
		
		Node uid = new Node("uid", Type.INT);
		
		Edge follows = new Edge("follows");
		follows.setFrom(uid);
		follows.setTo(uid);
		
		Edge lead = new Edge("lead");
		lead.setFrom(uid);
		lead.setTo(uid);
		
		Node pass = new Node("pass", Type.TEXT);
		Edge upass = new Edge("upass");
		upass.setFrom(uid);
		upass.setTo(pass);
		
		Node tid = new Node("tid", Type.INT);
		
		Edge author = new Edge("author");
		author.setFrom(uid);
		author.setTo(tid);
		
		Node body = new Node("body", Type.TEXT);
		Edge twbody = new Edge("twbody");
		twbody.setFrom(tid);
		twbody.setTo(body);
		
		Node ts = new Node("ts", Type.LONG);
		Edge tsedge = new Edge("twts");
		tsedge.setFrom(tid);
		tsedge.setTo(body);
		
		return sg;
	}

}
