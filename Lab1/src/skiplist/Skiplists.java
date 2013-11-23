package skiplist;

import java.io.PrintStream;

public interface Skiplists<T> {
	public void insert(T val);
	public void delete(int index) throws MyException;
	public int  search(T val);
	public boolean isEmpty();
	public void show(PrintStream p);
}
