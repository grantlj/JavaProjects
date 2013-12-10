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
 * Pixel.java:
 *   Description: This class(Pixel.java) defines the data structure of a pixel,
 *   which includes the value of Red, Green, Blue values(range from 0 to 255).
 *   Each pixel is initialized as white(R:255, G:255, B:255).
 *   
 *   Bugs: Unknown.
 *   
 *   @author 
 */
package p4;

public class Pixel {
  public int r,g,b;
  
  /**
   * Constructor_1: To initialize a point as the color of white.
   */
  public Pixel()
  {
	  r=255;
	  g=255;
	  b=255;
  }
  
  /**
   * Consturct_2: To initialize a point as an exact color.
   * @param r  color info.
   * @param g  color info.
   * @param b  color info.
   */
  public Pixel(int r, int g, int b)
  {
	  this.r=r;this.g=g;this.b=b;
  }

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */

/**
 *  The toString(): To return the r,g,b info as a whole string.
 */
@Override
public String toString() {
	// TODO Auto-generated method stub
	return r+" "+g+" "+b;
}
  
  
}
