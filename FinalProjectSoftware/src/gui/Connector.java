package gui;

import gui.Controller;
import model.Model;
import gui.View;


public class Connector{

	public Connector() {

		View myView 	= new View();
		Controller myController = new Controller();
		Model myModel 	= new Model();
	

		
		myModel.addObserver(myView);
		myController.addModel(myModel);
		myController.addView(myView);
		myView.addController(myController,myController);
	

	} 

} 