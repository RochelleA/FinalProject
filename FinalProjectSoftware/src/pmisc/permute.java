package pmisc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import core.MyArgument;

public class permute{
	 
	static ArrayList<MyArgument> swap(ArrayList<MyArgument> list, int i , int j){
		MyArgument temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		return list;
		
	}
    static Collection<ArrayList<MyArgument>> Permute(ArrayList<MyArgument> currentCombination, Collection<ArrayList<MyArgument>> possibleCombinations,int k){
    	
        for(int i = k; i < currentCombination.size(); i++){
            currentCombination=swap(currentCombination, i, k);
            possibleCombinations=Permute(currentCombination,possibleCombinations, k+1);
            currentCombination=swap(currentCombination, k, i);
        }
        if (k == currentCombination.size() -1){
            System.out.println(java.util.Arrays.toString(currentCombination.toArray())+currentCombination);
            System.out.println("added");
           ArrayList<MyArgument> combination = new ArrayList<MyArgument>(currentCombination);
           possibleCombinations.add(combination);
		}
       
        return possibleCombinations;
    }
    public static void main(String[] args){
    	MyArgument v1 = new MyArgument(0);
    	MyArgument v2 = new MyArgument(1);
    	MyArgument v3 = new MyArgument(2);
    	MyArgument v4 = new MyArgument(3);
    	MyArgument v5 = new MyArgument(4);
    	ArrayList<MyArgument> vertices= new ArrayList<MyArgument>();
    	vertices.add(v1);
    	vertices.add(v2);
    	vertices.add(v3);
    	Collection<ArrayList<MyArgument>> possibleCombinations = new LinkedHashSet<ArrayList<MyArgument>>();
        permute.Permute(vertices,possibleCombinations, 0);
        System.out.println(permute.Permute(vertices,possibleCombinations, 0)+"");
    }
}