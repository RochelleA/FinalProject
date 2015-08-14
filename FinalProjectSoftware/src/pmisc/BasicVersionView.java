package pmisc;

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
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

import javax.swing.border.BevelBorder;

import org.apache.commons.collections15.Transformer;

import java.awt.Color;



class BasicVersionView extends JFrame implements java.util.Observer {
	
	private static final long serialVersionUID = 2118299654730994785L;

	private JPanel contentPane;
	JPanel graphVisualPanel = new JPanel();
	JPanel graphChangePanel;
	JPanel graphBuildPanel = new JPanel();
	JPanel graphSemanticsPanel = new JPanel();
	JPanel mainPanel = new JPanel();
	JPanel groundedSemanticsPanel= new JPanel();
	JPanel preferredSemanticsPanel = new JPanel();
	private  JLabel graphVisualLabel = new JLabel("Graph Visualisation");
	private  JLabel graphEditingLabel = new JLabel("Graph Editor");
	private  JLabel graphSemanticslLabel = new JLabel("Graph Semantics");
	JButton addVertexButton = new JButton("Add Argument");
	JButton addEdgeButton = new JButton("Add Attack");
	JButton enterButton = new JButton("Enter");
	JButton resetGraph = new JButton("Reset Graph");
	JButton deleteVertexButton = new JButton("Delete Argument");
	JButton deleteEdgeButton = new JButton("Delete Attack");
	JButton groundedLabellingButton = new JButton("Grounded Labelling");
	JButton preferredLabellingButton = new JButton("Admissible Labelling");
	JButton allAddmissibleLabellingButton = new JButton("All Admissble Labelling");
	public JTextArea groundedSemanticsInfo = new JTextArea();
	public JTextArea preferredSemanticsInfo = new JTextArea();
	JTextArea graphString= new JTextArea();
	JTextArea messageFromController= new JTextArea();
	private JPanel displayGraph = new JPanel();
	private MyGraph viewGraph = new MyGraph();
	private CircleLayout<MyArg, MyAtt> viewLayout;
	VisualizationViewer<MyArg,MyAtt> viewVV;
	PickedState<MyArg> viewVertexPickedState;
	PickedState<MyAtt> viewEdgePickedState;
	private DefaultModalGraphMouse<MyArg, MyAtt> gm;
	MyArg currentVertex;
	MyArg deleteVertex;
	MyAtt deleteEdge;
	MyPluggableGraphMouse noPickMouse;
	
