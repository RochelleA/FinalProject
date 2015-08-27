package gui;

import core.*;

import java.util.Observable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Toolkit;

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

	private JScrollPane contentPane = new JScrollPane();
	private JPanel graphVisualPanel = new JPanel();
	private JPanel graphViewPanel= new JPanel();
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
	private JPanel allAdmissiblePanel= new JPanel();
	private JPanel completeSemanticsPanel= new JPanel();
	private JPanel graphChangePanel = new JPanel();
	private JPanel graphLabelPanel = new JPanel();
	private JPanel changeToBuildPanel = new JPanel();
	private JLabel graphVisualLabel = new JLabel("Framework Visualisation",SwingConstants.CENTER);
	private JLabel graphEditingLabel = new JLabel("Framework Editor");
	private JLabel graphSemanticslLabel = new JLabel("Framework Semantics");
	private JButton addArgumentButton = new JButton("Add Argument");
	private JButton addAttackButton = new JButton("Add Attack");
	private JButton enterButton = new JButton("Enter");
	private JButton resetGraphButton = new JButton("Reset Framework");
	private JButton deleteArgumentButton = new JButton("Delete Argument");
	private JButton deleteAttackButton = new JButton("Delete Attack");
	private JButton semanticsButton = new JButton("Change to Semantics View");
	private JButton buildButton = new JButton("Return to Build View");
	private JButton groundedLabellingButton = new JButton("Grounded Labelling");
	private JButton admissibleLabellingButton = new JButton("Admissible Labelling");
	private JButton allAdmissibleLabellingButton = new JButton("All Admissble Labellings");
	private JButton resetLabelsButton = new JButton("Reset Labels");
	private JButton preferredButton = new JButton("All Preferred Labellings");
	private JButton completeButton = new JButton("All Complete Labellings");
	private JTextArea groundedSemanticsInfo = new JTextArea();
	private JTextArea admissibleSemanticsInfo = new JTextArea();
	private JTextArea preferredSemanticsInfo = new JTextArea();
	private JTextArea areYouReady = new JTextArea();
	private JTextArea allAdmissibleSemanticInfo = new JTextArea();
	private JTextArea completeSemanticInfo = new JTextArea();
	private JScrollPane allAdmissiblePane =  new JScrollPane(allAdmissibleSemanticInfo);
	private JScrollPane preferredPane = new JScrollPane(preferredSemanticsInfo);
	private JScrollPane completePane = new JScrollPane(completeSemanticInfo);
	private JScrollPane admissiblePane = new JScrollPane(admissibleSemanticsInfo);
	private JScrollPane groundedPane = new JScrollPane(groundedSemanticsInfo);
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
	private JPanel displayGraphPanel = new JPanel();
	private MyGraph viewGraph = new MyGraph();
	private CircleLayout<MyArg, MyAtt> viewLayout;
	private VisualizationViewer<MyArg,MyAtt> viewVV;
	private PickedState<MyArg> viewArgPickedState;
	private PickedState<MyAtt> viewAttPickedState;
	private DefaultModalGraphMouse<MyArg, MyAtt> mouse;
	MyArg currentArg;
	MyArg deleteArgument;
	MyAtt deleteAttack;
