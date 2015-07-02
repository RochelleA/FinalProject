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
	MyGraph ControllerGraph;
	boolean clicked= false;

	MyVertex from = new MyVertex(0);
	MyVertex to = new MyVertex(0);

	Controller() {	
		System.out.println ("Controller()");
	} 
	public void actionPerformed(ActionEvent e){
		  Object src = e.getSource();

		  if (src == MyView.AddVertexButton) {
			  MyView.ViewPickedState.clear();
			  MyView.changeToNoPickingMouse();
			  MyView.MessageFromController.setText("");
			  MyView.panel.add(MyView.MessageFromController);
			System.out.println("AddVertexButton Pressed");
			MyModel.addVertex();
		  }
		  else if (src == MyView.AddEdgeButton) {
			  MyView.ViewPickedState.clear();
			  MyView.currentVertex=null;
			  System.out.println("AddEdgeButton Pressed");
			  MyView.AskForFromVertex();
			  MyView.MessageFromController.setText("Please select an attacking vertex");
			  MyView.panel.add(MyView.MessageFromController);
		  }
		  else if((src==MyView.EnterButton) ){
			  System.out.println("EnterButton Pressed");
			  System.out.println("current"+MyView.currentVertex);
			  if(MyView.currentVertex==null){
				 MyView.MessageFromController.setText("You have not select a vertex. Please select a vertex");
				 MyView.panel.add(MyView.MessageFromController);
			  }
			  else{ 
				check();
			  }
		  }
//		 else{
//			 if ( clicked=false){
//					clicked=true;
//					MyView.MessageFromController.setText("The vertex used will be "+ MyView.currentVertex);
//					MyView.panel.add(MyView.MessageFromController);
//					MyVertex from = new MyVertex();
//					from =MyView.currentVertex;
//					MyModel.storeVertex(from);
//					System.out.println("from vertex is "+from);
//					MyView.ViewPickedState.clear();
//					MyView.changeToNoPickingMouse();
//					MyView.AskForToVertex();
//				}
//			 else {
//				 System.out.println("system help");
//			 }
//		  }
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
	
	public void check(){
		  if(clicked==false){
			  System.out.print(clicked);
				clicked=true;
				MyView.MessageFromController.setText("The vertex used will be "+ MyView.currentVertex);
				MyView.panel.add(MyView.MessageFromController);
				from =MyView.currentVertex;
				MyModel.storeVertex(from);
				System.out.println("from vertex is "+from);
				MyView.ViewPickedState.clear();
				MyView.changeToNoPickingMouse();
				MyView.AskForToVertex();
			  }
		 else if (clicked==true){
			 System.out.print(clicked);
			 clicked=false;
			 if (from ==MyView.currentVertex){
				 MyView.MessageFromController.setText("You have selected the same attacking and attacked vertex. Please start again.");
					MyView.panel.add(MyView.MessageFromController);
					MyView.currentVertex=null;
					MyModel.v1=null;
//					MyView.ViewPickedState.clear();
					MyView.changeToNoPickingMouse();
			 }
			 else{
			
			 MyView.MessageFromController.setText("The vertex used will be "+ MyView.currentVertex);
			MyView.panel.add(MyView.MessageFromController);
				to =MyView.currentVertex;
				MyModel.storeVertex(to);
				System.out.println("to vertex is "+to);
				MyView.ViewPickedState.clear();
				MyView.changeToNoPickingMouse();
				MyView.currentVertex=null;
				System.out.println("We have now reset the currentvertex to be null");

			 
		 }
	}
	}
} 