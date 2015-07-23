package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Collections2;
import com.google.common.collect.Collections2.*;

import org.omg.CORBA.CurrentHolder;

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
	public void resetMyGraph(){
		MyGraph g= new MyGraph();
		this.ModelGraph=g;
		System.out.println(ModelGraph.toString());
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
		HashSet<MyVertex> UA = new LinkedHashSet<MyVertex>();
		LinkedHashSet<MyVertex> vertexList =  new LinkedHashSet<MyVertex>(this.ModelGraph.getMyVertices());
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
		LinkedHashSet<MyVertex> UA = new LinkedHashSet<MyVertex>();
		MyLabelling L1= new MyLabelling(1);
		L1.setNotLabelledVertices(new LinkedHashSet<MyVertex>(this.ModelGraph.getMyVertices()));
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
			LinkedHashSet<MyVertex> v1Attackers = new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v));
			LinkedHashSet<MyVertex> intsection = new LinkedHashSet<MyVertex>(v1Attackers);
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
				LinkedHashSet<MyVertex> vaAttackers = new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(va));
				LinkedHashSet<MyVertex> TempVaAttackers= new LinkedHashSet<MyVertex>(vaAttackers);
				System.out.println("Va attacker contains: " +vaAttackers);
				Iterator<MyVertex> AttackersIterator = vaAttackers.iterator();
				while(AttackersIterator.hasNext()){
					MyVertex vb = AttackersIterator.next();
					System.out.println("vb is :" +vb.toString());
					LinkedHashSet<MyVertex> h = new LinkedHashSet<MyVertex>();
					if(vb.isOut()){
						h.add(vb);
						System.out.println("h contains"+h);
						AttackersIterator.remove();
					}	
					System.out.println("Now TempVattackers contains" +TempVaAttackers +" and h contains" + h);
				if(TempVaAttackers.equals(h)){
					System.out.print(va.toString()+va.getLabel());
					LCurrent.addInVertex(va);
					System.out.println(LCurrent.addInVertex(va));
					InNotLabelledIterator.remove();
				}
			}
				LPrevious.getNotLabelledVertices().removeAll(LCurrent.getInVertices());
			}
			Iterator<MyVertex> OutNotLabelledIterator = LPrevious.getNotLabelledVertices().iterator();
			while(OutNotLabelledIterator.hasNext()){
				MyVertex v1= OutNotLabelledIterator.next();
				LinkedHashSet<MyVertex> v1Attackers = new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
				LinkedHashSet<MyVertex> intsection = new LinkedHashSet<MyVertex>(v1Attackers);
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
		System.out.println(LCurrent.getInVertices());
		System.out.println(LCurrent.toString());
		return LCurrent;
		
	}
	
	public LinkedHashSet<MyVertex> resetLabels(){
		LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>(this.ModelGraph.getMyVertices());
		LinkedHashSet<MyVertex> tempVertices = vertices;
		Iterator< MyVertex> tempVerticesIterator = tempVertices.iterator();
		while(tempVerticesIterator.hasNext()){
			tempVerticesIterator.next().setLabel("NONE");
		}
		return tempVertices;
	}
	
	
	public LinkedHashSet<MyVertex> getGroundedExtension(){
		return this.GroundedLabelling().getInVertices();
	}
	
	public boolean isLegallyOut(MyVertex v1){
		if(v1.isIn()||v1.isUndec()){
			return false;
		}
		LinkedHashSet<MyVertex> attackers = new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
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
		LinkedHashSet<MyVertex> attackers = new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
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
		LinkedHashSet<MyVertex> attackers = new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getPredecessors(v1));
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
	public boolean hasillegallyIn(MyLabelling L){
		LinkedHashSet<MyVertex> h = L.getInVertices();
		LinkedHashSet<MyVertex> h1 = h;
		Iterator<MyVertex> I = h1.iterator();
		while(I.hasNext()){
			MyVertex v= I.next();
//			I.remove();
			if(!(this.isLegallyIn(v))){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasillegallyIn(LinkedHashSet<MyLabelling> L){
		Iterator<MyLabelling> LIterator = L.iterator();
		while(LIterator.hasNext()){
			MyLabelling tempLabelling = LIterator.next();
			if(hasillegallyIn(tempLabelling)){
				return true;
			}
			
		}
		return false;
	}
	
	public MyLabelling transitionStep(MyLabelling L){
		  LinkedHashSet<MyVertex> tempInVertices = new LinkedHashSet<MyVertex>(L.getInVertices());
		  System.out.println("(Temp in) current labelling in vertices are: " +tempInVertices.toString());
			Iterator<MyVertex> verticesIterator= tempInVertices.iterator();
			MyVertex currentVertex;
			while(verticesIterator.hasNext()){
				currentVertex = verticesIterator.next();
				System.out.print("current Vertex is: "+currentVertex.toString());
				System.out.println("l1 in vertices are" +L.getInVertices().toString());
				verticesIterator.remove();
				if(!(this.isLegallyIn(currentVertex))){
					System.out.println("If 1 entered");
					System.out.println("l1 in vertices are" +L.getInVertices().toString());
					L.deleteFromInVertices(currentVertex);
					L.addOutVertex(currentVertex);
					LinkedHashSet<MyVertex> illegOutVerticesCheck= new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getSuccessors(currentVertex));
					illegOutVerticesCheck.add(currentVertex);
					Iterator<MyVertex> illegalIterator =illegOutVerticesCheck.iterator();
					while(illegalIterator.hasNext()){
						MyVertex v = illegalIterator.next();
						if(v.isOut()){
							if(!(this.isLegallyOut(v))){
								System.out.println("If 2 entered");
								L.deleteFromOutVertices(v);
								L.addUndecVertex(v);
							}
						}
					}
				}
			}
			return L;
			
		}
	
	public MyLabelling AdmissibleLabelling(){
		LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>(this.ModelGraph.getMyVertices());
		System.out.println("vertices in the graph are: "+vertices);
		MyLabelling currentLabelling = new MyLabelling(0);
		currentLabelling.setInVerties(vertices);
		System.out.println("current labelling in vertices are: " +currentLabelling.getInVertices().toString());
		while(this.hasillegallyIn(currentLabelling)){
			currentLabelling=transitionStep(currentLabelling);
		}
//	    System.out.println("l1 in vertices are" +l1.getInVertices().toString());
//	    LinkedHashSet<MyVertex> tempInVertices = new LinkedHashSet<MyVertex>(l1.getInVertices());
//		Iterator<MyVertex> verticesIterator= tempInVertices.iterator();
//		while(verticesIterator.hasNext()){
//			currentVertex = verticesIterator.next();
//			System.out.print("current Vertex is: "+currentVertex.toString());
//			System.out.println("l1 in vertices are" +l1.getInVertices().toString());
//			verticesIterator.remove();
//			if(!(this.isLegallyIn(currentVertex))){
//				System.out.println("If 1 entered");
//				System.out.println("l1 in vertices are" +l1.getInVertices().toString());
//				l1.deleteFromInVertices(currentVertex);
//				l1.addOutVertex(currentVertex);
//				illegOutVerticesCheck= new LinkedHashSet<MyVertex>(this.ModelGraph.getmygraph().getSuccessors(currentVertex));
//				illegOutVerticesCheck.add(currentVertex);
//				Iterator<MyVertex> illegalIterator =illegOutVerticesCheck.iterator();
//				while(illegalIterator.hasNext()){
//					MyVertex v = illegalIterator.next();
//					if(v.isOut()){
//						if(!(this.isLegallyOut(v))){
//							System.out.println("If 2 entered");
//							l1.deleteFromOutVertices(v);
//							l1.addUndecVertex(v);
//						}
//					}
//				}
//			}
//		}
		setChanged();
		notifyObservers(ModelGraph);
		System.out.println("current labelling in vertices are: " +currentLabelling.getInVertices().toString());
		return currentLabelling;
		
	}
	
	public void deleteMyVertex(MyVertex deleteVertex) {
		int vertexId=deleteVertex.getId();
		LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>(ModelGraph.getMyVertices());
		//hashet to add all vertices with a id larger than the id of the vertex we are deleting.
		LinkedHashSet<MyVertex> afterDeleteVertex = new LinkedHashSet<MyVertex>();
		Iterator<MyVertex> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			MyVertex v= verticesIterator.next();
			if(v.getId()>vertexId){
				afterDeleteVertex.add(v);
			}
		}
		// now iterate through the newer vertices and reduce their id by one.
		Iterator<MyVertex> afterDeleteVertexIterator = afterDeleteVertex.iterator();
		while(afterDeleteVertexIterator.hasNext()){
			MyVertex v1= afterDeleteVertexIterator.next();
			v1.setId(v1.getId()-1);
		}
		HashSet<MyEdge> edges = new HashSet<MyEdge>(this.ModelGraph.getMyEdges());
		Iterator<MyEdge> edgesiterator = edges.iterator();
		while(edgesiterator.hasNext()){
			MyEdge e = edgesiterator.next();
			e.updateLabel();
		}
		//remove the vertex to be deleted
		this.ModelGraph.myGraph.removeVertex(deleteVertex);
		// reduce the vertex count by 1. This stops new vertices having a id one higher than they should.
		int newVertexCount= this.ModelGraph.GetMyVertexCount() -1;
		this.ModelGraph.setMyVertexCount(newVertexCount);
		//Notify the view that the model has changed. 
		setChanged();
		notifyObservers(ModelGraph);
		
	}
	public void deleteMyEdge(MyEdge deleteEdge){
		System.out.println("Old Edge count is"+ModelGraph.GetMyEdgeCount());
		int newEdgeCount= this.ModelGraph.GetMyEdgeCount()-1;
		this.ModelGraph.setMyEdgeCount(newEdgeCount);
		this.ModelGraph.myGraph.removeEdge(deleteEdge);
		setChanged();
		notifyObservers(ModelGraph);
	}
	
	public LinkedHashSet<MyVertex> reorderSet(LinkedHashSet<MyVertex> h){
		Iterator<MyVertex> iterateVertices = h.iterator();
		System.out.println("h is: "+ h.toString());
			MyVertex current =iterateVertices.next();
			iterateVertices.remove();
			LinkedHashSet<MyVertex> h1= h;
			h1.add(current);
			System.out.println("h1 is: "+ h1.toString());
			return h1;
	}
	
	/**
	 * This method swaps the a[i] and a[j] elements in an ArrayList.
	 * @param list An array of elements 
	 * @param i	The first index to swap
	 * @param j The second index to swap
	 * @return a list with the a[i] and a[j] elements swapped.
	 */
	static ArrayList<MyVertex> swap(ArrayList<MyVertex> list, int i , int j){
		MyVertex temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		return list;
		
	}
	
	/**
	 * This methods permutates all the elements in an ArrayList. 
	 * It permutates the elements starting from the kth. 
	 * @param currentCombination list to be permutated.
	 * @param possibleCombinations collection to store the list into.
	 * @param k the index that the permutation should start at.
	 * @return
	 */
    static Collection<ArrayList<MyVertex>> Permute(ArrayList<MyVertex> currentCombination, Collection<ArrayList<MyVertex>> possibleCombinations,int k){
    	if(k>currentCombination.size()){
    		throw new IllegalArgumentException("The index is large than the number of elements in the list");
    	}
    	else{
        for(int i = k; i < currentCombination.size(); i++){
            currentCombination=swap(currentCombination, i, k);
            possibleCombinations=Permute(currentCombination,possibleCombinations, k+1);
            currentCombination=swap(currentCombination, k, i);
        }
        if (k == currentCombination.size() -1){
            System.out.println(java.util.Arrays.toString(currentCombination.toArray())+currentCombination);
            System.out.println("added");
           ArrayList<MyVertex> combination = new ArrayList<MyVertex>(currentCombination);
           possibleCombinations.add(combination);
		}
    	}
//        System.out.println("Possible Combinatiosns are:"+possibleCombinations);
        return possibleCombinations;
    }
   
    
    /**
     * This method checks whether a set already contains a labelling
     * @param setOfLabelling A set of labellings. 
     * @param labelling The labelling to be checked.
     */
    public boolean alreadyContains(HashSet<MyLabelling> setOfLabellings, MyLabelling labelling){
    	Iterator<MyLabelling > setOfLabellingsIterator = setOfLabellings.iterator();
    	while(setOfLabellingsIterator.hasNext()){
    		MyLabelling tempLabellings = setOfLabellingsIterator.next();
    		if(tempLabellings.equals(labelling)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public HashSet<MyLabelling> allAdmissibleLabellings(){
    	LinkedHashSet<MyLabelling> admissibleLabellings= new LinkedHashSet<MyLabelling>();
    	LinkedHashSet<MyVertex> graphVertices = new LinkedHashSet<MyVertex>(ModelGraph.getMyVertices());
    	ArrayList<MyVertex> vertices = new ArrayList<MyVertex>();
    	vertices.addAll(graphVertices);
    	LinkedHashSet<ArrayList<MyVertex>> possibleCombinations = new LinkedHashSet<ArrayList<MyVertex>>();
    	MyVertex currentVertex = new MyVertex(0);
    	/*This method finds all possible combinations of the vertices. Each combination must be checked in order to find all labellings. 
    	 * Some combinations will find the same labelling. Duplicates are not added.
    	 * */
    	Permute(vertices, possibleCombinations, 0);
    	Iterator<ArrayList<MyVertex>> possibleCombinationsIterator = possibleCombinations.iterator();
    	while(possibleCombinationsIterator.hasNext()){
    		MyLabelling currentLabelling = new MyLabelling(0);
    		LinkedHashSet<MyVertex> VertexOrder = new LinkedHashSet<MyVertex>(possibleCombinationsIterator.next());
    		LinkedHashSet<MyVertex> currentVertexOrder = new LinkedHashSet<MyVertex>();
    		currentVertexOrder.addAll(VertexOrder);
    		currentLabelling.setInVerties(currentVertexOrder);
    		while(hasillegallyIn(currentLabelling)){
    			LinkedHashSet<MyVertex> tempIn = new LinkedHashSet<MyVertex>();
    			tempIn.addAll(currentLabelling.getInVertices());
    			LinkedHashSet<MyVertex> tempOut = new LinkedHashSet<MyVertex>();
    			tempOut.addAll(currentLabelling.getOutVertices());
    			Iterator<MyVertex> inIterator = tempIn.iterator();
    			while(inIterator.hasNext()){
    				MyVertex tempv= inIterator.next();
    				currentVertex= tempv;
    				if(!(isLegallyIn(currentVertex))){
    					currentLabelling.deleteFromInVertices(currentVertex);
    					currentLabelling.addOutVertex(currentVertex);
    					LinkedHashSet<MyVertex> illegOut = new LinkedHashSet<MyVertex>();
    					illegOut.addAll(ModelGraph.myGraph.getSuccessors(currentVertex));
    					illegOut.add(currentVertex);
    					Iterator<MyVertex> illegOutIterator = illegOut.iterator();
    					while(illegOutIterator.hasNext()){
    					MyVertex v = illegOutIterator.next();
	    					if(v.isOut()){
	    						if(!(isLegallyOut(v))){
	    							currentLabelling.deleteFromOutVertices(v);
	    							currentLabelling.addUndecVertex(v);
	    						}
    						
	    					}	
    					}
    				}
    				
    			}
    		}
    		if (!(alreadyContains(admissibleLabellings, currentLabelling))){
    		admissibleLabellings.add(currentLabelling);
    		}
    	}
    	return admissibleLabellings;
    }
  
} 