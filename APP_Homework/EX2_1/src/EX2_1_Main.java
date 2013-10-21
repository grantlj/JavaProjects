/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/21
   Introduction  :
     Homework    :  Ex2.1
     Print out the prime number between 1 and 100.
 */
import java.util.*;
public class EX2_1_Main {
 public static void main(String[] args)
 {
	 int Count=0;
	 for(int i=2;i<=100;i++)
	 {
		 Boolean flag=true;
		 for (int j=2;j<=Math.sqrt(i);j++)
			 if (i%j==0) {
				 flag=false;
				 break;
			 }
		 if (flag)
		 {
			 System.out.print(i+" ");
			 Count++;
			 if (Count%5==0) System.out.println();
		 }
	 }
	 //System.out.println();
	 System.out.println("There are "+Count+" prime numbers in all.");
 }
}
