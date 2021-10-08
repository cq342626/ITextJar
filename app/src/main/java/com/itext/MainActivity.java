package com.itext;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.itextpdf.R;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onTest();
    }

    private void onTest() {
        try {
//            File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/pdf");
//            if (!path.exists()) {
//                path.mkdirs();
//            }
//            File file = new File(path + "/test.pdf");
//            if (!file.exists()) {
//                file.createNewFile();
//            }
            textTransformPdf();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void textTransformPdf() {
        Document document = new Document(PageSize.A4);// 创建一个document对象
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/pdf/createSamplePDF2.pdf"); // pdf_address为Pdf文件保存到sd卡的路径
            PdfWriter.getInstance(document, fos);
            // 开启
            document.open();

            // 字体资源需要自己下载或者在电脑里（C:\Windows\Fonts）找一下，这里是宋体
            @SuppressLint("ResourceType") String yaHeiFontName = getResources().getString(R.raw.simsun);
            // 网上教程就是这样的，没有具体理解
            yaHeiFontName += ",1";
            // 使用宋体显示中文
            BaseFont bfChinese = BaseFont.createFont(yaHeiFontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);//中文
            Font FontChinese11 = new Font(bfChinese, 12, com.itextpdf.text.Font.ITALIC);// Font添加的字体对象
            Font FontChinese11Normal = new Font(bfChinese, 12, com.itextpdf.text.Font.NORMAL);

            Font text = new Font(bfChinese, 12, Font.NORMAL);
            // Paragraph锻炼，默认换行
            Paragraph pg1 = new Paragraph("我相信有这种疑惑的同学有这种疑惑的同学一定也不在少数，那么今天我就结合我的实际经验，来简单介绍一下，作为一名即将毕业的计算机专业的应届生，我们需要做哪些功课才能帮助我们更快地找到Android相关的工作。", text);
            pg1.setAlignment(Element.ALIGN_LEFT);//设置文字居中 0靠左   1，居中     2，靠右
            // 下面设置的12、24、20、5、10，应该是和文字大小有关，列如中文文字大小为12，则24是两个中文
            pg1.setIndentationLeft(12); //设置左缩进
            pg1.setIndentationRight(12); //设置右缩进
            pg1.setFirstLineIndent(24); //设置首行缩进
            pg1.setLeading(20f); //行间距
            pg1.setSpacingBefore(5f); //设置段落上空白
            pg1.setSpacingAfter(10f); //设置段落下空白
            document.add(pg1);

            // Chunk是字符串，字符串默认不会换行
            document.add(new Chunk("文本-默认", new Font(bfChinese, 12, Font.NORMAL)));
            document.add(new Chunk("文本-字体24", new Font(bfChinese, 24, Font.NORMAL)));
            document.add(new Chunk("文本-加粗", new Font(bfChinese, 12, Font.BOLD)));
            document.add(new Chunk("文本-斜体", new Font(bfChinese, 12, Font.ITALIC)));
            document.add(new Chunk("文本-下划线", new Font(bfChinese, 12, Font.UNDERLINE)));
            document.add(new Chunk("文本-删除线", new Font(bfChinese, 12, Font.STRIKETHRU)));


            Paragraph paragraphLeft = new Paragraph("文本-左对齐", text);// Paragraph是段落
            paragraphLeft.setAlignment(Element.ALIGN_LEFT);// 左对齐
            document.add(paragraphLeft);// 添加到文档
            Paragraph paragraphCenter = new Paragraph("文本-居中", text);
            paragraphCenter.setAlignment(Element.ALIGN_CENTER);// 居中
            document.add(paragraphCenter);
            Paragraph paragraphRight = new Paragraph("文本-右对齐", text);
            paragraphRight.setAlignment(Element.ALIGN_RIGHT);// 右对齐
            document.add(paragraphRight);

            document.add(new Chunk(Chunk.NEWLINE)); // 换行

            //table3
            PdfPTable table = new PdfPTable(3);// 设置3列
            table.setWidthPercentage(100);// 表格宽度占可编辑宽度百分比
//            int width3[] = {40, 35, 25};
//            table3.setWidths(width3);
            PdfPCell cell31 = new PdfPCell(new Paragraph("文本-左对齐", FontChinese11Normal));// 单元格对象
            PdfPCell cell32 = new PdfPCell(new Paragraph("文本-居中", FontChinese11Normal));
            PdfPCell cell33 = new PdfPCell(new Paragraph("文本-右对齐", FontChinese11Normal));
            cell31.setBorder(0);// 隐藏边框
            cell31.setHorizontalAlignment(Element.ALIGN_LEFT);// 左对齐
            cell32.setBorder(0);
            cell32.setHorizontalAlignment(Element.ALIGN_CENTER);// 左右居中
            cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);// 上下居中
            cell33.setBorder(0);
            cell33.setHorizontalAlignment(Element.ALIGN_RIGHT);// 右对齐
            table.addCell(cell31);// 添加到表格
            table.addCell(cell32);
            table.addCell(cell33);
            document.add(table);// 表格添加到文档

            document.add(new Chunk(Chunk.NEWLINE)); // 换行

            PdfPTable table1 = new PdfPTable(3); // 设置3列
            for (int i = 0; i < 8; i++) {
                if (i < 3) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格1-左对齐", FontChinese11Normal));
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_LEFT); // 单元格文字对左对齐
                    table1.addCell(pdfPCell1);
                } else if (i < 6) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格1-居中", FontChinese11Normal));
                    if (i == 3) {
                        pdfPCell1.setPaddingTop(20f); // 单元格文字向上距离20
                    }
                    pdfPCell1.setFixedHeight(70f); // 单元格内文字高度
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER); // 单元格内文字左右居中
                    pdfPCell1.setVerticalAlignment(Element.ALIGN_MIDDLE); // 单元格内文字上下居中
                    table1.addCell(pdfPCell1);
                } else {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格1-右对齐", FontChinese11Normal));
                    if (i == 7)
                        pdfPCell1.setColspan(2); // 单元格占2列
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_RIGHT); // 单元格内文字右对齐
                    table1.addCell(pdfPCell1);
                }
            }
            document.add(table1);

            document.add(new Chunk(Chunk.NEWLINE)); // 换行

            float[] point = {1f, 2f, 3f}; // 表格3列，列宽度比例1:2:3
            PdfPTable table2 = new PdfPTable(point); // 新建表格，表格设定
            table2.setWidthPercentage(100); // 表格所占可编辑宽度的100%，默认80%居中显示
            for (int i = 0; i < 9; i++) {
                if (i < 3) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格2-左对齐", FontChinese11Normal));
                    if (i == 1) {
                        pdfPCell1.disableBorderSide(Rectangle.TOP);
                    }
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table2.addCell(pdfPCell1);
                } else if (i < 6) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格2-居中", FontChinese11Normal));
                    if (i == 3) {
                        pdfPCell1.disableBorderSide(Rectangle.LEFT);
                    }
                    if (i == 5) {
                        pdfPCell1.disableBorderSide(Rectangle.RIGHT);
                    }
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table2.addCell(pdfPCell1);
                } else {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格2-右对齐", FontChinese11Normal));
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    if (i == 7) {
                        pdfPCell1.disableBorderSide(Rectangle.BOTTOM);
                    }
                    table2.addCell(pdfPCell1);
                }
            }
            document.add(table2);

            document.add(new Chunk(Chunk.NEWLINE)); // 换行

            PdfPTable table3 = new PdfPTable(3); // 设置3列
            table3.setWidthPercentage(50); // 表格宽度显示50%
            table3.setHorizontalAlignment(Element.ALIGN_LEFT); // 表格左对齐
            for (int i = 0; i < 8; i++) {
                if (i < 3) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格3-左对齐", FontChinese11Normal));
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table3.addCell(pdfPCell1);
                } else if (i < 6) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格3-居中", FontChinese11Normal));
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table3.addCell(pdfPCell1);
                } else {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格3-右对齐", FontChinese11Normal));
                    if (i == 7)
                        pdfPCell1.setColspan(2);
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table3.addCell(pdfPCell1);
                }
            }
            document.add(table3);

            PdfPTable table4 = new PdfPTable(3); // 设置3列
            table4.setWidthPercentage(50);
            table4.setHorizontalAlignment(Element.ALIGN_RIGHT); // 表格右对齐
            for (int i = 0; i < 8; i++) {
                if (i < 3) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格4-左对齐", FontChinese11Normal));
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table4.addCell(pdfPCell1);
                } else if (i < 6) {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格4-居中", FontChinese11Normal));
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table4.addCell(pdfPCell1);
                } else {
                    PdfPCell pdfPCell1 = new PdfPCell(new Paragraph("表格4-右对齐", FontChinese11Normal));
                    if (i == 7)
                        pdfPCell1.setColspan(2);
                    pdfPCell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table4.addCell(pdfPCell1);
                }
            }
            document.add(table4);


            //加入空行
            Paragraph blankRow31 = new Paragraph(18f, " ", FontChinese11);
            document.add(blankRow31);

//            document.add(Image.getInstance(ContextCompat.get)))

            // 关闭
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logs(String a, String b) {
        Log.e(a, b);
    }

    public Font setChineseFont() {
        Font fontChinese = null;
        try {
            // STSong-Light : Adobe的字体
            // UniGB-UCS2-H : pdf 字体
            @SuppressLint("ResourceType") String yaHeiFontName = getResources().getString(R.raw.simsun);
            yaHeiFontName += ",1";
            // 使用微软雅黑字体显示中文
            BaseFont bfChinese = BaseFont.createFont(yaHeiFontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);//中文简体
            fontChinese = new Font(bfChinese, 12, Font.NORMAL);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fontChinese;
    }
}