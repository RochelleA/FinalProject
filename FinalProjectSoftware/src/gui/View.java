package gui;

import core.*;

import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

import javax.swing.border.BevelBorder;

import java.awt.Color;



class View extends JFrame implements java.util.Observer {
	
	private static final long serialVersionUID = 2118299654730994785L;

	private JPanel contentPane;
	JPanel panel = new JPanel();
	private  JLabel lblGraphDisplay = new JLabel("Graph Display",SwingConstants.CENTER);
	JButton AddVertexButton = new JButton("Add Vertex");
	JButton AddEdgeButton = new JButton("Add Edge");
	JButton EnterButton = new JButton("Enter");
	private JTextArea GraphString= new JTextArea();
	JTextArea MessageFromController= new JTextArea();
	private JPanel DisplayGraph = new JPanel();
	private MyGraph ViewGraph = new MyGraph();
	private CircleLayout<MyVertex, MyEdge> ViewLayout;
	private VisualizationViewer<MyVertex,MyEdge> ViewVV;
	 PickedState<MyVertex> ViewPickedState;
	private DefaultModalGraphMouse<MyVertex, MyEdge> gm;
	MyVertex currentVertex;
	MyPluggableGraphMouse NoPickMouse;
	
	View() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBounds(0, 0, 950, 950);
		
		setContentPane(contentPane);
		
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setBounds(0, 0, 700, 700);
		panel.setLayout(null);
		AddVertexButton.setBounds(300, 80, 100, 50);
		AddEdgeButton.setBounds(430, 80, 100, 50);
		EnterButton.setBounds(300, 160, 100, 50);
		GraphString.setBounds(30, 160,240, 100);
		MessageFromController.setBounds(30, 80, 240, 50);
		lblGraphDisplay.setBounds(300, 30, 100, 50);
		DisplayGraph.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		DisplayGraph.setBounds(30, 290, 500, 375);
		panel.add(DisplayGraph);
		panel.add(AddVertexButton);
		panel.add(AddEdgeButton);
		panel.add(EnterButton);
		panel.add(GraphString);
		panel.add(MessageFromController);
		panel.add(lblGraphDisplay);
		
		ViewLayout = new CircleLayout<MyVertex, MyEdge>(ViewGraph);
        ViewLayout.setSize(new Dimension(400,275));
		ViewVV = new VisualizationViewer<MyVertex,MyEdge>(ViewLayout,new Dimension(400,275));
		ViewPickedState= ViewVV.getPickedVertexState();
		NoPickMouse = new MyPluggableGraphMouse();
        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);   
        gm = new DefaultModalGraphMouse<MyVertex, MyEdge>();
        DisplayGraph.add(ViewVV);		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
		
   

	
		}
	 
  	public void update(Observable obs, Object obj) {
  		System.out.println("Updated");
  		MyGraph y=(MyGraph)obj;
  		displayGraphAsString(y);
 		displayGraph(y);

  	} 
  	
	public void addController(ActionListener controller,ItemListener Controller1){
		System.out.println("View      : adding controller");
	AddVertexButton.addActionListener(controller);	
	AddEdgeButton.addActionListener(controller);
	EnterButton.addActionListener(controller);
	ViewPickedState.addItemListener(Controller1);
	} 
	
	public void displayGraph(MyGraph g1){
	        ViewLayout =g1.getGraphLayout();
	        ViewLayout.setSize(new Dimension(490,365));
	        ViewVV  = g1.getGraphVisualizationViewer(ViewLayout);
	        ViewVV.setBounds(0, 0,490, 365);
	        ViewVV.setPreferredSize(new Dimension(490,365));
	        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
	        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);     
	        ViewPickedState = ViewVV.getPickedVertexState();
	        DisplayGraph.removeAll();
	        DisplayGraph.add(ViewVV);
	}
	
	public void displayGraphAsString(MyGraph g1){
  		String z= g1.toString();
  		GraphString.setText("Graph Represented as a string \n"+ z);
	}
	
	public void setPickingMode(){
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
	}

	public MyVertex AskForFromVertex() {
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
		MessageFromController.setText("Please select an attacking vertex");
		ViewPickedState.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Object subject = e.getItem();
				if (subject instanceof MyVertex) {
					MyVertex vertex = (MyVertex) subject;
					if (ViewPickedState.isPicked(vertex)) {
						MessageFromController.setText("You have selected "+ vertex +"from");
						panel.add(MessageFromController);
						System.out.println("Vertex " + vertex
								+ " is now selected");
						currentVertex=vertex;
					} else {
						MessageFromController.setText("You have unselected "+ vertex + ". \n Please Select a new vertex."+"from");
						panel.add(MessageFromController);
						System.out.println("Vertex " + vertex
								+ " no longer selected");
						currentVertex=null;
					}
				}
			}
		});
		
		return currentVertex;
		
	}
	
	public MyVertex AskForToVertex() {
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
		MessageFromController.setText("Please select an attacking vertex");
		ViewPickedState.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Object subject = e.getItem();
				if (subject instanceof MyVertex) {
					MyVertex vertex = (MyVertex) subject;
					if (ViewPickedState.isPicked(vertex)) {
						MessageFromController.setText("You have selected "+ vertex +"from");
						panel.add(MessageFromController);
						System.out.println("Vertex " + vertex
								+ " is now selected");
						currentVertex=vertex;
					} else {
						MessageFromController.setText("You have unselected "+ vertex + ". \n Please Select a new vertex."+"from");
						panel.add(MessageFromController);
						System.out.println("Vertex " + vertex
								+ " no longer selected");
						currentVertex=null;
					}
				}
			}
		});
		
		return currentVertex;
		
	}

	public void changeToNoPickingMouse() {
		PluggableGraphMouse NoPick = new PluggableGraphMouse();
		ViewVV.setGraphMouse(NoPick);
//		ViewVV.setGraphMouse(NoPickMouse.gm);
		
	}


	} 