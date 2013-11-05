/*
   Author        :  LiuJiang
   Compile Date  :  2013/11/5
   Introduction  :
     Homework    :  Ex4.1
     Exercises for the usage of class, array, equals() function.
**/
import java.util.*;
class ArrayMem
{
	public int val;
	public final int MaxCounts=10;
	
	ArrayMem(int x)
	{
		val=x;
	}
	ArrayMem()
	{
		
	}
	
	//Rewrite the method for compare VALUE.
	public boolean equals(ArrayMem that)
	{
		if (that.val!=val)
			return false;
		else 
			return true;
	}
	
}
public class EX1 {
	public static void main(String[] args)
	{
		//Get the limit of array a and b.
		int lim=new ArrayMem().MaxCounts;
		
		//Instantiation for Array a and b.
		ArrayMem[] a=new ArrayMem[lim];
		ArrayMem[] b=new ArrayMem[lim];
		
		for (int i=0;i<lim;i++)
		{
			//Fill value and print out.
			int rndVal=(int)(Math.random()*90+10);
			a[i]=new ArrayMem(rndVal);
		    b[i]=new ArrayMem(rndVal);
		    System.out.println("a["+i+"]="+a[i].val+"     b["+i+"]="+b[i].val);
		}
		
		/* Array a is not equals to b for the Array.equals()
		 * ONLY COMPARE EACH HANDLE OF ArrayMem in a and b
		 * rather than the value.
		 */
		System.out.println("Array a and Array b is equal to each other by Arrays.equals() method:"+Arrays.equals(a, b));
	    
	    /*
	     * Now compare array a & b by the method we rewrite in
	     * class ArrayMem.
	     */
		
		boolean flag=true;
		for (int i=0;i<lim;i++)
		   if (!a[i].equals(b[i])) 
		   {
			   flag=false;
			   break;
		   }
	    System.out.println("Array a and Array b is equal to each other by REWRITED method:"+flag);
	    
	}

}
