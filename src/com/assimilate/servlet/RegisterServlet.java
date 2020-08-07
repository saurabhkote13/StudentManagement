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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		int roll_number=Integer.parseInt(request.getParameter("roll_number"));
		String name=request.getParameter("name");
		String department=request.getParameter("department");
		String year=request.getParameter("year");
		Double contact_number=Double.parseDouble(request.getParameter("contact_number"));
		Student s1=new Student(roll_number, name, department, year, contact_number);
//		HttpSession session=request.getSession();
//		String user=(String)session.getAttribute("UserLoggedIn");
//		if(user==null)
//		{
//			System.out.println("user not logged in, please log in");
//			response.sendRedirect("index.html");
//			return;
//		}
				
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
			PreparedStatement ps=con.prepareStatement("insert into studentdetails values(?,?,?,?,?)");
			ps.setInt(1,s1.getRoll_number());
			ps.setString(2,s1.getName());
			ps.setString(3,s1.getDepartment());
			ps.setString(4,s1.getYear());
			ps.setDouble(5,s1.getContact_number());
			int rows=ps.executeUpdate();
			System.out.println(rows+":row added");
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			pw.print("<h1><i>Student Registered Successfully</i></h1>");
			pw.println("<br><a href='register.html'>Add Student Record</a>");
			pw.println("<br><a href='GetServlet'>View Student Record</a>");
			pw.println("<br><a href='Delete.html'>Delete Student Record</a>");
			pw.println("<br><a href='LogoutServlet'>Logout</a>");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			
			
		}
	}

}
