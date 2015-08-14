package core;

import static org.junit.Assert.*; 

import org.junit.Test;

import core.MyArgument;


public class MyVertexTest {

MyArgument v1 = new MyArgument(0);

	
	@Test
	public void testMyVertex() {
		
		//need plus 1 because when the new vertex is created it alters the vertexCount
//		System.out.println(MyVertex.vertexCount);
//		MyVertex v = new MyVertex();
		assertEquals(0,v1.id);
		assertEquals(0, v1.weight);
		assertEquals("NONE", v1.label);
	}
	
	@Test
	public void testGetId() {
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
		assertEquals("NONE", x);
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
		assertEquals(v1.toString(),"0");
	}
	
	@Test 
	public void testEquals(){
		MyArgument v1 = new MyArgument(1);
		MyArgument v2 = new MyArgument(1);
		assertTrue(v1.equals(v2));
		v2.setId(2);
		assertFalse(v1.equals(v2));
		v2.setId(1);
		v2.setWeight(1);
		assertFalse(v1.equals(v2));
		v2.setWeight(0);
		v2.setLabel("changed");
		assertFalse(v1.equals(v2));
		
	}
}