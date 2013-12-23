package priTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMain {
  public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException
  {
	  Class<?> demo=null;
	  demo=Class.forName("priTest.Person");
	  Method method=demo.getMethod("showInfo");
	  Constructor<?> con[]=demo.getConstructors();
	  System.out.println("constructors count:"+con[0].toString());
	   demo=(Class<?>) con[0].newInstance();
	 // method=demo.getMethod("showInfo");
	//  method.invoke(demo);
	 
  }
}
