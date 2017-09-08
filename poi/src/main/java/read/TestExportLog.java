/*
 * file name:  TestExportLog.java
 * copyright:  Les Copyright 2017,  All rights reserved
 * description:  <description>
 * create time:  2017年9月4日
 */
package read;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * @author zhangjiawei
 *
 */
public class TestExportLog {
    public static void main(String[] args) {
        //文件路径
		String dirFilePath = "C:\\Users\\z\\Desktop\\chy";
		//文件名
		String fileName = "9.5报表.xls";
		//文件位置
		String filePath = dirFilePath + File.separator + fileName;
		File file = new File(filePath);
		if(!file.exists()){
			System.out.println("<<<<<<<<<<文件路径错误<<<<<<<<<<<");
		}
		
		HSSFWorkbook wb = new HSSFWorkbook();
    }
}
