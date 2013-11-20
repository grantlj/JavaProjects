package task4;

public class MyException extends Exception
{

	private static final long serialVersionUID = 1L;
	public MyException(String msg)
	{
		super("I_PriQueue Exception:"+msg);
	}
}
