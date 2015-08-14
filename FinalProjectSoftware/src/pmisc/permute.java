package pmisc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import core.MyArg;

public class permute{
	 
	static ArrayList<MyArg> swap(ArrayList<MyArg> list, int i , int j){
		MyArg temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
		return list;
		
	}
    static Collection<ArrayList<MyArg>> Permute(ArrayList<MyArg> currentCombination, Collection<ArrayList<MyArg>> possibleCombinations,int k){
    	
        for(int i = k; i < currentCombination.size(); i++){
            currentCombination=swap(currentCombination, i, k);
            possibleCombinations=Permute(currentCombination,possibleCombinations, k+1);
            currentCombination=swap(currentCombination, k, i);
        }
        if (k == currentCombination.size() -1){
            System.out.println(java.util.Arrays.toString(currentCombination.toArray())+currentCombination);
            System.out.println("added");
           ArrayList<MyArg> combination = new ArrayList<MyArg>(currentCombination);
           possibleCombinations.add(combination);
		}
       
        return possibleCombinations;
    }
    public static void main(String[] args){
    	MyArg v1 = new MyArg(0);
    	MyArg v2 = new MyArg(1);
    	MyArg v3 = new MyArg(2);
    	MyArg v4 = new MyArg(3);
    	MyArg v5 = new MyArg(4);
    	ArrayList<MyArg> vertices= new ArrayList<MyArg>();
    	vertices.add(v1);
    	vertices.add(v2);
    	vertices.add(v3);
    	Collection<ArrayList<MyArg>> possibleCombinations = new LinkedHashSet<ArrayList<MyArg>>();
        permute.Permute(vertices,possibleCombinations, 0);
        System.out.println(permute.Permute(vertices,possibleCombinations, 0)+"");
    }
}