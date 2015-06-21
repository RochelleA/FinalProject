/**
 * 
 */
package core;

import java.util.Collection;

//import edu.uci.ics.jung.graph.DirectedSparseGraph;

/**
 * @author Rochelle
 *
 */
public interface IMyGraph {
	
	//Return all the edges in the Graph.
	public Collection<MyVertex> getMyVertices();
	
	//Returns all the vertices in the Graph
	public Collection<MyEdge> getMyEdges();
	
	//This method first creates a new vertex and then adds it to the graph
	public boolean addMyVertex();


	//This method creates a vertex between V1 and V2 and then adds it to the graph
	public boolean addMyEdge(MyVertex v1, MyVertex v2);
	}
