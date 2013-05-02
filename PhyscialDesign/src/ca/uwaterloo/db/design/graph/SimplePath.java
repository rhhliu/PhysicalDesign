package ca.uwaterloo.db.design.graph;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-03-27
 * PhyscialDesign
 */
public class SimplePath extends AbstractPath  {
	
	protected LinkedList<PathNode> nodeList = new LinkedList<>();
	
	
	
	public SimplePath(String str){
		String [] strs = str.split(seperator);
		
		nodeList.add(new PathNode(strs[0]));
		for(int i= 1; i< strs.length; i++){
			String arcName = strs[i++];
			String nodeName = strs[i];
			
			PathNode n = new PathNode(nodeName);
			PathNode pre = nodeList.get(nodeList.size()-1);
			PathEdge e = new PathEdge(arcName);
			e.setFrom(pre);
			e.setTo(n);
			
			//pre.addOutEdge(e);
			nodeList.add(n);
		}
	}
	
	
	

	public Iterator<PathNode> nodeIterator() {
		return nodeList.iterator();
	}
}
