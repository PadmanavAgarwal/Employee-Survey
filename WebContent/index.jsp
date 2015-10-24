<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.util.ArrayList,Login.UserBean" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ultimate</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/bootstrap.min.css" >
<style>


.na{
font-size:22px;
}

div.stars {
  /width: 270px;
  display: inline-block;
}

input.star { display: none; }

label.star {
  float: right;
  padding-left: 10px;
  font-size: 22px;
  color: #444;
  transition: all .2s;
}

input.star:checked ~ label.star:before {
  content: '\f005';
  color: #FD4;
  transition: all .25s;
}

input.star-5:checked ~ label.star:before {
  color: #FE7;
  text-shadow: 0 0 20px #952;
}

input.star-1:checked ~ label.star:before { color: #F62; }

label.star:hover { transform: rotate(-15deg) scale(1.3); }

label.star:before {
  content: '\f006';
  font-family: FontAwesome;
}
</style>
</head>

<body>
<%
response.setHeader("Cache-Control", "no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0");
response.setHeader("Expires", "Fri, 31 Dec 1998 12:00:00 GMT");
%>
<%if (session.getAttribute("currentSessionUser")==null){
	response.sendRedirect("login.jsp");
   }
   
   else
   {   UserBean user=(UserBean)session.getAttribute("currentSessionUser");
       
	   //pageContext.setAttribute("questions", questions);
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
if(!user.hasAttempted())
{
ArrayList<ArrayList<String>> questions = (ArrayList<ArrayList<String>>)session.getAttribute("questions");
if(questions == null)response.sendRedirect("sorry.jsp");

%>
<div class="container">
      <h1>Survey Form</h1>
      <br/><br/>
	  <form class="form-horizontal" action="surveySubmit" method="post">
	    
	   
	   <c:forEach items="${questions}" var="current">
         <div class="form-group">
	   <div class="col-md-8 lead">
	  <c:out value="${current.get(0)}" />. <c:out value="${current.get(1)}" />
	   </div>
        <div class="col-md-4">
	    <div class="stars">
	    <input class="star star-5" id="star-${current.get(0)}-5" type="radio" value="5" name="ques${current.get(0)}" />
	    <label class="star star-5" for="star-${current.get(0)}-5"></label>
	    <input class="star star-4" id="star-${current.get(0)}-4" type="radio" value="4" name="ques${current.get(0)}"/>
	    <label class="star star-4" for="star-${current.get(0)}-4"></label>
	    <input class="star star-3" id="star-${current.get(0)}-3" type="radio" value="3" name="ques${current.get(0)}"/>
	    <label class="star star-3" for="star-${current.get(0)}-3"></label>
	    <input class="star star-2" id="star-${current.get(0)}-2" type="radio" value="2" name="ques${current.get(0)}"/>
	    <label class="star star-2" for="star-${current.get(0)}-2"></label>
	    <input class="star star-1" id="star-${current.get(0)}-1" type="radio" value="1" name="ques${current.get(0)}"/>
	    <label class="star star-1" for="star-${current.get(0)}-1"></label>
	    <input class="na" id="star-${current.get(0)}-0" type="radio" value="0" name="ques${current.get(0)}" checked="checked"/>
	    <label class="na" for="star-${current.get(0)}-0">N/A</label>
	    
	    </div>
	    </div>
	   
	  </div>
      </c:forEach>
	   
	  
	  <div class="form-group">
	  </div>
	  <div class="form-group">
	  <input class="col-md-6 col-md-offset-3 btn btn-primary" type="submit" value="Submit Feedback"/>
	  </div>
	  </form>
</div>
 <script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<% }
     else {
%>   	 
       <div class="container">
       <div class="alert alert-success lead">
       <Strong>Thanks!</Strong> You have already completed this survey!
       </div>
       </div>
 <%
     }
  }	   
   %>
</body>
</html>