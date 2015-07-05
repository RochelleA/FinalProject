package core;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class MyLabellingTest {

	@Test
	public void testMyLabelling() {
		MyLabelling l1 = new MyLabelling(0);
		assertEquals(l1.id,0);
		assertTrue(l1.InLabels.isEmpty());
		assertTrue(l1.OutLabels.isEmpty());
		assertTrue(l1.UndecLabels.isEmpty());
	}

	@Test
	public void testGetInVertices() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addInVertex(v);
		assertEquals(l1.InLabels,l1.getInVertices());
	}

	@Test
	public void testAddInVertex() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addInVertex(v);
		assertTrue(l1.InLabels.contains(v));
		assertTrue(v.label=="IN");
	}

	@Test
	public void testSetInVerties() {
		MyLabelling l1 = new MyLabelling(0);
		HashSet<MyVertex> h1 = new HashSet<MyVertex>();
		MyVertex v = new MyVertex(0);
		h1.add(v);
		l1.setInVerties(h1);
		assertEquals(h1, l1.getInVertices());
	}

	@Test
	public void testGetOutVertices() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addOutVertex(v);
		assertEquals(l1.OutLabels,l1.getOutVertices());
	}

	@Test
	public void testAddOutVertex() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addOutVertex(v);
		assertTrue(l1.getOutVertices().contains(v));
		assertTrue(v.label=="OUT");
	}

	@Test
	public void testGetUndecVertices() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addUndecVertex(v);
		assertEquals(l1.UndecLabels,l1.getUndecVertices());
	}

	@Test
	public void testAddUndecVertex() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addUndecVertex(v);
		assertTrue(l1.UndecLabels.contains(v));
		assertTrue(v.label=="UNDEC");
		}

	@Test
	public void testToString() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addUndecVertex(v);
		String s = "{\u00D8,\u00D8,[V0]}";
		assertEquals(s, l1.toString());
	}

	@Test
	public void testEqualsObject() {
		MyLabelling l1 = new MyLabelling(0);
		MyLabelling l2 = new MyLabelling(0);
		assertTrue(l2.equals(l1));
		MyVertex v = new MyVertex(0);
		l1.addUndecVertex(v);
		assertFalse(l2.equals(l1));
		
	}

	@Test
	public void testContains() {
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(0);
		MyVertex v2 = new MyVertex(0);
		MyVertex v3 = new MyVertex(0);
		l1.addUndecVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		assertTrue(l1.contains(v1));
	}

}