package p4;

public class Grid {
  
  public static final int maxHeight=1024;
  public static final int maxWidth=1024;
  
  private int height,width;
  
  private Pixel[][] PixelMap=new Pixel[maxHeight][maxWidth];
  
  public int getGridHeight()
  {
	  return height;
  }
  
  public int getGridWidth()
  {
	  return width;
  }
  
  public String getPixInfo(int i,int j)
  {
	 return PixelMap[i][j].toString();
  }
  
  
  private int dist(int ax, int ay, int bx, int by)
  {
	  return (int)(Math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by)));
  }
  private void initPixelMap()
  {
	  for (int i=0;i<height;i++)
		  for (int j=0;j<width;j++)
			  PixelMap[i][j]=new Pixel();
	  
  }
  
  private void drawRectange(int r,int g,int b,double pstx, double psty, double pwidth, double pheight)
  {
	 Pixel pix=new Pixel(r,g,b);
	 int stx=(int) (this.width*pstx)-1;
	 int sty=(int) (this.height*psty)-1;
	 int width=(int) (this.width*pwidth);
	 int height=(int) (this.height*pheight);
	 for (int i=stx;i<=stx+width;i++)
		 for (int j=sty;j<=sty+height;j++)
			  try
		  { 
		    	PixelMap[j][i]=pix;
		  }
		  catch (Exception e)
		  {
			  
		  }
  }
  
  
  private void drawRing(int r,int g,int b,double pstx, double psty, double prout, double prin)
  {
	  Pixel pix=new Pixel(r,g,b);
	  int stx=(int) (this.width*pstx)-1;
	  int sty=(int) (this.height*psty)-1;
	  int rout=(int) (this.width*prout);
	  int rin=(int) (this.width*prout);
	  
	   for (int i=stx-rout;i<=stx+rout;i++)
		  for (int j=sty-rout;j<=sty+rout;j++)
			  try
		  { 
		    	if (dist(i,j,stx,sty)<=rout && dist(i,j,stx,sty)>=rin-1) PixelMap[j][i]=pix;
		  }
		  catch (Exception e)
		  {
			  
		  }
  }
  
  
  private void drawSquare(int r,int g,int b,double pstx, double psty, double pl)
  {
	  Pixel pix=new Pixel(r,g,b);
	  
	  int stx=(int) (this.width*pstx)-1;
	  int sty=(int) (this.height*psty)-1;
	  
	  int width=(int) (this.width*pl);
	  int height=(int) (this.width*pl);
	  
	  for (int i=stx;i<=stx+width;i++)
	    for (int j=sty;j<=sty+height;j++)
		  try
	  { 
	    	PixelMap[j][i]=pix;
	  }
	  catch (Exception e)
	  {
		  
	  }
  }
  
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
		    	if (dist(i,j,stx,sty)<=radius) PixelMap[j][i]=pix;
		  }
		  catch (Exception e)
		  {
			  
		  }
  }
  
  public void draw()
  {
	for (int i=0;i<ShapeRec.shapeCount;i++)
		switch (ShapeRec.shapes[i].shapeType)
		{
		case(1):
		{
			drawRectange(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
					     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
					     ShapeRec.shapes[i].arg1,ShapeRec.shapes[i].arg2);
			break;
		}
		
		case(2):
		{
			drawSquare(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
				     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
				     ShapeRec.shapes[i].arg1);
			break;
		}
		
		case(3):
		{
			drawCircle(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
				     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
				     ShapeRec.shapes[i].arg1);
			break;
		}
		
		case(4):
		{
			drawRing(ShapeRec.shapes[i].r,ShapeRec.shapes[i].g,ShapeRec.shapes[i].b,
				     ShapeRec.shapes[i].x_org,ShapeRec.shapes[i].y_org,
				     ShapeRec.shapes[i].arg1,ShapeRec.shapes[i].arg2);
			break;
		}
		}
  }
  
  
  public Grid(int w,int h)
  {
	  this.width=w;
	  this.height=h;
	  initPixelMap();
  }
}
