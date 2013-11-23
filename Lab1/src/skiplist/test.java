package skiplist;
import org.junit.Test;

public class test {
	//test1 to test4:  basic test.
	//test5 to test7:  more than 15 sequence test.
	//test8 to test10: throw exception test.
	@Test
	public void test1(){
	  I_Skiplists<String> is = new I_Skiplists<String>();
	  is.insert("string");
	  is.insert("apple");
	  is.insert("toy");
	  is.insert("baby");
	  is.show(System.out);
	}
	
	@Test
	public void test2() throws MyException{
		  I_Skiplists<String> is = new I_Skiplists<String>();
		  is.insert("string");
		  is.insert("apple");
		  is.insert("toy");
		  is.insert("baby");
		  is.show(System.out);
		  is.delete(2);
		  is.show(System.out);
		}
	
	@Test
	public void test3() throws MyException{
		  I_Skiplists<Integer> is = new I_Skiplists<Integer>();
		  is.insert(1);
		  is.insert(2);
		  is.insert(3);
		  is.insert(4);
		  is.delete(2);
		  is.show(System.out);
		  System.out.println(is.search(3));
		  is.show(System.out);
		}
	
	@Test
	public void test4() throws MyException{
		  I_Skiplists<Integer> is = new I_Skiplists<Integer>();
		  is.insert(1);
		  is.insert(2);
		  is.insert(3);
		  is.insert(4);
		  is.delete(2);
		  is.show(System.out);
		  System.out.println(is.search(3));
		  is.show(System.out);
		}
	
	@Test
	public void test5(){
		I_Skiplists<Integer> is=new I_Skiplists<Integer>();
		for (int i=0;i<20;i++)
			is.insert(i);
		is.show(System.out);
		
	}
	
	@Test
	public void test6() throws MyException
	{
		I_Skiplists<Integer> is=new I_Skiplists<Integer>();
		for (int i=0;i<50;i++)
			is.insert(i);
		is.show(System.out);
		for (int i=10;i<20;i++)
			is.delete(i);
		is.show(System.out);
	}
	
	@Test
	public void test7()
	{
		I_Skiplists<Integer> is=new I_Skiplists<Integer>();
		for (int i=0;i<100;i++)
			is.insert(i);
		is.show(System.out);
		for (int i=0;i<10;i++)
		{
			int rnd=(int) (Math.random()*20+10);
			System.out.println(is.search(rnd));
		}
	}
	
	@Test
	public void test8() throws MyException
	{
		I_Skiplists<Integer> is=new I_Skiplists<Integer>();
		is.delete(1);
	}
	
	@Test
	public void test9() throws MyException
	{
		I_Skiplists<Integer> is=new I_Skiplists<Integer>();
		for (int i=0;i<100;i++)
		{
			is.insert(i);
			is.delete(i);
		}
	}
	
	@Test
	public void test10() throws MyException
	{
		I_Skiplists<Integer> is=new I_Skiplists<Integer>();
		for (int i=0;i<100;i++)
		{
			is.insert(i);
			is.search(i);
		}
		for (int i=1;i<100;i++)
			is.delete(i);
		
	}
	
}
 
