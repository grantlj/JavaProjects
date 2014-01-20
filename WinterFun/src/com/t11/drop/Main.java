package com.t11.drop;

public class Main {
  public static final int h=100;
  public static final int n=10;
  public static void main(String[] args)
  {
	  double s=h;
	  double[] f=new double[n+1];
	  f[0]=h;
	  for (int i=1;i<=n;i++)
	  {
		  f[i]=f[i-1]/2;
		  s+=(2*f[i]);
	  }
	  s-=(2*f[n]);
	  System.out.println("Total distance at "+n+" times:"+s);
	  System.out.println("Remaining height at "+n+ "times:"+f[n]);
	  
  }
}
