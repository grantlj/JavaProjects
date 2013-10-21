/*
   Author        :  LiuJiang
   Compile Date  :  2013/10/21
   Introduction  :
     Homework    :  Ex2.1a
     Test switch...case with Break.
 */
public class EX2_2a {
  public static void main(String[] args)
  {
	  for (int i=0;i<5;i++)
	  {
		  int x=i;
		  System.out.println("Here comes number:"+i);
		  //Without break.The program will continue do next cases!!!!!
		  switch (x) {
		  case 0:
			  System.out.println("Checkpoint "+0);
		  case 1:
			  System.out.println("Checkpoint "+1);
		  case 2:
			  System.out.println("Checkpoint "+2);
		  case 3:
			  System.out.println("Checkpoint "+3);
		  default:
			  System.out.println("Checkpoint "+4);
	  
		  }
	  }		  
  }
}
