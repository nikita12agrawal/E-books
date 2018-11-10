<%@ page import="com.dm.ebooks.model.Login" %>
<%@ page import="com.dm.ebooks.model.Chunk" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 1/4/18
  Time: 4:24 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<% String user=null;
   int user_id=0;
    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("login.jsp");
    }
    else

        user= (String) session.getAttribute("username");
         user_id=(Integer)session.getAttribute("personId");
%>
<head> <link rel="stylesheet" href="button.css"></head>

<body>
<div class="jumbotron text-center">
    login successful as a user  <%=user%>
<form action="add" method="get">
    SEARCH: <input type="text" name="topic"><br><br>
    <input type="submit" value="Search" name="submit" ><br>
    <input type="submit" value="Show cart" name="submit" >
</form>

    <form action="historyServlet" method="post">
        <label class="">
            <input type="submit"  class="btn btn-info" value="history" name="MyOrders"></label></form>

    <form action="logoutServlet" method="post">
    <label class="logoutLblPos">
        <input type="submit" class="btn btn-default" value="LOGOUT" name="logout"></label></form>
</div>
</body>
</html>
