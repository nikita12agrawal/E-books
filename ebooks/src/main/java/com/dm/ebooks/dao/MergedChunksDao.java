package com.dm.ebooks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static java.sql.DriverManager.getConnection;

public class MergedChunksDao {
    public void insertIntoMergedChunks(int chunkId,int orderId){
      try
          {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
        Statement st = con.createStatement();
              PreparedStatement ps=con.prepareStatement("INSERT INTO mergedchunks(chunkId,orderId) values(?,?)");
              ps.setInt(1,chunkId);
              ps.setInt(2,orderId);
                 ps.executeUpdate();
          }
    catch(Exception e){
          System.out.println(e);
    }
    }
}
