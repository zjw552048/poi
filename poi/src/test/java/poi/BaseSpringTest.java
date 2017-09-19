package poi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.z.core.dao.OrderInfoMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class BaseSpringTest {
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Test
	public void test1(){
		
	}
}
