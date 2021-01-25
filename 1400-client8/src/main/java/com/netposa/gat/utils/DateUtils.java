package com.netposa.gat.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;

/**
 * 日期助手
 * 
 * @author amx
 * 
 */
public class DateUtils {
	private static Logger log = LoggerFactory.getLogger(DateUtils.class);
	public static final TimeZone TimeZoneGMT8 = TimeZone.getTimeZone("GMT+8");
	final static int SecondMilliSecond = 1000;

	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public static final String DateFormatCNSSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DateFormatCN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String DateFormatEN = "yyyy/MM/dd HH:mm:ss";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DateFormatNoSpan = "yyyyMMddHHmmss";
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final String DateFormatLongNoSpan = "yyyyMMddHHmmssSSS";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DateFormat = "yyyy-MM-dd";
	/**
	 * HH:mm:ss
	 */
	public static final String TimeFormat = "HH:mm:ss";

	public static Date GetDate() {
		return new Date();
	}

	public static Date AddSecond(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}
	
	public static Date AddMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	public static long GetTimeSpanSeconds(Date start, Date end) {
		long RetVal = end.getTime() - start.getTime();
		RetVal /= (SecondMilliSecond);
		return RetVal;
	}

	/**
	 * 去除字符串的中文字符
	 * 
	 */
	private static String subStrForMath(String str) {
		String string = "";
		for (int i = 0; i < str.length(); i++) {
			String str0 = "";
			if (!str.substring(i, i + 1).matches("[u4e00-u9fa5]+")) {
				if (str.substring(i, i + 1).matches("/") || str.substring(i, i + 1).matches(" ")) {
					str0 = str.substring(i, i + 1) + "";
				}
			} else {
				str0 = str.substring(i, i + 1) + "";
			}
			string += str0;
		}
		return string;
	}

	/**
	 * 字符串转化成时间
	 * 
	 * @param strDate
	 *            "yyyy-MM-dd HH:mm:ss" "yyyyMMddHHmmss" "yyyyMMddHHmmssSSS"
	 * @return
	 */
	public static Date Convert2Date(String strDate) {
		strDate = strDate.trim();
		if (strDate.indexOf(':') < 0) {
			if (strDate.length() == DateFormatNoSpan.length()) {
				return Convert2Date(strDate, DateFormatNoSpan);
			} else if (strDate.length() == DateFormatLongNoSpan.length()) {
				return Convert2Date(strDate, DateFormatLongNoSpan);
			} else if (strDate.length() > DateFormatNoSpan.length()) {
				return Convert2Date(strDate.substring(0, DateFormatNoSpan.length()), DateFormatNoSpan);
			}
		}

		if (strDate.indexOf('/') > 0) {
			if (strDate.length() > DateFormatEN.length()) {
				strDate = subStrForMath(strDate);
			}
			return Convert2Date(strDate, DateFormatEN);
		}

		return Convert2Date(strDate, DateFormatCN);

	}

	/**
	 * 字符串转换时
	 * 
	 * @param strDate
	 * @param strDateFormat
	 * @return
	 */
	public static Date Convert2Date(String strDate, String strDateFormat) {
		Date retValue = new Date();
		SimpleDateFormat sdf = getDateFormat(strDateFormat);
		try {
			if (sdf != null) {
				retValue = sdf.parse(strDate);
			}
		} catch (ParseException e) {
			log.error(ExceptionUtils.ExceptionMessage(e));
		}
		return retValue;
	}

	/**
	 * 将日期转换为字符 "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 * @return
	 */
	public static String Convert2String(Date date) {
		return Convert2String(date, DateFormatCN);
	}

	/**
	 * 按格式strDateFormat 将日期转换为字符
	 * 
	 * @param date
	 * @param strDateFormat
	 * @return
	 */
	public static String Convert2String(Date date, String strDateFormat) {
		String retValue = "";
		SimpleDateFormat sdf = getDateFormat(strDateFormat);
		try {
			if (sdf != null) {
				retValue = sdf.format(date);
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.ExceptionMessage(e));
		}
		return retValue;
	}

