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
		
		Node name = new Node("name", Type.TEXT);
		Edge uname = new Edge("name", true);
		uname.setFrom(uid);
		uname.setTo(name);
		
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
		
		Query timeLineQuery = new Query("Timeline");
		timeLineQuery.addQueryPaths(tlp1, tlp2);
		
		QueryPath ulp1 = new QueryPath();
		ulp1.addEdges(author, twbody);
		
		QueryPath ulp2 = new QueryPath();
		ulp2.addEdges(author, twts);
		Query userLineQuery = new Query("Userline");
		userLineQuery.addQueryPaths( ulp1, ulp2);
		

		QueryPath passPath1 = new QueryPath();
		passPath1.addEdges(upass);
		
		QueryPath passPath2 = new QueryPath();
		passPath2.addEdges(uname);
		Query passwordQuery = new Query("UserInfor");
		passwordQuery.addQueryPaths(passPath1, passPath2);

		QueryPath fllPath = new QueryPath();
		fllPath.addEdges(lead);
		Query followersQuery = new Query("Followers");
		followersQuery.addQueryPaths(fllPath);
		
		return sg;
	}

}
