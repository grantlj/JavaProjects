package task3;

import java.io.PrintStream;

public class I_Queue<T> implements Queue<T>{
    
	@SuppressWarnings("rawtypes")
	private SnocList head,tail,now;
	private boolean empFlag=true;
	
	@SuppressWarnings("unchecked")
	public T peek() throws MyException {
		if (empFlag)
			throw new MyException("Queue is empty.");
		else
			return (T) head.c;
	}

	public void dequeue() {
	if (empFlag) return ; 
	if (head.l!=null)
		head=head.l;
	  else
	  {
		  head=null;
		  empFlag=true;
	  }
	}


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void enqueue(T c) {
	  now=new SnocList(c);
      if (empFlag)
      {
    	  head=now;
    	  tail=head;
    	  empFlag=false;
      }
      else
      {
	    tail.l=now;
        tail=now;
      }
     
	}


	public boolean isEmpty() {
		return empFlag;
	}


	@SuppressWarnings("rawtypes")
	public void show(PrintStream p) {
		if (!empFlag) {
		  p.print("--head--");
		  SnocList tmp;
		  tmp=head;
		  do
		  {
			  p.print(tmp.c+" ");
		  }
		  while ((tmp=tmp.l)!=null);
		  p.println("--tail---"); 
          }
		else
			p.println("Queue is empty.");
		}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public I_Queue(T c)
	{
		if (empFlag) 
		{
			head=new SnocList(c);
			tail=head;
			empFlag=false;
		}
		else
		  enqueue(c);
			
	}
	
	public I_Queue()
	{
		
	}
	
}
