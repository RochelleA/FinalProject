package core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class MyLabelling implements IMyLabelling {
	
	int id;
	LinkedHashSet<MyVertex> InLabels;
	LinkedHashSet<MyVertex> OutLabels;
	LinkedHashSet<MyVertex> UndecLabels;
	private LinkedHashSet<MyVertex> NotLabelledVertices;
	

	public MyLabelling(int id) {
		this.id = id;
		InLabels= new LinkedHashSet<MyVertex>();
		OutLabels = new LinkedHashSet<MyVertex>();
		UndecLabels = new LinkedHashSet<MyVertex>();
		setNotLabelledVertices(new LinkedHashSet<MyVertex>());
	}

	@Override
	public LinkedHashSet<MyVertex> getInVertices() {
		return InLabels;
	}

	@Override
	public boolean addInVertex(MyVertex v) {
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
		return true;
	}
	
	public boolean deleteFromInVertices(MyVertex v){
		if(!(this.InLabels.contains(v))){
			throw new IllegalArgumentException("This vertex is not in the in hashset");
		}
		else{
			InLabels.remove(v);
			v.setLabel("NONE");
		}
		return true;
	}
	
	public void setInVerties(LinkedHashSet<MyVertex> h1){
		Iterator<MyVertex> I = h1.iterator();
		while(I.hasNext()){
			I.next().setLabel("IN");
		}
		InLabels=h1;
			}

	
	@Override
	public LinkedHashSet<MyVertex> getOutVertices() {
		return  OutLabels;
	}

	@Override
	public boolean addOutVertex(MyVertex v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled in");
		}
		if(this.UndecLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled undec");
		}
		else{v.setLabel("OUT");
		OutLabels.add(v);
		return true;
		}
		
	}
	public void setOutVertices(LinkedHashSet<MyVertex> h1){
		Iterator<MyVertex> I = h1.iterator();
		while(I.hasNext()){
			I.next().setLabel("OUT");
		}
		OutLabels=h1;
			}
	
	public boolean deleteFromOutVertices(MyVertex v){
		if(!(this.OutLabels.contains(v))){
			throw  new IllegalArgumentException("This vertex is not in the out hashset");
		}
		else{
			NotLabelledVertices.add(v);
			OutLabels.remove(v);
		}
		return true;
	}

	@Override
	public LinkedHashSet<MyVertex> getUndecVertices() {
		return UndecLabels;
	}

	@Override
	public boolean addUndecVertex(MyVertex v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled in");
		}
		if(this.OutLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled out");
		}
		v.setLabel("UNDEC");
		UndecLabels.add(v);
		return true;
	}
	
	public boolean deleteFromUndecVertices(MyVertex v){
		if(!(this.UndecLabels.contains(v))){
			throw new IllegalArgumentException("This vertex is not in the undec hashset");
		}
		else{
			UndecLabels.remove(v);
		}
		return true;
	}

	public void setUndecVertices(LinkedHashSet<MyVertex> h1){
		Iterator<MyVertex> I = h1.iterator();
		while(I.hasNext()){
			I.next().setLabel("UNDEC");
		}
		UndecLabels=h1;
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
	    return (InLabels.containsAll(label.InLabels) && OutLabels.containsAll(label.OutLabels) && UndecLabels.containsAll(label.UndecLabels) && id==label.id);
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

public LinkedHashSet<MyVertex> getNotLabelledVertices() {
	return NotLabelledVertices;
}

public void setNotLabelledVertices(LinkedHashSet<MyVertex> notLabelledVertices) {
	Iterator<MyVertex> I = notLabelledVertices.iterator();
	while(I.hasNext()){
		I.next().setLabel("NONE");
	}
	NotLabelledVertices=notLabelledVertices;
}

public String DisplayLabelling(){
	String display ="In labelled arguments:  "+InLabels.toString()+"\n Out labelled arguments:  "+OutLabels.toString()+"\n Undecided Labelled arguments: "+UndecLabels.toString();
	return display;
}




}

