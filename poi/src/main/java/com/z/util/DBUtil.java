package com.z.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.mysql.cj.jdbc.Driver;




public class DBUtil {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		Map<String, String> map = PropertyUtil.getPropMap("properties/jdbc.properties");
		
//		BasicDataSource dbs = new BasicDataSource();
		String driverClass = map.get("driverClass");
		String url = map.get("url");
		String username = map.get("username");
		String password = map.get("password");
		
//		Class.forName(driverClass);
		Driver driver = new Driver();
		
		Connection connection = DriverManager.getConnection(url, username, password);
		Statement s = connection.createStatement();
		String query = "select * from POI.order_info";
		ResultSet rs = s.executeQuery(query);
		while(rs.next()){
			System.out.println(rs);
		}
		
		
	}
}
