package com.z.util.readCellUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

import com.z.core.entity.DetailedPriceInfo;
import com.z.core.entity.OrderInfo;
import com.z.exception.ColumnTitleNotFoundException;
import com.z.util.DateFormatUtil;
import com.z.util.StatusUtil;

public class ReadExcelCellUtilForTypeC extends BaseReadCellUtil{
	
	/**
	 * 根据列明分别设置orderInfoBean属性
	 * @param orderInfo
	 * @param row
	 * @param columnTitleMap
	 * @param columnTitle
	 * @throws ParseException
	 */
	public static void setOrderInfoBeanAttribute(OrderInfo orderInfo, Row row, String columnTitle,
												  Map<String, String> columnTitleMap) throws ParseException{
		String titleNum = columnTitleMap.get(columnTitle);
		if(titleNum == null){
			throw new ColumnTitleNotFoundException("配置文件中,不存在列<"+columnTitle+">");
		}
		String cellValue = getCellValue(row.getCell(Integer.parseInt(titleNum)));
		switch(columnTitle){
		case "内部订单号":// 内部订单号 String orderId
			orderInfo.setOrderId(cellValue);
			break;
		case "下单日期":// 下单日期 Date dateOfOrder
			//比较特殊,下单日期可能为空
			orderInfo.setDateOfOrder(DateFormatUtil.parseToDate(cellValue));
			break;
		case "订单状态":// 订单状态 0=交易关闭 1=已完成 int statusOfOrder
			orderInfo.setStatusOfOrder(StatusUtil.getStatusOfOrder(cellValue));
			break;
		case "下单账号":// 下单账号,即下单来源 String sourceOfOrder
			orderInfo.setSourceOfOrder(cellValue);
			break;
		case "商品名称":// 商品名称 String nameOfCommodity
			orderInfo.setNameOfCommodity(cellValue);
			break;
		case "场次Id":// 场次Id String performanceId
			orderInfo.setPerformanceId(cellValue);
			break;
		case "场次名称":// 场次名称 String nameOfPerformance
			orderInfo.setNameOfPerformance(cellValue);
			break;
		case "演出日期":// 演出日期 Date dateOfPerformance
			orderInfo.setDateOfPerformance(DateFormatUtil.parseToDate(cellValue));
			break;
		case "明细":// 明细,即票价明细=单价*张数 String priceAndNumber
			orderInfo.setPriceAndNumber(cellValue);
			break;
		case "应收":// 应收 double shouldPayment
			orderInfo.setShouldPayment(Double.parseDouble(cellValue));
			break;
		case "实收":// 实收=应收*折扣 double actualPayment
			orderInfo.setActualPayment(Double.parseDouble(cellValue));
			break;
		}
	}
	
	/**
	 * 当有多种票价组合在一个单元格时，此处做拆分行处理
	 * 票价实际为两部分, 票价=单价*张数
	 * 单价 double priceOfTicket 
	 * 张数 int numberOfTicket
	 * @param orderInfo
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public static List<OrderInfo> getMultiplePriceOrderInfo(OrderInfo orderInfo) throws CloneNotSupportedException{
		List<OrderInfo> list = new ArrayList<OrderInfo>();
		String priceAndNumber = orderInfo.getPriceAndNumber();
		//获取该条记录的票价单元格详细信息List,判断同一条数据存在多种票种的情况
		//当该条记录有多种票时返回详细票价信息list
		List<DetailedPriceInfo> pricesList = getDetailedPriceList(priceAndNumber, " ", "\\*");
		
		//如果存在多条记录,即复制其余属性,修改单价/张数属性
		for(int i=0;i<pricesList.size();i++){
			OrderInfo OrderInfoSameLine = (OrderInfo) orderInfo.clone();
			OrderInfoSameLine.setPriceOfTicket(pricesList.get(i).getPrice());
			OrderInfoSameLine.setNumberOfTicket(pricesList.get(i).getNum());
			list.add(OrderInfoSameLine);
		}
		return list;
	}
	
}
