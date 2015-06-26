package core;

public interface IMyEdge {
	
	// returns the edge's Id
	int getId();
	
	//sets the edge's id
	void setId(int id);
	
	//Gets the edge's label
	String getLabel();
		
	//gets the edge's from argument 
	MyVertex getFrom();
	
	//sets the edge's from argument
	void setFrom(MyVertex fromEdge);
	
	//Gets the edge's to argument
	MyVertex getTo();

	//Sets the edge's to argument
	void setTo(MyVertex toEdge);
	
	//Overriding the toString function of an edge so that the string representation of a graph will write edges as att[v1,v2]
	String toString();
	
	

}
