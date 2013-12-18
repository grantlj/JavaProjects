import java.text.SimpleDateFormat;
import java.util.Date;


public class MyException extends Exception{
 
	private static final long serialVersionUID = 1L;

public MyException(String msg)
   {
	   System.out.println();
	   SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
       System.out.println(df.format(new Date())+"------Error---->"+msg);
	   System.out.println("Program exited.");
	   System.exit(0);
   }
}
