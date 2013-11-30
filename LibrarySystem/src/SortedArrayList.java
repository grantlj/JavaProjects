/*SortedArrayList.java
 * =========================================
 * SortedArrayList is the core to implement
 * the sort function of arraylist.
 */


import java.util.*;
@SuppressWarnings({ "rawtypes" })

class ComparableEle implements Comparable{
    String val;
	public int compareTo(Object arg0) {      //org>dest:1 else 0;
		String org=val;
		String dest=(new ComparableEle(arg0)).val;
		int minlen;
		
		if (org.length()<=dest.length())
			minlen=org.length();
		else
			minlen=dest.length();
		
		for (int i=0;i<minlen;i++)
			if (org.charAt(i)<dest.charAt(i)) 
				{ 
				   return 1;
				}
			else if (org.charAt(i)>dest.charAt(i))
			{
				return 0;
			}
		return 0;
	}
	
	public ComparableEle(Object c)
	{
		this.val=c.toString();
	}
	
	
}

public class SortedArrayList<E> extends ArrayList<E>{


	private static final long serialVersionUID = 1L;
	private int defaultSize=100;
	private ArrayList<E> s;
	private boolean isEmpty=true;
	

   
	public void insert(E element)
    {
    	if (isEmpty) 
    	{
    		s.add(0,element);
    		isEmpty=false;
    	}
    	else
    	{
		int p=0;
    	while (p<s.size() && new ComparableEle(element).compareTo(s.get(p))==0)
    		p++;
        s.add(p,element);
    	//System.out.println(element+" add at:"+(p));
    	}
    
    }
	
	
    public SortedArrayList(int size)
    {
    	defaultSize=size;
    	s=new ArrayList<E>(defaultSize);
    }
    
    public int size()
    {
    	return s.size();
    }
    
    public E get(int index)
    {
    	return s.get(index);
    }
}
