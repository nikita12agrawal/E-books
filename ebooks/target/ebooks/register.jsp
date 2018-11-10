<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 30/03/2018
  Time: 6:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<head>
    <title>DETAILS</title>
</head>
<script>
    function validate(){
        var username=document.form.username.value;
        var password=document.form.password.value;
        var firstname=document.form.firstname.value;
        var lastname=document.form.lastname.value;
        var email=document.form.email.value;
        if(username==""){
            alert("Enter Username!");
            return false;
        }
        if(firstname==""){
            alert("Enter firstname!");
            return false;
        }
        if(lastname==""){
            alert("Enter lastname!");
            return false;
        }
        if(email==""){
            alert("Enter email!");
            return false;
        }
        return true;
    }
</script>
<body>
<div class="jumbotron text-center">
<h4>Enter your Details</h4>
<form name="form" action="registerServlet" onsubmit="javascript:return validate();">
FIRSTNAME: <input type="text"  name="firstname"><br><br>
LASTNAME: <input type="text"  name="lastname"><br><br>
USERNAME: <input type="text"  name="username"><br><br>
PASSWORD: <input type="text"  name="password"><br><br>
eMAIL       : <input type="email" padding="5px"  name="email"><br><br>
<INPUT TYPE="radio"  name="status" value="1"/>PUBLISHER
<INPUT TYPE="radio"  name="status" value="0"/>BUYER<br><br>
<input type="submit" class="btn btn-primary" value="Register" >
</form>

</div>
</body>
</html>
