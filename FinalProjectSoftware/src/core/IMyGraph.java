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
	public Collection<MyArgument> getMyVertices();
	
	//Returns all the vertices in the Graph
	public Collection<MyAttack> getMyEdges();

	//This method first creates a new vertex and then adds it to the graph
	public MyArgument addMyVertex();

	//This method creates a vertex between V1 and V2 and then adds it to the graph
	public MyAttack addMyEdge(MyArgument v1, MyArgument v2);
	
	//return myGraph.toString();
	public String toString();
	
	//return the directed sparse graph inside the myGraph object
	public DirectedSparseGraph<MyArgument, MyAttack> getGraph();
	}