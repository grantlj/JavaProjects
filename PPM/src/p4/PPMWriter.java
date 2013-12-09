package p4;

import java.io.*;

public class PPMWriter {
	 private String outFile;
	 private int w,h;
	 private PrintStream print;
	 
	 private void writePPMTitle() throws FileNotFoundException
	 {
		 
		 print.println("P3");
		 print.println(w+" "+h);
	
		 print.println("255");
	 }
	 
	 private void writePPMPixel(Grid g)
	 {
		 for (int i=0;i<h;i++)
			 for (int j=0;j<w;j++)
			    print.println(g.getPixInfo(i,j));
				 
	 }
	 public void saveToFile(Grid g) throws FileNotFoundException
	 {
		 print=new PrintStream(new FileOutputStream(new File(outFile)));
		 w=g.getGridWidth();
		 h=g.getGridHeight();
		 try {
			writePPMTitle();
			writePPMPixel(g);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 print.close();
	 }
	 
	 public PPMWriter(String s)
	 {
		 this.outFile=s;
	 }
}
