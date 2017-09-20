package com.z.util.readUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class BaseReadExcel {
	protected static Sheet getSheet(String fileName, int sheetIndex) throws IOException, 
											 EncryptedDocumentException, InvalidFormatException{
		//读取文件流
		try(InputStream is = BaseReadExcel.class.getClassLoader().getResourceAsStream(fileName);
		    BufferedInputStream bf = new BufferedInputStream(is);){
			Workbook wb = WorkbookFactory.create(bf);
			Sheet sheet = wb.getSheetAt(sheetIndex);
			return sheet;
		}
	}
	/**
	 * 获取工作簿标题数组
	 * @param sheet
	 * @return
	 */
	protected static String[] getSheetColumnTitleArray(Sheet sheet){
		//获取标题行
		Row titleRow = sheet.getRow(0);
		//获取标题数目
		int titleNum = titleRow.getLastCellNum();
		//新建标题数目长度的数组
		String[] columnTitleArray = new String[titleNum];
		for(int i=0;i<titleNum;i++){
			//将标题值,依次存入数组
			columnTitleArray[i] = titleRow.getCell(i).getStringCellValue();
		}
		return columnTitleArray;
	}
}
