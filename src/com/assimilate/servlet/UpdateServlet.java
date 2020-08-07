package com.assimilate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		Integer roll_number=Integer.parseInt(request.getParameter("roll_number"));
	    try
	    {
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
	    	PreparedStatement ps=con.prepareStatement("select * from studentdetails where roll_number=?");
	    	ps.setInt(1,roll_number);
	    	ResultSet rs=ps.executeQuery();
	    	rs.next();
	    	Student s1=new Student(rs.getInt("roll_number"), rs.getString("name"), rs.getString("department"),rs.getString("year"),rs.getDouble("contact_number"));
	        response.setContentType("text/html");
	    	PrintWriter pw=response.getWriter();
	        pw.print("<form action='UpdateServlet' method='post'>");
	        pw.print("<table>");
	        pw.print("<tr><td>Roll-Number:</td><td><input type='text' hidden name='roll_number' value='"+s1.getRoll_number()+"'/></td></tr>");
	        pw.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+s1.getName()+"'/></td></tr>");
	        pw.print("<tr><td>Department:</td><td><input type='text' name='department' value='"+s1.getDepartment()+"'/></td></tr>");
	        pw.print("<tr><td>Year:</td><td><input type='text' name='year' value='"+s1.getYear()+"'/></td></tr>");
	        pw.print("<tr><td>Contact_Number</td><td><input type='text' name='contact_number' value='"+s1.getContact_number()+"'/></td></tr>");
	    	pw.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");
	    	pw.print("</table></form>");
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
		int roll_number=Integer.parseInt(request.getParameter("roll_number"));
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
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
		    pw.print("<h1><i>Student Record Updated</i></h1>");
		    pw.print("<br><a href='GetServlet'>View Student Records</a>");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
