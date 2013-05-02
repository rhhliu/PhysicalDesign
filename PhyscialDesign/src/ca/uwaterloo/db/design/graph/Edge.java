package ca.uwaterloo.db.design.graph;

import ca.uwaterloo.db.design.graphIf.EdgeIf;
import ca.uwaterloo.db.design.graphIf.NodeIf;
import ca.uwaterloo.db.design.graphIf.PathNodeIf;


/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-03-27
 * PhyscialDesign
 */
public abstract class Edge implements EdgeIf{
	static final String CONCAT_SEPERATOR	=	"^";
	static final String MERGE_SEPERATOR		=	"|";
	
	protected NodeIf from, to;
	private int fromCardinality, toCardinality;
	protected String name;
	
	protected Edge(){
		
	}
	
	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.EdgeIF#getFrom()
	 */
	@Override
	public NodeIf getFrom() {
		return from;
	}

	/**
	 * @param from the node1 to set
	 */
	
	public void setFrom(Node from) {
		this.from = from;
		if (from != null)
			from.addAdj(this);
	}

	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.EdgeIF#getTo()
	 */
	@Override
	public NodeIf getTo() {
		return to;
	}

	/**
	 * @param to the node2 to set
	 */
	public void setTo(NodeIf to) {
		this.to = to;

	}

	public int getFromCardinality() {
		return fromCardinality;
	}
	public void setFromCardinality(int fromCardinality) {
		this.fromCardinality = fromCardinality;
	}
	public int getToCardinality() {
		return toCardinality;
	}
	public void setToCardinality(int toCardinality) {
		this.toCardinality = toCardinality;
	}
	/* (non-Javadoc)
	 * @see ca.uwaterloo.db.design.graph.EdgeIF#getName()
	 */
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public EdgeIf merge(EdgeIf e){
		//TODO: add impl for merge
		return null;
	}
	
	public EdgeIf inLine(EdgeIf e){
		//TODO: add impl. for inLine
		return null;
	}



	public abstract PathNodeIf match(GraphNode gNode, PathNode pNode) ;
}
