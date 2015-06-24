package gui;


import model.Model;
import gui.View;


class Controller implements java.awt.event.ActionListener {

	Model MyModel;
	View MyView;

	Controller() {	
		System.out.println ("Controller()");
	} 
	public void actionPerformed(java.awt.event.ActionEvent e){
		System.out.println("Controller: acting on Model");
		MyModel.addVertex();
	} 
	public void addModel(Model m){
		System.out.println("Controller: adding model");
		this.MyModel = m;
	} 
	public void addView(View v){
		System.out.println("Controller: adding view");
		this.MyView = v;
	} 
 
} 