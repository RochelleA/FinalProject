package core;

public class MyAttack implements IMyAttack {
	// id identifies an edge in the graph
	int id;
	//identifies the argument the edge comes from
	MyArgument from;
	//identifies the argument the edge attacks
	MyArgument to;
	//is a label such as Att(1,2)
	String label;

	 static MyArgument v1= new MyArgument(0);
	static MyArgument v2= new MyArgument(0);
	//count the number of edge so that the edge Id can be incremented by 1 each time.
	
	
	public MyAttack(int id){
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

	public MyArgument getFrom() {
		return from;
	}
	
	public void setFrom(MyArgument from) {
		this.from = from;
	}
	
	public MyArgument getTo() {
		return to;
	}
	
	public void setTo(MyArgument to) {
		this.to = to;
	}
	public void setLabel(MyArgument vl1, MyArgument vl2){
		this.label="Att("+vl1.getId()+","+vl2.getId()+")";
	}
	public void updateLabel(){
		this.setLabel(from,to);
	}
	public String toString() {
        return "Att["+this.from+","+this.to+"]";
	}

	public boolean equals(Object other) {
		    if (other == this) return true;
		    if (other == null) return false;
		    if (getClass() != other.getClass()) return false;
		    MyAttack edge = (MyAttack)other;
		    return (to.equals(edge.getTo()) && from.equals(edge.getFrom()));
		  }
		

}
