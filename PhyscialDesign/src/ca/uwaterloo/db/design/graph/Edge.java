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
	
	private Node from;
	protected Node to;
	private int fromCardinality, toCardinality;
	private String name;
	public Edge(String name, Node from, Node to) {
		this.name = name;
		this.to= to;
		this.from = from;
	}
	public Node getFrom() {
		return from;
	}
	public void setFrom(Node from) {
		this.from = from;
	}
	public Node getTo() {
		return to;
	}
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

	public abstract Node match(PathEdge pEdge);
}
