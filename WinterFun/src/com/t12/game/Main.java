package com.t12.game;

public class Main {
  private static final int col=20;
  private static final int row=20;
  private static int[][] map=new int[row][col];
  private static int[][] map2=new int[row][col];
  private static int isAlive(int x,int y)
  {
	  int count=0;
	  for (int i=x-1;i<=x+1;i++)
		  for (int j=y-1;j<=x+1;j++)
		  {
			  if (i>=0 && i<row && j>=0 && j<col)
				  if (map[i][j]==1) count++;
		  }
	  
	 if (count==0) return 0;
	 if (count>4) return 0;
	 if (count==2) return 2;
	 if (count==3) return 1;
	 return 0;
  }
  
  public static void main(String[] args)
  {
	  System.out.println("Map Size if 30*30:(0 is dead,1 is alive)");
	  System.out.println();
	  System.out.println("Original map");
	  System.out.println("==============");
	  int s1=0,s2=0;
	 
	  for (int i=0;i<row;i++)
	  {
		  for (int j=0;j<col;j++)
		  {
			  map[i][j]=(int)(Math.random()*1000)%2;
	          System.out.print(map[i][j]);
	          if (map[i][j]==1) s1++;
		  }
		  System.out.println();
	  }
	 System.out.println("Alive/All:"+s1+"/"+row*col);
	 System.out.println();
	 
	 for (int i=0;i<row;i++)
	 {
		 for (int j=0;j<col;j++)
		 {
		   System.out.print(map[i][j]);
		   map2[i][j]=isAlive(i,j);
		   if (map2[i][j]==2) map2[i][j]=map[i][j];
		 }
		 System.out.println();
	 }
	 
	 System.out.println("Next map");
	 System.out.println("===============");
	
	 
	 for (int i=0;i<row;i++)
	  {
		  for (int j=0;j<col;j++)
		  {
			 System.out.print(map2[i][j]);
			 if (map2[i][j]==1) s2++;
		  }
		  System.out.println();
	  }
	  System.out.println("Alive/All:"+s2+"/"+row*col);
  }
}
