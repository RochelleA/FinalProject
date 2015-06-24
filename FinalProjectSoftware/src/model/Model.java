package model;

import core.MyGraph;


public class Model extends java.util.Observable {	
	
	private MyGraph graph = new MyGraph();	

	public Model(){

		System.out.println("Model()");	

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
	

} 