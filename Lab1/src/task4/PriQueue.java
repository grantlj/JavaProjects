package task4;

import java.io.PrintStream;

public interface PriQueue<T>{
	public T next() throws MyException;
	public void deleteItem();
	public void insertItem(int pri,T c);
	public boolean isEmpty();
	public void show(PrintStream p);
}