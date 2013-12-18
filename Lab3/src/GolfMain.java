import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GolfMain {
 
  public static void main(String[] args) throws FileNotFoundException
  {
	  Scanner sc=new Scanner(System.in);
	  String fileName;
	  System.out.print("Please input the data file name(like test.txt):");
	  fileName=sc.nextLine();
	  //throw out file not exists exception.
	  File file=new File(fileName);
	 
	  if (!file.exists())
	  {
		 new MyException("File not found:"+fileName);
	  }
	  
	  sc=new Scanner(file);
	  
	  Golfer player[]=new Golfer[100];
	  Golfer par=null;
	  int playerCount=0;
	  
	  while (sc.hasNext())
	  {
	     String tmpStr=sc.nextLine();
	     tmpStr=tmpStr.replaceAll(" ", "");
			  
	     String tmpName;
	     tmpName=tmpStr.substring(0,tmpStr.indexOf(','));
	     tmpStr=tmpStr.substring(tmpStr.indexOf(',')+1);

	     String[] tmpScore=tmpStr.split(",");
	     int[] arg=new int[9];
	     for (int i=0;i<9;i++)
		    arg[i]=Integer.parseInt(tmpScore[i]);
	     
	     if (tmpName.toLowerCase().indexOf("par")!=-1)
	     {
	    	 if (par!=null)
	    	 {
	    		 new MyException("Duplicated Par found.");
	    	 }
	    	 else
	    		 par=new Golfer(tmpName,arg);
	     }
	     else
	        player[playerCount++]=new Golfer(tmpName,arg);
	  }
	  
	  par.setPar(par);
	  System.out.print("Name           ");
	  for (int i=0;i<9;i++)
		  System.out.print("H-"+i+" ");
	  System.out.print("Total     +/-Par");
	  System.out.println();
	  System.out.println(par.toString());
	  
	  int winKey=-1;int winPoint=10000;
	  
	  for (int i=0;i<playerCount;i++)
	  {
		  player[i].setPar(par);
		  System.out.println(player[i].toString());
		  if (player[i].totalStrokes()<winPoint)
		  {
			  winPoint=player[i].totalStrokes();
			  winKey=i;
		  }
	  }
	  
	  System.out.println("The lower score is: "+player[winKey].totalStrokes());
	  System.out.println("The winner is     : "+player[winKey].getName());
	  
  }
}
