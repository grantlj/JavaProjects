package com.t9.random;

import java.util.Random;

public class Main {
  public static void main(String[] args)
  {
	  int[] dig=new int[10];
	  boolean[] map=new boolean[10];
	  
	  for (int i=0;i<=9;i++)
	    map[i]=false;
	  
	  for (int i=0;i<=9;i++)
	  {
		  int x;
		  do
			  x=new Random().nextInt(10);
	      while (map[x]!=false);
		  map[x]=true;
		  dig[i]=x;
	  }
	  
	  for (int i=0;i<=9;i++)
		  System.out.print(dig[i]);
	  System.out.println();
  }
}
