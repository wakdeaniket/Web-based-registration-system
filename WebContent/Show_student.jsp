<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Info</title>
<style>



body {
	
	display: flex;
	padding: 20px;
	min-height: 100vh;
	position: relative;
	flex-direction: column;
	justify-content: center;
	font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
	background-color: #a06060;
	background: linear-gradient(130deg, #a06060 20%, #aaa 100%) no-repeat center center fixed;
}
table{
margin: auto auto auto 180px;
}
.box{
    padding: 10px;
    margin: 25px 60px;
    box-shadow: $shadow2;
    

}
</style>
</head>
<body>
 <% 
 try{
 Connection conn=null;
 Statement st=null;
 ResultSet rs=null;
 Class.forName("com.mysql.jdbc.Driver").newInstance();
 conn=DriverManager.getConnection("jdbc:mysql://localhost/RegistrationDb","root","root");
 st=conn.createStatement();
 rs=st.executeQuery("select * from StudentDb");
 
 %>
 <TABLE cellpadding="15" border="1" style="background-color:#ffffcc";>
  <TR><TH>ID</TH><TH>NAME</TH><TH>BRANCH</TH><TH>AGE</TH><TH>10th Marks</TH><TH>12th Marks</TH><TH>CATEGEORY</TH></TR>
 
 <%
  while(rs.next()){
 %>
 <TR>
 <TD><%=rs.getInt(1) %></TD>
  <TD><%=rs.getString(2) %></TD>
  <TD><%=rs.getString(3) %></TD>
  <TD><%=rs.getString(4) %></TD>
  <TD><%=rs.getString(5) %></TD>
  <TD><%=rs.getString(6) %></TD>
  <TD><%=rs.getString(7) %></TD>
 </TR>
 <%} %>
 
 <%
 
 rs.close();
 st.close();
 conn.close();
 }
 catch(Exception ex){
 %>
 <% 
 out.println("unable to connect");
 }
 %>
 </TABLE>
</body>
</html>