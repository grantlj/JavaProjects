/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/21
   Introduction  :
     Homework    :  Ex2.1b
     Test switch...case with Break.
 */
public class EX2_2b {
  public static void main(String[] args)
  {
	  for (int i=0;i<5;i++)
	  {
		  int x=i;
		  System.out.println("Here comes number:"+i);
		  //With break.The program wont continue do next cases.
		  //BUT break ONLY EXIT switch case RAHTER THAN FOR....
		  switch (x) {
		  case 0:
			  System.out.println("Checkpoint "+0);
			  break;
		  case 1:
			  System.out.println("Checkpoint "+1);
			  break;
		  case 2:
			  System.out.println("Checkpoint "+2);
			  break;
		  case 3:
			  System.out.println("Checkpoint "+3);
			  break;
		  default:
			  System.out.println("Checkpoint "+4);
	  
		  }
	  }		  
  }
}