package com.fourm.client.task;

import java.util.concurrent.Callable;

import org.apache.commons.net.ftp.FTPClient;

import com.fourm.common.FourmFtpClient;

/**
 * 在一个线程中执行FTP任务，并在调用时限制执行时间。
 * 这样做是为了解决FTP连接假死又不抛出异常的问题。
 */

public class FutureTransferTask implements Callable<Boolean> {

	private FourmFtpClient fourmftp;
	private FTPClient ftpclient;
	private String zipPath;
	private String serverPath;
	
	public FutureTransferTask(FourmFtpClient fourmftp, String zipPath, String serverPath) {
		this.fourmftp = fourmftp;
		this.zipPath = zipPath;
		this.serverPath = serverPath;
	}
	
	public Boolean call() throws Exception {
		ftpclient = fourmftp.loginToFtpServer();
		fourmftp.putToServer(ftpclient, this.zipPath, this.serverPath);
		ftpclient.logout();
		ftpclient.disconnect();
		return true;
	}	
}
