package core;

import static org.junit.Assert.*;
import org.junit.Test;
import core.MyAtt;
import core.MyGraph;
import core.MyArg;
import java.util.*;

public class MyGraphTest {
	

	@Test
	public void testMyGraph(){
		MyGraph g1 = new MyGraph();
		g1.addMyArg();
		g1.addMyArg();
		assertEquals(g1.ArgCount,2);
		assertEquals(g1.attCount,0);
	}
	
//	@Test 
//	public void testGetMyArgCount(){
//		MyGraph g =new MyGraph();
//		g.addMyArg();
//		g.addMyArg();
//		g.addMyArg();
//		int y = g.ArgCount;
//		assertEquals(y, 3);
//	}
//	
//	@Test 
//	public void testGetMyAttCount(){
//		MyGraph g5 = new MyGraph();
//		MyArg v1 =g5.addMyArg();
//		MyArg v2= g5.addMyArg();
//		MyAtt e1 = g5.addMyAtt(v1, v2);
//		System.out.println("g5 is   \n" +g5.toString());
//		int number =g5.AttCount;
//		int number1 = g5.getAttCount();
//		System.out.println("n1" +number);
//		System.out.println("n2" +number1);
//		assertEquals(number,1);
//		assertEquals(number1,1);
//	}
	
@Test
public void testGetMyArgs(){
	MyGraph g1 = new MyGraph();
	MyArg v1 = new MyArg(1);
	g1.getmygraph().addVertex(v1);
	System.out.println("g1 " +g1.toString());
	Collection<MyArg> c1 = g1.getMyArgs();
	assertTrue(c1.contains(v1));
	
}

@Test
public void testGetMyAtts(){
	MyGraph g2 = new MyGraph();
	MyArg ve1=g2.addMyArg();
	MyArg ve2=g2.addMyArg();
	MyAtt e2 = new MyAtt(0);
	System.out.println("g2 " + g2.toString());
	System.out.println(e2.getLabel());
	g2.getmygraph().addEdge(e2, ve1,ve2);
	System.out.println("g2 " + g2.toString());
	System.out.println(e2.getLabel());
	Collection<MyAtt> c2 = g2.getMyAtts();
	assertTrue(c2.contains(e2));
	}

@Test
public void testAddArg(){
	MyGraph g3 = new MyGraph();
	MyArg v1 =g3.addMyArg();
	System.out.println("g3"+g3.toString());
	Collection<MyArg> c3 = g3.getMyArgs();
	System.out.println("Size of Arg collection is "+c3.size());
	assertTrue(c3.contains(v1));
}

@Test 
public void testAddAtt(){
	MyGraph g4 = new MyGraph();
	MyArg v1 =g4.addMyArg();
	MyArg v2= g4.addMyArg();
//	MyArg v2 =new MyArg(1);
	MyAtt e1 = g4.addMyAtt(v1, v2);
	System.out.println("g4 "+ g4.toString());
	Collection<MyAtt> c4 = g4.getMyAtts();
	System.out.println("Size of Att collection is " + c4.size());
	assertTrue(c4.contains(e1));
}

@Test
public void testToString(){
	MyGraph g1= new MyGraph();
	HashSet<MyArg> h = new HashSet<MyArg>(g1.getVertices());
	HashSet<MyAtt> h1 = new HashSet<MyAtt>(g1.getEdges());
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
	MyArg v1=g6.addMyArg();
	MyArg v2 =g6.addMyArg();
	g6.addMyAtt(v1, v2);
	System.out.println("g6 as a graph "+g6.toString());
	Collection<MyArg> c1 = g6.getmygraph().getPredecessors(v2);
	System.out.println(c1.size()+"");
	assertTrue(c1.contains(v1));
}

@Test 
public void testGetNoneLabelledArgs(){
	MyGraph g6= new MyGraph();
	MyArg v1=g6.addMyArg();
	MyArg v2 =g6.addMyArg();
	v2.setLabel("in");
	HashSet<MyArg > h1 =g6.getNoneLabelledArgs();
	assertTrue(h1.contains(v1));
	assertFalse(h1.contains(v2));
}

@Test
public void testGetInLabelledArgs(){
	MyGraph g6= new MyGraph();
	MyArg v1=g6.addMyArg();
	MyArg v2 =g6.addMyArg();
	v2.setLabel("IN");
	HashSet<MyArg > h1 =g6.getInLabelledArgs();
	assertTrue(h1.contains(v2));
	assertFalse(h1.contains(v1));
}

@Test
public void testGetOutLabelledArgs(){
	MyGraph g6= new MyGraph();
	MyArg v1=g6.addMyArg();
	MyArg v2 =g6.addMyArg();
	v2.setLabel("OUT");
	HashSet<MyArg > h1 =g6.getOutLabelledArgs();
	assertTrue(h1.contains(v2));
	assertFalse(h1.contains(v1));
}

@Test
public void testGetUndecLabelledArgs(){
	MyGraph g6= new MyGraph();
	MyArg v1=g6.addMyArg();
	MyArg v2 =g6.addMyArg();
	v2.setLabel("UNDEC");
	HashSet<MyArg > h1 =g6.getUndecLabelledArgs();
	assertTrue(h1.contains(v2));
	assertFalse(h1.contains(v1));
}

@Test
public void testResetGraph(){
	MyGraph g6= new MyGraph();
	MyArg v1=g6.addMyArg();
	MyArg v2 =g6.addMyArg();
	g6.addMyAtt(v1, v2);
	g6.resetGraph();
	assertTrue(g6.getMyArgs().isEmpty());
	assertTrue(g6.getMyAtts().isEmpty());
	assertTrue(g6.ArgCount==0);
	assertTrue(g6.attCount==0);
}

@Test 
public void tesFindMyArg(){
	MyGraph g6= new MyGraph();
	MyArg v1= g6.addMyArg();
	MyArg v2 = new MyArg(0);
	assertTrue(g6.findMyArg(v1));
	assertFalse(g6.findMyArg(v2));
	
}

@Test 
public void testFindMyAtt(){
	MyGraph g6= new MyGraph();
	MyArg v1=g6.addMyArg();
	MyArg v2 =g6.addMyArg();
	MyAtt e = g6.addMyAtt(v1, v2);
	MyAtt e1 = new MyAtt(0);
	assertTrue(g6.findMyAtt(e));
	assertFalse(g6.findMyAtt(e1));
}

@Test 
public void testContainsAllArgs(){
	MyGraph g = new MyGraph();
	MyGraph g1 = new MyGraph();
	g1.addMyArg();
	g.addMyArg();
	g.addMyArg();
	g1.addMyArg();
	assertTrue(g.containsAllArgs(g1.getMyArgs()));
}

@Test 
public void testContainsAllAtts(){
	MyGraph g = new MyGraph();
	MyGraph g1 = new MyGraph();
	 MyArg g1v= g1.addMyArg();
	MyArg gv= g.addMyArg();
	MyArg gv1= g.addMyArg();
	MyArg g1v1=g1.addMyArg();
	g.addMyAtt(gv, gv1);
	g1.addMyAtt(g1v, g1v1);
	System.out.println("g to string" + g.toString()+"\n g1 to string: "+ g1.toString());
	assertTrue(g.containsAllAtts(g1.getMyAtts()));
}

@Test 
public void testEquals(){
	MyGraph g = new MyGraph();
	MyGraph g1 = new MyGraph();
	assertTrue(g.equals(g1));
	 MyArg g1v= g1.addMyArg();
	MyArg gv= g.addMyArg();
	System.out.println("g to string" + g.toString()+"g1 to string: "+ g1.toString());
	assertTrue(g.equals(g1));
	MyArg gv1= g.addMyArg();
	MyArg g1v1=g1.addMyArg();
	g.addMyAtt(gv, gv1);
	g1.addMyAtt(g1v, g1v1);
	System.out.println("g to string" + g.toString()+"\n g1 to string: "+ g1.toString());
	assertTrue(g.equals(g1));
}
}