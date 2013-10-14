/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/14
   Introduction  :
     Homework    :  Ex1.3
     Radix conversion.
     Work mode 1 : Convert a DEC number to other.
     Work mode 2 : Convert other number to DEC.
 */
import java.util.*;
import java.math.*;
public class Ex1_3_Main {
	static Scanner inp=new Scanner(System.in);
   
	//WorkMode1:Convert Dec number to others.
	static void DoDecToOther()
	{
		System.out.println("Convert a DEC number to other.");
		System.out.print("Input destinate radix:");
		int dest=inp.nextInt();
		System.out.print("Input DEC number:");
		int x=inp.nextInt();
	    String Sx=Integer.toString(x);
		BigInteger src=new BigInteger(Sx);
		
		System.out.println("The DEC number:"+x+" convert to "+dest+ " is:"+src.toString(dest));
		
	}
    
	//WorkMode2:Convert other numbers to Dec.
	static void DoOtherToDec()
	{
		System.out.println("Convert a other number to DEC.");
		System.out.print("Input original radix:");
		int orig=inp.nextInt();
	    System.out.print("Input origin number:");
	    String x=inp.next();
	    BigInteger src=new BigInteger(x,orig);
	   System.out.println("The RADIX:"+orig+" number:"+x+" convert to DEC is "+src.toString(10));
	    
	 }
	
	public static void main(String[] args)
	{
		
		int WorkMode;
		do
		{
			System.out.print("Select work mode:");
			WorkMode=inp.nextInt();
		} 
		while ((WorkMode!=1)&&(WorkMode!=2));
	if (WorkMode==1) DoDecToOther();
	else DoOtherToDec();

   }
}
