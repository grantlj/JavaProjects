package MyPackage;

public class MyClass2 {
	  int a,b,c;
	  static
	  {
		  System.out.println("This is MyClass2!");
	  }
	  public MyClass2(int a,int b,int c)
	  {
		  this.a=a;this.b=b;this.c=c;
	  }
	  public void prt()
	  {
		  System.out.println(a+" "+b+" "+c);
	  }
}
