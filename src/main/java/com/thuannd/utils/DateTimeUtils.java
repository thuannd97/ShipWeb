package com.thuannd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {

	public static String DD_MM_YYYY_HH_MM = "dd/MM/yyyy HH:mm";
	public static String DD_MM_YYYY = "dd/MM/yyyy";

	public static Date parseDate(String date, String type) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException();
		}
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
