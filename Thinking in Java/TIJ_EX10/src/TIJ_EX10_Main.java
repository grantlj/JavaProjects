//import java.util.*;
public class TIJ_EX10_Main {
  public static void main(String[] args)
  {
	  int[][] a,b;
	  a=new int[5][5];
	  b=new int[5][5];
	 
	  for (int i=0;i<a.length;i++)
	  {
		  for (int j=0;j<a[i].length;j++)
		  {
			a[i][j]=(int)(Math.random()*90)+10;
		    System.out.print(a[i][j]+" ");
		  }
		  System.out.println();
	  }
	  b=a;
	  for (int i=0;i<b.length;i++)
		  for (int j=0;j<b[i].length;j++)
		    b[i][j]++;
	  System.out.println("Changed:");
	  for (int i=0;i<a.length;i++)
	  {
		  for (int j=0;j<a[i].length;j++)
			  System.out.print(a[i][j]+" ");
		  System.out.println();
	  }
  }
}