//	MyPluggableGraphMouse noPickMouse;
	PluggableGraphMouse noPickMouse;
	GraphMousePlugin pickingMouse;
	private final JLabel LogoLabel;
	
	View() {

		this.setIconImage(Toolkit.getDefaultToolkit().getImage("/TaaTLogo.png"));

		resetLabelsButton.setBackground(buttonColour);
		resetLabelsButton.setPreferredSize(buildButton.getPreferredSize());
		
		buildButton.setBackground(buttonColour);

		changeToBuildPanel.setBackground(panelColour);
		changeToBuildPanel.add(buildButton);
		changeToBuildPanel.add(resetLabelsButton);
		changeToBuildPanel.setPreferredSize(new Dimension(400,60));

		preferredSemanticsInfo.setBounds(0, 0, 380, 100);
		preferredSemanticsInfo.setLineWrap(true);
		preferredSemanticsInfo.setWrapStyleWord(true);
		preferredSemanticsInfo.setEditable(false);
		preferredSemanticsInfo.setBackground(tBoxColour);
		preferredSemanticsInfo.setForeground(Color.white);
		
		preferredPane.setBounds(260, 10, 370, 100);
//		preferredPane.setLayout(null);
		preferredPane.setBackground(tBoxColour);
//		preferredPane.add(preferredSemanticsInfo);
		
		preferredButton.setBounds(30,10,200,30);
		preferredButton.setBackground(buttonColour);

		preferredSemanticsPanel.setPreferredSize(new Dimension(400, 100));
		preferredSemanticsPanel.setMaximumSize(new Dimension(400, 300));
		preferredSemanticsPanel.setLayout(null);
		preferredSemanticsPanel.setBackground(panelColour);
		preferredSemanticsPanel.add(preferredButton);
		preferredSemanticsPanel.add(preferredPane);
		
		completeSemanticInfo.setBounds(0, 0, 380, 100);
		completeSemanticInfo.setLineWrap(true);
		completeSemanticInfo.setEditable(false);
		completeSemanticInfo.setBackground(tBoxColour);
		completeSemanticInfo.setForeground(Color.white);
		
		completePane.setBounds(260, 10, 370, 100);
		completePane.setBackground(tBoxColour);
		
		completeButton.setBounds(30, 10, 200, 30);
		completeButton.setBackground(buttonColour);
		
		completeSemanticsPanel.setPreferredSize(new Dimension(400,100));
		completeSemanticsPanel.setMaximumSize(new Dimension(400, 300));
		completeSemanticsPanel.setLayout(null);
		completeSemanticsPanel.setBackground(panelColour);
		completeSemanticsPanel.add(completeButton);
		completeSemanticsPanel.add(completePane);

		allAdmissibleSemanticInfo.setBackground(tBoxColour);
		allAdmissibleSemanticInfo.setForeground(Color.white);
		allAdmissibleSemanticInfo.setBounds(260, 10, 370, 100);
		allAdmissibleSemanticInfo.setEditable(false);
		
		allAdmissiblePane.setBounds(260, 10, 370, 100);
		allAdmissiblePane.setBackground(tBoxColour);
				
		allAdmissibleLabellingButton.setBounds(30, 10, 200, 30);
		allAdmissibleLabellingButton.setBackground(buttonColour);
		
		allAdmissiblePanel.setLayout(null);
		allAdmissiblePanel.setPreferredSize(new Dimension(400, 100));
		allAdmissiblePanel.setBackground(panelColour);
	    allAdmissiblePanel.add(allAdmissibleLabellingButton);
		allAdmissiblePanel.add(allAdmissiblePane);
		
		admissibleSemanticsInfo.setBounds(260, 10, 370, 100);
		admissibleSemanticsInfo.setLineWrap(true);
		admissibleSemanticsInfo.setWrapStyleWord(true);
		admissibleSemanticsInfo.setEditable(false);
		admissibleSemanticsInfo.setBackground(tBoxColour);
		admissibleSemanticsInfo.setForeground(Color.WHITE);
		
		admissiblePane.setBounds(260, 10, 370, 100);
		admissiblePane.setBackground(tBoxColour);
		
		admissibleLabellingButton.setBounds(30, 10, 200, 30);
		admissibleLabellingButton.setBackground(buttonColour);
		
		admissibleSemanticsPanel.setLayout(null);
		admissibleSemanticsPanel.setPreferredSize(new Dimension(400, 100));
		admissibleSemanticsPanel.setMaximumSize(new Dimension(400, 300));
		admissibleSemanticsPanel.setBackground(panelColour);
		admissibleSemanticsPanel.add(admissibleLabellingButton);
		admissibleSemanticsPanel.add(admissiblePane);
		
		groundedSemanticsInfo.setBounds(260, 10, 370, 100);
		groundedSemanticsInfo.setLineWrap(true);
		groundedSemanticsInfo.setWrapStyleWord(true);
		groundedSemanticsInfo.setEditable(false);
		groundedSemanticsInfo.setBackground(tBoxColour);
		groundedSemanticsInfo.setForeground(Color.white);

		groundedPane.setBounds(260, 10, 370, 100);
		groundedPane.setBackground(tBoxColour);
		
		groundedLabellingButton.setBounds(30, 10, 200, 30);
		groundedLabellingButton.setBackground(buttonColour);
		
		groundedSemanticsPanel.setLayout(null);
		groundedSemanticsPanel.setPreferredSize(new Dimension(400, 100));
		groundedSemanticsPanel.setMaximumSize(new Dimension(400, 300));
		groundedSemanticsPanel.setBackground(panelColour);
		groundedSemanticsPanel.add(groundedLabellingButton);
		groundedSemanticsPanel.add(groundedPane);

		semanticsContentsPanel.setLayout(new BorderLayout(0, 10));
		semanticsContentsPanel.setPreferredSize(new Dimension(420, 370));
		semanticsContentsPanel.setMaximumSize(new Dimension(430, 380));
		semanticsContentsPanel.setBackground(panelColour);
		semanticsContentsPanel.setLayout(new GridLayout(5, 1, 0, 1));
		semanticsContentsPanel.add(groundedSemanticsPanel);
		semanticsContentsPanel.add(admissibleSemanticsPanel);
		semanticsContentsPanel.add(allAdmissiblePanel);
		semanticsContentsPanel.add(completeSemanticsPanel);
		semanticsContentsPanel.add(preferredSemanticsPanel);
//		semanticsContentsPanel.add(groundedSemanticsPanel, BorderLayout.PAGE_START);
//		semanticsContentsPanel.add(admissibleSemanticsPanel, BorderLayout.CENTER);
//		semanticsContentsPanel.add(preferredSemanticsPanel, BorderLayout.PAGE_END);
		

		graphSemanticslLabel.setFont(labelFont);
		graphSemanticslLabel.setForeground(titleColour);
		graphSemanticslLabel.setHorizontalAlignment(JLabel.CENTER);
		
		semanticsLabelPanel.add(graphSemanticslLabel);
		semanticsLabelPanel.setPreferredSize(new Dimension(400,25));
		semanticsLabelPanel.setBackground(panelColour);
//		Font font = graphSemanticslLabel.getFont();
//		Map attributes = font.getAttributes();
//		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
//		graphSemanticslLabel.setFont(font.deriveFont(attributes));


		graphSemanticsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphSemanticsPanel.setPreferredSize(new Dimension(450,400));
		graphSemanticsPanel.setBackground(panelColour);
		graphSemanticsPanel.setLayout(new BorderLayout(0, 10));
		graphSemanticsPanel.add(changeToBuildPanel,BorderLayout.SOUTH);
		graphSemanticsPanel.add(semanticsContentsPanel, BorderLayout.CENTER);
		graphSemanticsPanel.add(semanticsLabelPanel, BorderLayout.NORTH);

		
		graphString.setBounds(30, 120,560, 150);
		graphString.setLineWrap(true);
		graphString.setWrapStyleWord(true);
		graphString.setBorder(compoundBorder);
		graphString.setBackground(tBoxColour);
		graphString.setForeground(Color.white);
		graphString.setMargin(new Insets(15, 15, 15, 15));
		graphString.setFont(smallTBoxFont);
		graphString.setEditable(false);
	
		messageFromController.setBounds(30, 30, 560, 60);
		messageFromController.setLineWrap(true);
		messageFromController.setWrapStyleWord(true);
		messageFromController.setEditable(false);
		messageFromController.setBackground(tBoxColour);
		messageFromController.setForeground(Color.white);
		messageFromController.setBorder(compoundBorder);
		messageFromController.setFont(largeTBoxFont);

		informationPanel.setBackground(panelColour);
		informationPanel.setPreferredSize(new Dimension(650, 300));
		informationPanel.setMinimumSize(new Dimension(650, 300));
		informationPanel.setBounds(30, 440, 620, 300);
		informationPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		informationPanel.setLayout(null);
		informationPanel.add(getGraphString());
		informationPanel.add(messageFromController);

		
		semanticsButton.setBounds(30, 210, 210, 30);
		semanticsButton.setMargin(new Insets(0, 0, 0, 0));
		semanticsButton.setBackground(buttonColour);
		semanticsButton.setFont(buttonFont);
		
		areYouReady.setBounds(30, 180, 300, 30);
		areYouReady.setBackground(panelColour);
		areYouReady.setForeground(Color.WHITE);
		areYouReady.setFont(largeTextFont);
		areYouReady.setText("Are you ready to test semantics?");
		areYouReady.setEditable(false);


		enterButton.setBounds(365, 120, 150, 30);
		enterButton.setMargin(new Insets(0, 0, 0, 0));
		enterButton.setBackground(buttonColour);
		enterButton.setFont(buttonFont);

		deleteAttackButton.setBounds(200, 120, 150, 30);
		deleteAttackButton.setMargin(new Insets(0, 0, 0, 0));
		deleteAttackButton.setBackground(buttonColour);
		deleteAttackButton.setFont(buttonFont);

		addAttackButton.setBounds(30, 120, 150, 30);
		addAttackButton.setMargin(new Insets(0, 0, 0, 0));
		addAttackButton.setBackground(buttonColour);
		addAttackButton.setFont(buttonFont);
		
		resetGraphButton.setBounds(365, 60, 150, 30);
		resetGraphButton.setMargin(new Insets(0, 0, 0, 0));
		resetGraphButton.setFont(buttonFont);
		resetGraphButton.setBackground(buttonColour);

		deleteArgumentButton.setBounds(200, 60, 150, 30);
		deleteArgumentButton.setMargin(new Insets(0, 0, 0, 0));
		deleteArgumentButton.setBackground(buttonColour);
		deleteArgumentButton.setFont(buttonFont);

		addArgumentButton.setBounds(30, 60, 150, 30);
		addArgumentButton.setMargin(new Insets(0, 0, 0, 0));
		addArgumentButton.setBackground(buttonColour);
		addArgumentButton.setFont(buttonFont);

		graphEditingLabel.setBounds(260, 30, 130, 20);
		graphEditingLabel.setBackground(panelColour);
		graphEditingLabel.setForeground(titleColour);

		
		
		
		buttonsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		buttonsPanel.setBackground(panelColour);
		buttonsPanel.setPreferredSize(new Dimension(650,300));
		buttonsPanel.setLayout(null);
		buttonsPanel.add(graphEditingLabel);
		buttonsPanel.setBounds(30, 30, 400, 300);
		buttonsPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		buttonsPanel.add(addArgumentButton);
		buttonsPanel.add(addAttackButton);
		buttonsPanel.add(enterButton);
		buttonsPanel.add(resetGraphButton);
		buttonsPanel.add(deleteAttackButton);
		buttonsPanel.add(deleteArgumentButton);
		buttonsPanel.add(semanticsButton);
		buttonsPanel.add(areYouReady);
		buttonsPanel.add(graphEditingLabel);
//		buttonsPanel.add(graphVisualLabel);


		Image img  = new ImageIcon(this.getClass().getResource("/TaaTLogo.png")).getImage();
		
		LogoLabel = new JLabel("");
		LogoLabel.setBounds(210, 11, 307, 55);
		LogoLabel.setIcon(new ImageIcon(img));

		
		titlePanel.setBackground(panelColour);
//		titlePanel.setLayout(new BorderLayout());
		titlePanel.setPreferredSize(new Dimension(650,80));
		titlePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		titlePanel.setLayout(null);
		titlePanel.add(LogoLabel, BorderLayout.CENTER);

		graphChangePanel.setBackground(panelColour);
		graphChangePanel.setPreferredSize(new Dimension(650, 650));
		graphChangePanel.setMinimumSize(new Dimension(650, 650));
		graphChangePanel.setLayout(new BorderLayout(0, 0));
		graphChangePanel.add(buttonsPanel,BorderLayout.NORTH);
		graphChangePanel.add(informationPanel,BorderLayout.CENTER);
		
		graphViewPanel.setBackground(panelColour);
		graphViewPanel.setPreferredSize(new Dimension(650, 650));
		graphViewPanel.setMinimumSize(new Dimension(650, 650));
		graphViewPanel.setLayout(new BorderLayout(0, 0));
		graphViewPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphViewPanel.add(titlePanel, BorderLayout.NORTH);
		graphViewPanel.add(graphChangePanel, BorderLayout.CENTER);
		
		

		graphVisualLabel.setForeground(titleColour);
		graphVisualLabel.setFont(labelFont);
//		graphVisualLabel.setFont(font.deriveFont(attributes));

		graphLabelPanel.setPreferredSize(new Dimension(600,40));
		graphLabelPanel.setMinimumSize(new Dimension(600, 40));
		graphLabelPanel.setLayout(new BorderLayout());
		graphLabelPanel.setBackground(panelColour);
		graphLabelPanel.add(graphVisualLabel, BorderLayout.CENTER);
		
		viewLayout = new CircleLayout<MyArg, MyAtt>(viewGraph);
        viewLayout.setSize(new Dimension(640,630));
		viewVV = new VisualizationViewer<MyArg,MyAtt>(viewLayout,new Dimension(590,590));
		viewVV.setPreferredSize(new Dimension(640, 630));
		viewArgPickedState= viewVV.getPickedVertexState();
		viewAttPickedState=viewVV.getPickedEdgeState();
		noPickMouse = new PluggableGraphMouse();
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
        viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultAttLabelRenderer(Color.BLACK,Color.BLACK));
        viewVV.setBackground(buttonColour);
        viewVV.setBorder(new BevelBorder(BevelBorder.LOWERED, borderColour, borderColour));
        Transformer<MyAtt, Paint> attColour = new Transformer<MyAtt, Paint>() {
			public Paint transform(MyAtt att){
				if(viewAttPickedState.isPicked(att)){
					setBackground(Color.BLUE);
					setForeground(Color.BLUE);
					return Color.BLUE;
				}
					return Color.BLACK;	
			}
		};
		viewVV.getRenderContext().setEdgeDrawPaintTransformer(attColour);
		viewVV.getRenderContext().setArrowDrawPaintTransformer(attColour);
		viewVV.getRenderContext().setArrowFillPaintTransformer(attColour);
        mouse = new PickingMouse();

        
		displayGraphPanel.setPreferredSize(new Dimension(650, 650));
		displayGraphPanel.setMinimumSize(new Dimension(650, 650));
		displayGraphPanel.setBackground(panelColour);
		displayGraphPanel.add(viewVV);

		graphVisualPanel.setBackground(panelColour);
		graphViewPanel.setPreferredSize(new Dimension(650, 650));
		graphViewPanel.setMinimumSize(new Dimension(650, 650));
		graphVisualPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		graphVisualPanel.add(graphLabelPanel);
		graphVisualPanel.add(displayGraphPanel);

		mainPanel.setPreferredSize(new Dimension(1300,690));
		mainPanel.setMinimumSize(new Dimension(1300, 690));
		mainPanel.setBackground(panelColour);
		mainPanel.setLayout(new GridLayout(0,2,15,15));
		mainPanel.add(graphViewPanel);
		mainPanel.add(graphVisualPanel);


		contentPane.setBackground(Color.BLACK);
		contentPane.setViewportView(mainPanel);
		contentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		contentPane= new JScrollPane(mainPanel);

		this.add(contentPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(600,695));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.pack();
		this.setVisible(true);

		}
	
	



	public JPanel getChangeToBuild() {
		return changeToBuildPanel;
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


//
//	public JScrollPane getAllAdmissiblePane() {
//		return allAdmissiblePane;
//	}
//
	 
	public JTextArea getCompleteSemanticsInfo() {
		return completeSemanticInfo;
	}

	 
	public JTextArea getPreferredSemanticsInfo() {
		return preferredSemanticsInfo;
	}


	public VisualizationViewer<MyArg, MyAtt> getViewVV() {
		return viewVV;
	}



	public JButton getAddArgumentButton() {
		return addArgumentButton;
	}



	public JButton getAddAttackButton() {
		return addAttackButton;
	}



	public JButton getEnterButton() {
		return enterButton;
	}



	public JButton getResetGraph() {
		return resetGraphButton;
	}



	public JButton getDeleteArgumentButton() {
		return deleteArgumentButton;
	}



	public JButton getDeleteAttackButton() {
		return deleteAttackButton;
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



	public JButton getCompleteButton() {
		return completeButton;
	}



	public JButton getPreferredButton() {
		return preferredButton;
	}



	public JButton getResetLabelsButton() {
		return resetLabelsButton;
	}



	public PickedState<MyArg> getViewArgPickedState() {
		return viewArgPickedState;
	}



	public void setViewArgPickedState(PickedState<MyArg> viewArgPickedState) {
		this.viewArgPickedState = viewArgPickedState;
	}



	public PickedState<MyAtt> getViewAttPickedState() {
		return viewAttPickedState;
	}



	public void setViewAttPickedState(PickedState<MyAtt> viewAttPickedState) {
		this.viewAttPickedState = viewAttPickedState;
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
  	
	public void addController(ActionListener controller){
		System.out.println("View      : adding controller");
	addArgumentButton.addActionListener(controller);	
	addAttackButton.addActionListener(controller);
	enterButton.addActionListener(controller);
	resetGraphButton.addActionListener(controller);
	deleteArgumentButton.addActionListener(controller);
	deleteAttackButton.addActionListener(controller);
	semanticsButton.addActionListener(controller);
	completeButton.addActionListener(controller);
	
	groundedLabellingButton.addActionListener(controller);
	admissibleLabellingButton.addActionListener(controller);
	allAdmissibleLabellingButton.addActionListener(controller);
	preferredButton.addActionListener(controller);
	buildButton.addActionListener(controller);
	resetLabelsButton.addActionListener(controller);

	
	} 
	
	private void displayGraph(MyGraph g1){
		displayGraphPanel.setPreferredSize(new Dimension(640, 630));
		displayGraphPanel.setBackground(panelColour);
		viewLayout = g1.getGraphLayout();
        viewLayout.setSize(new Dimension(640,630));
		viewVV = g1.getGraphVisualizationViewer(viewLayout);
		viewVV.setPreferredSize(new Dimension(640, 630));
		viewArgPickedState= viewVV.getPickedVertexState();
		viewAttPickedState=viewVV.getPickedEdgeState();
//		noPickMouse = new PluggableGraphMouse();
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);  
        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyAtt>());
        viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultAttLabelRenderer(Color.BLACK,Color.BLACK));
        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);   
        viewVV.setBackground(buttonColour);
        viewVV.setBorder(new BevelBorder(BevelBorder.LOWERED, borderColour, borderColour));
        Transformer<MyAtt, Paint> attColour = new Transformer<MyAtt, Paint>() {
			public Paint transform(MyAtt att){
				if(viewAttPickedState.isPicked(att)){
					setBackground(Color.BLUE);
					setForeground(Color.BLUE);
					return Color.BLUE;
				}
					return Color.BLACK;	
			}
		};
		viewVV.getRenderContext().setEdgeDrawPaintTransformer(attColour);
		viewVV.getRenderContext().setArrowDrawPaintTransformer(attColour);
		viewVV.getRenderContext().setArrowFillPaintTransformer(attColour);
        mouse = new PickingMouse();
        viewVV.getRenderContext().setLabelOffset(17);
