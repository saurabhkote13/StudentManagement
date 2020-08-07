package com.assimilate.servlet;

import java.io.IOException;
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
 * Servlet implementation class DeleteServlet2
 */
@WebServlet("/DeleteServlet2")
public class DeleteServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet2() {
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
//		HttpSession session=request.getSession();
//		String user=(String)session.getAttribute("UserLoggedIn");
//		if(user==null)
//		{
//			System.out.println("user not logged in, please log in");
//			response.sendRedirect("index.html");
//			return;
//		}
		Integer roll_number=Integer.parseInt(request.getParameter("roll_number"));
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
			PreparedStatement ps =con.prepareStatement("delete from studentdetails where roll_number=?");
			ps.setInt(1,roll_number);
			ps.executeUpdate();
			response.setContentType("text/html");
			response.getWriter().write("<h1><i>Record Deleted Successfully</i></h1>");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
		
	}

}
