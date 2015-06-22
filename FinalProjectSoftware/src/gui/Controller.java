/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;

import view.DefaultGraph;

import java.awt.event.ActionListener;

import core.MyGraph;
import view.*;

/**
 * @author Rochelle
 *
 */
public class Controller {
	private View MyView;
	private Model MyModel;
	
	public Controller(View MyView, Model MyModel){
		this.MyView=MyView;
		this.MyModel=MyModel;
		

		
		this.MyView.addAddVertexListener(new AddVertexListener());
	}
	
class AddVertexListener implements ActionListener{
	
	public void actionPerformed(ActionEvent e){
		MyModel.addVertex();
		MyView.updateView();
	}
	
	
}
}
