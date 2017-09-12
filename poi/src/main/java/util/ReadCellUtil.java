package util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import entity.OrderInfo;
import entity.DetailedPriceInfo;
import exception.CellTypeIllegalException;
import exception.ColumnTitleNotFoundException;

public class ReadCellUtil {
	
	/**
	 * 当前设定合法类型只有三种, BLANK\STRING\NUMERIC
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
        Object cellValue = null;
        if(cell == null){
        	return null;
        }
        switch (cell.getCellTypeEnum()) {
        case BLANK://空单元格
        	cellValue = null;
        	break;
        case STRING://字符串单元格
        	cellValue = cell.getStringCellValue();
        	break;
        case NUMERIC://数值类型单元格，分为数值/日期
        	if (DateUtil.isCellDateFormatted(cell)) {
        		cellValue = cell.getDateCellValue();
            } else {
            	cellValue = cell.getNumericCellValue();
            }
        	break;
        default:
        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"]类型非法");
//        case FORMULA:
////        	cellValue = cell.getCellFormula();
////        	break;
//        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"],cellType = FORMULA");
//        case BOOLEAN:
////        	cellValue = cell.getBooleanCellValue();
////        	break;
//        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"],cellType = BOOLEAN");
//        case _NONE:
//        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"],cellType = _NONE");
//        case ERROR:
//        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"],cellType = ERROR");
        }
        return cellValue;  
	}
	
	/**
	 * 根据列明分别设置orderInfoBean属性
	 * @param orderInfo
	 * @param row
	 * @param columnTitleMap
	 * @param columnTitle
	 * @throws ParseException
	 */
	public static List<DetailedPriceInfo> setOrderInfoBeanAttribute(OrderInfo orderInfo, Row row, String columnTitle,
												  Map<String, Integer> columnTitleMap) throws ParseException{
		//定义票价数组，当该条记录有多种票时返回,否则返回null
		List<DetailedPriceInfo> pricesList = null; 
		
		Integer titleNum = columnTitleMap.get(columnTitle);
		if(titleNum == null){
			throw new ColumnTitleNotFoundException("配置文件中,不存在列<"+columnTitle+">");
		}
		System.out.println(titleNum);
		System.out.println(row.getCell(titleNum));
		Object cellValue = ReadCellUtil.getCellValue(row.getCell(titleNum));
		switch(columnTitle){
		case "下单来源":// 下单来源 String sourceOfOrder
			orderInfo.setSourceOfOrder((String) cellValue);
			break;
		case "内部订单号":// 内部订单号 String orderId
			orderInfo.setOrderId((String) cellValue);
			break;
		case "用户订单号":// 用户订单号 String customOrderId
			orderInfo.setCustomOrderId((String) cellValue);
			break;
		case "下单日期":// 下单日期 Date dateOfOrder
			Date dateOfOrder = null;
			if(cellValue instanceof String){
				dateOfOrder = DateFormatUtil.parseToDate((String) cellValue);
			}else if(cellValue instanceof Date){
				dateOfOrder = (Date) cellValue;
			}
			orderInfo.setDateOfOrder(dateOfOrder);
			break;
		case "发货日期":// 发货日期 Date dateOfIssuance
			Date dateOfIssuance = null;
			if(cellValue instanceof String){
				dateOfIssuance = DateFormatUtil.parseToDate((String) cellValue);
			}else if(cellValue instanceof Date){
				dateOfIssuance = (Date) cellValue;
			}
			orderInfo.setDateOfIssuance(dateOfIssuance);
			break;
		case "订单状态":// 订单状态 0=交易关闭 1=已完成 int statusOfOrder
			orderInfo.setStatusOfOrder(StatusUtil.getStatusOfOrder((String) cellValue));
			break;
		case "支付方式":// 支付方式 String methodOfPayment
			orderInfo.setMethodOfPayment((String) cellValue);
			break;
		case "支付状态 ":// 支付状态 0=未支付 1=已支付 int statusOfPayment
			orderInfo.setStatusOfPayment(StatusUtil.getStatusOfPayment((String) cellValue));
			break;
		case "配送方式":// 配送方式  String methodOfDistribution
			orderInfo.setMethodOfDistribution((String) cellValue);
			break;
		case "收货人":// 收货人 String consignee
			orderInfo.setConsignee((String) cellValue);
			break;
		case "收货人手机号":// 收货人手机号 String phoneNumberOfConsignee
			orderInfo.setPhoneNumberOfConsignee((String) cellValue);
			break;
		case "下单人手机号":// 下单人手机号 String phoneNumberOfCustom
			orderInfo.setPhoneNumberOfCustom((String) cellValue);
			break;
		case "下单会员等级折扣":// 下单会员等级折扣 String discountGrade
			orderInfo.setDiscountGrade((String) cellValue);
			break;
		case "订单会员权益":// 订单会员权益 String perksOfMembershi
			orderInfo.setPerksOfMembershi((String) cellValue);
			break;
		case "商品名称":// 商品名称 String nameOfCommodity
			orderInfo.setNameOfCommodity((String) cellValue);
			break;
		case "场次Id":// 场次Id String performanceId
			orderInfo.setPerformanceId((String) cellValue);
			break;
		case "场次名称":// 场次名称 String nameOfPerformance
			orderInfo.setNameOfPerformance((String) cellValue);
			break;
		case "馆厅":// 馆厅 String ieldOfPerformance
			orderInfo.setIeldOfPerformance((String) cellValue);
			break;
		case "演出日期":// 演出日期 Date dateOfPerformance
			Date dateOfPerformance = null;
			if(cellValue instanceof String){
				dateOfPerformance = DateFormatUtil.parseToDate((String) cellValue);
			}else if(cellValue instanceof Date){
				dateOfPerformance = (Date) cellValue;
			}
			orderInfo.setDateOfPerformance(dateOfPerformance);
			break;
		case "票价":// 票价=单价*张数 
			/*
			 * 此处做特殊处理
			 * 票价实际为两部分, 票价=单价*张数 
			 * 单价 double priceOfTicket 
			 * 张数 int numberOfTicket
			 */
			String pricesCellValue = (String) cellValue;
			//返回详细票价信息list
			pricesList = getDetailedPriceList(pricesCellValue);
			//将第一种票价信息赋值
			orderInfo.setPriceOfTicket(pricesList.get(0).getPrice());
			orderInfo.setNumberOfTicket(pricesList.get(0).getNum());
			break;
		case "应收":// 应收 double shouldPayment
			orderInfo.setShouldPayment((Double) cellValue);
			break;
		case "实收":// 实收=应收*折扣 double actualPayment
			orderInfo.setActualPayment((Double) cellValue);
			break;
		}
		return pricesList;
	}
	/**
	 * 处理票价，返回 单价*张数 的List
	 * 主要是为了处理形如"100*2 300*1"多种票价的情况
	 * @param pricesCellValue
	 * @return
	 */
	public static List<DetailedPriceInfo> getDetailedPriceList(String pricesCellValue){
		List<DetailedPriceInfo> list = new ArrayList<DetailedPriceInfo>();
		String[] pricesArray = pricesCellValue.split(" ");
		for(int i=0;i<pricesArray.length;i++){
			String[] detailedPriceArray = pricesArray[i].split("\\*");
			DetailedPriceInfo dpi = new DetailedPriceInfo();
			dpi.setPrice(Double.parseDouble(detailedPriceArray[0]));
			dpi.setNum(Integer.parseInt(detailedPriceArray[1]));
			list.add(dpi);
		}
		return list;
	}
}
