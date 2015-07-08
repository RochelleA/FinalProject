package gui;

import core.*;

import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

import javax.swing.border.BevelBorder;

import org.apache.commons.collections15.Transformer;

import java.awt.Color;



class View extends JFrame implements java.util.Observer {
	
	private static final long serialVersionUID = 2118299654730994785L;

	private JPanel contentPane;
	JPanel graphVisualPanel = new JPanel();
	JPanel graphChangePanel;
	JPanel graphBuildPanel = new JPanel();
	JPanel graphSemanticsPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel GroundedSemanticsPanel= new JPanel();
	JPanel PreferredSemanticsPanel = new JPanel();
	private  JLabel graphVisualLabel = new JLabel("Graph Visualisation");
	private  JLabel graphEditingLabel = new JLabel("Graph Editor");
	private  JLabel graphSemanticslLabel = new JLabel("Graph Semantics");
	JButton AddVertexButton = new JButton("Add Vertex");
	JButton AddEdgeButton = new JButton("Add Edge");
	JButton EnterButton = new JButton("Enter");
	JButton GroundedLabellingButton = new JButton("Grounded Labelling");
	JTextArea GroundedSemanticsInfo = new JTextArea();
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
		graphChangePanel= new JPanel();
//		{
//			@Override
//			public Dimension getPreferredSize(){
//				return new Dimension(400,700);
//			}
//		};
//		contentPane.setBounds(0, 0, 950, 950);
		setContentPane(contentPane);
		contentPane.add(mainPanel);
		mainPanel.setLayout(new GridLayout(0,2,15,15));
		mainPanel.add(graphChangePanel);
		mainPanel.add(graphVisualPanel);

		
//		graphChangePanel.setBounds(15, 15, 350, 650);
		graphChangePanel.setLayout(new BoxLayout(graphChangePanel,BoxLayout.PAGE_AXIS));
		graphChangePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphChangePanel.add(graphBuildPanel);
		graphChangePanel.add(graphSemanticsPanel);
		
	
		
		graphSemanticsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
//		graphSemanticsPanel.setBounds(30, 380, 200, 250)
		graphSemanticsPanel.setLayout(new GridLayout(3, 1, 5, 5));
		graphSemanticsPanel.add(graphSemanticslLabel);
		graphSemanticsPanel.add(GroundedSemanticsPanel);
		graphSemanticsPanel.add(PreferredSemanticsPanel);
		
		graphSemanticslLabel.setHorizontalAlignment(JLabel.CENTER);
		
		GroundedSemanticsPanel.setLayout(null);
		GroundedSemanticsPanel.add(GroundedLabellingButton);
		GroundedSemanticsPanel.add(GroundedSemanticsInfo);
		
		GroundedLabellingButton.setBounds(30, 10, 100, 30);
		GroundedSemanticsInfo.setBounds(160,10 , 450, 100);
		GroundedSemanticsInfo.setLineWrap(true);
		GroundedSemanticsInfo.setWrapStyleWord(true);
		
		

		
		graphBuildPanel.setLayout(null);
		graphBuildPanel.add(graphEditingLabel);
//		graphBuildPanel.setBounds(30, 30, 400, 350);
		graphBuildPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphBuildPanel.add(AddVertexButton);
		graphBuildPanel.add(AddEdgeButton);
		graphBuildPanel.add(EnterButton);
		graphBuildPanel.add(GraphString);
		graphBuildPanel.add(MessageFromController);
		graphBuildPanel.add(graphVisualLabel);
		graphBuildPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
//		graphVisualLabel.setBounds(0, 10, 100, 30);
		

		AddVertexButton.setBounds(300, 60, 100, 30);
		AddEdgeButton.setBounds(430, 60, 100, 30);
		EnterButton.setBounds(300, 120, 100, 30);
		GraphString.setBounds(30, 180,500, 90);
		GraphString.setLineWrap(true);
		GraphString.setWrapStyleWord(true);
		MessageFromController.setBounds(30, 60, 240, 90);
		MessageFromController.setLineWrap(true);
		MessageFromController.setWrapStyleWord(true);
		
//		graphVisualPanel.setBounds(600, 5, 600, 600);
		graphVisualPanel.setLayout(null);
		graphVisualPanel.add(DisplayGraph);
		
		DisplayGraph.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		DisplayGraph.setBounds(00, 20, 600, 650);
		DisplayGraph.add(graphVisualLabel);
		
		ViewLayout = new CircleLayout<MyVertex, MyEdge>(ViewGraph);
        ViewLayout.setSize(new Dimension(590,590));
		ViewVV = new VisualizationViewer<MyVertex,MyEdge>(ViewLayout,new Dimension(590,590));
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
	GroundedLabellingButton.addActionListener(controller);
	ViewPickedState.addItemListener(Controller1);
	} 
	
	public void displayGraph(MyGraph g1){
	        ViewLayout =g1.getGraphLayout();
	        ViewLayout.setSize(new Dimension(590,590));
	        ViewVV  = g1.getGraphVisualizationViewer(ViewLayout);
	        ViewVV.setBounds(0, 0,590, 590);
	        ViewVV.setPreferredSize(new Dimension(590,590));
	        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
	        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);     
	       ViewVV.getRenderContext().setEdgeLabelTransformer(new Transformer<MyEdge, String>() {
                public String transform(MyEdge e) {
                    return (e.getLabel()) ;
                }
                
            });
	       ViewVV.getRenderContext().setLabelOffset(17);
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

	public MyVertex askForFromVertex() {
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
						graphBuildPanel.add(MessageFromController);
						System.out.println("Vertex " + vertex
								+ " is now selected");
						currentVertex=vertex;
					} else {
						MessageFromController.setText("");
						graphBuildPanel.add(MessageFromController);
						System.out.println("Picking state and message box cleared");
						currentVertex=null;
					}
				}
			}
		});
		
		return currentVertex;
		
	}
	
	public MyVertex askForToVertex() {
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
		MessageFromController.setText("Please select an attacked vertex");
		return currentVertex;
		
	}

	public void changeToNoPickingMouse() {
		currentVertex=null;
		PluggableGraphMouse NoPick = new PluggableGraphMouse();
		ViewVV.setGraphMouse(NoPick);
	}


	} 