	BasicVersionView() {

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
		graphSemanticsPanel.add(groundedSemanticsPanel);
		graphSemanticsPanel.add(preferredSemanticsPanel);
		
		graphSemanticslLabel.setHorizontalAlignment(JLabel.CENTER);
		
		groundedSemanticsPanel.setLayout(null);
		groundedSemanticsPanel.add(groundedLabellingButton);
		groundedSemanticsPanel.add(groundedSemanticsInfo);
		
		groundedLabellingButton.setBounds(30, 10, 200, 50);
		groundedSemanticsInfo.setBounds(260,10 , 350, 100);
		groundedSemanticsInfo.setLineWrap(true);
		groundedSemanticsInfo.setWrapStyleWord(true);
		groundedSemanticsInfo.setEditable(false);
		
		preferredSemanticsPanel.setLayout(null);
		preferredSemanticsPanel.add(preferredLabellingButton);
		preferredSemanticsPanel.add(preferredSemanticsInfo);
		preferredSemanticsPanel.add(allAddmissibleLabellingButton);

		preferredLabellingButton.setBounds(30, 10, 200, 30);
		allAddmissibleLabellingButton.setBounds(30, 60, 200, 30);
		preferredSemanticsInfo.setBounds(260, 10 , 350, 100);
		preferredSemanticsInfo.setLineWrap(true);
		preferredSemanticsInfo.setWrapStyleWord(true);
		preferredSemanticsInfo.setEditable(false);
		
		graphBuildPanel.setLayout(null);
		graphBuildPanel.add(graphEditingLabel);
//		graphBuildPanel.setBounds(30, 30, 400, 350);
		graphBuildPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphBuildPanel.add(addVertexButton);
		graphBuildPanel.add(addEdgeButton);
		graphBuildPanel.add(enterButton);
		graphBuildPanel.add(resetGraph);
		graphBuildPanel.add(deleteEdgeButton);
		graphBuildPanel.add(deleteVertexButton);
		graphBuildPanel.add(graphString);
		graphBuildPanel.add(messageFromController);
//		graphBuildPanel.add(graphVisualLabel);
		graphBuildPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
//		graphVisualLabel.setBounds(0, 10, 100, 30);
		

		addVertexButton.setBounds(255, 60, 125, 30);
		addVertexButton.setMargin(new Insets(0, 0, 0, 0));
		addEdgeButton.setBounds(255, 120, 125, 30);
		addEdgeButton.setMargin(new Insets(0, 0, 0, 0));
		enterButton.setBounds(535, 120, 125, 30);
		resetGraph.setBounds(535, 60, 125, 30);
		deleteVertexButton.setBounds(395, 60, 125, 30);
		deleteVertexButton.setMargin(new Insets(0, 0, 0, 0));
		deleteEdgeButton.setBounds(395, 120, 125, 30);
		graphString.setBounds(30, 180,500, 90);
		graphString.setLineWrap(true);
		graphString.setWrapStyleWord(true);
		messageFromController.setBounds(30, 60, 200, 90);
		messageFromController.setLineWrap(true);
		messageFromController.setWrapStyleWord(true);
		messageFromController.setEditable(false);
		
//		graphVisualPanel.setBounds(600, 5, 600, 600);
		graphVisualPanel.setLayout(new BoxLayout(graphVisualPanel,BoxLayout.PAGE_AXIS));
		graphVisualPanel.add(graphVisualLabel);
		graphVisualPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		graphVisualPanel.add(displayGraph);
		
		displayGraph.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		displayGraph.setBounds(00, 20, 600, 650);
//		DisplayGraph.add(graphVisualLabel);
		
		viewLayout = new CircleLayout<MyArg, MyAtt>(viewGraph);
        viewLayout.setSize(new Dimension(590,590));
		viewVV = new VisualizationViewer<MyArg,MyAtt>(viewLayout,new Dimension(590,590));
		viewVertexPickedState= viewVV.getPickedVertexState();
		viewEdgePickedState=viewVV.getPickedEdgeState();
		noPickMouse = new MyPluggableGraphMouse();
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
        viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK,Color.BLACK));
        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);   
        Transformer<MyAtt, Paint> edgeColour = new Transformer<MyAtt, Paint>() {
			public Paint transform(MyAtt edge){
				if(viewEdgePickedState.isPicked(edge)){
					setBackground(Color.BLUE);
					setForeground(Color.BLUE);
					return Color.BLUE;
				}
					return Color.BLACK;	
			}
		};
		viewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
		viewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
		viewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
        gm = new DefaultModalGraphMouse<MyArg, MyAtt>();
        displayGraph.add(viewVV);		
        
        
        
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
  	
	public void addController(ActionListener controller,ItemListener controller1){
		System.out.println("View      : adding controller");
	addVertexButton.addActionListener(controller);	
	addEdgeButton.addActionListener(controller);
	enterButton.addActionListener(controller);
	resetGraph.addActionListener(controller);
	deleteVertexButton.addActionListener(controller);
	deleteEdgeButton.addActionListener(controller);
	groundedLabellingButton.addActionListener(controller);
	preferredLabellingButton.addActionListener(controller);
	allAddmissibleLabellingButton.addActionListener(controller);
	viewVertexPickedState.addItemListener(controller1);
	viewEdgePickedState.addItemListener(controller1);
	} 
	
	public void displayGraph(MyGraph g1){
	        viewLayout =g1.getGraphLayout();
	        viewLayout.setSize(new Dimension(590,590));
	        viewVV  = g1.getGraphVisualizationViewer(viewLayout);
	        viewVV.setBounds(0, 0,590, 590);
	        viewVV.setPreferredSize(new Dimension(590,590));
	        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
	        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
	        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR); 
	        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
	        Transformer<MyAtt, Paint> edgeColour = new Transformer<MyAtt, Paint>() {
				public Paint transform(MyAtt edge){
					if(viewEdgePickedState.isPicked(edge)){
						setBackground(Color.BLUE);
						setForeground(Color.BLUE);
						return Color.BLUE;
					}
						return Color.BLACK;	
				}
			};
			viewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
			viewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
			viewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
	        viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK,Color.BLACK));
	        viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
	        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
	        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);     
	       viewVV.getRenderContext().setEdgeLabelTransformer(new Transformer<MyAtt, String>() {
                public String transform(MyAtt e) {
                    return (e.getLabel()) ;
                }
                
            });
	       viewVV.getRenderContext().setLabelOffset(17);
	        viewVertexPickedState = viewVV.getPickedVertexState();
	        displayGraph.removeAll();
	        displayGraph.add(viewVV);
	}
	

	public void displayGraphAsString(MyGraph g1){
  		String z= g1.toString();
  		graphString.setText("Graph Represented as a string \n"+ z);
	}
	
	public void setPickingMode(){
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
	}

	public MyArg askForFromVertex() {

		viewVV.setPickedVertexState(viewVertexPickedState);
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
		messageFromController.setText("Please select an attacking argument");
		viewVertexPickedState.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK,Color.BLACK));
				Object pickedItem = e.getItem();
				
				if (pickedItem instanceof MyArg) {
					MyArg vertex = (MyArg) pickedItem;
					
					if (viewVertexPickedState.isPicked(vertex)) {
						Transformer<MyArg, Paint> vertexColour = new Transformer<MyArg, Paint>() {
							public Paint transform(MyArg vertex){
								if(viewVertexPickedState.isPicked(vertex)){
									return Color.YELLOW;
								}
								
								return Color.LIGHT_GRAY;
							}
						};
						
						viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLACK));
						viewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						messageFromController.setText("You have selected "+ vertex);
						graphBuildPanel.add(messageFromController);
						System.out.println("Argument " + vertex
								+ " is now selected");
						currentVertex=vertex;
					} 
					else if (pickedItem instanceof MyAtt){
						System.out.println("ha ha");
					}else {
						Transformer<MyAtt, Paint> edgeColour = new Transformer<MyAtt, Paint>() {
							public Paint transform(MyAtt edge){
								if(viewEdgePickedState.isPicked(edge)){
									setBackground(Color.BLUE);
									setForeground(Color.BLUE);
									return Color.BLUE;
								}
									return Color.BLACK;	
							}
						};
						viewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
						viewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
						viewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
						messageFromController.setText("");
						graphBuildPanel.add(messageFromController);
						System.out.println("Picking state and message box cleared");
						currentVertex=null;
					}
					
				}
			}
		});
		
		return currentVertex;
		
	}
	
	public MyArg askForToVertex() {
		viewVV.setPickedVertexState(viewVertexPickedState);
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
		messageFromController.setText("Please select an attacked argument and then press enter when you are done.");
		return currentVertex;
		
	}

	public void changeToNoPickingMouse() {
		currentVertex=null;
		PluggableGraphMouse noPick = new PluggableGraphMouse();
		viewVV.setGraphMouse(noPick);
	}

	public void colourGraph(MyGraph graph){
	 Transformer<MyArg,Paint> vertexColour = new Transformer<MyArg, Paint>() {
		public Paint transform(MyArg vertex){
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
	viewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
	}
	
	public void deleteVertex(){
		viewVV.setPickedVertexState(viewVertexPickedState);
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
		messageFromController.setText("Please select a argument to delete. Deleting this argument will delete all attacks connected to it. Press Enter once you have selected the vertex.");
		viewVertexPickedState.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				Transformer<MyAtt, Paint> edgeColour = new Transformer<MyAtt, Paint>() {
					public Paint transform(MyAtt edge){
						if(viewEdgePickedState.isPicked(edge)){
							setBackground(Color.BLUE);
							setForeground(Color.BLUE);
							return Color.BLUE;
						}
							return Color.BLACK;	
					}
				};
				viewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
				viewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
				viewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
				Object pickedItem = e.getItem();
				if (pickedItem instanceof MyArg) {
					deleteEdge=null;
					MyArg vertex = (MyArg) pickedItem;
					
					if (viewVertexPickedState.isPicked(vertex)) {
						Transformer<MyArg, Paint> vertexColour = new Transformer<MyArg, Paint>() {
							public Paint transform(MyArg vertex){
								if(viewVertexPickedState.isPicked(vertex)){
									return Color.YELLOW;
								}
									return Color.LIGHT_GRAY;	
							}
						};
						viewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						messageFromController.setText("You have selected "+ vertex+ ".");
						graphBuildPanel.add(messageFromController);
						System.out.println("Argument " + vertex
								+ " is now selected");
						deleteVertex=vertex;
						System.out.print("Delete is set to "+deleteVertex);
					} else {
						messageFromController.setText("");
						graphBuildPanel.add(messageFromController);
						System.out.println("Picking state and message box cleared");
						deleteVertex=null;
					}
				}
			}
		});
		
	}
	public void deleteEdge(){
		viewVertexPickedState.clear();
		viewVV.setPickedEdgeState(viewEdgePickedState);
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
		messageFromController.setText("Please select an attack to delete. Press Enter once you have selected the attack.");
		viewEdgePickedState.addItemListener(new ItemListener() {
			Transformer<MyArg,Paint> vertexColour = new Transformer<MyArg, Paint>(){
				public Paint transform(MyArg vertex) {
					return Color.LIGHT_GRAY;
				}
			};
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
				Object pickedItem = e.getItem();
				if (pickedItem instanceof MyAtt) {
					currentVertex=null;
					System.out.println("something has changed");
					MyAtt edge = (MyAtt) pickedItem;
					
					if (viewEdgePickedState.isPicked(edge)) {
						
						Transformer<MyAtt, Paint> edgeColour = new Transformer<MyAtt, Paint>() {
							public Paint transform(MyAtt edge){
								if(viewEdgePickedState.isPicked(edge)){
									setBackground(Color.BLUE);
									setForeground(Color.BLUE);
									return Color.BLUE;
								}
									return Color.BLACK;	
							}
						};
						
						viewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						viewVV.getRenderContext().setEdgeDrawPaintTransformer(edgeColour);
						viewVV.getRenderContext().setArrowDrawPaintTransformer(edgeColour);
						viewVV.getRenderContext().setArrowFillPaintTransformer(edgeColour);
						messageFromController.setText("You have selected "+ edge.getLabel()+ ".");
					
					viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLUE));
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
					graphBuildPanel.add(messageFromController);
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
			
			else if (pickedItem instanceof MyArg) {
			final MyArg vertex = (MyArg) pickedItem;
				
				if (viewVertexPickedState.isPicked(vertex)) {
					viewEdgePickedState.clear();
					viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLACK));
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK,Color.BLACK));
				} 
			}
				else {
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
					messageFromController.setText("");
					graphBuildPanel.add(messageFromController);
					System.out.println("Picking state and message box cleared");
					deleteVertex=null;
				}
		
	}
} ;
});
	}
	
	public void clearSemanticsInfoBoxes(){
		groundedSemanticsInfo.setText("");
		preferredSemanticsInfo.setText("");
	}
	
	public void openAdmissibleFrame(String string){
		new addmissibleFrame(string);
	}
}