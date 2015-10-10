package utility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Node implements Comparable<Node>{
	int vertexId;
	Node parent;
	int depth;
	double heur;
	public static Map<Node, List<Edge>> graph; 
	
	public Node(int vertexId) {
		super();
		this.vertexId = vertexId;
	}
	public Node(int vertexId, Node parent) {
		super();
		this.vertexId = vertexId;
		this.parent = parent;
	}
	
	public Set<Node> successors(Node root, Vertex destination){
		Set<Node> successors = new HashSet<Node>();
		List<Edge> edges = graph.get(this);
		for (Edge edge : edges) {
			int nextVertex = (edge.getV1() == vertexId) ? edge.getV2(): edge.getV1();
			Node nodeForNextVertex = DataUtil.vertexIdNodeMapping.get(nextVertex);
			if(!nodeForNextVertex.equals(root) && nodeForNextVertex.parent==null){
				//nodeForNextVertex.parent = this;
				setHeurValue(nodeForNextVertex, destination);
			}
			successors.add(nodeForNextVertex);
		}
		return successors;
	}
	/*public Set<Node> successors(Node root, Vertex destination){
		Set<Node> successors = new HashSet<Node>();
		List<Edge> edges = graph.get(this);
		for (Edge edge : edges) {
			int nextVertex = (edge.getV1() == vertexId) ? edge.getV2(): edge.getV1();
			Node nodeForNextVertex = DataUtil.vertexIdNodeMapping.get(nextVertex);
			if(!nodeForNextVertex.equals(root) && nodeForNextVertex.parent==null){
				nodeForNextVertex.parent = this;
				setHeurValue(nodeForNextVertex, destination);
			}
			successors.add(nodeForNextVertex);
		}
		return successors;
	}*/
	public void setHeurValue(Node node, Vertex destination) {
		Vertex current = DataUtil.vertexIdVertexMapping.get(node.getVertexId());
		double heur = Math.sqrt(Math.pow((current.getxCoor() - destination.getxCoor()),2)  +  Math.pow((current.getyCoor() - destination.getyCoor()),2));
		DecimalFormat newFormat = new DecimalFormat("###.#");
		heur=  Double.valueOf(newFormat.format(heur));
		node.setHeur(heur);
	}
	public List<Node> traceback(Node root){
		Node node = this;
		List<Node> path = new ArrayList<>();
		path.add(this);
		while(node != root){
			node = node.parent;
			path.add(node);
		}
		return path;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + vertexId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (vertexId != other.vertexId)
			return false;
		return true;
	}
	public int getVertexId() {
		return vertexId;
	}
	public void setVertexId(int vertexId) {
		this.vertexId = vertexId;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public double getHeur() {
		return heur;
	}
	public void setHeur(double heur) {
		this.heur = heur;
	}
	@Override
	public int compareTo(Node o) {
		if(o==null)
			return 0;
		if(this.getHeur() < o.getHeur())
			return -1;
		else
			return 1;
	}
}
