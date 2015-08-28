package core;

public interface IMyArg {
	

	/**
	 *  Returns the argument's id
	 * @return
	 */
	int getId();
	
	/**
	 * Sets the argument's id
	 * @param id
	 */
	void setId(int id);
	
	/**
	 * Returns the argument's label
	 * @return
	 */
	String getLabel();
	
	/**
	 * Sets the argument's label
	 * @param label
	 */
	void setLabel(String label);
	
	/**
	 * Return the argument's weight
	 * @return
	 */
	int getWeight();
	
	/**
	 * Sets the argument's weight
	 * @param weight
	 */
	void setWeight(int weight);
	
	/**
	 * Overides the toString function to print an argument as V followed by the argument's id.
	 * @return
	 */
	String toString();
	
	/**
	 * Overides the standard equals function to check to objects are equal only if their id, label and weight are equal.
	* As Id is determined automatically based on how many vertices are in the graph it is a key for vertices. 
	*/
	public boolean equals(Object arg2);
	
	/**
	 * Returns true if the argument is labelled in and false otherwise.
	 * @return
	 */
	public boolean isIn();
	
	/**
	 * Returns true if the argument is labelled out and false otherwise.
	 */
	public boolean isOut();

	/** 
	 * Returns true if the argument is labelled undec and false otherwise.
	 */
	public boolean isUndec();
}
