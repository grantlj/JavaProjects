package task3;

import java.io.PrintStream;

public class I_Queue implements Queue{
    
	private SnocList head,tail,now;
	private boolean empFlag=true;
	
	public char peek() throws MyException {
		if (empFlag)
			throw new MyException("Queue is empty.");
		else
			return head.c;
	}

	public void dequeue() {
	  if (head.l!=null)
		head=head.l;
	  else
	  {
		  head=null;
		  empFlag=true;
	  }
	}


	public void enqueue(char c) {
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
	
	public I_Queue(char c)
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
