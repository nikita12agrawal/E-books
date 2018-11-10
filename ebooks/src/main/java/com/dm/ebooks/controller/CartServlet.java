package com.dm.ebooks.controller;

import com.dm.ebooks.dao.CartDao;
import com.dm.ebooks.model.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

@WebServlet(name = "CartServlet")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        int userid=(Integer)session.getAttribute("personId");
        System.out.println(userid+"cartservlet");
       // request.get
      //  String[] results = request.getParameterValues("chunkName");
        //System.out.println(results[0]);
       String ref=request.getParameter("submit");
       System.out.println(ref+"  button clicked");
       if(ref.equals("cancel")){
           RequestDispatcher req = request.getRequestDispatcher("user.jsp");
           req.forward(request, response);
       }
       else {
           Map params = request.getParameterMap();
           Iterator i = params.keySet().iterator();
              // int price=0;
           while (i.hasNext()) {
               String key = (String) i.next();
               String val = ((String[]) params.get(key))[0];
               System.out.println("Key: " + key);
               System.out.println("Val: " + val);
               try{
                   int v=Integer.parseInt(val);
               }catch(Exception e){
                   break;
               }
               CartDao c = new CartDao();
               //Cart c1 =
               c.CartServlet(userid, Integer.parseInt(val));
               //System.out.println("inside cart servlet "+c1.getUserId() + " " + c1.getChunkId());
               //request.setAttribute("data",c1);/*creating object of login*/

           }
           RequestDispatcher req = request.getRequestDispatcher("user.jsp");
           req.forward(request, response);
       }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
