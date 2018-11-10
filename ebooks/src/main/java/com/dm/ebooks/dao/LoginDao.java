package com.dm.ebooks.dao;
import com.dm.ebooks.model.Login;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

//import static java.sql.DriverManager.getConnection;

public class LoginDao {
    public Login loginServlet(String username, String password) {
        Login a = new Login();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= getConnection("jdbc:mysql://localhost:3306/customebooks","root","");
            Statement st= con.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select * from logintable where userName='"+username+"' and password='"+password+"'");
            if(rs.next()){
                a.setUsername(rs.getString("userName"));
                a.setPassword(rs.getString("password"));
                a.setStatus(rs.getInt("status"));
                a.setpId(rs.getInt("personId"));
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return a;
    }
}


