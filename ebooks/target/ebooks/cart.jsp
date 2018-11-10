<%@ page import="java.util.Collection" %>
<%@ page import="com.dm.ebooks.model.Cart" %>
<%@ page import="com.dm.ebooks.model.Book" %>
<%@ page import="com.dm.ebooks.dao.BookInfoDao" %>
<%@ page import="com.dm.ebooks.model.Chunk" %>
<%@ page import="com.dm.ebooks.dao.ChunkInfoDao" %>
<%@ page import="java.sql.*" %>
<%@ page import="static java.sql.DriverManager.getConnection" %>
<%@ page import="java.sql.Statement" %><%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 14/04/2018
  Time: 11:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<!--script>
    $("#table-demo").rowSorter({
            handler         : null,

    tbody           : true,

    tableClass      : 'sorting-table',

    dragClass       : 'sorting-row',

    stickTopRows    : 0,

    stickBottomRows : 0,

    onDragStart     : null,

    onDrop          : null
    });
</script-->
<html>

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
</head>
login as <%=user%>
<!--script type="text/javascript">
    $(function() {
        jQuery.each($("table td"), function() {
            $(this).children(":eq(1)").after($(this).children(":eq(0)"));
        });
    });
</script-->
<form method="post" action="merge" id="myform">
<% Collection<Cart> cart=(Collection<Cart>)request.getAttribute("cartItems");%>
    <table id="table-demo" border="1">
        <tr>
            <th>Topic_Name</th>
            <th>Chunk_Name</th>
            <th>Book_Name</th>
            <th>Chunk_Price</th>
            <th>Pdf_link</th>
            <th>select</th>
        </tr>
        <% int i=0;
         for(Cart c : cart){ String bookname,chunkname;
        ChunkInfoDao chunk=new ChunkInfoDao();
        System.out.println(c.getChunkId());
        Chunk chunk1=chunk.getById(c.getChunkId());

        int bookid=chunk1.getBookId();
        System.out.println(bookid + "hello");
        BookInfoDao b1=new BookInfoDao();
        Book b=b1.getById(bookid);%>
        <tr>
            <td>
            <%=chunk1.getTopic()%>
        </td>
            <td><%=chunk1.getTopic()%></td>
            <td><%=b.getBookName()%></td>
            <td><%=chunk1.getPrice()%></td>
            <td>
                <a href="books/<%=chunk1.getTopic()%>_<%=chunk1.getBookId()%>.pdf">link</a>
            </td>
            <td>
                <input type="checkbox" value="<%=chunk1.getChunkId()%>" class="check" name="chunkName<%=i++%>" id="<%=chunk1.getPrice() %>" >
            </td></tr>
   <% }%>
    </table>


    <!--script>
    $("#table-demo").rowSorter({
    handler: "td.sorter"
    });
    </script-->
   <input type="button" value="SHOW BOOK PRICE" id="jqcc" >
    <span><div id="showPrice" name="price"></div></span>
    <input type="text" name="title" id="title" style="display: none" placeholder="Enter Title">
    <input type="submit" value="CREATE BOOK" name="merge">
    <input type="submit" value="DELETE" name="merge">
    <script>
            $(document).ready(function() {

                $('#jqcc').click(function() {
                    var tableControl= document.getElementById('mytable');
                    var total=0;
                    console.log("inside function");
                    <% float price=0;%>
                    //console.log(total);
                    document.getElementById('title').style.display='block';
                    $('input:checkbox').each(function() {
                        var $this=$(this);
                        if($this.is(":checked")){
                           total=total+parseInt($this.attr("id"));
                        }

                      //var id= parseInt($(this).val());

                         /*String str="<script>document.writeln(id);
                       System.out.println(str);
                       int Id= Integer.parseInt(str);*/

                    });
                    $("#showPrice").html(total);
                   /* var x = document.createElement("TEXTAREA");
                    var t = document.createTextNode(total);
                    x.appendChild(t);
                    document.body.appendChild(x);*/
                    console.log(total);
                    //document.getElementById("showPrice").style.display='block';
                   //total=document.getElementById("showPrice").innerHTML;
                });
            });
    </script>

</form>
</body>
</html>
