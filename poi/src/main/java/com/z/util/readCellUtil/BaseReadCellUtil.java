package com.z.util.readCellUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.z.core.entity.DetailedPriceInfo;
import com.z.exception.CellTypeIllegalException;

public class BaseReadCellUtil {
	/**
	 * 当前设定合法类型只有三种, BLANK\STRING
	 * @param cell
	 * @return
	 */
	protected static String getCellValue(Cell cell) {
		String cellValue = null;
        if(cell == null){
//        	System.out.println("this cell is null");
        	return null;
        }
//        System.out.println(cell.getCellTypeEnum());
        switch (cell.getCellTypeEnum()) {
        case BLANK://空单元格
        	cellValue = null;
        	break;
        case STRING://字符串单元格
        	cellValue = cell.getStringCellValue();
        	break;
        default:
        	System.out.println(cell.getCellTypeEnum());
        	System.out.println(cell.getNumericCellValue());
        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"]类型非法");
//        case NUMERIC://数值类型单元格，分为数值/日期
//        	if (DateUtil.isCellDateFormatted(cell)) {
//        		cellValue = cell.getDateCellValue();
//            } else {
//            	cellValue = cell.getNumericCellValue();
//            }
//        	break;
//        case FORMULA:
////        	cellValue = cell.getCellFormula();
////        	break;
//        case BOOLEAN:
////        	cellValue = cell.getBooleanCellValue();
////        	break;
//        case _NONE:
//        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"],cellType = _NONE");
//        case ERROR:
//        	throw new CellTypeIllegalException("单元格["+cell.getRowIndex()+","+cell.getColumnIndex()+"],cellType = ERROR");
        }
        return cellValue;  
	}
	
	/**
	 * 处理票价，返回 单价*张数 的List
	 * 主要是为了处理形如"100*2 300*1"多种票价的情况
	 * @param pricesCellValue
	 * @param separator1	票种数据之间分隔符
	 * @param separator2	票价张数之间分隔符
	 * @return
	 */
	protected static List<DetailedPriceInfo> getDetailedPriceList(String pricesCellValue, String separator1, String separator2){
		List<DetailedPriceInfo> list = new ArrayList<DetailedPriceInfo>();
		String[] pricesArray = pricesCellValue.split(separator1);
		for(int i=0;i<pricesArray.length;i++){
			String[] detailedPriceArray = pricesArray[i].split(separator2);
			DetailedPriceInfo dpi = new DetailedPriceInfo();
			dpi.setPrice(Double.parseDouble(detailedPriceArray[0]));
			dpi.setNum(Integer.parseInt(detailedPriceArray[1]));
			list.add(dpi);
		}
		return list;
	}
	
}
