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
		resp.setContentType("text/html;charset=gbk");
		try 
		{
		  PrintWriter pw=resp.getWriter();
		  pw.println("<html>");
		  pw.println("<body>");
		  pw.println("<h>��½��֤</h>");
		  pw.println("<form action=view method=post>");
		  pw.println("�û���:<input type=text name=username><br>");
		  pw.println("���루ѧ�ţ���<input type=password name=passwd><br>");
		  pw.println("<input type=submit value=��½>");
		  pw.println("<input type=reset name=re value=����><br>");
		  pw.println("</form>");
		  pw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
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
