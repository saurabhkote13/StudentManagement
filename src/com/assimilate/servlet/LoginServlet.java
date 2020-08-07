package com.assimilate.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.setContentType("text/html");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
			PreparedStatement ps=con.prepareStatement("select * from accounts where username=? and password=?");
			ps.setString(1,username);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String usernamefromdb=rs.getString("username");
				String passwordfromdb=rs.getString("password");
				System.out.println("username from db is: "+usernamefromdb+" password from db is: "+passwordfromdb);
				HttpSession session=request.getSession();
				session.setAttribute("UserLoggedIn",usernamefromdb);
				System.out.println("session id is :"+session.getId());
				response.getWriter().write("<h2><i>welcome :"+usernamefromdb+"</i></h2>");
				//response.sendRedirect("register.html");
				RequestDispatcher rd=request.getRequestDispatcher("register.html");
				rd.include(request,response);
			}
			else
			{
				response.getWriter().write("<h2><i>Incorrect username or Password</i></h2>");
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				rd.include(request,response);
				//response.sendRedirect("index.html");
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
