package poi;

import java.io.IOException;

import org.junit.Test;

import com.z.util.readUtil.ReadExcelTypeA;
import com.z.util.readUtil.ReadExcelTypeB;

public class T {
	@Test
	
	public void test1() throws IOException{
		ReadExcelTypeA.insertData();
		ReadExcelTypeB.insertData();
		ReadExcelTypeA.countData();
		ReadExcelTypeA.countTotalActualPayment();
	}
}
