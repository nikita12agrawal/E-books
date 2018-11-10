package com.dm.ebooks.controller;

import com.dm.ebooks.dao.BookInfoDao;
import com.dm.ebooks.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
@javax.servlet.annotation.MultipartConfig
public class BookInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession newSession = request.getSession(false);

        int  publisherid=0;
        if(newSession!=null) {
            //  String  p= newSession.getAttribute("publisherid");
             publisherid=(Integer)(newSession.getAttribute("personId"));
            //System.out.println(newSession.getAttribute("publisherid"));
        }
        else

            response.sendRedirect("index.jsp");


        final Part filePart = request.getPart("file");
        String name, desc;
        float price;
        int pages;
        name = request.getParameter("bname");
        price = Float.parseFloat(request.getParameter("bprice"));
        desc = request.getParameter("bdesc");
        pages = Integer.parseInt(request.getParameter("bpage"));

        InputStream pdfFileBytes = null;
        final PrintWriter writer = response.getWriter();

        try {

            if (!filePart.getContentType().equals("application/pdf")) {
                writer.println(" Invalid File");
                return;
            }


            pdfFileBytes = filePart.getInputStream();  // to get the body of the request as binary data

            final byte[] bytes = new byte[pdfFileBytes.available()];
            pdfFileBytes.read(bytes);  //Storing the binary data in bytes array.
            BookInfoDao a1 = new BookInfoDao(); //dao object to set username password etc call *Dao.java file;
            Book r = a1.BookInfoServlet(name, price, pages, desc, bytes,publisherid);
            request.setAttribute("data",r);/*creating object of book*/
            RequestDispatcher req = request.getRequestDispatcher("publisher.jsp");
            req.forward(request, response);


        }
        catch(Exception e){
            System.out.println(e);
        }

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
