package com.dm.ebooks.controller;

import com.dm.ebooks.dao.LoginDao;
import com.dm.ebooks.model.Login;

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

@WebServlet(name = "loginServlet")
public class LoginServletController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user,pass;
        user = request.getParameter("username");
        pass= request.getParameter("password");

        LoginDao a1= new LoginDao(); //dao object to set username password etc call *Dao.java file;
        Login a= a1.loginServlet(user,pass);/*creating object of login*/


        if(a.getUsername()!=null)
        {     HttpSession oldSession=request.getSession(false);
             if( oldSession!=null)
                oldSession.invalidate();
            HttpSession session= request.getSession();
            session.setAttribute("username",a.getUsername());
            session.setAttribute("personId",a.getpId());
            session.setMaxInactiveInterval(30*60);
            request.setAttribute("data",a);
            System.out.println(a.getUsername());
            if(a.getStatus()==0)
            {response.sendRedirect("user.jsp");}
            else
                response.sendRedirect("publisher.jsp");
        }
        else
        {
           // PrintWriter p=response.getWriter();
            //p.print("wrong credentials!!");
           response.sendRedirect("index.jsp");
            JOptionPane.showMessageDialog(null,"wrong credentials");

        }
        /*to fetch data from database writing database connectivity steups*/
        //RequestDispatcher rd= request.getRequestDispatcher("showLogin.jsp");
        //rd.forward(request,response);

    }
}
