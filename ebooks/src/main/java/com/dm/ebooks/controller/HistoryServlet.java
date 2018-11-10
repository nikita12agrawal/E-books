package com.dm.ebooks.controller;

import com.dm.ebooks.dao.CartDao;
import com.dm.ebooks.dao.MyOrdersDao;
import com.dm.ebooks.model.Cart;
import com.dm.ebooks.model.MyOrders;
import com.mysql.cj.api.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "HistoryServlet")
public class HistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       HttpSession session=request.getSession();

        int userId=(Integer)session.getAttribute("personId");
        MyOrdersDao md=new MyOrdersDao();
       // MyOrders myorder=new MyOrders();

        Collection<MyOrders> mo= md.showHistory(userId);
        request.setAttribute("MyOrders",mo);
        RequestDispatcher rd=request.getRequestDispatcher("orders.jsp");
        rd.forward(request,response);

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
