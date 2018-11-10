package com.dm.ebooks.dao;
import java.util.ArrayList;
import java.util.List;
//package com.dm.ebooks.util;
import java.io.*;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
//package com.javacodegeeks.java.core;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CreateXML {



        public void createXML(String title,List<Integer> idList){
            final String xmlFilePath="C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\"+title+".xml";
            System.out.println("create xml");
            try {
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();
                //create a root element
                Element root = document.createElement("Book");
                document.appendChild(root);
                //creating attribute title of book
                Attr attr = document.createAttribute("title");
                attr.setValue(title);
                root.setAttributeNode(attr);
                for(int i=0;i<idList.size();i++){

                    //root.appendChild(chunk);
                    //  Attr attr1 = document.createAttribute("name");
                    ChunkInfoDao cid=new ChunkInfoDao();
                    String str=cid.getChunkName(idList.get(i));
                    root.appendChild(getChunkDetails(document,i,str));
                    //chunk.setNodeValue(str);
                    // attr.setValue(str);
                    //chunk.setAttributeNode(attr);
                }
                // create the xml file
                //transform the DOM Object to an XML File
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(new File(xmlFilePath));
                // If you use
                // StreamResult result = new StreamResult(System.out);
                // the output will be pushed to the standard output ...
                // You can use that for debugging
                transformer.transform(domSource, streamResult);
                System.out.println("Done creating XML File");
            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
            try{
                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer transformer = tFactory.newTransformer(new StreamSource("C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\sam.xsl"));
                transformer.transform(new StreamSource("C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\"+title+".xml"),new StreamResult(new FileOutputStream("C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\"+title+".html")));
                String File_To_Convert = "C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\"+title+".html";
                String url = new File(File_To_Convert).toURI().toURL().toString();
                System.out.println(""+url);
                String HTML_TO_PDF = "C:\\Users\\mypc\\IdeaProjects\\ebooks\\src\\main\\webapp\\cover.pdf";
                OutputStream os = new FileOutputStream(HTML_TO_PDF);
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocument(url);
                renderer.layout();
                renderer.createPDF(os);
                os.close();
                System.out.println("coverpage success");
            }catch(Exception e){
                System.out.println(e);
            }


        }


        private static Node getChunkDetails(Document doc, int id, String name) {
            Element chunk = doc.createElement("Chunk");

            //set id attribute
            chunk.setAttribute("id", String.valueOf(id));
            chunk.setAttribute("title",name);
            return chunk;
        }

    }

