package com.netposa.gat.utils;

public class ExceptionUtils {

	/**
	 * 捕获Exception详细信息
	 * 
	 * @param ex
	 * @return
	 */
	public static String ExceptionMessage(Exception ex) {
		String msg = ex.getMessage();
		msg += "\r\n" + ex.getClass().getName();
		StackTraceElement[] messages = ex.getStackTrace();
		int length = messages.length;
		for (int i = 0; i < length; i++) {
			msg += "\r\n" + messages[i].toString();
		}
		return msg;
	} 
}
