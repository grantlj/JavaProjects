class R implements Runnable{
	private int x=0;
	public void run()
	{
		for (int i=0;i<100;i++)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(x++);
			
		}
	}
}
public class TIJ_EX29_Main {
  public static void main(String[] args)
  {
	  R r=new R();
	  for (int i=0;i<10;i++)
		  new Thread(r).start();  
  }
}
