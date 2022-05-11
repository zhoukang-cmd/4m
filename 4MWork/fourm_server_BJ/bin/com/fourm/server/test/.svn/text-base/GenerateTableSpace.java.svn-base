package com.fourm.server.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateTableSpace {

	/**
	 * 
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		printPartition();

	}
	public static void printTableSpace() throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		
		Calendar c = Calendar.getInstance();
		c.setTime(fmt.parse("20130101"));
		Calendar future = Calendar.getInstance();
		future.setTime(fmt.parse("20160102"));
		while(c.before(future)) {
			String tmp = fmt.format(c.getTime());
			System.out.println("CREATE SMALLFILE TABLESPACE \"FOURM_" + tmp + "\" DATAFILE 'D:\\ORACLE\\PRODUCT\\10.2.0\\ORADATA\\ORCL\\FOURM_DATA_" + tmp + "' SIZE 10M AUTOEXTEND ON NEXT 1024K MAXSIZE UNLIMITED LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO ;");
			c.add(Calendar.MONTH, 3);
		}
	}
	public static void printPartition() throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		
		Calendar c = Calendar.getInstance();
		c.setTime(fmt.parse("20130101"));
		Calendar future = Calendar.getInstance();
		future.setTime(fmt.parse("20160102"));
		while(c.before(future)) {
			String tmp = fmt.format(c.getTime());
			System.out.println("partition $TABLENAME$_" + tmp + " values less than(to_date('" + tmp + "','yyyymmdd')) tablespace FOURM_" + tmp + ",");
			c.add(Calendar.MONTH, 3);
		}
	}
}
