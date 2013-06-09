package ca.uwaterloo.db.nosql.sa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
	private String name;
	protected Set<QueryPath> queryPathSet = new HashSet<>(); 
	private boolean isOriginal = false;
	
	public Edge(String name, boolean isOriginal){
		this.name = name;
		this.isOriginal = isOriginal;
	}
	
//	public Edge(String name){
//		this(name, false);
//	}
	
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
	 * @return the isOriginal
	 */
	public boolean isOriginal() {
		return isOriginal;
	}

	/**
	 * @param isOriginal the isOriginal to set
	 */
	public void setOriginal(boolean isOriginal) {
		this.isOriginal = isOriginal;
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
	public Set<QueryPath> getQueryPaths() {
		return queryPathSet;
	}
	
	

	/**
	 * @param queries the queries to set
	 */
	public void setQueries(Set<QueryPath> queryPath) {
		this.queryPathSet = queryPath;
	}

	public String toString(){
		return getName();
	}

	public Map<Attribute, Attribute> getMappings() {
		HashMap<Attribute, Attribute> m = new HashMap<>();
		m.put(from.getAttribute(), to.getAttribute());
		return m;
	}

	public void removeFrom() {
		from.getOutEdges().remove(this);
		this.from = null;
		
	}

	public void removeTo() {
		to.getInEdges().remove(this);
		this.to = null;
				
		
	}

	public void addQueryPaths(QueryPath... qs) {
		this.queryPathSet.addAll(Arrays.asList(qs));
	}
	
	public void addQueryPath(QueryPath qp){
		this.queryPathSet.add(qp);
	}

//	public int getQueryRefCount(Query q) {
//		if (queryPath.contains(q))
//			return 1;
//		return 0;
//	}

	public Edge getFirstSubEdge() {
		
		return this;
	}

	public Edge getLastSubEdge() {
		return this;
	}

	public void removeQueryPath(QueryPath qp) {
		this.queryPathSet.remove(qp);
		
	}

	public Edge cloneEdge() {
		Edge e = new Edge();
		e.from = from;
		e.to = to;
		e.isOriginal = isOriginal;
		e.name = name;
		e.queryPathSet.addAll(queryPathSet);
		
		return e;
	}

	public void removeQueryPaths(Collection<QueryPath> commonPathSet) {
		this.queryPathSet.removeAll(commonPathSet);
	}

	
}
