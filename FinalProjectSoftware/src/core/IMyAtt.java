package core;

public interface IMyAtt {
	
	/**
	 *  Returns the attack's id
	 * @return the id of the argument.
	 */
	int getId();
	
	/**
	 * Sets the attack's id
	 * @param id the id number for the argument.
	 */
	void setId(int integer);
	
	/**
	 * Gets the attack's label
	 * @return the label for the argument.
	 */
	String getLabel();
		
	/**
	 * Gets the attack's from argument 
	 * @return the attacking argument.
	 */
	MyArg getFrom();
	
	/**
	 * Sets the attack's from argument
	 * @param argument the argument to be used as the attacking argument.
	 */
	void setFrom(MyArg argument);
	
	/**
	 * Gets the attack's to argument
	 * @return the argument being attacked.
	 */
	MyArg getTo();

	/**
	 * Sets the attack's to argument
	 * @param argument the argument to be used as the attacked argument.
	 */
	void setTo(MyArg argument);
	
	/**
	 * Overriding the toString function of an attack.
	 * This ensures that the string representation of a graph will write attacks as att[v1,v2].
	 * @return the string representation of the attack.
	 */
	String toString();
	
	

}
