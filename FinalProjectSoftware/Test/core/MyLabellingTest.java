package core;

import static org.junit.Assert.*;
import java.util.LinkedHashSet;

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
	public void testSetInVertices() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyVertex> h1 = new LinkedHashSet<MyVertex>();
		MyVertex v = new MyVertex(0);
		h1.add(v);
		l1.setInVerties(h1);
		assertEquals(h1, l1.getInVertices());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testDeleteFromInVertices(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(0);
		MyVertex v2 = new MyVertex(0);
		MyVertex v3 = new MyVertex(0);
		l1.addUndecVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		l1.deleteFromInVertices(v3);
		
	}	
	
	@Test
	public void testDeleteFromInVertices2(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(0);
		MyVertex v2 = new MyVertex(0);
		MyVertex v3 = new MyVertex(0);
		l1.addUndecVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		l1.deleteFromInVertices(v2);
		assertTrue((!(l1.InLabels.contains(v2)))&& v2.getLabel()=="NONE" && (l1.NotLabelledVertices.contains(v2)));
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
	public void testSetOutVertices() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyVertex> h1 = new LinkedHashSet<MyVertex>();
		MyVertex v = new MyVertex(0);
		h1.add(v);
		l1.setOutVertices(h1);
		assertEquals(h1, l1.getOutVertices());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testDeleteFromOutVertices(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(0);
		MyVertex v2 = new MyVertex(0);
		MyVertex v3 = new MyVertex(0);
		l1.addUndecVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		l1.deleteFromUndecVertices(v3);
		
	}
	
	@Test
	public void testDeleteFromOutVertices2(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(0);
		MyVertex v2 = new MyVertex(0);
		MyVertex v3 = new MyVertex(0);
		l1.addUndecVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		l1.deleteFromOutVertices(v3);
		assertTrue((!(l1.OutLabels.contains(v3)))&& v3.getLabel()=="NONE"&& (l1.NotLabelledVertices.contains(v3)));
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
	public void testSetUndecVerties() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyVertex> h1 = new LinkedHashSet<MyVertex>();
		MyVertex v = new MyVertex(0);
		h1.add(v);
		l1.setUndecVertices(h1);
		assertEquals(h1, l1.getUndecVertices());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testDeleteFromUndecVertices(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(0);
		MyVertex v2 = new MyVertex(0);
		MyVertex v3 = new MyVertex(0);
		l1.addUndecVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		l1.deleteFromUndecVertices(v3);
		assertTrue((!(l1.UndecLabels.contains(v3)))&& v3.getLabel()=="NONE"&& l1.NotLabelledVertices.contains(v3));
		
	}
	
	@Test
	public void testDeleteUndecInVertices2(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(0);
		MyVertex v2 = new MyVertex(0);
		MyVertex v3 = new MyVertex(0);
		l1.addUndecVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		l1.deleteFromUndecVertices(v1);
		assertTrue(!(l1.UndecLabels.contains(v1)));
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
	public void testFindMyVertex(){
		MyVertex v1 = new MyVertex(1);
		MyVertex v2 = new MyVertex(2);
		MyVertex v3 = new MyVertex(3);
		MyVertex v4 = new MyVertex(4);
		MyLabelling l1 = new MyLabelling(1);
		LinkedHashSet<MyVertex> set = new LinkedHashSet<MyVertex>();
		set.add(v1);
		set.add(v2);
		set.add(v3);
		assertTrue(l1.findMyVertex(v1, set));
		assertFalse(l1.findMyVertex(v4, set));
		v4.setId(1);
		assertTrue(l1.findMyVertex(v4, set));
	}
	
	@Test 
	public void testContainsAllVertices(){
		MyVertex v1 = new MyVertex(1);
		MyVertex v2 = new MyVertex(2);
		MyVertex v3 = new MyVertex(3);
		MyVertex v4 = new MyVertex(4);
		MyLabelling l1 = new MyLabelling(1);
		LinkedHashSet<MyVertex> set = new LinkedHashSet<MyVertex>();
		set.add(v1);
		set.add(v2);
		set.add(v3);
		LinkedHashSet<MyVertex> set1 = new LinkedHashSet<MyVertex>();
		set1.add(v3);
		set1.add(v2);
		set1.add(v1);
		assertTrue(l1.containsAllVertices(set, set1));
		
	}
	@Test
	public void testEqualsObject() {
		MyLabelling l1 = new MyLabelling(0);
		MyLabelling l2 = new MyLabelling(0);
		assertTrue(l2.equals(l1));
		MyVertex v = new MyVertex(0);
		l1.addUndecVertex(v);
		System.out.println("l1 is:" + l1.toString());
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
	


	
	@Test 
	public void testNotLabelledVertices(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v = new MyVertex(0);
		l1.addUndecVertex(v);
		assertEquals(l1.NotLabelledVertices,l1.getNotLabelledVertices());
		
	}
	
	@Test
	public void testSetNotLabelledVertices() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyVertex> h1 = new LinkedHashSet<MyVertex>();
		MyVertex v = new MyVertex(0);
		h1.add(v);
		l1.setNotLabelledVertices(h1);
		assertEquals(h1, l1.getNotLabelledVertices());
	}
	
	@Test 
	public void testDisplayLabelling(){
		MyLabelling l1 = new MyLabelling(0);
		String s = "In labelled arguments:  "+ "\u00D8"+"\n Out labelled arguments:  "+  "\u00D8"+"\n Undecided labelled arguments: "+"\u00D8";
		assertEquals(s, l1.DisplayLabelling());
	}
	
	@Test 
	public void testCheckAllLabels(){
		MyLabelling l1 = new MyLabelling(0);
		MyVertex v1 = new MyVertex(1);
		MyVertex v2 = new MyVertex(2);
		MyVertex v3 = new MyVertex(3);
		MyVertex v4 = new MyVertex(5);
		MyVertex v5 = new MyVertex(6);
		l1.addInVertex(v1);
		l1.addInVertex(v2);
		l1.addOutVertex(v3);
		l1.addUndecVertex(v4);
		l1.addUndecVertex(v5);
		System.out.print(l1);
		assertTrue(l1.checkAllLabels());
		v1.setLabel("OUT");
		assertFalse(l1.checkAllLabels());
		
	}
}
