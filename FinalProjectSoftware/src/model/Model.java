package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import net.miginfocom.layout.LC;
import core.*;

public class Model extends java.util.Observable {	
	
	public MyGraph ModelGraph;
//	DefaultVisualizationModel<MyVertex, MyEdge> vm;
	public MyVertex v1;
	public MyVertex v2;
	public HashSet<MyVertex> NotLabelledVertices;

	public Model(){
		 ModelGraph = new MyGraph();	

		System.out.println("Model()");	
//		CircleLayout<MyVertex, MyEdge> layout = new CircleLayout<MyVertex, MyEdge>(ModelGraph);
//		 vm = new DefaultVisualizationModel<MyVertex, MyEdge>(layout);
//		 PickedState<MyVertex> ViewPickedState= vm.getPickedVertexState();
//		MyVertex v1= new MyVertex();
//		MyVertex v2= new MyVertex();
	} 
	public MyVertex addVertex() {
		MyVertex v =this.ModelGraph.addMyVertex();
		System.out.println("Model init: counter = " + ModelGraph.toString());
		setChanged();
		notifyObservers(ModelGraph);
		return v;
	}
	
	public void deleteVertex() {
		
		System.out.println("Vertex Deleted-- needs implementing");
		setChanged();
		notifyObservers(ModelGraph);
		
	}
	
	public MyEdge addEdge(MyVertex v){
		if(v1 ==null){
			v1=v;
			System.out.println("I have stored the from vertex as "+ v);
			return null;
		}
		else if(!(v1==null)){
			v2=v;
			System.out.println("I have stored the to vertex as "+ v);
			MyEdge e =ModelGraph.addMyEdge(v1, v2);
			System.out.println("Model init: counter = " + ModelGraph.toString());
			setChanged();
			notifyObservers(ModelGraph);
			v1=null;
			v2=null;
			return e;
		}
		return null;
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS
	
	
	public ArrayList<MyVertex> findUnattackedVertices(){
		ArrayList<MyVertex> UA = new ArrayList<MyVertex>();
		ArrayList<MyVertex> VertexList = new ArrayList<MyVertex>(this.ModelGraph.getMyVertices());
		System.out.println(""+VertexList.size());
		for (int i=0; i<VertexList.size();i++){
			System.out.println("iteration "+(i+1));
			MyVertex currentvertex = VertexList.get(i);
			System.out.println(currentvertex.toString());
			if(this.ModelGraph.getmygraph().getPredecessors(currentvertex).isEmpty()){
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
		HashSet<MyVertex> vertexList =  new HashSet<MyVertex>(this.ModelGraph.getMyVertices());
		Iterator<MyVertex> vertexIterator = vertexList.iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(this.ModelGraph.getmygraph().getPredecessors(currentVertex).isEmpty()){
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
		MyLabelling L1= new MyLabelling(1);
		L1.setNotLabelledVertices(new HashSet<MyVertex>(this.ModelGraph.getMyVertices()));
		Iterator<MyVertex> vertexIterator = L1.getNotLabelledVertices().iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(this.ModelGraph.getmygraph().getPredecessors(currentVertex).isEmpty()){
				System.out.print("The are no predecessors of vertex "+currentVertex.toString()+"\n");
				UA.add(currentVertex);
				
			}else{
				System.out.println(currentVertex.toString()+" has predecessors");
			}
		}
		L1.getNotLabelledVertices().removeAll(UA);
		System.out.println(" The Unattacked edges areUA); "+UA);
		L1.setInVerties(UA);
		Iterator<MyVertex> NotLabelledIterator = L1.getNotLabelledVertices().iterator();
		while(NotLabelledIterator.hasNext()){
			MyVertex v= NotLabelledIterator.next();
			HashSet<MyVertex> v1Attackers = new HashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v));
			HashSet<MyVertex> intsection = new HashSet<MyVertex>(v1Attackers);
			intsection.retainAll(L1.getInVertices());
			if(!(intsection.isEmpty())){
				L1.addOutVertex(v);
			}
			
		}
		L1.getNotLabelledVertices().removeAll(L1.getOutVertices());
		return L1;
	}
	public MyLabelling GroundedLabelling(){
		MyLabelling LPrevious = new MyLabelling(0);
		MyLabelling LCurrent= new MyLabelling(0);
		LPrevious=this.findL1();
		System.out.println("L1/unattacked is: " +LPrevious.toString());
		while(!(LPrevious==LCurrent)){
			LCurrent=LPrevious;
			Iterator<MyVertex> InNotLabelledIterator = LPrevious.getNotLabelledVertices().iterator();
			while(InNotLabelledIterator.hasNext()){
				MyVertex va= InNotLabelledIterator.next();
				System.out.println("va is: " +va.toString());
				HashSet<MyVertex> vaAttackers = new HashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(va));
				HashSet<MyVertex> TempVaAttackers= new HashSet<MyVertex>(vaAttackers);
				System.out.println("Va attacker contains: " +vaAttackers);
				Iterator<MyVertex> AttackersIterator = vaAttackers.iterator();
				while(AttackersIterator.hasNext()){
					MyVertex vb = AttackersIterator.next();
					System.out.println("vb is :" +vb.toString());
					HashSet<MyVertex> h = new HashSet<MyVertex>();
					if(vb.isOut()){
						h.add(vb);
						System.out.println("h contains"+h);
						AttackersIterator.remove();
					}	
					System.out.println("Now TempVattackers contains" +TempVaAttackers +" and h contains" + h);
				if(TempVaAttackers.equals(h)){
					System.out.println(LCurrent.addInVertex(va));
					InNotLabelledIterator.remove();
				}
			}
				LPrevious.getNotLabelledVertices().removeAll(LCurrent.getInVertices());
			}
			Iterator<MyVertex> OutNotLabelledIterator = LPrevious.getNotLabelledVertices().iterator();
			while(OutNotLabelledIterator.hasNext()){
				MyVertex v1= OutNotLabelledIterator.next();
				HashSet<MyVertex> v1Attackers = new HashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
				HashSet<MyVertex> intsection = new HashSet<MyVertex>(v1Attackers);
				intsection.retainAll(LPrevious.getInVertices());
				if(!(intsection.isEmpty())){
					LCurrent.addOutVertex(v1);
				}
				
			}
			System.out.println("Current status of the labelling: "+ LCurrent.toString() +"Previous labelling state"+ LPrevious.toString());
		}
		LPrevious.getNotLabelledVertices().removeAll(LPrevious.getOutVertices());
		LCurrent.setUndecVertices(LPrevious.getNotLabelledVertices());
		setChanged();
		notifyObservers(ModelGraph);
		return LCurrent;
		
	}
	
