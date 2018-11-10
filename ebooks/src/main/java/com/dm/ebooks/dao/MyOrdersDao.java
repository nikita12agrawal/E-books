package com.dm.ebooks.dao;

import com.dm.ebooks.model.Cart;
import com.dm.ebooks.model.MyOrders;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class MyOrdersDao {
    public Collection<MyOrders> showHistory(int userId){

     //   final List<MyOrders> mo = new ArrayList<MyOrders>();
        try {
            System.out.println("inside show history");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("SELECT * from myorders WHERE userId='" + userId + "'");
            final List<MyOrders> mo = new ArrayList<MyOrders>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                final MyOrders m = OrderBuilder(rs);
                mo.add(m);
            }
            con.close();
            return mo;
        }
        catch(Exception e){
            System.out.println(e);
        }
     return null;

    }

    public MyOrders OrderBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int userId=rs.getInt("userId");
        System.out.println(userId);
        final String path=rs.getString("path");
        final float price=rs.getFloat("price");
        final String booktitle=rs.getString("booktitle");


        System.out.println("orderbuilder");

        return new MyOrders(price,userId,path,booktitle);
    }



}
