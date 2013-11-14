/*
   Author        :  LiuJiang
   Compile Date  :  2013/11/13
   Introduction  :
     Homework    :  Ex5.1
     Give an integer, return its bits and print out inversely.
**/
import java.util.*;
public class EX1 {
    
	private static int getBits(int num)
    {
    	return (int) Math.log10(num)+1;
    }
    
    private static void prtRev(int num)
    {
    	if (num!=0) {
    		System.out.print(num % 10);
    		prtRev(num/10);
    		}
    }
	
    public static void main(String[] args)
  {
	  int num=new Scanner(System.in).nextInt();
	  int bits=getBits(num);
	  System.out.println("The number:"+num+" has "+bits+" bits");
	  System.out.print("The rev:");
	  prtRev(num);
	  
  }
}
