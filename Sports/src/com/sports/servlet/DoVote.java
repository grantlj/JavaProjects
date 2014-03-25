package com.sports.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sports.util.IpListOperator;
import com.sports.util.RankListOperator;

public class DoVote extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DoVote() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

       this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8");
	   PrintWriter pw=response.getWriter();
	   
	   boolean flag=true;
	   int id=0;
	   String ip="";
	   
	   try
	   {
	      String idstr=request.getParameter("id");
	      id=Integer.parseInt(idstr);
	   }
	   catch (Exception e)
	   {
		   flag=false;
	   }
	   
	   flag=flag && (id>=1 && id<=28);
	   
	   if (!flag)
		   pw.println("参数异常，投票失败！");
	   
	   else
	   {
	      ip=getRemoteAddress(request);
	      flag=flag && IpListOperator.checkIp(ip);
	   }
	   
	   if (flag)
	   {
		   flag=flag && RankListOperator.addPoint(id); 
		   if (flag)
		      pw.println("恭喜！IP为："+ip+" 的同学，您已经投票成功！");
		   else
			   pw.println("对不起！投票请求失败！");
	   }
	   else
		   pw.println("对不起！非法投票！");
       
	   pw.flush();
       pw.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
    
	
	 public String getRemoteAddress(HttpServletRequest request){    
	     String ip = request.getHeader("x-forwarded-for");    
	     if(ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) ip = request.getHeader("Proxy-Client-IP");    
	     if(ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) ip = request.getHeader("WL-Proxy-Client-IP");    
	     if(ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) ip = request.getRemoteAddr();    
	     return ip;    
	 } 
	
}
