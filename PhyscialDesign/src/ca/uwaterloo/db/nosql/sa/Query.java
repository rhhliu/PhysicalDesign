/**
 * 
 */
package ca.uwaterloo.db.nosql.sa;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-06-02
 * PhyscialDesign
 */
public class Query extends Graph{
	
	public static AtomicInteger counter = new AtomicInteger(0);
	private Node inputNode;
	private int id = counter.incrementAndGet();
	private String name;
	
	private LinkedHashMap<Edge, Integer> edges = new LinkedHashMap<>();
	private Map<Edge, Integer> levelmap = new HashMap<>();

	public Query(String name) {
		this.name = name;
	}


	/**
	 * @param inputNode the inputNode to set
	 */
	public void setInputNode(Node inputNode) {
		this.inputNode = inputNode;
	}
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	private Edge[] es;
	public void addEdges(Edge... es){
		this.es = es;
		for (int i = 0; i< es.length; i++){
			addEdge(es[i]);
		}
	}
	
	public void addLevles(Integer...levels ){
		
		for (int i = 0; i< levels.length; i++){
			levelmap.put(es[i], levels[i]);
		}
	}

	
	public void addEdge(Edge e){
		
		if (edges.containsKey(e)){
			int c = edges.get(e);
			edges.put(e, c+1);
		
		}else{
			
			edges.put(e, 1);
		}
		
		e.addQuery(this);
		
	}
	
	public int getEdgeReferenceCount(Edge e){
		return edges.get(e);
	}
	
	public int getEdgeLevel(Edge e){
		
		return levelmap.get(e);
	}

	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return name;
		
	}
	
}
