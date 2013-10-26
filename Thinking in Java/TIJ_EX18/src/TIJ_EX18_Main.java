class Root
{
	public void play()
	{
		System.out.println("hello root.");
	}
	protected static void tune(Root i)
	{
		i.play();
	}
	Root()
	{
		System.out.println("Created!");
	}
}

public class TIJ_EX18_Main extends Root 
{
   TIJ_EX18_Main()
   {
	   //super();
	   System.out.println("Child Created.");
	   //super();
   }
  
   public void play()
   {
	   super.play();
	   System.out.print("shit's ");
	   //super();
   }
   public static void main(String[] args)
   {
	   TIJ_EX18_Main hi=new TIJ_EX18_Main();
	   Root.tune(hi);
   }
}
