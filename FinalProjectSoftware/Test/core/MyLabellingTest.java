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
	public void testGetInArgs() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addInArg(v);
		assertEquals(l1.InLabels,l1.getInArgs());
	}

	@Test
	public void testAddInArg() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addInArg(v);
		assertTrue(l1.InLabels.contains(v));
		assertTrue(v.label=="IN");
	}

	@Test
	public void testSetInArgs() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyArg> h1 = new LinkedHashSet<MyArg>();
		MyArg v = new MyArg(0);
		h1.add(v);
		l1.setInVerties(h1);
		assertEquals(h1, l1.getInArgs());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testDeleteFromInArgs(){
		MyLabelling l1 = new MyLabelling(0);
		MyArg v1 = new MyArg(0);
		MyArg v2 = new MyArg(0);
		MyArg v3 = new MyArg(0);
		l1.addUndecArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		l1.deleteFromInArgs(v3);
		
	}	
	
	@Test
	public void testDeleteFromInArgs2(){
		MyLabelling l1 = new MyLabelling(0);
		MyArg v1 = new MyArg(0);
		MyArg v2 = new MyArg(0);
		MyArg v3 = new MyArg(0);
		l1.addUndecArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		l1.deleteFromInArgs(v2);
		assertTrue((!(l1.InLabels.contains(v2)))&& v2.getLabel()=="NONE" && (l1.NotLabelledArgs.contains(v2)));
	}

	@Test
	public void testGetOutArgs() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addOutArg(v);
		assertEquals(l1.OutLabels,l1.getOutArgs());
	}

	@Test
	public void testAddOutArg() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addOutArg(v);
		assertTrue(l1.getOutArgs().contains(v));
		assertTrue(v.label=="OUT");
	}
	
	@Test
	public void testSetOutArgs() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyArg> h1 = new LinkedHashSet<MyArg>();
		MyArg v = new MyArg(0);
		h1.add(v);
		l1.setOutArgs(h1);
		assertEquals(h1, l1.getOutArgs());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testDeleteFromOutArgs(){
		MyLabelling l1 = new MyLabelling(0);
		MyArg v1 = new MyArg(0);
		MyArg v2 = new MyArg(0);
		MyArg v3 = new MyArg(0);
		l1.addUndecArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		l1.deleteFromUndecArgs(v3);
		
	}
	
	@Test
	public void testDeleteFromOutArgs2(){
		MyLabelling l1 = new MyLabelling(0);
		MyArg v1 = new MyArg(0);
		MyArg v2 = new MyArg(0);
		MyArg v3 = new MyArg(0);
		l1.addUndecArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		l1.deleteFromOutArgs(v3);
		assertTrue((!(l1.OutLabels.contains(v3)))&& v3.getLabel()=="NONE"&& (l1.NotLabelledArgs.contains(v3)));
	}

	@Test
	public void testGetUndecArgs() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addUndecArg(v);
		assertEquals(l1.UndecLabels,l1.getUndecArgs());
	}

	@Test
	public void testAddUndecArg() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addUndecArg(v);
		assertTrue(l1.UndecLabels.contains(v));
		assertTrue(v.label=="UNDEC");
		}
	
	@Test
	public void testSetUndecVerties() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyArg> h1 = new LinkedHashSet<MyArg>();
		MyArg v = new MyArg(0);
		h1.add(v);
		l1.setUndecArgs(h1);
		assertEquals(h1, l1.getUndecArgs());
	}
	
	@Test  (expected = IllegalArgumentException.class)
	public void testDeleteFromUndecArgs(){
		MyLabelling l1 = new MyLabelling(0);
		MyArg v1 = new MyArg(0);
		MyArg v2 = new MyArg(0);
		MyArg v3 = new MyArg(0);
		l1.addUndecArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		l1.deleteFromUndecArgs(v3);
		assertTrue((!(l1.UndecLabels.contains(v3)))&& v3.getLabel()=="NONE"&& l1.NotLabelledArgs.contains(v3));
		
	}
	
	@Test
	public void testDeleteUndecInArgs2(){
		MyLabelling l1 = new MyLabelling(0);
		MyArg v1 = new MyArg(0);
		MyArg v2 = new MyArg(0);
		MyArg v3 = new MyArg(0);
		l1.addUndecArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		l1.deleteFromUndecArgs(v1);
		assertTrue(!(l1.UndecLabels.contains(v1)));
	}
	


	@Test
	public void testToString() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addUndecArg(v);
		String s = "{\u00D8,\u00D8,[0]}";
		assertEquals(s, l1.toString());
	}
	
	@Test 
	public void testFindMyArg(){
		MyArg v1 = new MyArg(1);
		MyArg v2 = new MyArg(2);
		MyArg v3 = new MyArg(3);
		MyArg v4 = new MyArg(4);
		MyLabelling l1 = new MyLabelling(1);
		LinkedHashSet<MyArg> set = new LinkedHashSet<MyArg>();
		set.add(v1);
		set.add(v2);
		set.add(v3);
		assertTrue(l1.findMyArg(v1, set));
		assertFalse(l1.findMyArg(v4, set));
		v4.setId(1);
		assertTrue(l1.findMyArg(v4, set));
	}
	
	@Test 
	public void testContainsAllArgs(){
		MyArg v1 = new MyArg(1);
		MyArg v2 = new MyArg(2);
		MyArg v3 = new MyArg(3);
		MyArg v4 = new MyArg(4);
		MyLabelling l1 = new MyLabelling(1);
		LinkedHashSet<MyArg> set = new LinkedHashSet<MyArg>();
		set.add(v1);
		set.add(v2);
		set.add(v3);
		LinkedHashSet<MyArg> set1 = new LinkedHashSet<MyArg>();
		set1.add(v3);
		set1.add(v2);
		set1.add(v1);
		assertTrue(l1.containsAllArgs(set, set1));
		
	}
	@Test
	public void testEqualsObject() {
		MyLabelling l1 = new MyLabelling(0);
		MyLabelling l2 = new MyLabelling(0);
		assertTrue(l2.equals(l1));
		MyArg v = new MyArg(0);
		l1.addUndecArg(v);
		System.out.println("l1 is:" + l1.toString());
		assertFalse(l2.equals(l1));
		
	}

	@Test
	public void testContains() {
		MyLabelling l1 = new MyLabelling(0);
		MyArg v1 = new MyArg(0);
		MyArg v2 = new MyArg(0);
		MyArg v3 = new MyArg(0);
		l1.addUndecArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		assertTrue(l1.contains(v1));
	}
	


	
	@Test 
	public void testNotLabelledArgs(){
		MyLabelling l1 = new MyLabelling(0);
		MyArg v = new MyArg(0);
		l1.addUndecArg(v);
		assertEquals(l1.NotLabelledArgs,l1.getNotLabelledArgs());
		
	}
	
	@Test
	public void testSetNotLabelledArgs() {
		MyLabelling l1 = new MyLabelling(0);
		LinkedHashSet<MyArg> h1 = new LinkedHashSet<MyArg>();
		MyArg v = new MyArg(0);
		h1.add(v);
		l1.setNotLabelledArgs(h1);
		assertEquals(h1, l1.getNotLabelledArgs());
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
		MyArg v1 = new MyArg(1);
		MyArg v2 = new MyArg(2);
		MyArg v3 = new MyArg(3);
		MyArg v4 = new MyArg(5);
		MyArg v5 = new MyArg(6);
		l1.addInArg(v1);
		l1.addInArg(v2);
		l1.addOutArg(v3);
		l1.addUndecArg(v4);
		l1.addUndecArg(v5);
		System.out.print(l1);
		assertTrue(l1.checkAllLabels());
		v1.setLabel("OUT");
		assertFalse(l1.checkAllLabels());
		
	}
}
