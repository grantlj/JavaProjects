package task2;

import java.io.PrintStream;

public interface Stack<T>{
	/*
	 * top(): Get the top element of stack.
	 *        IF EMPTY, then throw out an 
	 *        Exception.
	 * pop(): Pop out the top element of stack.
	 * push(): Push in a new element to stack.
	 * isEmpty(): To tell whether the stack is
	 *            empty or not.
	 * show(): Print out the present situation of
	 *         stack.
	 */
	public T top() throws MyException;
	public void pop();
	public void push(T c);
	public boolean isEmpty();
	public void show(PrintStream p);
}
