package com.assimilate.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetServlet
 */
@WebServlet("/GetServlet")
public class GetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
//		HttpSession session=request.getSession();
//		String user=(String)session.getAttribute("UserLoggedIn");
//		if(user==null)
//		{
//			System.out.println("user not logged in, please log in");
//			response.sendRedirect("index.html");
//			return;
//		}
		PrintWriter pw=response.getWriter();
		pw.write("<h1><i>Student Record Table:</i></h1>");
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","root");
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select * from studentdetails");
		pw.println("<table border=\"1\">");
		pw.println("<tr>");
		pw.println("<th>"+"<h3>"+"Roll-No"+"</th>");
		pw.println("<th>"+"<h3>"+"Name"+"</th>");
		pw.println("<th>"+"<h3>"+"Department"+"</th>");
		pw.println("<th>"+"<h3>"+"Year"+"</th>");
		pw.println("<th>"+"<h3>"+"Contact-Number"+"</th>");
		pw.println("<th>"+"<h3>"+"Action"+"</th>");
		pw.println("<th>"+"<h3>"+"Action"+"</th>");
		pw.println("</tr>");
		while(rs.next())
		{
			pw.println("<tr>");
			pw.println("<td>"+"<h3>"+rs.getInt("roll_number")+"</td>");
			pw.println("<td>"+"<h3>"+rs.getString("name")+"</td>");
			pw.println("<td>"+"<h3>"+rs.getString("department")+"</td>");
			pw.println("<td>"+"<h3>"+rs.getString("year")+"</td>");
			pw.println("<td>"+"<h3>"+rs.getDouble("contact_number")+"</td>");
			pw.println("<td>"+"<h3>"+"<a href='UpdateServlet?roll_number="+rs.getInt("roll_number")+"'>update</a></td>");
			pw.println("<td>"+"<h3>"+"<a href='DeleteServlet?roll_number="+rs.getInt("roll_number")+"'>delete</a></td>");
			pw.println("</tr>");			
			}
		pw.println("</table>");
		pw.println("<br><a href='LogoutServlet'>Logout</a>");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	doGet(request, response);
	//}

}
