class TThread implements Runnable
{
	private String ThreadName;
	public TThread(String x)
	{
		ThreadName=x;
		System.out.println("Thread:"+ThreadName+" has been created.");
	}
	public void run()
	{
		for (int i=0;i<=10;i++)
		{
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ThreadName+" @ "+i);
		}
	}
}
public class TIJ_EX28_Main {
  public static void main(String[] args)
  {
	  TThread t1=new TThread("A");
	  Thread r1=new Thread(t1);
	  TThread t2=new TThread("B");
	  Thread r2=new Thread(t2);
	  r1.start();
	  try {
		r1.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  r2.start();
	  
  }
}
