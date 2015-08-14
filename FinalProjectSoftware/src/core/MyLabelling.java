package core;



import java.util.Iterator;
import java.util.LinkedHashSet;

public class MyLabelling implements IMyLabelling {
	
	int id;
	LinkedHashSet<MyArgument> InLabels;
	LinkedHashSet<MyArgument> OutLabels;
	LinkedHashSet<MyArgument> UndecLabels;
	LinkedHashSet<MyArgument> NotLabelledVertices;
	

	public MyLabelling(int id) {
		this.id = id;
		InLabels= new LinkedHashSet<MyArgument>();
		OutLabels = new LinkedHashSet<MyArgument>();
		UndecLabels = new LinkedHashSet<MyArgument>();
		NotLabelledVertices= new LinkedHashSet<MyArgument>();
	}
	
//	public MyLabelling(int id, LinkedHashSet<MyVertex> inVertices,LinkedHashSet<MyVertex> outVertices,LinkedHashSet<MyVertex> UndecVertices){
//		this.id=id;
//		this.InLabels=inVertices;
//		this.OutLabels=outVertices;
//		this.UndecLabels=UndecVertices;	
//		this.NotLabelledVertices= new LinkedHashSet<MyVertex>();
//	}

	@Override
	public LinkedHashSet<MyArgument> getInVertices() {
		return InLabels;
	}

	@Override
	public boolean addInVertex(MyArgument v) {
		if(this.OutLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled out");
		}
		else if(this.UndecLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled undec");
		}
		else{
			v.setLabel("IN");
			InLabels.add(v);
			if(this.NotLabelledVertices.contains(v)){
				NotLabelledVertices.remove(v);
			}
			
		}
		return true;
	}
	
	public boolean deleteFromInVertices(MyArgument v){
		if(!(this.InLabels.contains(v))){
			throw new IllegalArgumentException("This vertex is not in the in hashset");
		}
		else{
			NotLabelledVertices.add(v);
			InLabels.remove(v);
			v.setLabel("NONE");
		}
		return true;
	}
	
	public void setInVerties(LinkedHashSet<MyArgument> h1){
		Iterator<MyArgument> I = h1.iterator();
		while(I.hasNext()){
			MyArgument v =I.next();
			v.setLabel("IN");
			NotLabelledVertices.remove(v);
		}
		InLabels=h1;
		NotLabelledVertices.removeAll(h1);
			}

	
	@Override
	public LinkedHashSet<MyArgument> getOutVertices() {
		return  OutLabels;
	}

	@Override
	public boolean addOutVertex(MyArgument v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled in");
		}
		if(this.UndecLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled undec");
		}
		else{v.setLabel("OUT");
		OutLabels.add(v);
		if(this.NotLabelledVertices.contains(v)){
			NotLabelledVertices.remove(v);
		}
		
		return true;
		}
		
	}
	public void setOutVertices(LinkedHashSet<MyArgument> h1){
		Iterator<MyArgument> I = h1.iterator();
		while(I.hasNext()){
			MyArgument v =I.next();
			v.setLabel("OUT");
		}
		OutLabels=h1;
		NotLabelledVertices.removeAll(h1);
			}
	
	public boolean deleteFromOutVertices(MyArgument v){
		if(!(this.OutLabels.contains(v))){
			throw  new IllegalArgumentException("This vertex is not in the out hashset");
		}
		else{
			v.setLabel("NONE");
			NotLabelledVertices.add(v);
			OutLabels.remove(v);
		}
		return true;
	}

	@Override
	public LinkedHashSet<MyArgument> getUndecVertices() {
		return UndecLabels;
	}

	@Override
	public boolean addUndecVertex(MyArgument v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled in");
		}
		if(this.OutLabels.contains(v)){
			throw new IllegalArgumentException("This vertex is already labelled out");
		}
		v.setLabel("UNDEC");
		UndecLabels.add(v);
		if(this.NotLabelledVertices.contains(v)){
			NotLabelledVertices.remove(v);
		}
		
		return true;
	}
	
	public boolean deleteFromUndecVertices(MyArgument v){
		if(!(this.UndecLabels.contains(v))){
			throw new IllegalArgumentException("This vertex is not in the undec hashset");
		}
		else{
			NotLabelledVertices.add(v);
			v.setLabel("NONE");
			UndecLabels.remove(v);
			NotLabelledVertices.remove(v);
		}
		return true;
	}

	public void setUndecVertices(LinkedHashSet<MyArgument> h1){
		Iterator<MyArgument> I = h1.iterator();
		while(I.hasNext()){
			MyArgument v =I.next();
			v.setLabel("UNDEC");
			UndecLabels.add(v);
			NotLabelledVertices.remove(v);
		}
		UndecLabels=h1;
		NotLabelledVertices.removeAll(h1);
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
	public boolean findMyVertex(MyArgument v, LinkedHashSet<MyArgument> set){
		LinkedHashSet<MyArgument> vertices = set;
		Iterator<MyArgument> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			MyArgument currentvertex = verticesIterator.next();
			if(currentvertex.equals(v)){
				return true;
			}
		}
		return false;
	}
	public boolean containsAllVertices(LinkedHashSet<MyArgument> set, LinkedHashSet<MyArgument> set1){
		Iterator<MyArgument> setIterator = set.iterator();
		// This counts the number of vertices in the collection that are the same as in the graph.
		int sameVertexCount=0;
		while(setIterator.hasNext()){
			MyArgument currentVertex = setIterator.next();
			if(this.findMyVertex(currentVertex,set1)){
				sameVertexCount++;
			}
		}
		if(sameVertexCount==set.size()){
			return true;
		}
		return false;
		}
		
	@Override
	public boolean equals(Object label2) {
	    if (label2 == null) return false;
	    if (getClass() != label2.getClass()) return false;
	    MyLabelling label = (MyLabelling)label2;
	    return (label.containsAllVertices(label.InLabels,this.InLabels) && label.containsAllVertices(label.OutLabels, this.OutLabels) && label.containsAllVertices(label.UndecLabels, this.UndecLabels) && label.containsAllVertices(label.NotLabelledVertices, this.NotLabelledVertices));
	  }
	