	private static SimpleDateFormat getDateFormat(String strDateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		try {
			if (!TimeZoneGMT8.equals(sdf.getTimeZone())) {
				sdf.setTimeZone(TimeZoneGMT8);
			}
			return sdf;
		} catch (Exception e) {
			log.error(ExceptionUtils.ExceptionMessage(e));
			return null;
		}
	}

	private static DateFormat dfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 比较2个日期大小,第一个参数值是否比第二个参数值小
	 * 
	 * @param DATE1
	 * @param DATE2
	 * @return true 第一个参数值比第二个参数值大，否则返回小
	 * @throws ParseException
	 */
	public static boolean compareToDate(String strDATE1, String strDATE2) throws ParseException {
		Date dt1 = null;
		if (strDATE1.length() > 19) {
			dt1 = dfTime.parse(strDATE1);
		} else {
			dt1 = df.parse(strDATE1);
		}

		Date dt2 = null;
		if (strDATE2.length() > 19) {
			dt2 = dfTime.parse(strDATE2);
		} else {
			dt2 = df.parse(strDATE2);
		}

		if (dt1.getTime() > dt2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 比较两个日期天数
	 * 
	 * @param date1
	 * @param date2
	 * @return true:date1比date2;否则相反
	 */
	public static boolean compareDays(Date date1, Date date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String strLate = Convert2String(date1, "yyyy-MM-dd");
			Date lateDate = df.parse(strLate);
			String strEarly = Convert2String(date2, "yyyy-MM-dd");
			Date earlyDate = df.parse(strEarly);
			if (lateDate.getTime() > earlyDate.getTime()) {
				return true;
			}
			return false;
		} catch (Exception exception) {
			return false;
		}
	}

	public static int GetYear() {
		return GetYear(GetDate());
	}

	public static int GetYear(Date date) {
		return GetDateTypeValue(DateType.YEAR_, date);
	}

	public static int GetMonth() {
		return GetMonth(GetDate());
	}

	public static int GetMonth(Date date) {
		return GetDateTypeValue(DateType.MONTH_, date);
	}

	public static int GetDay() {
		return GetDay(GetDate());
	}

	public static enum DateType {
		YEAR_, MONTH_, DAY_, HOUR_, MINUTE_, SECOND_;
	}

	public static int GetDay(Date date) {
		// calendar.setTime(date);
		// return calendar.get(Calendar.DAY_OF_MONTH);
		return GetDateTypeValue(DateType.DAY_, date);
	}

	private static int GetDateTypeValue(DateType RetType, Date date) {
		int RetVal = -1;
		String dateStr = Convert2String(date, DateFormatNoSpan);
		if (dateStr != null && dateStr.length() == DateFormatNoSpan.length()) {
			switch (RetType) {
			case YEAR_:
				RetVal = Integer.parseInt(dateStr.substring(0, 4));
				break;
			case MONTH_:
				RetVal = Integer.parseInt(dateStr.substring(4, 6));
				break;
			case DAY_:
				RetVal = Integer.parseInt(dateStr.substring(6, 8));
				break;
			case HOUR_:
				RetVal = Integer.parseInt(dateStr.substring(8, 10));
				break;
			case MINUTE_:
				RetVal = Integer.parseInt(dateStr.substring(10, 12));
				break;
			case SECOND_:
				RetVal = Integer.parseInt(dateStr.substring(12));
				break;
			default:
				break;
			}
		}
		return RetVal;
	}

	public static Date AddHour(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}

	public static Date AddDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public static Date AddMonths(Date date, int Months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, Months);
		return calendar.getTime();
	}

	public static Date AddYears(Date date, int Years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, Years);
		return calendar.getTime();
	}

	/**
	 * 将时间戳转换为时间
	 * 
	 * @param s
	 * @return
	 */
	public static String stampToDate(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @throws ParseException
	 */
	public static String dateToStamp(String s) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}
}
