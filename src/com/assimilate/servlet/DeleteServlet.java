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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session=request.getSession();
//		String user=(String)session.getAttribute("UserLoggedIn");
//		if(user==null)
//		{
//			System.out.println("user not logged in, please log in");
//			response.sendRedirect("index.html");
//			return;
//		}
		int roll_number=Integer.parseInt(request.getParameter("roll_number"));
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
			PreparedStatement ps=con.prepareStatement("delete from studentdetails where roll_number=?");
			ps.setInt(1,roll_number);
			ps.executeUpdate();
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			pw.print("<h1><i>Student Record Deleted</i></h1>");
			pw.print("<br><a href='GetServlet'>View Student Records</a>");
			pw.print("<br><a href='register.html'>Register Student</a>");
			pw.print("<br><a href='Update.html'>Update Student Servlet</a>");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
