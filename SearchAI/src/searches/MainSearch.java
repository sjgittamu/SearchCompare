package searches;

import java.util.List;
import java.util.Set;

import utility.DataUtil;
import utility.Node;
import utility.Vertex;

public class MainSearch {
	private int iter = 0;
	private int vertexCount;
	int[] color ;
	int maxFrontierSize =0;

	public List<Node> search(Node root, Vertex goal, SearchDS frontier, int vertexCount){
		color = new int[vertexCount];
		this.vertexCount = vertexCount;
		//initialize with starting point
		frontier.addElement(root);
		color[root.getVertexId()]=1;
		root.setHeurValue(root, goal);
		//explored set initialization
		//loop in
		List<Node> path= null;
		while(!frontier.isEmpty()){
			Node nextElement = frontier.popNextElement();
			nextElement.setDepth(nextElement.getParent() == null ? 0:nextElement.getParent().getDepth()+1);
			Vertex output = DataUtil.vertexIdVertexMapping.get(nextElement.getVertexId());
			System.out.println("iter="+iter+", frontier="+frontier.getSize()+", popped="+nextElement.getVertexId()+" ("+output.getxCoor()+","+output.getyCoor()+"), depth="+nextElement.getDepth()+" dist2goal="+nextElement.getHeur());
			
			//System.out.println(output.getId()/*output.getxCoor()+" "+output.getyCoor()*/);
			if(nextElement.getVertexId() == goal.getId()){
				path = nextElement.traceback(root);
				return path;
			}
			Set<Node> successors = nextElement.successors(root,goal);
			for (Node node : successors) {
				int ver = node.getVertexId();
				if(color[ver] == 0){
					color[ver] =1;
					DataUtil.vertexIdNodeMapping.get(node.getVertexId()).setParent(nextElement);
					frontier.addElement(node);
					Vertex v = DataUtil.vertexIdVertexMapping.get(node.getVertexId());
					System.out.println("pushed "+node.getVertexId()+" ("+v.getxCoor()+","+v.getyCoor()+")");
					if(maxFrontierSize < frontier.getSize()){
						maxFrontierSize = frontier.getSize();
					}
				}else{
					color[ver]=2;
				}
			}
			iter++;
		}
		return path;
	}
	
	public int getVistedVertices(){
		int count=0;
		for(int i=0;i<this.vertexCount; i++){
			if(color[i] == 0)
				count++;
		}
		return this.vertexCount-count;
	}
	public int getIter() {
		return iter;
	}
	
	public int getMaxFrontierSize() {
		return maxFrontierSize;
	}
	
}


