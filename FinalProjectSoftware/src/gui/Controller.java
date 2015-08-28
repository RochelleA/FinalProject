package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import core.*;
import model.Model;
import gui.View;


class Controller implements ActionListener {
	

	Model MyModel;
	View MyView;
	MyGraph ControllerGraph;
	boolean clickedFlag= false;
	boolean deleteArgFlag =false;
	boolean deleteAttFlag=false;
	MyArg from = new MyArg(0);
	MyArg to = new MyArg(0);
 
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
/* This section of action performed deals with add an argument to the framework. All the flags must be set to false. This in case some has pressed the add delete argument button 
but stopped before picking an edge to delete. Anything that is picked must be removed so an argument can be selected. It is necessary to clear the semantics boxed in case a labelling 
has already been made for a framework. The addition of a new argument would invalidate the labellings. For this reason the labels must also be cleared.  */		  
		  if (src == MyView.getAddArgumentButton()) {

			System.out.println("AddArgButton Pressed");
			deleteArgFlag=false;
			clickedFlag=false;
			deleteAttFlag=false;
			MyView.getViewVV().getPickedVertexState().clear();
			MyView.getViewAttPickedState().clear();
			MyView.changeToNoPickingMouse();
			MyView.clearSemanticsInfoBoxes();
			MyModel.resetLabels();
			MyView.clearSemanticsInfoBoxes();
			MyView.getMessageFromController().setText("Argument "+MyModel.addMyArg()+" has been added.");
			
		  }
		  
/*
 * Again set all flags to be false. In the model class there are two vertices modelFrom and modelTo used to store the to and from vertices when adding an attack. 
 * When the add attack button is pressed it is necessary to ensure that the modelFrom and modelTo are null. 
 * The view also stores the current argument picked and this must be set to null also.
 */
		  else if (src == MyView.getAddAttackButton()) {

			  System.out.println("AddEdgeButton Pressed");
			  deleteArgFlag =false;
			  deleteAttFlag=false;
			  clickedFlag=false;
			  MyModel.setV1(null);
			  MyModel.setV2(null);
			  MyView.currentArg=null;
			  MyModel.resetLabels();
			  MyView.clearSemanticsInfoBoxes();
			  MyView.getViewVV().getPickedEdgeState().clear();
			  MyView.getViewVV().getPickedVertexState().clear();
//			  MyView.clearSemanticsInfoBoxes();
			  MyView.askForFromArgument();
			  MyView.getMessageFromController().setText("Please select an attacking argument and then select enter when you are done.");
		  }
		  /*
		   * All the flags must be set to false. This time a message is displayed to ask the user if they are sure about resetting the framework. 
		   * This function essentially resets the whole system back to the start
		   */
		  else if(src==MyView.getResetGraph()){

			  System.out.println("Reset framework Button pressed");
			  deleteArgFlag=false;
			  deleteAttFlag=false;
			  clickedFlag=false;
			  MyView.clearSemanticsInfoBoxes();
			  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the framework?", "Reset framework?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
			  if(selection==JOptionPane.YES_OPTION){
				  MyModel.resetMyGraph();
				  MyView.getMessageFromController().setText("The framework has been reset");
				  MyView.getGraphString().setText("");
				  MyView.clearSemanticsInfoBoxes();
			  } 
		  }
		  /*
		   * The deleteArgFlag is set to true as we are deleting an argument. The other falgs are set to false.
		   */
		  else if(src==MyView.getDeleteArgumentButton()){
			  System.out.println("Delete Arg button is pressed");
			  deleteAttFlag=false;
			  clickedFlag=false;
			  deleteArgFlag=true;
			  MyView.getViewArgPickedState().clear();
			  MyView.getViewAttPickedState().clear();
			  MyView.deleteArgument=null;
			  MyView.deleteArgument();
		  }
		  else if(src==MyView.getDeleteAttackButton()){
			  deleteAttFlag=true;
			  System.out.println("Delete Att button is pressed");
			  MyView.getViewArgPickedState().clear();
			  MyView.getViewAttPickedState().clear();
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
			  MyView.clearSemanticsInfoBoxes();
		  }
		  else if (src==MyView.getGroundedLabellingButton()){
			  System.out.println("Grounded labelling button pressed");
			  MyModel.resetLabels();
			  MyLabelling l1=MyModel.groundedLabelling();
			  MyView.getGroundedSemanticsInfo().setText("The grounded labelling for your current framework is: \n "+l1.DisplayLabelling());
		  }
		  else if (src==MyView.getAdmissibleLabellingButton()){
			  System.out.println("Preffered labelling button pressed");
			  MyModel.resetLabels();
			  MyLabelling l1=MyModel.admissibleLabelling1();
			  MyView.getAdmissibleSemanticsInfo().setText("An admissible labelling for your current framework is: \n "+l1.DisplayLabelling());
		  }
		  else if(src==MyView.getAllAdmissibleLabellingButton()){
			  System.out.println("All admissible labellings button pressed");
			  MyModel.resetLabels();
			  String string = MyModel.labellingSetString(MyModel.allAdmissibleLabelling());
			  System.out.println("All admissible labellings" + string);
			  MyView.getAllAdmissibleSemanticInfo().setText("The admissible labellings for your current framework are: \n " +MyModel.labellingSetString(MyModel.allAdmissibleLabelling()));
		  }
		  else if(src==MyView.getCompleteButton()){
			  System.out.println("All complete labellings button pressed");
			  MyModel.resetLabels();
			  String string = MyModel.labellingSetString(MyModel.completeLabellings());
			  System.out.println("All complete labellings" + string);
			  MyView.getCompleteSemanticsInfo().setText("The complete labellings for your current framework are: \n " +MyModel.labellingSetString(MyModel.completeLabellings()));
		  }

