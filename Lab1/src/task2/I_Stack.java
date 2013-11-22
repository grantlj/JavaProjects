package task2;

import java.io.PrintStream;
import java.util.ArrayList;



public class I_Stack<T> implements Stack<T>{
    @SuppressWarnings("rawtypes")
	private ArrayList lis;
	@SuppressWarnings("unchecked")
	public T top() throws MyException {
	  if (lis.isEmpty())
		  throw new MyException("Stack Is Empty");
	  T re=(T) lis.get(lis.size()-1);
	  lis.remove(lis.size()-1);
	  return re;
	}



	public void pop() {
		if (lis.size()!=0)
			lis.get(lis.size()-1);
	}


	@SuppressWarnings("unchecked")
	public void push(T c) {
	 lis.add(c);	
	}

	
	public boolean isEmpty() {
	  return lis.isEmpty();
	}


   public void show(PrintStream p) 
   {
	 p.println("top");
	 for (int i=lis.size()-1;i>=0;i--)
		 System.out.println("|"+lis.get(i)+"|");
	 p.println("---");
	 p.println("bot");
		 
   }
	
	@SuppressWarnings("rawtypes")
	public I_Stack()
	{
		lis=new ArrayList();
	}
}