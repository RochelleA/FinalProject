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
import java.awt.Insets;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.control.PluggableGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.AbstractPickedState;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;
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
	JButton AddVertexButton = new JButton("Add Argument");
	JButton AddEdgeButton = new JButton("Add Attack");
	JButton EnterButton = new JButton("Enter");
	JButton ResetGraph = new JButton("Reset Graph");
	JButton DeleteVertexButton = new JButton("Delete Argument");
	JButton DeleteEdgeButton = new JButton("Delete Attack");
	JButton GroundedLabellingButton = new JButton("Grounded Labelling");
	JButton PreferredLabellingButton = new JButton("Admissible Labelling");
	JButton AllAddmissibleLabellingButton = new JButton("All Admissble Labelling");
	JTextArea GroundedSemanticsInfo = new JTextArea();
	JTextArea PreferredSemanticsInfo = new JTextArea();
	JTextArea GraphString= new JTextArea();
	JTextArea MessageFromController= new JTextArea();
	private JPanel DisplayGraph = new JPanel();
	private MyGraph ViewGraph = new MyGraph();
	private CircleLayout<MyVertex, MyEdge> ViewLayout;
	VisualizationViewer<MyVertex,MyEdge> ViewVV;
	PickedState<MyVertex> ViewVertexPickedState;
	PickedState<MyEdge> ViewEdgePickedState;
	private DefaultModalGraphMouse<MyVertex, MyEdge> gm;
	MyVertex currentVertex;
	MyVertex deleteVertex;
	MyEdge deleteEdge;
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
		
		GroundedLabellingButton.setBounds(30, 10, 200, 50);
		GroundedSemanticsInfo.setBounds(260,10 , 350, 100);
		GroundedSemanticsInfo.setLineWrap(true);
		GroundedSemanticsInfo.setWrapStyleWord(true);
		GroundedSemanticsInfo.setEditable(false);
		
		PreferredSemanticsPanel.setLayout(null);
		PreferredSemanticsPanel.add(PreferredLabellingButton);
		PreferredSemanticsPanel.add(PreferredSemanticsInfo);
		PreferredSemanticsPanel.add(AllAddmissibleLabellingButton);

		PreferredLabellingButton.setBounds(30, 10, 200, 30);
		AllAddmissibleLabellingButton.setBounds(30, 60, 200, 30);
		PreferredSemanticsInfo.setBounds(260, 10 , 350, 100);
		PreferredSemanticsInfo.setLineWrap(true);
		PreferredSemanticsInfo.setWrapStyleWord(true);
		PreferredSemanticsInfo.setEditable(false);
		
		graphBuildPanel.setLayout(null);
		graphBuildPanel.add(graphEditingLabel);
//		graphBuildPanel.setBounds(30, 30, 400, 350);
		graphBuildPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphBuildPanel.add(AddVertexButton);
		graphBuildPanel.add(AddEdgeButton);
		graphBuildPanel.add(EnterButton);
		graphBuildPanel.add(ResetGraph);
		graphBuildPanel.add(DeleteEdgeButton);
		graphBuildPanel.add(DeleteVertexButton);
		graphBuildPanel.add(GraphString);
		graphBuildPanel.add(MessageFromController);
//		graphBuildPanel.add(graphVisualLabel);
		graphBuildPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
//		graphVisualLabel.setBounds(0, 10, 100, 30);
		

		AddVertexButton.setBounds(255, 60, 125, 30);
		AddVertexButton.setMargin(new Insets(0, 0, 0, 0));
		AddEdgeButton.setBounds(255, 120, 125, 30);
		AddEdgeButton.setMargin(new Insets(0, 0, 0, 0));
		EnterButton.setBounds(535, 120, 125, 30);
		ResetGraph.setBounds(535, 60, 125, 30);
		DeleteVertexButton.setBounds(395, 60, 125, 30);
		DeleteVertexButton.setMargin(new Insets(0, 0, 0, 0));
		DeleteEdgeButton.setBounds(395, 120, 125, 30);
		GraphString.setBounds(30, 180,500, 90);
		GraphString.setLineWrap(true);
		GraphString.setWrapStyleWord(true);
		MessageFromController.setBounds(30, 60, 200, 90);
		MessageFromController.setLineWrap(true);
		MessageFromController.setWrapStyleWord(true);
		MessageFromController.setEditable(false);
		
//		graphVisualPanel.setBounds(600, 5, 600, 600);
		graphVisualPanel.setLayout(new BoxLayout(graphVisualPanel,BoxLayout.PAGE_AXIS));
		graphVisualPanel.add(graphVisualLabel);
		graphVisualPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		graphVisualPanel.add(DisplayGraph);
		
		DisplayGraph.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		DisplayGraph.setBounds(00, 20, 600, 650);
