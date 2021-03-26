package com.supermarket.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.crypto.Data;

public class GetDate {
	public static String getDateTime() {

		SimpleDateFormat format;
		Date date = null;
		Calendar myDate = Calendar.getInstance();
		myDate.setTime(new java.util.Date());
		date = myDate.getTime();
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strRtn = format.format(date);
		return strRtn;

	}
}
