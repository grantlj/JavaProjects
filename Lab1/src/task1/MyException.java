package task1;

class MyException extends Exception
{

	private static final long serialVersionUID = 1L;
	public MyException(String msg)
	{
		super("I_Stack Exception:"+msg);
	}
}