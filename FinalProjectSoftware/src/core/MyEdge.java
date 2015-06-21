package core;

public class MyEdge implements IMyEdge {
	static int edgeCount=0;
	// id identifies an edge in the graph
	int id;
	//identifies the argument the edge comes from
	MyVertex from;
	//identifies the argument the edge attacks
	MyVertex to;
	//is a label such as Att(1,2)
	String label;
	//count the number of edge so that the edge Id can be incremented by 1 each time.
	
	
	public MyEdge(MyVertex v, MyVertex y){
		if(v==y){
			throw new IllegalArgumentException("The to and from vertices must be distinct");
		}
		else{
		this.from = v;
		this.to= y;
		this.id=++edgeCount;
		this.label= "Att("+v.getId()+","+y.getId()+")";
		}
	}

	public int getId() {
		return id;
	}
	public String getLabel() {
		return label;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String toString() {
        return "Att";
	}

}
