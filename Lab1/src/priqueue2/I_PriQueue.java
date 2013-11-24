package priqueue2;

import java.io.PrintStream;

public class I_PriQueue implements PriQueue{
  
	private Dlinknode head,tail,now;
    private boolean EmpFlag=true;
    private int count=0;
	

	public char next() throws MyException {
		Dlinknode dest=tail.prev;
		if (dest.level==0)
			throw new MyException("PriQueue is empty.");
		else
		{
			return  dest.val;
		}
	}

	public void deleteItem() {
		if (!EmpFlag)
		{
			Dlinknode dest=tail.prev;
			dest.prev.next=tail;
			if (dest.prev.level==0) EmpFlag=true;
			tail.prev=dest.prev;
			dest=null;
			count--;
			if (count==0) EmpFlag=true;
		}
	}
	

	

	private Dlinknode getPosition(int pri,char c)
	{
	   if (count==0)
	   {
		   EmpFlag=false;
		   return head;
	   }
	   else
	   {
		   Dlinknode tmpH=head.next;
		   while (tmpH.pri<pri && tmpH.level!=2)
			   tmpH=tmpH.next;
		   if (tmpH.level==2 || tmpH.pri>pri) return tmpH.prev;
		   else
		   {
			   while (tmpH.val<c && tmpH.level!=2 && tmpH.pri==pri)
				   tmpH=tmpH.next;
			  return tmpH.prev;
		   }
	   }
	}
	
	public void insertItem(int pri, char c) {
		now=new Dlinknode();
		now.pri=pri;
		now.val=c;
		now.level=1;
	    Dlinknode p=getPosition(pri,c);
	    now.prev=p;
	    now.next=p.next;
	    p.next.prev=now;
	    p.next=now;
	    count++;
	    
	}

	public boolean isEmpty() {
		return EmpFlag;
	} 
	public void show(PrintStream p) {
		Dlinknode tmpH=head;
		int i=0;
		while ((tmpH=tmpH.next).level!=2)
		{
		    i++;
			p.println("p"+i+" value:"+tmpH.val+" Pri:"+tmpH.pri+" level:"+tmpH.level);
		}
	}
	

	public I_PriQueue()
	{
		head=new Dlinknode();
		head.level=0;
	
		tail=new Dlinknode();
		tail.level=2;
		
		head.next=tail;
		head.prev=tail;
		
		tail.next=head;
		tail.prev=head;
	}
  
}
