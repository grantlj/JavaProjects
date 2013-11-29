package task1;

public class SortedListTest {
  public static void main(String[] args)
  {
	  SortedArrayList<String> s=new SortedArrayList<String>(100);
	 
	  s.insert("Snow");
	  s.insert("Hartley");
	  s.insert("Bishop");
	  s.insert("Reisig");
	  s.insert("Arnold");
	 
	  for (int i=0;i<s.size();i++)
		  System.out.println(s.get(i));

  }
}
