package core;

import static org.junit.Assert.*;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import core.MyEdge;
import core.MyGraph;
import core.MyVertex;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class MyGraphTest {

	@Test
	public void testGetMyVertices(){
		//creates a new MyGraph object
		MyGraph gT1 = new MyGraph();
		/*Check whether the collection returned from
		 * get the vertices of myGraph is the same as the vertices returned from the MyGraph object.*/
		CollectionUtils.isEqualCollection(gT1.myGraph.getVertices(), gT1.getMyVertices());
	}
	
	
	@Test
	public void getMyEdges() {
		
		MyGraph gT2 = new MyGraph();
		
		CollectionUtils.isEqualCollection(gT2.myGraph.getEdges(), gT2.getMyEdges());
	}

	@Test
	public void testAddVertex() {
		MyGraph g = new MyGraph();
		g.addMyVertex();
		System.out.println("The graph myGraph = " + g.myGraph.toString());
		MyVertex v1 = new MyVertex();
		v1.setId(MyVertex.vertexCount-1);
		DirectedSparseGraph<MyVertex, MyEdge> g1 = new DirectedSparseGraph<MyVertex, MyEdge>();
		g1.addVertex(v1);
		System.out.println("The graph g1 = " + g1.toString());
		
		CollectionUtils.isEqualCollection(g.getMyVertices(),g1.getVertices());
	}

	@Test
	public void testAddEdge() {
	 MyGraph gT1 = new MyGraph();
	 MyVertex v1 = new MyVertex();
	 MyVertex v2 = new MyVertex();
	 gT1.addMyEdge(v1, v2);
	 System.out.println("The graph gT1.myGraph = " + gT1.myGraph.toString());
	  
	MyEdge e1 = new MyEdge(v1,v2);
	DirectedSparseGraph<MyVertex, MyEdge> g1 = new DirectedSparseGraph<MyVertex, MyEdge>();
	g1.addEdge(e1,v1,v2);
	System.out.println("The graph g1 = " + g1.toString());
	CollectionUtils.isEqualCollection(gT1.getMyEdges(), g1.getEdges());
	fail("Not yet implemented");
	}
	
	@Test
	public void testDeleteEdge() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteVertex() {
		fail("Not yet implemented");
	}

}
