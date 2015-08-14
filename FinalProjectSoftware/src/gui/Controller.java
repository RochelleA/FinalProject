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
	MyArgument from = new MyArgument(0);
	MyArgument to = new MyArgument(0);
 
	public void addModel(Model m){
		this.MyModel = m;
	} 
	
//	public void addView(BasicVersionView v){
//		this.MyBasicView=v;
//	}
	public void addView(View v){
		this.MyView = v;
	}
	
	public void actionPerformed(ActionEvent e){
		  Object src = e.getSource();

		  if (src == MyView.getAddArgumentButton()) {
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
		  else if (src == MyView.getAddAttackButton()) {
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
			  MyView.askForFromArgument();
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
		  else if(src==MyView.getDeleteArgumentButton()){
			  System.out.println("Delete vertex button is pressed");
			  deleteVertexFlag=true;
			  MyView.getViewEdgePickedState().clear();
			  MyView.getViewEdgePickedState().clear();
			  MyView.deleteArgument=null;
			  MyView.deleteArgument();
		  }
		  else if(src==MyView.getDeleteAttackButton()){
			  deleteEdgeFlag=true;
			  System.out.println("Delete edge button is pressed");
			  MyView.getViewEdgePickedState().clear();
			  MyView.getViewEdgePickedState().clear();
			  MyView.deleteAttack=null;
			  MyView.deleteAttack();
		  }
		  else if(src==MyView.getSemanticsButton()){
			  System.out.print("Semantics button pressed");
			  MyView.changeToSemantics();
		  }
		  else if(src==MyView.getBuildButton()){
			  System.out.print("Build button pressed");
			  MyView.changeToBuild();
		  }
		  else if(src==MyView.getResetLabelsButton()){
			  System.out.println("Reset Labels Button pressed");
			  MyModel.resetLabels();
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
			  MyView.getAdmissibleSemanticsInfo().setText("An admissible labelling for your current graph is: \n "+l1.DisplayLabelling());
		  }
		  else if(src==MyView.getAllAdmissibleLabellingButton()){
			  System.out.println("All admissible labellings button pressed");
			  MyModel.resetLabels();
			  String string = MyModel.labellingSetString(MyModel.allAdmissibleLabelling());
			  System.out.println("All admissible labelling" + string);
			  MyView.getAllAdmissibleSemanticInfo().setText("The admissible labellings for your graph are: \n" +MyModel.labellingSetString(MyModel.allAdmissibleLabelling()));
		  }

		  else if((src==MyView.getPreferredButton())){
			  System.out.println("Preferred Button pressed ");
			  MyModel.resetLabels();
			  String s = MyModel.labellingSetString(MyModel.preferredLabelling());
			  System.out.println(s);
//			  MyView.preferredSemanticsInfo.setText("The preferred labellings are: \n"+s);
			  MyView.getPreferredSemanticsInfo().setText("The preferred labllings for your graph  are: \n"+MyModel.labellingSetString(MyModel.preferredLabelling()));
			  MyModel.displayALabelling(MyModel.preferredLabelling());
			  
		  }
		  else if((src==MyView.getEnterButton()) ){
			  System.out.println("EnterButton Pressed");
			  System.out.println("current"+MyView.currentVertex);
			  System.out.println("deleteCurrent "+MyView.deleteArgument);
			  System.out.println(deleteEdgeFlag); 
			  if(deleteEdgeFlag==true && MyView.deleteAttack!=null){
				  MyView.getViewVV().setPickedVertexState(MyView.getViewVertexPickedState());
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete attack "+ MyView.deleteAttack.getLabel() +"? This action cannot be undone.", "Delete Attack?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteEdge(MyView.deleteAttack);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteArgument=null;
					  MyView.getMessageFromController().setText("");
					  deleteEdgeFlag=false;
				  }
				  else{
					  MyView.getViewEdgePickedState().clear();
					  MyView.getViewVertexPickedState().clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  } 
			  }
			  if(MyView.deleteArgument!=null&&deleteVertexFlag==true){
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete argument "+ MyView.deleteArgument +"? This action cannot be undone.", "Delete Argument?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteVertex(MyView.deleteArgument);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.deleteArgument=null;
					  MyView.getMessageFromController().setText("");
				  }
				  else{
					  MyView.getViewEdgePickedState().clear();
					  MyView.getViewVertexPickedState().clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  }

			  }
			  else if(MyView.currentVertex==null){
				 MyView.getMessageFromController().setText(MyView.getMessageFromController().getText());
//						 +"You have not select a vertex. Please select a vertex.");
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
							MyView.askForToArgument(from);
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
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		System.out.println("There as been an item event");		
	} 
} 