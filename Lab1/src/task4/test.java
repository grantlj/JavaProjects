package task4;

import org.junit.Test;


public class test {
    //test1 to test4:  basic test.
	//test5 to test7:  more than 15 sequence test.
	//test8 to test10: throw exception test.
	@Test
	public void test1(){
		I_PriQueue<String> is = new I_PriQueue<String>();
		is.insertItem(1,"string");
		is.deleteItem();
		is.show(System.out);
		
	}
	
	@Test
	public void test2() throws MyException{
		I_PriQueue<String> is=new I_PriQueue<String>();
		is.insertItem(2,"string1");
		is.insertItem(1,"string2");
		is.insertItem(2,"string3");
		is.deleteItem();
		is.deleteItem();
	}
	
	@Test
	public void test3() throws MyException{
		I_PriQueue<String> is=new I_PriQueue<String>();
		is.deleteItem();
		is.insertItem(1,"hello");
		is.insertItem(0,"hello!");
		is.insertItem(3,"java");
		is.deleteItem();
		is.insertItem(4,"cpp");
		is.show(System.out);
	}
	
	@Test
	public void test4() throws MyException{
		I_PriQueue<Integer> is=new I_PriQueue<Integer>();
		for (int i=0;i<10;i++)
		{
			int rndPri=(int) (Math.random()*4+1);
			is.insertItem(rndPri,i);
		}
		is.show(System.out);
		for (int i=0;i<10;i++)
			is.next();
	}
	
	@Test
	public void test5(){
		I_PriQueue<Integer> is=new I_PriQueue<Integer>();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.insertItem(i,i);
			else
				is.deleteItem();
		}
	}
	
	@Test
	public void test6() throws MyException{
		I_PriQueue<Character> is=new I_PriQueue<Character>();
		for (char i='a';i<='z';i++)
		{
			int rndPri=(int) (Math.random()*4+1);
			is.insertItem(rndPri,i);
		}
	    is.show(System.out);
		for (int i=0;i<=25;i++)
			is.next();
		is.show(System.out);
	}
	
	@Test
	public void test7() throws MyException{
		I_PriQueue<String> is=new I_PriQueue<String>();
		for (int i=0;i<=15;i++)
		{
			is.insertItem(i,"tester"+i);
		    is.next();
		}
	}
	
	@Test
	public void test8() throws MyException{
		I_PriQueue<String> is = new I_PriQueue<String>();
	    is.next();
		is.show(System.out);
	}
	
	@Test
	public void test9() throws MyException{
		I_PriQueue<String> is=new I_PriQueue<String>();
		for (int i=0;i<=15;i++)
		{
			is.insertItem(i,"tester"+i);
		    is.next();
		    is.deleteItem();
		}
		is.next();
	}
	

	@Test
	public void test10() throws MyException{
		I_PriQueue<Integer> is=new I_PriQueue<Integer>();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.insertItem(i,i);
			else
				is.deleteItem();
		}
		is.next();
	}
	
		
	}
	