package httpserv;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;



public class login extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession hs=req.getSession(true);
		String u=(String) hs.getAttribute("username");
		String p=(String) hs.getAttribute("passwd");
		
		if (hs.getAttribute("passed")!="yes")
		{
			//The very first login or wrong login. we dont know whether it is valid.
			
			hs.setAttribute("passed", "unknown");
		
		resp.setContentType("text/html;charset=gbk");
		try 
		{
		  PrintWriter pw=resp.getWriter();
		  pw.println("<html>");
		  pw.println("<head>");
		  pw.println("<meta charset=\"utf-8\">");
		  pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css.css\">");
		  pw.println("</head>");
		  pw.println("<body>");
		  pw.println("<div class=\"top\"></div>");
		  pw.println("<div class=\"middle\">");
		  pw.println("<div class=\"besure\">��½��֤</div>");
		  
		  pw.println("<form action=view method=post>");
		  pw.println("<div class=\"username\">");
		  
		
		  pw.println("�û���:<input class=\"usernameinput\" type=text name=username>");
		
		  pw.println("</div>");
		  
		  pw.println("<div class=\"userkey\">");
		 
		  pw.println("���루ѧ�ţ���<input type=password name=passwd>");
		
		  pw.println("</div>");
		  
		  pw.println("<div class=\"login\">");
		  pw.println("<input class=\"createbutton\" type=submit value=\"��½\">");
		  pw.println("<input class=\"createbutton\" type=reset name=re value=\"����\">");
		 
		
		  pw.println("</div>");
		  pw.println("</div>");
		  pw.println("</form>");
		  pw.println("<div class=\"bottom\"></div>");
		  pw.println("</body>");
		  
		  pw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		}
		else
		    //valid.
		    resp.sendRedirect("view?username="+u+"&passwd="+p);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	

}
