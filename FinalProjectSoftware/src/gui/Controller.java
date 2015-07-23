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

		  if (src == MyView.AddVertexButton) {
			  deleteVertexFlag=false;
			  MyView.ViewVV.getPickedVertexState().clear();
			  MyView.ViewEdgePickedState.clear();
			  MyView.changeToNoPickingMouse();
			  MyView.MessageFromController.setText("");
			  MyView.clearSemanticsInfoBoxes();
			  MyView.graphBuildPanel.add(MyView.MessageFromController);
			System.out.println("AddVertexButton Pressed");
			MyModel.resetLabels();
			MyModel.addVertex();
		  }
		  else if (src == MyView.AddEdgeButton) {
			  deleteVertexFlag =false;
			  clickedFlag=false;
			  MyModel.v1=null;
			  MyModel.v2=null;
			  MyView.currentVertex=null;
			  MyModel.resetLabels();
			  MyView.ViewVV.getPickedEdgeState().clear();
			  MyView.ViewVV.getPickedVertexState().clear();
			  System.out.println("AddEdgeButton Pressed");
//			  MyView.clearSemanticsInfoBoxes();
			  MyView.askForFromVertex();
			  MyView.MessageFromController.setText("Please select an attacking argument and then select enter when you are done.");
			  MyView.graphBuildPanel.add(MyView.MessageFromController);
		  }
		  else if(src==MyView.ResetGraph){
			  deleteVertexFlag=false;
			  deleteEdgeFlag=false;
			  clickedFlag=false;
			  System.out.println("Reset Graph Button pressed");
			  MyView.clearSemanticsInfoBoxes();
			  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the graph?", "Reset Graph?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
			  if(selection==JOptionPane.YES_OPTION){
				  MyModel.resetMyGraph();
				  MyView.MessageFromController.setText("");
				  MyView.GraphString.setText("");
				  MyView.GroundedSemanticsInfo.setText("");
				  MyView.PreferredSemanticsInfo.setText("");
			  } 
		  }
		  else if(src==MyView.DeleteVertexButton){
			  System.out.println("Delete vertex button is pressed");
			  deleteVertexFlag=true;
			  MyView.ViewEdgePickedState.clear();
			  MyView.ViewEdgePickedState.clear();
			  MyView.deleteVertex=null;
			  MyView.deleteMyVertex();
		  }
		  else if(src==MyView.DeleteEdgeButton){
			  deleteEdgeFlag=true;
			  System.out.println("Delete edge button is pressed");
			  MyView.ViewEdgePickedState.clear();
			  MyView.ViewEdgePickedState.clear();
			  MyView.deleteEdge=null;
			  MyView.deleteMyEdge();
		  }
		  else if (src==MyView.GroundedLabellingButton){
			  System.out.println("Grounded labelling button pressed");
			  MyLabelling l1=MyModel.GroundedLabelling();
			  MyView.GroundedSemanticsInfo.setText("The grounded labelling for your current graph is: \n "+l1.DisplayLabelling());
		  }
		  else if (src==MyView.PreferredLabellingButton){
			  System.out.println("Preffered labelling button pressed");
			  MyLabelling l1=MyModel.AdmissibleLabelling();
			  MyView.PreferredSemanticsInfo.setText("The Preferred labelling for your current graph is: \n "+l1.DisplayLabelling());
		  }
		  else if(src==MyView.AllAddmissibleLabellingButton){
			  System.out.println("All Admissible Labelling button pressed");
			  String string = MyModel.allAdmissibleLabellings().toString();
			  MyView.openAdmissibleFrame(string);
		  }
		  else if((src==MyView.EnterButton) ){
			  System.out.println("EnterButton Pressed");
			  System.out.println("current"+MyView.currentVertex);
			  System.out.println("deleteCurrent "+MyView.deleteVertex);
			  System.out.println(deleteEdgeFlag);
			  if(deleteEdgeFlag==true && MyView.deleteEdge!=null){
				  MyView.ViewVV.setPickedVertexState(MyView.ViewVertexPickedState);
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete attack "+ MyView.deleteEdge.getLabel() +"? This action can not be undone.", "Delete Attack?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteMyEdge(MyView.deleteEdge);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteVertex=null;
					  MyView.MessageFromController.setText("");
				  }
				  else{
					  MyView.ViewEdgePickedState.clear();
					  MyView.ViewVertexPickedState.clear();
					  MyView.colourGraph(MyModel.ModelGraph);
				  } 
			  }
			  if(MyView.deleteVertex!=null&&deleteVertexFlag==true){
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete argument "+ MyView.deleteVertex +"? This action can not be undone.", "Delete Argument?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteMyVertex(MyView.deleteVertex);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteVertex=null;
					  MyView.MessageFromController.setText("");
				  }
				  else{
					  MyView.ViewEdgePickedState.clear();
					  MyView.ViewVertexPickedState.clear();
					  MyView.colourGraph(MyModel.ModelGraph);
				  }

			  }
			  else if(MyView.currentVertex==null){
				 MyView.MessageFromController.setText("You have not select a vertex. Please select a vertex.");
			  }
			  else{ 
				  {
					  if(clickedFlag==false){
						  System.out.print(clickedFlag);
							clickedFlag=true;
							MyView.MessageFromController.setText("The vertex used will be "+ MyView.currentVertex);
							MyView.graphBuildPanel.add(MyView.MessageFromController);
							from =MyView.currentVertex;
							MyModel.addEdge(from);
							System.out.println("from vertex is "+from);
							MyView.ViewEdgePickedState.clear();
							MyView.ViewVertexPickedState.clear();
							MyView.changeToNoPickingMouse();
							MyView.askForToVertex();
						  }
					 else if (clickedFlag==true){
						 System.out.print(clickedFlag);
						 
						 if (from ==MyView.currentVertex){
							 MyView.MessageFromController.setText("You have selected the same attacking and attacked argument. Please start again.");
								MyView.currentVertex=null;
								MyModel.v1=null;
//								MyView.ViewVertexPickedState.clear();
								MyView.changeToNoPickingMouse();
						 }
						 else{
							 clickedFlag=false;
						 MyView.MessageFromController.setText("The argument used will be "+ MyView.currentVertex);
							to =MyView.currentVertex;
							MyModel.addEdge(to);
							System.out.println("to vertex is "+to);
							MyView.ViewEdgePickedState.clear();
							MyView.ViewVertexPickedState.clear();
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