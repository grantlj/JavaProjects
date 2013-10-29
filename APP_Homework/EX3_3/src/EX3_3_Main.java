/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/29
   Introduction  :
     Homework    :  Ex3.3
     Practice the usage of two-dimension array. 
*/
public class EX3_3_Main {
  public static int[][] generatePoints()
  {
	  //Generate points by random.
	  int[][]a=new int[5][3];
	  for (int i=0;i<5;i++)
		  for (int j=0;j<3;j++)
			  a[i][j]=(int)(Math.random()*101);
	  return a;
  }
  public static void prtTable(int[][] a,int[] b)
  {
	  //Print out the Table. 
	  System.out.printf("NO.\tC\tJava\tC++\tTotal\n");
	  for (int i=0;i<5;i++)
		  System.out.printf("%d\t%d\t%d\t%d\t%d\n",i,a[i][0],a[i][1],a[i][2],b[i]);
  }
  
  public static void main(String[] args)
  {
	  int[][]Points=new int[5][3];
	  Points=generatePoints();
	  int[] Total=new int[5];
	 
	  for (int i=0;i<5;i++)
		  //Do sum.
		  Total[i]=Points[i][0]+Points[i][1]+Points[i][2];
	  
	  prtTable(Points,Total); 
  }
}
