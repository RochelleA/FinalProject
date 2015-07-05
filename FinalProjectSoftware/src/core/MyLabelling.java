package core;

import java.util.ArrayList;
import java.util.HashSet;

public class MyLabelling implements IMyLabelling {
	
	int id;
	HashSet<MyVertex> InLabels;
	HashSet<MyVertex> OutLabels;
	HashSet<MyVertex> UndecLabels;
	

	public MyLabelling(int id) {
		this.id = id;
		InLabels= new HashSet<MyVertex>();
		OutLabels = new HashSet<MyVertex>();
		UndecLabels = new HashSet<MyVertex>();
		
	}

	@Override
	public HashSet<MyVertex> getInVertices() {
		return InLabels;
	}

	@Override
	public void addInVertex(MyVertex v) {
		InLabels.add(v);

	}
	
	public HashSet<MyVertex> setInVerties(HashSet<MyVertex> h1){
		InLabels=h1;
		return InLabels;
	}

	@Override
	public HashSet<MyVertex> getOutVertices() {
		return  OutLabels;
	}

	@Override
	public void addOutVertex(MyVertex v) {
		OutLabels.add(v);
	}

	@Override
	public HashSet<MyVertex> getUndecVertices() {
		return UndecLabels;
	}

	@Override
	public void addUndecVertex(MyVertex v) {
		UndecLabels.add(v);
	}

}
