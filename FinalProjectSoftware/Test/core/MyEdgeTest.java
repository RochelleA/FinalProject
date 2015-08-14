/**
 * 
 */
package core;

import static org.junit.Assert.*;

import org.junit.Test;

import core.MyAttack;
import core.MyArgument;

/**
 * @author Rochelle
 *
 */
public class MyEdgeTest {
//	MyVertex v1 = new MyVertex(0);
//	MyVertex v2 = new MyVertex(1);
	MyArgument v3 = new MyArgument(2);
	MyAttack e1 = new MyAttack(0);
	/**
	 * Test method for {@link core.MyAttack#MyEdge(core.MyArgument, core.MyArgument)}.
	 */
	@Test
	public void testMyEdge() {
		
		assertEquals(0,e1.id);
		assertEquals(MyAttack.v1,e1.from);
		assertEquals(MyAttack.v2,e1.to);
		assertEquals("Att("+ 0 +","+ 0 +")",e1.label);
		
		
	}

	/**
	 * Test method for {@link core.MyAttack#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals(0, e1.getId());
	}

	/**
	 * Test method for {@link core.MyAttack#getLabel()}.
	 */
	@Test
	public void testGetLabel() {
		String x = e1.getLabel();
		String y = "Att("+ 0 +","+0+")";
		assertEquals(y,x);
	}

	/**
	 * Test method for {@link core.MyAttack#setId(int)}.
	 */
	@Test
	public void testSetId() {
		e1.setId(3);
		assertEquals(3, e1.getId());
	}

	/**
	 * Test method for {@link core.MyAttack#getFrom()}.
	 */
	@Test
	public void testGetFrom() {
		e1.setFrom(MyAttack.v1);
		assertEquals(MyAttack.v1, e1.getFrom());
			}

	/**
	 * Test method for {@link core.MyAttack#setFrom(core.MyArgument)}.
	 */
	@Test
	public void testSetFrom() {
		e1.setFrom(v3);
		assertEquals(v3,e1.getFrom());
		
	}

	/**
	 * Test method for {@link core.MyAttack#getTo()}.
	 */
	@Test
	public void testGetTo() {
		e1.setTo(MyAttack.v2);
		assertEquals(MyAttack.v2, e1.getTo());
	}

	/**
	 * Test method for {@link core.MyAttack#setTo(core.MyArgument)}.
	 */
	@Test
	public void testSetTo() {
		e1.setTo(v3);
		assertEquals(v3, e1.getTo());
	}
	
	@Test 
	public void testSetLabel(){
		e1.setLabel(MyAttack.v1, MyAttack.v2);
		String y = "Att(0,0)";
		assertEquals(e1.label,y );
	}
	
	
	@Test
	public void testToString(){
		assertEquals(e1.toString(),"Att[0,0]");

	}
	
	@Test
	public void testEquals(){
		MyAttack e1 = new MyAttack(1);
		MyAttack e2 = new MyAttack(1);
		MyAttack e3 = new MyAttack(1);
		MyAttack e4 = new MyAttack(2);
		MyArgument v4 = new MyArgument(3);
		e3.setFrom(v4);
		assertFalse(e1.equals(e3));
		assertTrue(e1.equals(e4));
		assertTrue(e1.equals(e2));
		}

}

