<html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />;
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<head>

<script>
    function validate(){
        var username=document.form.username.value;
        var password=document.form.password.value;
        if(username==""){
            alert("Enter username!");
            return false;
        }
        if(password==""){
            alert("Enter Password!");
            return false;
        }
        return true;
    }
</script>
</head>
<div class="jumbotron text-center">
<body >
<h2><strong>WELCOME!!</strong> Enter username and password</h2>
<img src="https://olimex.files.wordpress.com/2013/10/ebookslaptop2.jpg" align="right" class="img-circle" alt="Cinque Terre" width="304" height="236"><br>
<form name="form" accept-charset=utf-8 action="loginServlet" onsubmit="javascript:return validate();">
   USERNAME: <input type="text" name="username"><br><br>
   PASSWORD: <input type="password" name="password"><br><br>
    <input type="submit" class="btn btn-success" value="Login" >
    <tr><td></td><td><a href="register.jsp">new user? Register Here</a></td></tr>

</form>
</body>
</div>
</html>
