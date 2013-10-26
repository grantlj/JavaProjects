package MyPackage;

public class MyClass1 {
  int a,b;
  static
  {
	  System.out.println("This is MyClass1!");
  }
  public MyClass1(int a,int b)
  {
	  this.a=a;this.b=b;
  }
  public void prt()
  {
	  System.out.println(a+" "+b);
  }
}
