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
 * ShapeRec.java:
 *   Description: The ShapeRec.java is absolutely STATIC.Indeed, it is an array
 *   which the basic element of it is shapeInfo. It saves the shapes' infor-
 *   mation and arguments which have been loaded from the input file.
 *   
 *   shapeType definition:
 *   1:  rectangle;
 *   2:  square;
 *   3:  circle;
 *   4:  ring;
 *   
 *   Bugs: Unknown.
 *   
 *   @author 
 */

package p4;


/**
 * The basic element of shapes array.
 * r,g,b is the color info of the shape,
 * x_org, y_org, arg1 and arg2 are the 
 * arguments.
 * 
 * @author 
 *
 */
class shapeInfo{
	public int shapeType;
	public int r,g,b;
	public double x_org,y_org;
	public double arg1,arg2;
	
}


public class ShapeRec {
  public static final int shapeMax=100;
  public static int shapeCount=0;
  public static shapeInfo[] shapes=new shapeInfo[shapeMax];
  
  /**
   * handleNewShape is to store a new shape into the shapes' array.
   * @param st the shape type.
   * @param r  color info.
   * @param g  color info.
   * @param b  color info.
   * @param x  the local point's x coordinate.
   * @param y  the local point's y coordinate.
   * @param arg1 the extra arguments.
   * @param arg2 the extra arguments.
   */
  public static void handleNewShape(int st, int r, int g, int b, double x, double y, double arg1, double arg2)
  {
	   //We add a new shape to array if and ONLY IF the array is not full.
	  if (shapeCount+1<=shapeMax)
	  {
		  shapes[shapeCount].shapeType=st;   
		  shapes[shapeCount].r=r;shapes[shapeCount].g=g;shapes[shapeCount].b=b;
		  shapes[shapeCount].x_org=x;shapes[shapeCount].y_org=y;
		  shapes[shapeCount].arg1=arg1;
		  shapes[shapeCount].arg2=arg2;
		  shapeCount++;
	  }
  }
  
  /**
   * To instantiate the elements in shapes array. 
   */
  static
  {
	  for (int i=0;i<shapeMax;i++)
		  shapes[i]=new shapeInfo();
  }
  
}
