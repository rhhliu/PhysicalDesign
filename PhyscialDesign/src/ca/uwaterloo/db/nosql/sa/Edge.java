package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.uwaterloo.db.design.graphIf.NodeIf;

public class Edge {
	
	static final String CONCAT_SEPERATOR	=	"^";
	static final String MERGE_SEPERATOR		=	"|";
	
	
	private Node from, to;
	//private int fromMultiplicity, toMultiplicity;
	private String name;
	protected Set<Query> queries = new HashSet<Query>(); 
	
	public Edge(String name){
		this.name = name;
	}
	
	protected Edge(){}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param lookUpName the name to set
	 */
	public void setName(String lookUpName) {
		this.name = lookUpName;
	}



	/**
	 * @return the from
	 */
	public Node getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(Node from) {
		
		if (this.from != null){
			this.from.getOutEdges().remove(this);
		}
		
		this.from = from;
		from.addOutEdge(this);
	}

	/**
	 * @return the to
	 */
	public Node getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(Node to) {
		if (this.to != null){
			this.to.getInEdges().remove(this);
		}
		
		this.to = to;
		to.addInEdge(this);
	}
	
	
	/**
	 * @return the queries
	 */
	public Set<Query> getQueries() {
		return queries;
	}
	
	

	/**
	 * @param queries the queries to set
	 */
	public void setQueries(Set<Query> queries) {
		this.queries = queries;
	}

	public String toString(){
		return getName();
	}

	public Map<Attribute, Attribute> getMappings() {
		HashMap<Attribute, Attribute> m = new HashMap<>();
		m.put(from.getAttribute(), to.getAttribute());
		return m;
	}

	public Edge cloneEdge() {
		Edge e = new Edge(name);
		e.queries = queries;
		return e;
	}

	public void removeFrom() {
		from.getOutEdges().remove(this);
		this.from = null;
		
	}

	public void removeTo() {
		to.getInEdges().remove(this);
		this.to = null;
				
		
	}

	public void addQuerys(Query... qs) {
		this.queries.addAll(Arrays.asList(qs));
	}
	
	public void addQuery(Query q){
		this.queries.add(q);
	}

	public int getQueryRefCount(Query q) {
		if (queries.contains(q))
			return 1;
		return 0;
	}

	public Edge getFirstSubEdge() {
		
		return this;
	}

	public Edge getLastSubEdge() {
		return this;
	}
	
}
