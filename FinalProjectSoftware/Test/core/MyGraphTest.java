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
		g1.addMyVertex();
		g1.addMyVertex();
		assertEquals(g1.vertexCount,2);
		assertEquals(g1.edgeCount,0);
	}
	
//	@Test 
//	public void testGetMyVertexCount(){
//		MyGraph g =new MyGraph();
//		g.addMyVertex();
//		g.addMyVertex();
//		g.addMyVertex();
//		int y = g.vertexCount;
//		assertEquals(y, 3);
//	}
//	
//	@Test 
//	public void testGetMyEdgeCount(){
//		MyGraph g5 = new MyGraph();
//		MyVertex v1 =g5.addMyVertex();
//		MyVertex v2= g5.addMyVertex();
//		MyEdge e1 = g5.addMyEdge(v1, v2);
//		System.out.println("g5 is   \n" +g5.toString());
//		int number =g5.edgeCount;
//		int number1 = g5.getEdgeCount();
//		System.out.println("n1" +number);
//		System.out.println("n2" +number1);
//		assertEquals(number,1);
//		assertEquals(number1,1);
//	}
	
@Test
public void testGetMyVertices(){
	MyGraph g1 = new MyGraph();
	MyVertex v1 = new MyVertex(1);
	g1.getmygraph().addVertex(v1);
	System.out.println("g1 " +g1.toString());
	Collection<MyVertex> c1 = g1.getMyVertices();
	assertTrue(c1.contains(v1));
	
}

@Test
public void testGetMyEdges(){
	MyGraph g2 = new MyGraph();
	MyVertex ve1=g2.addMyVertex();
	MyVertex ve2=g2.addMyVertex();
	MyEdge e2 = new MyEdge(0);
	System.out.println("g2 " + g2.toString());
	System.out.println(e2.getLabel());
	g2.getmygraph().addEdge(e2, ve1,ve2);
	System.out.println("g2 " + g2.toString());
	System.out.println(e2.getLabel());
	Collection<MyEdge> c2 = g2.getMyEdges();
	assertTrue(c2.contains(e2));
	}

@Test
public void testAddVertex(){
	MyGraph g3 = new MyGraph();
	MyVertex v1 =g3.addMyVertex();
	System.out.println("g3"+g3.toString());
	Collection<MyVertex> c3 = g3.getMyVertices();
	System.out.println("Size of Vertex collection is "+c3.size());
	assertTrue(c3.contains(v1));
}

@Test 
public void testAddEdge(){
	MyGraph g4 = new MyGraph();
	MyVertex v1 =g4.addMyVertex();
	MyVertex v2= g4.addMyVertex();
//	MyVertex v2 =new MyVertex(1);
	MyEdge e1 = g4.addMyEdge(v1, v2);
	System.out.println("g4 "+ g4.toString());
	Collection<MyEdge> c4 = g4.getMyEdges();
	System.out.println("Size of Edge collection is " + c4.size());
	assertTrue(c4.contains(e1));
}

@Test
public void testToString(){
	MyGraph g1= new MyGraph();
	String y = "Vertices\n"+"Edges:\n"+"Vertex Count: 0\n"+"Edge Count: 0";
	assertEquals(g1.toString(),y);
	}

@Test
public void testGetgraph(){
MyGraph g1= new MyGraph();
assertEquals(g1.getGraph(),g1.myGraph);
}

@Test
public void testGetPredecessors(){
	MyGraph g6= new MyGraph();
	MyVertex v1=g6.addMyVertex();
	MyVertex v2 =g6.addMyVertex();
	MyEdge e1=g6.addMyEdge(v1, v2);
	System.out.println("g6 as a graph "+g6.toString());
	Collection<MyVertex> c1 = g6.getmygraph().getPredecessors(v2);
	System.out.println(c1.size()+"");
	assertTrue(c1.contains(v1));
}

@Test 
public void testGetNoneLabelledVertices(){
	MyGraph g6= new MyGraph();
	MyVertex v1=g6.addMyVertex();
	MyVertex v2 =g6.addMyVertex();
	v2.setLabel("in");
	HashSet<MyVertex > h1 =g6.getNoneLabelledVertices();
	assertTrue(h1.contains(v1));
	assertFalse(h1.contains(v2));
}

@Test
public void testGetInLabelledVertices(){
	MyGraph g6= new MyGraph();
	MyVertex v1=g6.addMyVertex();
	MyVertex v2 =g6.addMyVertex();
	v2.setLabel("IN");
	HashSet<MyVertex > h1 =g6.getInLabelledVertices();
	assertTrue(h1.contains(v2));
	assertFalse(h1.contains(v1));
}
}