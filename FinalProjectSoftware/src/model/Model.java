package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import core.*;

public class Model extends java.util.Observable {	
	
	public MyGraph modelGraph;
//	DefaultVisualizationModel<MyArg, MyAtt> vm;
	public MyArg v1;
	public MyArg v2;
	public int counter =0;
//	public HashSet<MyArg> NotLabelledArgs;

	public Model(){
		 modelGraph = new MyGraph();	

		System.out.println("Model()");	
	} 
	
	public MyArg getV1() {
		return v1;
	}
	public void setV1(MyArg v1) {
		this.v1 = v1;
	}
	public MyArg getV2() {
		return v2;
	}
	public void setV2(MyArg v2) {
		this.v2 = v2;
	}
	public MyArg addMyArg() {
		MyArg v =this.modelGraph.addMyArg();
		setChanged();
		notifyObservers(modelGraph);
		return v;
	}
	
	public void deleteArg(MyArg deleteArg) {
		int ArgId=deleteArg.getId();
		LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>(modelGraph.getMyArgs());
		//hashet to add all Args with a id larger than the id of the Arg we are deleting.
		LinkedHashSet<MyArg> afterDeleteArg = new LinkedHashSet<MyArg>();
		Iterator<MyArg> ArgsIterator = Args.iterator();
		while(ArgsIterator.hasNext()){
			MyArg v= ArgsIterator.next();
			if(v.getId()>ArgId){
				afterDeleteArg.add(v);
			}
		}
		// now iterate through the newer Args and reduce their id by one.
		Iterator<MyArg> afterDeleteArgIterator = afterDeleteArg.iterator();
		while(afterDeleteArgIterator.hasNext()){
			MyArg v1= afterDeleteArgIterator.next();
			v1.setId(v1.getId()-1);
		}
		HashSet<MyAtt> Atts = new HashSet<MyAtt>(this.modelGraph.getMyAtts());
		Iterator<MyAtt> Attsiterator = Atts.iterator();
		while(Attsiterator.hasNext()){
			MyAtt e = Attsiterator.next();
			e.updateLabel();
		}
		//remove the Arg to be deleted
		this.modelGraph.myGraph.removeVertex(deleteArg);
		// reduce the Arg count by 1. This stops new Args having a id one higher than they should.
		int newArgCount= this.modelGraph.GetMyArgCount() -1;
		this.modelGraph.setMyArgCount(newArgCount);
		//Notify the view that the model has changed. 
		setChanged();
		notifyObservers(modelGraph);
		
	}
	
	public MyAtt addAtt(MyArg v){
		if(v1 ==null){
			v1=v;
			System.out.println("I have stored the from Arg as "+ v);
			return null;
		}
		else if(!(v1==null)){
			v2=v;
			System.out.println("I have stored the to Arg as "+ v);
			MyAtt e =modelGraph.addMyAtt(v1, v2);
			System.out.println("Model init: counter = " + modelGraph.toString());
			setChanged();
			notifyObservers(modelGraph);
			v1=null;
			v2=null;
			return e;
		}
		return null;
	}
	
	public void deleteAtt(MyAtt deleteAtt){
		System.out.println("Old Att count is"+modelGraph.GetMyAttCount());
		int newAttCount= this.modelGraph.GetMyAttCount()-1;
		this.modelGraph.setMyAttCount(newAttCount);
		this.modelGraph.myGraph.removeEdge(deleteAtt);
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
		LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>(modelGraph.getMyArgs());
		Iterator<MyArg> ArgsIterator = Args.iterator();
		while(ArgsIterator.hasNext()){
			ArgsIterator.next().setLabel("NONE");
		}
		setChanged();
		notifyObservers(modelGraph);
		return true;
		
	}
	
	public boolean isLegallyIn(MyArg v1){
		if(v1.isUndec()||v1.isOut()){
			return false;
	}
		Collection<MyArg> predecessors = modelGraph.getPredecessors(v1);
		LinkedHashSet<MyArg> attackers = new LinkedHashSet<MyArg>(predecessors);
		Iterator<MyArg> attackersIterator = attackers.iterator();
		int tempCount1=0;
		while(attackersIterator.hasNext()){
			MyArg tempArg = attackersIterator.next();
			if(tempArg.isOut()){
				tempCount1++;
			}
		}
			if(tempCount1==attackers.size() || attackers.size()==0){
				return true;
			}
		
				return false;
	}
	
	
	public boolean isLegallyOut(MyArg v1){
		if(v1.isIn()||v1.isUndec()){
			return false;
		}
		Collection<MyArg> predecessors = modelGraph.getmygraph().getPredecessors(v1);
		LinkedHashSet<MyArg> attackers = new LinkedHashSet<MyArg>(predecessors);
		Iterator<MyArg> attackersIterator = attackers.iterator();
		int count =0;
		while(attackersIterator.hasNext()){
			MyArg tempArg = attackersIterator.next();
			if(tempArg.isIn()){
				count++;
			}
			if(count>=1){
				return true;
			}
		}
		return false;
	}
	
