package core;

import java.util.Collection;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class MyGraph extends DirectedSparseGraph<MyVertex, MyEdge> implements IMyGraph {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static DirectedSparseGraph<MyVertex, MyEdge> myGraph = new DirectedSparseGraph<MyVertex, MyEdge>();
	int vertexCount;
	int edgeCount;
	CircleLayout<MyVertex, MyEdge> layout;
	VisualizationViewer<MyVertex, MyEdge> vv;
	
	public MyGraph(){
		this.vertexCount=0;
		this.edgeCount=0;
	 }

	
	public int GetMyVertexCount(){
		return vertexCount;
	}
	
	public int GetMyEdgeCount(){
		return edgeCount;
	}
	
	public Collection<MyVertex> getMyVertices(){
		return myGraph.getVertices();
	}
	
	public Collection<MyEdge> getMyEdges(){
		MyGraph vg = new MyGraph();
		return vg.getmygraph().getEdges();
	}
	public DirectedSparseGraph<MyVertex, MyEdge> getmygraph(){
		return myGraph;
	}
	
	@Override
	public boolean addMyVertex() {
		MyVertex v = new MyVertex();
		v.setId(++vertexCount);
		myGraph.addVertex(v);
		return true;
	}
	
	public boolean addMyEdge(MyVertex v1, MyVertex v2){
			
		//create new MyGraph object
		MyGraph g = new MyGraph();
			
		//create new edge
		MyEdge e1 = new MyEdge(v1,v2);
			
		//check whether the MyGraph g already contains an edge e in its DirectedSparseGraph called myGraph.
		if (g.getmygraph().containsEdge(e1)){
			throw new IllegalArgumentException("Edge already exist");
		}
		else{
			g.getmygraph().addEdge(e1, v1,v2, EdgeType.DIRECTED);
			++edgeCount;
		}
			
		myGraph=g.getmygraph();
			
		return true;
			
		}
		
	public String toString(){
		String y = myGraph.toString() + "\n" +"Vertex Count: " +vertexCount + "\n" +"Edge Count: " +edgeCount;
		return y;
		}

	public DirectedSparseGraph<MyVertex, MyEdge> getGraph(){
		return myGraph;
	}
	
	public CircleLayout<MyVertex, MyEdge> getGraphLayout(){
		MyGraph g1 = new MyGraph();
		layout = new CircleLayout<MyVertex,MyEdge>(g1.getmygraph());
		return layout;
	}
	public void setGraphLayout(CircleLayout<MyVertex, MyEdge> layout){
		this.layout=layout;
	}
	public void setGraphVisualizationViewer(VisualizationViewer<MyVertex, MyEdge> vv){
		this.vv=vv;
	}
	
	public VisualizationViewer<MyVertex, MyEdge> getGraphVisualizationViewer(Layout<MyVertex, MyEdge> layout){

        vv = new VisualizationViewer<MyVertex, MyEdge>(layout);
        return vv;
	}
	
	
}




