package com.t2.rev;

import java.util.Scanner;

public class Main {
  private static void doRev(int x,int k)
  {
	   if (x==0)
	   {
		   System.out.println();
		   System.out.println("The count of bit is:"+k);
	   }
	   
	   else
	   {
		   System.out.print(x%10);
		   doRev(x/10,k+1);
	   }
  }
  
  public static void main(String[] args)
  {
	  int a=new Scanner(System.in).nextInt();
	  doRev(a,0);
  }
}
