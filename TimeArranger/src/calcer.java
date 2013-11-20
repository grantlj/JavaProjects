/*Project Name: Time Arranger
 *Version     : 1.0
 *Author:       Grant Liu
 *              Deng Jie
 *      
 *History     :
 *Version 1.0   2013/11/20     *Fix bugs in GUI.
 *Version 1.0   2013/11/17     *Finish basic functions. 
 *
 *Brief Introduction:
 *   Time Arranger is a program to simplify the process 
 *   of arranging the time for lecture high efficiently.
 *   The program is based on C/S frame, with the tech of
 *   Ftp and Servlet in Java language.   
 */

/*Module Name: calcer
 * Version   : 1.0
 * Brief Introduction:
 *   calcer is the main moudle of the server, which based
 *   on ftp. The calcer generate arrange.info and calc out
 *   the best time for class, then save it to a .result
 *   file which is later for servlet to use.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*Result
 * The class store the temporary or final result of arrange-
 * ment.
 * 
 * timeStamp : record the result's generate time
 * week      : the week which the result record
 * lecture   : the lecturer's name
 * lesson    : a brief introduction of lecture
 * AttenCount: the counter of users who are able to attend 
 *             class
 * UttenCount: the counter of users who are not able to attend
 *             class
 * Atten[]   : the list of those who should attend class
 * Utten[]   : the list of those who cant attend class
 */

class Result
{
	static String timeStamp;
	static int week;
	static String lecture;
	static String lesson;
	static int AttenCount;
	static int UttenCount;
	static int selectedTime;
	public  static int[] Atten=new int[12];
	public  static int[] Utten=new int[12];
}

/*calcer
 * The main class of calcer.java
 */
public class calcer {
	static String arrangeFileName="arrange.info";
	static int week;
	static String lecture;
	static String lesson;
	
	static boolean[][] Raw=new boolean[12][7];
	static int[] total=new int[7];
	
	/*checkArrangeFile()
	 * To check whether the arrange.info is exist or
	 * not.
	 */
	public static boolean checkArrangeFile()
	{
       File file=new File(arrangeFileName);
       if (file.exists()) 
    	   return true;
       else 
    	   return false;
	}
	
	/*saveInfoToFile()
	 * To save class info to arrange.info.
	 */
	private static void saveInfoToFile() throws FileNotFoundException
	{
		PrintStream print=new PrintStream(new FileOutputStream(new File(arrangeFileName)));
		print.println(week);
		print.println(lecture);
		print.print(lesson);
		print.close();
		System.out.println("Save arrange file successfully.");
	}
	
	/*getInfoFromFile()
	 * to obtain class info from arrange.info.
	 */
	private static boolean getInfoFromFile()
	{
		if (checkArrangeFile())
		{
		File file=new File(arrangeFileName);
		
		try {
			Scanner sc=new Scanner(file);
			week=sc.nextInt();
			sc.nextLine();
			lecture=sc.nextLine();
			lesson=sc.nextLine();
			sc.close();
		
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		return true;
		}
		else {
			System.out.println("Arrange file not exists.");
			return false;
		}
		
	}
	
	/*generateArrangeFile()
	 * To create arrange.info and get the class info from console.
	 */
	public static void generateArrangeFile() throws FileNotFoundException
	{
		System.out.println();
		System.out.println("=====================Gnenerate===========================");
		
		if (checkArrangeFile())
		{
			int sel;
			System.out.println("Arrange file already exists.");
			getInfoFromFile();
			showInfo();
			do
			{
			
		    System.out.println("1.Rewrite.");
		    System.out.println("2.Abort.");
		    sel=new Scanner(System.in).nextInt();
			}
			while (sel!=1 && sel!=2);
			if (sel==2)
				return;
 		}
		
		System.out.print("Week:");
		week=new Scanner(System.in).nextInt();
		
		System.out.print("Lecturer:");
		lecture=new Scanner(System.in).nextLine();
		
		System.out.print("Lesson information:");
		lesson=new Scanner(System.in).nextLine();
		
		System.out.println("**********Generate completed.**********");
		showInfo();
		saveInfoToFile();
		
	}
	
	/*refreshAttendList()
	 * To refresh the Result class.
	 */
	private static void refreshAttendList(int time)
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Result.selectedTime=time;
		Result.AttenCount=0;Result.UttenCount=0;
		Result.lecture=lecture;
		Result.lesson=lesson;
		Result.week=week;
		Result.timeStamp=df.format(new Date());
		for (int i=0;i<12;i++)
			if (Raw[i][time])
			{
				
				Result.Atten[Result.AttenCount++]=i;
			}
			else
			{
				
				Result.Utten[Result.UttenCount++]=i;
			}
	}
	
