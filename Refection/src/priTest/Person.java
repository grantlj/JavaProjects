package priTest;

public class Person {
 String arg; 
 public void showInfo()
  {
	  System.out.println("hello moto "+arg);
  }
  
  public void run()
  {
	  showInfo();
  }
  
  public Person(String s){
	  arg=s;
  }
}
