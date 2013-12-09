package p4;

/*shapeType define:
 * 1:  rectangle;
 * 2:  square;
 * 3:  circle;
 * 4:  ring;
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
  
  public static void handleNewShape(int st, int r, int g, int b, double x, double y, double arg1, double arg2)
  {
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
  
  static
  {
	  for (int i=0;i<shapeMax;i++)
		  shapes[i]=new shapeInfo();
  }
  
}
