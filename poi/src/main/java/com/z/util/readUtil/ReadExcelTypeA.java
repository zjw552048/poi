package com.z.util.readUtil;

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

import com.z.core.dao.OrderInfoMapper;
import com.z.core.entity.OrderInfo;
import com.z.util.PropertyUtil;
import com.z.util.readCellUtil.ReadExcelCellUtilForTypeA;

/**
 * 读取自主订单数据,根据是否爱乐卡计算是否95折
 * @author Z
 *
 */
public class ReadExcelTypeA extends BaseReadExcel{
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
	/**
	 * 插入数据
	 * @throws IOException
	 */
	public static void insertData() throws IOException{
		String propName = "properties/columnOfTypeA.properties";
    	String fileName = "订单查询20170919 22%3A52.xls";
    	
    	List<OrderInfo> list = readSheet(propName, fileName);
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
	
	/**
	 * 统计数据
	 * @throws IOException
	 */
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
					orderInfo.setOrderType("A");
					//进入工具类，根据标题设置bean属性
					ReadExcelCellUtilForTypeA.setOrderInfoBeanAttribute(orderInfo, row, columnTitleArray[j], columnTitleMap);
				}
				//拆分并返回拆分后的list，添加到结果集合里
				list.addAll(ReadExcelCellUtilForTypeA.getMultiplePriceOrderInfo(orderInfo));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
