package com.fourm.client.task;

import java.util.concurrent.Callable;

import org.apache.commons.net.ftp.FTPClient;

import com.fourm.common.FourmFtpClient;


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
		this.ftpclient = fourmftp.loginToFtpServer();
		fourmftp.putToServer(ftpclient, this.zipPath, this.serverPath);
		ftpclient.logout();
		ftpclient.disconnect();
		return true;
	}	
}
