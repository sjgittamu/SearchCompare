package searches;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utility.DataUtil;
import utility.Node;
import utility.Vertex;

public class MainSearch2 {

	public List<Node> search(Node root, Vertex goal, SearchDS frontier){
		
		//initialize with starting point
		frontier.addElement(root);
		//explored set initialization
		Set<Node> exploredSet = new HashSet<Node>();
		Set<Node> discoverySet = new HashSet<Node>();
		//loop in
		List<Node> path= null;
		while(!frontier.isEmpty()){
			Node nextElement = frontier.popNextElement();
			Vertex output = DataUtil.vertexIdVertexMapping.get(nextElement.getVertexId());
			System.out.println(output.getxCoor()+" "+output.getyCoor());
			if(nextElement.getVertexId() == goal.getId()){
				path = nextElement.traceback(root);
				return path;
			}
			exploredSet.add(nextElement);
			Set<Node> successors = nextElement.successors(root,goal);
			for (Node node : successors) {
				if((!exploredSet.contains(node)) && (!frontier.elementExists(node)) && !discoverySet.contains(node)){ 
					frontier.addElement(node);
					discoverySet.add(node);
				}
			}
		}
		return null;
	}
}


/*//Stack<Node> frontier = null;
if(searchDS instanceof DFSearchDS){
	//frontier = (Stack<Node>)searchDS.getFrontier();
}
else if (searchDS instanceof BFSearchDS){
	
}
else if (searchDS instanceof GBFSearchDS){
	
}	*/
