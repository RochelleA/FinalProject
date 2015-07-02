package core;

import static org.junit.Assert.*; 

import org.junit.Test;

import core.MyVertex;


public class MyVertexTest {

MyVertex v1 = new MyVertex(0);

	
	@Test
	public void testMyVertex() {
		System.out.println(v1.id);
		
		//need plus 1 because when the new vertex is created it alters the vertexCount
//		System.out.println(MyVertex.vertexCount);
//		MyVertex v = new MyVertex();
		assertEquals(0,v1.id);
		assertEquals(0, v1.weight);
		assertEquals("no label", v1.label);
	}
	
	@Test
	public void testGetId() {
		
		System.out.println(""+v1.id);
		int x = v1.getId();
		assertEquals(0,x);
	}

	@Test
	public void testSetId() {
		v1.setId(4);
		assertEquals(4, v1.id);
	}

	@Test
	public void testGetLabel() {
		String x = v1.getLabel();
		assertEquals("no label", x);
	}

	@Test
	public void testSetLabel() {
		v1.setLabel("Yes");
		assertEquals("Yes", v1.getLabel());
	}

	@Test
	public void testGetWeight() {
		int x = v1.getWeight();
		assertEquals(0,x);
		
	}

	@Test
	public void testSetWeight() {
		v1.setWeight(4);
		assertEquals(4, v1.getWeight());
	}

	@Test
public void testToString(){
		assertEquals(v1.toString(),"V0");
	}
}