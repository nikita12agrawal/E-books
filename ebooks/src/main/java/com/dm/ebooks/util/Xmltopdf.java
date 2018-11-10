package com.dm.ebooks.util;
import java.io.*;
import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class Xmltopdf {
    public static void main(String[] args)
            throws IOException, DocumentException, TransformerException,TransformerConfigurationException,FileNotFoundException {
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer(new StreamSource("src/main/webapp/samplexsl.xsl"));
        transformer.transform(new StreamSource("src/main/webapp/sample.xml"),new StreamResult(new FileOutputStream("src/main/webapp/sample1.html")));
        String File_To_Convert = "src/main/webapp/sample1.html";
        String url = new File(File_To_Convert).toURI().toURL().toString();
        System.out.println(""+url);
        String HTML_TO_PDF = "ConvertedFile2.pdf";
        OutputStream os = new FileOutputStream(HTML_TO_PDF);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        renderer.createPDF(os);
        os.close();
    }

}
