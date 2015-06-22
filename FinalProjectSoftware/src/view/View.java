/**
 * 
 */
package view;

import java.awt.event.ActionListener;

import javax.swing.*;

import core.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * @author Rochelle
 *
 */
public class View extends JFrame{

	private static final long serialVersionUID = 2118299654730994785L;

	private JButton AddVertexButton = new JButton("Add Vertex");

	public View(){
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(313, 164);
	
	JPanel MainPanel = new JPanel();
	getContentPane().add(MainPanel, BorderLayout.NORTH);
		MainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JPanel GraphDisplayPanel = new JPanel();
		GraphDisplayPanel.setSize(275, 175);
		MainPanel.add(GraphDisplayPanel);
		JLabel GraphDisplaylLabel = new JLabel("Graph Display");
		GraphDisplayPanel.add(GraphDisplaylLabel);
		JPanel EditGraphPanel= new JPanel();
		MainPanel.add(EditGraphPanel);
		JLabel EditGraphLabel = new JLabel("Edit Graph");
		EditGraphPanel.add(EditGraphLabel);
		EditGraphPanel.add(AddVertexButton);
		
	this.pack();
	

	}
	
	public void DisplayGraph(MyGraph graph){
		System.out.println("the Graph is displayed");
	}
	
	public void DisplayGraph(DefaultGraph graph){
		System.out.println("the Graph is displayed");
	}
	
	public void addAddVertexListener(ActionListener listenForAddVertexButton){
		
			        AddVertexButton.addActionListener(listenForAddVertexButton);
		    }
//public void updateView(MyGraph g1){
//		
//		System.out.println("not yet implemented");
//	}
//	
	public void updateView(){
		System.out.println("not yet implemented");
	}
}
