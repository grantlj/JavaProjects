/*Project Name: Time Arranger
 *Version     : 1.0
 *Author:       Grant Liu
 *              Deng Jie
 *      
 *History     :
 *Version 1.0   2013/11/17     *Finish basic functions. 
 *
 *Brief Introduction:
 *   Time Arranger is a program to simplify the process 
 *   of arranging the time for lecture high efficiently.
 *   The program is based on C/S frame, with the tech of
 *   Ftp and Servlet in Java language.   
 */

/*Module Name: submiter
 * Version   : 1.0
 * Brief Introduction:
 *   submiter is the main moudle of the client, which drive
 *   the user to submit his or her free time information to
 *   an ftp server.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class submiter {
	
	/* Basic constant and variable defined.
	 * boolean time[7] is a array to save users free time info,
	 * true means free, false means busy.
	 * 
	 * servIP is the dest server ip to connect.
	 * WE MUST REPLACE it to a domain LATER.
	 * 
	 * username and pwd is for user's info.
	 * 
	 * arrangeFile:submitter download this file from ftp server
	 * to update latest class information.
	 * 
	 * week, lecture, lesson is the variable to save temporary 
	 * information from arrangeFile.
	 * 
	 */
	static boolean[] time=new boolean[7];
	static FtpUtil ftp;
	static String servIP="grantlj.gicp.net";
	
	static String username;
	static String pwd;
	
	static String arrangeFileName="arrange.info";
	static int week;
	static String lecture;
	static String lesson;
	
	/*Logger()
	 *The Logger is a function to print out latest program
	 *information.
	 */
    public static void Logger(String info)
    {
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	System.out.println(df.format(new Date())+"---->"+info);
    }
	
    /*initProgram()
     *Initial the array of time.
     */
	public static void initProgram()
	{
	  for (int i=0;i<7;i++)
		time[i]=false;  
	  Logger("Program initialized.");
	}
	
	/*Login()
	 * The user must enter his user name and password to verify.
	 * All the user's information are saved in infos.java.
	 */
	public static void login()
	{
		do
		{
		  System.out.println("Enter your username:");
		  username=new Scanner(System.in).nextLine().toUpperCase();
		  System.out.println("Enter your password(stu id):");
		  pwd=new Scanner(System.in).nextLine().toUpperCase();
		}
		while (!new MemberSets().inNameList(username,pwd));
	}
	
  /*initFtp()
   * To create a new class of FtpUtil.
   * The implementation of Ftp function can be found in FtpUtil.java 
   */
  public static void initFtp()
	{
		ftp=new FtpUtil();
		Logger("Ftp client initialized.");
	}
	
  /*connectFtp()
   * To connect to ftp server.
   */
	public static void connectFtp()
	{
		ftp.connectServer(servIP,username,pwd,"");
		Logger("Ftp connected successfully.");
	}
	
  /*getInfoFromFtp()
   * To download the arrange.info file to local disk,
   * then analysis it and set value for week, lecture, lesson.
   */
	public static void getInfoFromFtp()
	{
		try {
			ftp.download(arrangeFileName,arrangeFileName);
			Logger("Download arrangement file successfully.");
		} catch (Exception e) {
			Logger("Download arrangement file unsuccessfully.");
			e.printStackTrace();
			System.exit(0);
		}
		
		File file=new File(arrangeFileName);
		
		try {
			Scanner sc=new Scanner(file);
			week=sc.nextInt();
			sc.nextLine();
			lecture=sc.nextLine();
			lesson=sc.nextLine();
			sc.close();
			file.delete();
			Logger("Analyse arrangement file successfully.");
		} catch (FileNotFoundException e) {
			Logger("Analyse arrangement file unsuccessfully.");
			e.printStackTrace();
			System.exit(0);
		}
		
	    	
	}
	
	/*showClassInfo()
	 * To print out the basic class information which has just
	 * obtained from ftp server.
	 */
	public static void showClassInfo()
	{
		System.out.println();
		System.out.println("=====================Class Info==========================");
		System.out.println("Week:"+week);
		System.out.println("Lecturer:"+lecture);
		System.out.println("Lesson:"+lesson);
	}
	
    /*showTimeTable()
     * To give out tips for user to input his free time info easily.
     */
	public static void showTimeTable()
	{
	   System.out.println();
	   System.out.println("=======================Time Table========================");
	   for (int i=1;i<=6;i++) 
	   {
		new TimeSets();
		System.out.println(i+"."+TimeSets.weekDay[i]);
	   }
	   System.out.println("You need to input your free time by the cardinal of it. Enter -1 to stop entering.");
	}
	
	/*inputVal()
	 * The core to input time info.
	 */
	public static void inputVal()
	{
		int avlTime;
		//Input name and CHECK IT VALID FIRST.
		do
		{
			avlTime=new Scanner(System.in).nextInt();
			if (avlTime>=1 && avlTime<=6) 
			   time[avlTime]=true; //avail.
			else 
				if (avlTime!=-1) System.out.println("Error input.");
				
		}
		while (avlTime!=-1);
		
	}
	
	/*SaveToFile()
	 * Save the user's time info to a .dat file and later upload it
	 * to ftp server.
	 */
	public static void saveToFile(String fileName) throws FileNotFoundException
	{
	PrintStream print=new PrintStream(new FileOutputStream(new File(fileName)));
	print.println(week);
    for (int i=1;i<=6;i++)
	   print.println(time[i]);
	print.close();
	}
	
	/*uploadFileToFtp()
	 * Upload user's .dat file to ftp server.
	 */
	public static void uploadFileToFtp(String str)
	{
		try {
			ftp.upload(str);
			Logger("Upload your time arrangement file successfully.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger("Upload your time arrangement file unsuccessfully.");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
    /*main()
     * Program run from here.
     */
	public static void main(String[] args) throws Exception
	{
		initProgram();
		initFtp();
		login();
		connectFtp();
		getInfoFromFtp();
		showClassInfo();
		showTimeTable();
		inputVal();
		saveToFile(week+"_"+username+".dat");
		uploadFileToFtp(week+"_"+username+".dat");
	}
}
