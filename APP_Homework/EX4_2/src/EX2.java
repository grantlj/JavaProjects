/*
   Author        :  LiuJiang
   Compile Date  :  2013/11/5
   Introduction  :
     Homework    :  Ex4.2
     Give out all the PERFECT NUMBER between 1 and 1000.
**/

public class EX2 {
  
	public static int getSum(int x)
  {
	  int s=0;
	  for (int i=1;i<Math.sqrt((double)x);i++)
	  {
		  if (x%i==0)
		  {
			  s+=i;
			  s+=(x/i);
		  }
	  }
	 // System.out.printf("%d\t%d\n",x,s);
	  return s/2;
  }
  public static void main(String[] args)
  {
	 int total=0;
	 for (int i=2;i<=1000;i++)
	 {
		 int sum=getSum(i);
		 if (sum==i)
		 {
			 total++;
			 System.out.print(i+" ");
			 if (total%5==0) System.out.println();
		 }
	 }
  }
}
