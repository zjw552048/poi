/*
 * file name:  TestExportLog.java
 * copyright:  Les Copyright 2017,  All rights reserved
 * description:  <description>
 * create time:  2017年9月4日
 */
package com.z.util.readUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.z.core.dao.OrderInfoMapper;
import com.z.core.entity.OrderInfo;
import com.z.util.PropertyUtil;
import com.z.util.ReadCellUtil;


/**
 * @author zhangjiawei
 *
 */
public class TestReadExcel {
	public static SqlSession getMapper() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
    	SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
    	SqlSession sqlSession = factory.openSession();
    	return sqlSession;
	}
	public static void main(String[] args) throws IOException {
		insertData();
		countData();
    }
	public static void insertData() throws IOException{
		String propName = "properties/column.properties";
    	String fileName = "订单查询20170919 22%3A52.xls";
    	
    	TestReadExcel t = new TestReadExcel();
    	List<OrderInfo> list = t.readSheet(propName, fileName);
    	System.out.println(list.size());
    	
    	SqlSession sqlSession = getMapper();
    	OrderInfoMapper mapper = sqlSession.getMapper(OrderInfoMapper.class);
    	for(OrderInfo bean : list){
//    		System.out.println(bean);
    		mapper.addOrderInfo(bean);
//    		System.out.println("success");
    	}
    	sqlSession.commit();
	}
	
	public static void countData() throws IOException{
		SqlSession sqlSession = getMapper();
    	OrderInfoMapper mapper = sqlSession.getMapper(OrderInfoMapper.class);
		List<String> getNameOfCommodityList = mapper.getNameOfCommodityList();
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(String nameOfCommodity : getNameOfCommodityList){
    		int num = mapper.getNumberOfTicketsSold(nameOfCommodity);
    		map.put(nameOfCommodity, num);
    	}
    	System.out.println(map);
	}
	/**
	 * 获取工作簿标题数组
	 * @param sheet
	 * @return
	 */
	private String[] getSheetColumnTitleArray(Sheet sheet){
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
	/**
	 * 读取工作簿内容
	 * @param propName
	 * @param fileName
	 */
	public List<OrderInfo> readSheet(String propName, String fileName){
		try (//读取文件流
			InputStream is = TestReadExcel.class.getClassLoader().getResourceAsStream(fileName);
		    BufferedInputStream bf = new BufferedInputStream(is);){
			
			List<OrderInfo> list = new ArrayList<OrderInfo>();
			Workbook wb = WorkbookFactory.create(bf);
			Sheet sheet = wb.getSheetAt(0);
			//获取excel标题数组
			String[] columnTitleArray = getSheetColumnTitleArray(sheet);
			//获取配置文件允许的标题map
			Map<String, String> columnTitleMap = PropertyUtil.getPropMap(propName);
			//获取最后一行
			int lastRow = sheet.getLastRowNum();
			//跳过标题,从第二行开始读取数据
			int currentRow = 1;
			Row row;
			for(int i=0;i<lastRow;i++){
				OrderInfo orderInfo = new OrderInfo();
				row = sheet.getRow(currentRow++);
				for(int j=0;j<columnTitleArray.length;j++){
					//进入工具类，根据标题设置bean属性
					ReadCellUtil.setOrderInfoBeanAttribute(orderInfo, row, columnTitleArray[j], columnTitleMap);
				}
				//拆分并返回拆分后的list，添加到结果集合里
				list.addAll(ReadCellUtil.getMultiplePriceOrderInfo(orderInfo));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
