package com.itext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 作者: CQ
 * 日期: 2021-04-20
 * 说明:
 */
public class HelloWorld {
    public static void main(String[] args) {
        String FILE_DIR = "./"; // 项目根目录：盘符:/.../.../项目名称，注意：点号并不表示当前类文件所在的目录
        //Step 1—Create a Document.
        Document document = new Document();
        try {
            //Step 2—Get a PdfWriter instance.
            PdfWriter.getInstance(document, new FileOutputStream(FILE_DIR + "createSamplePDF.pdf"));
            //Step 3—Open the Document.
            document.open();
            //Step 4—Add content.
            document.add(new Paragraph("Hello World"));
            //Step 5—Close the Document.
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
