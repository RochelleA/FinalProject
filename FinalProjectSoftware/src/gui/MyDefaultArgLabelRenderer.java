package gui;

import java.awt.Color;

import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;

public class MyDefaultArgLabelRenderer extends DefaultVertexLabelRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6088342925039407710L;
	protected Color defaultunpickedcolour = Color.BLACK;
	
	public MyDefaultArgLabelRenderer(Color defaultUnpickedColour, Color defaultPickedColour) {
		super(defaultPickedColour);
	}

}
