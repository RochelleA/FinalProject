package model;

import core.*;
import static org.junit.Assert.*;

public class TestSemanticsGraphs {


	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Model m2 = new Model();
		MyVertex v1= m2.modelGraph.addMyVertex();
		MyVertex v2= m2.modelGraph.addMyVertex();
		MyVertex v3=  m2.modelGraph.addMyVertex();
		MyVertex v4 = m2.modelGraph.addMyVertex();
		MyVertex v5 = m2.modelGraph.addMyVertex();
		MyEdge e1 = m2.modelGraph.addMyEdge(v1, v2);
		MyEdge e2 = m2.modelGraph.addMyEdge(v2, v1);
		MyEdge e3 = m2.modelGraph.addMyEdge(v2, v3);
		MyEdge e4 = m2.modelGraph.addMyEdge(v3, v4);
		MyEdge e5 = m2.modelGraph.addMyEdge(v4, v5);
		MyEdge e6 = m2.modelGraph.addMyEdge(v5, v3);
		System.out.println(m2.groundedLabelling().toString());
		
		Model m1 = new Model();
		MyVertex v6 =m1.modelGraph.addMyVertex();
		MyVertex v7= m1.modelGraph.addMyVertex();
		MyVertex v8=  m1.modelGraph.addMyVertex();
		MyEdge e7 = m1.modelGraph.addMyEdge(v7, v6);
		MyEdge e8 = m1.modelGraph.addMyEdge(v8, v7);
		System.out.println(m1.groundedLabelling().toString());
		v7.setLabel("IN");
		System.out.print("is Legally in? "+ m1.isLegallyIn(v7)+"\n");
		v7.setLabel("OUT");
		v6.setLabel("OUT");
		System.out.print("is Legally Out? "+ m1.isLegallyOut(v6)+"\n");
		
		
	}

}
