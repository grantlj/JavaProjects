import java.util.*;
public class TIJ_EX3_Main {
   static Scanner sc=new Scanner(System.in);
   static class TPoint{
	  double x,y;
	  TPoint(double a,double b)
	  {
		  this.x=a;
		  this.y=b;
		  //System.out.println(this.x+" "+this.y);
	  }
	  TPoint()
	  {
		  System.out.println("no arguments!");
	  }
	  void prt()
	  {
		  System.out.println(x+" "+y);
	  }
  }
	public static void main(String[] args)
  {
	  TPoint a=new TPoint(sc.nextDouble(),sc.nextDouble());
	  TPoint b=new TPoint(sc.nextDouble(),sc.nextDouble());
	  a=b;//this is still a pointer!!!!
	  b.x=-30;
	  a.prt();b.prt();
	  TPoint c=new TPoint();//Overload...
  }
}
