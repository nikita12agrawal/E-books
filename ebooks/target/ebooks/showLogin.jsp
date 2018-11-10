<%@ page import="com.dm.ebooks.model.Login" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 30/03/2018
  Time: 4:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<head>
    <title>Loginpage</title>
</head>
<body bgcolor="#ffe4c4">
<div class="jumbotron text-center">
<%
    Login a= (Login) request.getAttribute("data");
    if(a.getUsername()==null){
        out.println("Invalid username or password");
     response.sendRedirect("index.jsp");
    }
    else
    out.println("Welcome "+a.getUsername());
          %>
<form action="logoutServlet" method="post">
    <input type="submit"  value="LOGOUT" name="logout"></form>
</div>
</body>
</html>
