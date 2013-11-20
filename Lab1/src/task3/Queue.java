package task3;

import java.io.PrintStream;

public interface Queue{
	public char peek() throws MyException;
	public void dequeue();
	public void enqueue(char c);
	public boolean isEmpty();
	public void show(PrintStream p);
}