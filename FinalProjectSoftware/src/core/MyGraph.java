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
	public  DirectedSparseGraph<MyVertex, MyEdge> myGraph = new DirectedSparseGraph<MyVertex, MyEdge>();
	int vertexCount;
	int edgeCount;
	CircleLayout<MyVertex, MyEdge> layout;
	VisualizationViewer<MyVertex, MyEdge> vv;
	
	public MyGraph(){
		this.vertexCount=0;
		this.edgeCount=0;
	 }

//	
//	public int GetMyVertexCount(){
//		return vertexCount;
//	}
//	
//	public int GetMyEdgeCount(){
//		return this.edgeCount;
//	}
//	
	public Collection<MyVertex> getMyVertices(){
		return myGraph.getVertices();
	}
	
	public Collection<MyEdge> getMyEdges(){
		return myGraph.getEdges();
	}
	public DirectedSparseGraph<MyVertex, MyEdge> getmygraph(){
		return myGraph;
	}
	
	@Override
	public MyVertex addMyVertex() {
		++vertexCount;
		MyVertex v = new MyVertex(vertexCount);
//		v.setId(vertexCount);
		myGraph.addVertex(v);
		return v;
	}
	
	public MyEdge addMyEdge(MyVertex v1, MyVertex v2){
	
				
		if(v1==v2){
			throw new IllegalArgumentException("The to and from vertices must be distinct");
		}
		System.out.println("Current edge count " +edgeCount);
	//create new edge
		    ++edgeCount;
		    System.out.println("New edge count " +edgeCount);
			MyEdge e1 = new MyEdge(edgeCount);
			e1.setFrom(v1);
			e1.setTo(v2);
			e1.setLabel(v1, v2);
		//check whether the MyGraph g already contains an edge e in its DirectedSparseGraph called myGraph.
		if (myGraph.containsEdge(e1)){
//			--edgeCount;
			throw new IllegalArgumentException("Edge already exist");
		}
		else{
			myGraph.addEdge(e1, v1,v2, EdgeType.DIRECTED);
			System.out.println("this else is evaluated");
		return e1;
		}
			
		}
		
	public String toString(){
		String y = myGraph.toString() + "\n" +"Vertex Count: " +vertexCount + "\n" +"Edge Count: " +edgeCount;
		return y;
		}

	public DirectedSparseGraph<MyVertex, MyEdge> getGraph(){
		return myGraph;
	}
	
	public CircleLayout<MyVertex, MyEdge> getGraphLayout(){
		layout = new CircleLayout<MyVertex,MyEdge>(myGraph);
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
