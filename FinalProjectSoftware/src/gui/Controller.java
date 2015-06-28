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
	public void actionPerformed(ActionEvent e){
		  Object src = e.getSource();

		  if (src == MyView.AddVertexButton) {
			System.out.println("AddVertexButton Pressed");
			MyModel.addVertex();
		  }
		  else if (src == MyView.AddEdgeButton) {
			  System.out.println("AddEdgeButton Pressed");
			  MyView.AskForFromVertex();
			  MyView.MessageFromController.setText("Please select an attacking vertex");
			  MyView.panel.add(MyView.MessageFromController);
		  }
		  else if(src==MyView.EnterButton){
			  System.out.println("EnterButton Pressed");
			  if(MyView.currentVertex==null){
				 MyView.MessageFromController.setText("You have not select a vertex. \n Please select an attacking vertex");
				 MyView.panel.add(MyView.MessageFromController);
//				 
			  }
			  else{
				  MyView.MessageFromController.setText("The attacking vertex will be "+ MyView.currentVertex);
				  MyView.panel.add(MyView.MessageFromController);
				  MyVertex from = new MyVertex();
				  from =MyView.currentVertex;
				  System.out.println("from vertex is "+from);
				  MyView.ClearPicketState();
			  
			  }
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