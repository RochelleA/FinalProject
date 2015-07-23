package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import core.*;

import org.junit.Test;

public class ModelTest {
	
	@SuppressWarnings("unused")
	@Test
	public void TestIsIllegallyIn(){
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		MyVertex v4= m.ModelGraph.addMyVertex();
		MyVertex v5= m.ModelGraph.addMyVertex();
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.ModelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.ModelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.ModelGraph.addMyEdge(v2, v5);
		m.GroundedLabelling();
		System.out.println(""+v2.getLabel());
		v2.setLabel("IN");
		System.out.println(""+v2.getLabel());
		assertFalse(m.isLegallyIn(v2));
	}

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
		assertTrue(L1.getNotLabelledVertices().isEmpty());
		@SuppressWarnings("unused")
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v3);
		MyLabelling L2 = m.findL1();
		HashSet<MyVertex> HIn2 = L2.getInVertices();
		System.out.println("The In vertices are: " + HIn2+"The labelling is: "+ L2.toString()+ "The not labelled vertices are: "+ L2.getNotLabelledVertices());
		assertTrue(HIn2.contains(v1));
		assertTrue(HIn2.contains(v2));
		assertFalse(HIn2.contains(v3));
		assertTrue(L2.getNotLabelledVertices().isEmpty());
		assertTrue(L2.getOutVertices().contains(v3));
		assertEquals(v3.getLabel(), "OUT");

	}
	
	@SuppressWarnings("unused")
	@Test 
	public void TestGroundedLabelling(){
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		MyVertex v4= m.ModelGraph.addMyVertex();
		MyVertex v5= m.ModelGraph.addMyVertex();
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.ModelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.ModelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.ModelGraph.addMyEdge(v2, v5);
		MyLabelling l1 =m.GroundedLabelling();
		System.out.println("not lablled vertices" + l1.getNotLabelledVertices());
		assertTrue(l1.getNotLabelledVertices().isEmpty());
		assertTrue(l1.getInVertices().contains(v1));
		System.out.print(v1.toString()+v1.getLabel());
		assertTrue(l1.getInVertices().contains(v3));
		assertTrue(l1.getInVertices().contains(v4));
		assertTrue(l1.getInVertices().contains(v5));
		assertTrue(l1.getOutVertices().contains(v2));
		System.out.println("Grounded Labelling is: " + l1.toString()+ "\n Vertices not labelled anymore");


		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void TestGetGroundedLabelling(){
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		MyVertex v4= m.ModelGraph.addMyVertex();
		MyVertex v5= m.ModelGraph.addMyVertex();
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.ModelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.ModelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.ModelGraph.addMyEdge(v2, v5);
		
		
		HashSet<MyVertex> h1= m.getGroundedExtension();
		
		
	}
	
	@SuppressWarnings("unused")
	@Test 
	public void TestIsIllegallyOut(){
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		MyVertex v4= m.ModelGraph.addMyVertex();
		MyVertex v5= m.ModelGraph.addMyVertex();
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.ModelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.ModelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.ModelGraph.addMyEdge(v2, v5);
		m.GroundedLabelling();
		System.out.println(""+v3.getLabel());
		v3.setLabel("OUT");
		System.out.println(""+v3.getLabel());
		assertFalse(m.isLegallyOut(v3));
	}
	
	@SuppressWarnings("unused")
	@Test 
	public void TestIsIlegallyUndec(){
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		MyVertex v4= m.ModelGraph.addMyVertex();
		MyVertex v5= m.ModelGraph.addMyVertex();
		MyEdge e1 =m.ModelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.ModelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.ModelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.ModelGraph.addMyEdge(v2, v5);
		m.GroundedLabelling();
		System.out.println(""+v3.getLabel());
		v3.setLabel("UNDEC");
		System.out.println(""+v3.getLabel());
		assertFalse(m.isLegallyUndec(v3));
	}
	
	@Test
	public void TestAdmissibleLabelling(){
		Model m = new Model();
		MyVertex v1 =m.ModelGraph.addMyVertex();
		MyVertex v2= m.ModelGraph.addMyVertex();
		MyVertex v3=  m.ModelGraph.addMyVertex();
		MyVertex v4 = m.ModelGraph.addMyVertex();
		m.ModelGraph.addMyEdge(v4, v3);
		m.ModelGraph.addMyEdge(v2, v1);
		m.ModelGraph.addMyEdge(v3, v2);
		MyLabelling l1=m.AdmissibleLabelling();
		System.out.println("Here now" + l1.DisplayLabelling());
		HashSet<MyVertex> inTemp = new HashSet<MyVertex>(l1.getInVertices());
		Iterator<MyVertex> inIterator = inTemp.iterator();
		while(inIterator.hasNext()){
			MyVertex current = inIterator.next();
			assertTrue(m.isLegallyIn(current));
		}
		HashSet<MyVertex> outTemp = new HashSet<MyVertex>(l1.getOutVertices());
		Iterator<MyVertex> outIterator = outTemp.iterator();
		while(outIterator.hasNext()){
			MyVertex current = outIterator.next();
			assertTrue(m.isLegallyOut(current));
		}
		
	}
	
	@Test
	public void TestReorderSet(){
		Model m = new Model();
		MyVertex v1 =m.ModelGraph.addMyVertex();
		MyVertex v2= m.ModelGraph.addMyVertex();
		MyVertex v3=  m.ModelGraph.addMyVertex();
		MyVertex v4 = m.ModelGraph.addMyVertex();
		m.ModelGraph.addMyEdge(v4, v3);
		m.ModelGraph.addMyEdge(v2, v1);
		m.ModelGraph.addMyEdge(v3, v2);
		LinkedHashSet<MyVertex> vertices =new LinkedHashSet<MyVertex>(m.ModelGraph.getMyVertices());
		MyVertex[] array1 = new MyVertex[vertices.size()];
		vertices.toArray(array1);
		LinkedHashSet<MyVertex> verticesReoder=m.reorderSet(vertices);
		System.out.println("vertices reordered"+verticesReoder.toString()+ vertices.size());
		MyVertex[] array2= new MyVertex[verticesReoder.size()];
		verticesReoder.toArray(array2);
		System.out.println("array 2"+array2.toString() );
		System.out.println("array1 element 1 "+ array1[0] + array1[1] + array1[2] + array1[3] );
		System.out.println("array2 element 3"+ array2[0] + array2[1] + array2[2] + array2[3]);
		assertEquals(array1[0], array2[3]);
	}
	
	@Test 
	public void TestAllAdmissibleLabellings(){
		Model m = new Model();
		MyVertex v1= m.ModelGraph.addMyVertex();
		MyVertex v2=m.ModelGraph.addMyVertex();
		MyVertex v3= m.ModelGraph.addMyVertex();
		MyVertex v4= m.ModelGraph.addMyVertex();
		m.ModelGraph.addMyEdge(v1, v2);
		m.ModelGraph.addMyEdge(v2, v1);
		m.ModelGraph.addMyEdge(v1, v3);
		m.ModelGraph.addMyEdge(v2, v3);
		m.ModelGraph.addMyEdge(v3, v4);
		System.out.println(m.allAdmissibleLabellings());
	}
}
