package core;

import java.util.HashSet;
import java.util.Iterator;

public class MyLabelling implements IMyLabelling {
	
	int id;
	HashSet<MyVertex> InLabels;
	HashSet<MyVertex> OutLabels;
	HashSet<MyVertex> UndecLabels;
	

	public MyLabelling(int id) {
		this.id = id;
		InLabels= new HashSet<MyVertex>();
		OutLabels = new HashSet<MyVertex>();
		UndecLabels = new HashSet<MyVertex>();
		
	}

	@Override
	public HashSet<MyVertex> getInVertices() {
		return InLabels;
	}

	@Override
	public void addInVertex(MyVertex v) {
		if(this.OutLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled out");
		}
		else if(this.UndecLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled undec");
		}
		else{
			v.setLabel("IN");
			InLabels.add(v);
		}
	}
	
	public HashSet<MyVertex> setInVerties(HashSet<MyVertex> h1){
		Iterator<MyVertex> I = h1.iterator();
		while(I.hasNext()){
			I.next().setLabel("IN");
		}
		InLabels=h1;
		return InLabels;
	}

	@Override
	public HashSet<MyVertex> getOutVertices() {
		return  OutLabels;
	}

	@Override
	public void addOutVertex(MyVertex v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled in");
		}
		if(this.UndecLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled undec");
		}
		v.setLabel("OUT");
		OutLabels.add(v);
	}

	@Override
	public HashSet<MyVertex> getUndecVertices() {
		return UndecLabels;
	}

	@Override
	public void addUndecVertex(MyVertex v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled in");
		}
		if(this.OutLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled out");
		}
		v.setLabel("UNDEC");
		UndecLabels.add(v);
	}

	@Override
	public String toString(){
		String inString;
		String outString;
		String undecString;
		if(InLabels.isEmpty()){
			inString= "\u00D8";
		}
		else 
			inString=InLabels.toString();
		if(OutLabels.isEmpty()){
			outString="\u00D8";
		}
		else 
			outString=OutLabels.toString();
		if(UndecLabels.isEmpty()){
			undecString="\u00D8";
		}
		else{
			undecString=UndecLabels.toString();
		}
		
		String string = "{"+ inString + "," + outString +"," + undecString + "}";
		return string;
	}

	public boolean equals(Object label2) {
		if (label2 == this) return true;
	    if (label2 == null) return false;
	    if (getClass() != label2.getClass()) return false;
	    MyLabelling label = (MyLabelling)label2;
	    return (InLabels.equals(label.InLabels) && OutLabels.equals(label.OutLabels) && UndecLabels.equals(label.UndecLabels) && id==label.id);
	  }
	

public boolean contains(MyVertex v){
	if(this.InLabels.contains(v)){
		return true;
	}
	else if(this.OutLabels.contains(v)){
		return true;
	}
	else if (this.UndecLabels.contains(v)){
		return true;
	}
	else{
		return false;
	}
}
}

