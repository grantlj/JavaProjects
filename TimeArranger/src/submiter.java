
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class submiter {
	
	static boolean[] time=new boolean[7];
	static FtpUtil ftp;
	static String servIP="127.0.0.1";
	
	static String username;
	static String pwd;
	
	static String arrangeFileName="arrange.info";
	static int week;
	static String lecture;
	static String lesson;
	
    public static void Logger(String info)
    {
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	System.out.println(df.format(new Date())+"---->"+info);
    }
	
	public static void initProgram()
	{
	  for (int i=0;i<7;i++)
		time[i]=false;  
	  Logger("Program initialized.");
	}
	
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
	

  public static void initFtp()
	{
		ftp=new FtpUtil();
		Logger("Ftp client initialized.");
	}
	
	public static void connectFtp()
	{
		ftp.connectServer(servIP,username,pwd,"");
		Logger("Ftp connected successfully.");
	}
	
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
	
	public static void showClassInfo()
	{
		System.out.println();
		System.out.println("=====================Class Info==========================");
		System.out.println("Week:"+week);
		System.out.println("Lecturer:"+lecture);
		System.out.println("Lesson:"+lesson);
	}
	
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
	
	public static void saveToFile(String fileName) throws FileNotFoundException
	{
	PrintStream print=new PrintStream(new FileOutputStream(new File(fileName)));
	print.println(week);
    for (int i=1;i<=6;i++)
	   print.println(time[i]);
	print.close();
	}
	
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
