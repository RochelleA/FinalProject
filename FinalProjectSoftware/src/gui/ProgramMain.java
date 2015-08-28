package gui;

import javax.swing.SwingUtilities;

import model.Model;


public class ProgramMain{
	static Controller myController = new Controller();
	static Model myModel 	= new Model();

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
      public void run() {
         GUI();
      }
  });

		
	
	

	}	
    private static void GUI(){
    	
    	View myView 	= new View();

  		
  		myModel.addObserver(myView);
  		myController.addModel(myModel);
  		myController.addView(myView);
  		myView.addController(myController);
    
//      JFrame.setDefaultLookAndFeelDecorated(true);
//      JFrame frame = new JFrame("[=] JButton Scores! [=]");
//
//      //Create and set up the content pane.
//      ButtonDemo_Extended demo = new ButtonDemo_Extended();
//      frame.setContentPane(demo.createContentPane());
//
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      frame.setSize(280, 190);
//      frame.setVisible(true);
  

	} 

}