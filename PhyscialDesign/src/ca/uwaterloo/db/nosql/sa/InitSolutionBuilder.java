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
		
		Edge follows = new Edge("follows", true);
		follows.setFrom(uid);
		follows.setTo(uid);
		
		Edge lead = new Edge("lead", true);
		lead.setFrom(uid);
		lead.setTo(uid);
		
		Node pass = new Node("pass", Type.TEXT);
		Edge upass = new Edge("upass", true);
		upass.setFrom(uid);
		upass.setTo(pass);
		
		Node tid = new Node("tid", Type.INT);
		
		Edge author = new Edge("author", true);
		author.setFrom(uid);
		author.setTo(tid);
		
		Node body = new Node("body", Type.TEXT);
		Edge twbody = new Edge("twbody", true);
		twbody.setFrom(tid);
		twbody.setTo(body);
		
		Node ts = new Node("ts", Type.LONG);
		Edge twts = new Edge("twts", true);
		twts.setFrom(tid);
		twts.setTo(ts);
		
		sg.addNodes(uid, pass, tid, body, ts);
		sg.addEdges(follows, lead, upass, author, twbody);
		
		
		//Query path
		QueryPath tlp1 = new QueryPath();
		tlp1.addEdges(follows, author, twbody);
		QueryPath tlp2 = new QueryPath();
		tlp2.addEdges(follows, author, twts);
		
		Query2 timeLineQuery = new Query2("Timeline");
		timeLineQuery.addQueryPaths(tlp1, tlp2);
		
		QueryPath ulp1 = new QueryPath();
		ulp1.addEdges(author, twbody);
		
		QueryPath ulp2 = new QueryPath();
		ulp2.addEdges(author, twts);
		Query2 userLineQuery = new Query2("Userline");
		userLineQuery.addQueryPaths( ulp1, ulp2);
		

		QueryPath passPath = new QueryPath();
		passPath.addEdges(upass);
		Query2 passwordQuery = new Query2("Password");
		passwordQuery.addQueryPaths(passPath);

		QueryPath fllPath = new QueryPath();
		fllPath.addEdges(lead);
		Query2 followersQuery = new Query2("Followers");
		followersQuery.addQueryPaths(fllPath);
		
		return sg;
	}

}
