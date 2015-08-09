package gui;

import core.*;

import java.util.Map;
import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.TextAttribute;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.GraphMousePlugin;
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
	private JPanel graphVisualPanel = new JPanel();
	private JPanel graphChangePanel;
//	JPanel graphBuildPanel = new JPanel();
	private JPanel graphSemanticsPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel informationPanel = new JPanel();
	private JPanel titlePanel = new JPanel();
	private JPanel buttonsPanel = new JPanel();
	private JPanel groundedSemanticsPanel= new JPanel();
	private JPanel preferredSemanticsPanel = new JPanel();
	private JPanel semanticsLabelPanel = new JPanel();
	private JPanel semanticsContentsPanel = new JPanel();
	private JPanel admissibleSemanticsPanel = new JPanel();
	private JPanel graphLabelPanel = new JPanel();
	private JPanel changeToBuild = new JPanel();
	private JLabel graphVisualLabel = new JLabel("Graph Visualisation",SwingConstants.CENTER);
	private JLabel graphEditingLabel = new JLabel("Graph Editor");
	private JLabel graphSemanticslLabel = new JLabel("Graph Semantics");
	private JButton addVertexButton = new JButton("Add Argument");
	private JButton addEdgeButton = new JButton("Add Attack");
	private JButton enterButton = new JButton("Enter");
	private JButton resetGraph = new JButton("Reset Graph");
	private JButton deleteVertexButton = new JButton("Delete Argument");
	private JButton deleteEdgeButton = new JButton("Delete Attack");
	private JButton semanticsButton = new JButton("Change to Semantics View");
	private JButton buildButton = new JButton("Return to Build View");
	private JButton groundedLabellingButton = new JButton("Grounded Labelling");
	private JButton admissibleLabellingButton = new JButton("Admissible Labelling");
	private JButton allAdmissibleLabellingButton = new JButton("All Admissble Labelling");
	private JButton preferredButton = new JButton("Preferred Labelling");
	private JTextArea groundedSemanticsInfo = new JTextArea();
	private JTextArea admissibleSemanticsInfo = new JTextArea();
	private JTextArea preferredSemanticsInfo = new JTextArea();
	private JTextArea areYouReady = new JTextArea();
	private JTextArea allAdmissibleSemanticInfo = new JTextArea();
	private JScrollPane allAdmissiblePane =  new JScrollPane(allAdmissibleSemanticInfo);
	private Color panelColour = new Color(105, 105, 105);
	private Color borderColour = new Color(82, 82, 82);
	private Color buttonColour = new Color(184, 184, 184);
	private Color tBoxColour = new Color(128, 128, 128);
	private Color titleColour = new Color(230, 230, 230);
	private Border standardBorder = new BevelBorder(BevelBorder.LOWERED, borderColour, borderColour);
	private Border marginBorder = new EmptyBorder(new Insets(5, 5, 5, 5));
	private Border compoundBorder = new CompoundBorder(standardBorder,marginBorder);
	private Font largeTextFont =  new Font("univers", Font.BOLD, 14);
	private Font labelFont = new Font("univers", Font.BOLD, 16);
	private Font smallTBoxFont = new Font("caslon", Font.BOLD, 12);
	private Font largeTBoxFont = new Font("caslon", Font.BOLD, 14);
	private Font buttonFont = new Font("caslon", Font.BOLD, 12);
	private JTextArea graphString= new JTextArea();
	private JTextArea messageFromController= new JTextArea();
	private JPanel displayGraph = new JPanel();
	private MyGraph viewGraph = new MyGraph();
	private CircleLayout<MyVertex, MyEdge> viewLayout;
	private VisualizationViewer<MyVertex,MyEdge> viewVV;
	private PickedState<MyVertex> viewVertexPickedState;
	private PickedState<MyEdge> viewEdgePickedState;
	private DefaultModalGraphMouse<MyVertex, MyEdge> gm;
	MyVertex currentVertex;
	MyVertex deleteVertex;
	MyEdge deleteEdge;
	MyPluggableGraphMouse noPickMouse;
	GraphMousePlugin pickingMouse;
	private final JLabel LogoLabel;
	
	View() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		graphChangePanel= new JPanel();
		graphChangePanel.setBackground(panelColour);
