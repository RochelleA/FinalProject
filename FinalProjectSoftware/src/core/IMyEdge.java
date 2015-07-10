package core;

public interface IMyEdge {
	
	/**
	 *  Returns the edge's id
	 * @return the id of the vertex.
	 */
	int getId();
	
	/**
	 * Sets the edge's id
	 * @param id the id number for the vertex.
	 */
	void setId(int integer);
	
	/**
	 * Gets the edge's label
	 * @return the label for the Vertex.
	 */
	String getLabel();
		
	/**
	 * Gets the edge's from argument 
	 * @return the attacking vertex.
	 */
	MyVertex getFrom();
	
	/**
	 * Sets the edge's from argument
	 * @param vertex the vertex to be used as the attacking vertex.
	 */
	void setFrom(MyVertex vertex);
	
	/**
	 * Gets the edge's to argument
	 * @return the vertex being attacked.
	 */
	MyVertex getTo();

	/**
	 * Sets the edge's to argument
	 * @param vertex the vertex to be used as the attacked vertex.
	 */
	void setTo(MyVertex vertex);
	
	/**
	 * Overriding the toString function of an edge.
	 * This ensures that the string representation of a graph will write edges as att[v1,v2].
	 * @return the string representation of the edge.
	 */
	String toString();
	
	

}
