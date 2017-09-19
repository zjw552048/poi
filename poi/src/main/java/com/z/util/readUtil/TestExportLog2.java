/*
 * file name:  TestExportLog.java
 * copyright:  Les Copyright 2017,  All rights reserved
 * description:  <description>
 * create time:  2017年9月4日
 */
package com.z.util.readUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author zhangjiawei
 *
 */
public class TestExportLog2 {
    public static void main(String[] args) {
        try {
            //文件路径
            String dirFilePath = "C:\\Users\\Administrator\\Desktop";
            //文件名
            String fileName = "t.xlsx";
            //文件位置
            String filePath = dirFilePath + File.separator + fileName;
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
            //初始化文件
            initExcel(file);
            
            List<RzsxxxBean> list = new ArrayList<RzsxxxBean>();
            RzsxxxBean a = new RzsxxxBean("a","a","a","a");
            RzsxxxBean b = new RzsxxxBean("b","b","b","b");
            RzsxxxBean c = new RzsxxxBean("c","c","c","c");
            list.add(a);
            list.add(b);
            list.add(c);
            
            List<RzsxxxBean> list2 = new ArrayList<RzsxxxBean>();
            RzsxxxBean aa = new RzsxxxBean("aa","a","a","a");
            RzsxxxBean bb = new RzsxxxBean("bb","b","b","b");
            RzsxxxBean cc = new RzsxxxBean("cc","c","c","c");
            list.add(aa);
            list.add(bb);
            list.add(cc);
            
            List<RzsxxxBean> list3 = new ArrayList<RzsxxxBean>();
            RzsxxxBean aaa = new RzsxxxBean("aaa","a","a","a");
            RzsxxxBean bbb = new RzsxxxBean("bba","b","b","b");
            RzsxxxBean ccc = new RzsxxxBean("cca","c","c","c");
            list.add(aaa);
            list.add(bbb);
            list.add(ccc);
            
            writeExcel(file,list);
            writeExcel(file,list2);
            writeExcel(file,list3);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void initExcel(File file) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        //第一次写入,设置格式
        // ----------------------------设置列格式--------------------------------------
        sheet.setColumnWidth((short) 0, 25 * 256);
        sheet.setColumnWidth((short) 1, 15 * 256);
        sheet.setColumnWidth((short) 2, 15 * 256);
        sheet.setColumnWidth((short) 3, 40 * 256);
        // ----------------------------设置单元格格式--------------------------------------
        CellStyle cellStyle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // 设置标题单元格字体
        Font font = wb.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        cellStyle.setFont(font);
        // 第一行第一列
        String rzUsername = "用户";
        String rzTime = "时间";
        String rzIp = "ip";
        String rzAction = "操作描述";
        Row row1 = sheet.createRow(0);
        Cell cell1 = row1.createCell(0);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue(rzUsername);
        // 第一行第er列
        cell1 = row1.createCell(1);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue(rzTime);
        // 第一行第san列
        cell1 = row1.createCell(2);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue(rzIp);
        // 第一行第si列
        cell1 = row1.createCell(3);
        cell1.setCellStyle(cellStyle);
        cell1.setCellValue(rzAction);
        
        wb.write(fos);
        fos.close();
    }
    private static void writeExcel(File file,List<RzsxxxBean> list) throws IOException{
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        XSSFWorkbook wb = new XSSFWorkbook(bis);
        int a = wb.getNumberOfSheets();
        int b = wb.getSheetAt(0).getLastRowNum();
        
        
        FileOutputStream fos = new FileOutputStream(file);
        SXSSFWorkbook swb = new SXSSFWorkbook(wb, 10000);
        Sheet sheet = swb.getSheetAt(0);
        int rowsNum = sheet.getLastRowNum();
     // ----------------------------设置单元格格式--------------------------------------
        CellStyle cellStyle = wb.createCellStyle();
        // 指定单元格居中对齐
        cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        // 指定单元格垂直居中对齐
        cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        // ----------------------------填入单元格内容--------------------------------------
        additionalWrite(sheet, cellStyle, list, rowsNum);
        
        swb.write(fos);
        fos.close();
        fis.close();
        
    }
    private static void additionalWrite(Sheet sheet, CellStyle cellStyle, 
                              List<RzsxxxBean> list, int rowsNum){
        Row row;
        Cell cell;
        for (int i = 0; i < list.size(); i++) {
            //第一行为标题,从第二行开始
            row = sheet.createRow(i + rowsNum + 1);
            
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getUsername());
            
            
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getTime() + "");
            
            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getIp() + "");
            
            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getAction() + "");
        }
    }
}
