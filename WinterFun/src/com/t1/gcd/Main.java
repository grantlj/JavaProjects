package com.t1.gcd;

import java.util.Scanner;

public class Main {
  private static int getGcd(int a,int b)
  {
	 if (a<b) 
	 {
		 int t=a;a=b;b=t;
	 }
	 
	 if (b==0)
		 return a;
	 
	 if (a%2==0 && b%2==0)
		 return 2*getGcd(a>>1,b>>1);
	 
	 if (a%2==0 && b%2==1)
		 return getGcd(a>>1,b);
	 
	 if (a%2==1 && b%2==0)
		 return getGcd(a,b>>1);
	 
	 return getGcd((a-b)>>1,b);
		 
	 
  }
  
  public static void main(String[] args)
  {
	  int a=new Scanner(System.in).nextInt();
	  int b=new Scanner(System.in).nextInt();  
      int re=getGcd(a,b);
      
      System.out.println("GCD("+a+","+b+")="+re);
      System.out.println("LCM("+a+","+b+")="+a*b/re);
      
      
  }
}
