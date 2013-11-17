package httpserv;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class servletor implements Servlet{
    static int week,webcount;
    static String fileName;
	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#getServletConfig()
	 */
	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#getServletInfo()
	 */
	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Servletor is running...");
		webcount=0;
		week=new Scanner(System.in).nextInt();
		fileName="D:\\apache-tomcat-6.0.37-windows-x64\\apache-tomcat-6.0.37\\webapps\\ta\\WEB-INF\\classes\\httpserv\\"+week+".result";
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Servlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		arg0.setCharacterEncoding("utf-8");
		arg1.setContentType("text/html;charset=gbk");
		// TODO Auto-generated method stub
		webcount++;
	    PrintWriter pw=arg1.getWriter();
		System.out.println("New visitor:"+webcount);
		File f=new File(fileName);
		pw.println("<html>"); 
		pw.println(" <head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">");
		pw.println("<body>"); 
		if (!f.exists())
		{
			pw.println("<p>第"+week+" 周计划尚未发布，请稍后查询。 "+"</p>");
		}
		else
		{
		   Scanner sc=new Scanner(f);
			do
			{
				String out=sc.nextLine();
				pw.println("<p>"+out+"</p>");
			}
			while (sc.hasNextLine());
			sc.close();
		
		}
		pw.println("/<body>");
		pw.println("</html>");
		pw.close();	
		
	}
	
	public static void main(String[] args)
	{
		
	}
	
   
}
