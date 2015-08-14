package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class MyGraph extends DirectedSparseGraph<MyArgument, MyAttack> implements IMyGraph {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  DirectedSparseGraph<MyArgument, MyAttack> myGraph = new DirectedSparseGraph<MyArgument, MyAttack>();
	int vertexCount;
	int edgeCount;
	CircleLayout<MyArgument, MyAttack> layout;
	VisualizationViewer<MyArgument, MyAttack> vv;
	
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
	
	public Collection<MyArgument> getMyVertices(){
		return myGraph.getVertices();
	}
	
	public Collection<MyAttack> getMyEdges(){
		return myGraph.getEdges();
	}
	public DirectedSparseGraph<MyArgument, MyAttack> getmygraph(){
		return myGraph;
	}
	
	@Override
	public MyArgument addMyVertex() {
		++vertexCount;
		MyArgument v = new MyArgument(vertexCount);
//		v.setId(vertexCount);
		myGraph.addVertex(v);
		return v;
	}
	
	public MyAttack addMyEdge(MyArgument v1, MyArgument v2){
		if(!(this.getMyVertices().contains(v1)) || !(this.getMyVertices().contains(v2))){
			throw new IllegalArgumentException("Vertices not in graph");
		}
				
		if(v1==v2){
			throw new IllegalArgumentException("The to and from vertices must be distinct");
		}
	//create new edge
		    ++edgeCount;
			MyAttack e1 = new MyAttack(edgeCount);
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
		HashSet<MyArgument> vertices = new HashSet<MyArgument>(myGraph.getVertices());
		HashSet<MyAttack> edges = new HashSet<MyAttack>(myGraph.getEdges());
		String verticesToString = "Arguments: "+vertices.toString();
		String edgesToString = "Attacks: "+edges.toString();
		String y =  verticesToString+ "\n" + edgesToString+"\n" +"Arguments Count: " +vertexCount + "\n" +"Attacks Count: " +edgeCount;
		return y;
		}

	public DirectedSparseGraph<MyArgument, MyAttack> getGraph(){
		return myGraph;
	}
	
	public CircleLayout<MyArgument, MyAttack> getGraphLayout(){
		layout = new CircleLayout<MyArgument,MyAttack>(myGraph);
		return layout;
	}
	public void setGraphLayout(CircleLayout<MyArgument, MyAttack> layout){
		this.layout=layout;
	}
	public void setGraphVisualizationViewer(VisualizationViewer<MyArgument, MyAttack> vv){
		this.vv=vv;
	}
	
	public VisualizationViewer<MyArgument, MyAttack> getGraphVisualizationViewer(Layout<MyArgument, MyAttack> layout){

        vv = new VisualizationViewer<MyArgument, MyAttack>(layout);
        return vv;
	}
	
	public HashSet<MyArgument> getNoneLabelledVertices(){
		HashSet<MyArgument> noneLabelledVertices = new HashSet<MyArgument>();
		HashSet<MyArgument> listVertices = new HashSet<MyArgument>(this.getMyVertices());
		Iterator<MyArgument> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyArgument currentVertex = vertexIterator.next();
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
	
	public HashSet<MyArgument> getInLabelledVertices(){
		HashSet<MyArgument> inLabelledVertices = new HashSet<MyArgument>();
		HashSet<MyArgument> listVertices = new HashSet<MyArgument>(this.getMyVertices());
		Iterator<MyArgument> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyArgument currentVertex = vertexIterator.next();
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
	
	
	public HashSet<MyArgument> getOutLabelledVertices(){
		HashSet<MyArgument> outLabelledVertices = new HashSet<MyArgument>();
		HashSet<MyArgument> listVertices = new HashSet<MyArgument>(this.getMyVertices());
		Iterator<MyArgument> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyArgument currentVertex = vertexIterator.next();
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
	
	public HashSet<MyArgument> getUndecLabelledVertices(){
		HashSet<MyArgument> undecLabelledVertices = new HashSet<MyArgument>();
		HashSet<MyArgument> listVertices = new HashSet<MyArgument>(this.getMyVertices());
		Iterator<MyArgument> vertexIterator = listVertices.iterator();
		while(vertexIterator.hasNext()){
			MyArgument currentVertex = vertexIterator.next();
			if(currentVertex.isUndec()){
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
		HashSet<MyArgument> vertices = new HashSet<MyArgument>(this.getMyVertices());
		Iterator<MyArgument> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			MyArgument currentVertex = verticesIterator.next();
			this.myGraph.removeVertex(currentVertex);
		}
		if(this.getVertexCount()==0){
			vertexCount=0;
			edgeCount=0;
			return true;
		}
		return false;
	}
	
	public boolean findMyVertex(MyArgument v){
		LinkedHashSet<MyArgument> vertices = new LinkedHashSet<MyArgument>(this.getMyVertices());
		Iterator<MyArgument> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			MyArgument currentvertex = verticesIterator.next();
			if(currentvertex.equals(v)){
				return true;
			}
		}
		return false;
	}
	
	public MyArgument getMyVertex(MyArgument v){
		LinkedHashSet<MyArgument> vertices = new LinkedHashSet<MyArgument>(this.getMyVertices());
		Iterator<MyArgument> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			MyArgument currentvertex = verticesIterator.next();
			if(currentvertex.equals(v)){
				return currentvertex;
			}
		}
		throw new IllegalArgumentException("Vertex not in graph");
		
	}
	
	public boolean findMyEdge(MyAttack e){
		LinkedHashSet<MyAttack> edges = new LinkedHashSet<MyAttack>(this.getMyEdges());
		Iterator<MyAttack> edgesIterator = edges.iterator();
		while(edgesIterator.hasNext()){
			MyAttack currentEdge= edgesIterator.next();
			if(currentEdge.equals(e)){
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAllVertices(Collection<MyArgument> collection){
		LinkedHashSet<MyArgument> set = new LinkedHashSet<MyArgument>(collection);
		Iterator<MyArgument> setIterator = set.iterator();
		// This counts the number of vertices in the collection that are the same as in the graph.
		int sameVertexCount=0;
		while(setIterator.hasNext()){
			MyArgument currentVertex = setIterator.next();
			if(this.findMyVertex(currentVertex)){
				sameVertexCount++;
			}
		}
	if(sameVertexCount==this.GetMyVertexCount()){
		return true;
	}
	return false;
	}
	
	public boolean containsAllEdges(Collection<MyAttack> collection){
		LinkedHashSet<MyAttack> set = new LinkedHashSet<MyAttack>(collection);
		Iterator<MyAttack> setIterator = set.iterator();
		int sameEdgeCount=0;
		
		while(setIterator.hasNext()){
			MyAttack currentEdge= setIterator.next();
			if(this.findMyEdge(currentEdge)){
				sameEdgeCount++;
			}
		}
		
		if(sameEdgeCount==this.GetMyEdgeCount()){
			return true;
		}
		
		return false;
	}
	
	public boolean equals(Object other) {
	    if (other == this) return true;
	    if (other == null) return false;
	    if (getClass() != other.getClass()) return false;
	    MyGraph graph = (MyGraph)other;
	    return (graph.containsAllVertices(this.getMyVertices()) && graph.containsAllEdges(this.getMyEdges()) && this.GetMyVertexCount()== graph.GetMyVertexCount() && this.GetMyEdgeCount() == graph.GetMyEdgeCount() );
	  }
}
