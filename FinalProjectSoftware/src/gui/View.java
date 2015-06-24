package gui;

import core.*;

import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;



class View extends JFrame implements java.util.Observer {
	
	private static final long serialVersionUID = 2118299654730994785L;

	private JButton AddVertexButton = new JButton("Add Vertex");
	private JTextField GraphString= new JTextField("");
	private JPanel GraphDisplayPanel= new JPanel();
	private JPanel DisplayPanel= new JPanel();
	private final JPanel ButtonsPanel = new JPanel();
	private VisualizationViewer<MyVertex,MyEdge> VV;

	
	
	View() {

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
		this.setVisible(true);
		
   

	
		}
	 
  	public void update(Observable obs, Object obj) {
  		System.out.println("Updated");
  		MyGraph y=(MyGraph)obj;
  		String z= y.toString();
  		GraphString.setText(z);
 		displaygraph(y);
//  		
//		GraphString.setText("" + ((MyGraph)obj).toString());	//obj is an Object, need to cast to an MyGraph type

  	} 
  	
	public void addController(ActionListener controller){
		System.out.println("View      : adding controller");
		AddVertexButton.addActionListener(controller);	
	} 
	
	public void displaygraph(MyGraph g){
	        Layout<MyVertex, MyEdge> layout = new StaticLayout<MyVertex,MyEdge>(g);
	        layout.setSize(new Dimension(300,300));
	        VisualizationViewer<MyVertex,MyEdge> vv = new VisualizationViewer<MyVertex,MyEdge>(layout);
	        vv.setPreferredSize(new Dimension(350,350));
	        // Show vertex and edge labels
//	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
//	        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	        // Create a graph mouse and add it to the visualization viewer
	        // Our Vertices are going to be Integer objects so we need an Integer factory
//	        EditingModalGraphMouse<MyVertex,MyEdge> gm = new EditingModalGraphMouse<MyVertex,MyEdge>(vv.getRenderContext(), sgv.vertexFactory, sgv.edgeFactory); 
//	        vv.setGraphMouse(gm);
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);   
	        
	     
	       
	        getContentPane().add(vv); 
	        DisplayPanel.setBounds(480, 0, 164, 415);
	        this.add(DisplayPanel);
	        DisplayPanel.setVisible(true);
	        DisplayPanel.revalidate();
	       DisplayPanel.repaint();
	       
	}
	} 
