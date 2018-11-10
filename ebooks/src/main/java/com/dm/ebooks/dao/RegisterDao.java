package com.dm.ebooks.dao;

import com.dm.ebooks.model.Register;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class RegisterDao {
    public Register RegisterServlet(String first, String last, String user, String pass, String email,int status) {
        Register r = new Register();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps=con.prepareStatement("INSERT INTO logintable(firstName,lastName,userName,password,email,status) values(?,?,?,?,?,?)");
            //  ps.setString(1,u.getUname());
            //ps.setString(2,u.getUemail());
            // ps.setString(3,u.getUpass());
            ps.setString(1,first);
            ps.setString(2,last);
            ps.setString(3,user);
            ps.setString(4,pass);
            ps.setString(5,email);
            ps.setInt(6,status);
            ps.executeUpdate();
            // st.executeUpdate("INSERT INTO logintable(firstName,lastName,userName,password,email,status) VALUES ('"+first+",''"+last+",''"+user+",''"+pass+",''"+email+",''"+status+",')");
        } catch (Exception e) {
            System.out.println(e);
        }
        return r;
    }
}