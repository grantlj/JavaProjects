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
 * SVGReader.java:
 *   Description: The SVGReader.java is to loaded the SVG data from the specific
 *   input file, and then save it to the ShapeRec.
 *   
 *   Bugs: Unknown.
 *   
 *   @author 
 */

package p4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SVGReader {
  private String inFile;
  
  /**
   * load the shape information from inFile and transfer it
   * to the ShapeRec.java.
   */
  public void loadFromFile()
  {
	  File file=new File(inFile);
	  int lineCount=0;
	  try {
		Scanner sc=new Scanner(file);
		
		//Whenever not end of file, then read next line.
		while (sc.hasNextLine())
		{
			if (!sc.hasNext())
				//This line contains nothing(usually an enter pressed after
				//last line, so we break out the loop.
				break;
			
			lineCount++;
			
			String type;
			int shapeType;
			int r,g,b;
			double x_org,y_org;
			double arg_1=-32767,arg_2=-32767;
			type=sc.next();
			r=sc.nextInt();g=sc.nextInt();b=sc.nextInt();
			x_org=sc.nextDouble();y_org=sc.nextDouble();
			
			//tell shapes.
			if (type.equals("rectangle") || type.equals("ring"))
			{
				arg_1=sc.nextDouble();arg_2=sc.nextDouble();
				if (type.equals("rectangle")) shapeType=1;
				else shapeType=4;
			}
			else
			{
				arg_1=sc.nextDouble();
				if (type.equals("square"))
					shapeType=2;
				else if (type.equals("circle"))
					shapeType=3;
				else 
				{
					//not a rectangle, square, circle or ring. 
					//Give out a message that this line is invalid.
					System.out.println("Unknown shape description: LINE "+lineCount+" in "+inFile);
					continue;
				}
			}
			
			//Add new shape to shapeRec.
			ShapeRec.handleNewShape(shapeType, r, g, b, x_org,y_org, arg_1, arg_2);
		}
	
		sc.close();
		
	  } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  /**
   * Constructor.
   * @param s the inFile name.
   */
  
  SVGReader(String s)
  {
	  this.inFile=s;
  }
}
