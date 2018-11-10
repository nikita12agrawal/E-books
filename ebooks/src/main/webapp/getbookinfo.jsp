<%--
  Created by IntelliJ IDEA.
  User: mypc
  Date: 31/03/2018
  Time: 3:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<head>
    <link rel="stylesheet" href="button.css">
    <title>Enter Book details</title>
</head>
<script>
    function validate(){
        var bname=document.form.bname.value;
        var bprice=document.form.bprice.value;
        var bpage=document.form.bpage.value;
        var bdesc=document.form.bdesc.value;
        if(bname==""){
            alert("Enter Book name!");
            return false;
        }
        if(bprice==""){
            alert("Enter price!");
            return false;
        }
        if(bpage==""){
            alert("Enter total pages!");
            return false;
        }
        if(bdesc==""){
            alert("Enter description!");
            return false;
        }
        return true;
    }
</script>
<body>

<% String user=null;
        int personId;
    if(session.getAttribute("username")==null)
    {
        response.sendRedirect("index.jsp");
    }
    else

        user= (String) session.getAttribute("username");
       //   personId=(Integer) session.getAttribute("personId");
%>
<div class="jumbotron text-center">
login successful as a publisher <%=user%>
<h1>ENTER BOOK DETAILS</h1>
<form name="form" method="post" action="bookinfo" enctype='multipart/form-data' onsubmit="javascript:return validate();">
    BOOK NAME: <input type="text" name="bname"><br><br>
    PRICE: <input type="text" name="bprice"><br><br>
    TOTAL PAGES: <input type="text" name="bpage"><br><br>
    DESCRIPTION: <input type="text" name="bdesc"><br><br>
    UPLOAD FILE:<input type="file" name="file" id="file" />
    <input type="submit" class="btn btn-primary" value="Upload" name="upload" id="upload" />
</form>
    <form action="logoutServlet" method="post">
        <label class="logoutLblPos1">
            <input type="submit" class="btn btn-default" value="LOGOUT" name="logout"></label>
    </form>
    <!--a href="getchunkinfo.jsp">Add chunk for existing book</-->
</div>
</body>
</html>