//		{
//			@Override
//			public Dimension getPreferredSize(){
//				return new Dimension(400,700);
//			}
//		};
//		contentPane.setBounds(0, 0, 950, 950);
		setContentPane(contentPane);
		
		mainPanel.setBackground(panelColour);
		contentPane.add(mainPanel);
		mainPanel.setLayout(new GridLayout(0,2,15,15));
		mainPanel.add(graphChangePanel);
		graphVisualPanel.setBackground(panelColour);
		mainPanel.add(graphVisualPanel);

		
//		Font buttonFont = new Font("arial", Font.PLAIN, 12);

		
//		graphChangePanel.setBounds(15, 15, 350, 650);
//		graphChangePanel.setLayout(new BoxLayout(graphChangePanel,BoxLayout.PAGE_AXIS));
		graphChangePanel.setLayout(new BorderLayout(0, 0));
		graphChangePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		titlePanel.setBackground(panelColour);
		
//		graphChangePanel.add(graphSemanticsPanel);
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(650,80));
		titlePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphChangePanel.add(titlePanel, BorderLayout.NORTH);
		buttonsPanel.setBackground(panelColour);
		buttonsPanel.setPreferredSize(new Dimension(650,200));
		graphChangePanel.add(buttonsPanel,BorderLayout.CENTER);
		informationPanel.setBackground(panelColour);
		informationPanel.setPreferredSize(new Dimension(650, 300));
		informationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphChangePanel.add(informationPanel,BorderLayout.SOUTH);
		
	
		
		graphSemanticsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphSemanticsPanel.setPreferredSize(new Dimension(450,400));
		graphSemanticsPanel.setBackground(panelColour);
//		graphSemanticsPanel.setBounds(30, 380, 200, 250)
		graphSemanticsPanel.setLayout(new BorderLayout(5, 5));

		
		graphSemanticslLabel.setFont(labelFont);
		graphSemanticslLabel.setForeground(titleColour);

		Font font = graphSemanticslLabel.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		graphSemanticslLabel.setFont(font.deriveFont(attributes));
		graphSemanticslLabel.setHorizontalAlignment(JLabel.CENTER);
		groundedSemanticsPanel.setPreferredSize(new Dimension(400,100));
		groundedSemanticsPanel.setLayout(null);
		groundedSemanticsPanel.setBackground(panelColour);
		groundedSemanticsPanel.add(groundedLabellingButton);
		groundedSemanticsPanel.add(groundedSemanticsInfo);
		
		groundedLabellingButton.setBounds(30, 10, 200, 30);
		groundedLabellingButton.setBackground(buttonColour);
		groundedSemanticsInfo.setBounds(260,10 , 370, 80);
		groundedSemanticsInfo.setLineWrap(true);
		groundedSemanticsInfo.setWrapStyleWord(true);
		groundedSemanticsInfo.setEditable(false);
		groundedSemanticsInfo.setBackground(tBoxColour);
		groundedSemanticsInfo.setForeground(Color.white);
		
		admissibleSemanticsPanel.setLayout(null);
		admissibleSemanticsPanel.setPreferredSize(new Dimension(400, 180));
		admissibleSemanticsPanel.setBackground(panelColour);
		admissibleSemanticsPanel.add(admissibleLabellingButton);
		admissibleSemanticsPanel.add(admissibleSemanticsInfo);
		admissibleSemanticsPanel.add(allAdmissibleLabellingButton);
		admissibleSemanticsPanel.add(allAdmissiblePane);
		allAdmissiblePane.setBounds(260, 110, 370, 160);
		admissibleLabellingButton.setBounds(30, 10, 200, 30);
		admissibleLabellingButton.setBackground(buttonColour);
		allAdmissibleLabellingButton.setBounds(30, 110, 200, 30);
		allAdmissibleLabellingButton.setBackground(buttonColour);
		allAdmissibleSemanticInfo.setBounds(260, 100, 370, 80);
		admissibleSemanticsInfo.setBounds(260, 10 , 370, 80);
		admissibleSemanticsInfo.setLineWrap(true);
		admissibleSemanticsInfo.setWrapStyleWord(true);
		admissibleSemanticsInfo.setEditable(false);
		admissibleSemanticsInfo.setBackground(tBoxColour);
		admissibleSemanticsInfo.setForeground(Color.WHITE);
		allAdmissibleSemanticInfo.setEditable(false);
		
		preferredSemanticsPanel.setPreferredSize(new Dimension(400, 100));
		preferredSemanticsPanel.setLayout(null);
		preferredSemanticsPanel.setBackground(panelColour);
		preferredButton.setBounds(30,10,200,30);
		preferredButton.setBackground(buttonColour);
		preferredSemanticsInfo.setBounds(260, 10, 370, 80);
		preferredSemanticsInfo.setBackground(tBoxColour);
		preferredSemanticsInfo.setForeground(Color.white);
		preferredSemanticsInfo.setEditable(false);
		preferredSemanticsPanel.add(preferredButton);
		preferredSemanticsPanel.add(preferredSemanticsInfo);
		semanticsLabelPanel.add(graphSemanticslLabel);
		semanticsLabelPanel.setPreferredSize(new Dimension(400,30));
		semanticsLabelPanel.setBackground(panelColour);
		graphSemanticsPanel.add(semanticsLabelPanel, BorderLayout.NORTH);
		semanticsContentsPanel.setLayout(new BorderLayout(0, 0));
		semanticsContentsPanel.setPreferredSize(new Dimension(420, 360));
		semanticsContentsPanel.add(groundedSemanticsPanel, BorderLayout.NORTH);
		semanticsContentsPanel.add(admissibleSemanticsPanel, BorderLayout.CENTER);
		semanticsContentsPanel.add(preferredSemanticsPanel, BorderLayout.SOUTH);
		
		graphSemanticsPanel.add(semanticsContentsPanel, BorderLayout.CENTER);
		
		changeToBuild.setBackground(panelColour);
		allAdmissibleSemanticInfo.setBackground(tBoxColour);
		allAdmissibleSemanticInfo.setForeground(Color.white);
		changeToBuild.add(buildButton);
		buildButton.setBackground(buttonColour);
		graphSemanticsPanel.add(changeToBuild,BorderLayout.SOUTH);
		
		buttonsPanel.setLayout(null);
		buttonsPanel.add(graphEditingLabel);
		buttonsPanel.setBounds(30, 30, 400, 400);
		buttonsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		buttonsPanel.add(addVertexButton);
		buttonsPanel.add(addEdgeButton);
		buttonsPanel.add(enterButton);
		buttonsPanel.add(resetGraph);
		buttonsPanel.add(deleteEdgeButton);
		buttonsPanel.add(deleteVertexButton);
		buttonsPanel.add(semanticsButton);
		buttonsPanel.add(areYouReady);
		buttonsPanel.add(graphEditingLabel);
