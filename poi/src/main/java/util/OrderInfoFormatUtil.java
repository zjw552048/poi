package util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import entity.OrderInfo;
import exception.CellTypeIllegalException;

public class OrderInfoFormatUtil {
	public static void setOrderInfoAttribute(OrderInfo orderInfo, String columnTitle){
		
	}
	/**
	 * 当前设定合法类型只有三种, BLANK\STRING\NUMERIC
	 * @param cell
	 * @return
	 */
	private Object getCellValue(Cell cell) {
        Object cellValue = null;  
        switch (cell.getCellTypeEnum()) {
        case BLANK:
        	cellValue = null;
        	break;
        case STRING:
        	cellValue = cell.getStringCellValue();
        	break;
        case NUMERIC:
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
}
