package task4;

public class test {
  public static void main(String[] args)
  {
	  I_PriQueue s=new I_PriQueue();
	  System.out.println(s.isEmpty());
	  s.deleteItem();
	  for (int i=0;i<3;i++)
		 for (char j='a';j<='d';j++)
			 s.insertItem(i,j);
	  s.show(System.out);
	  s.insertItem(0, 'a');
	  s.insertItem(0, 'f');
	  s.show(System.out);
	  s.insertItem(0,'e');
	  s.show(System.out);
	  s.deleteItem();
	  s.deleteItem();
      s.show(System.out);
	  
  }
}
