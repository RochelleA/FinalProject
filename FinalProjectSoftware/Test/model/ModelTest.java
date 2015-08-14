package model;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import core.*;

import org.junit.Test;

public class ModelTest {


	@Test
	public void testAddArg(){
		Model m= new Model();
		MyArg v1=m.addMyArg();
		assertTrue(m.modelGraph.getMyArgs().contains(v1));

	}
	
	@Test 
	public void testDeleteArg(){
		Model m= new Model();
		MyArg v1=m.addMyArg();
		m.deleteArg(v1);
		assertTrue(!(m.modelGraph.getMyArgs().contains(v1)));	
	}
	
	@Test 
	public void testAddAtt(){
		Model m = new Model();
		MyArg v11 = m.addMyArg();
		
		MyArg v22 = m.addMyArg();
		m.addAtt(v11);
		assertEquals(v11,m.getV1());
		MyAtt e = m.addAtt(v22);
		assertTrue(m.modelGraph.getMyAtts().contains(e));
		assertEquals(null,m.getV2());
	}

	@Test
	public void testDeleteAtt(){
		Model m = new Model();
		MyArg v11 = m.addMyArg();
		
		MyArg v22 = m.addMyArg();
		m.addAtt(v11);
		assertEquals(v11,m.getV1());
		MyAtt e = m.addAtt(v22);
		m.deleteAtt(e);
		assertTrue(!(m.modelGraph.getMyAtts().contains(e)));
		
	}
	@Test 
	public void testResetGraph(){
		Model model = new Model();
		MyArg v1 =model.addMyArg();
		MyArg v2 = model.addMyArg();
		model.addAtt(v1);
		model.addAtt(v2);
		assertTrue(!(model.modelGraph.getMyArgs().isEmpty()));
		assertTrue(!(model.modelGraph.getMyAtts().isEmpty()));
		model.resetMyGraph();
		assertTrue(model.modelGraph.getMyArgs().isEmpty());
		assertTrue(model.modelGraph.getMyAtts().isEmpty());
		assertTrue(model.modelGraph.GetMyArgCount()==0 && model.modelGraph.GetMyAttCount()==0);
	}
	

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS	///SEMANTICS
	
	
	
//	@Test
//	public void testFindUnattackedArgsHashSet() {
//		Model m = new Model();
//		MyArg v1= m.modelGraph.addMyArg();
//		MyArg v2=m.modelGraph.addMyArg();
//		MyArg v3= m.modelGraph.addMyArg();
//		@SuppressWarnings("unused")
//		MyAtt e1 =m.modelGraph.addMyAtt(v1, v3);
//		ArrayList<MyArg> a1 =m.findUnattackedArgsArray();
//		assertTrue(a1.contains(v1));
//		assertTrue(a1.contains(v2));
//		assertFalse(a1.contains(v3));
//		HashSet<MyArg> a2 = m.findUnattackedArgsHashSet();
//		assertTrue(a2.contains(v1));
//		assertTrue(a2.contains(v2));
//		assertFalse(a2.contains(v3));
//	}
	
	
//	@Test
//	public void testFindL1() {
//		Model m = new Model();
//		MyArg v1= m.modelGraph.addMyArg();
//		MyArg v2=m.modelGraph.addMyArg();
//		MyArg v3= m.modelGraph.addMyArg();
//		MyLabelling L1= m.findL1();
//		HashSet<MyArg> HIn1 = L1.getInArgs();
//		System.out.println("The In Args are: "+ HIn1  +"The labelling is: "+L1.toString());
//		assertTrue(HIn1.contains(v1));
//		assertTrue(HIn1.contains(v2));
//		assertTrue(HIn1.contains(v3));
//		assertEquals(v3.getLabel(), "IN");
//		assertTrue(L1.getNotLabelledArgs().isEmpty());
//		@SuppressWarnings("unused")
//		MyAtt e1 =m.modelGraph.addMyAtt(v1, v3);
//		MyLabelling L2 = m.findL1();
//		HashSet<MyArg> HIn2 = L2.getInArgs();
//		System.out.println("The In Args are: " + HIn2+"The labelling is: "+ L2.toString()+ "The not labelled Args are: "+ L2.getNotLabelledArgs());
//		assertTrue(HIn2.contains(v1));
//		assertTrue(HIn2.contains(v2));
//		assertFalse(HIn2.contains(v3));
//		assertTrue(L2.getNotLabelledArgs().isEmpty());
//		assertTrue(L2.getOutArgs().contains(v3));
//		assertEquals(v3.getLabel(), "OUT");
//
//	}
	
