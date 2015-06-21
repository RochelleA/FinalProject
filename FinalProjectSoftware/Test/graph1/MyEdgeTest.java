/**
 * 
 */
package graph1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Rochelle
 *
 */
public class MyEdgeTest {

	/**
	 * Test method for {@link graph1.MyEdge#MyEdge(graph1.MyVertex, graph1.MyVertex)}.
	 */
	@Test
	public void testMyEdge() {
		//create a vertex and store the current vertex count into an integer. This value is the id for the vertex v1.
		MyVertex v1 = new MyVertex();
		int v1VertexCount = MyVertex.vertexCount;

		//create a vertex and store the current vertex count into an integer. This value is the id for the vertex v2.
		MyVertex v2 = new MyVertex();
		int v2VertexCount = MyVertex.vertexCount;

		
		MyEdge e1 = new MyEdge(v1,v2);
		assertEquals(MyEdge.edgeCount,e1.id);
		assertEquals(v1,e1.from);
		assertEquals(v2,e1.to);
		assertEquals("Att("+ v1VertexCount +","+ v2VertexCount +")",e1.label);
		
		
	}

	/**
	 * Test method for {@link graph1.MyEdge#getId()}.
	 */
	@Test
	public void testGetId() {
		MyVertex v1 = new MyVertex();
		MyVertex v2 = new MyVertex();
		MyEdge e1 = new MyEdge(v1,v2);
		assertEquals(MyEdge.edgeCount, e1.getId());
	}

	/**
	 * Test method for {@link graph1.MyEdge#getLabel()}.
	 */
	@Test
	public void testGetLabel() {
		
		//create a vertex and store the current vertex count into an integer. This value is the id for the vertex v1.
		MyVertex v1 = new MyVertex();
		int v1VertexCount = MyVertex.vertexCount;

		//create a vertex and store the current vertex count into an integer. This value is the id for the vertex v2.
		MyVertex v2 = new MyVertex();
		int v2VertexCount = MyVertex.vertexCount;
		
		MyEdge e1 = new MyEdge(v1,v2);
		String x = e1.getLabel();
		String y = "Att("+v1VertexCount+","+v2VertexCount+")";
		assertEquals(y,x);
	}

	/**
	 * Test method for {@link graph1.MyEdge#setId(int)}.
	 */
	@Test
	public void testSetId() {
		MyVertex v1 = new MyVertex();
		MyVertex v2 = new MyVertex();
		MyEdge e1 = new MyEdge(v1,v2);
		e1.setId(3);
		assertEquals(3, e1.getId());
	}

	/**
	 * Test method for {@link graph1.MyEdge#getFrom()}.
	 */
	@Test
	public void testGetFrom() {
		MyVertex v1 = new MyVertex();
		MyVertex v2 = new MyVertex();
		MyEdge e1 = new MyEdge(v1,v2);
		assertEquals(v1, e1.getFrom());
			}

	/**
	 * Test method for {@link graph1.MyEdge#setFrom(graph1.MyVertex)}.
	 */
	@Test
	public void testSetFrom() {
		MyVertex v1 = new MyVertex();
		MyVertex v2 = new MyVertex();
		MyVertex v3 = new MyVertex();
		MyEdge e1 = new MyEdge(v1,v2);
		e1.setFrom(v3);
		assertEquals(v3,e1.getFrom());
		
	}

	/**
	 * Test method for {@link graph1.MyEdge#getTo()}.
	 */
	@Test
	public void testGetTo() {
		MyVertex v1 = new MyVertex();
		MyVertex v2 = new MyVertex();
		MyEdge e1 = new MyEdge(v1,v2);
		assertEquals(v2, e1.getTo());
	}

	/**
	 * Test method for {@link graph1.MyEdge#setTo(graph1.MyVertex)}.
	 */
	@Test
	public void testSetTo() {
		MyVertex v1 = new MyVertex();
		MyVertex v2 = new MyVertex();
		MyVertex v3 = new MyVertex();
		MyEdge e1 = new MyEdge(v1,v2);
		e1.setTo(v3);
		assertEquals(v3, e1.getTo());
	}

}
