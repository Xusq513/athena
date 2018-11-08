package com.refutrue.athena.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String date2Str(Date date,String formatter) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(formatter);
		try {
			result = formater.format(date);
		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * 把符合日期格式的字符串转换为日期类型
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date stringtoDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			// log.error(e);
			d = null;
		}
		return d;
	}
	
	/**
	 * 计算两个日期相差的天数，如果date1 > date2 返回正数，否则返回负数
	 * 
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 * @return long
	 */
	public static long dayDiff(Date date1, Date date2) {
		return (date1.getTime() - date2.getTime()) / (1000*24*60*60);
	}
}
