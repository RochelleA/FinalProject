package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import core.*;
import model.Model;
import gui.View;


class Controller implements ActionListener,ItemListener {

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
		   MyView.setPickingMode(MyModel.graph);
		   MyVertex from = new MyVertex();
		   from.setId(3);
		   from.setLabel("haha");
		   from =MyView.AskForToVertex();
		   
		
		  System.out.println("from vertex is"+from);
//		   MyVertex to= new MyVertex();
//		   to= MyView.AskForFromVertex();
//		   MyModel.graph.addMyEdge(from, to);
		  }
	} 
	
	 
	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.MyModel = m;
	} 
	public void addView(View v){
		System.out.println("Controller: adding view");
		this.MyView = v;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("ItemEvent!");		
	} 
 
} 