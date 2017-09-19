package com.z.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	public static Date parseToDate(String dateString) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdf.parse(dateString);
		return date;
	}
	public static void main(String[] args) {
		try {
			System.out.println(parseToDate("2017-09-08 14:05"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
