package task3;

public class MyException extends Exception
{

	private static final long serialVersionUID = 1L;
	public MyException(String msg)
	{
		super("I_Queue Exception:"+msg);
	}
}

