package com.t4.getBit;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
  public static void main(String[] args)
  {
	 BigInteger a=new Scanner(System.in).nextBigInteger();
	 if (String.valueOf(a).length()<7)
	   System.out.println("Invalid input.");
	 else
	   System.out.println(String.valueOf(a).substring(4,8));
  }
}
