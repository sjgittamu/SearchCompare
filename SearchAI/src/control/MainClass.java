package control;

import java.util.List;
import java.util.Map;

import searches.BFSearchDS;
import searches.DFSearchDS;
import searches.GBFSearchDS;
import searches.MainSearch;
import searches.SearchDS;
import utility.DataUtil;
import utility.Edge;
import utility.Node;
import utility.Vertex;

public class MainClass {
	public static void main(String[] args) {
		//FileRead and //Convert to Graph
		DataUtil file = new DataUtil();
		//command line args
		String searchAlgo = args[0];
		SearchDS frontier = null;
		if(searchAlgo.equalsIgnoreCase("BFS_nav")){
			frontier = new BFSearchDS();
			searchAlgo = "BFS";
		}else if(searchAlgo.equalsIgnoreCase("DFS_nav")){
			frontier = new DFSearchDS();
			searchAlgo = "DFS";
		}else if(searchAlgo.equalsIgnoreCase("GBFS_nav")){
			frontier = new GBFSearchDS();
			searchAlgo = "GBFS";
		}
		
		String filename = args[1];
		//Input from file
		Map<Node, List<Edge>> graph= file.getGraphFromFile(filename);
		Node.graph = graph;

		//Source and destination 
		int sourceX = Integer.valueOf(args[2]);
		int sourceY = Integer.valueOf(args[3]);
		int destiX = Integer.valueOf(args[4]);
		int destiY = Integer.valueOf(args[5]);
		int sourceVertexId =0;
		int destVertexId =0;
		for (Vertex v: file.vertexIdVertexMapping.values()) {
			if(v.getxCoor() == sourceX && v.getyCoor() == sourceY){
				sourceVertexId = v.getId();
			}
			if(v.getxCoor() == destiX && v.getyCoor() == destiY){
				destVertexId = v.getId();
			}
		}
		System.out.println("start=("+sourceX+","+sourceY+"), goal=("+destiX+","+destiY+"), vertices: "+sourceVertexId+" and "+ destVertexId);
		
		//take arguments source and destination
		MainSearch searchMain = new MainSearch();
		Node start = new Node(sourceVertexId);
		Vertex end = new Vertex(destVertexId,destiX,destiY);
		//Search algo call
		List<Node> path = searchMain.search(start, end, frontier,file.getVertexCount());
		System.out.println("=====================");
		System.out.println("solution path:");
		for (int i=path.size()-1; i>=0; i--) {
			Node node = path.get(i);
			Vertex v = DataUtil.vertexIdVertexMapping.get(node.getVertexId());
			System.out.println("vertex "+v.getId()+" ("+v.getxCoor()+","+ v.getyCoor()+")");
			//System.out.println(v.getxCoor()+" "+ v.getyCoor());
		}
		System.out.println();
		System.out.println("search algorithm= "+searchAlgo);
		System.out.println("total iterations= "+searchMain.getIter());
		System.out.println("max frontier size= "+ searchMain.getMaxFrontierSize());
		System.out.println("Vertices visited= "+ searchMain.getVistedVertices()+"/"+file.getVertexCount());
		System.out.println("path length= "+Integer.valueOf(path.size()-1));	//subtract one because this is the number of vertices, for path number shud be less by one
		
		
		
	}
}
