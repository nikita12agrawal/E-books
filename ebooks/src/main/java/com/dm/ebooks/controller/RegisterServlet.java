package com.dm.ebooks.controller;

import com.dm.ebooks.dao.RegisterDao;
import com.dm.ebooks.model.Register;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user,pass,first,last,email;
        int status;
        user = request.getParameter("username");
        first = request.getParameter("firstname");
        last = request.getParameter("lastname");
        email = request.getParameter("email");
        System.out.println(email);
        status=Integer.parseInt(request.getParameter("status"));
        pass= request.getParameter("password");
        RegisterDao a1= new RegisterDao(); //dao object to set username password etc call *Dao.java file;
        Register r= a1.RegisterServlet(first,last,user,pass,email,status);
        request.setAttribute("data",r);/*creating object of login*/
        RequestDispatcher req = request.getRequestDispatcher("index.jsp");
        req.forward(request, response);
    }
}
