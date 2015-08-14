package pmisc;

import java.awt.Color;

import edu.uci.ics.jung.visualization.renderers.DefaultVertexLabelRenderer;

public class MyDefaultVertexLabelRenderer extends DefaultVertexLabelRenderer {

	protected Color defaultunpickedcolour = Color.BLACK;
	
	public MyDefaultVertexLabelRenderer(Color defaultUnpickedColour, Color defaultPickedColour) {
		super(defaultPickedColour);
	}

}
