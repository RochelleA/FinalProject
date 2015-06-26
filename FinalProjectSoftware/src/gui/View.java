package gui;

import core.*;

import java.util.Observable;		//for update();
import java.awt.event.ActionListener;	//for addController()
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

import javax.swing.border.BevelBorder;

import java.awt.Color;



class View extends JFrame implements java.util.Observer {
	
	private static final long serialVersionUID = 2118299654730994785L;

	private JPanel contentPane;
	private  JPanel panel = new JPanel();
	private  JLabel lblGraphDisplay = new JLabel("Graph Display",SwingConstants.CENTER);
//	private JLabel lblEditGraph = new JLabel("Edit Graph",SwingConstants.CENTER);
	JButton AddVertexButton = new JButton("Add Vertex");
	 JButton AddEdgeButton = new JButton("Add Edge");
	private JTextArea GraphString= new JTextArea();
	private JTextArea MessageFromController= new JTextArea();
	private JPanel DisplayGraph = new JPanel();
	Layout<MyVertex, MyEdge> layout;
	VisualizationViewer<MyVertex, MyEdge> vv;
	static PickedState<MyVertex> pickedState;
	MyVertex currentVertex;
	
	
	View() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1000, 1000);
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
		MessageFromController.setBounds(30, 80, 240, 50);
		GraphString.setBounds(30, 160,500, 100);
		lblGraphDisplay.setBounds(300, 30, 100, 50);
		DisplayGraph.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		DisplayGraph.setBounds(30, 290, 500, 300);
		panel.add(DisplayGraph);
		panel.add(AddVertexButton);
		panel.add(AddEdgeButton);
		panel.add(GraphString);
		panel.add(lblGraphDisplay);
		panel.add(MessageFromController);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
		
   

	
		}
	 
  	public void update(Observable obs, Object obj) {
  		System.out.println("Updated");
  		MyGraph y=(MyGraph)obj;
  		String z= y.toString();
  		GraphString.setText("Graph Represented as a string \n"+ z);
 		displaygraph(y);
 
//  		
//		GraphString.setText("" + ((MyGraph)obj).toString());	//obj is an Object, need to cast to an MyGraph type

  	} 
  	
	public void addController(ActionListener controller, ItemListener controller1){
		System.out.println("View      : adding controller");
	AddVertexButton.addActionListener(controller);	
	AddEdgeButton.addActionListener(controller);
//	View.pickedState.addItemListener(controller1);
	} 
	
	public void setCurrentVertex(MyVertex v){
		currentVertex=v;
	}
	public void AskForToVertex(){
		MessageFromController.setText("Please select an attacking vertex");
	}
	

	
	public void displaygraph(MyGraph g1){  

			final Layout<MyVertex, MyEdge> layout = new CircleLayout<MyVertex, MyEdge>(g1.getmygraph());
			layout.setSize(new Dimension(200, 200));
			VisualizationViewer<MyVertex, MyEdge> vv = new VisualizationViewer<MyVertex, MyEdge>(
					layout);
			vv.setPreferredSize(new Dimension(250, 250));
			
			// Attach the listener for listening to when vertices are selected (or deselected).
			// You can also listen for changes to the selection of edges, by using vv.getPickedEdgeState() in
			// place of vv.getPickedVertexState().
			
			// Set the mouse to "picking" mode so that vertices and edges can be selected.
			DefaultModalGraphMouse<MyVertex, MyEdge> modalMouse = new DefaultModalGraphMouse<MyVertex, MyEdge>();
			modalMouse.setMode(Mode.PICKING);
			vv.setGraphMouse(modalMouse);

			// Set up rendering for the vertices.
			RenderContext<MyVertex, MyEdge> renderContext = vv.getRenderContext();
			renderContext.setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
			renderContext.setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
			g1.setGraphLayout(layout);
			g1.setGraphVisualizationViewer(vv);
	        DisplayGraph.removeAll();
	        DisplayGraph.add(vv);
	        
	}
	
	public void getPickedVertex(MyGraph g1,ItemListener controller){
		pickedState = g1.getGraphVisualizationViewer(g1.getGraphLayout()).getPickedVertexState();
		pickedState.addItemListener(controller);
		
	}
	
	public PickedState<MyVertex> getPickedState(){
		return View.pickedState;
	}
//	 public void pickVertex(MyGraph g1){   
//		 layout= g1.getGraphLayout();
//		 vv = g1.getGraphVisualizationViewer(layout);
//	        final PickedState<MyVertex> pickedState = vv.getPickedVertexState();
//			pickedState.addItemListener(new ItemListener() {
//
//				@Override
//				public void itemStateChanged(ItemEvent e) {
//					Object subject = e.getItem();
//					if (subject instanceof MyVertex) {
//						MyVertex vertex = (MyVertex) subject;
//						if (pickedState.isPicked(vertex)) {
//							System.out.println("Vertex " + vertex
//									+ " is now selected");
//						} else {
//							System.out.println("Vertex " + vertex
//									+ " no longer selected");
//						}
//					}
//				}
//			});
//			
//			DefaultModalGraphMouse<MyVertex, MyEdge> modalMouse = new DefaultModalGraphMouse<MyVertex, MyEdge>();
//			modalMouse.setMode(Mode.PICKING);
//			vv.setGraphMouse(modalMouse);
//
//			// Set up rendering for the vertices.
//			RenderContext<MyVertex, MyEdge> renderContext = vv.getRenderContext();
//			renderContext
//					.setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
//			renderContext.setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
//			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
//			
//	        
//	}
//	
//	public MyVertex VertexSelected(){
//		
//	}
	} 