	@SuppressWarnings("unused")
	@Test 
	public void testGroundedLabelling(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		MyAtt e1 =m.modelGraph.addMyAtt(v1, v2);
		MyAtt e2 = m.modelGraph.addMyAtt(v3, v2);
		MyAtt e3 = m.modelGraph.addMyAtt(v2, v4);
		MyAtt e4 = m.modelGraph.addMyAtt(v2, v5);
		MyLabelling l1 =m.groundedLabelling();
		System.out.println("not lablled Args" + l1.getNotLabelledArgs());
		assertTrue(l1.getNotLabelledArgs().isEmpty());
		assertTrue(l1.getInArgs().contains(v1));
		System.out.print(v1.toString()+v1.getLabel());
		assertTrue(l1.getInArgs().contains(v3));
		assertTrue(l1.getInArgs().contains(v4));
		assertTrue(l1.getInArgs().contains(v5));
		assertTrue(l1.getOutArgs().contains(v2));
		System.out.println("Grounded Labelling is: " + l1.toString()+ "\n Args not labelled anymore");


		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testGetGroundedLabelling(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		MyAtt e1 =m.modelGraph.addMyAtt(v1, v2);
		MyAtt e2 = m.modelGraph.addMyAtt(v3, v2);
		MyAtt e3 = m.modelGraph.addMyAtt(v2, v4);
		MyAtt e4 = m.modelGraph.addMyAtt(v2, v5);
		
		
		HashSet<MyArg> h1= m.getGroundedExtension();
		
		assertEquals(h1, m.groundedLabelling().getInArgs());
	}
	
	@Test
	public void testResetLabels(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v3, v2);
		m.modelGraph.addMyAtt(v2, v4);
		m.modelGraph.addMyAtt(v2, v5);
		m.groundedLabelling();
		assertTrue(v2.getLabel()=="OUT");
		assertTrue(v1.getLabel()=="IN");
		m.resetLabels();
		assertTrue(v2.getLabel()=="NONE");
		assertTrue(v1.getLabel()=="NONE"&&v3.getLabel()=="NONE"&&v4.getLabel()=="NONE"&&v5.getLabel()=="NONE");
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testIsIllegallyIn(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		MyAtt e1 =m.modelGraph.addMyAtt(v1, v2);
		MyAtt e2 = m.modelGraph.addMyAtt(v3, v2);
		MyAtt e3 = m.modelGraph.addMyAtt(v2, v4);
		MyAtt e4 = m.modelGraph.addMyAtt(v2, v5);
		m.groundedLabelling();
		System.out.println(""+v2.getLabel());
		v2.setLabel("IN");
		System.out.println(""+v2.getLabel());
		assertFalse(m.isLegallyIn(v2));
	}
	
	@SuppressWarnings("unused")
	@Test 
	public void testIsIllegallyOut(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		MyAtt e1 =m.modelGraph.addMyAtt(v1, v2);
		MyAtt e2 = m.modelGraph.addMyAtt(v3, v2);
		MyAtt e3 = m.modelGraph.addMyAtt(v2, v4);
		MyAtt e4 = m.modelGraph.addMyAtt(v2, v5);
		m.groundedLabelling();
		System.out.println(""+v3.getLabel());
		v3.setLabel("OUT");
		System.out.println(""+v3.getLabel());
		assertFalse(m.isLegallyOut(v3));
	}
	
	@SuppressWarnings("unused")
	@Test 
	public void testIsIlegallyUndec(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		MyAtt e1 =m.modelGraph.addMyAtt(v1, v2);
		MyAtt e2 = m.modelGraph.addMyAtt(v3, v2);
		MyAtt e3 = m.modelGraph.addMyAtt(v2, v4);
		MyAtt e4 = m.modelGraph.addMyAtt(v2, v5);
		m.groundedLabelling();
		System.out.println(""+v3.getLabel());
		v3.setLabel("UNDEC");
		System.out.println(""+v3.getLabel());
		assertFalse(m.isLegallyUndec(v3));
	}
	
	@Test
	public void testHasIllegallyInLabelling(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v3, v2);
		m.modelGraph.addMyAtt(v2, v4);
		m.modelGraph.addMyAtt(v2, v5);
		MyLabelling labelling =m.groundedLabelling();
		System.out.println("labelling is: "+labelling.toString()+ "V2's label is "+ v2.getLabel());
		labelling.deleteFromOutArgs(v2);
		System.out.println("labelling is: "+labelling.toString()+ "V2's label is "+ v2.getLabel());
		labelling.addInArg(v2);
		assertTrue(m.hasillegallyIn(labelling));
	}
	