		  else if((src==MyView.getPreferredButton())){
			  System.out.println("Preferred Button pressed ");
			  MyModel.resetLabels();
			  String s = MyModel.labellingSetString(MyModel.preferredLabelling());
			  System.out.println("All preferred labellings"+s);
			  MyView.getPreferredSemanticsInfo().setText("The preferred labllings for your current framework  are: \n "+MyModel.labellingSetString(MyModel.preferredLabelling()));
			  MyModel.displayALabelling(MyModel.preferredLabelling());
			  
		  }
		  else if((src==MyView.getEnterButton()) ){
			  System.out.println("EnterButton Pressed");
			  System.out.println("current"+MyView.currentArg);
			  System.out.println("deleteCurrent "+MyView.deleteArgument);
			  System.out.println(deleteAttFlag); 
			  if(deleteAttFlag==true && MyView.deleteAttack!=null){
				  MyView.getViewVV().setPickedVertexState(MyView.getViewArgPickedState());
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete attack "+ MyView.deleteAttack.getLabel() +"? This action cannot be undone.", "Delete Attack?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  MyModel.deleteAtt(MyView.deleteAttack);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  MyView.getMessageFromController().setText("Attack "+ MyView.deleteAttack+ " has been deleted.");
					  MyView.deleteAttack=null;
					  deleteAttFlag=false;
				  }
				  else{
					  MyView.getViewAttPickedState().clear();
					  MyView.getViewArgPickedState().clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  } 
			  }
			  if(MyView.deleteArgument!=null&&deleteArgFlag==true){
				  int selection= JOptionPane.showConfirmDialog(null, "Are you sure you want to delete argument "+ MyView.deleteArgument +"? This action cannot be undone.", "Delete Argument?",JOptionPane.YES_NO_OPTION , JOptionPane.QUESTION_MESSAGE);
				  if(selection==JOptionPane.YES_OPTION){
					  System.out.println(MyView.deleteArgument.getId()+""+MyModel.modelGraph.GetMyArgCount());
					  MyModel.deleteArg(MyView.deleteArgument);
					  MyModel.resetLabels();
					  MyView.clearSemanticsInfoBoxes();
					  System.out.println(MyView.deleteArgument.getId()+""+MyModel.modelGraph.GetMyArgCount());
					  if(MyView.deleteArgument.getId()<MyModel.modelGraph.GetMyArgCount()+1){
						  MyView.getMessageFromController().setText("Argument "+ MyView.deleteArgument+ " has been deleted and the numbering of all arguments has been reset.");

					  }
					  else{
						  MyView.getMessageFromController().setText("Argument "+ MyView.deleteArgument+ " has been deleted.");  
					  }
					  MyView.deleteArgument=null;
				  }
				  else{
					  MyView.getViewAttPickedState().clear();
					  MyView.getViewArgPickedState().clear();
					  MyView.colourGraph(MyModel.modelGraph);
				  }

			  }
			  else if(MyView.currentArg==null){
				 MyView.getMessageFromController().setText(MyView.getMessageFromController().getText());
//						 +"You have not select a Arg. Please select a Arg.");
			  }
			  else if(clickedFlag==false){
						  System.out.print(clickedFlag);
							clickedFlag=true;
							MyView.getMessageFromController().setText("The Arg used will be "+ MyView.currentArg);
							from =MyView.currentArg;
							MyModel.addAtt(from);
							System.out.println("from argument is "+from);
							MyView.getViewAttPickedState().clear();
							MyView.getViewArgPickedState().clear();
							MyView.changeToNoPickingMouse();
							MyView.askForToArgument(from);
						  }
					 else if (clickedFlag==true){
						 System.out.print(clickedFlag);
						 
//						 if (from ==MyView.currentArg){
//							 MyView.getMessageFromController().setText("You have selected the same attacking and attacked argument. Please press the \"Add Attack\" button to start again.");
//								MyView.currentArg=null;
//								MyModel.setV1(null);;
//								MyView.changeToNoPickingMouse();
//						 }
//						 else{
							 clickedFlag=false;	
							 to =MyView.currentArg;
							 MyAtt att= MyModel.addAtt(to);
							 System.out.print("att Id is "+att.getId());
								if(!(att.getId()==0)){
									 MyView.getMessageFromController().setText("An attack has been added from "+from+ " to " + MyView.currentArg);
								}
								else if(att.getId()==0){
									MyView.getMessageFromController().setText("This attack att["+  att.getFrom() +","+ att.getTo()+"] already exist. ");
								}
								System.out.println("to argument is "+to);
						
							MyView.getViewAttPickedState().clear();
							MyView.getViewArgPickedState().clear();
							MyView.clearSemanticsInfoBoxes();
							MyView.changeToNoPickingMouse();
							MyView.currentArg=null;
							System.out.println("We have now reset the currentArg to be null");

					 }
				  }
	}
	
	}

