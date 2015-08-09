package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import core.MyVertex;

public class permute{
	 
	static ArrayList<MyVertex> swap(ArrayList<MyVertex> list, int i , int j){
		MyVertex temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		return list;
		
	}
    static Collection<ArrayList<MyVertex>> Permute(ArrayList<MyVertex> currentCombination, Collection<ArrayList<MyVertex>> possibleCombinations,int k){
    	
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
       
        return possibleCombinations;
    }
    public static void main(String[] args){
    	MyVertex v1 = new MyVertex(0);
    	MyVertex v2 = new MyVertex(1);
    	MyVertex v3 = new MyVertex(2);
    	MyVertex v4 = new MyVertex(3);
    	MyVertex v5 = new MyVertex(4);
    	ArrayList<MyVertex> vertices= new ArrayList<MyVertex>();
    	vertices.add(v1);
    	vertices.add(v2);
    	vertices.add(v3);
    	Collection<ArrayList<MyVertex>> possibleCombinations = new LinkedHashSet<ArrayList<MyVertex>>();
        permute.Permute(vertices,possibleCombinations, 0);
        System.out.println(permute.Permute(vertices,possibleCombinations, 0)+"");
    }
}