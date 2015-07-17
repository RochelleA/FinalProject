package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

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

	
	public int GetMyVertexCount(){
		return vertexCount;
	}
	public void setMyVertexCount(int integer){
		vertexCount=integer;
		
	}
	
	public int GetMyEdgeCount(){
		return this.edgeCount;
	}
	public void setMyEdgeCount(int Integer){
		this.edgeCount=Integer;
	}
	
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
		if(!(this.getMyVertices().contains(v1)) || !(this.getMyVertices().contains(v2))){
			throw new IllegalArgumentException("Vertices not in graph");
		}
				
		if(v1==v2){
			throw new IllegalArgumentException("The to and from vertices must be distinct");
		}
	//create new edge
		    ++edgeCount;
			MyEdge e1 = new MyEdge(edgeCount);
			e1.setFrom(v1);
			e1.setTo(v2);
			e1.setLabel(v1, v2);
		//check whether the MyGraph g already contains an edge e in its DirectedSparseGraph called myGraph.
		if (myGraph.containsEdge(e1)){
//			--edgeCount;
			throw new IllegalArgumentException("Edge already exist");
		}
		else if (myGraph.addEdge(e1, v1,v2, EdgeType.DIRECTED)){
			System.out.println("edge added");
			return e1;
		}
		else{
			System.out.println("edge not added");
			throw new IllegalArgumentException("Vertices not in graph");
		
		}
			
		}
		
	public String toString(){
		HashSet<MyVertex> vertices = new HashSet<MyVertex>(myGraph.getVertices());
		HashSet<MyEdge> edges = new HashSet<MyEdge>(myGraph.getEdges());
		String verticesToString = "Arguments: "+vertices.toString();
		String edgesToString = "Attacks: "+edges.toString();
		String y =  verticesToString+ "\n" + edgesToString+"\n" +"Arguments Count: " +vertexCount + "\n" +"Attacks Count: " +edgeCount;
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
	
	public HashSet<MyVertex> getNoneLabelledVertices(){
		HashSet<MyVertex> noneLabelledVertices = new HashSet<MyVertex>();
		HashSet<MyVertex> listVertices = new HashSet<MyVertex>(this.getMyVertices());
		Iterator<MyVertex> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(currentVertex.hasNoLabel()){
				noneLabelledVertices.add(currentVertex);
			}
			else{
				System.out.println(currentVertex.toString() + "has a label" + currentVertex.getLabel());
			}
		}
			System.out.print("Vertices Labelled none are "+noneLabelledVertices);
			return noneLabelledVertices;
		
	}
	
	public HashSet<MyVertex> getInLabelledVertices(){
		HashSet<MyVertex> inLabelledVertices = new HashSet<MyVertex>();
		HashSet<MyVertex> listVertices = new HashSet<MyVertex>(this.getMyVertices());
		Iterator<MyVertex> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(currentVertex.isIn()){
				inLabelledVertices.add(currentVertex);
			}
			else{
				System.out.println(currentVertex.toString() + "has a label" + currentVertex.getLabel());
			}
		}
			System.out.print("Vertices Labelled in are "+inLabelledVertices);
			return inLabelledVertices;
		
	}
	
	
	public HashSet<MyVertex> getOutLabelledVertices(){
		HashSet<MyVertex> outLabelledVertices = new HashSet<MyVertex>();
		HashSet<MyVertex> listVertices = new HashSet<MyVertex>(this.getMyVertices());
		Iterator<MyVertex> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(currentVertex.isOut()){
				outLabelledVertices.add(currentVertex);
			}
			else{
				System.out.print("Vertices Labelled out are "+outLabelledVertices);
				System.out.println(currentVertex.toString() + "has a label" + currentVertex.getLabel());
			}
		}
			return outLabelledVertices;
		
	}
	
	public HashSet<MyVertex> getUndecLabelledVertices(){
		HashSet<MyVertex> undecLabelledVertices = new HashSet<MyVertex>();
		HashSet<MyVertex> listVertices = new HashSet<MyVertex>(this.getMyVertices());
		Iterator<MyVertex> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(currentVertex.isIn()){
				undecLabelledVertices.add(currentVertex);
			}
			else{
				System.out.print("Vertices Labelled undec are "+undecLabelledVertices);
				System.out.println(currentVertex.toString() + "has a label" + currentVertex.getLabel());
			}
		}
			return undecLabelledVertices;
		
	}

	public boolean resetGraph(){
		HashSet<MyVertex> vertices = new HashSet<MyVertex>(this.getMyVertices());
		Iterator<MyVertex> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			MyVertex currentVertex = verticesIterator.next();
			this.myGraph.removeVertex(currentVertex);
		}
		if(this.getVertexCount()==0){
			vertexCount=0;
			edgeCount=0;
			return true;
		}
		return false;
	}
}
