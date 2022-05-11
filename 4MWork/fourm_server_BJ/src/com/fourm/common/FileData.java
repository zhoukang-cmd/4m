package com.fourm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * 数据文件表示
 * @author zhangtaichao , Mobile Bank System, CSII
 * modified by wangzhe 增加方法猜测时间格式
 * <p>created on 2012-3-4 </p>
 */
public class FileData {
	public static final String HEADER_STR = "***End_of_Header***";
	public static final String SUFFIX = ".lvm";
	public static final String SUFFIX_DAT = ".dat";
	public static final String SPLIT_STR = "\\t";
	public static final String LSPLIT = " |\\t";//非振动数据文件分隔符
	public static final String FILENAME_SPLITSTR = "\\+";
	public static final String FILENAME_DATE_FMT_SEC = "yyyyMMddHHmmss";//精确到秒
	public static final String FILENAME_DATE_FMT_MIN = "yyyyMMddHHmm";//精确到分钟
	public static final String FILENAME_DATE_FMT_HOUR = "yyyyMMddHH";//精确到小时
	public static final SimpleDateFormat TIME_FMT = new SimpleDateFormat("HH:mm:ss");
	public static final int COLUMN_NUM = 8;
	public String province;
	public String company;
	/**
	 * 矿区
	 */
	public String mine;
	public String room;
	public String equipName;
	public int equipCode;
	public String fileType;
	public Date collectDate;
	public String Writer_Version;
	public String Reader_Version;
	public String Separator;
	public String Decimal_Separator;
	public String Multi_Headings;
	public String X_Columns;
	public String Time_Pref;
	public String Operator;
	public String Date1;
	public String Time1;
	public String Channels;
	
	public String[] Samples;
	public String[] Date2;
	public String[] Time2;
	public String[] Y_Unit_Label;
	public String[] X_Dimension;
	public String[] X0;
	public String[] Delta_X;
	public String[] X_Value;
	
	/**
	 * 
	 * @return 监测时间的秒和微秒
	 */
	public Calendar getStartmmS() {
		if(Time2 == null || Time2.length == 0) {
			return null;
		}
		String time = Time2[0];
		Calendar c = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat(guessPattern(time));
		try {
			c.setTime(fmt.parse(time));
		} catch (ParseException e) {
			return null;
		}
		return c;
	}
	
	/**
	 * 猜测时间格式
	 * @param time
	 * @return
	 */
	public static String guessPattern(String time) {
		String[] timeElems = time.split("\\:");
		if ( timeElems.length > 2 ) {
			//含有小时
			return "HH:mm:ss.SSSSS";
		} else if ( timeElems.length == 2 ) {
			//不含小时
			return "mm:ss.SSSSS";
		} else {
			//没有冒号也叫时间？！
			throw new RuntimeException("LVM文件头time不合规范(没有冒号):["+ time +"]!");
		}
	}
	
	public static void main(String[] args) {
		//String[] ss = new String[] {"Decimal_Separator","Multi_Headings","X_Columns","Time_Pref","Operator"
		//		,"Date","Time","Channels"};
		String[] ss2 = new String[] {"Samples","Date","Time","Y_Unit_Label","X_Dimension"
				,"X0","Delta_X"};
		for(String s : ss2) {
			System.out.println("public String[] " + s + ";");
		}
	}



	@Override
	public String toString() {
		return "FileData ["
				+ (province != null ? "province=" + province + ", " : "")
				+ (company != null ? "company=" + company + ", " : "")
				+ (mine != null ? "mine=" + mine + ", " : "")
				+ (room != null ? "room=" + room + ", " : "")
				+ (equipName != null ? "equipName=" + equipName + ", " : "")
				+ (equipCode != -1 ? "equinCode=" + equipCode + ", " : "")
				+ (collectDate != null ? "collectDate=" + collectDate + ", "
						: "")
				+ (Writer_Version != null ? "Writer_Version=" + Writer_Version
						+ ", " : "")
				+ (Reader_Version != null ? "Reader_Version=" + Reader_Version
						+ ", " : "")
				+ (Separator != null ? "Separator=" + Separator + ", " : "")
				+ (Decimal_Separator != null ? "Decimal_Separator="
						+ Decimal_Separator + ", " : "")
				+ (Multi_Headings != null ? "Multi_Headings=" + Multi_Headings
						+ ", " : "")
				+ (X_Columns != null ? "X_Columns=" + X_Columns + ", " : "")
				+ (Time_Pref != null ? "Time_Pref=" + Time_Pref + ", " : "")
				+ (Operator != null ? "Operator=" + Operator + ", " : "")
				+ (Date1 != null ? "Date1=" + Date1 + ", " : "")
				+ (Time1 != null ? "Time1=" + Time1 + ", " : "")
				+ (Channels != null ? "Channels=" + Channels + ", " : "")
				+ (Samples != null ? "Samples=" + Arrays.toString(Samples)
						+ ", " : "")
				+ (Date2 != null ? "Date2=" + Arrays.toString(Date2) + ", "
						: "")
				+ (Time2 != null ? "Time2=" + Arrays.toString(Time2) + ", "
						: "")
				+ (Y_Unit_Label != null ? "Y_Unit_Label="
						+ Arrays.toString(Y_Unit_Label) + ", " : "")
				+ (X_Dimension != null ? "X_Dimension="
						+ Arrays.toString(X_Dimension) + ", " : "")
				+ (X0 != null ? "X0=" + Arrays.toString(X0) + ", " : "")
				+ (Delta_X != null ? "Delta_X=" + Arrays.toString(Delta_X)
						+ ", " : "")
				+ (X_Value != null ? "X_Value=" + Arrays.toString(X_Value) : "")
				+ "]";
	}



	
	
}