//        viewArgPickedState = viewVV.getPickedVertexState();
        displayGraphPanel.removeAll();
        displayGraphPanel.add(viewVV);
        displayGraphPanel.validate();
        displayGraphPanel.repaint();
		
		
//	        viewLayout =g1.getGraphLayout();
//	        viewLayout.setSize(new Dimension(640,630));
//	        viewVV  = g1.getGraphVisualizationViewer(viewLayout);
//	        viewVV.setBounds(0, 0,640, 630);
//	        viewVV.setPreferredSize(new Dimension(640,630));
//	        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
//	        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
//	        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR); 
//	        viewVV.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<MyArg>());
//	        viewVV.setBackground(buttonColour);
//	        viewVV.setBorder(new BevelBorder(BevelBorder.LOWERED, borderColour, borderColour));
//	        Transformer<MyEdge, Paint> attColour = new Transformer<MyEdge, Paint>() {
//				public Paint transform(MyEdge att){
//					if(viewEdgePickedState.isPicked(att)){
//						setBackground(Color.BLUE);
//						setForeground(Color.BLUE);
//						return Color.BLUE;
//					}
//						return Color.BLACK;	
//				}
//			};
//			viewVV.getRenderContext().setEdgeDrawPaintTransformer(attColour);
//			viewVV.getRenderContext().setArrowDrawPaintTransformer(attColour);
//			viewVV.getRenderContext().setArrowFillPaintTransformer(attColour);
//	        viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultEdgeLabelRenderer(Color.BLACK,Color.BLACK));
//	        viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultVertexLabelRenderer(Color.BLACK, Color.BLACK));
//	        viewVV.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<MyEdge>());
//	        viewVV.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);     
//	       viewVV.getRenderContext().setEdgeLabelTransformer(new Transformer<MyEdge, String>() {
//                public String transform(MyAtt e) {
//                    return (e.getLabel()) ;
//                }
//                
//            });
//	       viewVV.getRenderContext().setLabelOffset(17);
//	        viewArgPickedState = viewVV.getPickedVertexState();
//	        displayGraph.removeAll();
//	        displayGraph.add(viewVV);
	}
	

	public void displayGraphAsString(MyGraph g1){
  		String z= g1.toString();
  		getGraphString().setText("Framework Represented as a string \n"+ z);
	}
	
	public void setPickingMode(){
		mouse.setMode(Mode.PICKING);
		viewVV.setGraphMouse(mouse);
	}

	public MyArg askForFromArgument() {

		viewVV.setPickedVertexState(viewArgPickedState);
		mouse.setMode(Mode.PICKING);
		viewVV.setGraphMouse(mouse);
		messageFromController.setText("Please select an attacking argument");
		viewArgPickedState.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultArgLabelRenderer(Color.BLACK,Color.BLACK));
				Object pickedItem = e.getItem();
				
				if (pickedItem instanceof MyArg) {
					MyArg Arg = (MyArg) pickedItem;
					
					if (viewArgPickedState.isPicked(Arg)) {
						Transformer<MyArg, Paint> ArgColour = new Transformer<MyArg, Paint>() {
							public Paint transform(MyArg Arg){
								if(viewArgPickedState.isPicked(Arg)){
									return Color.YELLOW;
								}
								
								return tBoxColour;
							}
						};
						
						viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultAttLabelRenderer(Color.BLACK, Color.BLACK));
						viewVV.getRenderContext().setVertexFillPaintTransformer(ArgColour);
						messageFromController.setText("You have selected "+ Arg+". Press enter to use this argument.");
						informationPanel.add(messageFromController);
						System.out.println("Argument " + Arg
								+ " is now selected");
						currentArg=Arg;
					} 
					else if (pickedItem instanceof MyAtt){
						System.out.println("ha ha");
					}else {
//						Transformer<MyAtt, Paint> attColour = new Transformer<MyAtt, Paint>() {
//							public Paint transform(MyAtt att){
//								if(viewAttPickedState.isPicked(att)){
//									setBackground(Color.BLUE);
//									setForeground(Color.BLUE);
//									return Color.BLUE;
//								}
//									return Color.BLACK;	
//							}
//						};
//						viewVV.getRenderContext().setEdgeDrawPaintTransformer(attColour);
//						viewVV.getRenderContext().setArrowDrawPaintTransformer(attColour);
//						viewVV.getRenderContext().setArrowFillPaintTransformer(attColour);
						messageFromController.setText("");
						informationPanel.add(messageFromController);
						System.out.println("Picking state and message box cleared");
						currentArg=null;
					}
					
				}
			}
		});
		return currentArg;
		
	}
	
	public MyArg askForToArgument(MyArg from) {
		viewVV.setPickedVertexState(viewArgPickedState);
		mouse.setMode(Mode.PICKING);
		viewVV.setGraphMouse(mouse);
		messageFromController.setText("The attacking argument will be "+from+". Please select an attacked argument and then press enter when you are done.");
		return currentArg;
		
	}

	public void changeToNoPickingMouse() {
		currentArg=null;
		PluggableGraphMouse noPick = new PluggableGraphMouse();
		viewVV.setGraphMouse(noPick);
	}

	public void colourGraph(MyGraph graph){
	 Transformer<MyArg,Paint> ArgColour = new Transformer<MyArg, Paint>() {
		public Paint transform(MyArg Arg){
			if(Arg.getLabel()=="IN"){
				return Color.GREEN;
			}
			else if(Arg.getLabel()=="OUT"){
				return Color.RED;
			}
			else if(Arg.getLabel()=="UNDEC"){
				return Color.ORANGE;
			}
			else{
				return tBoxColour;
			}
		}
	};
	viewVV.getRenderContext().setVertexFillPaintTransformer(ArgColour);
	}
	
	public void deleteArgument(){
		viewVV.setPickedVertexState(viewArgPickedState);
		mouse.setMode(Mode.PICKING);
		viewVV.setGraphMouse(mouse);
		messageFromController.setText("Please select a argument to delete. Deleting this argument will delete all attacks connected to it. Press Enter once you have selected the argument.");
		viewArgPickedState.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				Transformer<MyAtt, Paint> attColour = new Transformer<MyAtt, Paint>() {
					public Paint transform(MyAtt att){
						if(viewAttPickedState.isPicked(att)){
							setBackground(Color.BLUE);
							setForeground(Color.BLUE);
							return Color.BLUE;
						}
							return Color.BLACK;	
					}
				};
				viewVV.getRenderContext().setEdgeDrawPaintTransformer(attColour);
				viewVV.getRenderContext().setArrowDrawPaintTransformer(attColour);
				viewVV.getRenderContext().setArrowFillPaintTransformer(attColour);
				Object pickedItem = e.getItem();
				if (pickedItem instanceof MyArg) {
					deleteAttack=null;
					MyArg Arg = (MyArg) pickedItem;
					
					if (viewArgPickedState.isPicked(Arg)) {
						Transformer<MyArg, Paint> ArgColour = new Transformer<MyArg, Paint>() {
							public Paint transform(MyArg Arg){
								if(viewArgPickedState.isPicked(Arg)){
									return Color.YELLOW;
								}
									return tBoxColour;	
							}
						};
						viewVV.getRenderContext().setVertexFillPaintTransformer(ArgColour);
						messageFromController.setText("You have selected "+ Arg+ ". Press enter to use this argument");
						informationPanel.add(messageFromController);
						System.out.println("Argument " + Arg
								+ " is now selected");
						deleteArgument=Arg;
						System.out.print("Delete is set to "+deleteArgument);
					} else {
						messageFromController.setText("");
						informationPanel.add(messageFromController);
						System.out.println("Picking state and message box cleared");
						deleteArgument=null;
					}
				}
			}
		});
		
	}
	public void deleteAttack(){
		viewArgPickedState.clear();
		viewVV.setPickedEdgeState(viewAttPickedState);
		mouse.setMode(Mode.PICKING);
		viewVV.setGraphMouse(mouse);
		messageFromController.setText("Please select an attack to delete. Press Enter once you have selected the attack.");
		viewAttPickedState.addItemListener(new ItemListener() {
			Transformer<MyArg,Paint> ArgColour = new Transformer<MyArg, Paint>(){
				public Paint transform(MyArg Arg) {
					return tBoxColour;
				}
			};
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultArgLabelRenderer(Color.BLACK, Color.BLACK));
				Object pickedItem = e.getItem();
				if (pickedItem instanceof MyAtt) {
					currentArg=null;
					System.out.println("something has changed");
					MyAtt att = (MyAtt) pickedItem;
					
					if (viewAttPickedState.isPicked(att)) {
						
						Transformer<MyAtt, Paint> attColour = new Transformer<MyAtt, Paint>() {
							public Paint transform(MyAtt att){
								if(viewAttPickedState.isPicked(att)){
									setBackground(Color.BLUE);
									setForeground(Color.BLUE);
									return Color.BLUE;
								}
									return Color.BLACK;	
							}
						};
						
						viewVV.getRenderContext().setVertexFillPaintTransformer(ArgColour);
						viewVV.getRenderContext().setEdgeDrawPaintTransformer(attColour);
						viewVV.getRenderContext().setArrowDrawPaintTransformer(attColour);
						viewVV.getRenderContext().setArrowFillPaintTransformer(attColour);
						messageFromController.setText("You have selected "+ att.getLabel()+ ". Press enter to use this attack.");
					viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultAttLabelRenderer(Color.BLACK, Color.BLUE));
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultArgLabelRenderer(Color.BLACK, Color.BLACK));
					informationPanel.add(messageFromController);
						System.out.println("Att " + att+ " is now selected");
						deleteAttack=att;
						System.out.print("Delete is set to "+deleteAttack.getLabel());
//						else {
//						MessageFromController.setText("");
//						buttonsPanel.add(MessageFromController);
//						System.out.println("Picking state and message box cleared");
//						deleteVertex=null;
//					}
				}
			
			else if (pickedItem instanceof MyArg) {
			final MyArg Arg = (MyArg) pickedItem;
				
				if (viewArgPickedState.isPicked(Arg)) {
					viewAttPickedState.clear();
					viewVV.getRenderContext().setEdgeLabelRenderer(new MyDefaultAttLabelRenderer(Color.BLACK, Color.BLACK));
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultArgLabelRenderer(Color.BLACK,Color.BLACK));
				} 
			}
				else {
					viewVV.getRenderContext().setVertexLabelRenderer(new MyDefaultArgLabelRenderer(Color.BLACK, Color.BLACK));
					messageFromController.setText("");
					informationPanel.add(messageFromController);
					System.out.println("Picking state and message box cleared");
					deleteArgument=null;
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
		completeSemanticInfo.setText("");
	}
	
	public void clearBuildInforBoxes(){
		messageFromController.setText("");
		getGraphString().setText("");
	}
	
//	public void openAdmissibleFrame(String string){
//		new addmissibleFrame(string);
//	}
	
	public void changeToSemantics(){
		messageFromController.setText("");
		graphViewPanel.remove(graphChangePanel);
		graphViewPanel.add(graphSemanticsPanel);
//		titlePanel.setPreferredSize(new Dimension(650,60));
		graphViewPanel.validate();
		graphViewPanel.repaint();
	}
	public void changeToBuild(){
		graphViewPanel.remove(graphSemanticsPanel);
		graphViewPanel.add(graphChangePanel);
		titlePanel.setPreferredSize(new Dimension(650,80));
		graphViewPanel.validate();
		graphViewPanel.repaint();
	}



	public JTextArea getGraphString() {
		return graphString;
	}



	public void setGraphString(JTextArea graphString) {
		this.graphString = graphString;
	}
}