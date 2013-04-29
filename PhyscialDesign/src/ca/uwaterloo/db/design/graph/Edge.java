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
	
	protected Node node1, node2;
	private int fromCardinality, toCardinality;
	protected String name;
	public Edge(String name, Node node1, Node node2) {
		this.name = name;
		
		setNode1(node1);
		setNode2(node2);
		
	}
	
	protected Edge(){
		
	}
	
	/**
	 * @return the node1
	 */
	public Node getNode1() {
		return node1;
	}

	/**
	 * @param node1 the node1 to set
	 */
	public void setNode1(Node node1) {
		this.node1 = node1;
		if (node1 != null)
			node1.addAdj(this);
	
	}

	/**
	 * @return the node2
	 */
	public Node getNode2() {
		return node2;
	}

	/**
	 * @param node2 the node2 to set
	 */
	public void setNode2(Node node2) {
		this.node2 = node2;
		if (node2 != null)
			node2.addAdj(this);
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
