package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataUtil {
	public static Map<Node, List<Edge>> graph;
	public static Map<Integer,Vertex> vertexIdVertexMapping = new HashMap<Integer,Vertex>();
	public static Map<Integer, Node> vertexIdNodeMapping = new HashMap<Integer, Node>();
	int vertexCount ;
	
	public int getVertexCount() {
		return vertexCount;
	}
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
	public DataUtil() {
		graph = new HashMap<Node, List<Edge>>();
	}
	public Map<Node, List<Edge>> getGraphFromFile(String filename){
		Scanner scanner = null;
		
		try { //make the vertex and edge list
			scanner = new Scanner(new File(filename));
			scanner.next();
			
			vertexCount = scanner.nextInt();
			for(int i=0; i<vertexCount; i++){
				Vertex vertex = new Vertex(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
				vertexIdVertexMapping.put(vertex.getId(),vertex);
			}
			scanner.next();
			List<Edge> edges = new LinkedList<Edge>();
			int edgeCount = scanner.nextInt();
			for(int i=0; i<edgeCount; i++){
				Edge edge= new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
				edges.add(edge);
			}
			System.out.println("vertices: "+vertexCount+", edges: "+edgeCount);
			graph = createGraph(edges);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			scanner.close();
		}
		return graph;
	}
	
	private Map<Node, List<Edge>> createGraph(List<Edge> edges){
		Map<Node, List<Edge>> graph = new HashMap<Node, List<Edge>>();
		
		for (Edge edge : edges) {
			graphVertexOccurance(edge.getV1(), edge, graph);
			graphVertexOccurance(edge.getV2(), edge, graph);
		}
		return graph;
	}
	
	private void graphVertexOccurance(int vertex,Edge edge, Map<Node, List<Edge>> graph){
		Node temp = new Node(vertex);
		vertexIdNodeMapping.put(vertex, temp);
		if(graph.containsKey(temp)){
			graph.get(temp).add(edge);
		}else{
			List<Edge> list1 = new LinkedList<Edge>();
			list1.add(edge);
			graph.put(temp, list1);
		}
	}
}



