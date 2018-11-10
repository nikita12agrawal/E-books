<%@ page import="java.sql.*" %>

<html>
<head>
    <link rel="stylesheet" href="button.css">
</head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<% HttpSession newSession = request.getSession(false);

    int  publisherid=0;
    if(newSession!=null) {
//  String  p= newSession.getAttribute("publisherid");
publisherid=(Integer)(newSession.getAttribute("personId"));
System.out.println(publisherid);
}
else
response.sendRedirect("index.jsp");


%>
<%
    String driverName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/";
    String dbName = "customebooks";
    String userId = "root";
    String password = "";

    try {
        Class.forName(driverName);
    } catch (ClassNotFoundException e) {
       // out.println("error 1 occurred");
        e.printStackTrace();
    }

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try{
        connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
        statement=connection.createStatement();
        PreparedStatement ps=connection.prepareStatement("SELECT bookId,bookName,totalPages FROM bookinfo where publisherId=?");
        ps.setInt(1,publisherid);
        resultSet=ps.executeQuery();

%>

<script>
    function validate(){
        var topic=document.form.topic.value;
        var spage=document.form.spage.value;
        var epage=document.form.epage.value;
        var desc=document.form.desc.value;
        var price=document.form.price.value;
        if(topic==""){
            alert("Enter topic!");
            return false;
        }
        if(spage==""){
            alert("Enter starting page!");
            return false;
        }
        if(epage==""){
            alert("Enter ending page!");
            return false;
        }
        if(desc==""){
            alert("Enter description!");
            return false;
        }
        if(price==""){
            alert("Enter price!");
            return false;
        }
        if(parseInt(spage)>parseInt(epage)){
            alert("end page should be greater than start page!");
            return false;
        }
        return true;
    }
</script>
<div class="jumbotron text-center">
<form name="form" action="ChunkInfoServlet" method="post" onsubmit="javascript:return validate();">
    SELECT BOOK:<select name="bookid">
    <%while(resultSet.next()){%>
    <option value="<%=resultSet.getInt("bookId")%>"><%=resultSet.getString("BookName") %></option>
    <%

            }

        } catch (Exception e) {
        out.println("error 2 occurred"+e);
            e.printStackTrace();
        }
    %>
</select>
    TOPIC: <input type="text" name="topic" ><br>
    START PAGE: <input type="number" min="1" name="spage"><br><br>
    END PAGE: <input type="number" min="1" name="epage"><br><br>
    PRICE : <input type="number" min="1" name="price"><br><br>
    DESCRIPTION : <input type="text" name="desc"><br><br>
    <input type="submit" name="submit" class="btn btn-info" value="SUBMIT AND ADD MORE">
    <input type="submit" name="submit" class="btn btn-primary" value="SUBMIT">
    <input type="submit" name="submit" class="btn btn-danger" value="CANCEL">

</form>
<form action="logoutServlet" method="post">
    <label class="logoutLblPos">
        <input type="submit" class="btn btn-default" value="LOGOUT" name="logout"></label></form>

</div>
</body>
</html>