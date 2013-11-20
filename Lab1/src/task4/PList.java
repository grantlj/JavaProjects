package task4;

public class PList {
  public final static int PioMax=5;  //0,1,2,3,4
  public final static int EleMax=11; 
  private char element[][];
  private int  elementCount[];
		  private char defaultChar=127;
  private int count;
 
  public void clear()
  {
	  for (int i=0;i<PioMax;i++)
	  {
		  for (int j=0;j<EleMax;j++)
			  element[i][j]=defaultChar;
	      elementCount[i]=0; 
	  }
	  count=0;
  }
  
  public int getCount()
  {
	  return count;
  }
  
  public int getCountAt(int pio)
  {
	  return elementCount[pio];
  }
  
  public int getHigh()
  {
	  for (int i=0;i<PioMax;i++)
		  if (elementCount[i]!=0) 
			  return i;
	  return 0;
  }
  
  public void add(int pio,char c)
  {
	  if (pio<0)
		  pio=0;
	  else if (pio>4)
		  pio=4;
	  
	  if (elementCount[pio]+1>EleMax)
		  return;
	  
	 if (elementCount[pio]==0)
	 {
		 element[pio][0]=c;
		 elementCount[pio]++;
		 count++;
	     return;
	 }
	 
	  int p=0;
	  while (c>=element[pio][p])
		  p++;
	
	  for (int i=elementCount[pio];i>=p+1;i--)
		  element[pio][i]=element[pio][i-1];
	  element[pio][p]=c;
	 
	  count++;
	  elementCount[pio]++;
  }
  
  public char get(int pio,int i)
  {
	 if (pio<0 || pio>4 || elementCount[pio]<1)
		 return (char) -1;
	  return element[pio][i];
  }
  
  public void delete(int pio)
  {
	 if (elementCount[pio]<=0) 
		  return;
	  elementCount[pio]--;
	  count--;
	  element[pio][elementCount[pio]]=0;
  }
  
  public PList()
  {
	  element=new char[PioMax][EleMax];
	  elementCount=new int[PioMax];
	  clear();
  }
  
}

