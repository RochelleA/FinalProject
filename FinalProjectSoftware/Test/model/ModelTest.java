package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import core.*;

import org.junit.Test;

public class ModelTest {
	
	@Test 
	public void TestAddEdge(){
		Model m = new Model();
		MyVertex v11 = m.addVertex();
		
		MyVertex v22 = m.addVertex();
		m.addEdge(v11);
		assertEquals(v11,m.v1);
		MyEdge e = m.addEdge(v22);
		assertTrue(m.ModelGraph.getMyEdges().contains(e));
		assertEquals(null,m.v2);
	}

	@Test
	public void TestAddvertex(){
		Model m= new Model();
		MyVertex v1=m.addVertex();
		assertTrue(m.ModelGraph.getMyVertices().contains(v1));

	}
	
	
	@Test
	public void testFindUnattackedVertices() {
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		@SuppressWarnings("unused")
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v3);
		ArrayList<MyVertex> a1 =m.findUnattackedVertices();
		assertTrue(a1.contains(v1));
		assertTrue(a1.contains(v2));
		assertFalse(a1.contains(v3));
		HashSet<MyVertex> a2 = m.findUnattackedVertices1();
		assertTrue(a2.contains(v1));
		assertTrue(a2.contains(v2));
		assertFalse(a2.contains(v3));
	}
	
	
	@Test
	public void testFindL1() {
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		MyLabelling L1= m.findL1();
		HashSet<MyVertex> HIn1 = L1.getInVertices();
		System.out.println("The In vertices are: "+ HIn1  +"The labelling is: "+L1.toString());
		assertTrue(HIn1.contains(v1));
		assertTrue(HIn1.contains(v2));
		assertTrue(HIn1.contains(v3));
		assertEquals(v3.getLabel(), "IN");
		assertTrue(m.NotLabelledVertices.isEmpty());
		@SuppressWarnings("unused")
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v3);
		MyLabelling L2 = m.findL1();
		HashSet<MyVertex> HIn2 = L2.getInVertices();
		System.out.println("The In vertices are: " + HIn2+"The labelling is: "+ L2.toString());
		assertTrue(HIn2.contains(v1));
		assertTrue(HIn2.contains(v2));
		assertFalse(HIn2.contains(v3));
		assertTrue(m.NotLabelledVertices.contains(v3));
		assertTrue(L2.getOutVertices().contains(v3));
		assertEquals(v3.getLabel(), "OUT");

	}

}
