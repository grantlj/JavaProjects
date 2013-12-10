/*///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            (program's title)
// Files:            (list of source files)
// Semester:         CS302 Fall 2013
//
// Author:           (your name)
// Email:            (your email address)
// CS Login:         (your login name)
// Lecturer's Name:  (name of your lecturer)
// Lab Section:      (your lab section number)
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          (list anyone who helped you write your program)
*/

/**
 * PortablePixelMapper.java:
 *   Description: The main class of PPM, it validations the arguments from the
 *   command line, then callback other class or method to implement the 
 *   methods of reading data from exact file, fill the grid panel, transferring
 *   and saving the gird information into a .ppm file.
 *   
 *   Bugs: Unknown.
 *   
 *   @author 
 */


package p4;

import java.io.File;
import java.io.FileNotFoundException;



public class PortablePixelMapper {
    private static String inFile,outFile;
    private static int width,height;
	
    
     /**
      * To check whether the arguments from the command line are valid
      * or not.
      * @param args The array that includes all the arguments.
      * @return true if all the arguments are valid.
      */
    @SuppressWarnings("unused")
	private static boolean checkArguments(String[] args)
    {
    	//The length of the arguments array must equal to 4.
    	if (args.length!=4) return false;                       
    	try
    	{
    		/**Try to convert the width and height to integer, 
    		 * if fails it means that the input is error.
    		 */
    		int t;
    		t=Integer.parseInt(args[1]);                       
    		t=Integer.parseInt(args[2]);
    	}
    	catch (Exception e)
    	{
    		return false;
    	}
    	
    	//Check input file's existence.
    	if (!new File(args[0]).exists())
    		return false;
    	
    	//Check whether the output file's name is valid.
    	if (args[3].toLowerCase().indexOf(".ppm")==-1)
    		return false;
    	
    	//All the arguments are correct, return true.
    	return true;
    	
    }
    
    /**
     * To show the usage message to console when the arguments have
     * been checked not valid.
     */
    private static void showUsageMessage()
    {
    	System.out.println("Usage:");
    	System.out.println("  java PortablePixelMapper <inFile> <width> <height> <outFile>");
    	System.out.println();
    	System.out.println();
    	System.out.println("  <inFile> is the name of an input file with svg shape information(MUST EXIST)");
    	System.out.println("  <width> is the result image width in pixels");
    	System.out.println("  <height> is the result image height in pixels");
    	System.out.println("  <outFile> is the name of the ppm file to create(MUST be *.ppm)");
        System.out.println("Example:");
    	System.out.println("  java PortablePixelMapper sample01.txt 100 100 image01.ppm");
    	System.out.println();
    }
    
    /**
     * The main entrance.
     * @param args the arguments entered from the command line by user.
     */
    public static void main(String[] args)
   {
	   
	   if (checkArguments(args))
	   {
	     inFile=args[0];
	     outFile=args[3];
	     width=Integer.parseInt(args[1]);
	     height=Integer.parseInt(args[2]);
	     
	     //Instantiate the Grid, Reader and Writer.
	     Grid myGrid=new Grid(width,height);
	     SVGReader mySVG=new SVGReader(inFile);
	     PPMWriter myPPM=new PPMWriter(outFile);
	  
	     //Load data from input file.
	     try
	     {
	       mySVG.loadFromFile();
	     } catch (Exception e)
	     {
	    	 System.out.println("Error: Fail to load data from file.");
	     }
	     
	     System.out.println("Load data from file: "+inFile+" Completed...");
	    
	     //Draw the grid.
	     myGrid.draw();
	     
	     System.out.println("Generate grid Completed...");
	     
	     //Save the grid information into the PPM file.
	     try {
		   myPPM.saveToFile(myGrid);
		   System.out.println("Save gird to file: "+outFile+" Completed...");
	      } catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
	    	e.printStackTrace();
	      }
	   }
	   else
		   showUsageMessage();
	   System.out.println("Prgoram Exited...");
	}
}
