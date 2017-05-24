package com.njfu.surveypark.util;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * ��־������
 * @author CK
 * 2015��4��14������4:38:54
 */
public class LogUtil {
	/**
	 * ������־������:logs_2013_9 
	 * @param offset :ƫ����
	 * @return
	 */
	public static String generateLogTableName(int offset) {
		Calendar c = Calendar.getInstance();
		// 2015
		int year = c.get(Calendar.YEAR);
		// 0 -11
		int month = c.get(Calendar.MONTH) + 1 + offset;		
		if(month > 12){
			year ++ ;
			month = month - 12 ;
		}
		if(month < 1){
			year -- ;
			month = month + 12 ;
		}
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("00");
		return "logs_" + year + "_" + df.format(month) ;
	}

}
