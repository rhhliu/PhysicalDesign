package ca.uwaterloo.db.design.graph;


/**
 * 
 * @author Rui Liu, rui.liu09@gmail.com
 * University of Waterloo
 * 2013-03-27
 * PhyscialDesign
 */
public abstract class Edge {
	static final String CONCAT_SEPERATOR	=	"^";
	static final String MERGE_SEPERATOR		=	"|";
	
	protected Node from, to;
	private int fromCardinality, toCardinality;
	protected String name;
	
	protected Edge(){
		
	}
	
	/**
	 * @return the node1
	 */
	public Node getFrom() {
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

	/**
	 * @return the node2
	 */
	public Node getTo() {
		return to;
	}

	/**
	 * @param to the node2 to set
	 */
	public void setTo(Node to) {
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
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public Edge merge(Edge e){
		//TODO: add impl for merge
		return null;
	}
	
	public Edge inLine(Edge e){
		//TODO: add impl. for inLine
		return null;
	}



	public abstract PathNode match(GraphNode gNode, PathNode pNode) ;
}
