/**
 * 
 */
package core;



/**
 * @author Rochelle
 *
 */
public class MyVertex implements IMyVertex {
	//identifies the vertex
	int id;
	//this is either not set or {in,out,undec}
	String label;
	//this is the possible weight of the vertex. It is set at zero when first instantiated.
	int weight;
	
	/* (non-Javadoc)
	 * @see graph1.IMyVertex#MyVertex(int)
	 */
	
	
//	public MyVertex(int vertexId) {
	public MyVertex(int id){
		this.id=id;
		this.weight=0;
		this.label="no label";

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String toString() {
        return "V"+id;
	}
}