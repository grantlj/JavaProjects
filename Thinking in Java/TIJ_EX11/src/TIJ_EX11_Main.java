import MyPackage.*;
import MyPackage2.*; //Test confliction between Package1 & Package2...
public class TIJ_EX11_Main {
  public static void main(String[] args)
  {
	  MyPackage.MyClass1 c1=new MyPackage.MyClass1(1,2);
	 // MyClass1 c3=new MyClass1(4,5);
	  MyClass2 c2=new MyClass2(3,4,5);
	  c1.prt();
	  c2.prt();
	  System.out.println(c1.a);
  }
}