	@Test
	public void testHasIllegallyInLinkedHashSet(){
		Model m = new Model();
		LinkedHashSet<MyLabelling> setOfLabellings = new LinkedHashSet<MyLabelling>();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v3, v2);
		m.modelGraph.addMyAtt(v2, v4);
		m.modelGraph.addMyAtt(v2, v5);
		MyLabelling labelling =m.groundedLabelling();
		MyLabelling labelling1 = m.groundedLabelling();
		setOfLabellings.add(labelling);
		setOfLabellings.add(labelling1);
		assertFalse(m.hasillegallyIn(setOfLabellings));
		labelling.deleteFromOutArgs(v2);
		labelling.addInArg(v2);
		assertTrue(m.hasillegallyIn(setOfLabellings));
	}
	
	@Test 
	public void testTransitionStep(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		MyArg v5= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v3, v2);
		m.modelGraph.addMyAtt(v2, v4);
		m.modelGraph.addMyAtt(v2, v5);
		MyLabelling labelling = new MyLabelling(0);
		LinkedHashSet<MyArg> tempInArgs = new LinkedHashSet<MyArg>();
		tempInArgs.addAll(m.modelGraph.getMyArgs());
		labelling.setInVerties(tempInArgs);
		m.transitionStep(labelling);
		assertTrue(labelling.getOutArgs().contains(v2));
	}
	
	@Test
	public void testAdmissibleLabelling(){
		Model m = new Model();
		MyArg v1 =m.modelGraph.addMyArg();
		MyArg v2= m.modelGraph.addMyArg();
		MyArg v3=  m.modelGraph.addMyArg();
		MyArg v4 = m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v4, v3);
		m.modelGraph.addMyAtt(v2, v1);
		m.modelGraph.addMyAtt(v3, v2);
		MyLabelling l1=m.admissibleLabelling();
		System.out.println("Here now" + l1.DisplayLabelling());
		HashSet<MyArg> inTemp = new HashSet<MyArg>(l1.getInArgs());
		Iterator<MyArg> inIterator = inTemp.iterator();
		while(inIterator.hasNext()){
			MyArg current = inIterator.next();
			assertTrue(m.isLegallyIn(current));
		}
		HashSet<MyArg> outTemp = new HashSet<MyArg>(l1.getOutArgs());
		Iterator<MyArg> outIterator = outTemp.iterator();
		while(outIterator.hasNext()){
			MyArg current = outIterator.next();
			assertTrue(m.isLegallyOut(current));
		}
		
	}
	
	@Test
	public void testReorderSet(){
		Model m = new Model();
		MyArg v1 =m.modelGraph.addMyArg();
		MyArg v2= m.modelGraph.addMyArg();
		MyArg v3=  m.modelGraph.addMyArg();
		MyArg v4 = m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v4, v3);
		m.modelGraph.addMyAtt(v2, v1);
		m.modelGraph.addMyAtt(v3, v2);
		LinkedHashSet<MyArg> Args =new LinkedHashSet<MyArg>(m.modelGraph.getMyArgs());
		MyArg[] array1 = new MyArg[Args.size()];
		Args.toArray(array1);
		LinkedHashSet<MyArg> ArgsReoder=m.reorderSet(Args);
		System.out.println("Args reordered"+ArgsReoder.toString()+ Args.size());
		MyArg[] array2= new MyArg[ArgsReoder.size()];
		ArgsReoder.toArray(array2);
		System.out.println("array 2"+array2.toString() );
		System.out.println("array1 element 1 "+ array1[0] + array1[1] + array1[2] + array1[3] );
		System.out.println("array2 element 3"+ array2[0] + array2[1] + array2[2] + array2[3]);
		assertEquals(array1[0], array2[3]);
	}
	
	@Test 
	public void testCheckAllLabels(){
		MyLabelling l1 = new MyLabelling(1);
		MyLabelling l2 = new MyLabelling(2);
		Model m = new Model();
		MyArg v1 = new MyArg(1);
		MyArg v2 = new MyArg(2);
		l1.addInArg(v1);
		l2.addInArg(v1);
		l1.addOutArg(v2);
		l2.addOutArg(v2);
		LinkedHashSet<MyLabelling> set = new LinkedHashSet<MyLabelling>();
		set.add(l2);
		set.add(l1);
		assertEquals(m.checkAllLabels(set), 2);
		l2.deleteFromInArgs(v1);
		l2.addOutArg(v1);
		v1.setLabel("IN");
		assertEquals(m.checkAllLabels(set), 1);
		v1.setLabel("OUT");
		assertEquals(m.checkAllLabels(set), 1);
		
		
		
	}
	
	@Test 
	public void testAllAdmissibleLabellings(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v2, v1);
		m.modelGraph.addMyAtt(v1, v3);
		m.modelGraph.addMyAtt(v2, v3);
		m.modelGraph.addMyAtt(v3, v4);
		System.out.println(m.allAdmissibleLabelling());
		System.out.println("all addmissible outcome " +m.allAdmissibleLabelling());
		System.out.println("Preferred Labellings are: " + m.preferredLabelling());
	}
	
	
	@Test 
	public void testAllAdmissibleLabellings2(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v2, v3);
		System.out.println(m.allAdmissibleLabelling());
		System.out.println("all addmissible outcome 2" +m.allAdmissibleLabelling());
		
	}
	
	@Test
	public void testDisplayAnAdmissiblelabelling(){
		Model m = new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v2, v3);

		System.out.println("all addmissible outcome " +m.allAdmissibleLabelling());
		m.labellingSetString(m.allAdmissibleLabelling());
		System.out.print("Arg 1 label is "+v1.getLabel()+ " \n Arg 2 label is "+ v2.getLabel()+" \n Arg 3's label is "+ v3.getLabel());
	}
	
	@Test
	public void testSuperIllegallyIn(){
		Model m = new Model();

		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v2, v3);
		MyLabelling labelling = new MyLabelling(0);
		labelling.addInArg(v1);
		labelling.addInArg(v2);
		labelling.addUndecArg(v3);
		assertTrue(m.superIllegallyIn(v2, labelling));
		
		Model m1 = new Model();
		MyArg v11= m1.modelGraph.addMyArg();
		MyArg v22=m1.modelGraph.addMyArg();
		MyArg v33= m1.modelGraph.addMyArg();
		m1.modelGraph.addMyAtt(v11, v22);
		m1.modelGraph.addMyAtt(v22, v33);
		MyLabelling labelling1 = new MyLabelling(1);
		labelling1.addUndecArg(v11);
		labelling1.addInArg(v22);
		labelling1.addOutArg(v33);
		assertTrue(m1.superIllegallyIn(v22, labelling1));
	}
	
	@Test 
	public void testFindSuperIllegallyIn(){
		Model m= new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4 = m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v2, v3);
		m.modelGraph.addMyAtt(v3, v4);
		MyLabelling labelling = new MyLabelling(1);
		labelling.addInArg(v2);
		labelling.addInArg(v4);
		labelling.addInArg(v1);
		labelling.addUndecArg(v3);
		LinkedHashSet<MyArg> superIllegallyIn= m.findSuperIllegallyIn(labelling);
		assertTrue(superIllegallyIn.contains(v2));
		assertTrue(superIllegallyIn.contains(v4));
		assertFalse(superIllegallyIn.contains(v1));
		Model m1 = new Model();
		MyArg v11 = m1.modelGraph.addMyArg();
		MyArg v22 = m1.modelGraph.addMyArg();
		MyArg v33 = m1.modelGraph.addMyArg();
		MyArg v44 = m1.modelGraph.addMyArg();
		MyArg v55 = m1.modelGraph.addMyArg();
		m1.modelGraph.addMyAtt(v11, v22);
		m1.modelGraph.addMyAtt(v22, v33);
		m1.modelGraph.addMyAtt(v33, v44);
		m1.modelGraph.addMyAtt(v44, v55);
		MyLabelling l1= new MyLabelling(0);
		l1.setInVerties(new LinkedHashSet<MyArg>(m1.modelGraph.getMyArgs()));
		LinkedHashSet<MyArg> set =m1.findSuperIllegallyIn(l1);
		assertTrue(set.contains(v22));
		assertFalse(set.contains(v33));
		assertFalse(set.contains(v44));
		assertFalse(set.contains(v55));
	}
	
	@Test
	public void testTransitionSequence(){
		Model m= new Model();
		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		MyArg v4 = m.modelGraph.addMyArg();
		MyArg v5 = m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v2, v3);
		m.modelGraph.addMyAtt(v3, v4);
		m.modelGraph.addMyAtt(v3, v2);
		m.modelGraph.addMyAtt(v5, v3);
