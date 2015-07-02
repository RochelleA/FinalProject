/**
 * 
 */
package core;

import java.util.Collection;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

//import edu.uci.ics.jung.graph.DirectedSparseGraph;

/**
 * @author Rochelle
 *
 */
public interface IMyGraph {
//	
//	
//	//Returns the number of vertices in the graph
//	public int GetMyVertexCount();
//	
//	//Returns the number of edges in the graph
//	public int GetMyEdgeCount();

	//Return all the edges in the Graph.
	public Collection<MyVertex> getMyVertices();
	
	//Returns all the vertices in the Graph
	public Collection<MyEdge> getMyEdges();

	//This method first creates a new vertex and then adds it to the graph
	public MyVertex addMyVertex();

	//This method creates a vertex between V1 and V2 and then adds it to the graph
	public MyEdge addMyEdge(MyVertex v1, MyVertex v2);
	
	//return myGraph.toString();
	public String toString();
	
	//return the directed sparse graph inside the myGraph object
	public DirectedSparseGraph<MyVertex, MyEdge> getGraph();
	}