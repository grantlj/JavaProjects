/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/14
   Introduction  :
     Homework    :  Ex1.1
     Calculate the area of ANY N-SIDE POLYGONAL.
 */
import java.util.*;



public class Ex1_1_Main {
	static Scanner inp=new Scanner(System.in);
	
	public static class TPoint
	{
	  double x,y;
	  public  TPoint()
	  {
		  x=0;y=0;
	  }
	}
    public static double Dis(double ax,double ay,double bx,double by)
    {
    	return Math.sqrt((ax-bx)*(ax-bx)+(ay-by)*(ay-by));
    	
    }
    public static double Heron(double ax,double ay,double bx,double by,double cx,double cy)
    {
    	double l1,l2,l3,p;
    	l1=Dis(ax,ay,bx,by);
    	l2=Dis(ax,ay,cx,cy);
    	l3=Dis(bx,by,cx,cy);
    	p=(l1+l2+l3)/2;
    	return Math.sqrt(p*(p-l1)*(p-l2)*(p-l3));
    }
	public static void main(String[] args)
	{
		System.out.print("Enter the sides of POLYGONAL:");
		int n=inp.nextInt();
	    TPoint[] p=new TPoint[n+1];
		
		for (int i=1;i<=n;i++)
        {
	        p[i]=new TPoint();
			System.out.print("Enter NO."+i+" point's coordinate:");
            p[i].x=inp.nextDouble();
            p[i].y=inp.nextDouble();
            //System.out.println(p[i].x+" "+p[i].y);
        }
        double area=0;
        for (int i=2;i<=n-1;i++)
        	area=area+Heron(p[1].x,p[1].y,p[i].x,p[i].y,p[i+1].x,p[i+1].y);
        System.out.print("The area of this N-SIDE POLYGONAL is:");
        System.out.printf("%5.2f",area); 	
        	

	}

}
