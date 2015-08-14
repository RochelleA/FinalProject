package pmisc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JComponent;

import edu.uci.ics.jung.visualization.renderers.DefaultEdgeLabelRenderer;

public class MyDefaultEdgeLabelRenderer extends DefaultEdgeLabelRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3841988462775172992L;
	protected Color unpickedColour = Color.BLACK;
	
	public MyDefaultEdgeLabelRenderer(Color defaultUnpickedColour, Color defaultPickedColour) {
		super(defaultPickedColour);
	}


}
