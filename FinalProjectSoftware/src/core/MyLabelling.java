package core;



import java.util.Iterator;
import java.util.LinkedHashSet;

public class MyLabelling implements IMyLabelling {
	
	int id;
	LinkedHashSet<MyArg> InLabels;
	LinkedHashSet<MyArg> OutLabels;
	LinkedHashSet<MyArg> UndecLabels;
	LinkedHashSet<MyArg> NotLabelledArgs;
	

	public MyLabelling(int id) {
		this.id = id;
		InLabels= new LinkedHashSet<MyArg>();
		OutLabels = new LinkedHashSet<MyArg>();
		UndecLabels = new LinkedHashSet<MyArg>();
		NotLabelledArgs= new LinkedHashSet<MyArg>();
	}
	
//	public MyLabelling(int id, LinkedHashSet<MyArg> inArgs,LinkedHashSet<MyArg> outArgs,LinkedHashSet<MyArg> UndecArgs){
//		this.id=id;
//		this.InLabels=inArgs;
//		this.OutLabels=outArgs;
//		this.UndecLabels=UndecArgs;	
//		this.NotLabelledArgs= new LinkedHashSet<MyArg>();
//	}

	@Override
	public LinkedHashSet<MyArg> getInArgs() {
		return InLabels;
	}

	@Override
	public boolean addInArg(MyArg v) {
		if(this.OutLabels.contains(v)){
			throw new IllegalArgumentException("This argument is already labelled out");
		}
		else if(this.UndecLabels.contains(v)){
			throw new IllegalArgumentException("This argument is already labelled undec");
		}
		else{
			v.setLabel("IN");
			InLabels.add(v);
			if(this.NotLabelledArgs.contains(v)){
				NotLabelledArgs.remove(v);
			}
			
		}
		return true;
	}
	
	public boolean deleteFromInArgs(MyArg v){
		if(!(this.InLabels.contains(v))){
			throw new IllegalArgumentException("This argument is not in the in hashset");
		}
		else{
			NotLabelledArgs.add(v);
			InLabels.remove(v);
			v.setLabel("NONE");
		}
		return true;
	}
	
	public void setInVerties(LinkedHashSet<MyArg> h1){
		Iterator<MyArg> I = h1.iterator();
		while(I.hasNext()){
			MyArg v =I.next();
			v.setLabel("IN");
			NotLabelledArgs.remove(v);
		}
		InLabels=h1;
		NotLabelledArgs.removeAll(h1);
			}

	
	@Override
	public LinkedHashSet<MyArg> getOutArgs() {
		return  OutLabels;
	}

