<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 06/04/2018
  Time: 6:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<head>
    <link rel="stylesheet" href="button.css">
    <title>Title</title>
</head>
<body>
<div class="jumbotron text-center">
<a href="getbookinfo.jsp">Enter book details</a>
<a href="getchunkinfo.jsp">Enter chunk details</a><br>
    <form action="logoutServlet" method="post">
        <label class="logoutLblPos">
            <input type="submit" class="btn btn-default" value="LOGOUT" name="logout"></label></form>
</div>
</body>
</html>
