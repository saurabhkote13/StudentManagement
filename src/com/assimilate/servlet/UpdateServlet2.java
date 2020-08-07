package com.assimilate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateServlet2
 */
@WebServlet("/UpdateServlet2")
public class UpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 Integer roll_number=Integer.parseInt(request.getParameter("roll_number"));
//	 HttpSession session=request.getSession();
//		String user=(String)session.getAttribute("UserLoggedIn");
//		if(user==null)
//		{
//			System.out.println("user not logged in, please log in");
//			response.sendRedirect("index.html");
//			return;
//		}
	 String name=request.getParameter("name");
	 String department=request.getParameter("department");
	 String year=request.getParameter("year");
	 Double contact_number=Double.parseDouble(request.getParameter("contact_number"));
	 try
	 {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
		 PreparedStatement ps=con.prepareStatement("update studentdetails set name=?,department=?,year=?,contact_number=? where roll_number=?");
		 ps.setString(1,name);
		 ps.setString(2,department);
		 ps.setString(3,year);
		 ps.setDouble(4,contact_number);
		 ps.setInt(5,roll_number);
		 ps.executeUpdate();
		 response.setContentType("text/html");
		 response.getWriter().write("record updated successfully");
		 response.getWriter().write("<br><a href='GetServlet'>View Student Record</a>");
		 
	 }catch(Exception e)
	 {
		 e.printStackTrace();
		 System.out.println(e);
	 }
	}
}