	@Override
	public boolean addOutArg(MyArg v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This argument is already labelled in");
		}
		if(this.UndecLabels.contains(v)){
			throw new IllegalArgumentException("This argument is already labelled undec");
		}
		else{v.setLabel("OUT");
		OutLabels.add(v);
		if(this.NotLabelledArgs.contains(v)){
			NotLabelledArgs.remove(v);
		}
		
		return true;
		}
		
	}
	public void setOutArgs(LinkedHashSet<MyArg> h1){
		Iterator<MyArg> I = h1.iterator();
		while(I.hasNext()){
			MyArg v =I.next();
			v.setLabel("OUT");
		}
		OutLabels=h1;
		NotLabelledArgs.removeAll(h1);
			}
	
	public boolean deleteFromOutArgs(MyArg v){
		if(!(this.OutLabels.contains(v))){
			throw  new IllegalArgumentException("This argument is not in the out hashset");
		}
		else{
			v.setLabel("NONE");
			NotLabelledArgs.add(v);
			OutLabels.remove(v);
		}
		return true;
	}

	@Override
	public LinkedHashSet<MyArg> getUndecArgs() {
		return UndecLabels;
	}

	@Override
	public boolean addUndecArg(MyArg v) {
		if(this.InLabels.contains(v)){
			throw new IllegalArgumentException("This argument is already labelled in");
		}
		if(this.OutLabels.contains(v)){
			throw new IllegalArgumentException("This argument is already labelled out");
		}
		v.setLabel("UNDEC");
		UndecLabels.add(v);
		if(this.NotLabelledArgs.contains(v)){
			NotLabelledArgs.remove(v);
		}
		
		return true;
	}
	
	public boolean deleteFromUndecArgs(MyArg v){
		if(!(this.UndecLabels.contains(v))){
			throw new IllegalArgumentException("This argument is not in the undec hashset");
		}
		else{
			NotLabelledArgs.add(v);
			v.setLabel("NONE");
			UndecLabels.remove(v);
			NotLabelledArgs.remove(v);
		}
		return true;
	}

	public void setUndecArgs(LinkedHashSet<MyArg> h1){
		Iterator<MyArg> I = h1.iterator();
		while(I.hasNext()){
			MyArg v =I.next();
			v.setLabel("UNDEC");
			UndecLabels.add(v);
			NotLabelledArgs.remove(v);
		}
		UndecLabels=h1;
		NotLabelledArgs.removeAll(h1);
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
	public boolean findMyArg(MyArg v, LinkedHashSet<MyArg> set){
		LinkedHashSet<MyArg> Args = set;
		Iterator<MyArg> ArgsIterator = Args.iterator();
		while(ArgsIterator.hasNext()){
			MyArg currentArg = ArgsIterator.next();
			if(currentArg.equals(v)){
				return true;
			}
		}
		return false;
	}
	public boolean containsAllArgs(LinkedHashSet<MyArg> set, LinkedHashSet<MyArg> set1){
		Iterator<MyArg> setIterator = set.iterator();
		// This counts the number of Args in the collection that are the same as in the graph.
		int sameArgCount=0;
		while(setIterator.hasNext()){
			MyArg currentArg = setIterator.next();
			if(this.findMyArg(currentArg,set1)){
				sameArgCount++;
			}
		}
		if(sameArgCount==set.size()){
			return true;
		}
		return false;
		}
		
	@Override
	public boolean equals(Object label2) {
	    if (label2 == null) return false;
	    if (getClass() != label2.getClass()) return false;
	    MyLabelling label = (MyLabelling)label2;
	    return (label.containsAllArgs(label.InLabels,this.InLabels) && label.containsAllArgs(label.OutLabels, this.OutLabels) && label.containsAllArgs(label.UndecLabels, this.UndecLabels) && label.containsAllArgs(label.NotLabelledArgs, this.NotLabelledArgs));
	  }
	

public boolean contains(MyArg v){
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

public LinkedHashSet<MyArg> getNotLabelledArgs() {
	return NotLabelledArgs;
}

public void setNotLabelledArgs(LinkedHashSet<MyArg> notLabelledArgs) {
	Iterator<MyArg> I = notLabelledArgs.iterator();
	while(I.hasNext()){
		I.next().setLabel("NONE");
	}
	NotLabelledArgs=notLabelledArgs;
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
	Iterator<MyArg> inIterator = InLabels.iterator();
	while(inIterator.hasNext()){
		if(inIterator.next().getLabel()=="IN"){
			inCount++;
			
		}
	}
	if(inCount==InLabels.size()){
		int outCount=0;
		Iterator<MyArg> outIterator = OutLabels.iterator();
		while(outIterator.hasNext()){
			if(outIterator.next().getLabel()=="OUT"){
				outCount++;
			}
		}
			if(outCount==OutLabels.size()){
				int undecCount=0;
				Iterator<MyArg> undecIterator = UndecLabels.iterator();
				while(undecIterator.hasNext()){
					if(undecIterator.next().getLabel()=="UNDEC"){
						undecCount++;
					}
				}
					if(undecCount==UndecLabels.size()){
						int notCount=0;
						Iterator<MyArg> notIterator = NotLabelledArgs.iterator();
						while(notIterator.hasNext()){
							if(notIterator.next().getLabel()=="NONE"){
								notCount++;
							}
						}
							if(notCount==NotLabelledArgs.size()){
								return true;
							}
						
					}
				
			}
	}
		
	return false;
}

public void correctLabels(){
	this.setInVerties(InLabels);
	this.setOutArgs(OutLabels);
	this.setUndecArgs(UndecLabels);
	this.setNotLabelledArgs(NotLabelledArgs);
}

public void makecopy(MyLabelling labelling){
	this.setInVerties(new LinkedHashSet<MyArg>(labelling.getInArgs()));
	this.setOutArgs(new LinkedHashSet<MyArg>(labelling.getOutArgs()));
	this.setUndecArgs(new LinkedHashSet<MyArg>(labelling.getUndecArgs()));
	this.setNotLabelledArgs(new LinkedHashSet<MyArg>(labelling.getNotLabelledArgs()));
}

}

