package com.t8.joseph;

import java.util.Scanner;

public class Main {
  public static void doCalcByFormula(int n)
  {
	 System.out.println();
	 System.out.println("Calc by formula");
	 System.out.println("==================");
	 
	 int[] ans=new int[n+1];
	 ans[1]=0;
	 for (int i=2;i<=n;i++)
		 ans[i]=(ans[i-1]+3)%i;
	 System.out.println("Survivor:"+(ans[n]+1));
  }
  
  public static void main(String[] args)
  {
	  int n=new Scanner(System.in).nextInt();
	  System.out.println("Calc by simulate");
	  System.out.println("==================");
	  
	 
	  boolean[] killed=new boolean[n+1];
	 
	  for (int i=1;i<=n;i++)
		  killed[i]=false;
	  
	  int killCount=0;
	  int p=0;
	  int num=0;
	  
	  do
	  {
		  do
			  p=p%n+1;
		  while (killed[p]!=false);
		  num++;
		  if (num%3==0)
		  {
			  killCount++;
			  killed[p]=true;
			  System.out.print(p+" ");
		  }
	  }
	  while (killCount<n-1);
	  
	  System.out.println();
	  for (int i=1;i<=n;i++)
		  if (killed[i]==false) System.out.println("Survivor:"+i);
	  
	  doCalcByFormula(n);
  }
}
