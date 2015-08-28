package model;

import java.text.DecimalFormat;

import core.*;

import org.junit.Test;

public class LabellingsTest {
	
	Model FE1 = new Model();
	Model FE2 = new Model();
	Model FE3 = new Model();
	 Model AE2 = new Model();
	
	
	public LabellingsTest(){
	
	MyArg FE11 =FE1.addMyArg();
	MyArg FE12 =FE1.addMyArg();
	MyArg FE13 = FE1.addMyArg();
	FE1.modelGraph.addMyAtt(FE11, FE12);
	FE1.modelGraph.addMyAtt(FE12, FE13);
	FE1.modelGraph.addMyAtt(FE13, FE11);
	
	

	MyArg FE21 = FE2.addMyArg();
	MyArg FE22 = FE2.addMyArg();
	MyArg FE23 = FE2.addMyArg();
	MyArg FE24 = FE2.addMyArg();
	FE2.modelGraph.addMyAtt(FE21, FE22);
	FE2.modelGraph.addMyAtt(FE22, FE21);
	FE2.modelGraph.addMyAtt(FE21, FE23);
	FE2.modelGraph.addMyAtt(FE22, FE23);
	FE2.modelGraph.addMyAtt(FE23, FE24);
	

	MyArg FE31 = FE3.addMyArg();
	MyArg FE32 = FE3.addMyArg();
	MyArg FE33 = FE3.addMyArg();
	MyArg FE34 = FE3.addMyArg();
	MyArg FE35 = FE3.addMyArg();
	MyArg FE36 = FE3.addMyArg();
	FE3.modelGraph.addMyAtt(FE31, FE32);
	FE3.modelGraph.addMyAtt(FE32, FE31);
	FE3.modelGraph.addMyAtt(FE32, FE33);
	FE3.modelGraph.addMyAtt(FE33, FE34);
	FE3.modelGraph.addMyAtt(FE34, FE34);
	FE3.modelGraph.addMyAtt(FE35, FE34);
	FE3.modelGraph.addMyAtt(FE35, FE36);
	
	MyArg v101= AE2.addMyArg();
	MyArg v102= AE2.addMyArg();
	MyArg v103= AE2.addMyArg();
	MyArg v104= AE2.addMyArg();
	MyArg v105= AE2.addMyArg();
	MyArg v106= AE2.addMyArg();
	AE2.modelGraph.addMyAtt(v101, v102);
	AE2.modelGraph.addMyAtt(v102, v103);
	AE2.modelGraph.addMyAtt(v103, v104);
	AE2.modelGraph.addMyAtt(v104, v105);
	AE2.modelGraph.addMyAtt(v105, v106);
	AE2.modelGraph.addMyAtt(v106, v104);
	
	}
	
	@Test
	public void FE1Grounded(){
		long startTime = System.nanoTime();
	FE1.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE1G "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void FE1Admissible(){
		long startTime = System.nanoTime();
	FE1.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE1A "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void FE1Preferred(){
		long startTime = System.nanoTime();
	FE1.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE1P "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void FE1Complete(){
		long startTime = System.nanoTime();
	FE1.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE1C "+new DecimalFormat("#.##########").format(seconds) + " ns"); 	}

	@Test
	public void FE2Grounded(){
		long startTime = System.nanoTime();
	FE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE2G "+new DecimalFormat("#.##########").format(seconds) + " ns"); }
	
	@Test
	public void FE2Admissible(){
		long startTime = System.nanoTime();
	FE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE2A "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	
	}
	
	@Test
	public void FE2Preferred(){
		long startTime = System.nanoTime();
	FE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE2P "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void FE2Complete(){
		long startTime = System.nanoTime();
	FE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE2C "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	@Test
	public void FE3Grounded(){
		long startTime = System.nanoTime();
	FE3.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE3G "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void FE3Admissible(){
		long startTime = System.nanoTime();
	FE3.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE3A "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	
	}
	
	@Test
	public void FE3Preferred(){
		long startTime = System.nanoTime();
	FE3.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE3P "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void FE3Complete(){
		long startTime = System.nanoTime();
	FE3.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("FE3C "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void AE2Grounded(){
		long startTime = System.nanoTime();
	AE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("AE2G "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void AE2Admissible(){
		long startTime = System.nanoTime();
	AE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("AE2A "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void AE2Preferred(){
		long startTime = System.nanoTime();
	AE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("AE2P "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
	
	@Test
	public void AE2Complete(){
		long startTime = System.nanoTime();
	AE2.allAdmissibleLabelling();
	long endTime = System.nanoTime();
	final double seconds = ((double)(endTime - startTime) / 1000000000);
	System.out.println("AE2P "+new DecimalFormat("#.##########").format(seconds) + " ns"); 
	}
}
