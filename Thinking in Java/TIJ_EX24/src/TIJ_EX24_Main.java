/*Return an Array in Function.
 * 
 */
public class TIJ_EX24_Main {
  static int[] FunctionReturnsArray(int x,int y)
  {
	 int[] b;
	 b=new int[100];
	 for (int i=0;i<100;i++)
		 b[i]=(int)(Math.random()*(y-x)+x);
	 return b;
	 //Return an array....
  }
  public static void main(String[] args)
  {
	  int[] a;
	  a=new int[100];
	  a=FunctionReturnsArray(1,100);
      for (int i=0;i<100;i++)
      {
    	  System.out.print(a[i]+" ");
    	  if ((i+1)%5==0) System.out.println();
      }
  }
}
