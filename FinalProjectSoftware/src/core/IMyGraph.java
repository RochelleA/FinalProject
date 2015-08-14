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
//	//Returns the number of Args in the graph
//	public int GetMyargumentCount();
//	
//	//Returns the number of attackss in the graph
//	public int GetMyattCount();

	//Return all the attacks in the Graph.
	public Collection<MyArg> getMyArgs();
	
	//Returns all the Args in the Graph
	public Collection<MyAtt> getMyAtts();

	//This method first creates a new argument and then adds it to the graph
	public MyArg addMyArg();

	//This method creates a argument between V1 and V2 and then adds it to the graph
	public MyAtt addMyAtt(MyArg v1, MyArg v2);
	
	//return myGraph.toString();
	public String toString();
	
	//return the directed sparse graph inside the myGraph object
	public DirectedSparseGraph<MyArg, MyAtt> getGraph();
	}