package com.dm.ebooks.controller;

import com.dm.ebooks.dao.ChunkInfoDao;
import com.dm.ebooks.model.Chunk;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChunkInfoServlet")
public class ChunkInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String topic,desc;
        int spage,epage,price;
        int bookid;
        String ref;
        ref=request.getParameter("submit");
        if(ref.equals("CANCEL")){
            RequestDispatcher req = request.getRequestDispatcher("publisher.jsp");
            req.forward(request, response);
        }
        topic = request.getParameter("topic");
        desc = request.getParameter("desc");
        price = Integer.parseInt(request.getParameter("price"));
        spage = Integer.parseInt(request.getParameter("spage"));
        epage= Integer.parseInt(request.getParameter("epage"));
        bookid=Integer.parseInt(request.getParameter("bookid"));
        ChunkInfoDao c= new ChunkInfoDao(); //dao object to set username password etc call *Dao.java file;
        Chunk ch= c.ChunkInfoServlet(topic,spage,epage,bookid,desc,price);
        request.setAttribute("data",ch);
        System.out.println(ref);
        if(ref.equals("SUBMIT AND ADD MORE")) {
            RequestDispatcher req = request.getRequestDispatcher("getchunkinfo.jsp");
            req.forward(request, response);
        }

      else {
            RequestDispatcher req = request.getRequestDispatcher("publisher.jsp");
            req.forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
