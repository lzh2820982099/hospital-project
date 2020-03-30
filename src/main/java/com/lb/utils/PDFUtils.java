package com.lb.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lb.entity.LbAppointment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFUtils {
    private static BaseFont bf;//创建字体
    private static Font font;

    static {
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            font = new Font(bf, 12);//使用字体
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createAppointment(LbAppointment appointment, String path) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path+appointment.getPatientName()+DateUtils.date2String(new Date())+"挂号单.pdf"));
            document.open();
            PdfPTable pdfPTable = new PdfPTable(4);
            createCell("挂号单", 4, pdfPTable, font);
            createCell("预约号码:", 2, pdfPTable, font);
            createCell(appointment.getId() + "", 2, pdfPTable, font);
            createCell("患者姓名:", 2, pdfPTable, font);
            createCell(appointment.getPatientName(), 2, pdfPTable, font);
            createCell("预约科室:", 2, pdfPTable, font);
            createCell(appointment.getDepartment(), 2, pdfPTable, font);
            createCell("预约医生:", 2, pdfPTable, font);
            createCell(appointment.getDoctorName(), 2, pdfPTable, font);
            createCell("门诊费:", 2, pdfPTable, font);
            createCell(appointment.getExpenses() + "  (元)", 2, pdfPTable, font);
            createCell("预约时间:", 2, pdfPTable, font);
            createCell(date2String(appointment.getTime()), 2, pdfPTable, font);
            document.add(pdfPTable);
            document.close();
            return "挂号单生成成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统内部错误，生成失败";
        }
    }

    private static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY年MM月dd日");
        return sdf.format(date);
    }

    private static void createCell(String text, int colspan, PdfPTable pdfPTable, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setColspan(colspan);
        pdfPTable.addCell(cell);
    }
}
