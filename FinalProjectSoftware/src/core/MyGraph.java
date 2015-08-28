package core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class MyGraph extends DirectedSparseGraph<MyArg, MyAtt> implements IMyGraph {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  DirectedSparseGraph<MyArg, MyAtt> myGraph = new DirectedSparseGraph<MyArg, MyAtt>();
	int ArgCount;
	int attCount;
	CircleLayout<MyArg, MyAtt> layout;
	VisualizationViewer<MyArg, MyAtt> vv;
	
	public MyGraph(){
		this.ArgCount=0;
		this.attCount=0;
	 }

	
	public int GetMyArgCount(){
		return ArgCount;
	}
	public void setMyArgCount(int integer){
		ArgCount=integer;
		
	}
	
	public int GetMyAttCount(){
		return this.attCount;
	}
	public void setMyAttCount(int Integer){
		this.attCount=Integer;
	}
	
	public Collection<MyArg> getMyArgs(){
		return myGraph.getVertices();
	}
	
	public Collection<MyAtt> getMyAtts(){
		return myGraph.getEdges();
	}
	public DirectedSparseGraph<MyArg, MyAtt> getmygraph(){
		return myGraph;
	}
	
	@Override
	public MyArg addMyArg() {
		++ArgCount;
		MyArg v = new MyArg(ArgCount);
//		v.setId(ArgCount);
		myGraph.addVertex(v);
		return v;
	}
	
	
	public boolean containsEdge(MyAtt att){
		LinkedHashSet<MyAtt> attacks = new LinkedHashSet<MyAtt>(myGraph.getEdges());
		Iterator<MyAtt> attacksIterator = attacks.iterator();
		while(attacksIterator.hasNext()){
			MyAtt tempAttack = attacksIterator.next();
			if(tempAttack.getFrom().equals(att.getFrom())&&tempAttack.getTo().equals(att.getTo())){;
				return true;
			}
		}
		return false;
		
	}
	
	public MyAtt addMyAtt(MyArg v1, MyArg v2){
		if(!(this.getMyArgs().contains(v1)) || !(this.getMyArgs().contains(v2))){
			throw new IllegalArgumentException("Args not in graph");
		}
		

				
//		if(v1==v2){
//			throw new IllegalArgumentException("The to and from Args must be distinct");
//		}
	//create new attacks
		    ++attCount;
			MyAtt e1 = new MyAtt(attCount);
			e1.setFrom(v1);
			e1.setTo(v2);
			e1.setLabel(v1, v2);
		//check whether the MyGraph g already contains an Att e in its DirectedSparseGraph called myGraph.
		if (myGraph.containsEdge(e1)){
//			--attCount;
			throw new IllegalArgumentException("Attack already exist");
		}
		else if (myGraph.addEdge(e1, v1,v2, EdgeType.DIRECTED)){
			System.out.println("Attack added");
			return e1;
		}
		else{
			System.out.println("Attack not added");
			throw new IllegalArgumentException("Args not in graph");
		
		}
			
		}
		
	public String toString(){
		HashSet<MyArg> Args = new HashSet<MyArg>(myGraph.getVertices());
		HashSet<MyAtt> Atts = new HashSet<MyAtt>(myGraph.getEdges());
		String ArgsToString = "Arguments: "+Args.toString();
		String AttsToString = "Attacks: "+Atts.toString();
		String y =  ArgsToString+ "\n" + AttsToString+"\n" +"Arguments Count: " +ArgCount + "\n" +"Attacks Count: " +attCount;
		return y;
		}

	public DirectedSparseGraph<MyArg, MyAtt> getGraph(){
		return myGraph;
	}
	
	public CircleLayout<MyArg, MyAtt> getGraphLayout(){
		layout = new CircleLayout<MyArg,MyAtt>(myGraph);
		return layout;
	}
	public void setGraphLayout(CircleLayout<MyArg, MyAtt> layout){
		this.layout=layout;
	}
	public void setGraphVisualizationViewer(VisualizationViewer<MyArg, MyAtt> vv){
		this.vv=vv;
	}
	
	public VisualizationViewer<MyArg, MyAtt> getGraphVisualizationViewer(Layout<MyArg, MyAtt> layout){

        vv = new VisualizationViewer<MyArg, MyAtt>(layout);
        return vv;
	}
	
	public HashSet<MyArg> getNoneLabelledArgs(){
		HashSet<MyArg> noneLabelledArgs = new HashSet<MyArg>();
		HashSet<MyArg> listArg = new HashSet<MyArg>(this.getMyArgs());
		Iterator<MyArg> listArgIterator = listArg.iterator();
		while(listArgIterator.hasNext()){
			MyArg currentargument = listArgIterator.next();
			if(currentargument.hasNoLabel()){
				noneLabelledArgs.add(currentargument);
			}
			else{
				System.out.println(currentargument.toString() + "has a label" + currentargument.getLabel());
			}
		}
			System.out.print("Args Labelled none are "+noneLabelledArgs);
			return noneLabelledArgs;
		
	}
	
	public HashSet<MyArg> getInLabelledArgs(){
		HashSet<MyArg> inLabelledArgs = new HashSet<MyArg>();
		HashSet<MyArg> listArgs = new HashSet<MyArg>(this.getMyArgs());
		Iterator<MyArg> argumentIterator = listArgs.iterator();
		while(argumentIterator.hasNext()){
			MyArg currentargument = argumentIterator.next();
			if(currentargument.isIn()){
				inLabelledArgs.add(currentargument);
			}
			else{
				System.out.println(currentargument.toString() + "has a label" + currentargument.getLabel());
			}
		}
			System.out.print("Args Labelled in are "+inLabelledArgs);
			return inLabelledArgs;
		
	}
	
	
	public HashSet<MyArg> getOutLabelledArgs(){
		HashSet<MyArg> outLabelledArgs = new HashSet<MyArg>();
		HashSet<MyArg> listArgs = new HashSet<MyArg>(this.getMyArgs());
		Iterator<MyArg> argumentIterator = listArgs.iterator();
		while(argumentIterator.hasNext()){
			MyArg currentargument = argumentIterator.next();
			if(currentargument.isOut()){
				outLabelledArgs.add(currentargument);
			}
			else{
				System.out.print("Args Labelled out are "+outLabelledArgs);
				System.out.println(currentargument.toString() + "has a label" + currentargument.getLabel());
			}
		}
			return outLabelledArgs;
		
	}
	
	public HashSet<MyArg> getUndecLabelledArgs(){
		HashSet<MyArg> undecLabelledArgs = new HashSet<MyArg>();
		HashSet<MyArg> listArgs = new HashSet<MyArg>(this.getMyArgs());
		Iterator<MyArg> argumentIterator = listArgs.iterator();
		while(argumentIterator.hasNext()){
			MyArg currentargument = argumentIterator.next();
			if(currentargument.isUndec()){
				undecLabelledArgs.add(currentargument);
			}
			else{
				System.out.print("Args Labelled undec are "+undecLabelledArgs);
				System.out.println(currentargument.toString() + "has a label" + currentargument.getLabel());
			}
		}
			return undecLabelledArgs;
		
	}

	public boolean resetGraph(){
		HashSet<MyArg> Args = new HashSet<MyArg>(this.getMyArgs());
		Iterator<MyArg> ArgsIterator = Args.iterator();
		while(ArgsIterator.hasNext()){
			MyArg currentargument = ArgsIterator.next();
			this.myGraph.removeVertex(currentargument);
			ArgCount--;
		}
		if(this.GetMyArgCount()==0){
			ArgCount=0;
			attCount=0;
			return true;
		}
		return false;
	}
	
	public boolean findMyArg(MyArg v){
		LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>(this.getMyArgs());
		Iterator<MyArg> ArgsIterator = Args.iterator();
		while(ArgsIterator.hasNext()){
			MyArg currentargument = ArgsIterator.next();
			if(currentargument.equals(v)){
				return true;
			}
		}
		return false;
	}
	
	public MyArg getMyArg(MyArg v){
		LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>(this.getMyArgs());
		Iterator<MyArg> ArgsIterator = Args.iterator();
		while(ArgsIterator.hasNext()){
			MyArg currentargument = ArgsIterator.next();
			if(currentargument.equals(v)){
				return currentargument;
			}
		}
		throw new IllegalArgumentException("argument not in graph");
		
	}
	
	public boolean findMyAtt(MyAtt e){
		LinkedHashSet<MyAtt> Atts = new LinkedHashSet<MyAtt>(this.getMyAtts());
		Iterator<MyAtt> AttsIterator = Atts.iterator();
		while(AttsIterator.hasNext()){
			MyAtt currentAtt= AttsIterator.next();
			if(currentAtt.equals(e)){
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAllArgs(Collection<MyArg> collection){
		LinkedHashSet<MyArg> set = new LinkedHashSet<MyArg>(collection);
		Iterator<MyArg> setIterator = set.iterator();
		// This counts the number of Args in the collection that are the same as in the graph.
		int sameargumentCount=0;
		while(setIterator.hasNext()){
			MyArg currentargument = setIterator.next();
			if(this.findMyArg(currentargument)){
				sameargumentCount++;
			}
		}
	if(sameargumentCount==this.GetMyArgCount()){
		return true;
	}
	return false;
	}
	
	public boolean containsAllAtts(Collection<MyAtt> collection){
		LinkedHashSet<MyAtt> set = new LinkedHashSet<MyAtt>(collection);
		Iterator<MyAtt> setIterator = set.iterator();
		int sameAttCount=0;
		
		while(setIterator.hasNext()){
			MyAtt currentAtt= setIterator.next();
			if(this.findMyAtt(currentAtt)){
				sameAttCount++;
			}
		}
		
		if(sameAttCount==this.GetMyAttCount()){
			return true;
		}
		
		return false;
	}
	
	public boolean equals(Object MyGraph2) {
	    if (MyGraph2 == null) return false;
	    if (getClass() != MyGraph2.getClass()) return false;
	    MyGraph graph = (MyGraph)MyGraph2;
	    return (graph.containsAllArgs(this.getMyArgs()) && graph.containsAllAtts(this.getMyAtts()) && this.GetMyArgCount()== graph.GetMyArgCount() && this.GetMyAttCount() == graph.GetMyAttCount() );
	  }
}
