package com.dm.ebooks.dao;

import com.dm.ebooks.model.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ChunkInfoDao {
    static void splitPdfFile(InputStream inputPdf,
                             OutputStream outputStream, int startPage,
                             int endPage) throws Exception{
        //Create document and pdfReader objects.
        Document document = new Document();
        PdfReader pdfReader = new PdfReader(inputPdf);

        //Get total no. of pages in the pdf file.
        int totalPages = pdfReader.getNumberOfPages();
        //Check the startPage should not be greater than the endPage
        //and endPage should not be greater than total no. of pages.
        if(startPage > endPage || endPage > totalPages) {
            System.out.println("Kindly pass the valid values " +
                    "for startPage and endPage.");
        }else{
            // Create writer for the outputStream
            PdfWriter writer =
                    PdfWriter.getInstance(document, outputStream);

            //Open document
            document.open();

            //Contain the pdf data.
            PdfContentByte pdfContentByte =
                    writer.getDirectContent();
            PdfImportedPage page;

            while(startPage <= endPage) {
                document.newPage();
                page=writer.getImportedPage(pdfReader, startPage);
                pdfContentByte.addTemplate(page, 0, 0);
                startPage++;
            }

            //Close document and outputStream.
            outputStream.flush();
            document.close();
            outputStream.close();
        }
    }


    public Chunk ChunkInfoServlet(String topic, int spage, int epage, int bookid,String description,int price) {
        Chunk c = new Chunk();
        String bname="",path="";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            PreparedStatement ps1=con.prepareStatement("SELECT bookName from bookinfo where bookId=?");
            ps1.setInt(1,bookid);
            ResultSet rs1=ps1.executeQuery();
            if(rs1.next()){
                bname=rs1.getString("bookName");
            }


                //Prepare output stream for
                //new pdf file after split process.
            path+="C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\books\\" + topic +"_"+bookid+".pdf";
                OutputStream outputStream =
                        new FileOutputStream(path);


                //call method to split pdf file.
                splitPdfFile(new FileInputStream("C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\books\\" + bname + ".pdf"),
                        outputStream, spage, epage);

                System.out.println("Pdf file splitted successfully.");

            PreparedStatement ps=con.prepareStatement("INSERT INTO chunkinfo(topic,startPage,endPage,bookId,description,price,path) values(?,?,?,?,?,?,?)");
            //  ps.setString(1,u.getUname());
            //ps.setString(2,u.getUemail());
            // ps.setString(3,u.getUpass());
            ps.setString(1,topic);
            ps.setInt(2,spage);
            ps.setInt(3,epage);
            ps.setInt(4,bookid);
            ps.setInt(6,price);
            ps.setString(5,description);
            ps.setString(7,path);
            ps.executeUpdate();

        } catch (Exception e) {
                e.printStackTrace();
            }
        return c;
    }
    public Collection<Chunk> getTopic(String topic) {System.out.println("gettopic");
        try {
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks","root","");
            final List<Chunk> chunks = new ArrayList<Chunk>();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM chunkinfo WHERE  topic LIKE '%" + topic +"%'OR description LIKE '%" + topic +"%'");
            //ps.setString(1,topic );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final Chunk c = chunkBuilder(rs);
                System.out.println(c.getTopic());
                System.out.println(c.getChunkId());
                chunks.add(c);
            }
            con.close();
            return chunks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Chunk chunkBuilder(ResultSet rs) throws NullPointerException, SQLException {
       System.out.println("chunkbuilder");
        if (rs == null) {
            throw new NullPointerException("Result Set");
        }
         final int chunkId=rs.getInt("chunkId");
        final int bookId = rs.getInt("bookId");
        final String topic = rs.getString("topic");
        final String description = rs.getString("description");
        final int startPage = rs.getInt("startPage");
        final int endPage = rs.getInt("endPage");
        final int price = rs.getInt("price");
       System.out.println("chunkbuilder");

        return new Chunk( chunkId,topic,startPage,endPage,bookId,price,description);
    }

    public Chunk getById(int chunkId){
        try {
            Chunk c=new Chunk();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps1=con.prepareStatement("SELECT * from chunkinfo where chunkId='"+chunkId+"'");
            ResultSet rs1=ps1.executeQuery();
            if(rs1.next()){
                System.out.println("exception in wergetbyid");
                c=chunkBuilder(rs1);
                return c;
            }
        }catch(Exception e){
            System.out.println("exception in getbyid");
            e.printStackTrace();
        }
        return null;
    }

    public float getPrice(int chunkId){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps1 = con.prepareStatement("SELECT price from chunkinfo where chunkId='" + chunkId + "'");
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()){
                return rs1.getFloat("price");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }

    public String getChunkName(int chunkId)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection("jdbc:mysql://localhost:3306/customebooks", "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps1 = con.prepareStatement("SELECT topic from chunkinfo where chunkid='" + chunkId + "'");
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()){
                return rs1.getString("topic");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

}
