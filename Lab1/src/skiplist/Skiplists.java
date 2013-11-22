package skiplist;

import java.io.PrintStream;

public interface Skiplists {
	public void insert(int val);
	public void delete(int index);
	public int  search(int val);
	public boolean isEmpty();
	public void show(PrintStream p);
}
