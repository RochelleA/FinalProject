package gui;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import core.MyVertex;
import edu.uci.ics.jung.visualization.picking.PickedState;
import model.Model;
import gui.View;


class Controller implements java.awt.event.ActionListener,ItemListener {

	Model MyModel;
	View MyView;

	Controller() {	
		System.out.println ("Controller()");
	} 
	public void actionPerformed(java.awt.event.ActionEvent e){
		  Object src = e.getSource();

		  if (src == MyView.AddVertexButton) {
			  System.out.println("Vertex is added");
				MyModel.addVertex();
		  }
		  else if (src == MyView.AddEdgeButton) {
		   System.out.println("A vertex will be added");
		   MyView.AskForToVertex();
		   
		   MyModel.addEdge();
		  }
	} 

		
		public void itemStateChanged(ItemEvent e) {
//			Object subject = e.getItem();
//			if (subject == MyView.getPickedState()){
//				@SuppressWarnings("unchecked")
//				PickedState<MyVertex> p = (PickedState<MyVertex>) subject;
//			if (subject instanceof MyVertex) {
//				MyVertex vertex = (MyVertex) subject;
//				if (View.pickedState.isPicked(vertex)) {
//					MyView.currentVertex=vertex;
//					System.out.println(MyView.currentVertex.toString());
//					System.out.println("Vertex " + vertex
//							+ " is now selected");
//				} else {
//					System.out.println("Vertex " + vertex
//							+ " no longer selected");
//				}
//			}
//		}
		}
	
	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.MyModel = m;
	} 
	public void addView(View v){
		System.out.println("Controller: adding view");
		this.MyView = v;
	} 
 
} 