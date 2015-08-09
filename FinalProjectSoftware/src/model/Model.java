package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import core.*;

public class Model extends java.util.Observable {	
	
	public MyGraph modelGraph;
//	DefaultVisualizationModel<MyVertex, MyEdge> vm;
	private  MyVertex v1;
	private MyVertex v2;
//	public HashSet<MyVertex> NotLabelledVertices;

	public MyVertex getV1() {
		return v1;
	}
	public void setV1(MyVertex v1) {
		this.v1 = v1;
	}
	public MyVertex getV2() {
		return v2;
	}
	public void setV2(MyVertex v2) {
		this.v2 = v2;
	}
	public Model(){
		 modelGraph = new MyGraph();	

		System.out.println("Model()");	
	} 
	public MyVertex addMyVertex() {
		MyVertex v =this.modelGraph.addMyVertex();
		setChanged();
		notifyObservers(modelGraph);
		return v;
	}
	
	public void deleteVertex(MyVertex deleteVertex) {
		int vertexId=deleteVertex.getId();
		LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>(modelGraph.getMyVertices());
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
		HashSet<MyEdge> edges = new HashSet<MyEdge>(this.modelGraph.getMyEdges());
		Iterator<MyEdge> edgesiterator = edges.iterator();
		while(edgesiterator.hasNext()){
			MyEdge e = edgesiterator.next();
			e.updateLabel();
		}
		//remove the vertex to be deleted
		this.modelGraph.myGraph.removeVertex(deleteVertex);
		// reduce the vertex count by 1. This stops new vertices having a id one higher than they should.
		int newVertexCount= this.modelGraph.GetMyVertexCount() -1;
		this.modelGraph.setMyVertexCount(newVertexCount);
		//Notify the view that the model has changed. 
		setChanged();
		notifyObservers(modelGraph);
		
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
			MyEdge e =modelGraph.addMyEdge(v1, v2);
			System.out.println("Model init: counter = " + modelGraph.toString());
			setChanged();
			notifyObservers(modelGraph);
			v1=null;
			v2=null;
			return e;
		}
		return null;
	}
	
	public void deleteEdge(MyEdge deleteEdge){
		System.out.println("Old Edge count is"+modelGraph.GetMyEdgeCount());
		int newEdgeCount= this.modelGraph.GetMyEdgeCount()-1;
		this.modelGraph.setMyEdgeCount(newEdgeCount);
		this.modelGraph.myGraph.removeEdge(deleteEdge);
		setChanged();
		notifyObservers(modelGraph);
	}

	public void resetMyGraph(){
		MyGraph g= new MyGraph();
		this.modelGraph=g;
		setChanged();
		notifyObservers(modelGraph);
	}
	

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS
	
	
	@SuppressWarnings("unused")
	private ArrayList<MyVertex> findUnattackedVerticesArray(){
		ArrayList<MyVertex> unattacked = new ArrayList<MyVertex>();
		ArrayList<MyVertex> vertexList = new ArrayList<MyVertex>(this.modelGraph.getMyVertices());
		for (int i=0; i<vertexList.size();i++){
			MyVertex currentvertex = vertexList.get(i);
			System.out.println(currentvertex.toString());
			if(this.modelGraph.getmygraph().getPredecessors(currentvertex).isEmpty()){
				unattacked.add(vertexList.get(i));			
			}
			else{
				System.out.println(vertexList.get(i).toString()+" has predecessors");
			}
		}
		return unattacked;
		
	}
	
	@SuppressWarnings("unused")
	private HashSet<MyVertex> findUnattackedVerticesHashSet(){
		HashSet<MyVertex> unattacked = new LinkedHashSet<MyVertex>();
		LinkedHashSet<MyVertex> vertexList =  new LinkedHashSet<MyVertex>(this.modelGraph.getMyVertices());
		Iterator<MyVertex> vertexIterator = vertexList.iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(this.modelGraph.getmygraph().getPredecessors(currentVertex).isEmpty()){
				System.out.print("The are no predecessors of vertex "+currentVertex.toString()+"\n");
				unattacked.add(currentVertex);
				
			}else{
				System.out.println(currentVertex.toString()+" has predecessors");
			}
		}
		return unattacked;
	}
	
	
	private MyLabelling findL1(){
		LinkedHashSet<MyVertex> unattacked = new LinkedHashSet<MyVertex>();
		MyLabelling labelling1= new MyLabelling(1);
		labelling1.setNotLabelledVertices(new LinkedHashSet<MyVertex>(this.modelGraph.getMyVertices()));
		Iterator<MyVertex> vertexIterator = labelling1.getNotLabelledVertices().iterator();
		while(vertexIterator.hasNext()){
			MyVertex currentVertex = vertexIterator.next();
			if(this.modelGraph.getmygraph().getPredecessors(currentVertex).isEmpty()){
				System.out.print("The are no predecessors of vertex "+currentVertex.toString()+"\n");
				unattacked.add(currentVertex);
				
			}else{
				System.out.println(currentVertex.toString()+" has predecessors");
			}
		}
		labelling1.getNotLabelledVertices().removeAll(unattacked);
		System.out.println(" The Unattacked edges areUA); "+unattacked);
		labelling1.setInVerties(unattacked);
		LinkedHashSet<MyVertex> notLabelled = new LinkedHashSet<MyVertex>();
		notLabelled.addAll(labelling1.getNotLabelledVertices());
		Iterator<MyVertex> notLabelledIterator = notLabelled.iterator();
		while(notLabelledIterator.hasNext()){
			MyVertex v= notLabelledIterator.next();
			LinkedHashSet<MyVertex> v1Attackers = new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getPredecessors(v));
			LinkedHashSet<MyVertex> intsection = new LinkedHashSet<MyVertex>(v1Attackers);
			intsection.retainAll(labelling1.getInVertices());
			if(!(intsection.isEmpty())){
				labelling1.addOutVertex(v);
			}
			
		}
		labelling1.getNotLabelledVertices().removeAll(labelling1.getOutVertices());
		return labelling1;
	}
	public MyLabelling groundedLabelling(){
		MyLabelling lPrevious = new MyLabelling(0);
		MyLabelling lCurrent= new MyLabelling(0);
		lPrevious=this.findL1();
		System.out.println("labelling1/unattacked is: " +lPrevious.toString());
		while(!(lPrevious==lCurrent)){
			lCurrent=lPrevious;
			LinkedHashSet<MyVertex> notIn = new LinkedHashSet<MyVertex>();
			notIn.addAll(lPrevious.getNotLabelledVertices());
			Iterator<MyVertex> inNotLabelledIterator = notIn.iterator();
			while(inNotLabelledIterator.hasNext()){
				MyVertex va= inNotLabelledIterator.next();
				System.out.println("va is: " +va.toString());
				LinkedHashSet<MyVertex> vaAttackers = new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getPredecessors(va));
				LinkedHashSet<MyVertex> tempVaAttackers= new LinkedHashSet<MyVertex>(vaAttackers);
				System.out.println("Va attacker contains: " +vaAttackers);
				Iterator<MyVertex> attackersIterator = vaAttackers.iterator();
				while(attackersIterator.hasNext()){
					MyVertex vb = attackersIterator.next();
					System.out.println("vb is :" +vb.toString());
					LinkedHashSet<MyVertex> h = new LinkedHashSet<MyVertex>();
					if(vb.isOut()){
						h.add(vb);
						System.out.println("h contains"+h);
						attackersIterator.remove();
					}	
					System.out.println("Now TempVattackers contains" +tempVaAttackers +" and h contains" + h);
				if(tempVaAttackers.equals(h)){
					System.out.print(va.toString()+va.getLabel());
					lCurrent.addInVertex(va);
					System.out.println(lCurrent.addInVertex(va));
					inNotLabelledIterator.remove();
				}
			}
				lPrevious.getNotLabelledVertices().removeAll(lCurrent.getInVertices());
			}
			Iterator<MyVertex> outNotLabelledIterator = lPrevious.getNotLabelledVertices().iterator();
			while(outNotLabelledIterator.hasNext()){
				MyVertex v1= outNotLabelledIterator.next();
				LinkedHashSet<MyVertex> v1Attackers = new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getPredecessors(v1));
				LinkedHashSet<MyVertex> intsection = new LinkedHashSet<MyVertex>(v1Attackers);
				intsection.retainAll(lPrevious.getInVertices());
				if(!(intsection.isEmpty())){
					lCurrent.addOutVertex(v1);
				}
				
			}
			System.out.println("Current status of the labelling: "+ lCurrent.toString() +"Previous labelling state"+ lPrevious.toString());
		}
		lPrevious.getNotLabelledVertices().removeAll(lPrevious.getOutVertices());
		LinkedHashSet<MyVertex> tempNotLabelledVertices = new LinkedHashSet<MyVertex>();
		tempNotLabelledVertices.addAll(lPrevious.getNotLabelledVertices());
		lCurrent.setUndecVertices(tempNotLabelledVertices);
		setChanged();
		notifyObservers(modelGraph);
		return lCurrent;
		
	}
	
	
	public LinkedHashSet<MyVertex> getGroundedExtension(){
		return this.groundedLabelling().getInVertices();
	}
	
	
	public LinkedHashSet<MyVertex> resetLabels(){
		LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>(this.modelGraph.getMyVertices());
		LinkedHashSet<MyVertex> tempVertices = vertices;
		Iterator< MyVertex> tempVerticesIterator = tempVertices.iterator();
		while(tempVerticesIterator.hasNext()){
			tempVerticesIterator.next().setLabel("NONE");
		}
		return tempVertices;
	}
	
	public boolean isLegallyIn(MyVertex v1){
		if(v1.isUndec()||v1.isOut()){
			return false;
	}
		LinkedHashSet<MyVertex> attackers = new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getPredecessors(v1));
		Iterator<MyVertex> attackersIterator = attackers.iterator();
		int tempCount1=0;
		while(attackersIterator.hasNext()){
			MyVertex tempVertex = attackersIterator.next();
			if(tempVertex.isOut()){
				tempCount1++;
			}
		}
			if(tempCount1==attackers.size() || attackers.size()==0){
				return true;
			}
		
				return false;
	}
	
	
	public boolean isLegallyOut(MyVertex v1){
		if(v1.isIn()||v1.isUndec()){
			return false;
		}
		LinkedHashSet<MyVertex> attackers = new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getPredecessors(v1));
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
		LinkedHashSet<MyVertex> attackers = new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getPredecessors(v1));
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
	

	public boolean hasillegallyIn(MyLabelling labelling){
		labelling.correctLabels();
		LinkedHashSet<MyVertex> h = labelling.getInVertices();
		LinkedHashSet<MyVertex> h1 = h;
		Iterator<MyVertex> h1Iterator = h1.iterator();
		while(h1Iterator.hasNext()){
			MyVertex v= h1Iterator.next();
//			I.remove();
			if(!(this.isLegallyIn(v))){
				return true;
			}
		}
		return false;
	}
	
	public boolean hasillegallyIn(LinkedHashSet<MyLabelling> labelling){
		Iterator<MyLabelling> labellingIterator = labelling.iterator();
		while(labellingIterator.hasNext()){
			MyLabelling tempLabelling = labellingIterator.next();
			tempLabelling.correctLabels();
			if(hasillegallyIn(tempLabelling)){
				return true;
			}
			
		}
		return false;
	}
	
	public MyLabelling transitionStep(MyLabelling labelling){
		  LinkedHashSet<MyVertex> tempInVertices = new LinkedHashSet<MyVertex>(labelling.getInVertices());
			Iterator<MyVertex> verticesIterator= tempInVertices.iterator();
			MyVertex currentVertex;
			while(verticesIterator.hasNext()){
				currentVertex = verticesIterator.next();
				verticesIterator.remove();
				if(!(this.isLegallyIn(currentVertex))){
					labelling.deleteFromInVertices(currentVertex);
					labelling.addOutVertex(currentVertex);
					LinkedHashSet<MyVertex> illegOutVerticesCheck= new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getSuccessors(currentVertex));
					illegOutVerticesCheck.add(currentVertex);
					Iterator<MyVertex> illegalIterator =illegOutVerticesCheck.iterator();
					while(illegalIterator.hasNext()){
						MyVertex v = illegalIterator.next();
						if(v.isOut()){
							if(!(this.isLegallyOut(v))){
								labelling.deleteFromOutVertices(v);
								labelling.addUndecVertex(v);
							}
						}
					}
				}
			}
			return labelling;
			
		}
	
	
	public MyLabelling admissibleLabelling(){
		LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>(this.modelGraph.getMyVertices());
		MyLabelling currentLabelling = new MyLabelling(0);
		currentLabelling.setInVerties(vertices);
		while(this.hasillegallyIn(currentLabelling)){
			currentLabelling=transitionStep(currentLabelling);
		}
		setChanged();
		notifyObservers(modelGraph);
		return currentLabelling;
		
	}
	

	
	public LinkedHashSet<MyVertex> reorderSet(LinkedHashSet<MyVertex> h){
		Iterator<MyVertex> iterateVertices = h.iterator();
			MyVertex current =iterateVertices.next();
			iterateVertices.remove();
			LinkedHashSet<MyVertex> h1= h;
			h1.add(current);
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
    static Collection<ArrayList<MyVertex>> permute(ArrayList<MyVertex> currentCombination, Collection<ArrayList<MyVertex>> possibleCombinations,int k){
    	if(k>currentCombination.size()){
    		throw new IllegalArgumentException("The index is large than the number of elements in the list");
    	}
    	else{
        for(int i = k; i < currentCombination.size(); i++){
            currentCombination=swap(currentCombination, i, k);
            possibleCombinations=permute(currentCombination,possibleCombinations, k+1);
            currentCombination=swap(currentCombination, k, i);
        }
        if (k == currentCombination.size() -1){
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
    static boolean alreadyContains(HashSet<MyLabelling> setOfLabellings, MyLabelling labelling){
    	Iterator<MyLabelling > setOfLabellingsIterator = setOfLabellings.iterator();
    	while(setOfLabellingsIterator.hasNext()){
    		MyLabelling tempLabellings = setOfLabellingsIterator.next();
    		if(tempLabellings.equals(labelling)){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * This method check if a set of labellings contains another set of labellings.
     * @param setOfLabellings A set of labellings.
     * @param containLabellings A set of labellings to check whether they are in setOfLabellings
     * @return This returns to true if setOfLabellings contains all of the labellings in containLabellings and false otherwise.
     */
    static boolean alreadyContains(HashSet<MyLabelling> setOfLabellings,HashSet<MyLabelling> containLabellings){
    	Iterator<MyLabelling > containLabellingsIterator = containLabellings.iterator();
    	int count =0;
    	while(containLabellingsIterator.hasNext()){
    		MyLabelling tempLabellings = containLabellingsIterator.next();
    		if(alreadyContains(setOfLabellings, tempLabellings)){
    			count++;
    		}
    	}
    	if(count==setOfLabellings.size()&& count!=0){
    		return true;
    	}
   
    	return false;
    }
    
    /**
     * Generates all the admissible labellings for the model.
     * @return Returns a set of all the admissible labellings for the model.
     */
    public LinkedHashSet<MyLabelling> allAdmissibleLabellings(){
    	LinkedHashSet<MyLabelling> admissibleLabellings= new LinkedHashSet<MyLabelling>();
    	LinkedHashSet<MyVertex> graphVertices = new LinkedHashSet<MyVertex>(modelGraph.getMyVertices());
    	ArrayList<MyVertex> vertices = new ArrayList<MyVertex>();
    	vertices.addAll(graphVertices);
    	LinkedHashSet<ArrayList<MyVertex>> possibleCombinations = new LinkedHashSet<ArrayList<MyVertex>>();
    	MyVertex currentVertex = new MyVertex(0);
    	/*This method finds all possible combinations of the vertices. Each combination must be checked in order to find all labellings. 
    	 * Some combinations will find the same labelling. Duplicates are not added.
    	 * */
    	permute(vertices, possibleCombinations, 0);
    	Iterator<ArrayList<MyVertex>> possibleCombinationsIterator = possibleCombinations.iterator();
    	while(possibleCombinationsIterator.hasNext()){
    		MyLabelling currentLabelling = new MyLabelling(0);
    		LinkedHashSet<MyVertex> vertexOrder = new LinkedHashSet<MyVertex>(possibleCombinationsIterator.next());
    		LinkedHashSet<MyVertex> currentVertexOrder = new LinkedHashSet<MyVertex>();
    		currentVertexOrder.addAll(vertexOrder);
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
    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentVertex));
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
    
    private LinkedHashSet<MyVertex> findAllIllegIn(MyLabelling labelling){
    	LinkedHashSet<MyVertex> illegalInVertices = new LinkedHashSet<MyVertex>();
    	LinkedHashSet<MyVertex> inVertices = new LinkedHashSet<MyVertex>(labelling.getInVertices());
    	Iterator<MyVertex> inVerticesIterator = inVertices.iterator();
    	while(inVerticesIterator.hasNext()){
    		MyVertex currentVertex = inVerticesIterator.next();
    		if(!(this.isLegallyIn(currentVertex))){
    			illegalInVertices.add(currentVertex);
    			
    		}
    		
    	}
		return illegalInVertices;
    	
    }
    
    public int checkAllLabels(LinkedHashSet<MyLabelling> set){
    	int count=0;
    	Iterator<MyLabelling> setIterator = set.iterator();
    	while(setIterator.hasNext()){
    		if(setIterator.next().checkAllLabels()){
    			count++;
    		}
    	}
    	
    	return count;
    }
  
  
    
    public LinkedHashSet<MyLabelling> allAdmissibleLabelling2(){
    	LinkedHashSet<MyLabelling> allAdmissibleLabellings = new LinkedHashSet<MyLabelling>();
    	LinkedHashSet<MyLabelling> possibleCombinations = new LinkedHashSet<MyLabelling>();
    	LinkedHashSet<MyLabelling> possibleCombinations1 = new LinkedHashSet<MyLabelling>();
    	LinkedHashSet<MyVertex> graphVertices = new LinkedHashSet<MyVertex>(this.modelGraph.getMyVertices());
    	LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>();
    	vertices.addAll(graphVertices);
    	if(modelGraph.GetMyEdgeCount()==0){
    		System.out.println("System no edge loop entered");
    		MyLabelling allIn = new MyLabelling(0);
    		allIn.setInVerties(graphVertices);
    		allAdmissibleLabellings.add(allIn);
    		return allAdmissibleLabellings;
    	}
    	MyLabelling labelling = new MyLabelling(0);
    	labelling.setInVerties(vertices);
    	LinkedHashSet<MyVertex> illegallyIn =findAllIllegIn(labelling);
    	if(illegallyIn.size()==vertices.size()){
    		System.out.println("All illegally if statement entered");
    		return(this.allAdmissibleLabellings());
    	}
    	
    	for(int i=0; i<=vertices.size();i++){
    		LinkedHashSet<MyVertex> currentVertexOrder = reorderSet(vertices);
    		MyLabelling labelling1 = new MyLabelling(0);
    		labelling1.setInVerties(currentVertexOrder);
    		possibleCombinations.add(labelling1);
    		while(hasillegallyIn(possibleCombinations)){
    			Iterator<MyLabelling> possibleCombinationsIterator = possibleCombinations.iterator();
    			while(possibleCombinationsIterator.hasNext()){
    				LinkedHashSet<ArrayList<MyVertex>> illegInCombinations = new LinkedHashSet<ArrayList<MyVertex>>();
    				MyLabelling labelling2 = possibleCombinationsIterator.next();
//    				labelling2.setInVerties(labelling2.getInVertices());
//    				labelling2.setOutVertices(labelling2.getOutVertices());
//    				labelling2.setUndecVertices(labelling2.getUndecVertices());
    				labelling2.correctLabels();
    				if(hasillegallyIn(labelling2)){
    				LinkedHashSet<MyVertex> illegIn =findAllIllegIn(labelling2);
    				ArrayList<MyVertex> illegInArray =new  ArrayList<MyVertex>();
    				illegInArray.addAll(illegIn);
    				permute(illegInArray, illegInCombinations, 0);
    				Iterator<ArrayList<MyVertex>> illegInCombinationIterator = illegInCombinations.iterator();
    					while(illegInCombinationIterator.hasNext()){
	    					MyLabelling labelling3 = new MyLabelling(2);
	    					LinkedHashSet<MyVertex> l2InVertices = new LinkedHashSet<MyVertex>();
	    					l2InVertices.addAll(labelling2.getInVertices());
	    					labelling3.setInVerties(l2InVertices);
	    					LinkedHashSet<MyVertex> l2OutVertices = new LinkedHashSet<MyVertex>();
	    					l2InVertices.addAll(labelling2.getOutVertices());
	    					labelling3.setOutVertices(l2OutVertices);
	    					LinkedHashSet<MyVertex> l2UndecVertices = new LinkedHashSet<MyVertex>();
	    					l2InVertices.addAll(labelling2.getUndecVertices());
	    					labelling3.setUndecVertices(l2UndecVertices);
	    					LinkedHashSet<MyVertex> l2NotVertices = new LinkedHashSet<MyVertex>();
	    					l2InVertices.addAll(labelling2.getNotLabelledVertices());
	    					labelling3.setNotLabelledVertices(l2NotVertices);
	    					LinkedHashSet<MyVertex> tempIllegIn = new LinkedHashSet<MyVertex>();
	    					tempIllegIn.addAll(illegInCombinationIterator.next());
	    					Iterator<MyVertex> tempIllegInIterator = tempIllegIn.iterator();
	    					while(tempIllegInIterator.hasNext()){
	    						labelling3.correctLabels();
	    						MyVertex currentVertex = tempIllegInIterator.next();
	    						if(!(isLegallyIn(currentVertex))){
			    					labelling3.deleteFromInVertices(currentVertex);
			    					labelling3.addOutVertex(currentVertex);
			    					LinkedHashSet<MyVertex> illegOut = new LinkedHashSet<MyVertex>();
			    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentVertex));
			    					illegOut.add(currentVertex);
			    					Iterator<MyVertex> illegOutIterator = illegOut.iterator();
			    					while(illegOutIterator.hasNext()){
			    					MyVertex v = illegOutIterator.next();
				    					if(v.isOut()){
				    						if(!(isLegallyOut(v))){
				    							labelling3.deleteFromOutVertices(v);
				    							labelling3.addUndecVertex(v);
				    							
				    						}
			    						
				    					}	
				    					}
	    						}
	    					}
	    					if(!(alreadyContains(possibleCombinations1, labelling3))){
	    						possibleCombinations1.add(labelling3);
	    					}
	    				}
	//    				possibleCombinations.addAll(possibleCombinations1);	
	    			}
	    		}
    			if(!(alreadyContains(possibleCombinations, possibleCombinations1))){
    			possibleCombinations.remove(labelling1);
    			possibleCombinations.addAll(possibleCombinations1);
    			}
    			possibleCombinations1.clear();
    		}
    		if(!(alreadyContains(allAdmissibleLabellings, possibleCombinations))){
    		allAdmissibleLabellings.addAll(possibleCombinations);
    		}
    		possibleCombinations.clear();
    	}
    	if(!(alreadyContains(allAdmissibleLabellings, possibleCombinations))){
    	allAdmissibleLabellings.addAll(possibleCombinations);
    	}
    	return allAdmissibleLabellings;
    	
    }
    
    public void displayALabelling(LinkedHashSet<MyLabelling> set){
    	Iterator<MyLabelling> setIterator = set.iterator();
    	MyLabelling labelling =setIterator.next();
    	Iterator<MyVertex> inIterator = labelling.getInVertices().iterator();
    	while(inIterator.hasNext()){
    		modelGraph.getMyVertex(inIterator.next()).setLabel("IN");
    	}
    	Iterator<MyVertex> outIterator = labelling.getOutVertices().iterator();
    	while(outIterator.hasNext()){
    		modelGraph.getMyVertex(outIterator.next()).setLabel("OUT");
    	}
    	Iterator<MyVertex> undecIterator = labelling.getUndecVertices().iterator();
    	while(undecIterator.hasNext()){
    		modelGraph.getMyVertex(undecIterator.next()).setLabel("UNDEC");
    	}
    	setChanged();
    	notifyObservers(modelGraph);
    	
    }
    public String labellingSetString( LinkedHashSet<MyLabelling> set){
    	String s="";
    	Iterator<MyLabelling> setIterator = set.iterator();
    	while(setIterator.hasNext()){
    		s= s+ setIterator.next().toString()+"  \n";
    	}
    	
    	return s;
    }
    
    public boolean superIllegallyIn(MyVertex v, MyLabelling labelling ){
    	labelling.correctLabels();
    	if(!(v.getLabel()=="IN")){
    		return false;
    	}
    	else if(this.modelGraph.getmygraph().getPredecessors(v).isEmpty()){
    		return false;
    	}
    	else{
    		LinkedHashSet<MyVertex> predecessors = new LinkedHashSet<MyVertex>(modelGraph.getmygraph().getPredecessors(v));
    		Iterator<MyVertex> predecessorsIterator = predecessors.iterator();
    		while(predecessorsIterator.hasNext()){
    			MyVertex currentVertex = predecessorsIterator.next();
    			if(this.isLegallyIn(currentVertex) || currentVertex.getLabel()=="UNDEC"){
    				return true;
    			}
    		}
    		}
    	return false;
    }
    
    public boolean hasSuperIllegallyIn(MyLabelling labelling){
    	LinkedHashSet<MyVertex> inVertices = labelling.getInVertices();
    	Iterator<MyVertex> inVerticesIterator = inVertices.iterator();
    	while(inVerticesIterator.hasNext()){
    		if(superIllegallyIn(inVerticesIterator.next(), labelling)){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public LinkedHashSet<MyVertex> findSuperIllegallyIn(MyLabelling labelling ){
    	LinkedHashSet<MyVertex> inVertices = labelling.getInVertices();
    	LinkedHashSet<MyVertex> superIllegallyIn = new LinkedHashSet<MyVertex>();
    	Iterator<MyVertex> inVerticesIterator = inVertices.iterator();
    	while(inVerticesIterator.hasNext()){
    		MyVertex currentVertex = inVerticesIterator.next();
    		if(superIllegallyIn(currentVertex, labelling)){
    			superIllegallyIn.add(currentVertex);
    		}
    	}
    	return superIllegallyIn;
    	
    }
    
    public MyLabelling transitionStep(MyVertex vertex, MyLabelling labelling){
    	MyLabelling labelling1 = new MyLabelling(0, labelling.getInVertices(), labelling.getOutVertices(), labelling.getUndecVertices());
    	if(this.isLegallyIn(vertex)){
    		return labelling1;
    	}
			labelling1.deleteFromInVertices(vertex);
			labelling1.addOutVertex(vertex);
			LinkedHashSet<MyVertex> illegOutVerticesCheck= new LinkedHashSet<MyVertex>(this.modelGraph.getmygraph().getSuccessors(vertex));
			illegOutVerticesCheck.add(vertex);
			Iterator<MyVertex> illegalIterator =illegOutVerticesCheck.iterator();
			while(illegalIterator.hasNext()){
				MyVertex v = illegalIterator.next();
				if(v.isOut()){
					if(!(this.isLegallyOut(v))){
						labelling1.deleteFromOutVertices(v);
						labelling1.addUndecVertex(v);
					}
				}
			}
		return labelling1;
    
    }
    
    public MyLabelling transitionSequence(MyLabelling labelling){
    	int transitionNumber=0;
    	System.out.println("Lablling "+transitionNumber +" is: "+labelling);
    	while(this.hasillegallyIn(labelling)){
    		LinkedHashSet<MyVertex> illegIn=this.findAllIllegIn(labelling);
    		Iterator<MyVertex> illegInIterator =illegIn.iterator();
    		labelling=this.transitionStep(illegInIterator.next(),labelling);
    		transitionNumber++;
    		System.out.println("Labelling "+transitionNumber +" is: "+labelling);
    	}
    	return labelling; 
    }
    
    public LinkedHashSet<MyLabelling> preferredLabelling(){
    	System.out.println("Model graph is: "+this.modelGraph.toString());
    	LinkedHashSet<MyLabelling> candidateLabelling = new LinkedHashSet<MyLabelling>();
    	MyLabelling labelling = new MyLabelling(0,	new LinkedHashSet<MyVertex>(this.modelGraph.getMyVertices()) , new LinkedHashSet<MyVertex>(), new LinkedHashSet<MyVertex>());
    	return findLabelling(candidateLabelling, labelling);
    	
    }
    
   
    public LinkedHashSet<MyLabelling> findLabelling(LinkedHashSet<MyLabelling> candidateLabellings, MyLabelling labelling){
    	labelling.correctLabels();
    	MyLabelling currentLabelling = new MyLabelling(0, new LinkedHashSet<MyVertex>(labelling.getInVertices()), new LinkedHashSet<MyVertex>(labelling.getOutVertices()), new LinkedHashSet<MyVertex>(labelling.getUndecVertices()));
    	LinkedHashSet<MyLabelling> currentCandidateLabelling = new LinkedHashSet<MyLabelling>(candidateLabellings);
    	if(!(currentCandidateLabelling.isEmpty())){
    		    		Iterator<MyLabelling> candidateLabellingIterator= currentCandidateLabelling.iterator();
    		while(candidateLabellingIterator.hasNext()){
    			if(currentLabelling.getInVertices().size()<candidateLabellingIterator.next().getInVertices().size()){
    				return currentCandidateLabelling;
    			}
    		}
    	}
    	
    	if(!(this.hasillegallyIn(currentLabelling))){
    		LinkedHashSet<MyLabelling> tempCandidateLabelling = new LinkedHashSet<MyLabelling>();
    		tempCandidateLabelling.addAll(currentCandidateLabelling);
    		Iterator<MyLabelling> tempCandidateLabellingIterator = tempCandidateLabelling.iterator();
    		while(tempCandidateLabellingIterator.hasNext()){
    			MyLabelling labelling1 =tempCandidateLabellingIterator.next();
    			if(labelling1.getInVertices().size()<currentLabelling.getInVertices().size()){
    				currentCandidateLabelling.remove(labelling1);
    			}
    		}
    		MyLabelling testLabelling = new MyLabelling(0, new LinkedHashSet<MyVertex>(currentLabelling.getInVertices()),new LinkedHashSet<MyVertex>(currentLabelling.getOutVertices()),new LinkedHashSet<MyVertex>(currentLabelling.getUndecVertices()));
    		if(!(alreadyContains(currentCandidateLabelling, testLabelling))){
    		currentCandidateLabelling.add(testLabelling);
    		}
    		
    		return currentCandidateLabelling;
    	}
    	else{
    		if(hasSuperIllegallyIn(currentLabelling)){
    			LinkedHashSet<MyVertex> supperIllegVertices =this.findSuperIllegallyIn(currentLabelling);
    			Iterator<MyVertex> superIllegVerticesIterator = supperIllegVertices.iterator();
    			MyVertex vertex =superIllegVerticesIterator.next();
    			MyLabelling transitionLabelling = transitionStep(vertex, currentLabelling);
    			currentCandidateLabelling =findLabelling(currentCandidateLabelling,transitionLabelling);
    		}
    		else{
    			LinkedHashSet<MyVertex> IllegIn =this.findAllIllegIn(currentLabelling);
    			Iterator<MyVertex> IllegInIterator = IllegIn.iterator();
    			LinkedHashSet<MyVertex> illegInCopy =new LinkedHashSet<MyVertex>();
    			illegInCopy.addAll(IllegIn);
    			currentLabelling.correctLabels();
    			while(IllegInIterator.hasNext()){
    				if(!(currentLabelling.getInVertices().containsAll(IllegIn))&& !(IllegIn.containsAll(currentLabelling.getInVertices()))){
    					Iterator<MyVertex> illegInCopyIterator = illegInCopy.iterator();
    					currentLabelling.correctLabels();
    					while(illegInCopyIterator.hasNext()){
    						MyVertex vertex =illegInCopyIterator.next();
    						if(vertex.getLabel()=="OUT"){
    							currentLabelling.deleteFromOutVertices(vertex);
    							currentLabelling.addInVertex(vertex);
    						}
    						if(vertex.getLabel()=="UNDEC"){
    							currentLabelling.deleteFromUndecVertices(vertex);
    							currentLabelling.addInVertex(vertex);
    					
    						}
    					}
    				}
    				MyVertex currentVertex =IllegInIterator.next();
    				MyLabelling transitionLabelling = transitionStep(currentVertex, currentLabelling);
        			currentCandidateLabelling=findLabelling(currentCandidateLabelling,transitionLabelling);
    			}
    		}
    	}
    	
    	return currentCandidateLabelling;
    	
    }
} 