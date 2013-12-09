package p4;

public class Pixel {
  public int r,g,b;
  public Pixel()
  {
	  r=255;
	  g=255;
	  b=255;
  }
  
  public Pixel(int r, int g, int b)
  {
	  this.r=r;this.g=g;this.b=b;
  }

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	// TODO Auto-generated method stub
	return r+" "+g+" "+b;
}
  
  
}
