package model;

import core.MyGraph;

public class Model extends java.util.Observable {	
	
	public MyGraph graph = new MyGraph();	
//	DefaultVisualizationModel<MyVertex, MyEdge> vm;

	public Model(){

		System.out.println("Model()");	
//		CircleLayout<MyVertex, MyEdge> layout = new CircleLayout<MyVertex, MyEdge>(graph);
//		 vm = new DefaultVisualizationModel<MyVertex, MyEdge>(layout);
//		 PickedState<MyVertex> ViewPickedState= vm.getPickedVertexState();
	} 
	public void addVertex() {
		this.graph.addMyVertex();
		System.out.println("Model init: counter = " + graph.toString());
		setChanged();
		notifyObservers(graph);
		

	}
	
	public void deleteVertex() {
		
		System.out.println("Vertex Deleted-- needs implementing");
		setChanged();
		notifyObservers(graph);
		
	}
	public void addEdge() {
		// TODO Auto-generated method stub
		
	}
	

} 