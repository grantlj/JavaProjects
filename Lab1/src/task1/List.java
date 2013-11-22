package task1;

public class List<T>
{
	/* listMax: The max elements that the list can contain;
	 * element[]: The array to store data;
	 * count: The counter of elements that have been stored.
	 */
	public static int listMax=101;
	private Object[] element;
	private int count;
	
	/*
	 * clear(): Clear the list.
	 */
	public void clear()
	{
		count=0;
		for (int i=0;i<=listMax-1;i++)
			element[i]=0;
	}
	
	/*
	 * getCount(): Get the former count of list.
	 */
	public int getCount()
	{
		return count;
	}
	
	/* 
	 * add(val,index): Add element val at index. 
	 */
	public boolean add(Object val,int index)
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
	
	/*
	 * delete(index): Delete the element at index.
	 */
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
	
	/*
	 * get(index): Get the element at index.
	 */
	@SuppressWarnings("unchecked")
	public T get(int index)
	{
		if (index>=0 && index<=count-1)
		  return (T)element[index];
		else
		  return null;
	}
	
	public List()
	{
		element=new Object[listMax];
		clear();
	}
}



