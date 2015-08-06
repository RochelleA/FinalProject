package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import core.*;

import org.junit.Test;

public class ModelTest {


	@Test
	public void testAddvertex(){
		Model m= new Model();
		MyVertex v1=m.addVertex();
		assertTrue(m.modelGraph.getMyVertices().contains(v1));

	}
	
	@Test 
	public void testDeleteVertex(){
		Model m= new Model();
		MyVertex v1=m.addVertex();
		m.deleteVertex(v1);
		assertTrue(!(m.modelGraph.getMyVertices().contains(v1)));	
	}
	
	@Test 
	public void testAddEdge(){
		Model m = new Model();
		MyVertex v11 = m.addVertex();
		
		MyVertex v22 = m.addVertex();
		m.addEdge(v11);
		assertEquals(v11,m.v1);
		MyEdge e = m.addEdge(v22);
		assertTrue(m.modelGraph.getMyEdges().contains(e));
		assertEquals(null,m.v2);
	}

	@Test
	public void testDeleteEdge(){
		Model m = new Model();
		MyVertex v11 = m.addVertex();
		
		MyVertex v22 = m.addVertex();
		m.addEdge(v11);
		assertEquals(v11,m.v1);
		MyEdge e = m.addEdge(v22);
		m.deleteEdge(e);
		assertTrue(!(m.modelGraph.getMyEdges().contains(e)));
		
	}
	@Test 
	public void testResetGraph(){
		Model model = new Model();
		MyVertex v1 =model.addVertex();
		MyVertex v2 = model.addVertex();
		model.addEdge(v1);
		model.addEdge(v2);
		assertTrue(!(model.modelGraph.getMyVertices().isEmpty()));
		assertTrue(!(model.modelGraph.getMyEdges().isEmpty()));
		model.resetMyGraph();
		assertTrue(model.modelGraph.getMyVertices().isEmpty());
		assertTrue(model.modelGraph.getMyEdges().isEmpty());
		assertTrue(model.modelGraph.GetMyVertexCount()==0 && model.modelGraph.GetMyEdgeCount()==0);
	}
	

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS
	
	
	
	@Test
	public void testFindUnattackedVerticesHashSet() {
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		@SuppressWarnings("unused")
		MyEdge e1 =m.modelGraph.addMyEdge(v1, v3);
		ArrayList<MyVertex> a1 =m.findUnattackedVerticesArray();
		assertTrue(a1.contains(v1));
		assertTrue(a1.contains(v2));
		assertFalse(a1.contains(v3));
		HashSet<MyVertex> a2 = m.findUnattackedVerticesHashSet();
		assertTrue(a2.contains(v1));
		assertTrue(a2.contains(v2));
		assertFalse(a2.contains(v3));
	}
	
	
	@Test
	public void testFindL1() {
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyLabelling L1= m.findL1();
		HashSet<MyVertex> HIn1 = L1.getInVertices();
		System.out.println("The In vertices are: "+ HIn1  +"The labelling is: "+L1.toString());
		assertTrue(HIn1.contains(v1));
		assertTrue(HIn1.contains(v2));
		assertTrue(HIn1.contains(v3));
		assertEquals(v3.getLabel(), "IN");
		assertTrue(L1.getNotLabelledVertices().isEmpty());
		@SuppressWarnings("unused")
		MyEdge e1 =m.modelGraph.addMyEdge(v1, v3);
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
	public void testGroundedLabelling(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		MyEdge e1 =m.modelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.modelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.modelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.modelGraph.addMyEdge(v2, v5);
		MyLabelling l1 =m.groundedLabelling();
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
	public void testGetGroundedLabelling(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		MyEdge e1 =m.modelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.modelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.modelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.modelGraph.addMyEdge(v2, v5);
		
		
		HashSet<MyVertex> h1= m.getGroundedExtension();
		
		assertEquals(h1, m.groundedLabelling().getInVertices());
	}
	
	@Test
	public void testResetLabels(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v1, v2);
		m.modelGraph.addMyEdge(v3, v2);
		m.modelGraph.addMyEdge(v2, v4);
		m.modelGraph.addMyEdge(v2, v5);
		m.groundedLabelling();
		assertTrue(v2.getLabel()=="OUT");
		assertTrue(v1.getLabel()=="IN");
		m.resetLabels();
		assertTrue(v2.getLabel()=="NONE");
		assertTrue(v1.getLabel()=="NONE"&&v3.getLabel()=="NONE"&&v4.getLabel()=="NONE"&&v5.getLabel()=="NONE");
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testIsIllegallyIn(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		MyEdge e1 =m.modelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.modelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.modelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.modelGraph.addMyEdge(v2, v5);
		m.groundedLabelling();
		System.out.println(""+v2.getLabel());
		v2.setLabel("IN");
		System.out.println(""+v2.getLabel());
		assertFalse(m.isLegallyIn(v2));
	}
	
	@SuppressWarnings("unused")
	@Test 
	public void testIsIllegallyOut(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		MyEdge e1 =m.modelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.modelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.modelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.modelGraph.addMyEdge(v2, v5);
		m.groundedLabelling();
		System.out.println(""+v3.getLabel());
		v3.setLabel("OUT");
		System.out.println(""+v3.getLabel());
		assertFalse(m.isLegallyOut(v3));
	}
	
	@SuppressWarnings("unused")
	@Test 
	public void testIsIlegallyUndec(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		MyEdge e1 =m.modelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m.modelGraph.addMyEdge(v3, v2);
		MyEdge e3 = m.modelGraph.addMyEdge(v2, v4);
		MyEdge e4 = m.modelGraph.addMyEdge(v2, v5);
		m.groundedLabelling();
		System.out.println(""+v3.getLabel());
		v3.setLabel("UNDEC");
		System.out.println(""+v3.getLabel());
		assertFalse(m.isLegallyUndec(v3));
	}
	
	@Test
	public void testHasIllegallyInLabelling(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v1, v2);
		m.modelGraph.addMyEdge(v3, v2);
		m.modelGraph.addMyEdge(v2, v4);
		m.modelGraph.addMyEdge(v2, v5);
		MyLabelling labelling =m.groundedLabelling();
		System.out.println("labelling is: "+labelling.toString()+ "V2's label is "+ v2.getLabel());
		labelling.deleteFromOutVertices(v2);
		System.out.println("labelling is: "+labelling.toString()+ "V2's label is "+ v2.getLabel());
		labelling.addInVertex(v2);
		assertTrue(m.hasillegallyIn(labelling));
	}
	
	@Test
	public void testHasIllegallyInLinkedHashSet(){
		Model m = new Model();
		LinkedHashSet<MyLabelling> setOfLabellings = new LinkedHashSet<MyLabelling>();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v1, v2);
		m.modelGraph.addMyEdge(v3, v2);
		m.modelGraph.addMyEdge(v2, v4);
		m.modelGraph.addMyEdge(v2, v5);
		MyLabelling labelling =m.groundedLabelling();
		MyLabelling labelling1 = m.groundedLabelling();
		setOfLabellings.add(labelling);
		setOfLabellings.add(labelling1);
		assertFalse(m.hasillegallyIn(setOfLabellings));
		labelling.deleteFromOutVertices(v2);
		labelling.addInVertex(v2);
		assertTrue(m.hasillegallyIn(setOfLabellings));
	}
	
