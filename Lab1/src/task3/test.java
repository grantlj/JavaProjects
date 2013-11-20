package task3;

public class test {
  public static void main(String[] args)
  {
	  I_Queue s=new I_Queue();
	  s.show(System.out);
	  try {
		s.peek();
	} catch (MyException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  for (char i='a';i<='k';i++)
	    s.enqueue(i);
	  s.show(System.out);
	  try {
		System.out.println(s.peek());
	} catch (MyException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  s.dequeue();
	  s.show(System.out);
	  s.enqueue('x');
	  s.enqueue('y');
	  s.show(System.out);
  }
}
