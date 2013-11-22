package task1;

import java.io.PrintStream;

//import java.io.PrintStream;


public class I_Stack<T> implements Stack<T>{
    @SuppressWarnings("rawtypes")
	private List lis;
	
    @SuppressWarnings("unchecked")
	public T top() throws MyException{
	if (lis.getCount()-1<0)
		throw new MyException("Stack Is Empty");
	else
		return (T)lis.get(lis.getCount()-1);
	}



	public void pop() {
		lis.delete(lis.getCount()-1);
	}


	public void push(T c) {
	if (lis.add(c, lis.getCount())==false) System.out.println("push error:"+c+" count:"+lis.getCount());	
	}

	
	public boolean isEmpty() {
	   return (lis.getCount()==0);
	}


   public void show(PrintStream p) 
   {
	 p.println("top");
	 for (int i=0;i<lis.getCount();i++)
	 {	 
		 p.println("---");
		 p.println("|"+lis.get(lis.getCount()-i-1)+"|");
	 }
	 p.println("---");
	 p.println("bot");
		 
   }
	
	@SuppressWarnings("rawtypes")
	public I_Stack()
	{
		lis=new List();
	}
}

