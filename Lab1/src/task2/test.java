package task2;

import org.junit.Test;

public class test {
    //test1 to test4:  basic test.
	//test5 to test7:  more than 15 sequence test.
	//test8 to test10: throw exception test.
	@Test
	public void test1(){
		I_Stack<String> is = new I_Stack<String>();
		is.push("string1");
		is.push("string2");
		is.show(System.out);
		
	}
	
	@Test
	public void test2() throws MyException{
		I_Stack<String> is=new I_Stack<String>();
		is.push("string1");
		is.push("string2");
		is.push("string3");
		is.pop();
		is.pop();
		System.out.println(is.top());
	}
	
	@Test
	public void test3() throws MyException{
		I_Stack<Integer> is=new I_Stack<Integer>();
		is.pop();
		is.push(1);
		is.push(2);
		is.push(3);
		is.pop();
		is.push(4);
		is.show(System.out);
		System.out.println(is.top());
	}
	
	@Test
	public void test4(){
		I_Stack<Integer> is=new I_Stack<Integer>();
		for (int i=0;i<10;i++)
			is.push(i);
		for (int i=0;i<10;i++)
			is.pop();
	}
	
	@Test
	public void test5(){
		I_Stack<Integer> is=new I_Stack<Integer>();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.push(i);
			else
				is.pop();
		}
	}
	
	@Test
	public void test6() throws MyException{
		I_Stack<Character> is=new I_Stack<Character>();
		for (char i='a';i<='z';i++)
			is.push(i);
		for (int i=0;i<=25;i++)
			is.top();
		is.show(System.out);
	}
	
	@Test
	public void test7(){
		I_Stack<String> is=new I_Stack<String>();
		for (int i=0;i<=15;i++)
		{
			is.push("tester"+i);
		    is.pop();
		}
	}
	
	@Test
	public void test8() throws MyException{
		I_Stack<String> is = new I_Stack<String>();
	    is.top();
		is.show(System.out);
	}
	
	@Test
	public void test9() throws MyException{
		I_Stack<String> is=new I_Stack<String>();
		for (int i=0;i<=15;i++)
		{
			is.push("tester"+i);
		    is.top();
		    is.pop();
		}
		is.top();
		
	}
	
	@Test
	public void test10() throws MyException{
		I_Stack<Integer> is=new I_Stack<Integer>();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.push(i);
			else
			{
				is.pop();
				is.top();
			}
		}
		is.top();

	}
	
}