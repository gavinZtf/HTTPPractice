package com.gavin.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @功能  threadLocal 例子教程
 * @作者 Gavin
 * @版本 1.0
 * @时间 2018年4月27日
 */
public class ThReadLocalDateUtil {

	private static final String date_format = "yyyy-MM-dd HH:mm:ss";
	
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();
	
	/**
	 * @功能  获取本地线程局部变量
	 * @作者 Gavin
	 */
	public static DateFormat getDateFormat(){
		DateFormat df = threadLocal.get(); 
		if(null == df){
			df = new SimpleDateFormat(date_format);
			threadLocal.set(df);
		}
		return df;
	}
	
	
		
}
