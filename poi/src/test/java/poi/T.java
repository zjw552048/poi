package poi;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.z.core.dao.OrderInfoMapper;
import com.z.util.readUtil.ReadExcelTypeA;
import com.z.util.readUtil.ReadExcelTypeB;

public class T {
	@Test
	
	public void test1() throws IOException{
		ReadExcelTypeA.insertData();
		ReadExcelTypeB.insertData();
		ReadExcelTypeA.countData();
		ReadExcelTypeA.countTotalActualPayment();
		
		SqlSession session = ReadExcelTypeA.getSqlSession();
		OrderInfoMapper mapper = session.getMapper(OrderInfoMapper.class);
		System.err.println(mapper.getTotalActualPayment("共话昆曲《长生殿》"));
	}
}
