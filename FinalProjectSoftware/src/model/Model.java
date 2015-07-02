package model;

import core.*;

public class Model extends java.util.Observable {	
	
	public MyGraph ModelGraph = new MyGraph();	
//	DefaultVisualizationModel<MyVertex, MyEdge> vm;
	public MyVertex v1;
	public MyVertex v2;

	public Model(){

		System.out.println("Model()");	
//		CircleLayout<MyVertex, MyEdge> layout = new CircleLayout<MyVertex, MyEdge>(ModelGraph);
//		 vm = new DefaultVisualizationModel<MyVertex, MyEdge>(layout);
//		 PickedState<MyVertex> ViewPickedState= vm.getPickedVertexState();
//		MyVertex v1= new MyVertex();
//		MyVertex v2= new MyVertex();
	} 
	public void addVertex() {
		this.ModelGraph.addMyVertex();
		System.out.println("Model init: counter = " + ModelGraph.toString());
		setChanged();
		notifyObservers(ModelGraph);
		

	}
	
	public void deleteVertex() {
		
		System.out.println("Vertex Deleted-- needs implementing");
		setChanged();
		notifyObservers(ModelGraph);
		
	}
	public void addEdge() {
		// TODO Auto-generated method stub
		
	}
	
	public void storeVertex(MyVertex v){
		if(v1 ==null){
			v1=v;
			System.out.println("I have stored the from vertex as "+ v);
		}
		else if(!(v1==null)){
			v2=v;
			System.out.println("I have stored the to vertex as "+ v);
			ModelGraph.addMyEdge(v1, v2);
			System.out.println("Model init: counter = " + ModelGraph.toString());
			setChanged();
			notifyObservers(ModelGraph);
			v1=null;
			v2=null;
		}
	}
	

} 