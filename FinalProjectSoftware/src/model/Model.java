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
	public MyArgument v1;
	public MyArgument v2;
	public int counter =0;
//	public HashSet<MyVertex> NotLabelledVertices;

	public Model(){
		 modelGraph = new MyGraph();	

		System.out.println("Model()");	
	} 
	
	public MyArgument getV1() {
		return v1;
	}
	public void setV1(MyArgument v1) {
		this.v1 = v1;
	}
	public MyArgument getV2() {
		return v2;
	}
	public void setV2(MyArgument v2) {
		this.v2 = v2;
	}
	public MyArgument addMyVertex() {
		MyArgument v =this.modelGraph.addMyVertex();
		setChanged();
		notifyObservers(modelGraph);
		return v;
	}
	
	public void deleteVertex(MyArgument deleteVertex) {
		int vertexId=deleteVertex.getId();
		LinkedHashSet<MyArgument> vertices = new LinkedHashSet<MyArgument>(modelGraph.getMyVertices());
		//hashet to add all vertices with a id larger than the id of the vertex we are deleting.
		LinkedHashSet<MyArgument> afterDeleteVertex = new LinkedHashSet<MyArgument>();
		Iterator<MyArgument> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			MyArgument v= verticesIterator.next();
			if(v.getId()>vertexId){
				afterDeleteVertex.add(v);
			}
		}
		// now iterate through the newer vertices and reduce their id by one.
		Iterator<MyArgument> afterDeleteVertexIterator = afterDeleteVertex.iterator();
		while(afterDeleteVertexIterator.hasNext()){
			MyArgument v1= afterDeleteVertexIterator.next();
			v1.setId(v1.getId()-1);
		}
		HashSet<MyAttack> edges = new HashSet<MyAttack>(this.modelGraph.getMyEdges());
		Iterator<MyAttack> edgesiterator = edges.iterator();
		while(edgesiterator.hasNext()){
			MyAttack e = edgesiterator.next();
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
	
	public MyAttack addEdge(MyArgument v){
		if(v1 ==null){
			v1=v;
			System.out.println("I have stored the from vertex as "+ v);
			return null;
		}
		else if(!(v1==null)){
			v2=v;
			System.out.println("I have stored the to vertex as "+ v);
			MyAttack e =modelGraph.addMyEdge(v1, v2);
			System.out.println("Model init: counter = " + modelGraph.toString());
			setChanged();
			notifyObservers(modelGraph);
			v1=null;
			v2=null;
			return e;
		}
		return null;
	}
	
	public void deleteEdge(MyAttack deleteEdge){
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
	
	public boolean resetLabels(){
		LinkedHashSet<MyArgument> vertices = new LinkedHashSet<MyArgument>(modelGraph.getMyVertices());
		Iterator<MyArgument> verticesIterator = vertices.iterator();
		while(verticesIterator.hasNext()){
			verticesIterator.next().setLabel("NONE");
		}
		setChanged();
		notifyObservers(modelGraph);
		return true;
		
	}
	
	public boolean isLegallyIn(MyArgument v1){
		if(v1.isUndec()||v1.isOut()){
			return false;
	}
		LinkedHashSet<MyArgument> attackers = new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getPredecessors(v1));
		Iterator<MyArgument> attackersIterator = attackers.iterator();
		int tempCount1=0;
		while(attackersIterator.hasNext()){
			MyArgument tempVertex = attackersIterator.next();
			if(tempVertex.isOut()){
				tempCount1++;
			}
		}
			if(tempCount1==attackers.size() || attackers.size()==0){
				return true;
			}
		
				return false;
	}
	
	
	public boolean isLegallyOut(MyArgument v1){
		if(v1.isIn()||v1.isUndec()){
			return false;
		}
		LinkedHashSet<MyArgument> attackers = new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getPredecessors(v1));
		Iterator<MyArgument> attackersIterator = attackers.iterator();
		int count =0;
		while(attackersIterator.hasNext()){
			MyArgument tempVertex = attackersIterator.next();
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
	public boolean isLegallyUndec(MyArgument v1){
		if(v1.isIn()||v1.isOut()){
			return false;
		}
		LinkedHashSet<MyArgument> attackers = new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getPredecessors(v1));
		int tempCount=0;
		Iterator<MyArgument> attackersIterator = attackers.iterator();
		while(attackersIterator.hasNext()){
			MyArgument tempVertex = attackersIterator.next();
			if(tempVertex.isOut()){
				tempCount++;
			}
		}
			if(tempCount<attackers.size()){
				while(attackersIterator.hasNext()){
					MyArgument tempVertex1 = attackersIterator.next();
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
		LinkedHashSet<MyArgument> h = labelling.getInVertices();
		LinkedHashSet<MyArgument> h1 = h;
		Iterator<MyArgument> h1Iterator = h1.iterator();
		while(h1Iterator.hasNext()){
			MyArgument v= h1Iterator.next();
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
	
    public void displayALabelling(LinkedHashSet<MyLabelling> set){
    	Iterator<MyLabelling> setIterator = set.iterator();
    	MyLabelling labelling =setIterator.next();
    	Iterator<MyArgument> inIterator = labelling.getInVertices().iterator();
    	while(inIterator.hasNext()){
    		modelGraph.getMyVertex(inIterator.next()).setLabel("IN");
    	}
    	Iterator<MyArgument> outIterator = labelling.getOutVertices().iterator();
    	while(outIterator.hasNext()){
    		modelGraph.getMyVertex(outIterator.next()).setLabel("OUT");
    	}
    	Iterator<MyArgument> undecIterator = labelling.getUndecVertices().iterator();
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
    
    
    private LinkedHashSet<MyArgument> findAllIllegIn(MyLabelling labelling){
    	LinkedHashSet<MyArgument> illegalInVertices = new LinkedHashSet<MyArgument>();
    	LinkedHashSet<MyArgument> inVertices = new LinkedHashSet<MyArgument>(labelling.getInVertices());
    	Iterator<MyArgument> inVerticesIterator = inVertices.iterator();
    	while(inVerticesIterator.hasNext()){
    		MyArgument currentVertex = inVerticesIterator.next();
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
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Grounded 	//Grounded 	//Grounded 	//Grounded 	//Grounded 	//Grounded 	//Grounded 	//Grounded 	//Grounded 	//Grounded 	//Grounded 
	public ArrayList<MyArgument> findUnattackedVerticesArray(){
		ArrayList<MyArgument> unattacked = new ArrayList<MyArgument>();
		ArrayList<MyArgument> vertexList = new ArrayList<MyArgument>(this.modelGraph.getMyVertices());
		for (int i=0; i<vertexList.size();i++){
			MyArgument currentvertex = vertexList.get(i);
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
	
	public HashSet<MyArgument> findUnattackedVerticesHashSet(){
		HashSet<MyArgument> unattacked = new LinkedHashSet<MyArgument>();
		LinkedHashSet<MyArgument> vertexList =  new LinkedHashSet<MyArgument>(this.modelGraph.getMyVertices());
		Iterator<MyArgument> vertexIterator = vertexList.iterator();
		while(vertexIterator.hasNext()){
			MyArgument currentVertex = vertexIterator.next();
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
		LinkedHashSet<MyArgument> unattacked = new LinkedHashSet<MyArgument>();
		MyLabelling labelling1= new MyLabelling(1);
		labelling1.setNotLabelledVertices(new LinkedHashSet<MyArgument>(this.modelGraph.getMyVertices()));
		Iterator<MyArgument> vertexIterator = labelling1.getNotLabelledVertices().iterator();
		while(vertexIterator.hasNext()){
			MyArgument currentVertex = vertexIterator.next();
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
		LinkedHashSet<MyArgument> notLabelled = new LinkedHashSet<MyArgument>();
		notLabelled.addAll(labelling1.getNotLabelledVertices());
		Iterator<MyArgument> notLabelledIterator = notLabelled.iterator();
		while(notLabelledIterator.hasNext()){
			MyArgument v= notLabelledIterator.next();
			LinkedHashSet<MyArgument> v1Attackers = new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getPredecessors(v));
			LinkedHashSet<MyArgument> intsection = new LinkedHashSet<MyArgument>(v1Attackers);
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
			LinkedHashSet<MyArgument> notIn = new LinkedHashSet<MyArgument>();
			notIn.addAll(lPrevious.getNotLabelledVertices());
			Iterator<MyArgument> inNotLabelledIterator = notIn.iterator();
			while(inNotLabelledIterator.hasNext()){
				MyArgument va= inNotLabelledIterator.next();
				System.out.println("va is: " +va.toString());
				LinkedHashSet<MyArgument> vaAttackers = new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getPredecessors(va));
				LinkedHashSet<MyArgument> tempVaAttackers= new LinkedHashSet<MyArgument>(vaAttackers);
				System.out.println("Va attacker contains: " +vaAttackers);
				Iterator<MyArgument> attackersIterator = vaAttackers.iterator();
				while(attackersIterator.hasNext()){
					MyArgument vb = attackersIterator.next();
					System.out.println("vb is :" +vb.toString());
					LinkedHashSet<MyArgument> h = new LinkedHashSet<MyArgument>();
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
			Iterator<MyArgument> outNotLabelledIterator = lPrevious.getNotLabelledVertices().iterator();
			while(outNotLabelledIterator.hasNext()){
				MyArgument v1= outNotLabelledIterator.next();
				LinkedHashSet<MyArgument> v1Attackers = new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getPredecessors(v1));
				LinkedHashSet<MyArgument> intsection = new LinkedHashSet<MyArgument>(v1Attackers);
				intsection.retainAll(lPrevious.getInVertices());
				if(!(intsection.isEmpty())){
					lCurrent.addOutVertex(v1);
				}
				
			}
			System.out.println("Current status of the labelling: "+ lCurrent.toString() +"Previous labelling state"+ lPrevious.toString());
		}
		lPrevious.getNotLabelledVertices().removeAll(lPrevious.getOutVertices());
		LinkedHashSet<MyArgument> tempNotLabelledVertices = new LinkedHashSet<MyArgument>();
		tempNotLabelledVertices.addAll(lPrevious.getNotLabelledVertices());
		lCurrent.setUndecVertices(tempNotLabelledVertices);
		setChanged();
		notifyObservers(modelGraph);
		return lCurrent;
		
	}
	
	
	public LinkedHashSet<MyArgument> getGroundedExtension(){
		return this.groundedLabelling().getInVertices();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	
	
	
	public MyLabelling transitionStep(MyLabelling labelling){
		  LinkedHashSet<MyArgument> tempInVertices = new LinkedHashSet<MyArgument>(labelling.getInVertices());
			Iterator<MyArgument> verticesIterator= tempInVertices.iterator();
			MyArgument currentVertex;
			while(verticesIterator.hasNext()){
				currentVertex = verticesIterator.next();
				verticesIterator.remove();
				if(!(this.isLegallyIn(currentVertex))){
					labelling.deleteFromInVertices(currentVertex);
					labelling.addOutVertex(currentVertex);
					LinkedHashSet<MyArgument> illegOutVerticesCheck= new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getSuccessors(currentVertex));
					illegOutVerticesCheck.add(currentVertex);
					Iterator<MyArgument> illegalIterator =illegOutVerticesCheck.iterator();
					while(illegalIterator.hasNext()){
						MyArgument v = illegalIterator.next();
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
		LinkedHashSet<MyArgument> vertices = new LinkedHashSet<MyArgument>(this.modelGraph.getMyVertices());
		MyLabelling currentLabelling = new MyLabelling(0);
		currentLabelling.setInVerties(vertices);
		while(this.hasillegallyIn(currentLabelling)){
			currentLabelling=transitionStep(currentLabelling);
		}
		setChanged();
		notifyObservers(modelGraph);
		return currentLabelling;
		
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//AllAdmissible     	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    
	
	public LinkedHashSet<MyArgument> reorderSet(LinkedHashSet<MyArgument> h){
		Iterator<MyArgument> iterateVertices = h.iterator();
			MyArgument current =iterateVertices.next();
			iterateVertices.remove();
			LinkedHashSet<MyArgument> h1= h;
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
	static ArrayList<MyArgument> swap(ArrayList<MyArgument> list, int i , int j){
		MyArgument temp = list.get(i);
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
    static Collection<ArrayList<MyArgument>> permute(ArrayList<MyArgument> currentCombination, Collection<ArrayList<MyArgument>> possibleCombinations,int k){
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
           ArrayList<MyArgument> combination = new ArrayList<MyArgument>(currentCombination);
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
    
//    public LinkedHashSet<MyLabelling> allAdmissibleLabellings3(){
//    	LinkedHashSet<MyLabelling> admissibleLabellings= new LinkedHashSet<MyLabelling>();
//    	LinkedHashSet<MyVertex> graphVertices = new LinkedHashSet<MyVertex>(modelGraph.getMyVertices());
//    	ArrayList<MyVertex> vertices = new ArrayList<MyVertex>();
//    	vertices.addAll(graphVertices);
//    	LinkedHashSet<ArrayList<MyVertex>> possibleCombinations = new LinkedHashSet<ArrayList<MyVertex>>();
//    	MyVertex currentVertex = new MyVertex(0);
//    	/*This method finds all possible combinations of the vertices. Each combination must be checked in order to find all labellings. 
//    	 * Some combinations will find the same labelling. Duplicates are not added.
//    	 * */
//    	permute(vertices, possibleCombinations, 0);
//    	Iterator<ArrayList<MyVertex>> possibleCombinationsIterator = possibleCombinations.iterator();
//    	while(possibleCombinationsIterator.hasNext()){
//    		MyLabelling currentLabelling = new MyLabelling(0);
//    		LinkedHashSet<MyVertex> vertexOrder = new LinkedHashSet<MyVertex>(possibleCombinationsIterator.next());
//    		LinkedHashSet<MyVertex> currentVertexOrder = new LinkedHashSet<MyVertex>();
//    		currentVertexOrder.addAll(vertexOrder);
//    		currentLabelling.setInVerties(currentVertexOrder);
//    		while(hasillegallyIn(currentLabelling)){
//    			LinkedHashSet<MyVertex> tempIn = new LinkedHashSet<MyVertex>();
//    			tempIn.addAll(currentLabelling.getInVertices());
//    			LinkedHashSet<MyVertex> tempOut = new LinkedHashSet<MyVertex>();
//    			tempOut.addAll(currentLabelling.getOutVertices());
//    			Iterator<MyVertex> inIterator = tempIn.iterator();
//    			while(inIterator.hasNext()){
//    				MyVertex tempv= inIterator.next();
//    				currentVertex= tempv;
//    				if(!(isLegallyIn(currentVertex))){
//    					currentLabelling.deleteFromInVertices(currentVertex);
//    					currentLabelling.addOutVertex(currentVertex);
//    					LinkedHashSet<MyVertex> illegOut = new LinkedHashSet<MyVertex>();
//    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentVertex));
//    					illegOut.add(currentVertex);
//    					Iterator<MyVertex> illegOutIterator = illegOut.iterator();
//    					while(illegOutIterator.hasNext()){
//    					MyVertex v = illegOutIterator.next();
//	    					if(v.isOut()){
//	    						if(!(isLegallyOut(v))){
//	    							currentLabelling.deleteFromOutVertices(v);
//	    							currentLabelling.addUndecVertex(v);
//	    						}
//    						
//	    					}	
//    					}
//    				}
//    				
//    			}
//    		}
//    		if (!(alreadyContains(admissibleLabellings, currentLabelling))){
//    		admissibleLabellings.add(currentLabelling);
//    		}
//    	}
//    	return admissibleLabellings;
//    }

  
//    public HashSet<MyLabelling> allAdmissibleLabellings1(){
//    	LinkedHashSet<MyLabelling> admissibleLabellings= new LinkedHashSet<MyLabelling>();
//    	LinkedHashSet<MyVertex> graphVertices = new LinkedHashSet<MyVertex>(modelGraph.getMyVertices());
//    	LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>();
//    	vertices.addAll(graphVertices);
//    	LinkedHashSet<ArrayList<MyVertex>> possibleCombinations = new LinkedHashSet<ArrayList<MyVertex>>();
//    	MyVertex currentVertex = new MyVertex(0);
//    	for(int i=0; i<vertices.size(); i++){;
//    		vertices =reorderSet(vertices);
//    		MyLabelling currentLabelling = new MyLabelling(0);
//    		currentLabelling.setInVerties(vertices);
//    	    		while(hasillegallyIn(currentLabelling)){
//    	    			LinkedHashSet<MyVertex> illegallyIn = findAllIllegIn(currentLabelling);
//    	    			ArrayList<MyVertex> illegallyInArray = new ArrayList<MyVertex>();
//    	    			illegallyInArray.addAll(illegallyIn);
//    	    			permute(illegallyInArray, possibleCombinations, 0);
//    	    			Iterator<ArrayList<MyVertex>> possibleCombinationIterator = possibleCombinations.iterator();
//    	    			while(possibleCombinationIterator.hasNext()){
//    	    				MyLabelling tempLabelling = new MyLabelling(0);
//    	    				LinkedHashSet<MyVertex > tempSet = new LinkedHashSet<MyVertex>();
//    	    				tempSet.addAll(possibleCombinationIterator.next());
//    	    				LinkedHashSet<MyVertex> newInVertices = new LinkedHashSet<MyVertex>();
//    	    				newInVertices.addAll(tempSet);
//    	    				tempLabelling.setInVerties(tempSet);
//    	    				Iterator<MyVertex> inIterator = tempLabelling.getInVertices().iterator();
//    	    				while(inIterator.hasNext()){
//    	    					MyVertex tempv= inIterator.next();
//    	    					currentVertex= tempv;
//			    				if(!(isLegallyIn(currentVertex))){
//			    					tempLabelling.deleteFromInVertices(currentVertex);
//			    					tempLabelling.addOutVertex(currentVertex);
//			    					LinkedHashSet<MyVertex> illegOut = new LinkedHashSet<MyVertex>();
//			    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentVertex));
//			    					illegOut.add(currentVertex);
//			    					Iterator<MyVertex> illegOutIterator = illegOut.iterator();
//			    					while(illegOutIterator.hasNext()){
//			    					MyVertex v = illegOutIterator.next();
//				    					if(v.isOut()){
//				    						if(!(isLegallyOut(v))){
//				    							tempLabelling.deleteFromOutVertices(v);
//				    							tempLabelling.addUndecVertex(v);
//				    						}
//			    						
//				    					}	
//			    					}
//			    				}
//    				
//    			}
//    		}
//    		if (!(alreadyContains(admissibleLabellings, currentLabelling))){
//    		admissibleLabellings.add(currentLabelling);
//    		}
//    	}
//    	}
//    	return admissibleLabellings;
//    }

    
//    public LinkedHashSet<MyLabelling> allAdmissibleLabelling2(){
//    	LinkedHashSet<MyLabelling> allAdmissibleLabellings = new LinkedHashSet<MyLabelling>();
//    	LinkedHashSet<MyLabelling> possibleCombinations = new LinkedHashSet<MyLabelling>();
//    	LinkedHashSet<MyLabelling> possibleCombinations1 = new LinkedHashSet<MyLabelling>();
//    	LinkedHashSet<MyVertex> graphVertices = new LinkedHashSet<MyVertex>(this.modelGraph.getMyVertices());
//    	LinkedHashSet<MyVertex> vertices = new LinkedHashSet<MyVertex>();
//    	vertices.addAll(graphVertices);
//    	if(modelGraph.GetMyEdgeCount()==0){
//    		System.out.println("System no edge loop entered");
//    		MyLabelling allIn = new MyLabelling(0);
//    		allIn.setInVerties(graphVertices);
//    		allAdmissibleLabellings.add(allIn);
//    		return allAdmissibleLabellings;
//    	}
//    	MyLabelling labelling = new MyLabelling(0);
//    	labelling.setInVerties(vertices);
//    	LinkedHashSet<MyVertex> illegallyIn =findAllIllegIn(labelling);
//    	if(illegallyIn.size()==vertices.size()){
//    		System.out.println("All illegally if statement entered");
//    		return(this.allAdmissibleLabellings());
//    	}
//    	
//    	for(int i=0; i<=vertices.size();i++){
//    		LinkedHashSet<MyVertex> currentVertexOrder = reorderSet(vertices);
//    		MyLabelling labelling1 = new MyLabelling(0);
//    		labelling1.setInVerties(currentVertexOrder);
//    		possibleCombinations.add(labelling1);
//    		while(hasillegallyIn(possibleCombinations)){
//    			Iterator<MyLabelling> possibleCombinationsIterator = possibleCombinations.iterator();
//    			while(possibleCombinationsIterator.hasNext()){
//    				LinkedHashSet<ArrayList<MyVertex>> illegInCombinations = new LinkedHashSet<ArrayList<MyVertex>>();
//    				MyLabelling labelling2 = possibleCombinationsIterator.next();
//    				labelling2.setInVerties(labelling2.getInVertices());
//    				labelling2.setOutVertices(labelling2.getOutVertices());
//    				labelling2.setUndecVertices(labelling2.getUndecVertices());
//    				if(hasillegallyIn(labelling2)){
//    				LinkedHashSet<MyVertex> illegIn =findAllIllegIn(labelling2);
//    				ArrayList<MyVertex> illegInArray =new  ArrayList<MyVertex>();
//    				illegInArray.addAll(illegIn);
//    				permute(illegInArray, illegInCombinations, 0);
//    				Iterator<ArrayList<MyVertex>> illegInCombinationIterator = illegInCombinations.iterator();
//    					while(illegInCombinationIterator.hasNext()){
//	    					MyLabelling labelling3 = new MyLabelling(2);
//	    					LinkedHashSet<MyVertex> l2InVertices = new LinkedHashSet<MyVertex>();
//	    					l2InVertices.addAll(labelling2.getInVertices());
//	    					labelling3.setInVerties(l2InVertices);
//	    					LinkedHashSet<MyVertex> l2OutVertices = new LinkedHashSet<MyVertex>();
//	    					l2InVertices.addAll(labelling2.getOutVertices());
//	    					labelling3.setOutVertices(l2OutVertices);
//	    					LinkedHashSet<MyVertex> l2UndecVertices = new LinkedHashSet<MyVertex>();
//	    					l2InVertices.addAll(labelling2.getUndecVertices());
//	    					labelling3.setUndecVertices(l2UndecVertices);
//	    					LinkedHashSet<MyVertex> l2NotVertices = new LinkedHashSet<MyVertex>();
//	    					l2InVertices.addAll(labelling2.getNotLabelledVertices());
//	    					labelling3.setNotLabelledVertices(l2NotVertices);
//	    					LinkedHashSet<MyVertex> tempIllegIn = new LinkedHashSet<MyVertex>();
//	    					tempIllegIn.addAll(illegInCombinationIterator.next());
//	    					Iterator<MyVertex> tempIllegInIterator = tempIllegIn.iterator();
//	    					while(tempIllegInIterator.hasNext()){
//	    						labelling3.correctLabels();
//	    						MyVertex currentVertex = tempIllegInIterator.next();
//	    						if(!(isLegallyIn(currentVertex))){
//			    					labelling3.deleteFromInVertices(currentVertex);
//			    					labelling3.addOutVertex(currentVertex);
//			    					LinkedHashSet<MyVertex> illegOut = new LinkedHashSet<MyVertex>();
//			    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentVertex));
//			    					illegOut.add(currentVertex);
//			    					Iterator<MyVertex> illegOutIterator = illegOut.iterator();
//			    					while(illegOutIterator.hasNext()){
//			    					MyVertex v = illegOutIterator.next();
//				    					if(v.isOut()){
//				    						if(!(isLegallyOut(v))){
//				    							labelling3.deleteFromOutVertices(v);
//				    							labelling3.addUndecVertex(v);
//				    							
//				    						}
//			    						
//				    					}	
//				    					}
//	    						}
//	    					}
//	    					if(!(alreadyContains(possibleCombinations1, labelling3))){
//	    						possibleCombinations1.add(labelling3);
//	    					}
//	    				}
//	//    				possibleCombinations.addAll(possibleCombinations1);	
//	    			}
//	    		}
//    			if(!(alreadyContains(possibleCombinations, possibleCombinations1))){
//    			possibleCombinations.remove(labelling1);
//    			possibleCombinations.addAll(possibleCombinations1);
//    			}
//    			possibleCombinations1.clear();
//    		}
//    		if(!(alreadyContains(allAdmissibleLabellings, possibleCombinations))){
//    		allAdmissibleLabellings.addAll(possibleCombinations);
//    		}
//    		possibleCombinations.clear();
//    	}
//    	
//    	allAdmissibleLabellings.addAll(possibleCombinations);
//    	return allAdmissibleLabellings;
//    	
//    }
    
    public LinkedHashSet<MyLabelling> AFindLabelling(LinkedHashSet<MyLabelling> candidateLabellings, MyLabelling labelling){
 	   MyLabelling currentLabelling = new MyLabelling(0);
 	   currentLabelling.makecopy(labelling);
 	   LinkedHashSet<MyLabelling> currentCandidateLabelling = new LinkedHashSet<MyLabelling>();
 	   currentCandidateLabelling.addAll(candidateLabellings);
 	   if(!(this.hasillegallyIn(currentLabelling))){
 		   MyLabelling labelling1 = new MyLabelling(0);
 		   labelling1.makecopy(currentLabelling);
 		   if(!(alreadyContains(currentCandidateLabelling, labelling1))){
 		   currentCandidateLabelling.add(labelling1);
 		   }
 	   }
    		else{
    			LinkedHashSet<MyArgument> IllegIn =this.findAllIllegIn(currentLabelling);
    			Iterator<MyArgument> IllegInIterator = IllegIn.iterator();
    			LinkedHashSet<MyArgument> illegInCopy =new LinkedHashSet<MyArgument>();
    			illegInCopy.addAll(IllegIn);
    			currentLabelling.correctLabels();
    			LinkedHashSet<MyArgument> copyInVertices = new LinkedHashSet<MyArgument>();
    			copyInVertices.addAll(currentLabelling.getInVertices());
    			while(IllegInIterator.hasNext()){
    				if(!(currentLabelling.getInVertices().containsAll(copyInVertices))){
    					Iterator<MyArgument> illegInCopyIterator = illegInCopy.iterator();
    					currentLabelling.correctLabels();
    					while(illegInCopyIterator.hasNext()){
    						MyArgument vertex =illegInCopyIterator.next();
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
    				MyArgument currentVertex =IllegInIterator.next();
    				MyLabelling transitionLabelling = transitionStep(currentVertex, currentLabelling);
        			currentCandidateLabelling=AFindLabelling(currentCandidateLabelling,transitionLabelling);
    			}
    		}
//    	}
    	
    	return currentCandidateLabelling;
 	   
    }
    
    public LinkedHashSet<MyLabelling> allAdmissibleLabelling(){
 	   MyLabelling allInLabelling = new MyLabelling(0);
 	   allInLabelling.setInVerties(new LinkedHashSet<MyArgument>(this.modelGraph.getMyVertices()));
 	   LinkedHashSet<MyLabelling> allAdmissibleLabellings = new LinkedHashSet<MyLabelling>();
 	   allAdmissibleLabellings=AFindLabelling(allAdmissibleLabellings, allInLabelling);
 	   return allAdmissibleLabellings;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   
    public boolean superIllegallyIn(MyArgument v, MyLabelling labelling ){
    	labelling.correctLabels();
    	if(!(v.getLabel()=="IN")){
    		return false;
    	}
    	else if(this.modelGraph.getmygraph().getPredecessors(v).isEmpty()){
    		return false;
    	}
    	else{
    		LinkedHashSet<MyArgument> predecessors = new LinkedHashSet<MyArgument>(modelGraph.getmygraph().getPredecessors(v));
    		Iterator<MyArgument> predecessorsIterator = predecessors.iterator();
    		while(predecessorsIterator.hasNext()){
    			MyArgument currentVertex = predecessorsIterator.next();
    			if(this.isLegallyIn(currentVertex) || currentVertex.getLabel()=="UNDEC"){
    				return true;
    			}
    		}
    		}
    	return false;
    }
    
    public boolean hasSuperIllegallyIn(MyLabelling labelling){
    	LinkedHashSet<MyArgument> inVertices = labelling.getInVertices();
    	Iterator<MyArgument> inVerticesIterator = inVertices.iterator();
    	while(inVerticesIterator.hasNext()){
    		if(superIllegallyIn(inVerticesIterator.next(), labelling)){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public LinkedHashSet<MyArgument> findSuperIllegallyIn(MyLabelling labelling ){
    	LinkedHashSet<MyArgument> inVertices = labelling.getInVertices();
    	LinkedHashSet<MyArgument> superIllegallyIn = new LinkedHashSet<MyArgument>();
    	Iterator<MyArgument> inVerticesIterator = inVertices.iterator();
    	while(inVerticesIterator.hasNext()){
    		MyArgument currentVertex = inVerticesIterator.next();
    		if(superIllegallyIn(currentVertex, labelling)){
    			superIllegallyIn.add(currentVertex);
    		}
    	}
    	return superIllegallyIn;
    	
    }
    
    public MyLabelling transitionStep(MyArgument vertex, MyLabelling labelling){
    	MyLabelling labelling1 = new MyLabelling(0);
    	labelling1.setInVerties(labelling.getInVertices());
    	labelling1.setOutVertices(labelling.getOutVertices());
    	labelling1.setUndecVertices(labelling.getUndecVertices());
    	labelling1.setNotLabelledVertices(labelling.getNotLabelledVertices());
    	if(this.isLegallyIn(vertex)){
    		return labelling1;
    	}
			labelling1.deleteFromInVertices(vertex);
			labelling1.addOutVertex(vertex);
			LinkedHashSet<MyArgument> illegOutVerticesCheck= new LinkedHashSet<MyArgument>(this.modelGraph.getmygraph().getSuccessors(vertex));
			illegOutVerticesCheck.add(vertex);
			Iterator<MyArgument> illegalIterator =illegOutVerticesCheck.iterator();
			while(illegalIterator.hasNext()){
				MyArgument v = illegalIterator.next();
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
    	MyLabelling admissibleLabelling= new MyLabelling(0);
    	admissibleLabelling.setInVerties(new LinkedHashSet<MyArgument>(labelling.getInVertices()));
    	admissibleLabelling.setOutVertices(new LinkedHashSet<MyArgument>(labelling.getOutVertices()));
    	admissibleLabelling.setUndecVertices(new LinkedHashSet<MyArgument>(labelling.getUndecVertices()));
    	admissibleLabelling.setNotLabelledVertices(new LinkedHashSet<MyArgument>(labelling.getNotLabelledVertices()));
    	int transitionNumber=0;
    	System.out.println("Lablling "+transitionNumber +" is: "+admissibleLabelling);
    	while(this.hasillegallyIn(admissibleLabelling)){
    		LinkedHashSet<MyArgument> illegIn=this.findAllIllegIn(admissibleLabelling);
    		Iterator<MyArgument> illegInIterator =illegIn.iterator();
//    		while(illegInIterator.hasNext()){
//    			MyVertex currentVertex = illegInIterator.next();
//    		admissibleLabelling=this.transitionStep(currentVertex,admissibleLabelling);
    		admissibleLabelling=this.transitionStep(illegInIterator.next(),admissibleLabelling);
    		transitionNumber++;
    		System.out.println("Labelling "+transitionNumber +" is: "+admissibleLabelling);
//    		}
    	}
    	return admissibleLabelling; 
    }
    
    public LinkedHashSet<MyLabelling> preferredLabelling(){
    	System.out.println("Model graph is: "+this.modelGraph.toString());
    	LinkedHashSet<MyLabelling> candidateLabelling = new LinkedHashSet<MyLabelling>();
    	MyLabelling labelling = new MyLabelling(0);
    	labelling.setInVerties( new LinkedHashSet<MyArgument>(this.modelGraph.getMyVertices()));
    	return findLabelling(candidateLabelling, labelling);
    	
    }
    
    
    public LinkedHashSet<MyLabelling> findLabelling(LinkedHashSet<MyLabelling> candidateLabellings, MyLabelling labelling){
    	labelling.correctLabels();
    	MyLabelling currentLabelling = new MyLabelling(0);
    	currentLabelling.setInVerties(new LinkedHashSet<MyArgument>(labelling.getInVertices()));
    	currentLabelling.setOutVertices(new LinkedHashSet<MyArgument>(labelling.getOutVertices()));
    	currentLabelling.setUndecVertices(new LinkedHashSet<MyArgument>(labelling.getUndecVertices()));
    	currentLabelling.setNotLabelledVertices(new LinkedHashSet<MyArgument>(labelling.getNotLabelledVertices()));
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
    		MyLabelling testLabelling = new MyLabelling(0);
        	testLabelling.setInVerties(new LinkedHashSet<MyArgument>(currentLabelling.getInVertices()));
        	testLabelling.setOutVertices(new LinkedHashSet<MyArgument>(currentLabelling.getOutVertices()));
        	testLabelling.setUndecVertices(new LinkedHashSet<MyArgument>(currentLabelling.getUndecVertices()));
        	testLabelling.setNotLabelledVertices(new LinkedHashSet<MyArgument>(currentLabelling.getNotLabelledVertices()));
//    		MyLabelling testLabelling = new MyLabelling(0, new LinkedHashSet<MyVertex>(currentLabelling.getInVertices()),new LinkedHashSet<MyVertex>(currentLabelling.getOutVertices()),new LinkedHashSet<MyVertex>(currentLabelling.getUndecVertices()));
    		if(!(alreadyContains(currentCandidateLabelling, testLabelling))){
    		currentCandidateLabelling.add(testLabelling);
    		}
    		
    		return currentCandidateLabelling;
    	}
    	else{
    		if(hasSuperIllegallyIn(currentLabelling)){
    			currentLabelling.correctLabels();
    			LinkedHashSet<MyArgument> supperIllegVertices =this.findSuperIllegallyIn(currentLabelling);
    			Iterator<MyArgument> superIllegVerticesIterator = supperIllegVertices.iterator();
    			MyArgument vertex =superIllegVerticesIterator.next();
    			MyLabelling transitionLabelling = transitionStep(vertex, currentLabelling);
    			currentCandidateLabelling =findLabelling(currentCandidateLabelling,transitionLabelling);
    		}
    		else{
    			LinkedHashSet<MyArgument> IllegIn =this.findAllIllegIn(currentLabelling);
    			Iterator<MyArgument> IllegInIterator = IllegIn.iterator();
    			LinkedHashSet<MyArgument> illegInCopy =new LinkedHashSet<MyArgument>();
    			illegInCopy.addAll(IllegIn);
    			currentLabelling.correctLabels();
    			while(IllegInIterator.hasNext()){
    				if(!(currentLabelling.getInVertices().containsAll(IllegIn))|| !(IllegIn.containsAll(currentLabelling.getInVertices()))){
    					Iterator<MyArgument> illegInCopyIterator = illegInCopy.iterator();
    					currentLabelling.correctLabels();
    					while(illegInCopyIterator.hasNext()){
    						MyArgument vertex =illegInCopyIterator.next();
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
    				MyArgument currentVertex =IllegInIterator.next();
    				MyLabelling transitionLabelling = transitionStep(currentVertex, currentLabelling);
        			currentCandidateLabelling=findLabelling(currentCandidateLabelling,transitionLabelling);
    			}
    		}
    	}
    	
    	return currentCandidateLabelling;
    	
    }
    

} 