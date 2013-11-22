package task3;

import org.junit.Test;

public class test {
    //test1 to test4:  basic test.
	//test5 to test7:  more than 15 sequence test.
	//test8 to test10: throw exception test.
	@Test
	public void test1(){
		I_Queue<String> is = new I_Queue<String>();
		is.enqueue("string");
		is.dequeue();
		is.show(System.out);
		
	}
	
	@Test
	public void test2() throws MyException{
		I_Queue<String> is=new I_Queue<String>();
		is.enqueue("string1");
		is.enqueue("string2");
		is.enqueue("string3");
		is.dequeue();
		is.dequeue();
		System.out.println(is.peek());
	}
	
	@Test
	public void test3() throws MyException{
		I_Queue<Integer> is=new I_Queue<Integer>();
		is.dequeue();
		is.enqueue(1);
		is.enqueue(2);
		is.enqueue(3);
		is.dequeue();
		is.enqueue(4);
		is.show(System.out);
	}
	
	@Test
	public void test4(){
		I_Queue<Integer> is=new I_Queue<Integer>();
		for (int i=0;i<10;i++)
			is.enqueue(i);
		for (int i=0;i<10;i++)
			is.dequeue();
	}
	
	@Test
	public void test5(){
		I_Queue<Integer> is=new I_Queue<Integer>();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.enqueue(i);
			else
				is.dequeue();
		}
	}
	
	@Test
	public void test6() throws MyException{
		I_Queue<Character> is=new I_Queue<Character>();
		for (char i='a';i<='z';i++)
			is.enqueue(i);
		for (int i=0;i<=25;i++)
			is.dequeue();
		is.show(System.out);
	}
	
	@Test
	public void test7(){
		I_Queue<String> is=new I_Queue<String>();
		for (int i=0;i<=15;i++)
		{
			is.enqueue("tester"+i);
		    is.dequeue();
		}
	}
	
	@Test
	public void test8() throws MyException{
		I_Queue<String> is = new I_Queue<String>();
	    is.peek();
		is.show(System.out);
	}
	
	@Test
	public void test9() throws MyException{
		I_Queue<String> is=new I_Queue<String>();
		for (int i=0;i<=15;i++)
		{
			is.enqueue("tester"+i);
		    is.peek();
		    is.dequeue();
		}
		is.peek();
		
	}
	
	@Test
	public void test10() throws MyException{
		I_Queue<Integer> is=new I_Queue<Integer>();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.enqueue(i);
			else
				is.dequeue();
		}
		is.peek();
	}
	
}