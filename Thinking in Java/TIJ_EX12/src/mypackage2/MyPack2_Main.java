package mypackage2;

//This doesn't work! for protected can't work beyond one package.

/*import mypackage1.test_protected;

public class MyPack2_Main {
	 public static void main(String[] args)
	   {
		   test_protected a=new test_protected(3,4);
		   a.prt();
		   
	   }
}
*/
//But you can extend it.....
import mypackage1.test_protected;
class ext extends test_protected
{

}

	
}

