package core;

public interface IMyVertex {
	

	/**
	 *  returns the vertex's id
	 * @return
	 */
	int getId();
	
	/**
	 * sets the vertex's id
	 * @param id
	 */
	void setId(int id);
	
	/**
	 * gets the vertex's label
	 * @return
	 */
	String getLabel();
	
	/**
	 * sets the Vertex's label
	 * @param label
	 */
	void setLabel(String label);
	
	/**
	 * Gets the vertex's weight
	 * @return
	 */
	int getWeight();
	
	/**
	 * Sets the vertex's weight
	 * @param weight
	 */
	void setWeight(int weight);
	
	/**
	 * Overides the toString function to print a vertex as V followed by the Vertex's id.
	 * @return
	 */
	String toString();
	
	/**Overide the standard equals function to check to objects are equal only if their id, label and weight are equal.
	* As Id is determined automatically based on how many vertices are in the graph it is a key for vertices. 
	*/
	public boolean equals(Object vertex2);
	
	/**
	 * Returns true if the vertex is labelled in
	 * @return
	 */
	public boolean isIn();
	
	/**
	 * Returns true if the vertex is labelled out
	 */
	public boolean isOut();

	/** 
	 * Return true if the vertex is labelled undec
	 */
	public boolean isUndec();
}
