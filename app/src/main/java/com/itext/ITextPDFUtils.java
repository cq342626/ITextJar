package com.itext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import com.itextpdf.R;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 作者: CQ
 * 日期: 2021-04-21
 * 说明:
 */
public class ITextPDFUtils {

    private Document document;
    private Context context;

    public ITextPDFUtils(String name, Context context) {
        this.document = new Document(); // 初始化
        this.context = context; // 初始化
        FileOutputStream fos; //定义输出流
        try {
            fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BAISON/MWMS/PDF/" + name); // pdf_address为Pdf文件保存到sd卡的路径
            PdfWriter pdfWriter = PdfWriter.getInstance(document, fos);
            pdfWriter.setPdfVersion(PdfWriter.PDF_VERSION_1_7);
            document.open(); //打开
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置标题
     * @param title
     */
    public void setTitle(String title) {
        Font FontChinese11 = new Font(getChineseBaseFont(), 24, Font.BOLD);  // 字体：24号，加粗
        Paragraph paragraph = new Paragraph(title, FontChinese11);
        paragraph.setAlignment(Element.ALIGN_CENTER); // 水平居中
        try {
            document.add(paragraph);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContext(String context) {
        try {
            document.add(new Paragraph(new Paragraph(context, getChineseFont())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContext(String left, String right) {
        PdfPTable table2 = new PdfPTable(2);
        table2.setWidthPercentage(100);// 百分比，默认宽度占可编辑的80%
        PdfPCell cell31 = new PdfPCell(new Paragraph(left, getChineseFont()));
        PdfPCell cell32 = new PdfPCell(new Paragraph(right, getChineseFont()));
        cell31.setBorder(0);
        cell31.setColspan(1);
        cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell32.setBorder(0);
        cell32.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table2.addCell(cell31);
        table2.addCell(cell32);
        try {
            document.add(table2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContext3(String left, String center, String right) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);// 百分比，默认宽度占可编辑的80%
        PdfPCell cell31 = new PdfPCell(new Paragraph(left, getChineseFont()));
        PdfPCell cell32 = new PdfPCell(new Paragraph(center, getChineseFont()));
        PdfPCell cell33 = new PdfPCell(new Paragraph(right, getChineseFont()));
        cell31.setBorder(0);
        cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell32.setBorder(0);
        cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell33.setBorder(0);
        cell33.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell31);
        table.addCell(cell32);
        table.addCell(cell33);
        try {
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置表格
     *
     * @param list a,b,c,a1,b1,c1,a2,b2,c2......
     * @param size 列数
     */
    public void setTable(LinkedList<String> list, int size) {
        PdfPTable table = new PdfPTable(size);
        table.setWidthPercentage(100);// 百分比，默认宽度占可编辑的80%
        for (int i = 0; i < list.size(); i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(list.get(i), getChineseFont()));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 横线居中
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 纵向居中
            table.addCell(cell);
        }
        try {
            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //换行
    public void onNewLine() {
        try {
            document.add(Chunk.NEWLINE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Font getChineseFont() {
        Font fontChinese = null;
        try {
            fontChinese = new Font(getChineseBaseFont(), 12, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fontChinese;
    }

    public BaseFont getChineseBaseFont() {
        @SuppressLint("ResourceType") String yaHeiFontName = context.getResources().getString(R.raw.simsun);
        yaHeiFontName += ",1";
        BaseFont bfChinese = null;
        // 使用微软雅黑字体显示中文
        try {
            bfChinese = BaseFont.createFont(yaHeiFontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);//中文简体
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bfChinese;
    }

    public void close() {
        document.close();
    }

    public void setFileName(String name) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/BAISON/MWMS/PDF/" + name); // pdf_address为Pdf文件保存到sd卡的路径
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
