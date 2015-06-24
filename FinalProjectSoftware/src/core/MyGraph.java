package core;

import java.util.Collection;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class MyGraph extends DirectedSparseGraph<MyVertex, MyEdge> implements IMyGraph {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DirectedSparseGraph<MyVertex, MyEdge> myGraph = new DirectedSparseGraph<MyVertex, MyEdge>();
	int vertexCount;

	 public MyGraph(){
		 myGraph = new DirectedSparseGraph<MyVertex, MyEdge>();
		 this.vertexCount=0;
	 }

	 
	@Override
	public boolean addMyVertex() {
	MyVertex v = new MyVertex();
	v.setId(++vertexCount);
	myGraph.addVertex(v);
	return true;
}
	
	public int GetMyVertexCount(){
		return vertexCount;
	}
	
	public Collection<MyVertex> getMyVertices(){
		return myGraph.getVertices();
	}
	
	public Collection<MyEdge> getMyEdges(){
		MyGraph vg = new MyGraph();
		return vg.myGraph.getEdges();
	}
		public boolean addMyEdge(MyVertex v1, MyVertex v2){
			
			//create new MyGraph object
			MyGraph g = new MyGraph();
			
			//create new edge
			MyEdge e1 = new MyEdge(v1,v2);
			
			//check whether the MyGraph g already contains an edge e in its DirectedSparseGraph called myGraph.
			if (g.myGraph.containsEdge(e1)){
				throw new IllegalArgumentException("Edge already exist");
			}
			else{
			g.myGraph.addEdge(e1, v1,v2, EdgeType.DIRECTED);
			}
			
			myGraph=g.myGraph;
			
			return true;
			
		}
		
		public String toString(){
			 return myGraph.toString();
		}

}




