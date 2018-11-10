package com.dm.ebooks;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * This class is used to split an existing pdf file using iText jar.
 * @author javawithease
 */
public class PDFSplitExample {
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

    public static void main(String args[]){
        try {
            //Prepare output stream for
            //new pdf file after split process.
            OutputStream outputStream =
                    new FileOutputStream("D:\\mtech\\split1.pdf");
          //  OutputStream outputStream1 =
            //        new FileOutputStream("D:\\SplitFile2.pdf");

            //call method to split pdf file.
            splitPdfFile(new FileInputStream("D:\\mtech\\kurose.pdf"),
                    outputStream, 1, 10);
            //splitPdfFile(new FileInputStream("D:\\TestFile.pdf"),
              //      outputStream1, 11, 15);

            System.out.println("Pdf file splitted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}