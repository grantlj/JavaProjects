package com.t7.perm;

public class Main {
  private static int[] dig=new int[10];
  private static boolean[] map=new boolean[10];
  
  private static void doPerm(int bit)
  {
	  
	  if (bit==11)
	  {
		  for (int i=9;i>=0;i--)
			  System.out.print(dig[i]);
		  System.out.println();
		  
	  }
	  else
	  {
	  
	  for (int i=0;i<=9;i++)
	  {
		 if (map[i]==false)
		 {
		  if (i%2==0)
		  {
		    if (i==0 && bit%2!=0)
		     {
			    dig[bit-1]=i;
			    map[i]=true;
			    doPerm(bit+1);
			    map[i]=false;
		     }
		    else
		    {
		    	dig[bit-1]=i;
		    	map[i]=true;
		    	doPerm(bit+1);
		    	map[i]=false;
		    }
		  }
		  
		  else if (bit%2!=1)
		  {
			  
			  dig[bit-1]=i;
			  map[i]=true;
			  doPerm(bit+1);
			  map[i]=false;
		  }
		 }   
			  
	  }
	  }
  }
  
  public static void main(String[] args)
  {
	  for (int i=0;i<10;i++)
		  map[i]=false;
	  doPerm(1);
  }
}
;