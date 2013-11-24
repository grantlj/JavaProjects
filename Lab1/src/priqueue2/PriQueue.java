package priqueue2;

import java.io.PrintStream;

public interface PriQueue{
	public char next() throws MyException;
	public void deleteItem();
	public void insertItem(int pri,char c);
	public boolean isEmpty();
	public void show(PrintStream p);
}