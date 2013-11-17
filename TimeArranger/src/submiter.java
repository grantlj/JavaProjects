
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.FileInputStream; 
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import sun.net.*; 
import sun.net.ftp.FtpClient;

class TimeSets{
	static int SAT_NOON=1;static int SUN_NOON=4;
	static int SAT_ANOO=2;static int SUN_ANOO=5;
	static int SAT_EVEN=3;static int SUN_EVEN=6;
	public final static String[] weekDay=new String[7];
	static
	{
		weekDay[SAT_NOON]="Saturday 8 am - 12 am";
		weekDay[SAT_ANOO]="Saturday 14 pm - 18 pm";
		weekDay[SAT_EVEN]="Saturday 19 pm - 22 pm";
		weekDay[SUN_NOON]="Sunday 8 am - 12 am";
		weekDay[SUN_ANOO]="Sunday 14 pm - 18 pm";
		weekDay[SUN_EVEN]="Sunday 19 pm - 22 pm";
		
	}
}

class MemberSets{
	private final static String[] Name=new String[12];
	private final static String[] Pwd=new String[12];
	static 
	{
		Name[0]="admin";           Pwd[0]="ADMIN";
		Name[1]="Liu Jiang";       Pwd[1]="2013210294";
		Name[2]="Qiao Nan";        Pwd[2]="2013211453";
		Name[3]="Gu Ruiqin";       Pwd[3]="2013214104";
		Name[4]="Deng Jie";        Pwd[4]="2013213733";
		Name[5]="Wang Jingjing";   Pwd[5]="2013212028";
		Name[6]="Wang Hexing";     Pwd[6]="2013211619";
		Name[7]="Quan Meng";       Pwd[7]="2013210261";
		Name[8]="Yan Youyu";       Pwd[8]="2013210315";
		Name[9]="Huang Ying";      Pwd[9]="2013213929";
		Name[10]="Hao Yu";         Pwd[10]="2013213808";
		Name[11]="Zhai Dongyan";   Pwd[11]="2013210102";
	}
	public boolean inNameList(String x,String y)
	{
		boolean bool=false;
		for (int i=0;i<12;i++)
			if (Name[i].toUpperCase().equals(x.toUpperCase()) && Pwd[i].equals(y.toUpperCase())) 
			{
				bool=true;
				break;
			}
		return bool;
	}
}

class FtpUtil 
{
	FtpClient ftpClient;
	public void connectServer(String server,String user,String password, String path) 
	{
	    try
	    {
		ftpClient=new FtpClient();
		ftpClient.openServer(server);
		ftpClient.login(user,password);
		if (path.length()!=0) ftpClient.cd(path);
		ftpClient.binary();
	    } catch (Exception e)
	    {
	    	e.printStackTrace();
	    	System.exit(0);
	    }
	}
	
	private long upload(String filename,String newname) 
	{
		long result=0;
		TelnetOutputStream os=null;
		FileInputStream is=null;
		try
		{
			java.io.File file_in=new java.io.File(filename);
			if (!file_in.exists()) return -1;   //File not exists.
			os=ftpClient.put(newname);
			result=file_in.length();           //Upload ok, return size.
			is=new FileInputStream(file_in);
			byte[] bytes=new byte[1024];
			int c;
			while ((c=is.read(bytes))!=-1) 
			{
				os.write(bytes,0,c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		finally{
			if (is!=null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
		    if(os!=null)
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(0);
				}
		}

	return result;
	}
	
	public long upload(String filename) throws Exception
	{
		return upload(filename,filename);
	}
	

	public long download(String filename,String newfilename) throws Exception
	{
		long result=0;
		TelnetInputStream is=null;
		FileOutputStream os=null;
		try
		{
			is=ftpClient.get(filename);
			java.io.File outfile=new java.io.File(newfilename);
			os=new FileOutputStream(outfile);
			byte[] bytes=new byte[1024];
			int c;
			while ((c=is.read(bytes))!=-1)
			{
				os.write(bytes,0,c);
				result+=c;
			}
		} catch(IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally {
			if (is!=null)
				is.close();
		    if (os!=null)
		    	os.close();
		}
		return result;
	}
	
	public void closeServer() throws IOException
	{
		try
		{
			if (ftpClient!=null)
				ftpClient.closeServer();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
 
}

public class submiter {
	
	static boolean[] time=new boolean[7];
	static FtpUtil ftp;
	static String servIP="127.0.0.1";
	
	static String username;
	static String pwd;
	
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
			ftp.download("arrange.info", "arrange.info");
			Logger("Download arrangement file successfully.");
		} catch (Exception e) {
			Logger("Download arrangement file unsuccessfully.");
			e.printStackTrace();
			System.exit(0);
		}
		
		File file=new File("arrange.info");
		
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
    for (int i=0;i<7;i++)
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
