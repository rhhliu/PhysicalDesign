package ca.uwaterloo.db.nosql.sa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Query2 {
	private String name;

	public Query2(String name) {
		this.name = name;
	}
	
	public void addQueryPaths(QueryPath... queryPaths){
		pathSet.addAll(Arrays.asList(queryPaths));
		
		for (QueryPath qp : queryPaths) {
			qp.setQuery(this);
		}
	}

	private Set<QueryPath> pathSet = new HashSet<>();
	
}
