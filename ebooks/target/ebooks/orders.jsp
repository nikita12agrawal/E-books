<%@ page import="com.dm.ebooks.model.MyOrders" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 24/4/18
  Time: 2:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<% Collection<MyOrders> mo=(Collection<MyOrders>)request.getAttribute("MyOrders");%>
<h3>my all orders</h3>

<table border="1">
    <tr>

        <th>BOOK TITLE</th>
        <th>PRICE</th>
        <th>BOOK LINK</th>


    </tr>

        <% for (MyOrders m: mo) {%>
    <tr>

        <td><%=m.getBooktitle()%></td>
        <td><%=m.getPrice()%></td>
        <td><a href="books/merged/<%=m.getBooktitle()%>_<%=m.getUserId()%>.pdf">link</a></td>
    </tr>

    <% } %>
</table>
</body>
</html>
