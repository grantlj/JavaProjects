/*Project Name: Time Arranger
 *Version     : 1.0
 *Author:       Grant Liu
 *              Deng Jie
 *      
 *History     :
 *Version 1.0   2013/11/19     *Fix Bugs.
                               *Update servlet.
 *Version 1.0   2013/11/17     *Finish basic functions. 
 *
 *Brief Introduction:
 *   Time Arranger is a program to simplify the process 
 *   of arranging the time for lecture high efficiently.
 *   The program is based on C/S frame, with the tech of
 *   Ftp and Servlet in Java language.   
 */

/*Module Name: httpserv/servletor
 * Version   : 1.0
 * Brief Introduction:
 *    servletor is a dynamic web server for users to view.
 *    Everytime you visit: http://domain/ta/view, tomcat 
 *    will callback the servletor.java to give out a webpage.
 *    
 */

package httpserv;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.*;

class MemberSets2{
	final static String[] Name=new String[12];
    final static String[] Pwd=new String[12];
	//private final static String[] Tel=new String[12];
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
	
	
	public static boolean inNameList(String x,String y)
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

public class servletor extends HttpServlet{
  
	private static final long serialVersionUID = 1L;
	static int week,webcount;
    static String fileName;
	
    static
    {
		System.out.println("Servletor is running...");
		webcount=0;
		week=new Scanner(System.in).nextInt();
		fileName="D:\\apache-tomcat-6.0.37-windows-x64\\apache-tomcat-6.0.37\\webapps\\ta\\WEB-INF\\classes\\httpserv\\"+week+".result";
		
	}

	
    
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=gbk");

		webcount++;
		System.out.println("WebCounter:"+webcount);
	    PrintWriter pw=resp.getWriter();
	   
	    String user=req.getParameter("username");
	    String pwd=req.getParameter("passwd");
	    
	    if (MemberSets2.inNameList(user, pwd))
	    {
		File f=new File(fileName);
		pw.println("<html>"); 
		pw.println("<head>");
		pw.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");
		pw.println("<style>");
		pw.println("body{");
		pw.println("background:url(\"bg.jpg\");");
		pw.println("height:900px;");
		pw.println("margin:0;");
		pw.println("}");
		pw.println("p{");
		pw.println("magin-top:10px;");
		pw.println("text-align:center;");
		pw.println("font-family:Microsoft YaHei;");
		pw.println("}");
		pw.println("div{");
		pw.println("border:5px rgb(4,66,133) solid;");
		pw.println("border-radius:15px;");
		pw.println("box-shadow:0 0 5px");
		pw.println("position:relative");
		
		pw.println("magin:0 auto;");
		pw.println("width:810px");
		pw.println("height:900px");
		
		pw.println("}");
		pw.println("h3{");
		pw.println("text-align:center;");
		pw.println("font-family:Microsoft YaHei");
		pw.println("color: rgb(94,13,126)");
		
		pw.println("}");
		
		pw.println("</head>");
		pw.println("<body>");
		pw.println("</style>");
		 
		pw.println("<div>");
		if (!f.exists())
		{
			pw.println("<p>第"+week+" 周计划尚未发布，请稍后查询。 "+"</p>");
		}
		else
		{
		   Scanner sc=new Scanner(f);
		   String out=sc.nextLine();
		   pw.println("<h3>"+out+"</h3>");
		   do
			{
				String out2=sc.nextLine();
				pw.println("<p>"+out2+"</p>");
			}
			while (sc.hasNextLine());
			sc.close();
		
		}
		pw.println("/<body>");
		pw.println("</html>");
		pw.close();	
	    }
	    
	    else
	    {
	    	resp.sendRedirect("login");
	    }
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	  doGet(req,resp);
	}
	
	public static void main(String[] args)
	{
		
	}



	
	
   
}
