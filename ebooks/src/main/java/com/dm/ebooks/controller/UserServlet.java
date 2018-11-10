package com.dm.ebooks.controller;

//import com.dm.ebooks.dao.CartDao;
import com.dm.ebooks.dao.BookInfoDao;
import com.dm.ebooks.dao.CartDao;
import com.dm.ebooks.dao.ChunkInfoDao;
import com.dm.ebooks.model.Book;
import com.dm.ebooks.model.Cart;
import com.dm.ebooks.model.Chunk;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String topic,bookName;
       HttpSession session=request.getSession();
        int userId=(Integer) session.getAttribute("personId");
        //int status;
        //status=Integer.parseInt(request.getParameter("status1"));
        //if(status==0) //search in chunk table

        //  System.out.println(status);
        topic=request.getParameter("topic");
        //request.setAttribute("topic",topic);
        String ref=request.getParameter("submit");
        if(ref.equals("Search")) {
            if (topic == "") {
                response.sendRedirect("user.jsp");
                JOptionPane.showMessageDialog(null, "enter topic name!!");
                return;
            }
           // request.setAttribute("topic",topic);
            ChunkInfoDao cd=new ChunkInfoDao();
            Collection<Chunk> chunks=cd.getTopic(topic);
            if(chunks.isEmpty())
            { //PrintWriter p=response.getWriter();
                //p.print("no such chunk exist try different topic name!!");
                response.sendRedirect("user.jsp");
                JOptionPane.showMessageDialog(null,"no such chunk exist try different topic name!!");

                return;
            }
            for(Chunk c:chunks)
            {
                System.out.println(c.getTopic()+" userservlet");
            }
            request.setAttribute("cartItem",chunks);


            //request.setAttribute("cartInfo",c);
            RequestDispatcher rd=request.getRequestDispatcher("useritem.jsp");
            rd.forward(request,response);
        }


        //String ref=request.getParameter("submit");
        //System.out.println(topic);
        else if(ref.equals("Show cart")){
            CartDao cd1=new CartDao();
            Collection<Cart> cart= cd1.getCartItem(userId);
         /*  for(Cart c : cart){
               ChunkInfoDao chunk=new ChunkInfoDao();
                   Chunk chunk1=chunk.getById(c.getChunkId());
               int bookid=chunk1.getBookId();
               BookInfoDao b1=new BookInfoDao();
               Book b=b1.getById(bookid);
           }*/
          /*  for(Cart c : cart){
                System.out.println(c.getChunkId());
            }*/
            request.setAttribute("cartItems",cart);
            RequestDispatcher rd=request.getRequestDispatcher("cart.jsp");
            rd.forward(request,response);
        }
        }
    }

