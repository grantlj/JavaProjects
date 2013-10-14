/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/14
   Introduction  :
     Homework    :  Ex1.2
     Simulate the result of playing coins for N times. 
 */
import java.util.*;
public class Ex1_2_Main {
	public static void main(String[] args)
	{
		Scanner Inp=new Scanner(System.in);
		Random  rnd=new Random();
		System.out.print("Input the times that you want to sitmulate:");
		int n=Inp.nextInt();
		int SumA=0,SumB=0,x;
		System.out.println("In "+n+" times experiments:");
		for (int i=0;i<n;i++)
		{
			x=Math.abs(rnd.nextInt())%2;
			if (x==0) 
				SumA++;
			else 
				SumB++;
		}
	   System.out.println("We hava A sides: "+SumA+" times");
	   System.out.println("We have B sides: "+SumB+" times");
	}

}
