package model;

import java.util.LinkedHashSet;

import core.*;
import static org.junit.Assert.*;

public class TestSemanticsGraphs {


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Model m5 = new Model();
		MyVertex v51 = m5.addMyVertex();
		MyVertex v52 = m5.addMyVertex();
		MyVertex v53 = m5.addMyVertex();
		MyEdge e51 = m5.modelGraph.addMyEdge(v51, v52);
		MyEdge e52 = m5.modelGraph.addMyEdge(v52, v53);
		MyEdge e53 =m5.modelGraph.addMyEdge(v53, v51);
		System.out.println("The preferred labellings are: "+m5.labellingSetString(m5.preferredLabelling()));
		
		
//		Model m4 = new Model();
//		MyVertex v41 = m4.addMyVertex();
//		MyVertex v42 = m4.addMyVertex();
//		MyVertex v43 = m4.addMyVertex();
//		MyVertex v44 = m4.addMyVertex();
//		MyEdge e41 = m4.modelGraph.addMyEdge(v44, v41);
//		MyEdge e42 = m4.modelGraph.addMyEdge(v44, v43);
//		MyEdge e43 =m4.modelGraph.addMyEdge(v43, v42);
//		System.out.println("The admissible labellings are: "+m4.labellingSetString(m4.allAdmissibleLabelling2()));
//		
//		
//		Model m3 = new Model();
//		MyVertex v31 = m3.addMyVertex();
//		MyVertex v32 = m3.addMyVertex();
//		MyVertex v33=m3.addMyVertex();
//		MyVertex v34 = m3.addMyVertex();
//		MyVertex v35 = m3.addMyVertex();
//		MyVertex v36 = m3.addMyVertex();
//		MyEdge e31 = m3.modelGraph.addMyEdge(v31, v32);
//		MyEdge e32 = m3.modelGraph.addMyEdge(v31, v34);
//		MyEdge e33 = m3.modelGraph.addMyEdge(v34, v31);
//		MyEdge e34 = m3.modelGraph.addMyEdge(v35, v32);
//		MyEdge e35 = m3.modelGraph.addMyEdge(v32, v35);
//		MyEdge e36 = m3.modelGraph.addMyEdge(v36, v35);
//		MyEdge e37 = m3.modelGraph.addMyEdge(v36, v33);
//		System.out.println("Model 3's addmissible labellings are: "+m3.allAdmissibleLabelling2());
//		System.out.println("Model 3's preferred labellings are:" +m3.preferredLabelling());
//		
//		
//				
//		Model m2 = new Model();
//		MyVertex v1= m2.modelGraph.addMyVertex();
//		MyVertex v2= m2.modelGraph.addMyVertex();
//		MyVertex v3=  m2.modelGraph.addMyVertex();
//		MyVertex v4 = m2.modelGraph.addMyVertex();
//		MyVertex v5 = m2.modelGraph.addMyVertex();
//		MyEdge e1 = m2.modelGraph.addMyEdge(v1, v2);
//		MyEdge e2 = m2.modelGraph.addMyEdge(v2, v1);
//		MyEdge e3 = m2.modelGraph.addMyEdge(v2, v3);
//		MyEdge e4 = m2.modelGraph.addMyEdge(v3, v4);
//		MyEdge e5 = m2.modelGraph.addMyEdge(v4, v5);
//		MyEdge e6 = m2.modelGraph.addMyEdge(v5, v3);
//		System.out.println(m2.groundedLabelling().toString());
//		MyLabelling labelling = new MyLabelling(0);
//		labelling.setInVerties(new LinkedHashSet<MyVertex>(m2.modelGraph.getMyVertices()));
//		System.out.println("The addmissible labellings are: "+ m2.labellingSetString(m2.allAdmissibleLabelling2()));
//		System.out.println("The transistion sequence is " +m2.transitionSequence(labelling));
//		System.out.println("The preferred Labelling is: "+m2.preferredLabelling());
//		
//		
//		Model m1 = new Model();
//		MyVertex v6 =m1.modelGraph.addMyVertex();
//		MyVertex v7= m1.modelGraph.addMyVertex();
//		MyVertex v8=  m1.modelGraph.addMyVertex();
//		MyEdge e7 = m1.modelGraph.addMyEdge(v7, v6);
//		MyEdge e8 = m1.modelGraph.addMyEdge(v8, v7);
//		System.out.println(m1.groundedLabelling().toString());
//		v7.setLabel("IN");
//		System.out.print("is Legally in? "+ m1.isLegallyIn(v7)+"\n");
//		v7.setLabel("OUT");
//		v6.setLabel("OUT");
//		System.out.print("is Legally Out? "+ m1.isLegallyOut(v6)+"\n");
//		MyLabelling labelling1 = new  MyLabelling(1);
//		labelling1.setInVerties(new LinkedHashSet<MyVertex>(m1.modelGraph.getMyVertices()));
//		System.out.println("The transistion sequence is " +m1.transitionSequence(labelling1));
//		System.out.println("The preferred labelling is: "+m1.preferredLabelling());
//		
//		
//		
	}

}
