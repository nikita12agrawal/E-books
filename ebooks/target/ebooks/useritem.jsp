<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 6/4/18
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.dm.ebooks.model.Login" %>
<%@ page import="com.dm.ebooks.model.Chunk" %>
<%@ page import="com.dm.ebooks.model.Book" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.dm.ebooks.dao.BookInfoDao" %>
<%@ page import="com.dm.ebooks.model.Book" %>
<%@ page import="java.io.PrintWriter" %><%--
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
   int id=-1;
    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("login.jsp");
    }
    else

        user= (String) session.getAttribute("username");
         id=(Integer)session.getAttribute("personId");
%>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="cart.js"></script>
    <link rel="stylesheet" href="button.css">

</head>

login successful as  user <%=user%>
<%  BookInfoDao bd = new BookInfoDao();
Collection<Chunk> chunks = (Collection<Chunk>) request.getAttribute("cartItem"); %>
<body>
<div class="jumbotron text-center">
<h3>My All Orders</h3>

    <form id="addtocart" action="CartServlet" method="post">
<table border="1">
    <tr>

        <th>TOPIC NAME</th>
        <th>BOOK NAME</th>
        <th>CHUNK PRICE</th>
        <th>BOOK PRICE</th>
        <th>PDF LINK</th>

    </tr>
    <%int i=0;
        for (Chunk c: chunks) {%>
    <tr>

        <td><%=c.getTopic()%></td>
        <td><%=bd.getById(c.getBookId()).getBookName()%></td>
        <td><%=c.getPrice()%></td>
        <td><%=bd.getById(c.getBookId()).getPrice()%></td>
        <td>
            <a href="books/<%=c.getTopic()%>_<%=c.getBookId()%>.pdf">link</a>
        </td>

        <td>
            <input type="checkbox" value="<%=c.getChunkId()%>" class="check" name="chunkName<%=i++%>" id="chunkName<%=i%>" >
        </td>


    </tr>

    <% } %>
</table>
    <input type="submit" id="btn" name="submit" class="btn btn-success" value="Add to cart" disabled="disabled" ><br>
        <input type="submit" name="submit" value="cancel" >

</form>

<form action="logoutServlet" method="post">
    <label class="logoutLblPos">
        <input type="submit" class="btn btn-default" value="LOGOUT" name="logout"></label></form>
</div>
</body>
</html>
