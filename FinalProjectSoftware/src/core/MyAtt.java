package core;

public class MyAtt implements IMyAtt {
	// id identifies an attack in the graph
	int id;
	//identifies the argument the attack comes from
	MyArg from;
	//identifies the argument the attack attacks
	MyArg to;
	//is a label such as Att(1,2)
	String label;

	 static MyArg v1= new MyArg(0);
	static MyArg v2= new MyArg(0);
	//count the number of attack so that the attack Id can be incremented by 1 each time.
	
	
	public MyAtt(int id){
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

	public MyArg getFrom() {
		return from;
	}
	
	public void setFrom(MyArg from) {
		this.from = from;
	}
	
	public MyArg getTo() {
		return to;
	}
	
	public void setTo(MyArg to) {
		this.to = to;
	}
	public void setLabel(MyArg vl1, MyArg vl2){
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
		    MyAtt att = (MyAtt)other;
		    return (to.equals(att.getTo()) && from.equals(att.getFrom()));
		  }
		

}
