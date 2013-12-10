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
 * Grid.java:
 *   Description: The grid panel of the program, it saves the r,g,b info in a 
 *   2-dimensions array. Meanwhile, Grid.java implements the method of drawing
 *   various kinds of shapes.
 *   
 *   Bugs: Unknown.
 *   
 *   @author 
 */
package p4;

public class Grid {
  
  /**We define the maxHeight and maxWidth to a Full-HD
   * quality.
   */
  
  public static final int maxHeight=1080;
  public static final int maxWidth=1920;
  
  private int height,width;
  
  private Pixel[][] PixelMap=new Pixel[maxHeight][maxWidth];
  
 /**
  * Return the grid's height.
  * @return height
  */
  public int getGridHeight()
  {
	  return height;
  }
  
  /**
   * Return the grid's width.
   * @return width
   */
  public int getGridWidth()
  {
	  return width;
  }
  
  /**
   * To get the exact Pixel information of an
   * specific point PixelMap[i][j];
   * @param i the column of the pixel.
   * @param j the row    of the pixel.
   * @return
   */
  public String getPixInfo(int i,int j)
  {
	  //Return the R G B information.
	  return PixelMap[i][j].toString();
  }
  
  
  /**
   * To return the distance between two points.
   * @param ax Point A's x value.
   * @param ay Point A's y value.
   * @param bx Point B's x value.
   * @param by Point B's y value.
   * @return the distance between point A and point B.
   */
  private int dist(int ax, int ay, int bx, int by)
  {
	  return (int)(Math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by)));
  }
  
  /**
   * To initial the Pixel Map.
   */
  private void initPixelMap()
  {
	  for (int i=0;i<height;i++)
		  for (int j=0;j<width;j++)
			  PixelMap[i][j]=new Pixel();
	  
  }
  
  /**
   * To draw a rectangle.
   * @param r  color info.
   * @param g  color info.
   * @param b  color info.
   * @param pstx the left-top point's coordinate.
   * @param psty the left-top point's coordinate.
   * @param pwidth the relative percentage of the rectangle's width.
   * @param pheight the relative percentage of the rectangle's height.
   */
  private void drawRectange(int r,int g,int b,double pstx, double psty, double pwidth, double pheight)
  {
	 Pixel pix=new Pixel(r,g,b);
	 
	 //stx, sty is the coordinate of the left-top point 
	 //of the rectangle.
	 int stx=(int) (this.width*pstx)-1;
	 int sty=(int) (this.height*psty)-1;
	 
	 //width, height is the absolute size of the rectangle.
	 int width=(int) (this.width*pwidth);
	 int height=(int) (this.height*pheight);
	 
	 for (int i=stx;i<=stx+width;i++) 
		 for (int j=sty;j<=sty+height;j++)
			  try
		  { 
		    	  //Set the color for each pixel.
				  PixelMap[j][i]=pix;
		  }
		  catch (Exception e)
		  {
			  //Prevent out of bounds error.
		  }
  }
  
  /**
   * To draw a Ring.
   * @param r  color info.
   * @param g  color info.
   * @param b  color info.
   * @param pstx the center's coordinate.
   * @param psty the cneter's coordinate.
   * @param prout the outer radius of the ring.
   * @param prin  the inner radius of the ring.
   */
  private void drawRing(int r,int g,int b,double pstx, double psty, double prout, double prin)
  {
	  Pixel pix=new Pixel(r,g,b);
	 
	  //stx, sty is the center's coordinate.
	  int stx=(int) (this.width*pstx)-1;
	  int sty=(int) (this.height*psty)-1;
	  
	  //rout and rin are the absolutely radius of the outer circle
	  //and the inner circle.
	  int rout=(int) (this.width*prout);
	  int rin=(int) (this.width*prout);
	  
	   for (int i=stx-rout;i<=stx+rout;i++)
		  for (int j=sty-rout;j<=sty+rout;j++)
			  try
		  { 
		    	  //Set the color when the distance between the present point
				  // and the center is less than the outer radius and larger 
				  // than the inner radius.
				  if (dist(i,j,stx,sty)<=rout && dist(i,j,stx,sty)>=rin-1) PixelMap[j][i]=pix;
		  }
		  catch (Exception e)
		  {
			  //Prevent out of bounds error.
		  }
  }
  
  /**
   * To draw a Square.
   * @param r  color info.
   * @param g  color info.
   * @param b  color info.
   * @param pstx the left-top point's coordinate.
   * @param psty the left-top point's coordinate.
   * @param pl the relative length of the square.
   */
  
  private void drawSquare(int r,int g,int b,double pstx, double psty, double pl)
  {
	  Pixel pix=new Pixel(r,g,b);
	  
	  //stx, sty is the coordinate of the left-top point 
	  //of the square.
	  int stx=(int) (this.width*pstx)-1;
	  int sty=(int) (this.height*psty)-1;
	  
	  //width, height is the absolute size of the square(They should be equal).
	  int width=(int) (this.width*pl);
	  int height=(int) (this.width*pl);
	  
	  for (int i=stx;i<=stx+width;i++)
	    for (int j=sty;j<=sty+height;j++)
		  try
	  { 
			//Set the color for each pixel.
			PixelMap[j][i]=pix;
	  }
	  catch (Exception e)
	  {
		//Prevent out of bounds error.
	  }
  }
  
  /**
   * To draw a circle.
   * @param r  color info.
   * @param g  color info.
   * @param b  color info.
   * @param pstx the center's coordinate.
   * @param psty the cneter's coordinate.
   * @param pradius the relative radius of the circle.
   */
  private void drawCircle(int r,int g,int b,double pstx, double psty, double pradius)
  {
	  Pixel pix=new Pixel(r,g,b);
	  int stx=(int) (this.width*pstx)-1;
	  int sty=(int) (this.height*psty)-1;
	  int radius=(int) (this.width*pradius);
	   for (int i=stx-radius;i<=stx+radius;i++)
		  for (int j=sty-radius;j<=sty+radius;j++)
			  try
		  { 
				 //Set the color when the distance between the present point
				 // and the center is less than the radius.
				if (dist(i,j,stx,sty)<=radius) PixelMap[j][i]=pix;
		  }
		  catch (Exception e)
		  {
			//Prevent out of bounds error.
		  }
  }
  
  /**
   * The drawing method. It reads and draw the Shape record 
   * one by one.
   */
 public void draw()
  {
	for (int i=0;i<ShapeRec.shapeCount;i++)
		switch (ShapeRec.shapes[i].shapeType)
		{
		case(1):
		{
			//type 1: rectangle
			drawRectange(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
					     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
					     ShapeRec.shapes[i].arg1,ShapeRec.shapes[i].arg2);
			break;
		}
		
		case(2):
		{
			//type 2: square 
			drawSquare(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
				     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
				     ShapeRec.shapes[i].arg1);
			break;
		}
		
		case(3):
		{
			//type 3: circle
			drawCircle(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
				     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
				     ShapeRec.shapes[i].arg1);
			break;
		}
		
		case(4):
		{
		    //type 4: ring
			drawRing(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
				     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
				     ShapeRec.shapes[i].arg1,ShapeRec.shapes[i].arg2);
			break;
		}
		}
  }
  
  /**
   * The constructor of Grid.
   * @param w the width of the grid.
   * @param h the height of the grid.
   */
  public Grid(int w,int h)
  {
	  if (w>Grid.maxWidth || h>Grid.maxHeight)
	  {
		  System.out.println("Grid size out of range(maxWidth=1920; maxHeight=1080)");
		  System.out.println("Program Exited...");
		  System.exit(0);
	  }
	  this.width=w;
	  this.height=h;
	  initPixelMap();
  }
}
