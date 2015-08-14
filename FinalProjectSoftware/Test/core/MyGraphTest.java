package core;

import static org.junit.Assert.*;
import org.junit.Test;
import core.MyAttack;
import core.MyGraph;
import core.MyArgument;
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
	MyArgument v1 = new MyArgument(1);
	g1.getmygraph().addVertex(v1);
	System.out.println("g1 " +g1.toString());
	Collection<MyArgument> c1 = g1.getMyVertices();
	assertTrue(c1.contains(v1));
	
}

@Test
public void testGetMyEdges(){
	MyGraph g2 = new MyGraph();
	MyArgument ve1=g2.addMyVertex();
	MyArgument ve2=g2.addMyVertex();
	MyAttack e2 = new MyAttack(0);
	System.out.println("g2 " + g2.toString());
	System.out.println(e2.getLabel());
	g2.getmygraph().addEdge(e2, ve1,ve2);
	System.out.println("g2 " + g2.toString());
	System.out.println(e2.getLabel());
	Collection<MyAttack> c2 = g2.getMyEdges();
	assertTrue(c2.contains(e2));
	}

@Test
public void testAddVertex(){
	MyGraph g3 = new MyGraph();
	MyArgument v1 =g3.addMyVertex();
	System.out.println("g3"+g3.toString());
	Collection<MyArgument> c3 = g3.getMyVertices();
	System.out.println("Size of Vertex collection is "+c3.size());
	assertTrue(c3.contains(v1));
}

@Test 
public void testAddEdge(){
	MyGraph g4 = new MyGraph();
	MyArgument v1 =g4.addMyVertex();
	MyArgument v2= g4.addMyVertex();
//	MyVertex v2 =new MyVertex(1);
	MyAttack e1 = g4.addMyEdge(v1, v2);
	System.out.println("g4 "+ g4.toString());
	Collection<MyAttack> c4 = g4.getMyEdges();
	System.out.println("Size of Edge collection is " + c4.size());
	assertTrue(c4.contains(e1));
}

@Test
public void testToString(){
	MyGraph g1= new MyGraph();
	HashSet<MyArgument> h = new HashSet<MyArgument>(g1.getVertices());
	HashSet<MyAttack> h1 = new HashSet<MyAttack>(g1.getEdges());
	String y = "Arguments: "+ h.toString()+ "\n"+"Attacks: "+ h1.toString() + "\n"+"Arguments Count: 0\n"+"Attacks Count: 0";
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
	MyArgument v1=g6.addMyVertex();
	MyArgument v2 =g6.addMyVertex();
	g6.addMyEdge(v1, v2);
	System.out.println("g6 as a graph "+g6.toString());
	Collection<MyArgument> c1 = g6.getmygraph().getPredecessors(v2);
	System.out.println(c1.size()+"");
	assertTrue(c1.contains(v1));
}

@Test 
public void testGetNoneLabelledVertices(){
	MyGraph g6= new MyGraph();
	MyArgument v1=g6.addMyVertex();
	MyArgument v2 =g6.addMyVertex();
	v2.setLabel("in");
	HashSet<MyArgument > h1 =g6.getNoneLabelledVertices();
	assertTrue(h1.contains(v1));
	assertFalse(h1.contains(v2));
}

@Test
public void testGetInLabelledVertices(){
	MyGraph g6= new MyGraph();
	MyArgument v1=g6.addMyVertex();
	MyArgument v2 =g6.addMyVertex();
	v2.setLabel("IN");
	HashSet<MyArgument > h1 =g6.getInLabelledVertices();
	assertTrue(h1.contains(v2));
	assertFalse(h1.contains(v1));
}

@Test
public void testGetOutLabelledVertices(){
	MyGraph g6= new MyGraph();
	MyArgument v1=g6.addMyVertex();
	MyArgument v2 =g6.addMyVertex();
	v2.setLabel("OUT");
	HashSet<MyArgument > h1 =g6.getOutLabelledVertices();
	assertTrue(h1.contains(v2));
	assertFalse(h1.contains(v1));
}

@Test
public void testGetUndecLabelledVertices(){
	MyGraph g6= new MyGraph();
	MyArgument v1=g6.addMyVertex();
	MyArgument v2 =g6.addMyVertex();
	v2.setLabel("UNDEC");
	HashSet<MyArgument > h1 =g6.getUndecLabelledVertices();
	assertTrue(h1.contains(v2));
	assertFalse(h1.contains(v1));
}

@Test
public void testResetGraph(){
	MyGraph g6= new MyGraph();
	MyArgument v1=g6.addMyVertex();
	MyArgument v2 =g6.addMyVertex();
	g6.addMyEdge(v1, v2);
	g6.resetGraph();
	assertTrue(g6.getMyVertices().isEmpty());
	assertTrue(g6.getMyEdges().isEmpty());
	assertTrue(g6.vertexCount==0);
	assertTrue(g6.edgeCount==0);
}

@Test 
public void tesFindMyVertex(){
	MyGraph g6= new MyGraph();
	MyArgument v1= g6.addMyVertex();
	MyArgument v2 = new MyArgument(0);
	assertTrue(g6.findMyVertex(v1));
	assertFalse(g6.findMyVertex(v2));
	
}

@Test 
public void testFindMyEdge(){
	MyGraph g6= new MyGraph();
	MyArgument v1=g6.addMyVertex();
	MyArgument v2 =g6.addMyVertex();
	MyAttack e = g6.addMyEdge(v1, v2);
	MyAttack e1 = new MyAttack(0);
	assertTrue(g6.findMyEdge(e));
	assertFalse(g6.findMyEdge(e1));
}

@Test 
public void testContainsAllVertices(){
	MyGraph g = new MyGraph();
	MyGraph g1 = new MyGraph();
	g1.addMyVertex();
	g.addMyVertex();
	g.addMyVertex();
	g1.addMyVertex();
	assertTrue(g.containsAllVertices(g1.getMyVertices()));
}

@Test 
public void testContainsAllEdges(){
	MyGraph g = new MyGraph();
	MyGraph g1 = new MyGraph();
	 MyArgument g1v= g1.addMyVertex();
	MyArgument gv= g.addMyVertex();
	MyArgument gv1= g.addMyVertex();
	MyArgument g1v1=g1.addMyVertex();
	g.addMyEdge(gv, gv1);
	g1.addMyEdge(g1v, g1v1);
	System.out.println("g to string" + g.toString()+"\n g1 to string: "+ g1.toString());
	assertTrue(g.containsAllEdges(g1.getMyEdges()));
}

@Test 
public void testEquals(){
	MyGraph g = new MyGraph();
	MyGraph g1 = new MyGraph();
	assertTrue(g.equals(g1));
	 MyArgument g1v= g1.addMyVertex();
	MyArgument gv= g.addMyVertex();
	System.out.println("g to string" + g.toString()+"g1 to string: "+ g1.toString());
	assertTrue(g.equals(g1));
	MyArgument gv1= g.addMyVertex();
	MyArgument g1v1=g1.addMyVertex();
	g.addMyEdge(gv, gv1);
	g1.addMyEdge(g1v, g1v1);
	System.out.println("g to string" + g.toString()+"\n g1 to string: "+ g1.toString());
	assertTrue(g.equals(g1));
}
}