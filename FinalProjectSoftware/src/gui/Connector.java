package gui;

import gui.Controller;
import model.Model;
import gui.View;


public class Connector{

	public Connector() {

		
		Model myModel 	= new Model();
		View myView 	= new View();

		
		myModel.addObserver(myView);

		Controller myController = new Controller();
		myController.addModel(myModel);
		myController.addView(myView);
		myView.addController(myController);
		

	} 

} 