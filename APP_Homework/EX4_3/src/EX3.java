import java.util.Scanner;

/*
   Author        :  LiuJiang
   Compile Date  :  2013/11/5
   Introduction  :
     Homework    :  Ex4.3
     Get the counts of letter, blank, digit and other characters.
**/
class TCounter
{
	private int letters;
	private int blanks;
	private int digits;
	private int others;
	private String str;
	
	TCounter(String s)
	{
		letters=blanks=digits=others=0;
		str=s;
	}
	
	public final void add(char c)
	{
		if ((c>='A' && c<='Z') || (c>='a' && c<='z')) 
			letters++;
		else if (c==' ')
			blanks++;
		else if (c>='0'&& c<='9')
			digits++;
		else 
			others++;
	
	}
	
	public final void prtVal()
	{
		System.out.println("Statistics tics of characters of string:"+str);
		System.out.println("letter:     "+letters);
		System.out.println("blank :     "+blanks);
		System.out.println("digit :     "+digits);
		System.out.println("other :     "+others);
	}
}
public class EX3 {
  public static void main(String[] args)
  {
	  //Input the string.
	  System.out.print("Enter the string:");
	  Scanner sc=new Scanner(System.in);
	  String str=sc.nextLine();
	  
	  //Instantiation a new count.
	  TCounter count=new TCounter(str);
	  
	  for (int i=0;i<str.length();i++)
		count.add(str.charAt(i));
	  
	  //Give out the answer.
	  count.prtVal();
	  
  }
}
