class base
{
	void DoFuck(int x)
	{
		System.out.println("Base DO FUCK.");
	}
	final void DoFuck2()
	{
		System.out.println("Base DO FUCK2.");
	}
}

class stu extends base
{
	void DoFuck()
	{
		System.out.println("HI!!!");
	}
	
	/*
	 * (non-Javadoc)
	 * @see base#DoFuck2()
	 This is FORBIDDEN. DoFuck2() is locked in base class.
	void DoFuck2()
	{
		System.out.println("hello world.");
	}
	*/
}
public class TIJ_EX21_Main {
  static void upcast(base t)
  {
	  t.DoFuck(2);
	  t.DoFuck2();
  }
	public static void main(String[] args)
  {
	  stu s=new stu();
	  s.DoFuck();
	  upcast(s);
  }
}
