package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashSet;

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

		  if (src == MyView.getAddVertexButton()) {
			  deleteVertexFlag=false;
			  clickedFlag=false;
			  deleteEdgeFlag=false;
			  MyView.getViewVV().getPickedVertexState().clear();
			  MyView.getViewEdgePickedState().clear();
			  MyView.changeToNoPickingMouse();
			  MyView.getMessageFromController().setText("");
			  MyView.clearSemanticsInfoBoxes();
//			  MyView.tBoxPanel.add(MyView.getMessageFromController());
			System.out.println("AddVertexButton Pressed");
			MyModel.resetLabels();
			MyModel.addMyVertex();
		  }
		  else if (src == MyView.getAddEdgeButton()) {
			  deleteVertexFlag =false;
			  clickedFlag=false;
			  MyModel.setV1(null);
			  MyModel.setV2(null);
			  MyView.currentVertex=null;
			  MyModel.resetLabels();
			  MyView.getViewVV().getPickedEdgeState().clear();
			  MyView.getViewVV().getPickedVertexState().clear();
			  System.out.println("AddEdgeButton Pressed");
//			  MyView.clearSemanticsInfoBoxes();
			  MyView.askForFromVertex();
			  MyView.getMessageFromController().setText("Please select an attacking argument and then select enter when you are done.");
		  }
		  else if(src==MyView.getResetGraph()){
			  deleteVertexFlag=false;
			  deleteEdgeFlag=false;
			  clickedFlag=false;
			  System.out.println("Reset Graph Button pressed");
			  MyView.clearSemanticsInfoBoxes();
			  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the graph?", "Reset Graph?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
			  if(selection==JOptionPane.YES_OPTION){
				  MyModel.resetMyGraph();
				  MyView.getMessageFromController().setText("");
				  MyView.getGraphString().setText("");
				  MyView.getGroundedSemanticsInfo().setText("");
				  MyView.getAdmissibleSemanticsInfo().setText("");
			  } 
		  }
		  else if(src==MyView.getDeleteVertexButton()){
			  System.out.println("Delete vertex button is pressed");
			  deleteVertexFlag=true;
			  MyView.getViewEdgePickedState().clear();
			  MyView.getViewEdgePickedState().clear();
			  MyView.deleteVertex=null;
			  MyView.deleteVertex();
		  }
		  else if(src==MyView.getDeleteEdgeButton()){
			  deleteEdgeFlag=true;
			  System.out.println("Delete edge button is pressed");
			  MyView.getViewEdgePickedState().clear();
			  MyView.getViewEdgePickedState().clear();
			  MyView.deleteEdge=null;
			  MyView.deleteEdge();
		  }
		  else if(src==MyView.getSemanticsButton()){
			  System.out.print("Semantics button pressed");
			  MyView.changeToSemantics();
		  }
		  else if(src==MyView.getBuildButton()){
			  System.out.print("Build button pressed");
			  MyView.changeToBuild();
		  }
		  else if (src==MyView.getGroundedLabellingButton()){
			  System.out.println("Grounded labelling button pressed");
			  MyModel.resetLabels();
			  MyLabelling l1=MyModel.groundedLabelling();
			  MyView.getGroundedSemanticsInfo().setText("The grounded labelling for your current graph is: \n "+l1.DisplayLabelling());
		  }
		  else if (src==MyView.getAdmissibleLabellingButton()){
			  System.out.println("Preffered labelling button pressed");
			  MyModel.resetLabels();
			  MyLabelling l1=MyModel.admissibleLabelling();
			  MyView.getAdmissibleSemanticsInfo().setText("The admissible labelling for your current graph is: \n "+l1.DisplayLabelling());
		  }
		  else if(src==MyView.getAllAdmissibleLabellingButton()){
			  System.out.println("All Admissible Labelling button pressed");
			  MyModel.resetLabels();
			  String string = MyModel.allAdmissibleLabelling2().toString();
			  System.out.println("All admissible labelling" + string);
			  MyView.getAllAdmissibleSemanticInfo().setText(MyModel.labellingSetString(MyModel.allAdmissibleLabelling2()));
			  LinkedHashSet<MyLabelling> allAdmissibleLabellings = MyModel.allAdmissibleLabelling2();
			  MyModel.labellingSetString(allAdmissibleLabellings);
//			  MyView.openAdmissibleFrame(string);
		  }
		  else if((src==MyView.getPreferredButton())){
			  System.out.println("Preferred Button pressed ");
			  MyModel.resetLabels();
			  String s = MyModel.labellingSetString(MyModel.preferredLabelling());
			  System.out.println(s);
//			  MyView.preferredSemanticsInfo.setText("The preferred labllings are: \n"+s);
			  MyView.getPreferredSemanticsInfo().setText("The preferred labllings are: \n"+MyModel.preferredLabelling());
			  MyModel.labellingSetString(MyModel.preferredLabelling());
			  
		  }
		  else if((src==MyView.getEnterButton()) ){
			  System.out.println("EnterButton Pressed");
			  System.out.println("current"+MyView.currentVertex);
			  System.out.println("deleteCurrent "+MyView.deleteVertex);
			  System.out.println(deleteEdgeFlag);
			  if(deleteEdgeFlag==true && MyView.deleteEdge!=null){
				  MyView.getViewVV().setPickedVertexState(MyView.getViewVertexPickedState());
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete attack "+ MyView.deleteEdge.getLabel() +"? This action cannot be undone.", "Delete Attack?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteEdge(MyView.deleteEdge);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteVertex=null;
					  MyView.getMessageFromController().setText("");
				  }
				  else{
					  MyView.getViewEdgePickedState().clear();
					  MyView.getViewVertexPickedState().clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  } 
			  }
			  if(MyView.deleteVertex!=null&&deleteVertexFlag==true){
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete argument "+ MyView.deleteVertex +"? This action cannot be undone.", "Delete Argument?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteVertex(MyView.deleteVertex);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteVertex=null;
					  MyView.getMessageFromController().setText("");
				  }
				  else{
					  MyView.getViewEdgePickedState().clear();
					  MyView.getViewVertexPickedState().clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  }

			  }
			  else if(MyView.currentVertex==null){
				 MyView.getMessageFromController().setText("You have not select a vertex. Please select a vertex.");
			  }
			  else{ 
				  {
					  if(clickedFlag==false){
						  System.out.print(clickedFlag);
							clickedFlag=true;
							MyView.getMessageFromController().setText("The vertex used will be "+ MyView.currentVertex);
							from =MyView.currentVertex;
							MyModel.addEdge(from);
							System.out.println("from vertex is "+from);
							MyView.getViewEdgePickedState().clear();
							MyView.getViewVertexPickedState().clear();
							MyView.changeToNoPickingMouse();
							MyView.askForToVertex(from);
						  }
					 else if (clickedFlag==true){
						 System.out.print(clickedFlag);
						 
						 if (from ==MyView.currentVertex){
							 MyView.getMessageFromController().setText("You have selected the same attacking and attacked argument. Please press the \"Add Attack\" button to start again.");
								MyView.currentVertex=null;
								MyModel.setV1(null);;
//								MyView.ViewVertexPickedState.clear();
								MyView.changeToNoPickingMouse();
						 }
						 else{
							 clickedFlag=false;
						 MyView.getMessageFromController().setText("An Edge has been added from "+from+ " to " + MyView.currentVertex);
							to =MyView.currentVertex;
							MyModel.addEdge(to);
							System.out.println("to vertex is "+to);
							MyView.getViewEdgePickedState().clear();
							MyView.getViewVertexPickedState().clear();
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