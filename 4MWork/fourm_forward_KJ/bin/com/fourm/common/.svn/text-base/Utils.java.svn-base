package com.fourm.common;

public class Utils {
	public static final String CLIENT_ID = "client.id";
	public static final String CLIENT_SOURCE = "client.sourcePath";
	public static final String CLIENT_CRON = "client.cron";
	public static final String CLIENT_SERVERS = "client.serverList";
	public static final String CLIENT_FTPHOST = "ftp.serverUrl";
	public static final String CLIENT_FTPPORT = "ftp.serverPort";
	public static final String CLIENT_FTPUSERNAME = "ftp.username";
	public static final String CLIENT_FTPPASSWORD = "ftp.password";
	public static final String CLIENT_FTPPATH = "ftp.serverPath";
	public static final String CLIENT_TCPHOST = "tcpserver.host";
	public static final String CLIENT_TCPPORT = "tcpserver.port";
	
	/**
	 * 是否是违反AK_FOURM_BAT约束异常
	 * @param e
	 * @return
	 */
	public static boolean isAK_FOURM_BAT(String msg) {
		if (( msg.indexOf("唯一索引") != -1 ) || //for MSSQL2008
			( msg.indexOf("unique constraint") != -1)){ //for oracle
			return true;
		} else {
			return false;
		}
	}
	
	public static String printStackTrace(Throwable e) {
		StringBuilder  str = new StringBuilder();
		str.append(e.getClass().getName() + ": " + e.getMessage()+"\n");
		StackTraceElement[] elements = e.getStackTrace();
		for(StackTraceElement element : elements){
			str.append("\tat "+element.toString()+"\n");
		}
		return str.toString();
    }
}