//		Model m = new Model();
//		MyArg v1= m.modelGraph.addMyArg();
//		MyArg v2=m.modelGraph.addMyArg();
//		MyArg v3= m.modelGraph.addMyArg();
//		MyArg v4= m.modelGraph.addMyArg();
//		m.modelGraph.addMyAtt(v1, v2);
//		m.modelGraph.addMyAtt(v2, v1);
//		m.modelGraph.addMyAtt(v1, v3);
//		m.modelGraph.addMyAtt(v2, v3);
//		m.modelGraph.addMyAtt(v3, v4);
		MyLabelling labelling = new MyLabelling(0);
		labelling.addInArg(v1);
		labelling.addInArg(v2);
		labelling.addInArg(v3);
		labelling.addInArg(v4);
		labelling.addInArg(v5);
		MyLabelling labelling1 =m.transitionSequence(labelling);
		System.out.println("The Final Labelling after the transition sequence is "+labelling1);
		System.out.println("Preferred labellings are " + m.preferredLabelling());
		
	}
	
	@Test 
	public void testFindLabelling(){
		Model m = new Model();

		MyArg v1= m.modelGraph.addMyArg();
		MyArg v2=m.modelGraph.addMyArg();
		MyArg v3= m.modelGraph.addMyArg();
		m.modelGraph.addMyAtt(v1, v2);
		m.modelGraph.addMyAtt(v2, v3);
		LinkedHashSet<MyLabelling> candidateLabelling = new LinkedHashSet<MyLabelling>();
		MyLabelling labelling= new MyLabelling(0);
		labelling.setInVerties(new LinkedHashSet<MyArg>(m.modelGraph.getMyArgs()));
//		MyLabelling labelling = new MyLabelling(0, new LinkedHashSet<MyArg>(m.modelGraph.getMyArgs()), new LinkedHashSet<MyArg>(), new LinkedHashSet<MyArg>());
		System.out.println("The candidate labellings are: "+m.findLabelling(candidateLabelling, labelling));

	}
}
