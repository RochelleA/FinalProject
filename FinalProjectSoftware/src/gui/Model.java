/**
 * 
 */
package gui;

import view.DefaultGraph;
import core.*;
/**
 * @author Rochelle
 *
 */
public class Model {
private MyGraph modelGraph;
private MyGraph defaultGraph;


public MyGraph makeDefaultGraph(){
	defaultGraph.addMyVertex();
	defaultGraph.addMyVertex();
	return defaultGraph;
}

public MyGraph getGraphState(){
	return modelGraph;
}

public boolean addVertex(){
	System.out.println("Not yet implemented");
	return true;
}
}
