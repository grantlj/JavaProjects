import java.util.*;
public class TIJ_EX26_Main {
  public static void main(String[] args)
  {
	  Vector<Integer> a=new Vector<Integer>(10);
	  for (int i=1;i<10;i++)
		 //Not only you can add a handle but also a num,string,etc...
		  a.addElement(i);
	  
	  a.insertElementAt(100,3);
	  a.setElementAt(200,4);
	  a.removeElementAt(0);
	  System.out.println("Find 100 @ "+a.indexOf(100));
	  System.out.println("The First element is:"+a.firstElement());
	  System.out.println("The Last  element is:"+a.lastElement());
	  
	  System.out.println("Using Vector to print out:");
	  for (int i=0;i<a.size();i++)
		  System.out.print(a.elementAt(i)+" ");
	  System.out.println();
	  
	  System.out.println("Using Enumeration to print out:");
	  Enumeration<Integer> e=a.elements();
	  while (e.hasMoreElements())
	  System.out.print(((int)e.nextElement())+" ");
  }
}
