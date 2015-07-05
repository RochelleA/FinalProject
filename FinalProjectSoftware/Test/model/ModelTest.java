package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import model.*;
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

}
