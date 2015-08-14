/**
 * 
 */
package core;



/**
 * @author Rochelle
 *
 */
public class MyArg implements IMyArg {
	//identifies the argument
	int id;
	//this is either not set or {in,out,undec}
	String label;
	//this is the possible weight of the argument. It is set at zero when first instantiated.
	int weight;
	
	/* (non-Javadoc)
	 * @see graph1.IMyargument#Myargument(int)
	 */
	
	
//	public Myargument(int argumentId) {
	public MyArg(int id){
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
        return id+"";
	}
	
	@Override
	public boolean equals(Object argument2) {
	    if (argument2 == null) return false;
	    if (getClass() != argument2.getClass()) return false;
	    MyArg argument = (MyArg)argument2;
	    return (id == argument.id && label == argument.label && weight == argument.weight );
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