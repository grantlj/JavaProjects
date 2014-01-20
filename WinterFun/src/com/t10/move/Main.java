package com.t10.move;

import java.util.Scanner;

public class Main {
  private static char[] c=new char[100];
  private static int len;
  private static int headp=0;
  
  
  private static void moveToHead(int pos)
  {
	  char t=c[pos];
	  for (int i=len;i>=headp+1;i--)
		  c[i]=c[i-1];
	  System.out.println("headp is"+headp);
	  c[headp]=t;
	  for (int i=pos+1;i<len;i++)
		  c[i]=c[i+1];
	  headp++;
 }
  
  private static void moveToTail(int pos)
  {
	 char t=c[pos];
	 c[len]=t;
	 for (int i=pos;i<len;i++)
		  c[i]=c[i+1];
  }
  
  private static boolean doCheckUpper(char[] c)
  {
	  int p1=0,p2=len-1;
	  while ((p1<len) && (!(c[p1]>='A' && c[p1]<='Z'))) p1++;
	  while ((p2>-1) && (c[p2]>='A' && c[p2]<='Z')) p2--;
	  p2++;
	  return (p1==p2);
	  
  }
  
  private static boolean doCheckLower(char[] c)
  {
	  int p1=0,p2=len-1;
	  while ((p1<len) && (!(c[p1]>='a' && c[p1]<='z'))) p1++;
	  while ((p2>-1) &&  (!(c[p2]>='a' && c[p2]<='z'))) p2--;
	  while ((p2>-1) && (c[p2]>='a' && c[p2]<='z'))p2--;
	  p2++;
	  return (p1==p2);
  }
  public static void main(String[] args)
  {
	  String str=new Scanner(System.in).nextLine();
	  c=(str+" ").toCharArray();
      len=c.length-1;
    
      
      for (int i=0;i<len;i++)
      {
        if (c[i]>='a' && c[i]<='z')
          {
        	moveToHead(i);
        	i--;
          }
        if (doCheckLower(c)) break;
      }
          
      
      for (int i=0;i<len;i++)
      {
    	if (c[i]>='A' && c[i]<='Z')
    	{ 
    		moveToTail(i);
    		i--;
    	 }
    	if (doCheckUpper(c)) break;
      }
     
      
      for (int i=0;i<len;i++)
    	  System.out.print(c[i]);
 
  }
}
