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
}
