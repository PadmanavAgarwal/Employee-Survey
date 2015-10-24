<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,Login.UserBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<%
response.setHeader("Cache-Control", "no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0");
response.setHeader("Expires", "Fri, 31 Dec 1998 12:00:00 GMT");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<%if (session.getAttribute("currentSessionUser")==null){
	response.sendRedirect("login.jsp");
   }
else {
	UserBean user = (UserBean)session.getAttribute("currentSessionUser");
	if(user.getPosition()<=0)
	{
		out.println("<h1>Restricted Area</h1>");
		response.sendRedirect("index.jsp");
	}
	else if(session.getAttribute("ratingMap")==null)
	{
		out.println("<h1>Unable to Access</h1>");
		response.sendRedirect("index.jsp");
	}
	else
	{
		
%>
<nav class="navbar navbar-inverse " >
  <div class="navbar-header">
     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
     <span class="sr-only">Toggle navigation</span>
     <span class="icon-bar"></span>
     <span class="icon-bar"></span>
     <span class="icon-bar"></span>
     </button>
     <a class="navbar-brand" href="#">Ultimate Solutions</a>
  </div>
           
 <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
 
    <ul class="navbar-nav nav">
    <li><a href="index.jsp">Survey</a></li>
    <li><a href="logout.jsp">Logout</a></li>
    </ul>

</div>
</nav>
<%
HashMap<String, int[]> ratingMap=(HashMap<String,int[]>)session.getAttribute("ratingMap");
Iterator<Map.Entry<String,int[]>> currentMap = ratingMap.entrySet().iterator();  
while(currentMap.hasNext())
{
Map.Entry<String,int[]> pair = (Map.Entry<String,int[]>)currentMap.next();

%>
<div class="container">


<div class="well">
 <% int rating=pair.getValue()[0];
       int total=pair.getValue()[2];
       int nna=pair.getValue()[1];
       float percent= 100*rating/(5*(total-nna));%>
 <div>Question: <%=pair.getKey()%>, Rating:<%=rating%>/<%=(total-nna)%>, N/A: <%=nna%></div>
</div>

<div class="progress">
    
  
  <div class="progress-bar progress-bar-striped active" style="width:<%=String.format("%.1f",percent)%>%;" role="progressbar" aria-valuenow="<%=pair.getValue()[0] %>" aria-valuemin="0" aria-valuemax="100">
    <span class="sr-only"></span>
  </div>
</div></div>
<%
	}
	}
}
%>
</body>
</html>