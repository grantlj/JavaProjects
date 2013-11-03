class Foo extends Thread
{
	static int val=100;
	String name;
	public void printVal()
	{
		System.out.println(name+" val:"+val);
	}
	public void changeVal() throws InterruptedException
	{
		synchronized(Foo.class){
		val+=10;
		}
		Thread.sleep(500);
	}
	public void run()
	{
	   for (int i=0;i<20;i++)
	   {
		   try {
			changeVal();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   printVal();
	   }
	}
	
	public Foo(String x)
	{
		name=x;
	}
}

class SyncTest2_Main{
	public static void main(String[] args) throws InterruptedException
	{
		Foo t1=new Foo("THREAD 1");
		Foo t2=new Foo("THREAD 2");
		t1.start();
		t2.start();
        t1.join();t2.join();
        System.out.println("Final Val is:"+Foo.val);
		
	}
}