//		buttonsPanel.add(graphVisualLabel);
		buttonsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
//		graphVisualLabel.setBounds(0, 10, 100, 30);
		
		graphEditingLabel.setFont(font.deriveFont(attributes));
		graphEditingLabel.setBounds(260, 30, 100, 20);
		graphEditingLabel.setBackground(panelColour);
		graphEditingLabel.setForeground(titleColour);
		addVertexButton.setBounds(30, 60, 150, 30);
		addVertexButton.setMargin(new Insets(0, 0, 0, 0));
		addVertexButton.setBackground(buttonColour);
		addVertexButton.setFont(buttonFont);
		addEdgeButton.setBounds(30, 120, 150, 30);
		addEdgeButton.setMargin(new Insets(0, 0, 0, 0));
		addEdgeButton.setBackground(buttonColour);
		addEdgeButton.setFont(buttonFont);
		deleteVertexButton.setBounds(200, 60, 150, 30);
		deleteVertexButton.setMargin(new Insets(0, 0, 0, 0));
		deleteVertexButton.setBackground(buttonColour);
		deleteVertexButton.setFont(buttonFont);
		deleteEdgeButton.setBounds(200, 120, 150, 30);
		deleteEdgeButton.setBackground(buttonColour);
		deleteEdgeButton.setFont(buttonFont);
		enterButton.setBounds(365, 120, 150, 30);
		enterButton.setBackground(buttonColour);
		enterButton.setFont(buttonFont);
		resetGraph.setBounds(365, 60, 150, 30);
		resetGraph.setFont(buttonFont);
		resetGraph.setBackground(buttonColour);
		semanticsButton.setBounds(30, 210, 210, 30);
		semanticsButton.setBackground(buttonColour);
		semanticsButton.setFont(buttonFont);
		areYouReady.setBackground(panelColour);
		areYouReady.setForeground(Color.WHITE);
		areYouReady.setFont(largeTextFont);
		areYouReady.setText("Are you ready to test semantics?");
		areYouReady.setBounds(30, 180, 300, 30);
		
		informationPanel.setLayout(null);
		informationPanel.add(getGraphString());
		informationPanel.add(messageFromController);
		getGraphString().setBounds(30, 120,560, 150);
		getGraphString().setLineWrap(true);
		getGraphString().setWrapStyleWord(true);
		getGraphString().setBorder(compoundBorder);
		getGraphString().setBackground(tBoxColour);
		getGraphString().setForeground(Color.white);
		getGraphString().setMargin(new Insets(15, 15, 15, 15));
		getGraphString().setFont(smallTBoxFont);
		getGraphString().setEditable(false);
		messageFromController.setBounds(30, 30, 560, 60);
		messageFromController.setLineWrap(true);
		messageFromController.setWrapStyleWord(true);
		messageFromController.setEditable(false);
		messageFromController.setBackground(tBoxColour);
		messageFromController.setForeground(Color.white);
		messageFromController.setBorder(compoundBorder);
		messageFromController.setFont(largeTBoxFont);
		
		titlePanel.setLayout(null);
		LogoLabel = new JLabel("");
		LogoLabel.setBounds(196, 11, 307, 55);
		
		Image img  = new ImageIcon(this.getClass().getResource("/TaaTLogo.png")).getImage();
		LogoLabel.setIcon(new ImageIcon(img));

		
		titlePanel.add(LogoLabel, BorderLayout.CENTER);
		
		
		