	public HashSet<MyVertex> resetLabels(){
		HashSet<MyVertex> vertices = new HashSet<MyVertex>(this.ModelGraph.getMyVertices());
		HashSet<MyVertex> tempVertices = vertices;
		Iterator< MyVertex> tempVerticesIterator = tempVertices.iterator();
		while(tempVerticesIterator.hasNext()){
			tempVerticesIterator.next().setLabel("NONE");
		}
		return tempVertices;
	}
	
	
	public HashSet<MyVertex> getGroundedExtension(){
		return this.GroundedLabelling().getInVertices();
	}
	
	public boolean isLegallyOut(MyVertex v1){
		if(v1.isIn()||v1.isUndec()){
			return false;
		}
		HashSet<MyVertex> attackers = new HashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
		System.out.println("attackers "+v1+attackers.toString());
		Iterator<MyVertex> attackersIterator = attackers.iterator();
		int count =0;
		while(attackersIterator.hasNext()){
			MyVertex tempVertex = attackersIterator.next();
			if(tempVertex.isIn()){
				count++;
			}
			if(count>=1){
				return true;
			}
		}
		return false;
	}
	
	//Caution before using the following function ensure that there are no vertices with a label "NONE"
	public boolean isLegallyUndec(MyVertex v1){
		if(v1.isIn()||v1.isOut()){
			return false;
		}
		HashSet<MyVertex> attackers = new HashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
		System.out.println("attackers "+v1+attackers.toString());
		int tempCount=0;
		Iterator<MyVertex> attackersIterator = attackers.iterator();
		while(attackersIterator.hasNext()){
			MyVertex tempVertex = attackersIterator.next();
			if(tempVertex.isOut()){
				tempCount++;
			}
		}
			if(tempCount<attackers.size()){
				while(attackersIterator.hasNext()){
					MyVertex tempVertex1 = attackersIterator.next();
					if(tempVertex1.isIn()){
						return false;
					}
					else{
						return true;
					}
				}
			}
		
		return false;
	}
	
	public boolean isLegallyIn(MyVertex v1){
		if(v1.isUndec()||v1.isOut()){
			return false;
	}
		HashSet<MyVertex> attackers = new HashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
		System.out.println("attackers "+v1+attackers.toString());
		Iterator<MyVertex> attackersIterator = attackers.iterator();
		int tempCount1=0;
		while(attackersIterator.hasNext()){
			MyVertex tempVertex = attackersIterator.next();
			if(tempVertex.isOut()){
				tempCount1++;
			}
		}
			System.out.println(tempCount1+attackers.size()+"");
			if(tempCount1==attackers.size()){
				return true;
			}
		
				return false;
	}
	
	public MyLabelling AdmissibleLabelling(){
		HashSet<MyVertex> vertices = new HashSet<MyVertex>(this.ModelGraph.getmygraph().getVertices());
		HashSet<MyVertex> illegOutVerticesCheck;
		MyLabelling l1 = new MyLabelling(0);
		MyVertex currentVertex;
		l1.setInVerties(vertices);
	    System.out.println("l1 in vertices are" +l1.getInVertices().toString());
	    HashSet<MyVertex> tempInVertices = new HashSet<MyVertex>(l1.getInVertices());
		Iterator<MyVertex> verticesIterator= tempInVertices.iterator();
		while(verticesIterator.hasNext()){
			currentVertex = verticesIterator.next();
			System.out.print("current Vertex is: "+currentVertex.toString());
			System.out.println("l1 in vertices are" +l1.getInVertices().toString());
			verticesIterator.remove();
			if(!(this.isLegallyIn(currentVertex))){
				System.out.println("If 1 entered");
				System.out.println("l1 in vertices are" +l1.getInVertices().toString());
				l1.deleteFromInVertices(currentVertex);
				l1.addOutVertex(currentVertex);
				illegOutVerticesCheck= new HashSet<MyVertex>(this.ModelGraph.getmygraph().getSuccessors(currentVertex));
				illegOutVerticesCheck.add(currentVertex);
				Iterator<MyVertex> illegalIterator =illegOutVerticesCheck.iterator();
				while(illegalIterator.hasNext()){
					MyVertex v = illegalIterator.next();
					if(v.isOut()){
						if(!(this.isLegallyOut(v))){
							System.out.println("If 2 entered");
							l1.deleteFromOutVertices(v);
							l1.addUndecVertex(v);
						}
					}
				}
			}
		}
		setChanged();
		notifyObservers(ModelGraph);
		return l1;
		
	}
} 