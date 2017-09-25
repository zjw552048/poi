package com.z.util.readUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.z.core.dao.OrderInfoMapper;
import com.z.core.entity.OrderInfo;
import com.z.util.PropertyUtil;
import com.z.util.readCellUtil.ReadExcelCellUtilForTypeB;

/**
 * 读取渠道订单数据,统一9折
 * @author Z
 *
 */
public class ReadExcelTypeB extends BaseReadExcel{
	public static void main(String[] args) throws IOException{
		insertData();
    }
	/**
	 * 插入数据
	 * @throws IOException
	 */
	public static void insertData() throws IOException{
		String propName = "properties/columnOfTypeB.properties";
    	String fileName = "20170926/渠道订单-查询20170925 23%3A03.xls";
    	
    	List<OrderInfo> list = readSheet(propName, fileName);
    	System.out.println(list.size());
    	
    	SqlSession sqlSession = getSqlSession();
    	OrderInfoMapper mapper = sqlSession.getMapper(OrderInfoMapper.class);
    	for(OrderInfo bean : list){
//    		System.out.println(bean);
    		mapper.addOrderInfo(bean);
//    		System.out.println("success");
    	}
    	sqlSession.commit();
    	
	}
	
	/**
	 * 读取工作簿内容
	 * @param propName
	 * @param fileName
	 */
	public static List<OrderInfo> readSheet(String propName, String fileName){
		try {
			List<OrderInfo> list = new ArrayList<OrderInfo>();
			Sheet sheet = getSheet(fileName, 0);
			//获取excel标题数组
			String[] columnTitleArray = getSheetColumnTitleArray(sheet);
			//获取配置文件允许的标题map
			Map<String, String> columnTitleMap = PropertyUtil.getPropMap(propName);
			System.out.println(columnTitleMap);
			//获取最后一行
			int lastRow = sheet.getLastRowNum();
			//跳过标题,从第二行开始读取数据
			int currentRow = 1;
			Row row;
			for(int i=0;i<lastRow;i++){
				OrderInfo orderInfo = new OrderInfo();
				row = sheet.getRow(currentRow++);
				for(int j=0;j<columnTitleArray.length;j++){
					//设置数据源类型
					orderInfo.setOrderType("B");
					//进入工具类，根据标题设置bean属性
					ReadExcelCellUtilForTypeB.setOrderInfoBeanAttribute(orderInfo, row, columnTitleArray[j], columnTitleMap);
				}
				//拆分并返回拆分后的list，添加到结果集合里
				list.addAll(ReadExcelCellUtilForTypeB.getMultiplePriceOrderInfo(orderInfo));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
