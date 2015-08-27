/**
 * 
 */
package core;

import static org.junit.Assert.*;

import org.junit.Test;

import core.MyAtt;
import core.MyArg;

/**
 * @author Rochelle
 *
 */
public class MyAttTest {
//	MyVertex v1 = new MyVertex(0);
//	MyVertex v2 = new MyVertex(1);
	MyArg v3 = new MyArg(2);
	MyAtt e1 = new MyAtt(0);
	/**
	 * Test method for {@link core.MyAtt#MyEdge(core.MyArg, core.MyArg)}.
	 */
	@Test
	public void testMyEdge() {
		
		assertEquals(0,e1.id);
		assertEquals(MyAtt.v1,e1.from);
		assertEquals(MyAtt.v2,e1.to);
		assertEquals("Att("+ 0 +","+ 0 +")",e1.label);
		
		
	}

	/**
	 * Test method for {@link core.MyAtt#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals(0, e1.getId());
	}

	/**
	 * Test method for {@link core.MyAtt#getLabel()}.
	 */
	@Test
	public void testGetLabel() {
		String x = e1.getLabel();
		String y = "Att("+ 0 +","+0+")";
		assertEquals(y,x);
	}

	/**
	 * Test method for {@link core.MyAtt#setId(int)}.
	 */
	@Test
	public void testSetId() {
		e1.setId(3);
		assertEquals(3, e1.getId());
	}

	/**
	 * Test method for {@link core.MyAtt#getFrom()}.
	 */
	@Test
	public void testGetFrom() {
		e1.setFrom(MyAtt.v1);
		assertEquals(MyAtt.v1, e1.getFrom());
			}

	/**
	 * Test method for {@link core.MyAtt#setFrom(core.MyArg)}.
	 */
	@Test
	public void testSetFrom() {
		e1.setFrom(v3);
		assertEquals(v3,e1.getFrom());
		
	}

	/**
	 * Test method for {@link core.MyAtt#getTo()}.
	 */
	@Test
	public void testGetTo() {
		e1.setTo(MyAtt.v2);
		assertEquals(MyAtt.v2, e1.getTo());
	}

	/**
	 * Test method for {@link core.MyAtt#setTo(core.MyArg)}.
	 */
	@Test
	public void testSetTo() {
		e1.setTo(v3);
		assertEquals(v3, e1.getTo());
	}
	
	@Test 
	public void testSetLabel(){
		e1.setLabel(MyAtt.v1, MyAtt.v2);
		String y = "Att(0,0)";
		assertEquals(e1.label,y );
	}
	
	
	@Test
	public void testToString(){
		assertEquals(e1.toString(),"Att[0,0]");

	}
	
	@Test
	public void testEquals(){
		MyAtt e1 = new MyAtt(1);
		MyAtt e2 = new MyAtt(1);
		MyAtt e3 = new MyAtt(1);
		MyAtt e4 = new MyAtt(2);
		MyArg v4 = new MyArg(3);
		e3.setFrom(v4);
		assertFalse(e1.equals(e3));
		assertTrue(e1.equals(e4));
		assertTrue(e1.equals(e2));
		}

}

