package task1;

public class List
{
	public static int listMax=101;
	private char[] element;
	private int count;
	
	public void clear()
	{
		count=0;
		for (int i=0;i<=listMax-1;i++)
			element[i]=0;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public boolean add(char val,int index)
	{
	
		if (count+1>=listMax || index>count+1) 
			return false;
		else
		{
			count++;
			for (int i=count;i>=index+1;i--)
				element[i]=element[i-1];
			element[index]=val;
			return true;
		}
	}
	
	public boolean delete(int index)
	{
		if (index>=0 && index<=count-1 && count>=1)
		{
			for (int i=index;i<=count-2;i++)
				element[i]=element[i+1];
			count--;
			return true;
		}
		else
			return false;
	}
	
	public char get(int index)
	{
		if (index>=0 && index<=count-1)
		  return element[index];
		else
		  return (char) -1;
	}
	
	public List()
	{
		element=new char[listMax];
		clear();
	}
}



