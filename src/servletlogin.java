

import java.io.IOException;



import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class servletlogin
 */
@WebServlet("/servletlogin")
public class servletlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		
		String op=request.getParameter("operation");

		PrintWriter out=response.getWriter();
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/RegistrationDb","root","root");
		Statement s=conn.createStatement();
	
		System.out.println(op);
		if(op.equals("Admin-login"))
		{
			String name=request.getParameter("username");
			String password=request.getParameter("password");
			if(name.equals("aniket") && password.equals("aniket123"))
			{
				response.sendRedirect("Admin_portal.html");
			}
			else
				response.sendRedirect("Admin_login.html");
		}

		else if(op.equals("Add User"))
		{
		int status=0;	
		String name=request.getParameter("usr_name");
		String password=request.getParameter("usr_pwd");
		String age=request.getParameter("usr_age");
		String email=request.getParameter("usr_email");
		String contact=request.getParameter("usr_contact");
			PreparedStatement ps=conn.prepareStatement("insert into UserDb(name,password,email,age,contact) values(?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,password);
			ps.setString(3,email);
			ps.setString(4, age);
			ps.setString(5,contact);
		
			status=ps.executeUpdate();
			if(status>0)
			{
				response.sendRedirect("Admin_portal.html");
			}
			else
			{
				response.sendRedirect("Add_user.html");
			}
		}
		else if(op.equals("Delete User"))
		{
			int status=0;
		    String uid=request.getParameter("Usr_id");
			int id=Integer.parseInt(uid);

			PreparedStatement ps=conn.prepareStatement("delete from UserDb where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			System.out.println(status);
			if(status>0)
			{
				response.sendRedirect("Admin_portal.html");

			}
			else
			{
				response.sendRedirect("Delete_user.html");
			}
			
		}
		
		else if(op.equals("User-login"))
		{boolean flag=false;
		 String name=request.getParameter("username");
		 String password=request.getParameter("password");

		 
		PreparedStatement ps=conn.prepareStatement("select * from UserDb where name=? and password=?");
		ps.setString(1,name);
		ps.setString(2,password);
		ResultSet rs=ps.executeQuery();
		flag=rs.next();
		if(flag)
			{
	            
				response.sendRedirect("UserPortal.html");
				

			}
		else
			response.sendRedirect("User_Login.html");
		}
		else if(op.equals("Add Student"))
		{ int status=0;
			String name=request.getParameter("Std_name");
			String department=request.getParameter("Std_dept");
			String age=request.getParameter("Std_age");
			String x=request.getParameter("Std_tn");
			String xii=request.getParameter("Std_tw");
			String categeory=request.getParameter("Std_cat");


			PreparedStatement ps=conn.prepareStatement("insert into StudentDb(name,Department,age,x,xii,categeory) values(?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,department);
			ps.setString(3,age);
			ps.setString(4,x);
			ps.setString(5,xii);
			ps.setString(6,categeory);

			status=ps.executeUpdate();
			if(status>0)
			{
				response.sendRedirect("UserPortal.html");
			}
			else
			{
				response.sendRedirect("Add_student.html");
			}
		}
		else if(op.equals("Delete Student"))
		{
			int status=0;
		    String sid=request.getParameter("Std_id");
			int id=Integer.parseInt(sid);

			PreparedStatement ps=conn.prepareStatement("delete from StudentDb where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			if(status>0)
			{
				response.sendRedirect("UserPortal.html");
			}
			else
			{
				response.sendRedirect("Delete_student.html");

			}
			
		}
		else if(op.equals("Add Staff"))
		{ int status=0;
			String name=request.getParameter("Stf_name");
			String age=request.getParameter("Stf_age");
			String qualification=request.getParameter("Stf_Qual");
			String department=request.getParameter("Stf_dept");
			String email=request.getParameter("Stf_email");
			String contact=request.getParameter("Stf_contact");
			


			PreparedStatement ps=conn.prepareStatement("insert into StaffDb(name,age,qualification,department,email,contact) values(?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,age);
			ps.setString(3,qualification);
			ps.setString(4,department);
			ps.setString(5,email);
			ps.setString(6,contact);

			status=ps.executeUpdate();
			if(status>0)
			{
				response.sendRedirect("UserPortal.html");
			}
			else
			{
				response.sendRedirect("Add_staff.html");
			
			}
		}
		else if(op.equals("Delete Staff"))
		{
			int status=0;
		    String sid=request.getParameter("Stf_id");
			int id=Integer.parseInt(sid);

			PreparedStatement ps=conn.prepareStatement("delete from StaffDb where id=?");
			ps.setInt(1,id);
			status=ps.executeUpdate();
			if(status>0)
			{
				response.sendRedirect("UserPortal.html");
			}
			else
			{
				response.sendRedirect("Delete_staff.html");
			}
			
		}
		
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