public boolean contains(MyArgument v){
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

public LinkedHashSet<MyArgument> getNotLabelledVertices() {
	return NotLabelledVertices;
}

public void setNotLabelledVertices(LinkedHashSet<MyArgument> notLabelledVertices) {
	Iterator<MyArgument> I = notLabelledVertices.iterator();
	while(I.hasNext()){
		I.next().setLabel("NONE");
	}
	NotLabelledVertices=notLabelledVertices;
}

public String DisplayLabelling(){
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
	String display ="In labelled arguments:  "+inString+"\n Out labelled arguments:  "+outString+"\n Undecided labelled arguments: "+undecString;
	return display;
}

public boolean checkAllLabels(){
	int inCount=0;
	Iterator<MyArgument> inIterator = InLabels.iterator();
	while(inIterator.hasNext()){
		if(inIterator.next().getLabel()=="IN"){
			inCount++;
			
		}
	}
	if(inCount==InLabels.size()){
		int outCount=0;
		Iterator<MyArgument> outIterator = OutLabels.iterator();
		while(outIterator.hasNext()){
			if(outIterator.next().getLabel()=="OUT"){
				outCount++;
			}
		}
			if(outCount==OutLabels.size()){
				int undecCount=0;
				Iterator<MyArgument> undecIterator = UndecLabels.iterator();
				while(undecIterator.hasNext()){
					if(undecIterator.next().getLabel()=="UNDEC"){
						undecCount++;
					}
				}
					if(undecCount==UndecLabels.size()){
						int notCount=0;
						Iterator<MyArgument> notIterator = NotLabelledVertices.iterator();
						while(notIterator.hasNext()){
							if(notIterator.next().getLabel()=="NONE"){
								notCount++;
							}
						}
							if(notCount==NotLabelledVertices.size()){
								return true;
							}
						
					}
				
			}
	}
		
	return false;
}

public void correctLabels(){
	this.setInVerties(InLabels);
	this.setOutVertices(OutLabels);
	this.setUndecVertices(UndecLabels);
	this.setNotLabelledVertices(NotLabelledVertices);
}

public void makecopy(MyLabelling labelling){
	this.setInVerties(new LinkedHashSet<MyArgument>(labelling.getInVertices()));
	this.setOutVertices(new LinkedHashSet<MyArgument>(labelling.getOutVertices()));
	this.setUndecVertices(new LinkedHashSet<MyArgument>(labelling.getUndecVertices()));
	this.setNotLabelledVertices(new LinkedHashSet<MyArgument>(labelling.getNotLabelledVertices()));
}

}

