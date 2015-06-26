package core;

import static org.junit.Assert.*; 

import org.junit.Test;

import core.MyVertex;


public class MyVertexTest {

MyVertex v1 = new MyVertex();

	
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
		MyVertex v = new MyVertex();
		System.out.println(""+v.id);
		int x = v.getId();
		assertEquals(0,x);
	}

	@Test
	public void testSetId() {
		MyVertex v3 = new MyVertex();
		v3.setId(4);
		assertEquals(4, v3.id);
	}

	@Test
	public void testGetLabel() {
		MyVertex v4 = new MyVertex();
		String x = v4.getLabel();
		assertEquals("no label", x);
	}

	@Test
	public void testSetLabel() {
		MyVertex v5 = new MyVertex();
		v5.setLabel("Yes");
		assertEquals("Yes", v5.getLabel());
	}

	@Test
	public void testGetWeight() {
		MyVertex v6= new MyVertex();
		int x = v6.getWeight();
		assertEquals(0,x);
		
	}

	@Test
	public void testSetWeight() {
		MyVertex v7 = new MyVertex();
		v7.setWeight(4);
		assertEquals(4, v7.getWeight());
	}

	@Test
public void testToString(){
		MyVertex v1 = new MyVertex();
		assertEquals(v1.toString(),"V0");
	}
}
