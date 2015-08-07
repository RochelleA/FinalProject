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
		this.label="NONE";

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
	
	@Override
	public boolean equals(Object vertex2) {
	    if (vertex2 == null) return false;
	    if (getClass() != vertex2.getClass()) return false;
	    MyVertex vertex = (MyVertex)vertex2;
	    return (id == vertex.id && label == vertex.label && weight == vertex.weight );
	  }
	
	public boolean isIn(){
		if(this.getLabel()=="IN"){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isOut(){
		if (this.getLabel()=="OUT"){
			return true;
			}
		else{
			return false;
		}
	}
	
	public boolean isUndec(){
		if(this.getLabel()=="UNDEC"){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean hasNoLabel(){
		if(this.getLabel()=="NONE"){
			return true;
		}
		else{
			return false;
		}
	}
}