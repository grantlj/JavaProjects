package task4;

import java.io.PrintStream;


public class I_PriQueue implements PriQueue{
	private PList lis;
	public char next() throws MyException {
		char c=lis.get(lis.getHigh(),lis.getCountAt(lis.getHigh()));
		if (c==-1) 
			throw new MyException("Queue is empty.");
		return c;
	}

	public void deleteItem() {
		lis.delete(lis.getHigh());
	}

	public void insertItem(int pri, char c) {
		lis.add(pri, c);
	}

	public boolean isEmpty() {
		return (lis.getCount()==0);
	}

	public void show(PrintStream p) {
	  System.out.println();
	  for (int i=0;i<PList.PioMax;i++)
	  {
		  System.out.print("|"+i+"|");
		  for (int j=0;j<=lis.getCountAt(i)-1;j++)
			  System.out.print(lis.get(i, j)+" ");
		  System.out.println();
	  }
	}
	
    public I_PriQueue(){
    	lis=new PList();
    }
	

}
