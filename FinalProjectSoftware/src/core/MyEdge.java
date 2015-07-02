package core;

public class MyEdge implements IMyEdge {
	// id identifies an edge in the graph
	int id;
	//identifies the argument the edge comes from
	MyVertex from;
	//identifies the argument the edge attacks
	MyVertex to;
	//is a label such as Att(1,2)
	String label;

	 static MyVertex v1= new MyVertex(0);
	static MyVertex v2= new MyVertex(0);
	//count the number of edge so that the edge Id can be incremented by 1 each time.
	
	
	public MyEdge(int id){
		this.from = v1;
		this.to= v2;
		this.id=id;
		this.label= "Att("+v1.getId()+","+v2.getId()+")";
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public MyVertex getFrom() {
		return from;
	}
	
	public void setFrom(MyVertex from) {
		this.from = from;
	}
	
	public MyVertex getTo() {
		return to;
	}
	
	public void setTo(MyVertex to) {
		this.to = to;
	}
	public void setLabel(MyVertex vl1, MyVertex vl2){
		this.label="Att("+vl1.getId()+","+vl2.getId()+")";
	}
	public String toString() {
        return "Att";
	}

}
