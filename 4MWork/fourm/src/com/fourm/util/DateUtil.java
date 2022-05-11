package com.fourm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {

	private static Log log = LogFactory.getLog(DateUtil.class);

	/**
	 * 将字符串格式化为日期对象
	 * 
	 * @param date
	 * @param format
	 * @return 如果date为空或格式不标准，返回NULL，否则返回对应的日期对象
	 */
	public static Date formatToDate(String date, String format) {
		try {
			if (StringUtils.isBlank(date)) {
				return null;
			}

			SimpleDateFormat sorceFmt = new SimpleDateFormat(format);
			return new Date(sorceFmt.parse(date).getTime());
		} catch (ParseException e) {
			log.warn("invalid date :" + date + " ,Default format: yyyy-MM-dd ");
			return null;
		}
	}

	/**
	 * 按自定义日期格式格式化日期
	 * 
	 * @param target
	 * @param format
	 * @return 格式化后的日期字符串，如果传入的日期对象为NULL，返回空字符串
	 */
	public static String formatDate(Date target, String format) {
		if (target == null) {
			return "";
		}
		return new SimpleDateFormat(format).format(target);
	}

	/**
	 * 获得当前时间的n天前或后
	 * 
	 * @param origin
	 * @param intervals
	 * @return
	 */
	public static Date getIntervalDate(Date origin, long intervals) {
		return new Date(origin.getTime() + intervals * 86400000);
	}

	//得到下一秒的时间
	public static Date getNextDate(Date origin, long intervals) {
		return new Date (origin.getTime() + intervals * 1000);
	}
	//得到上一秒的时间
	public static Date getPrevDate(Date origin, long intervals) {
		return new Date (origin.getTime() - intervals * 1000);
	}

}
