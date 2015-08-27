package gui;

import java.awt.Color;

import edu.uci.ics.jung.visualization.renderers.DefaultEdgeLabelRenderer;

public class MyDefaultAttLabelRenderer extends DefaultEdgeLabelRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3841988462775172992L;
	protected Color unpickedColour = Color.BLACK;
	
	public MyDefaultAttLabelRenderer(Color defaultUnpickedColour, Color defaultPickedColour) {
		super(defaultPickedColour);
	}


}