	@Test 
	public void testTransitionStep(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		MyVertex v5= m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v1, v2);
		m.modelGraph.addMyEdge(v3, v2);
		m.modelGraph.addMyEdge(v2, v4);
		m.modelGraph.addMyEdge(v2, v5);
		MyLabelling labelling = new MyLabelling(0);
		LinkedHashSet<MyVertex> tempInVertices = new LinkedHashSet<MyVertex>();
		tempInVertices.addAll(m.modelGraph.getMyVertices());
		labelling.setInVerties(tempInVertices);
		m.transitionStep(labelling);
		assertTrue(labelling.getOutVertices().contains(v2));
	}
	
	@Test
	public void testAdmissibleLabelling(){
		Model m = new Model();
		MyVertex v1 =m.modelGraph.addMyVertex();
		MyVertex v2= m.modelGraph.addMyVertex();
		MyVertex v3=  m.modelGraph.addMyVertex();
		MyVertex v4 = m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v4, v3);
		m.modelGraph.addMyEdge(v2, v1);
		m.modelGraph.addMyEdge(v3, v2);
		MyLabelling l1=m.admissibleLabelling();
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
	public void testReorderSet(){
		Model m = new Model();
		MyVertex v1 =m.modelGraph.addMyVertex();
		MyVertex v2= m.modelGraph.addMyVertex();
		MyVertex v3=  m.modelGraph.addMyVertex();
		MyVertex v4 = m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v4, v3);
		m.modelGraph.addMyEdge(v2, v1);
		m.modelGraph.addMyEdge(v3, v2);
		LinkedHashSet<MyVertex> vertices =new LinkedHashSet<MyVertex>(m.modelGraph.getMyVertices());
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
	public void testCheckAllLabels(){
		MyLabelling l1 = new MyLabelling(1);
		MyLabelling l2 = new MyLabelling(2);
		Model m = new Model();
		MyVertex v1 = new MyVertex(1);
		MyVertex v2 = new MyVertex(2);
		l1.addInVertex(v1);
		l2.addInVertex(v1);
		l1.addOutVertex(v2);
		l2.addOutVertex(v2);
		LinkedHashSet<MyLabelling> set = new LinkedHashSet<MyLabelling>();
		set.add(l2);
		set.add(l1);
		assertEquals(m.checkAllLabels(set), 2);
		l2.deleteFromInVertices(v1);
		l2.addOutVertex(v1);
		v1.setLabel("IN");
		assertEquals(m.checkAllLabels(set), 1);
		v1.setLabel("OUT");
		assertEquals(m.checkAllLabels(set), 0);
		
		
		
	}
	
	@Test 
	public void testAllAdmissibleLabellings(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		MyVertex v4= m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v1, v2);
		m.modelGraph.addMyEdge(v2, v1);
		m.modelGraph.addMyEdge(v1, v3);
		m.modelGraph.addMyEdge(v2, v3);
		m.modelGraph.addMyEdge(v3, v4);
		System.out.println(m.allAdmissibleLabellings());
		System.out.println("all addmissible outcome " +m.allAdmissibleLabelling2());
	}
	
	
	@Test 
	public void testAllAdmissibleLabellings2(){
		Model m = new Model();
		MyVertex v1= m.modelGraph.addMyVertex();
		MyVertex v2=m.modelGraph.addMyVertex();
		MyVertex v3= m.modelGraph.addMyVertex();
		m.modelGraph.addMyEdge(v1, v2);
		m.modelGraph.addMyEdge(v2, v3);
		System.out.println(m.allAdmissibleLabellings());
		System.out.println("all addmissible outcome 2" +m.allAdmissibleLabelling2());
		
	}
}