//		DisplayGraph.add(graphVisualLabel);
		
		ViewLayout = new CircleLayout<MyVertex, MyEdge>(ViewGraph);
        ViewLayout.setSize(new Dimension(590,590));
		ViewVV = new VisualizationViewer<MyVertex,MyEdge>(ViewLayout,new Dimension(590,590));
		ViewVertexPickedState= ViewVV.getPickedVertexState();
		ViewEdgePickedState=ViewVV.getPickedEdgeState();
		NoPickMouse = new MyPluggableGraphMouse();
        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
        ViewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
        ViewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK,Color.BLACK));
        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);   
        Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
			public Paint transform(MyEdge edge){
				if(ViewEdgePickedState.isPicked(edge)){
					setBackground(Color.BLUE);
					setForeground(Color.BLUE);
					return Color.BLUE;
				}
					return Color.BLACK;	
			}
		};
		ViewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
		ViewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
		ViewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
        gm = new DefaultModalGraphMouse<MyVertex, MyEdge>();
        DisplayGraph.add(ViewVV);		
        
        
        
        this.setMinimumSize(new Dimension(600,600));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
		
   

	
		}
	 
  	public void update(Observable obs, Object obj) {
  		System.out.println("Updated");
  		MyGraph y=(MyGraph)obj;
  		displayGraphAsString(y);
 		displayGraph(y);
 		colourGraph(y);

  	} 
  	
	public void addController(ActionListener controller,ItemListener Controller1){
		System.out.println("View      : adding controller");
	AddVertexButton.addActionListener(controller);	
	AddEdgeButton.addActionListener(controller);
	EnterButton.addActionListener(controller);
	ResetGraph.addActionListener(controller);
	DeleteVertexButton.addActionListener(controller);
	DeleteEdgeButton.addActionListener(controller);
	GroundedLabellingButton.addActionListener(controller);
	PreferredLabellingButton.addActionListener(controller);
	AllAddmissibleLabellingButton.addActionListener(controller);
	ViewVertexPickedState.addItemListener(Controller1);
	ViewEdgePickedState.addItemListener(Controller1);
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
	        Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
				public Paint transform(MyEdge edge){
					if(ViewEdgePickedState.isPicked(edge)){
						setBackground(Color.BLUE);
						setForeground(Color.BLUE);
						return Color.BLUE;
					}
						return Color.BLACK;	
				}
			};
			ViewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
			ViewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
			ViewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
	        ViewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK,Color.BLACK));
	        ViewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
	        ViewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        ViewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);     
	       ViewVV.getRenderContext().setEdgeLabelTransformer(new Transformer<MyEdge, String>() {
                public String transform(MyEdge e) {
                    return (e.getLabel()) ;
                }
                
            });
	       ViewVV.getRenderContext().setLabelOffset(17);
	        ViewVertexPickedState = ViewVV.getPickedVertexState();
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

		ViewVV.setPickedVertexState(ViewVertexPickedState);
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
		MessageFromController.setText("Please select an attacking argument");
		ViewVertexPickedState.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				ViewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK,Color.BLACK));
				Object pickedItem = e.getItem();
				
				if (pickedItem instanceof MyVertex) {
					MyVertex vertex = (MyVertex) pickedItem;
					
					if (ViewVertexPickedState.isPicked(vertex)) {
						Transformer<MyVertex, Paint> vertexColour = new Transformer<MyVertex, Paint>() {
							public Paint transform(MyVertex vertex){
								if(ViewVertexPickedState.isPicked(vertex)){
									return Color.YELLOW;
								}
								
								return Color.LIGHT_GRAY;
							}
						};
						
						ViewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLACK));
						ViewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						MessageFromController.setText("You have selected "+ vertex);
						graphBuildPanel.add(MessageFromController);
						System.out.println("Argument " + vertex
								+ " is now selected");
						currentVertex=vertex;
					} 
					else if (pickedItem instanceof MyEdge){
						System.out.println("ha ha");
					}else {
						Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
							public Paint transform(MyEdge edge){
								if(ViewEdgePickedState.isPicked(edge)){
									setBackground(Color.BLUE);
									setForeground(Color.BLUE);
									return Color.BLUE;
								}
									return Color.BLACK;	
							}
						};
						ViewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
						ViewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
						ViewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
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
		ViewVV.setPickedVertexState(ViewVertexPickedState);
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
		MessageFromController.setText("Please select an attacked argument and then press enter when you are done.");
		return currentVertex;
		
	}

	public void changeToNoPickingMouse() {
		currentVertex=null;
		PluggableGraphMouse NoPick = new PluggableGraphMouse();
		ViewVV.setGraphMouse(NoPick);
	}

	public void colourGraph(MyGraph graph){
	 Transformer<MyVertex,Paint> vertexColour = new Transformer<MyVertex, Paint>() {
		public Paint transform(MyVertex vertex){
			if(vertex.getLabel()=="IN"){
				return Color.GREEN;
			}
			else if(vertex.getLabel()=="OUT"){
				return Color.RED;
			}
			else if(vertex.getLabel()=="UNDEC"){
				return Color.ORANGE;
			}
			else{
				return Color.LIGHT_GRAY;
			}
		}
	};
	ViewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
	}
	
	public void deleteMyVertex(){
		ViewVV.setPickedVertexState(ViewVertexPickedState);
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
		MessageFromController.setText("Please select a argument to delete. Deleting this argument will delete all attacks connected to it. Press Enter once you have selected the vertex.");
		ViewVertexPickedState.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
					public Paint transform(MyEdge edge){
						if(ViewEdgePickedState.isPicked(edge)){
							setBackground(Color.BLUE);
							setForeground(Color.BLUE);
							return Color.BLUE;
						}
							return Color.BLACK;	
					}
				};
				ViewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
				ViewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
				ViewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
				Object pickedItem = e.getItem();
				if (pickedItem instanceof MyVertex) {
					deleteEdge=null;
					MyVertex vertex = (MyVertex) pickedItem;
					
					if (ViewVertexPickedState.isPicked(vertex)) {
						Transformer<MyVertex, Paint> vertexColour = new Transformer<MyVertex, Paint>() {
							public Paint transform(MyVertex vertex){
								if(ViewVertexPickedState.isPicked(vertex)){
									return Color.YELLOW;
								}
									return Color.LIGHT_GRAY;	
							}
						};
						ViewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						MessageFromController.setText("You have selected "+ vertex+ ".");
						graphBuildPanel.add(MessageFromController);
						System.out.println("Argument " + vertex
								+ " is now selected");
						deleteVertex=vertex;
						System.out.print("Delete is set to "+deleteVertex);
					} else {
						MessageFromController.setText("");
						graphBuildPanel.add(MessageFromController);
						System.out.println("Picking state and message box cleared");
						deleteVertex=null;
					}
				}
			}
		});
		
	}
	public void deleteMyEdge(){
		ViewVertexPickedState.clear();
		ViewVV.setPickedEdgeState(ViewEdgePickedState);
		gm.setMode(Mode.PICKING);
		ViewVV.setGraphMouse(gm);
		MessageFromController.setText("Please select an attack to delete. Press Enter once you have selected the attack.");
		ViewEdgePickedState.addItemListener(new ItemListener() {
			Transformer<MyVertex,Paint> vertexColour = new Transformer<MyVertex, Paint>(){
				public Paint transform(MyVertex vertex) {
					return Color.LIGHT_GRAY;
				}
			};
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				ViewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
				Object pickedItem = e.getItem();
				if (pickedItem instanceof MyEdge) {
					currentVertex=null;
					System.out.println("something has changed");
					MyEdge edge = (MyEdge) pickedItem;
					
					if (ViewEdgePickedState.isPicked(edge)) {
						
						Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
							public Paint transform(MyEdge edge){
								if(ViewEdgePickedState.isPicked(edge)){
									setBackground(Color.BLUE);
									setForeground(Color.BLUE);
									return Color.BLUE;
								}
									return Color.BLACK;	
							}
						};
						
						ViewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						ViewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
						ViewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
						ViewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
						MessageFromController.setText("You have selected "+ edge.getLabel()+ ".");
					
					ViewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLUE));
					ViewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
					graphBuildPanel.add(MessageFromController);
						System.out.println("Edge " + edge+ " is now selected");
						deleteEdge=edge;
						System.out.print("Delete is set to "+deleteEdge.getLabel());
//						else {
//						MessageFromController.setText("");
//						graphBuildPanel.add(MessageFromController);
//						System.out.println("Picking state and message box cleared");
//						deleteVertex=null;
//					}
				}
			
			else if (pickedItem instanceof MyVertex) {
				MyVertex vertex = (MyVertex) pickedItem;
				
				if (ViewVertexPickedState.isPicked(vertex)) {
					ViewEdgePickedState.clear();
					ViewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLACK));
					ViewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK,Color.BLACK));
				} 
			}
				else {
					ViewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
					MessageFromController.setText("");
					graphBuildPanel.add(MessageFromController);
					System.out.println("Picking state and message box cleared");
					deleteVertex=null;
				}
		
	}
} ;
});
	}
	
	public void clearSemanticsInfoBoxes(){
		GroundedSemanticsInfo.setText("");
		PreferredSemanticsInfo.setText("");
	}
	
	public void openAdmissibleFrame(String string){
		new addmissibleFrame(string);
	}
}