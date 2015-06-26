package core;

import static org.junit.Assert.*;

import java.lang.reflect.Array;

import org.apache.commons.collections4.CollectionUtils;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import core.MyEdge;
import core.MyGraph;
import core.MyVertex;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

import java.util.*;

public class MyGraphTest {

	
	@Test
	public void testMyGraph(){
		MyGraph g1 = new MyGraph();
		assertEquals(g1.vertexCount,0);
		assertEquals(g1.edgeCount,0);
	}
	
	@Test 
	public void testGetMyVertexCount(){
		MyGraph g =new MyGraph();
		g.addMyVertex();
		g.addMyVertex();
		g.addMyVertex();
		int y = g.GetMyVertexCount();
		assertEquals(y, 3);
	}
	
	@Test 
	public void testGetMyEdgeCount(){
		fail("no yet implemented");
	}
//	
//	@Test
//	public void testGetMyEdges(){
//		//creates a new MyGraph object
//		MyGraph g1 = new MyGraph();
//		MyVertex v1= new MyVertex();
//		MyVertex v2= new MyVertex();
//		g1.addMyEdge(v1, v2);
//		Collection<MyEdge> c= g1.getMyEdges();
//		assertEquals(1,c.size());
////		assertEquals(g1.getMyEdges(),g1.myGraph.getEdges());
//		assertTrue(org.apache.commons.collections15.CollectionUtils.isEqualCollection(g1.getMyEdges(), g1.myGraph.getEdges()));
//	}
	
	
	@Test
	public void testGetMyVertices(){
		//creates a new MyGraph object
		MyGraph g1 = new MyGraph();
		g1.addMyVertex();
		Collection<MyVertex> c= g1.getMyVertices();
		assertEquals(1,c.size());
		assertTrue(org.apache.commons.collections15.CollectionUtils.isEqualCollection(g1.getMyVertices(), g1.myGraph.getVertices()));
	}
	

//	
	public static boolean equals(MyVertex[] a, MyVertex[] b) {
	    if (a.length != b.length)
	        return false;
	    for (int i = 0; i < a.length; i++)
	        if (a[i] != b[i])
	            return false;
	    return true;
	}

//
	@Test
	public void testAddVertex() {
		MyGraph g = new MyGraph();
		g.addMyVertex();
		System.out.println("The graph myGraph = " + g.myGraph.toString());
		MyVertex v1 = new MyVertex();
		v1.setId(1);
		DirectedSparseGraph<MyVertex, MyEdge> g1 = new DirectedSparseGraph<MyVertex, MyEdge>();
		g1.addVertex(v1);
		System.out.println("The graph g1 = " + g1.toString());
		Collection<MyVertex> a = g.getMyVertices();
		Collection<MyVertex> b = g1.getVertices();
		MyVertex list1[] = new MyVertex[a.size()];
		MyVertex list2[] = new MyVertex[b.size()];
		
		assertTrue(Arrays.equals(list1,list2));
		

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
	public void testToString(){
		MyGraph g1 = new MyGraph();
		String y = "Vertices\n"+"Edges:\n"+"Vertex Count: 0\n"+"Edge Count: 0";
		assertEquals(g1.toString(),y);
		}
	
	@Test
	public void testGetgraph(){
	MyGraph g1 = new MyGraph();
	assertEquals(g1.getGraph(),g1.myGraph);
	}
//	@Test
//	public void testDeleteEdge() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteVertex() {
//		fail("Not yet implemented");
//	}

}
