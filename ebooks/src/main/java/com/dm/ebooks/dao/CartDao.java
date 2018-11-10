package com.dm.ebooks.dao;

import com.dm.ebooks.model.Cart;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.RequestDispatcher;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class CartDao {
    public void CartServlet(int userid,int chunkid){
        Cart c= new Cart();
        try { System.out.println("inside cartdao");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps1=con.prepareStatement("SELECT * from cart where userId='"+userid+"' and chunkId='"+chunkid+"'");
            ResultSet rs=ps1.executeQuery();
            if(!rs.next()){
            PreparedStatement ps=con.prepareStatement("INSERT INTO cart(userId,chunkId) values(?,?)");
            ps.setInt(1,userid);
            ps.setInt(2,chunkid);
            ps.executeUpdate();
            }
            con.close();
            // st.executeUpdate("INSERT INTO logintable(firstName,lastName,userName,password,email,status) VALUES ('"+first+",''"+last+",''"+user+",''"+pass+",''"+email+",''"+status+",')");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Collection<Cart> getCartItem(int userid) {
        Cart c = new Cart();
        try {
            System.out.println("inside getcartitem");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("SELECT * from cart WHERE userId='"+userid+"'");
            final List<Cart> cart = new ArrayList<Cart>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = cartBuilder(rs);
                cart.add(c);
            }
            con.close();
            return cart;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public Cart cartBuilder(ResultSet rs) throws NullPointerException, SQLException {
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
        final int chunkId=rs.getInt("chunkId");
        final int userId=rs.getInt("userId");

        System.out.println("cartbuilder");

        return new Cart( userId,chunkId);
    }

    public void deleteById(int chunkId,int userId){
        try {
            System.out.println("inside deleteById");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("DELETE  from cart WHERE chunkId='"+chunkId+"' and userId='"+userId+"'");
                         ps.executeUpdate();

        }catch(Exception e){
           System.out.println(e);
        }
    }
    public String getPathById(int chunkId){
        try {
            System.out.println("inside getPathById");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("SELECT path from chunkinfo WHERE chunkId='"+chunkId+"' ");
           ResultSet rs= ps.executeQuery();
           if(rs.next()){
               String path1=rs.getString("path");
               return path1;
           }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public int insertIntoMyorders(int userId, String path,float price,String title){
        try {
            System.out.println("inside insertIntoMyOrders");
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps = con.prepareStatement("INSERT into myorders(userId,path,price,booktitle) VALUES (?,?,?,?)");
            ps.setInt(1,userId);
            ps.setString(2,path);
            ps.setFloat(3,price);
            ps.setString(4,title);
            ps.executeUpdate();
            //ps.getInt()
            PreparedStatement ps1 = con.prepareStatement("SELECT max(id) as maxid FROM myorders ");
            ResultSet rs=ps1.executeQuery();
            int orderId=0;
            if(rs.next())
             orderId=rs.getInt("maxid");
            con.close();
            return orderId;
        }catch (Exception e){
       System.out.println(e);
        }
        return -1;
    }


    public int mergePdfFiles(List<InputStream> inputPdfList,
                              OutputStream outputStream,int userId,String path,float price,String title) throws Exception{

        //Create document and pdfReader objects.

      //  System.out.println(path);
        Document document = new Document();
        List<PdfReader> readers =
                new ArrayList<PdfReader>();
        int totalPages = 0;

        //Create pdf Iterator object using inputPdfList.
        Iterator<InputStream> pdfIterator =
                inputPdfList.iterator();

        // Create reader list for the input pdf files.
        while (pdfIterator.hasNext()) {
            InputStream pdf = pdfIterator.next();
            PdfReader pdfReader = new PdfReader(pdf);
            readers.add(pdfReader);
            totalPages = totalPages + pdfReader.getNumberOfPages();
        }

        // Create writer for the outputStream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        //Open document.
        document.open();

        //Contain the pdf data.
        PdfContentByte pageContentByte = writer.getDirectContent();

        PdfImportedPage pdfImportedPage;
        int currentPdfReaderPage = 1;
        Iterator<PdfReader> iteratorPDFReader = readers.iterator();

        // Iterate and process the reader list.
        while (iteratorPDFReader.hasNext()) {
            PdfReader pdfReader = iteratorPDFReader.next();
            //Create page and add content.
            while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
                document.newPage();
                pdfImportedPage = writer.getImportedPage(
                        pdfReader,currentPdfReaderPage);
                pageContentByte.addTemplate(pdfImportedPage, 0, 0);
                currentPdfReaderPage++;
            }
            currentPdfReaderPage = 1;
        }

        //Close document and outputStream.
        outputStream.flush();
        document.close();
        outputStream.close();

        System.out.println("Pdf files merged successfully.");
       // path+=outputStream;
       int id= insertIntoMyorders(userId,path,price,title);
          return id;
    }
}

