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
		
//		Node ts = new Node("ts", Type.LONG);
//		Edge twts = new Edge("twts");
//		twts.setFrom(tid);
//		twts.setTo(ts);
		
		sg.addNodes(uid, pass, tid, body);
		sg.addEdges(follows, lead, upass, author, twbody);
		
		
		// Querys;
		Query timeLineQuery = new Query("Timeline");
		Query userLineQuery = new Query("Userline");
		Query passwordQuery = new Query("Password");
		Query followersQuery = new Query("Followers");
		
		
		timeLineQuery.addEdges(follows, author, twbody);
		timeLineQuery.addLevles(0,1,2);
		userLineQuery.addEdges(author);
		userLineQuery.addLevles(0);
		passwordQuery.addEdges(upass);
		passwordQuery.addLevles(0);
		followersQuery.addEdges(lead);
		followersQuery.addLevles(0);
		
		
		// Query references
//		lead.addQuery(followersQuery);
//		follows.addQuery(timeLineQuery);
//		upass.addQuery(passwordQuery);
//		
//		author.addQuerys(timeLineQuery, userLineQuery);
//		twbody.addQuerys(timeLineQuery, userLineQuery);
//		twts.addQuerys(timeLineQuery,userLineQuery);
		
		return sg;
	}

}