	//Caution before using the following function ensure that there are no Args with a label "NONE"
	public boolean isLegallyUndec(MyArg v1){
		if(v1.isIn()||v1.isOut()){
			return false;
		}
		LinkedHashSet<MyArg> attackers = new LinkedHashSet<MyArg>(this.modelGraph.getmygraph().getPredecessors(v1));
		int tempCount=0;
		Iterator<MyArg> attackersIterator = attackers.iterator();
		while(attackersIterator.hasNext()){
			MyArg tempArg = attackersIterator.next();
			if(tempArg.isOut()){
				tempCount++;
			}
		}
			if(tempCount<attackers.size()){
				while(attackersIterator.hasNext()){
					MyArg tempArg1 = attackersIterator.next();
					if(tempArg1.isIn()){
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
		LinkedHashSet<MyArg> h = labelling.getInArgs();
		LinkedHashSet<MyArg> h1 = h;
		Iterator<MyArg> h1Iterator = h1.iterator();
		while(h1Iterator.hasNext()){
			MyArg v= h1Iterator.next();
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
    	Iterator<MyArg> inIterator = labelling.getInArgs().iterator();
    	while(inIterator.hasNext()){
    		modelGraph.getMyArg(inIterator.next()).setLabel("IN");
    	}
    	Iterator<MyArg> outIterator = labelling.getOutArgs().iterator();
    	while(outIterator.hasNext()){
    		modelGraph.getMyArg(outIterator.next()).setLabel("OUT");
    	}
    	Iterator<MyArg> undecIterator = labelling.getUndecArgs().iterator();
    	while(undecIterator.hasNext()){
    		modelGraph.getMyArg(undecIterator.next()).setLabel("UNDEC");
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
    
    
    private LinkedHashSet<MyArg> findAllIllegIn(MyLabelling labelling){
    	LinkedHashSet<MyArg> illegalInArgs = new LinkedHashSet<MyArg>();
    	LinkedHashSet<MyArg> inArgs = new LinkedHashSet<MyArg>(labelling.getInArgs());
    	Iterator<MyArg> inArgsIterator = inArgs.iterator();
    	while(inArgsIterator.hasNext()){
    		MyArg currentArg = inArgsIterator.next();
    		if(!(this.isLegallyIn(currentArg))){
    			illegalInArgs.add(currentArg);
    			
    		}
    		
    	}
		return illegalInArgs;
    	
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
	public ArrayList<MyArg> findUnattackedArgsArray(){
		ArrayList<MyArg> unattacked = new ArrayList<MyArg>();
		ArrayList<MyArg> ArgList = new ArrayList<MyArg>(this.modelGraph.getMyArgs());
		for (int i=0; i<ArgList.size();i++){
			MyArg currentArg = ArgList.get(i);
			System.out.println(currentArg.toString());
			if(this.modelGraph.getmygraph().getPredecessors(currentArg).isEmpty()){
				unattacked.add(ArgList.get(i));			
			}
			else{
				System.out.println(ArgList.get(i).toString()+" has predecessors");
			}
		}
		return unattacked;
		
	}
	
	public HashSet<MyArg> findUnattackedArgsHashSet(){
		HashSet<MyArg> unattacked = new LinkedHashSet<MyArg>();
		LinkedHashSet<MyArg> ArgList =  new LinkedHashSet<MyArg>(this.modelGraph.getMyArgs());
		Iterator<MyArg> ArgIterator = ArgList.iterator();
		while(ArgIterator.hasNext()){
			MyArg currentArg = ArgIterator.next();
			if(this.modelGraph.getmygraph().getPredecessors(currentArg).isEmpty()){
				System.out.print("The are no predecessors of Arg "+currentArg.toString()+"\n");
				unattacked.add(currentArg);
				
			}else{
				System.out.println(currentArg.toString()+" has predecessors");
			}
		}
		return unattacked;
	}
	
	

	private MyLabelling findL1(){
		LinkedHashSet<MyArg> unattacked = new LinkedHashSet<MyArg>();
		MyLabelling labelling1= new MyLabelling(1);
		labelling1.setNotLabelledArgs(new LinkedHashSet<MyArg>(this.modelGraph.getMyArgs()));
		Iterator<MyArg> ArgIterator = labelling1.getNotLabelledArgs().iterator();
		while(ArgIterator.hasNext()){
			MyArg currentArg = ArgIterator.next();
			if(this.modelGraph.getmygraph().getPredecessors(currentArg).isEmpty()){
				System.out.print("The are no predecessors of Arg "+currentArg.toString()+"\n");
				unattacked.add(currentArg);
				
			}else{
				System.out.println(currentArg.toString()+" has predecessors");
			}
		}
		labelling1.getNotLabelledArgs().removeAll(unattacked);
		System.out.println(" The Unattacked Atts areUA); "+unattacked);
		labelling1.setInVerties(unattacked);
		LinkedHashSet<MyArg> notLabelled = new LinkedHashSet<MyArg>();
		notLabelled.addAll(labelling1.getNotLabelledArgs());
		Iterator<MyArg> notLabelledIterator = notLabelled.iterator();
		while(notLabelledIterator.hasNext()){
			MyArg v= notLabelledIterator.next();
			LinkedHashSet<MyArg> v1Attackers = new LinkedHashSet<MyArg>(this.modelGraph.getmygraph().getPredecessors(v));
			LinkedHashSet<MyArg> intsection = new LinkedHashSet<MyArg>(v1Attackers);
			intsection.retainAll(labelling1.getInArgs());
			if(!(intsection.isEmpty())){
				labelling1.addOutArg(v);
			}
			
		}
		labelling1.getNotLabelledArgs().removeAll(labelling1.getOutArgs());
		return labelling1;
	}
	
	public MyLabelling groundedLabelling(){
		MyLabelling lPrevious = new MyLabelling(0);
		MyLabelling lCurrent= new MyLabelling(0);
		lPrevious=this.findL1();
		System.out.println("labelling1/unattacked is: " +lPrevious.toString());
		while(!(lPrevious==lCurrent)){
			lCurrent=lPrevious;
			LinkedHashSet<MyArg> notIn = new LinkedHashSet<MyArg>();
			notIn.addAll(lPrevious.getNotLabelledArgs());
			Iterator<MyArg> inNotLabelledIterator = notIn.iterator();
			while(inNotLabelledIterator.hasNext()){
				MyArg va= inNotLabelledIterator.next();
				System.out.println("va is: " +va.toString());
				LinkedHashSet<MyArg> vaAttackers = new LinkedHashSet<MyArg>(this.modelGraph.getmygraph().getPredecessors(va));
				LinkedHashSet<MyArg> tempVaAttackers= new LinkedHashSet<MyArg>(vaAttackers);
				System.out.println("Va attacker contains: " +vaAttackers);
				Iterator<MyArg> attackersIterator = vaAttackers.iterator();
				while(attackersIterator.hasNext()){
					MyArg vb = attackersIterator.next();
					System.out.println("vb is :" +vb.toString());
					LinkedHashSet<MyArg> h = new LinkedHashSet<MyArg>();
					if(vb.isOut()){
						h.add(vb);
						System.out.println("h contains"+h);
						attackersIterator.remove();
					}	
					System.out.println("Now TempVattackers contains" +tempVaAttackers +" and h contains" + h);
				if(tempVaAttackers.equals(h)){
					System.out.print(va.toString()+va.getLabel());
					lCurrent.addInArg(va);
					System.out.println(lCurrent.addInArg(va));
					inNotLabelledIterator.remove();
				}
			}
				lPrevious.getNotLabelledArgs().removeAll(lCurrent.getInArgs());
			}
			Iterator<MyArg> outNotLabelledIterator = lPrevious.getNotLabelledArgs().iterator();
			while(outNotLabelledIterator.hasNext()){
				MyArg v1= outNotLabelledIterator.next();
				LinkedHashSet<MyArg> v1Attackers = new LinkedHashSet<MyArg>(this.modelGraph.getmygraph().getPredecessors(v1));
				LinkedHashSet<MyArg> intsection = new LinkedHashSet<MyArg>(v1Attackers);
				intsection.retainAll(lPrevious.getInArgs());
				if(!(intsection.isEmpty())){
					lCurrent.addOutArg(v1);
				}
				
			}
			System.out.println("Current status of the labelling: "+ lCurrent.toString() +"Previous labelling state"+ lPrevious.toString());
		}
		lPrevious.getNotLabelledArgs().removeAll(lPrevious.getOutArgs());
		LinkedHashSet<MyArg> tempNotLabelledArgs = new LinkedHashSet<MyArg>();
		tempNotLabelledArgs.addAll(lPrevious.getNotLabelledArgs());
		lCurrent.setUndecArgs(tempNotLabelledArgs);
		setChanged();
		notifyObservers(modelGraph);
		return lCurrent;
		
	}
	
	
	public LinkedHashSet<MyArg> getGroundedExtension(){
		return this.groundedLabelling().getInArgs();
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	//Admissible	
	
	
	public MyLabelling transitionStep(MyLabelling labelling){
		counter++;
		System.out.print("Transition step entered"+ counter);
		  LinkedHashSet<MyArg> tempInArgs = new LinkedHashSet<MyArg>(labelling.getInArgs());
			Iterator<MyArg> ArgsIterator= tempInArgs.iterator();
			MyArg currentArg;
			while(ArgsIterator.hasNext()){
				currentArg = ArgsIterator.next();
				ArgsIterator.remove();
				if(!(this.isLegallyIn(currentArg))){
					labelling.deleteFromInArgs(currentArg);
					labelling.addOutArg(currentArg);
					LinkedHashSet<MyArg> illegOutArgsCheck= new LinkedHashSet<MyArg>(this.modelGraph.getmygraph().getSuccessors(currentArg));
					illegOutArgsCheck.add(currentArg);
					Iterator<MyArg> illegalIterator =illegOutArgsCheck.iterator();
					while(illegalIterator.hasNext()){
						MyArg v = illegalIterator.next();
						if(v.isOut()){
							if(!(this.isLegallyOut(v))){
								labelling.deleteFromOutArgs(v);
								labelling.addUndecArg(v);
							}
						}
					}
				}
			}
			return labelling;
			
		}
	public MyLabelling transitionStep1(MyLabelling labelling, MyArg argument){
		labelling.deleteFromInArgs(argument);
		labelling.addOutArg(argument);
		LinkedHashSet<MyArg> illegOutArgsCheck= new LinkedHashSet<MyArg>(this.modelGraph.getmygraph().getSuccessors(argument));
		illegOutArgsCheck.add(argument);
		Iterator<MyArg> illegalIterator =illegOutArgsCheck.iterator();
		while(illegalIterator.hasNext()){
			MyArg v = illegalIterator.next();
			if(v.isOut()){
				if(!(this.isLegallyOut(v))){
					labelling.deleteFromOutArgs(v);
					labelling.addUndecArg(v);
				}
			}
		}
			return labelling;
	}
	
	public MyLabelling admissibleLabelling1(){
		LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>(this.modelGraph.getMyArgs());
		MyLabelling currentLabelling = new MyLabelling(0);
		currentLabelling.setInVerties(Args);
		while(this.hasillegallyIn(currentLabelling)){
			  LinkedHashSet<MyArg> tempInArgs = new LinkedHashSet<MyArg>(currentLabelling.getInArgs());
				Iterator<MyArg> ArgsIterator= tempInArgs.iterator();
				MyArg currentArg;
				while(ArgsIterator.hasNext()){
					currentArg = ArgsIterator.next();
					ArgsIterator.remove();
					if(!(this.isLegallyIn(currentArg))){
			currentLabelling=transitionStep1(currentLabelling, currentArg);
					}
				}
		}
		setChanged();
		notifyObservers(modelGraph);
		return currentLabelling;
	}
	
	public MyLabelling admissibleLabelling(){
		LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>(this.modelGraph.getMyArgs());
		MyLabelling currentLabelling = new MyLabelling(0);
		currentLabelling.setInVerties(Args);
		while(this.hasillegallyIn(currentLabelling)){
			currentLabelling=transitionStep(currentLabelling);
		}
		setChanged();
		notifyObservers(modelGraph);
		return currentLabelling;
		
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//AllAdmissible     	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    	//AllAdmissible    
	
	public LinkedHashSet<MyArg> reorderSet(LinkedHashSet<MyArg> h){
		Iterator<MyArg> iterateArgs = h.iterator();
			MyArg current =iterateArgs.next();
			iterateArgs.remove();
			LinkedHashSet<MyArg> h1= h;
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
	static ArrayList<MyArg> swap(ArrayList<MyArg> list, int i , int j){
		MyArg temp = list.get(i);
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
    static Collection<ArrayList<MyArg>> permute(ArrayList<MyArg> currentCombination, Collection<ArrayList<MyArg>> possibleCombinations,int k){
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
           ArrayList<MyArg> combination = new ArrayList<MyArg>(currentCombination);
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
//    	LinkedHashSet<MyArg> graphArgs = new LinkedHashSet<MyArg>(modelGraph.getMyArgs());
//    	ArrayList<MyArg> Args = new ArrayList<MyArg>();
//    	Args.addAll(graphArgs);
//    	LinkedHashSet<ArrayList<MyArg>> possibleCombinations = new LinkedHashSet<ArrayList<MyArg>>();
//    	MyArg currentArg = new MyArg(0);
//    	/*This method finds all possible combinations of the Args. Each combination must be checked in order to find all labellings. 
//    	 * Some combinations will find the same labelling. Duplicates are not added.
//    	 * */
//    	permute(Args, possibleCombinations, 0);
//    	Iterator<ArrayList<MyArg>> possibleCombinationsIterator = possibleCombinations.iterator();
//    	while(possibleCombinationsIterator.hasNext()){
//    		MyLabelling currentLabelling = new MyLabelling(0);
//    		LinkedHashSet<MyArg> ArgOrder = new LinkedHashSet<MyArg>(possibleCombinationsIterator.next());
//    		LinkedHashSet<MyArg> currentArgOrder = new LinkedHashSet<MyArg>();
//    		currentArgOrder.addAll(ArgOrder);
//    		currentLabelling.setInVerties(currentArgOrder);
//    		while(hasillegallyIn(currentLabelling)){
//    			LinkedHashSet<MyArg> tempIn = new LinkedHashSet<MyArg>();
//    			tempIn.addAll(currentLabelling.getInArgs());
//    			LinkedHashSet<MyArg> tempOut = new LinkedHashSet<MyArg>();
//    			tempOut.addAll(currentLabelling.getOutArgs());
//    			Iterator<MyArg> inIterator = tempIn.iterator();
//    			while(inIterator.hasNext()){
//    				MyArg tempv= inIterator.next();
//    				currentArg= tempv;
//    				if(!(isLegallyIn(currentArg))){
//    					currentLabelling.deleteFromInArgs(currentArg);
//    					currentLabelling.addOutArg(currentArg);
//    					LinkedHashSet<MyArg> illegOut = new LinkedHashSet<MyArg>();
//    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentArg));
//    					illegOut.add(currentArg);
//    					Iterator<MyArg> illegOutIterator = illegOut.iterator();
//    					while(illegOutIterator.hasNext()){
//    					MyArg v = illegOutIterator.next();
//	    					if(v.isOut()){
//	    						if(!(isLegallyOut(v))){
//	    							currentLabelling.deleteFromOutArgs(v);
//	    							currentLabelling.addUndecArg(v);
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
//    	LinkedHashSet<MyArg> graphArgs = new LinkedHashSet<MyArg>(modelGraph.getMyArgs());
//    	LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>();
//    	Args.addAll(graphArgs);
//    	LinkedHashSet<ArrayList<MyArg>> possibleCombinations = new LinkedHashSet<ArrayList<MyArg>>();
//    	MyArg currentArg = new MyArg(0);
//    	for(int i=0; i<Args.size(); i++){;
//    		Args =reorderSet(Args);
//    		MyLabelling currentLabelling = new MyLabelling(0);
//    		currentLabelling.setInVerties(Args);
//    	    		while(hasillegallyIn(currentLabelling)){
//    	    			LinkedHashSet<MyArg> illegallyIn = findAllIllegIn(currentLabelling);
//    	    			ArrayList<MyArg> illegallyInArray = new ArrayList<MyArg>();
//    	    			illegallyInArray.addAll(illegallyIn);
//    	    			permute(illegallyInArray, possibleCombinations, 0);
//    	    			Iterator<ArrayList<MyArg>> possibleCombinationIterator = possibleCombinations.iterator();
//    	    			while(possibleCombinationIterator.hasNext()){
//    	    				MyLabelling tempLabelling = new MyLabelling(0);
//    	    				LinkedHashSet<MyArg > tempSet = new LinkedHashSet<MyArg>();
//    	    				tempSet.addAll(possibleCombinationIterator.next());
//    	    				LinkedHashSet<MyArg> newInArgs = new LinkedHashSet<MyArg>();
//    	    				newInArgs.addAll(tempSet);
//    	    				tempLabelling.setInVerties(tempSet);
//    	    				Iterator<MyArg> inIterator = tempLabelling.getInArgs().iterator();
//    	    				while(inIterator.hasNext()){
//    	    					MyArg tempv= inIterator.next();
//    	    					currentArg= tempv;
//			    				if(!(isLegallyIn(currentArg))){
//			    					tempLabelling.deleteFromInArgs(currentArg);
//			    					tempLabelling.addOutArg(currentArg);
//			    					LinkedHashSet<MyArg> illegOut = new LinkedHashSet<MyArg>();
//			    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentArg));
//			    					illegOut.add(currentArg);
//			    					Iterator<MyArg> illegOutIterator = illegOut.iterator();
//			    					while(illegOutIterator.hasNext()){
//			    					MyArg v = illegOutIterator.next();
//				    					if(v.isOut()){
//				    						if(!(isLegallyOut(v))){
//				    							tempLabelling.deleteFromOutArgs(v);
//				    							tempLabelling.addUndecArg(v);
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
//    	LinkedHashSet<MyArg> graphArgs = new LinkedHashSet<MyArg>(this.modelGraph.getMyArgs());
//    	LinkedHashSet<MyArg> Args = new LinkedHashSet<MyArg>();
//    	Args.addAll(graphArgs);
//    	if(modelGraph.GetMyAttCount()==0){
//    		System.out.println("System no Att loop entered");
//    		MyLabelling allIn = new MyLabelling(0);
//    		allIn.setInVerties(graphArgs);
//    		allAdmissibleLabellings.add(allIn);
//    		return allAdmissibleLabellings;
//    	}
//    	MyLabelling labelling = new MyLabelling(0);
//    	labelling.setInVerties(Args);
//    	LinkedHashSet<MyArg> illegallyIn =findAllIllegIn(labelling);
//    	if(illegallyIn.size()==Args.size()){
//    		System.out.println("All illegally if statement entered");
//    		return(this.allAdmissibleLabellings());
//    	}
//    	
//    	for(int i=0; i<=Args.size();i++){
//    		LinkedHashSet<MyArg> currentArgOrder = reorderSet(Args);
//    		MyLabelling labelling1 = new MyLabelling(0);
//    		labelling1.setInVerties(currentArgOrder);
//    		possibleCombinations.add(labelling1);
//    		while(hasillegallyIn(possibleCombinations)){
//    			Iterator<MyLabelling> possibleCombinationsIterator = possibleCombinations.iterator();
//    			while(possibleCombinationsIterator.hasNext()){
//    				LinkedHashSet<ArrayList<MyArg>> illegInCombinations = new LinkedHashSet<ArrayList<MyArg>>();
//    				MyLabelling labelling2 = possibleCombinationsIterator.next();
//    				labelling2.setInVerties(labelling2.getInArgs());
//    				labelling2.setOutArgs(labelling2.getOutArgs());
//    				labelling2.setUndecArgs(labelling2.getUndecArgs());
//    				if(hasillegallyIn(labelling2)){
//    				LinkedHashSet<MyArg> illegIn =findAllIllegIn(labelling2);
//    				ArrayList<MyArg> illegInArray =new  ArrayList<MyArg>();
//    				illegInArray.addAll(illegIn);
//    				permute(illegInArray, illegInCombinations, 0);
//    				Iterator<ArrayList<MyArg>> illegInCombinationIterator = illegInCombinations.iterator();
//    					while(illegInCombinationIterator.hasNext()){
//	    					MyLabelling labelling3 = new MyLabelling(2);
//	    					LinkedHashSet<MyArg> l2InArgs = new LinkedHashSet<MyArg>();
//	    					l2InArgs.addAll(labelling2.getInArgs());
//	    					labelling3.setInVerties(l2InArgs);
//	    					LinkedHashSet<MyArg> l2OutArgs = new LinkedHashSet<MyArg>();
//	    					l2InArgs.addAll(labelling2.getOutArgs());
//	    					labelling3.setOutArgs(l2OutArgs);
//	    					LinkedHashSet<MyArg> l2UndecArgs = new LinkedHashSet<MyArg>();
//	    					l2InArgs.addAll(labelling2.getUndecArgs());
//	    					labelling3.setUndecArgs(l2UndecArgs);
//	    					LinkedHashSet<MyArg> l2NotArgs = new LinkedHashSet<MyArg>();
//	    					l2InArgs.addAll(labelling2.getNotLabelledArgs());
//	    					labelling3.setNotLabelledArgs(l2NotArgs);
//	    					LinkedHashSet<MyArg> tempIllegIn = new LinkedHashSet<MyArg>();
//	    					tempIllegIn.addAll(illegInCombinationIterator.next());
//	    					Iterator<MyArg> tempIllegInIterator = tempIllegIn.iterator();
//	    					while(tempIllegInIterator.hasNext()){
//	    						labelling3.correctLabels();
//	    						MyArg currentArg = tempIllegInIterator.next();
//	    						if(!(isLegallyIn(currentArg))){
//			    					labelling3.deleteFromInArgs(currentArg);
//			    					labelling3.addOutArg(currentArg);
//			    					LinkedHashSet<MyArg> illegOut = new LinkedHashSet<MyArg>();
//			    					illegOut.addAll(modelGraph.myGraph.getSuccessors(currentArg));
//			    					illegOut.add(currentArg);
//			    					Iterator<MyArg> illegOutIterator = illegOut.iterator();
//			    					while(illegOutIterator.hasNext()){
//			    					MyArg v = illegOutIterator.next();
//				    					if(v.isOut()){
//				    						if(!(isLegallyOut(v))){
//				    							labelling3.deleteFromOutArgs(v);
//				    							labelling3.addUndecArg(v);
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
    			LinkedHashSet<MyArg> IllegIn =this.findAllIllegIn(currentLabelling);
    			Iterator<MyArg> IllegInIterator = IllegIn.iterator();
    			LinkedHashSet<MyArg> illegInCopy =new LinkedHashSet<MyArg>();
    			illegInCopy.addAll(IllegIn);
    			currentLabelling.correctLabels();
    			LinkedHashSet<MyArg> copyInArgs = new LinkedHashSet<MyArg>();
    			copyInArgs.addAll(currentLabelling.getInArgs());
    			while(IllegInIterator.hasNext()){
    				if(!(currentLabelling.getInArgs().containsAll(copyInArgs))){
    					Iterator<MyArg> illegInCopyIterator = illegInCopy.iterator();
    					currentLabelling.correctLabels();
    					while(illegInCopyIterator.hasNext()){
    						MyArg Arg =illegInCopyIterator.next();
    						if(Arg.getLabel()=="OUT"){
    							currentLabelling.deleteFromOutArgs(Arg);
    							currentLabelling.addInArg(Arg);
    						}
    						if(Arg.getLabel()=="UNDEC"){
    							currentLabelling.deleteFromUndecArgs(Arg);
    							currentLabelling.addInArg(Arg);
    					
    						}
    					}
    				}
    				MyArg currentArg =IllegInIterator.next();
    				MyLabelling transitionLabelling = transitionStep(currentArg, currentLabelling);
        			currentCandidateLabelling=AFindLabelling(currentCandidateLabelling,transitionLabelling);
    			}
    		}
//    	}
    	
    	return currentCandidateLabelling;
 	   
    }
    
    public LinkedHashSet<MyLabelling> allAdmissibleLabelling(){
 	   MyLabelling allInLabelling = new MyLabelling(0);
 	   allInLabelling.setInVerties(new LinkedHashSet<MyArg>(this.modelGraph.getMyArgs()));
 	   LinkedHashSet<MyLabelling> allAdmissibleLabellings = new LinkedHashSet<MyLabelling>();
 	   allAdmissibleLabellings=AFindLabelling(allAdmissibleLabellings, allInLabelling);
 	   return allAdmissibleLabellings;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   //Preferred   
    public boolean superIllegallyIn(MyArg v, MyLabelling labelling ){
    	labelling.correctLabels();
    	if(!(v.getLabel()=="IN")){
    		return false;
    	}
    	else if(this.modelGraph.getmygraph().getPredecessors(v).isEmpty()){
    		return false;
    	}
    	else{
    		LinkedHashSet<MyArg> predecessors = new LinkedHashSet<MyArg>(modelGraph.getmygraph().getPredecessors(v));
    		Iterator<MyArg> predecessorsIterator = predecessors.iterator();
    		while(predecessorsIterator.hasNext()){
    			MyArg currentArg = predecessorsIterator.next();
    			if(this.isLegallyIn(currentArg) || currentArg.getLabel()=="UNDEC"){
    				return true;
    			}
    		}
    		}
    	return false;
    }
    
    public boolean hasSuperIllegallyIn(MyLabelling labelling){
    	LinkedHashSet<MyArg> inArgs = labelling.getInArgs();
    	Iterator<MyArg> inArgsIterator = inArgs.iterator();
    	while(inArgsIterator.hasNext()){
    		if(superIllegallyIn(inArgsIterator.next(), labelling)){
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public LinkedHashSet<MyArg> findSuperIllegallyIn(MyLabelling labelling ){
    	LinkedHashSet<MyArg> inArgs = labelling.getInArgs();
    	LinkedHashSet<MyArg> superIllegallyIn = new LinkedHashSet<MyArg>();
    	Iterator<MyArg> inArgsIterator = inArgs.iterator();
    	while(inArgsIterator.hasNext()){
    		MyArg currentArg = inArgsIterator.next();
    		if(superIllegallyIn(currentArg, labelling)){
    			superIllegallyIn.add(currentArg);
    		}
    	}
    	return superIllegallyIn;
    	
    }
    
    public MyLabelling transitionStep(MyArg Arg, MyLabelling labelling){
    	MyLabelling labelling1 = new MyLabelling(0);
    	labelling1.setInVerties(labelling.getInArgs());
    	labelling1.setOutArgs(labelling.getOutArgs());
    	labelling1.setUndecArgs(labelling.getUndecArgs());
    	labelling1.setNotLabelledArgs(labelling.getNotLabelledArgs());
    	if(this.isLegallyIn(Arg)){
    		return labelling1;
    	}
			labelling1.deleteFromInArgs(Arg);
			labelling1.addOutArg(Arg);
			LinkedHashSet<MyArg> illegOutArgsCheck= new LinkedHashSet<MyArg>(this.modelGraph.getmygraph().getSuccessors(Arg));
			illegOutArgsCheck.add(Arg);
			Iterator<MyArg> illegalIterator =illegOutArgsCheck.iterator();
			while(illegalIterator.hasNext()){
				MyArg v = illegalIterator.next();
				if(v.isOut()){
					if(!(this.isLegallyOut(v))){
						labelling1.deleteFromOutArgs(v);
						labelling1.addUndecArg(v);
					}
				}
			}
		return labelling1;
    
    }
    
    public MyLabelling transitionSequence(MyLabelling labelling){
    	MyLabelling admissibleLabelling= new MyLabelling(0);
    	admissibleLabelling.setInVerties(new LinkedHashSet<MyArg>(labelling.getInArgs()));
    	admissibleLabelling.setOutArgs(new LinkedHashSet<MyArg>(labelling.getOutArgs()));
    	admissibleLabelling.setUndecArgs(new LinkedHashSet<MyArg>(labelling.getUndecArgs()));
    	admissibleLabelling.setNotLabelledArgs(new LinkedHashSet<MyArg>(labelling.getNotLabelledArgs()));
    	int transitionNumber=0;
    	System.out.println("Lablling "+transitionNumber +" is: "+admissibleLabelling);
    	while(this.hasillegallyIn(admissibleLabelling)){
    		LinkedHashSet<MyArg> illegIn=this.findAllIllegIn(admissibleLabelling);
    		Iterator<MyArg> illegInIterator =illegIn.iterator();
//    		while(illegInIterator.hasNext()){
//    			MyArg currentArg = illegInIterator.next();
//    		admissibleLabelling=this.transitionStep(currentArg,admissibleLabelling);
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
    	labelling.setInVerties( new LinkedHashSet<MyArg>(this.modelGraph.getMyArgs()));
    	return findLabelling(candidateLabelling, labelling);
    	
    }
    
    
    public LinkedHashSet<MyLabelling> findLabelling(LinkedHashSet<MyLabelling> candidateLabellings, MyLabelling labelling){
    	labelling.correctLabels();
    	MyLabelling currentLabelling = new MyLabelling(0);
    	currentLabelling.setInVerties(new LinkedHashSet<MyArg>(labelling.getInArgs()));
    	currentLabelling.setOutArgs(new LinkedHashSet<MyArg>(labelling.getOutArgs()));
    	currentLabelling.setUndecArgs(new LinkedHashSet<MyArg>(labelling.getUndecArgs()));
    	currentLabelling.setNotLabelledArgs(new LinkedHashSet<MyArg>(labelling.getNotLabelledArgs()));
    	LinkedHashSet<MyLabelling> currentCandidateLabelling = new LinkedHashSet<MyLabelling>(candidateLabellings);
    	if(!(currentCandidateLabelling.isEmpty())){
    		    		Iterator<MyLabelling> candidateLabellingIterator= currentCandidateLabelling.iterator();
    		while(candidateLabellingIterator.hasNext()){
    			if(currentLabelling.getInArgs().size()<candidateLabellingIterator.next().getInArgs().size()){
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
    			if(labelling1.getInArgs().size()<currentLabelling.getInArgs().size()){
    				currentCandidateLabelling.remove(labelling1);
    			}
    		}
    		MyLabelling testLabelling = new MyLabelling(0);
        	testLabelling.setInVerties(new LinkedHashSet<MyArg>(currentLabelling.getInArgs()));
        	testLabelling.setOutArgs(new LinkedHashSet<MyArg>(currentLabelling.getOutArgs()));
        	testLabelling.setUndecArgs(new LinkedHashSet<MyArg>(currentLabelling.getUndecArgs()));
        	testLabelling.setNotLabelledArgs(new LinkedHashSet<MyArg>(currentLabelling.getNotLabelledArgs()));
//    		MyLabelling testLabelling = new MyLabelling(0, new LinkedHashSet<MyArg>(currentLabelling.getInArgs()),new LinkedHashSet<MyArg>(currentLabelling.getOutArgs()),new LinkedHashSet<MyArg>(currentLabelling.getUndecArgs()));
    		if(!(alreadyContains(currentCandidateLabelling, testLabelling))){
    		currentCandidateLabelling.add(testLabelling);
    		}
    		
    		return currentCandidateLabelling;
    	}
    	else{
    		if(hasSuperIllegallyIn(currentLabelling)){
    			currentLabelling.correctLabels();
    			LinkedHashSet<MyArg> supperIllegArgs =this.findSuperIllegallyIn(currentLabelling);
    			Iterator<MyArg> superIllegArgsIterator = supperIllegArgs.iterator();
    			MyArg Arg =superIllegArgsIterator.next();
    			MyLabelling transitionLabelling = transitionStep(Arg, currentLabelling);
    			currentCandidateLabelling =findLabelling(currentCandidateLabelling,transitionLabelling);
    		}
    		else{
    			LinkedHashSet<MyArg> IllegIn =this.findAllIllegIn(currentLabelling);
    			Iterator<MyArg> IllegInIterator = IllegIn.iterator();
    			LinkedHashSet<MyArg> illegInCopy =new LinkedHashSet<MyArg>();
    			illegInCopy.addAll(IllegIn);
    			currentLabelling.correctLabels();
    			while(IllegInIterator.hasNext()){
    				if(!(currentLabelling.getInArgs().containsAll(IllegIn))|| !(IllegIn.containsAll(currentLabelling.getInArgs()))){
    					Iterator<MyArg> illegInCopyIterator = illegInCopy.iterator();
    					currentLabelling.correctLabels();
    					while(illegInCopyIterator.hasNext()){
    						MyArg Arg =illegInCopyIterator.next();
    						if(Arg.getLabel()=="OUT"){
    							currentLabelling.deleteFromOutArgs(Arg);
    							currentLabelling.addInArg(Arg);
    						}
    						if(Arg.getLabel()=="UNDEC"){
    							currentLabelling.deleteFromUndecArgs(Arg);
    							currentLabelling.addInArg(Arg);
    					
    						}
    					}
    				}
    				MyArg currentArg =IllegInIterator.next();
    				MyLabelling transitionLabelling = transitionStep(currentArg, currentLabelling);
        			currentCandidateLabelling=findLabelling(currentCandidateLabelling,transitionLabelling);
    			}
    		}
    	}
    	
    	return currentCandidateLabelling;
    	
    }
    

} 