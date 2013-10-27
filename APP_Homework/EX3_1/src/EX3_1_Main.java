/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/27
   Introduction  :
     Homework    :  Ex3.1
     Review Sort methods:
                         1.Select Sort;
                         2.Bubble Sort;
                         3.Quick  Sort;
                         4.Insert Sort;
                         5.Shell  Sort;
*/

class Tnums
{
  public int counts=10;
  int[] value=new int[counts];
  
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
	  if (method=="") 
		  System.out.println("UnSorted:");
	  else
	    System.out.println("Sorted by "+method+":");
	  for (int i=0;i<counts;i++)
		  System.out.print(value[i]+" ");
	  System.out.println();
	  System.out.println();
  }
  
  public void selectSort()
  {
	  for (int i=0;i<counts-1;i++)
		  for (int j=i+1;j<counts;j++)
			  if (value[i]<value[j]) 
			  {
				  int t=value[i];value[i]=value[j];value[j]=t;
			  }
  }
  
  public void BubbleSort()
  {
	  for (int i=1;i<=counts;i++)
		  for (int j=0;j<counts-i-1;j++)
			  if (value[j]<value[j+1])
			  {
				  int t=value[j];value[j]=value[j+1];value[j+1]=t;
			  }
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
  
 public void insertSort(int delta)
  {
	for (int i=0;i<counts;i++)
	{
		int k=value[i];
		int j=i-delta;
		while (j>=0 && k>value[j])
		{
			value[j+delta]=value[j];j-=delta;
		}
		value[j+delta]=k;
	}
  }
 
 public void shellSort()
 {
	 int delta=counts/2;
	 do
	 {
		insertSort(delta);
	    delta/=2;
	 }
	 while (delta!=0);
		 
 }

 }

public class EX3_1_Main {
  public static void main(String[] args)
  {
	  Tnums nums=new Tnums();
	  
	  nums.generateVals();
	  nums.prtVals("");
	  nums.selectSort();
	  nums.prtVals("Select Sort");
	  
	  nums.generateVals();
	  nums.prtVals("");
	  nums.BubbleSort();
	  nums.prtVals("Bubble Sort");
	  
	  nums.generateVals();
	  nums.prtVals("");
	  nums.qSort(0,nums.counts-1);
	  nums.prtVals("Quick Sort");
	  
	  nums.generateVals();
	  nums.prtVals("");
	  nums.insertSort(1);
	  nums.prtVals("Insert Sort");
	  
	  nums.generateVals();
	  nums.prtVals("");
	  nums.shellSort();
	  nums.prtVals("Shell Sort");
	  
  }
  
}
