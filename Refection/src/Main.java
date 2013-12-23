import java.util.*;
import java.lang.reflect.*;
public class Main {
  public static void main(String[] args) throws ClassNotFoundException
  {
	  @SuppressWarnings("rawtypes")
	Class c=Class.forName("java.lang.String");
	Field[] f=c.getFields();
	for (int i=0;i<f.length;i++)
	  System.out.println(f[i].toString());
	  
  }
}