	/*calcResult()
	 * The core of calcer.It works out the best time to arrange
	 * the class.
	 */
	public static void calcResult() throws FileNotFoundException
	{
		System.out.println();
		System.out.println("=====================Calc Result===========================");
		if (!getInfoFromFile())
		{
			System.out.println("Unable to calc class arragement result.");
			return;
		}
		else
		{
			for (int i=0;i<12;i++)
			{
				String tmpFileName=week+"_"+MemberSets.Name[i].toUpperCase()+".dat";
				File tmp=new File(tmpFileName);
				if (!tmp.exists()) 
					System.out.println(MemberSets.Name[i]+" haven't submit.");
				else
				{
			 	  Scanner sc=new Scanner(tmp);
				  sc.nextLine();
				  for (int j=1;j<=6;j++)
				  {
					  Raw[i][j]=sc.nextBoolean();
					  sc.nextLine();
				  }
				  sc.close();
				} 
			}
		    
			for (int i=1;i<=6;i++)
				total[i]=0;
		    for (int i=1;i<=6;i++)
		    	for (int j=0;j<12;j++)
		    		if (Raw[j][i]) total[i]++;
		    
		   int max=-1;int maxp=0;
		   for (int i=1;i<=6;i++)
			   if (total[i]>max)
			   {
				   max=total[i];
				   maxp=i;
				   refreshAttendList(maxp);
			   }
		   System.out.println("position:"+maxp);
		    
		}
	saveResultToFile();	
	}
	
	/*saveResultToFile()
	 * Save the result info to .result file.
	 */
	public static void saveResultToFile() throws FileNotFoundException
	{
		PrintStream print=new PrintStream(new FileOutputStream(new File(week+".result")));
		print.println("====================================第 "+week+" 周 APP组培训计划====================================");
		print.println("主讲人："+Result.lecture);
		print.println("培训内容："+Result.lesson);
		print.println("培训时间："+TimeSets.weekDay[Result.selectedTime]);
		print.println("========================================================================");
	    print.println();
		print.println("参与培训人员名单：");
	    for (int i=0;i<Result.AttenCount;i++)
	    	print.printf("NO.%d\t%s\t%s\n",i+1,MemberSets.Name[Result.Atten[i]],MemberSets.Pwd[Result.Atten[i]]);
	    print.println("========================================================================");
	    if (Result.UttenCount!=0)
	    {
	        print.println();
	    	print.println("缺席培训人员名单：");
	      for (int i=0;i<Result.UttenCount;i++)
	    	  print.printf("NO.%d\t%s\t%s\n",i+1,MemberSets.Name[Result.Utten[i]],MemberSets.Pwd[Result.Utten[i]]); 
	    }
	    for (int i=0;i<8;i++)
	      print.println();
	    print.println("制表时间"+Result.timeStamp);	    
	    print.close();
	}
	
	/*clean()
	 * To clean the files below:
	 * *.info   (arrange)
	 * *.dat    (user submitted files)
	 * *.reslut (calc out files)
	 */
	public static void clean() throws IOException
	{
	   System.out.println("*****************ARE YOU SURE TO CLEAN EVERYTHING?****************");
	 
	   int sc;
	   do
	   {
		   System.out.println("1=yes;2=no");
	       sc=new Scanner(System.in).nextInt();
	   }
	   while (sc!=1 && sc!=2);
	   if (sc==1) 
	   {
	
		  Runtime rt = Runtime.getRuntime();
		  rt.exec("cmd /c del *.info");
		  System.out.println(".info file deleted.");
		  rt.exec("cmd /c del *.dat");
		  System.out.println(".dat file deleted.");
		  rt.exec("cmd /c del *.result");
		  System.out.println(".result file deleted.");
	   }
	   
	}
	
	/*showResult()
	 * To read out the result from .result file.
	 */
	public static void showResult() throws FileNotFoundException
	{
		System.out.println();
		System.out.println("=====================Result=============================");
		String tmpFileName;
		File tmp;
		do
		{
		  System.out.println("Enter the week to view:");
		  int week=new Scanner(System.in).nextInt();
		  tmpFileName=week+".result";
		  tmp=new File(tmpFileName);
		}
		while (!tmp.exists());
		Scanner sc=new Scanner(tmp);
		do
		{
			String out=sc.nextLine();
			System.out.println(out);
		}
		while (sc.hasNextLine());
		sc.close();
	}
	
	/*showInfo()
	 * Give out the basic class info.
	 */
	public static void showInfo()
	{
		System.out.println();
		System.out.println("=====================Class Info==========================");
		System.out.println("Week:"+week);
		System.out.println("Lecturer:"+lecture);
		System.out.println("Lesson:"+lesson);
		System.out.println();
	}	
	
	/*showMenu()
	 * Show the menu, for administrator to choose
	 * function.
	 */
	public static void showMenu() throws IOException
	{

		do
		{
			System.out.println("=========================Menu============================");
			System.out.println("1.Generate/Edit new arrangement.");
			System.out.println("2.Calculate class arrange result.");
			System.out.println("3.Publish class arrange result.");
			System.out.println("4.Show class arrange result.");
			System.out.println("5.Clean everything.");
			System.out.println("6.Exit.");
			int sel;
			
		do
		{
		  System.out.println();
		  System.out.println("Select a funtion to continue.");
		  sel=new Scanner(System.in).nextInt();
		  switch (sel)
		  {
		    case 1:
		    	generateArrangeFile();
			    break;
		    case 2:
		    	calcResult();
			    break;
		    case 3:
		    	saveResultToFile();
			    break;
		    case 4:
		    	getInfoFromFile();
		    	showInfo();
		    	showResult();
			    break;
		    case 5:
		    	clean();
		    	break;
		    case 6:
			    System.exit(0);	  
		  }
		}
		while (!(sel>=1 && sel<=5));
		}
		while (true);
	}
  
  /*main()
   * Program run from here.
   */
  public static void main(String[] args) throws IOException
  {
	 try {
		showMenu();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
  }
}
