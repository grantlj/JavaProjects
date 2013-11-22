package task4;

import java.io.PrintStream;


public class I_PriQueue<T> implements PriQueue<T>{
	@SuppressWarnings("rawtypes")
	private PList lis;
	public T next() throws MyException {
		@SuppressWarnings("unchecked")
		T c=(T) lis.get(lis.getHigh(),lis.getCountAt(lis.getHigh()));
		if (c==null) 
			throw new MyException("Queue is empty.");
		return c;
	}

	public void deleteItem() {
		lis.delete(lis.getHigh());
	}

	public void insertItem(int pri, T c) {
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
	
    @SuppressWarnings("rawtypes")
	public I_PriQueue(){
    	lis=new PList();
    }
	

}