//		graphVisualPanel.setBounds(600, 5, 600, 600);
//		graphVisualPanel.setLayout(new BorderLayout(0, 5));
		graphVisualLabel.setForeground(titleColour);
		graphVisualLabel.setFont(labelFont);
		graphVisualLabel.setFont(font.deriveFont(attributes));
		graphLabelPanel.setPreferredSize(new Dimension(600,40));
		graphLabelPanel.setLayout(new BorderLayout());
		graphLabelPanel.add(graphVisualLabel, BorderLayout.CENTER);
		graphLabelPanel.setBackground(panelColour);
		graphVisualPanel.add(graphLabelPanel);
		graphVisualPanel.add(displayGraph);
		
		displayGraph.setPreferredSize(new Dimension(640, 630));
		displayGraph.setBackground(panelColour);

		
		viewLayout = new CircleLayout<MyVertex, MyEdge>(viewGraph);
        viewLayout.setSize(new Dimension(640,630));
		viewVV = new VisualizationViewer<MyVertex,MyEdge>(viewLayout,new Dimension(590,590));
		viewVV.setPreferredSize(new Dimension(640, 630));
		viewVertexPickedState= viewVV.getPickedVertexState();
		viewEdgePickedState=viewVV.getPickedEdgeState();
		noPickMouse = new MyPluggableGraphMouse();
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
        viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK,Color.BLACK));
        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);   
        viewVV.setBackground(buttonColour);
        viewVV.setBorder(new BevelBorder(BevelBorder.LOWERED, borderColour, borderColour));
        Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
			public Paint transform(MyEdge edge){
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
        gm = new PickingMouse();
        displayGraph.add(viewVV);		
        
        
        
        this.setMinimumSize(new Dimension(600,600));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);
	
		}
	
	
	 
	public JTextArea getPreferredSemanticsInfo() {
		return preferredSemanticsInfo;
	}



	public JPanel getChangeToBuild() {
		return changeToBuild;
	}



	public JButton getBuildButton() {
		return buildButton;
	}



	public JButton getGroundedLabellingButton() {
		return groundedLabellingButton;
	}



	public JTextArea getGroundedSemanticsInfo() {
		return groundedSemanticsInfo;
	}



	public JTextArea getAdmissibleSemanticsInfo() {
		return admissibleSemanticsInfo;
	}



	public JTextArea getAllAdmissibleSemanticInfo() {
		return allAdmissibleSemanticInfo;
	}



	public JScrollPane getAllAdmissiblePane() {
		return allAdmissiblePane;
	}



	public VisualizationViewer<MyVertex, MyEdge> getViewVV() {
		return viewVV;
	}



	public JButton getAddVertexButton() {
		return addVertexButton;
	}



	public JButton getAddEdgeButton() {
		return addEdgeButton;
	}



	public JButton getEnterButton() {
		return enterButton;
	}



	public JButton getResetGraph() {
		return resetGraph;
	}



	public JButton getDeleteVertexButton() {
		return deleteVertexButton;
	}



	public JButton getDeleteEdgeButton() {
		return deleteEdgeButton;
	}



	public JButton getSemanticsButton() {
		return semanticsButton;
	}



	public JButton getAdmissibleLabellingButton() {
		return admissibleLabellingButton;
	}



	public JButton getAllAdmissibleLabellingButton() {
		return allAdmissibleLabellingButton;
	}



	public JButton getPreferredButton() {
		return preferredButton;
	}



	public PickedState<MyVertex> getViewVertexPickedState() {
		return viewVertexPickedState;
	}



	public void setViewVertexPickedState(PickedState<MyVertex> viewVertexPickedState) {
		this.viewVertexPickedState = viewVertexPickedState;
	}



	public PickedState<MyEdge> getViewEdgePickedState() {
		return viewEdgePickedState;
	}



	public void setViewEdgePickedState(PickedState<MyEdge> viewEdgePickedState) {
		this.viewEdgePickedState = viewEdgePickedState;
	}



	public JTextArea getMessageFromController() {
		return messageFromController;
	}

	public void setMessageFromController(JTextArea messageFromController) {
		this.messageFromController = messageFromController;
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
	admissibleLabellingButton.addActionListener(controller);
	allAdmissibleLabellingButton.addActionListener(controller);
	viewVertexPickedState.addItemListener(controller1);
	viewEdgePickedState.addItemListener(controller1);
	semanticsButton.addActionListener(controller);
	buildButton.addActionListener(controller);
	preferredButton.addActionListener(controller);
	} 
	
	public void displayGraph(MyGraph g1){
	        viewLayout =g1.getGraphLayout();
	        viewLayout.setSize(new Dimension(640,640));
	        viewVV  = g1.getGraphVisualizationViewer(viewLayout);
	        viewVV.setBounds(0, 0,640, 630);
	        viewVV.setPreferredSize(new Dimension(640,630));
	        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR); 
	        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyVertex>());
	        viewVV.setBackground(buttonColour);
	        viewVV.setBorder(new BevelBorder(BevelBorder.LOWERED, borderColour, borderColour));
	        Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
				public Paint transform(MyEdge edge){
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
	        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
	        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);     
	       viewVV.getRenderContext().setEdgeLabelTransformer(new Transformer<MyEdge, String>() {
                public String transform(MyEdge e) {
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
  		getGraphString().setText("Graph Represented as a string \n"+ z);
	}
	
	public void setPickingMode(){
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
	}

	public MyVertex askForFromVertex() {

		viewVV.setPickedVertexState(viewVertexPickedState);
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
		messageFromController.setText("Please select an attacking argument");
		viewVertexPickedState.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK,Color.BLACK));
				Object pickedItem = e.getItem();
				
				if (pickedItem instanceof MyVertex) {
					MyVertex vertex = (MyVertex) pickedItem;
					
					if (viewVertexPickedState.isPicked(vertex)) {
						Transformer<MyVertex, Paint> vertexColour = new Transformer<MyVertex, Paint>() {
							public Paint transform(MyVertex vertex){
								if(viewVertexPickedState.isPicked(vertex)){
									return Color.YELLOW;
								}
								
								return tBoxColour;
							}
						};
						
						viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLACK));
						viewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						messageFromController.setText("You have selected "+ vertex);
						informationPanel.add(messageFromController);
						System.out.println("Argument " + vertex
								+ " is now selected");
						currentVertex=vertex;
					} 
					else if (pickedItem instanceof MyEdge){
						System.out.println("ha ha");
					}else {
						Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
							public Paint transform(MyEdge edge){
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
						informationPanel.add(messageFromController);
						System.out.println("Picking state and message box cleared");
						currentVertex=null;
					}
					
				}
			}
		});
		
		return currentVertex;
		
	}
	
	public MyVertex askForToVertex(MyVertex from) {
		viewVV.setPickedVertexState(viewVertexPickedState);
		gm.setMode(Mode.PICKING);
		viewVV.setGraphMouse(gm);
		messageFromController.setText("The attacking vertex will be "+from+". Please select an attacked argument and then press enter when you are done.");
		return currentVertex;
		
	}

	public void changeToNoPickingMouse() {
		currentVertex=null;
		PluggableGraphMouse noPick = new PluggableGraphMouse();
		viewVV.setGraphMouse(noPick);
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
				return tBoxColour;
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
				
				Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
					public Paint transform(MyEdge edge){
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
				if (pickedItem instanceof MyVertex) {
					deleteEdge=null;
					MyVertex vertex = (MyVertex) pickedItem;
					
					if (viewVertexPickedState.isPicked(vertex)) {
						Transformer<MyVertex, Paint> vertexColour = new Transformer<MyVertex, Paint>() {
							public Paint transform(MyVertex vertex){
								if(viewVertexPickedState.isPicked(vertex)){
									return Color.YELLOW;
								}
									return tBoxColour;	
							}
						};
						viewVV.getRenderContext().setVertexFillPaintTransformer(vertexColour);
						messageFromController.setText("You have selected "+ vertex+ ".");
						informationPanel.add(messageFromController);
						System.out.println("Argument " + vertex
								+ " is now selected");
						deleteVertex=vertex;
						System.out.print("Delete is set to "+deleteVertex);
					} else {
						messageFromController.setText("");
						informationPanel.add(messageFromController);
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
			Transformer<MyVertex,Paint> vertexColour = new Transformer<MyVertex, Paint>(){
				public Paint transform(MyVertex vertex) {
					return tBoxColour;
				}
			};
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
				Object pickedItem = e.getItem();
				if (pickedItem instanceof MyEdge) {
					currentVertex=null;
					System.out.println("something has changed");
					MyEdge edge = (MyEdge) pickedItem;
					
					if (viewEdgePickedState.isPicked(edge)) {
						
						Transformer<MyEdge, Paint> edgeColour = new Transformer<MyEdge, Paint>() {
							public Paint transform(MyEdge edge){
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
					informationPanel.add(messageFromController);
						System.out.println("Edge " + edge+ " is now selected");
						deleteEdge=edge;
						System.out.print("Delete is set to "+deleteEdge.getLabel());
//						else {
//						MessageFromController.setText("");
//						buttonsPanel.add(MessageFromController);
//						System.out.println("Picking state and message box cleared");
//						deleteVertex=null;
//					}
				}
			
			else if (pickedItem instanceof MyVertex) {
			final MyVertex vertex = (MyVertex) pickedItem;
				
				if (viewVertexPickedState.isPicked(vertex)) {
					viewEdgePickedState.clear();
					viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK, Color.BLACK));
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK,Color.BLACK));
				} 
			}
				else {
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
					messageFromController.setText("");
					informationPanel.add(messageFromController);
					System.out.println("Picking state and message box cleared");
					deleteVertex=null;
				}
		
	}
} ;
});
	}

	
	public void clearSemanticsInfoBoxes(){
		groundedSemanticsInfo.setText("");
		admissibleSemanticsInfo.setText("");
		allAdmissibleSemanticInfo.setText("");
		preferredSemanticsInfo.setText("");
	}
	
	public void clearBuildInforBoxes(){
		messageFromController.setText("");
		getGraphString().setText("");
	}
	
	public void openAdmissibleFrame(String string){
		new addmissibleFrame(string);
	}
	
	public void changeToSemantics(){
		messageFromController.setText("");
		graphChangePanel.remove(buttonsPanel);
		graphChangePanel.remove(informationPanel);
		graphChangePanel.add(graphSemanticsPanel);
//		titlePanel.setPreferredSize(new Dimension(650,60));
		graphChangePanel.validate();
		graphChangePanel.repaint();
	}
	public void changeToBuild(){
		graphChangePanel.remove(graphSemanticsPanel);
		graphChangePanel.add(buttonsPanel,BorderLayout.CENTER);
		graphChangePanel.add(informationPanel, BorderLayout.SOUTH);
		titlePanel.setPreferredSize(new Dimension(650,80));
		graphChangePanel.validate();
		graphChangePanel.repaint();
	}



	public JTextArea getGraphString() {
		return graphString;
	}



	public void setGraphString(JTextArea graphString) {
		this.graphString = graphString;
	}
}