/**
 * 
 */
package gui;

import core.*;
/**
 * @author Rochelle
 *
 */
public class Model {
private MyGraph modelGraph;
private MyGraph defaultGraph;

public void makeDefaultGraph(){
	defaultGraph.addMyVertex();
	defaultGraph.addMyVertex();
}

public MyGraph getGraphState(){
	return modelGraph;
}

public boolean addVertex(){
	System.out.println("Not yet implemented");
	return true;
}
}
