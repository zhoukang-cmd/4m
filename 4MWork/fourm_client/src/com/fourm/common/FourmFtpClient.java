package com.fourm.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * modified by wangzhe 20121007 ????FTPClient?????????????????????????
 *   
 */
public class FourmFtpClient {
	
	private static  Logger logger = LoggerFactory.getLogger(FourmFtpClient.class);	
    private String serverUrl;
    private String userName;
    private String password;
    private int serverPort;
    private String encoding = "UTF-8";
   
    public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
    public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

   
	public FourmFtpClient(){}
    public FourmFtpClient(String serverUrl,String userName,String passWord,int ftpPort){
    	this.serverUrl = serverUrl;
    	this.userName = userName;
    	this.password = passWord;
    	this.serverPort = ftpPort;
    	this.encoding = "UTF-8";
    }
  
    /**
     * @param ftp ftp????????
     * @param src ????????¡¤??
     * @param serverPath ????????????
     * @throws Exception 
     */
    public void putToServer(FTPClient ftp, String src, String serverPath) throws Exception {
    	File lf = new File(src);
    	if(!lf.exists()) {
    		logger.error("putToServer fail,file not exists:" + src);
    		return ;
    	}
    	logger.debug("putToServer()--> start to transfer go :" + src);
    	FileInputStream fin = null;
    	ByteArrayInputStream bin = null;
    	try {
    		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
    		ftp.setControlKeepAliveTimeout(60);
    		ftp.setDataTimeout(30000);
    		ftp.setDefaultTimeout(30000);
    		fin = new FileInputStream(lf);
    		ftp.changeWorkingDirectory(serverPath);
    		logger.debug("store file begin...");
    		final long filesize = lf.length();
    		ftp.setCopyStreamListener(new CopyStreamListenerImpl(filesize));
    		boolean result = ftp.storeFile(lf.getName(), fin);
    		logger.debug("store file end...");
    		if(!result) {
    			throw new Exception("storeFile result: false; errorcode:" + ftp.getReplyCode());
    		}
    		String ok = lf.getName() + ".ok";
    		bin = new ByteArrayInputStream(ok.getBytes());
    		logger.debug("store okfile begin...");
    		
    		ftp.storeFile(ok, bin);
    		
    		logger.debug("store okfile end...");
    		bin.close();
    		logger.debug("putToServer()--> transfer done :" + result);
    	} catch(Exception e) {
    		logger.error("putToServer fail:" + src + "\n" + Utils.printStackTrace(e));
    		throw e;
    	} finally {
    		if(fin != null) {
    			try {
					fin.close();
					fin = null;
				} catch (IOException e) {
				}
    		}
    		if(bin != null) {
    			try {
    				bin.close();
    				bin = null;
    			} catch(IOException e) {
    			}
    		}
    	}
    }

	public FTPClient loginToFtpServer() throws Exception {
		return this.loginToFtpServer(serverUrl, userName, password);
	}

	private FTPClient loginToFtpServer(String serverUrl,String userName, String passWord) throws Exception {
		FTPClient ftpClient = new FTPClient();
		try {
			int reply;
			logger.debug("start to connect to ftp server:"+ getServerUrl()+","+getUserName()+","+getPassword());
			ftpClient.setConnectTimeout(10000);
			ftpClient.connect(getServerUrl());
			ftpClient.enterLocalPassiveMode();
			reply = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)) {
		        ftpClient.disconnect();
		        logger.error("FTP server refused connection.");
		        return null;
		      }
			boolean login = ftpClient.login(getUserName(), getPassword());
			if(!login) {
				throw new Exception("login to ftpserver failed:username=" + getUserName() + "  " + "password:" + getPassword());
			}
			return ftpClient;
		} catch (Exception e) {
			logger.error("??????ftp??????"+ getServerUrl()+","+getUserName()+","+getPassword() +"????"+e.getMessage());
			throw e;
		}
	}
 
	class CopyStreamListenerImpl implements CopyStreamListener 
	{
		private Logger logger = LoggerFactory.getLogger(CopyStreamListenerImpl.class);
		private long filesize ;
		CopyStreamListenerImpl(long filesize) {
			this.filesize = filesize;
		}
		public void bytesTransferred(long l, int i, long l1) {
			if( l<1024 || l % 1024 == 0 ) {
				if(l1 == -1) {
					logger.debug(l + "Bytes transferd total " + this.filesize + "Bytes");
				} else {
					logger.debug(l + "Bytes transferd total " + l1 + "Bytes");
				}
			}
		}
		
		public void bytesTransferred(CopyStreamEvent event) {
			bytesTransferred(event.getTotalBytesTransferred(), event.getBytesTransferred(), event.getStreamSize());
		}
	}
}


