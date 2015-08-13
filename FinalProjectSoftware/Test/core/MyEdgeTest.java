/**
 * 
 */
package core;

import static org.junit.Assert.*;

import org.junit.Test;

import core.MyEdge;
import core.MyVertex;

/**
 * @author Rochelle
 *
 */
public class MyEdgeTest {
//	MyVertex v1 = new MyVertex(0);
//	MyVertex v2 = new MyVertex(1);
	MyVertex v3 = new MyVertex(2);
	MyEdge e1 = new MyEdge(0);
	/**
	 * Test method for {@link core.MyEdge#MyEdge(core.MyVertex, core.MyVertex)}.
	 */
	@Test
	public void testMyEdge() {
		
		assertEquals(0,e1.id);
		assertEquals(MyEdge.v1,e1.from);
		assertEquals(MyEdge.v2,e1.to);
		assertEquals("Att("+ 0 +","+ 0 +")",e1.label);
		
		
	}

	/**
	 * Test method for {@link core.MyEdge#getId()}.
	 */
	@Test
	public void testGetId() {
		assertEquals(0, e1.getId());
	}

	/**
	 * Test method for {@link core.MyEdge#getLabel()}.
	 */
	@Test
	public void testGetLabel() {
		String x = e1.getLabel();
		String y = "Att("+ 0 +","+0+")";
		assertEquals(y,x);
	}

	/**
	 * Test method for {@link core.MyEdge#setId(int)}.
	 */
	@Test
	public void testSetId() {
		e1.setId(3);
		assertEquals(3, e1.getId());
	}

	/**
	 * Test method for {@link core.MyEdge#getFrom()}.
	 */
	@Test
	public void testGetFrom() {
		e1.setFrom(MyEdge.v1);
		assertEquals(MyEdge.v1, e1.getFrom());
			}

	/**
	 * Test method for {@link core.MyEdge#setFrom(core.MyVertex)}.
	 */
	@Test
	public void testSetFrom() {
		e1.setFrom(v3);
		assertEquals(v3,e1.getFrom());
		
	}

	/**
	 * Test method for {@link core.MyEdge#getTo()}.
	 */
	@Test
	public void testGetTo() {
		e1.setTo(MyEdge.v2);
		assertEquals(MyEdge.v2, e1.getTo());
	}

	/**
	 * Test method for {@link core.MyEdge#setTo(core.MyVertex)}.
	 */
	@Test
	public void testSetTo() {
		e1.setTo(v3);
		assertEquals(v3, e1.getTo());
	}
	
	@Test 
	public void testSetLabel(){
		e1.setLabel(MyEdge.v1, MyEdge.v2);
		String y = "Att(0,0)";
		assertEquals(e1.label,y );
	}
	
	
	@Test
	public void testToString(){
		assertEquals(e1.toString(),"Att[0,0]");

	}
	
	@Test
	public void testEquals(){
		MyEdge e1 = new MyEdge(1);
		MyEdge e2 = new MyEdge(1);
		MyEdge e3 = new MyEdge(1);
		MyEdge e4 = new MyEdge(2);
		MyVertex v4 = new MyVertex(3);
		e3.setFrom(v4);
		assertFalse(e1.equals(e3));
		assertTrue(e1.equals(e4));
		assertTrue(e1.equals(e2));
		}

}

