package core;

import java.util.LinkedHashSet;

public class Preferred {
	
	LinkedHashSet<MyLabelling> candidateLabelling;
	MyLabelling labelling;

	public Preferred() {
		candidateLabelling = new LinkedHashSet<MyLabelling>();
		labelling = new MyLabelling(0);
	}

	public LinkedHashSet<MyLabelling> getCandidateLabelling() {
		return candidateLabelling;
	}

	public void setCandidateLabelling(LinkedHashSet<MyLabelling> candidateLabelling) {
		this.candidateLabelling = candidateLabelling;
	}

	public MyLabelling getLabelling() {
		return labelling;
	}

	public void setLabelling(MyLabelling labelling) {
		this.labelling = labelling;
	}

	
}
