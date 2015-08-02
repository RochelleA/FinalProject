package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;

import core.*;
import model.Model;
import gui.View;


class Controller implements ActionListener,ItemListener {

	Model MyModel;
	View MyView;
	MyGraph ControllerGraph;
	boolean clickedFlag= false;
	boolean deleteVertexFlag =false;
	boolean deleteEdgeFlag=false;
	MyVertex from = new MyVertex(0);
	MyVertex to = new MyVertex(0);
	

	Controller() {	
		System.out.println ("Controller()");
	} 
	public void actionPerformed(ActionEvent e){
		  Object src = e.getSource();

		  if (src == MyView.addVertexButton) {
			  deleteVertexFlag=false;
			  MyView.viewVV.getPickedVertexState().clear();
			  MyView.viewEdgePickedState.clear();
			  MyView.changeToNoPickingMouse();
			  MyView.messageFromController.setText("");
			  MyView.clearSemanticsInfoBoxes();
//			  MyView.graphBuildPanel.add(MyView.messageFromController);
			System.out.println("AddVertexButton Pressed");
			MyModel.resetLabels();
			MyModel.addVertex();
		  }
		  else if (src == MyView.addEdgeButton) {
			  deleteVertexFlag =false;
			  clickedFlag=false;
			  MyModel.v1=null;
			  MyModel.v2=null;
			  MyView.currentVertex=null;
			  MyModel.resetLabels();
			  MyView.viewVV.getPickedEdgeState().clear();
			  MyView.viewVV.getPickedVertexState().clear();
			  System.out.println("AddEdgeButton Pressed");
//			  MyView.clearSemanticsInfoBoxes();
			  MyView.askForFromVertex();
			  MyView.messageFromController.setText("Please select an attacking argument and then select enter when you are done.");
			  MyView.graphBuildPanel.add(MyView.messageFromController);
		  }
		  else if(src==MyView.resetGraph){
			  deleteVertexFlag=false;
			  deleteEdgeFlag=false;
			  clickedFlag=false;
			  System.out.println("Reset Graph Button pressed");
			  MyView.clearSemanticsInfoBoxes();
			  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the graph?", "Reset Graph?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
			  if(selection==JOptionPane.YES_OPTION){
				  MyModel.resetMyGraph();
				  MyView.messageFromController.setText("");
				  MyView.graphString.setText("");
				  MyView.groundedSemanticsInfo.setText("");
				  MyView.preferredSemanticsInfo.setText("");
			  } 
		  }
		  else if(src==MyView.deleteVertexButton){
			  System.out.println("Delete vertex button is pressed");
			  deleteVertexFlag=true;
			  MyView.viewEdgePickedState.clear();
			  MyView.viewEdgePickedState.clear();
			  MyView.deleteVertex=null;
			  MyView.deleteVertex();
		  }
		  else if(src==MyView.deleteEdgeButton){
			  deleteEdgeFlag=true;
			  System.out.println("Delete edge button is pressed");
			  MyView.viewEdgePickedState.clear();
			  MyView.viewEdgePickedState.clear();
			  MyView.deleteEdge=null;
			  MyView.deleteEdge();
		  }
		  else if (src==MyView.groundedLabellingButton){
			  System.out.println("Grounded labelling button pressed");
			  MyLabelling l1=MyModel.groundedLabelling();
			  MyView.groundedSemanticsInfo.setText("The grounded labelling for your current graph is: \n "+l1.DisplayLabelling());
		  }
		  else if (src==MyView.preferredLabellingButton){
			  System.out.println("Preffered labelling button pressed");
			  MyLabelling l1=MyModel.admissibleLabelling();
			  MyView.preferredSemanticsInfo.setText("The Preferred labelling for your current graph is: \n "+l1.DisplayLabelling());
		  }
		  else if(src==MyView.allAddmissibleLabellingButton){
			  System.out.println("All Admissible Labelling button pressed");
			  String string = MyModel.allAdmissibleLabellings().toString();
			  MyView.openAdmissibleFrame(string);
		  }
		  else if((src==MyView.enterButton) ){
			  System.out.println("EnterButton Pressed");
			  System.out.println("current"+MyView.currentVertex);
			  System.out.println("deleteCurrent "+MyView.deleteVertex);
			  System.out.println(deleteEdgeFlag);
			  if(deleteEdgeFlag==true && MyView.deleteEdge!=null){
				  MyView.viewVV.setPickedVertexState(MyView.viewVertexPickedState);
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete attack "+ MyView.deleteEdge.getLabel() +"? This action can not be undone.", "Delete Attack?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteEdge(MyView.deleteEdge);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteVertex=null;
					  MyView.messageFromController.setText("");
				  }
				  else{
					  MyView.viewEdgePickedState.clear();
					  MyView.viewVertexPickedState.clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  } 
			  }
			  if(MyView.deleteVertex!=null&&deleteVertexFlag==true){
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete argument "+ MyView.deleteVertex +"? This action can not be undone.", "Delete Argument?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteVertex(MyView.deleteVertex);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteVertex=null;
					  MyView.messageFromController.setText("");
				  }
				  else{
					  MyView.viewEdgePickedState.clear();
					  MyView.viewVertexPickedState.clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  }

			  }
			  else if(MyView.currentVertex==null){
				 MyView.messageFromController.setText("You have not select a vertex. Please select a vertex.");
			  }
			  else{ 
				  {
					  if(clickedFlag==false){
						  System.out.print(clickedFlag);
							clickedFlag=true;
							MyView.messageFromController.setText("The vertex used will be "+ MyView.currentVertex);
							MyView.graphBuildPanel.add(MyView.messageFromController);
							from =MyView.currentVertex;
							MyModel.addEdge(from);
							System.out.println("from vertex is "+from);
							MyView.viewEdgePickedState.clear();
							MyView.viewVertexPickedState.clear();
							MyView.changeToNoPickingMouse();
							MyView.askForToVertex();
						  }
					 else if (clickedFlag==true){
						 System.out.print(clickedFlag);
						 
						 if (from ==MyView.currentVertex){
							 MyView.messageFromController.setText("You have selected the same attacking and attacked argument. Please start again.");
								MyView.currentVertex=null;
								MyModel.v1=null;
//								MyView.ViewVertexPickedState.clear();
								MyView.changeToNoPickingMouse();
						 }
						 else{
							 clickedFlag=false;
						 MyView.messageFromController.setText("The argument used will be "+ MyView.currentVertex);
							to =MyView.currentVertex;
							MyModel.addEdge(to);
							System.out.println("to vertex is "+to);
							MyView.viewEdgePickedState.clear();
							MyView.viewVertexPickedState.clear();
							 MyView.clearSemanticsInfoBoxes();
							MyView.changeToNoPickingMouse();
							MyView.currentVertex=null;
							System.out.println("We have now reset the currentvertex to be null");

						 
					 }
				}
				}			  }
		  }
	}
	
	
	 
	public void addModel(Model m){
		this.MyModel = m;
	} 
	public void addView(View v){
		this.MyView = v;
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("ItemEvent!");		
	} 
} 