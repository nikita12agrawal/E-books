package com.dm.ebooks.controller;

import com.dm.ebooks.dao.CartDao;
import com.dm.ebooks.dao.ChunkInfoDao;
import com.dm.ebooks.dao.CreateXML;
import com.dm.ebooks.dao.MergedChunksDao;
import com.dm.ebooks.model.Cart;
import com.dm.ebooks.model.Chunk;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.*;
import java.util.*;

@WebServlet(name = "MergeServlet")
public class MergeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ref;
        HttpSession session=request.getSession();
        int userId=(Integer) session.getAttribute("personId");

        String title=request.getParameter("title");

        ref = request.getParameter("merge");
     //   int price=Integer.parseInt(request.getParameter("price"));
        Map params = request.getParameterMap();
        Iterator i = params.keySet().iterator();
        if (ref.equals("DELETE")) {
            while (i.hasNext()) {
                String key = (String) i.next();
                String val = ((String[]) params.get(key))[0];
                System.out.println("Key: " + key);
                System.out.println("Val: " + val);
                try {
                    int v = Integer.parseInt(val);
                    CartDao cd = new CartDao();
                    //Merged
                    cd.deleteById(v,userId);
                } catch (Exception e) {
                    break;
                }
            }
            CartDao cd = new CartDao();
            Collection<Cart> carts=cd.getCartItem(userId);
            request.setAttribute("cartItems",carts);
            RequestDispatcher req = request.getRequestDispatcher("cart.jsp");
            req.forward(request, response);
        }
        //when merge is clicked....
        else { // RequestDispatcher req = request.getRequestDispatcher("title.jsp");
            //req.forward(request, response);
           // req.
          //  Random r=new Random();
            //      int no=r.nextInt(500);
                  String path1="C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\books\\merged\\"+title+"_"+userId+".pdf";
            List<InputStream> chunkPdfList = new ArrayList<InputStream>();
            OutputStream outputStream =
                    new FileOutputStream(path1);
              float price=0;
               List<Integer> id=new ArrayList<Integer>();
            while (i.hasNext()) {
                String key = (String) i.next();
                String val = ((String[]) params.get(key))[0];
                System.out.println("Key: " + key);
                System.out.println("Val: " + val);
                try {
                    int v = Integer.parseInt(val);
                    id.add(v);
                    // for xslfo
                    ChunkInfoDao c=new ChunkInfoDao();
                    price=price+c.getPrice(v);
                    Chunk chunk=c.getById(v);


                       //end of xslfo
                    // price+=c.getPrice(v);
                    CartDao cd=new CartDao();
                    String path= cd.getPathById(v);
                    chunkPdfList.add(new FileInputStream(path));
                    cd.deleteById(v,userId);  //v is chunkId;
                } catch (Exception e) {
                    break;
                }


        }

            //creating an XML
            CreateXML cx=new CreateXML();
            cx.createXML(title,id);
            CartDao cd=new CartDao();
            chunkPdfList.add(0, new FileInputStream("C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\cover.pdf"));
           try{ int orderid=cd.mergePdfFiles(chunkPdfList, outputStream,userId,path1,price,title);
               MergedChunksDao mcd=new MergedChunksDao();
               for(int j=0;j<id.size();j++){
               mcd.insertIntoMergedChunks(id.get(j),orderid);
               }
           }
           catch(Exception e){
             System.out.println(e);
           }

            RequestDispatcher req = request.getRequestDispatcher("user.jsp");
            JOptionPane.showMessageDialog(null,"your book has been created");
            req.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
