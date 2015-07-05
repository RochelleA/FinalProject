package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import core.MyLabelling;
import core.MyVertex;

public class Semantics {

	Model m;
	public HashSet<MyVertex> NotLabelledVertices;
	
	
	
	public Semantics() {
	 m= new Model();
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS


	public ArrayList<MyVertex> findUnattackedVertices(){
		ArrayList<MyVertex> UA = new ArrayList<MyVertex>();
		ArrayList<MyVertex> VertexList = new ArrayList<MyVertex>(this.m.ModelGraph.getMyVertices());
		System.out.println(""+VertexList.size());
		for (int i=0; i<VertexList.size();i++){
			System.out.println("iteration "+(i+1));
			MyVertex currentvertex = VertexList.get(i);
			System.out.println(currentvertex.toString());
			if(this.m.ModelGraph.getmygraph().getPredecessors(currentvertex).isEmpty()){
				System.out.print("The are no predecessors of vertex "+VertexList.get(i).toString()+"\n");
				UA.add(VertexList.get(i));			
			}
			else{
				System.out.println(VertexList.get(i).toString()+" has predecessors");
			}
		}
		System.out.println(UA);
		return UA;
	}

	public HashSet<MyVertex> findUnattackedVertices1(){
		HashSet<MyVertex> UA = new HashSet<MyVertex>();
		HashSet<MyVertex> vertexList =  new HashSet<MyVertex>(this.m.ModelGraph.getMyVertices());
		Iterator<MyVertex> vertexIterator = vertexList.iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(this.m.ModelGraph.getmygraph().getPredecessors(currentVertex).isEmpty()){
System.out.print("The are no predecessors of vertex "+currentVertex.toString()+"\n");
UA.add(currentVertex);

}else{
System.out.println(currentVertex.toString()+" has predecessors");
}
}
System.out.println(UA);
return UA;
}


public MyLabelling findL1(){
HashSet<MyVertex> UA = new HashSet<MyVertex>();
NotLabelledVertices =  new HashSet<MyVertex>(this.m.ModelGraph.getMyVertices());
Iterator<MyVertex> vertexIterator = NotLabelledVertices.iterator();
while(vertexIterator.hasNext()){
MyVertex currentVertex = vertexIterator.next();
if(this.m.ModelGraph.getmygraph().getPredecessors(currentVertex).isEmpty()){
System.out.print("The are no predecessors of vertex "+currentVertex.toString()+"\n");
UA.add(currentVertex);

}else{
System.out.println(currentVertex.toString()+" has predecessors");
}
}
NotLabelledVertices.removeAll(UA);
System.out.println(UA);
MyLabelling L1= new MyLabelling(1);
L1.setInVerties(UA);
return L1;
}

public MyLabelling GroundedLabelling(){
MyLabelling L0 = new MyLabelling(0);
HashSet<MyVertex> vertexList =new HashSet<MyVertex>(this.m.ModelGraph.getMyVertices());


return null;

}
	
	
}
