package task2;

public class test {
	public static void main(String[] args)
	{
		I_Stack s=new I_Stack();
        for (char i='a';i<='l';i++)
        	s.push(i);
        s.pop();s.pop();
        s.show(System.out);
	}
}