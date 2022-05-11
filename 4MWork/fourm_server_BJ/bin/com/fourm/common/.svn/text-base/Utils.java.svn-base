package com.fourm.common;

public class Utils {
	
	public static final String E001  = "E001";//数据文件被删除{NAME}
	public static final String E002  = "E002";//文件名不合法{NAME}
	public static final String E003  = "E003";//数据行为空{NUM}
	public static final String E004  = "E004";//数据列个数错{NUM}
	public static final String E005  = "E005";//获取设备振动信号彩集点失败
	public static final String E006  = "E006";//未取得监测时间在X行{X}
	public static final String E007  = "E007";//数据库异常{SQL+VALUE}
	public static final String E008  = "E008";//channels字段为非数字
	public static final String E009  = "E009";//文件内容实际列数大于已配置振动采集点个数
	public static final String E999  = "E999";//未知异常{StackTrace}

	public static final String DBURL = "ibsdbDataSource.url";
	public static final String DBUSER = "ibsdbDataSource.username";
	public static final String DBPASS = "ibsdbDataSource.password";

	public static final String DATA = "tcpserver.dataPath";
	public static final String HIST = "tcpserver.histPath";
	public static final String LVM = "tcpserver.lvmPath";
	public static final String CRONEXP = "server.cron";
	
	public static String printStackTrace(Exception e) {
		StringBuilder  str = new StringBuilder();
		str.append(e.getClass().getName() + ": " + e.getMessage()+"\n");
		StackTraceElement[] elements = e.getStackTrace();
		for(StackTraceElement element : elements){
			str.append("\tat "+element.toString()+"\n");
		}
		return str.toString();
    }
}