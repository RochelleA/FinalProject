package graph1;

public interface IMyEdge {
	
	// returns the edge's Id
	int getId();
	
	//sets the edge's id
	void setId(int id);
	
	//gets the edge's from argument 
	MyVertex getFrom();
	
	//sets the edge's from argument
	void setFrom(MyVertex fromEdge);
	
	//Gets the edge's to argument
	MyVertex getTo();

	//Sets the edge's to argument
	void setTo(MyVertex toEdge);
	
	String getLabel();
	

}
