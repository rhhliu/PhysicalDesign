/**
 * 
 */
package ca.uwaterloo.db.design.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import ca.uwaterloo.db.design.graph.PathEdge;
import ca.uwaterloo.db.design.graph.PathNode;
import ca.uwaterloo.db.design.graph.SimplePath;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-04-21
 * PhyscialDesign
 */
public class SimplePathTest {

	/**
	 * Test method for {@link ca.uwaterloo.db.design.graph.SimplePath#SimplePath(java.lang.String)}.
	 */
	@Test
	public void testSimplePath() {
		SimplePath p = new SimplePath("uid:following:uid:tweet:tid:body:body");
		Iterator<PathNode> it = p.nodeIterator();
		PathNode n = it.next();
		assertTrue ( n.getName().equals("uid")) ;
		PathEdge outEdge = n.getOutEdge();
		assertTrue(outEdge.getName().equals("following"));
		
		n = it.next();
		assertTrue(n.getName().equals("uid"));
		outEdge = n.getOutEdge();
		assertTrue(outEdge.getName().equals("tweet"));
		
		n = it.next();
		assertTrue(n.getName().equals("tid"));
		outEdge = n.getOutEdge();
		assertTrue(outEdge.getName().equals("body"));
		
		n = it.next();
		assertTrue( n.getName().equals("body"));
	}

	

}
