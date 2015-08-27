package model;


import core.*;

public class TestSemanticsGraphs {


	public static void main(String[] args) {
		
//		Model m5 = new Model();
//		MyArg v51 = m5.addMyArg();
//		MyArg v52 = m5.addMyArg();
//		MyArg v53 = m5.addMyArg();
//		MyAtt e51 = m5.modelGraph.addMyAtt(v51, v52);
//		MyAtt e52 = m5.modelGraph.addMyAtt(v52, v53);
//		MyAtt e53 =m5.modelGraph.addMyAtt(v53, v51);
//		System.out.println("All Admissible Labelling"+ m5.allAdmissible3());
//		
//		System.out.println("The preferred labellings are: "+m5.labellingSetString(m5.preferredLabelling()));
		
		
//		Model m4 = new Model();
//		MyArg v41 = m4.addMyArg();
//		MyArg v42 = m4.addMyArg();
//		MyArg v43 = m4.addMyArg();
//		MyArg v44 = m4.addMyArg();
//		MyAtt e41 = m4.modelGraph.addMyAtt(v44, v41);
//		MyAtt e42 = m4.modelGraph.addMyAtt(v44, v43);
//		MyAtt e43 =m4.modelGraph.addMyAtt(v43, v42);
//		System.out.println("All Admissible Labelling"+ m4.allAdmissible3());
//		System.out.println("The admissible labellings are: "+m4.labellingSetString(m4.allAdmissibleLabelling2()));
//		
//		
//		Model m3 = new Model();
//		MyArg v31 = m3.addMyArg();
//		MyArg v32 = m3.addMyArg();
//		MyArg v33=m3.addMyArg();
//		MyArg v34 = m3.addMyArg();
//		MyArg v35 = m3.addMyArg();
//		MyArg v36 = m3.addMyArg();
//		MyAtt e31 = m3.modelGraph.addMyAtt(v31, v32);
//		MyAtt e32 = m3.modelGraph.addMyAtt(v31, v34);
//		MyAtt e33 = m3.modelGraph.addMyAtt(v34, v31);
//		MyAtt e34 = m3.modelGraph.addMyAtt(v35, v32);
//		MyAtt e35 = m3.modelGraph.addMyAtt(v32, v35);
//		MyAtt e36 = m3.modelGraph.addMyAtt(v36, v35);
//		MyAtt e37 = m3.modelGraph.addMyAtt(v36, v33);
//		System.out.println("All Admissible Labelling"+ m5.allAdmissible3());
//		System.out.println("Model 3's admissible labellings are: "+m3.allAdmissibleLabelling2());
//		System.out.println("Model 3's preferred labellings are:" +m3.preferredLabelling());
//		
//		
//				
//		Model m2 = new Model();
//		MyArg v1= m2.modelGraph.addMyArg();
//		MyArg v2= m2.modelGraph.addMyArg();
//		MyArg v3=  m2.modelGraph.addMyArg();
//		MyArg v4 = m2.modelGraph.addMyArg();
//		MyArg v5 = m2.modelGraph.addMyArg();
//		MyAtt e1 = m2.modelGraph.addMyAtt(v1, v2);
//		MyAtt e2 = m2.modelGraph.addMyAtt(v2, v1);
//		MyAtt e3 = m2.modelGraph.addMyAtt(v2, v3);
//		MyAtt e4 = m2.modelGraph.addMyAtt(v3, v4);
//		MyAtt e5 = m2.modelGraph.addMyAtt(v4, v5);
//		MyAtt e6 = m2.modelGraph.addMyAtt(v5, v3);
//		System.out.println(m2.groundedLabelling().toString());
//		MyLabelling labelling = new MyLabelling(0);
//		labelling.setInVerties(new LinkedHashSet<MyArg>(m2.modelGraph.getMyVertices()));
//		System.out.println("The admissible labellings are: "+ m2.labellingSetString(m2.allAdmissibleLabelling2()));
//		System.out.println("The transition sequence is " +m2.transitionSequence(labelling));
//		System.out.println("The preferred Labelling is: "+m2.preferredLabelling());
//		
//		
//		Model m1 = new Model();
//		MyArg v6 =m1.modelGraph.addMyArg();
//		MyArg v7= m1.modelGraph.addMyArg();
//		MyArg v8=  m1.modelGraph.addMyArg();
//		MyAtt e7 = m1.modelGraph.addMyAtt(v7, v6);
//		MyAtt e8 = m1.modelGraph.addMyAtt(v8, v7);
//		System.out.println(m1.groundedLabelling().toString());
//		v7.setLabel("IN");
//		System.out.print("is Legally in? "+ m1.isLegallyIn(v7)+"\n");
//		v7.setLabel("OUT");
//		v6.setLabel("OUT");
//		System.out.print("is Legally Out? "+ m1.isLegallyOut(v6)+"\n");
//		MyLabelling labelling1 = new  MyLabelling(1);
//		labelling1.setInVerties(new LinkedHashSet<MyArg>(m1.modelGraph.getMyVertices()));
//		System.out.println("The transistion sequence is " +m1.transitionSequence(labelling1));
//		System.out.println("The preferred labelling is: "+m1.preferredLabelling());
//		
//		
//		
		Model m7= new Model();
		MyArg v71=m7.addMyArg();
		MyArg v72 =m7.addMyArg();
		MyArg v73 =m7.addMyArg();
		MyArg v74 =m7.addMyArg();
		m7.modelGraph.addMyAtt(v71, v72);
		m7.modelGraph.addMyAtt(v72, v73);
		m7.modelGraph.addMyAtt(v73, v72);
		m7.modelGraph.addMyAtt(v73, v74);
		System.out.println("An Admissible labelling is: " + m7.admissibleLabelling());
		System.out.println("An Admissible labelling is: " + m7.admissibleLabelling1());
	
		Model m6 = new Model();
		MyArg v61= m6.addMyArg();
		MyArg v62 = m6.addMyArg();
		MyArg v63 = m6.addMyArg();
		MyArg v64= m6.addMyArg();
		m6.modelGraph.addMyAtt(v61, v62);
		m6.modelGraph.addMyAtt(v62, v61);
		m6.modelGraph.addMyAtt(v62, v63);
		m6.modelGraph.addMyAtt(v61, v63);
		m6.modelGraph.addMyAtt(v63, v64);
		System.out.println("All Admissible Labelling"+ m6.allAdmissibleLabelling());
		System.out.println("All Complete Labellings"+m6.completeLabellings());
		System.out.println("Preferred labellings are: "+ m6.preferredLabelling());
//		System.out.println("All Admissible Labelling"+ m6.allAdmissibleLabellings3());
		
		Model m8= new Model();
		MyArg v81 = m8.addMyArg();
		MyArg v82 = m8.addMyArg();
		MyArg v83 = m8.addMyArg();
		MyArg v84 = m8.addMyArg();
		MyArg v85 = m8.addMyArg();
		m8.modelGraph.addMyAtt(v81, v83);
		m8.modelGraph.addMyAtt(v84, v81);
		m8.modelGraph.addMyAtt(v85, v81);
		m8.modelGraph.addMyAtt(v83, v82);
		m8.modelGraph.addMyAtt(v82, v85);
		m8.modelGraph.addMyAtt(v85, v84);
		System.out.println("All Admissible labellings are:" + m8.allAdmissibleLabelling());
		System.out.println("All Complete Labellings"+m8.completeLabellings());
		System.out.println("Preferred labellings are: "+ m8.preferredLabelling());
		
		Model m9 = new Model();
		MyArg v91 = m9.addMyArg();
		MyArg v92 = m9.addMyArg();
		MyArg v93 = m9.addMyArg();
		m9.modelGraph.addMyAtt(v91, v92);
		m9.modelGraph.addMyAtt(v92, v93);
		m9.modelGraph.addMyAtt(v93, v91);
		System.out.println("All Admissible labellings are:" + m9.allAdmissibleLabelling());
		System.out.println("All Complete Labellings"+m9.completeLabellings());
		System.out.println("Preferred labellings are: "+ m9.preferredLabelling());
		
	}
	

}
