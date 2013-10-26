/*
 * 1.Test the usage of UPCASTING.
 * 2.Test the usage of Vector.
 * 3.Test the usage of Enumeration.
 */
import java.util.*;
class Animal
{
	void Sound()
	{
		System.out.println(" Sounding.");
	}
}
class TCat extends Animal
{
	private int id;
	TCat(int i)
	{
		id=i;
		System.out.println("Created.");
	}
	void Sound()
	{
		System.out.print(id+" a cat is");
		super.Sound();
	}
}

class TDog extends Animal
{
	private int id;
	TDog(int i)
	{
		id=i;
	}
	void Sound()
	{
		System.out.print(id+" a dog is");
		super.Sound();
	}
}

public class TIJ_EX25_Main {
  static void upcasting(Animal obj)
  {
	  obj.Sound();
  }

public static void main(String[] args)
  {
	 /*Test of array & upcasting begin.
	 TDog dog[]=new TDog[5];
	 TCat cat[]=new TCat[5];  
     for (int i=0;i<5;i++)
     {
    	 dog[i]=new TDog(i);
    	 cat[i]=new TCat(i);
    	 upcasting(dog[i]);
    	 upcasting(cat[i]);
     }
     Test of array & upcasting end.
     */
    Vector cats=new Vector();
    for (int i=0;i<5;i++)
    	cats.addElement(new TCat(i));
    for (int i=0;i<cats.size();i++)
        //We use TCat to trans the id to a type TCat!!!!
    	((TCat)(cats.elementAt(i))).Sound();
    System.out.println("Size="+cats.size());
    
  }
}
