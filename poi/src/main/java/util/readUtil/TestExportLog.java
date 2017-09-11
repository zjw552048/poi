/*
 * file name:  TestExportLog.java
 * copyright:  Les Copyright 2017,  All rights reserved
 * description:  <description>
 * create time:  2017年9月4日
 */
package util.readUtil;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import entity.OrderInfo;
import exception.ColumnTitleNotFoundException;
import util.DateFormatUtil;
import util.PropertyUtil;
import util.StatusUtil;


/**
 * @author zhangjiawei
 *
 */
public class TestExportLog {
	/**
	 * 获取工作簿标题数组
	 * @param sheet
	 * @return
	 */
	private static String[] getSheetColumnTitleArray(Sheet sheet){
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
	private static void setOrderInfoBeanAttribute(OrderInfo orderInfo, Row row, 
												  Map<String, Integer> columnTitleMap, String columnTitle){
		
	}
	private static String getStringCellValue(Row row, Map<String, Integer> columnTitleMap, String columnTitle){
		System.out.println(columnTitle);
		Integer titleNum = columnTitleMap.get(columnTitle);
		if(titleNum == null){
			throw new ColumnTitleNotFoundException("配置文件中,不存在列<"+columnTitle+">");
		}
		Cell cell = row.getCell(titleNum);
		System.out.println(cell.getCellTypeEnum());
		return cell.getStringCellValue();
	}
    public static void main(String[] args) {
    	String fileName = "test.xls";
    	String propName = "column.properties";
		try (
			//读取文件流
			InputStream is = TestExportLog.class.getClassLoader().getResourceAsStream(fileName);
		    BufferedInputStream bf = new BufferedInputStream(is);){
			Workbook wb = WorkbookFactory.create(bf);
			Sheet sheet = wb.getSheetAt(0);
			//获取配置文件允许的标题map
			Map<String, Integer> columnTitleMap = PropertyUtil.getColumnPropMap(propName);
			//获取excel标题数组
			String[] columnTitleArray = getSheetColumnTitleArray(sheet);
			//获取最后一行
			int lastRow = sheet.getLastRowNum();
			//跳过标题,从第二行开始读取数据
			int currentRow = 1;
			Row row;
			for(int i=0;i<lastRow;i++){
				OrderInfo orderInfo = new OrderInfo();
				row = sheet.getRow(currentRow++);
				for(int j=0;i<columnTitleArray.length;j++){
					Integer titleNum = columnTitleMap.get(columnTitleArray[i]);
					if(titleNum == null){
						throw new ColumnTitleNotFoundException("配置文件中,不存在列<"+columnTitleArray[i]+">");
					}
					setOrderInfoBeanAttribute(orderInfo, columnTitleArray[i]);
				}
				// 下单来源 String sourceOfOrder
				String sourceOfOrder = getStringCellValue(row, columnTitleMap, "下单来源"); 
				orderInfo.setSourceOfOrder(sourceOfOrder);
				// 内部订单号 String orderId
				String orderId = getStringCellValue(row, columnTitleMap, "内部订单号");  
				orderInfo.setOrderId(orderId);
				// 用户订单号 String customOrderId
				String customOrderId = getStringCellValue(row, columnTitleMap, "用户订单号");  
				orderInfo.setCustomOrderId(customOrderId);
				// 下单日期 Date dateOfOrder
				String dateOfOrder = getStringCellValue(row, columnTitleMap, "下单日期");  
				orderInfo.setDateOfOrder(DateFormatUtil.parseToDate(dateOfOrder));
				// 发货日期 Date dateOfIssuance
				String dateOfIssuance = getStringCellValue(row, columnTitleMap, "发货日期");  
				orderInfo.setDateOfIssuance(DateFormatUtil.parseToDate(dateOfIssuance));
				// 订单状态 0=交易关闭 1=已完成 int statusOfOrder
				String statusOfOrder = getStringCellValue(row, columnTitleMap, "订单状态");  
				orderInfo.setStatusOfOrder(StatusUtil.getStatusOfOrder(statusOfOrder));
				// 支付方式 String methodOfPayment
				String methodOfPayment = getStringCellValue(row, columnTitleMap, "支付方式");
				orderInfo.setMethodOfPayment(methodOfPayment);
				// 支付状态 0=未支付 1=已支付 int statusOfPayment
				String statusOfPayment = getStringCellValue(row, columnTitleMap, "支付状态");
				orderInfo.setStatusOfPayment(StatusUtil.getStatusOfPayment(statusOfPayment));
				// 配送方式  String methodOfDistribution
				String methodOfDistribution = getStringCellValue(row, columnTitleMap, "配送方式");
				orderInfo.setMethodOfDistribution(methodOfDistribution);
				// 收货人 String consignee
				String consignee = getStringCellValue(row, columnTitleMap, "收货人");
				orderInfo.setConsignee(consignee);
				// 收货人手机号 String phoneNumberOfConsignee
				String phoneNumberOfConsignee = getStringCellValue(row, columnTitleMap, "收货人手机号");
				orderInfo.setPhoneNumberOfConsignee(phoneNumberOfConsignee);
				// 下单人手机号 String phoneNumberOfCustom
				String phoneNumberOfCustom = getStringCellValue(row, columnTitleMap, "下单人手机号");
				orderInfo.setPhoneNumberOfCustom(phoneNumberOfCustom);
				// 下单会员等级折扣 String discountGrade
				String discountGrade = getStringCellValue(row, columnTitleMap, "下单会员等级折扣");
				orderInfo.setDiscountGrade(discountGrade);
				// 订单会员权益 String perksOfMembershi
				String perksOfMembershi = getStringCellValue(row, columnTitleMap, "订单会员权益");
				orderInfo.setPerksOfMembershi(perksOfMembershi);
				// 商品名称 String nameOfCommodity
				String nameOfCommodity = getStringCellValue(row, columnTitleMap, "商品名称");
				orderInfo.setNameOfCommodity(nameOfCommodity);
				// 场次Id String performanceId
				String performanceId = getStringCellValue(row, columnTitleMap, "场次Id");
				orderInfo.setPerformanceId(performanceId);
				// 场次名称 String nameOfPerformance
				String nameOfPerformance = getStringCellValue(row, columnTitleMap, "场次名称");
				orderInfo.setNameOfPerformance(nameOfPerformance);
				// 演出馆厅 String ieldOfPerformance
				String ieldOfPerformance = getStringCellValue(row, columnTitleMap, "演出馆厅");
				orderInfo.setIeldOfPerformance(ieldOfPerformance);
				// 演出日期 Date dateOfPerformance
				String dateOfPerformance = getStringCellValue(row, columnTitleMap, "演出日期");
				orderInfo.setDateOfPerformance(DateFormatUtil.parseToDate(dateOfPerformance));
				// 票价 double priceOfTicket
				String priceOfTicket = getStringCellValue(row, columnTitleMap, "票价");
				orderInfo.setPriceOfTicket(Double.parseDouble(priceOfTicket));
				// 张数 int numberOfTicket
				String numberOfTicket = getStringCellValue(row, columnTitleMap, "张数");
				orderInfo.setNumberOfTicket(Integer.parseInt(numberOfTicket));
				// 应收 double shouldPayment
				String shouldPayment = getStringCellValue(row, columnTitleMap, "应收");
				orderInfo.setShouldPayment(Double.parseDouble(shouldPayment));
				// 实收=应收*折扣 double actualPayment
				String actualPayment = getStringCellValue(row, columnTitleMap, "实收");
				orderInfo.setActualPayment(Double.parseDouble(actualPayment));
				System.out.println(orderInfo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
