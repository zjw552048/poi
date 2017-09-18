package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class PropertyUtil {
	public static Map<String, String> getPropMap(String propName) throws IOException{
		if (propName == null || "".equals(propName.trim())) {  
            throw new IllegalArgumentException("The property name is null,return");  
        }  
		Map<String, String> map = new HashMap<String, String>();
		//读取配置文件
		InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream(propName);
		InputStreamReader isr = new InputStreamReader(is,"utf-8");
		Properties prop = new Properties();
		prop.load(isr);
		
		Set<Entry<Object, Object>> entrySet = prop.entrySet();
		String key;
		String value;
		for(Entry<Object, Object> entry :entrySet){
			key = (String) entry.getKey();
			value = (String) entry.getValue();
			map.put(key, value);
		}
		return map;
	}
	public static void main(String[] args) {
		try {
			System.out.println(getPropMap("column.properties"));;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(1);
	}
}
