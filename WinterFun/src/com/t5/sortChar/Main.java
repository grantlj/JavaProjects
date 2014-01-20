package com.t5.sortChar;

public class Main {
  private static char[] qSort(char[] value,int l,int r)
	  {
	        int x=value[(l+r)/2];
	        int i=l;int j=r;
	        do
	        {
	                
	                while (value[i]>x) i++;
	                while (value[j]<x) j--;
	                if (i<=j)
	                {
	                         char t=value[i];value[i]=value[j];value[j]=t;
	                         i++;j--;
	                }
	                
	        }
	        while (i<j);
	        if (i<r) return qSort(value,i,r);
	        if (j>l) return qSort(value,l,j); 
	        return value;
	  } 
	
 public static void main(String[] args)
  {
	 char[] c=new char[10];
	 System.out.println("Unsorted:");
	 for (int i=0;i<10;i++)
	 {
	   c[i]=(char) ((Math.random()*26)+97);
	   System.out.print(c[i]+" ");
	 }
	 
	 System.out.println();
	 System.out.println("Sorted:");
	
	 c=qSort(c,0,9);
	 
	 for (int i=0;i<10;i++)
	    System.out.print(c[i]+" ");
	 
	 
  }
}
