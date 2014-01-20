package com.t6.insert;

import java.util.Scanner;

public class Main {
  private static final int argCount=5;
  public static void main(String[] args)
  {
	  int[] val=new int[argCount+1];
	  for (int i=0;i<argCount;i++)
		  val[i]=(int) ((Math.random()*200)+20);
	  
	  for (int i=0;i<argCount-1;i++)
		  for (int j=i+1;j<argCount;j++)
			  if (val[i]>val[j])
			  {
				  int t=val[i];val[i]=val[j];val[j]=t;
			  }
	 
	  System.out.println("Ready to Insert:");
	  
	  for (int i=0;i<argCount;i++)
		  System.out.print(val[i]+" ");
	  System.out.println();
	  System.out.print("Enter insert element:");
	  int x=new Scanner(System.in).nextInt();
	  int p=0;
	  while (x>val[p]) 
		  p++;
	  
	  for (int i=argCount;i>p;i--)
		  val[i]=val[i-1];
	  
	  val[p]=x;
	  
	  System.out.println("After insert:");
	  for (int i=0;i<argCount+1;i++)
		  System.out.print(val[i]+" ");
	  
  }
}
