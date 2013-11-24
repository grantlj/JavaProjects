package priqueue2;

import org.junit.Test;


public class test {
    //test1 to test4:  basic test.
	//test5 to test7:  more than 15 sequence test.
	//test8 to test10: throw exception test.
	@Test
	public void test1(){
		I_PriQueue is = new I_PriQueue();
		is.insertItem(1,'a');
		is.deleteItem();
		is.show(System.out);
		
	}
	
	@Test
	public void test2() throws MyException{
		I_PriQueue is=new I_PriQueue();
		is.insertItem(2,'a');
		is.insertItem(1,'b');
		is.insertItem(2,'c');
		is.deleteItem();
		is.deleteItem();
		is.show(System.out);
	}
	
	@Test
	public void test3() throws MyException{
		I_PriQueue is=new I_PriQueue();
		is.deleteItem();
		is.insertItem(1,'h');
		is.insertItem(0,'e');
		is.insertItem(3,'j');
		is.deleteItem();
		is.insertItem(4,'c');
		is.show(System.out);
	}
	
	@Test
	public void test4() throws MyException{
		I_PriQueue is=new I_PriQueue();
		for (int i=0;i<10;i++)
		{
			int rndPri=(int) (Math.random()*4+1);
			is.insertItem(rndPri,(char) i);
		}
		is.show(System.out);
		for (int i=0;i<10;i++)
			System.out.println(is.next());
	}
	
	@Test
	public void test5(){
		I_PriQueue is=new I_PriQueue();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.insertItem(i,(char) i);
			else
				is.deleteItem();
		}
	}
	
	@Test
	public void test6() throws MyException{
		I_PriQueue is=new I_PriQueue();
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
		I_PriQueue is=new I_PriQueue();
		for (int i=0;i<=15;i++)
		{
			is.insertItem(i,(char) (i+97));
		    is.next();
		}
	}
	
	@Test
	public void test8() throws MyException{
		I_PriQueue is = new I_PriQueue();
	    is.next();
		is.show(System.out);
	}
	
	@Test
	public void test9() throws MyException{
		I_PriQueue is=new I_PriQueue();
		for (int i=0;i<=15;i++)
		{
			is.insertItem(i,(char)(i+97));
		    is.next();
		    is.deleteItem();
		}
		is.next();
	}
	

	@Test
	public void test10() throws MyException{
		I_PriQueue is=new I_PriQueue();
		for (int i=0;i<=50;i++)
		{
			if (i%2==1)
				is.insertItem(i,(char)i);
			else
				is.deleteItem();
		}
		is.next();
	}
	
		
	}
	