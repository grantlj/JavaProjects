/*
 * ///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (name of main application class)
// File:             (name of this class's file)
// Semester:         CS302 Fall 2013
//
// Author:           (your name and email address)
// CS Login:         (your login name)
// Lecturer's Name:  (name of your lecturer)
// Lab Section:      (your lab section number)
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     (name of your pair programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
//////////////////////////// 80 columns wide //////////////////////////////////
 */

/**
 * PPMWriter.java:
 *   Description: The PPMWriter.java is to generate the destination .ppm file. It
 *   get each pixel information from Grid.java, and then write it to the output file.
 *   
 *   Bugs: Unknown.
 *   
 *   @author 
 */
package p4;

import java.io.*;

public class PPMWriter {
	 private String outFile;
	 private int w,h;
	 private PrintStream print;
	 
	 
	 /**
      * write the title of the ppm file.
      * Usually,it is:
      * ===============
      * P3            =
      * width height  =
      * 255           =
      * ===============
      * 
	  * @throws FileNotFoundException
	  */
	 private void writePPMTitle() throws FileNotFoundException
	 {
		 
		 print.println("P3");
		 print.println(w+" "+h);
	
		 print.println("255");
	 }
	 
	 /**
	  * write the grid information from the grid to PPM file,
	  * pixel by pixel...
	  * @throws FileNotFoundException
	  */
	 private void writePPMPixel(Grid g)
	 {
		 for (int i=0;i<h;i++)
			 for (int j=0;j<w;j++)
			    print.println(g.getPixInfo(i,j));
				 
	 }
	 
	 /**
	  * The main method of PPMWriter.
	  * @param g is a handle which pointer to the present grid.
	  * @throws FileNotFoundException
	  */
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
	 
	 /**
	  * Consturctor.
	  * @param s The destination file name.
	  */
	 
	 public PPMWriter(String s)
	 {
		 this.outFile=s;
	 }
}
