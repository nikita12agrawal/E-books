package com.dm.ebooks.dao;

import com.dm.ebooks.model.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class BookInfoDao {
    public Book BookInfoServlet(String name, float price, int pages, String desc,byte[] pdfFileBytes,int pubid) {
        Book b = new Book();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps=con.prepareStatement("INSERT INTO bookinfo(bookName, price, totalPages,publisherId, description,book) values(?,?,?,?,?,?)");
            //  ps.setString(1,u.getUname());
            //ps.setString(2,u.getUemail());
            // ps.setString(3,u.getUpass());
            ps.setString(1,name);
            ps.setFloat(2,price);
            ps.setInt(3,pages);
            ps.setInt(4,pubid);
            ps.setString(5,desc);
            ps.setBytes(6, pdfFileBytes);
            ps.executeUpdate();
            //saving pdf document to a folder
            PreparedStatement ps1=con.prepareStatement("SELECT bookId,bookName,book from bookinfo where bookId=(SELECT max(bookId) from bookinfo)");
             ResultSet rs= ps1.executeQuery();

            if(rs.next()){
                File file=new File("C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\books\\"+rs.getString("bookName")+".pdf");//creating a new file
                FileOutputStream output=new FileOutputStream(file);//creating object of output stream
                byte[] buffer=new byte[1024];
                InputStream input= rs.getBinaryStream("book");//creating object of inputstream and assigning book to it.
                ByteArrayOutputStream bos=new ByteArrayOutputStream();//to convert byte stream to pdf
                try{
                    for(int read;(read=input.read(buffer))!=-1;){//reading block by block
                        bos.write(buffer,0,read);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
               byte[] bytes=bos.toByteArray();//from stream object to byte array
                output.write(bytes);
                output.flush();
                output.close();
             /*   while(input.read(buffer)>0){
                    output.write(buffer);
                }*/
            }
            rs.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return b;
    }
    private Book bookBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }

        //final int bookId = rs.getInt("bookId");
        final String bookName = rs.getString("bookName");
        final float price = rs.getFloat("price");
        final int totalPages = rs.getInt("totalPages");
        final int publisherId = rs.getInt("publisherId");
        final String description = rs.getString("description");


        return new Book( bookName, price, totalPages,publisherId,description);
    }


    public Book getById(int bookId) {
        try {
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks","root","");
            // final List<Book> books = new ArrayList<Book>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bookinfo WHERE bookId=?");
            ps.setInt(1,bookId );
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //final String bookName=rs.getString("bookName");
                final Book b = bookBuilder(rs);
                return b;
            }
            con.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }
