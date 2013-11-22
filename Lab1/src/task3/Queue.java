package task3;

import java.io.PrintStream;

public interface Queue<T>{
	public T peek() throws MyException;
	public void dequeue();
	public void enqueue(T c);
	public boolean isEmpty();
	public void show(PrintStream p);
}