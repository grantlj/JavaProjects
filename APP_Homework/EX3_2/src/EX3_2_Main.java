/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/27
   Introduction  :
     Homework    :  Ex3.2
     NOTICE      :  THE DEFAULT SORT METHOD IS QSORT;
     Review Search methods:
                         1.ONE-BY-ONE Search;
                         2.Binary     Search;
*/
import java.util.*;
class Tnums
{
  public int counts=25;
  int[] value=new int[counts];
  Scanner sc=new Scanner(System.in);
  
  public Tnums()
  {
	  for (int i=0;i<counts;i++)
		  value[i]=0;
  }
  public void generateVals()
  {
	  for (int i=0;i<counts;i++)
		  value[i]=(int)(Math.random()*100);
  }
  
  public void prtVals(String method)
  {
	  System.out.println(method);
	  for (int i=0;i<counts;i++)
		  System.out.print(value[i]+" ");
	  System.out.println();
	  System.out.println();
  }
  
  public void qSort(int l,int r)
  {
	int x=value[(l+r)/2];
	int i=l;int j=r;
	do
	{
		
		while (value[i]>x) i++;
		while (value[j]<x) j--;
		if (i<=j)
		{
			 int t=value[i];value[i]=value[j];value[j]=t;
			 i++;j--;
		}
		
	}
	while (i<j);
	if (i<r) qSort(i,r);
	if (j>l) qSort(l,j);	
  }
  
  public void orderSearch()
  {
	System.out.print("Input the value you want to search:");
	int x=sc.nextInt();
	//System.out.println();
	for (int i=0;i<counts;i++)
		if (value[i]==x) 
		{
			System.out.println("Find value:"+x+" @ "+i+" by order search.");
			return;
		}
	System.out.println("value:"+x+" not found by order search.");
  }
  
  private int bSearch(int x,int l,int r)
  {
	  int p=(l+r)/2;
	  /*
	   For Debug....
	  System.out.println("l="+l+" r="+r+" p="+p);
	  */
	  if (l>=r && x!=value[p]) return -1;
	  if (x==value[p])
		  return p;
	    else
	      if (value[p]<x) return bSearch(x,l,p-1);
	                 else return bSearch(x,p+1,r);
	  
	  
  }
  public void binarySearch()
  {
	  System.out.print("Input the value you want to search:");
	  int x=sc.nextInt();
	  int pos=bSearch(x,0,counts-1);
	  if (pos==-1) 
			System.out.println("value:"+x+" not found by order search.");
	  else 
		  System.out.println("Find value:"+x+" @ "+pos+" by order search.");
  }
  
}

public class EX3_2_Main {
  public static void main(String[] args)
  {
	  Tnums nums=new Tnums();
	 
	  nums.generateVals();
	  nums.prtVals("Origin values:");
	  nums.qSort(0,nums.counts-1);
	  nums.prtVals("Sorted values:");
	  nums.orderSearch();
	 
	  nums.generateVals();
	  nums.prtVals("Origin values:");
	  nums.qSort(0,nums.counts-1);
	  nums.prtVals("Sorted values:");
	  nums.binarySearch();
	  
	  
	  
  }
}
