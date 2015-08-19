package gui;

import model.Model;


public class ProgramMain{

	public static void main(String[] args){


		View myView 	= new View();
		Controller myController = new Controller();
		Model myModel 	= new Model();
	

		
		myModel.addObserver(myView);
		myController.addModel(myModel);
		myController.addView(myView);
		myView.addController(myController,myController);
		

	} 

}