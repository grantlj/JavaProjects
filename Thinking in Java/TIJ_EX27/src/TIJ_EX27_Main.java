class TMulti extends Thread{
	String Name;
	int time;
	public TMulti(String x,int time)
	{
		this.Name=x;
		this.time=time;
		System.out.println(x+" thread created.");
	}
	
	
	public void run()
	{
		while (time!=-1)
		{
			System.out.println(Name+" counting down:"+this.time);
			this.time--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(Name+" paused.");
			}
		}
		}
	}


public class TIJ_EX27_Main {
	public static void main(String[] args)
	{
		TMulti a=new TMulti("my thread 1",20);
		TMulti b=new TMulti("my thread 2",20);
		a.setPriority(10);
		b.setPriority(1);
		a.start();
		b.start();
	}

}
