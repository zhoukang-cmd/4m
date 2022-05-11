package com.fourm.client.task;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fourm.common.FourmFtpClient;
import com.fourm.common.Utils;
import com.fourm.common.ZipUtil;

/**
 * 每天定时向服务端发送前一天的日志
 * @author yangaozhen
 */
public class SendLogTask {
	
	private Logger logger = LoggerFactory.getLogger(SendLogTask.class);
	private String clientId; //用于在服务端标识是哪个客户端发来的日志
	private FourmFtpClient ftpClient;
	private String serverPath;
	private File logFile;
	
	/*
	 * 处理方法
	 */
	public void process() {
		logger.info("Send Log file to server...");
		logFile = getLogFile("logback.xml");
		String zipFilePath = logFile.getAbsolutePath().replace(".log", ".") + clientId + ".zip";
		
		boolean success = false;
		int tryCount = 3;
		if (logFile != null && logFile.exists()) {
			ZipUtil.zip(logFile.getAbsolutePath(), zipFilePath);
			while(!success && tryCount > 0) {
				success = transfer(zipFilePath);
				--tryCount;
			};
		}
		if (success) {
			logger.info("Send Log file to server complete.");
		} else {
			logger.info("Send Log file failed.");
		}
		try {
			FileUtils.forceDelete(new File(zipFilePath));
		} catch (IOException e) {
		}
	}
	
	/*
	 * 取得日志文件路径
	 */
	private File getLogFile(String logbackFile) {
		try {
			//读取配置文件
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(logbackFile));
			Element rootElement = document.getDocumentElement();
		
			//读取配置文件的日志格式配置
			NodeList list = rootElement.getElementsByTagName("FileNamePattern");
			Element element = (Element) list.item(0);
			String fileNameFormat = element.getChildNodes().item(0).getNodeValue();
			
			//获得昨日日期
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			
			//替换字符串，得到文件路径
			Pattern filePattern = Pattern.compile("%d\\{([-\\w]+)\\}");
			Matcher dateMatcher = filePattern.matcher(fileNameFormat);
			if (dateMatcher.find()) {
				String datePattern = dateMatcher.group(0);
				String dateFormat = dateMatcher.group(1);
				String dateString = (new SimpleDateFormat(dateFormat)).format(calendar.getTime());
				String logFilePath = fileNameFormat.replace(datePattern, dateString).trim();
				return new File(logFilePath);
			} 
		} catch (Exception e) {
			logger.error("Get log file fail:" + Utils.printStackTrace(e));
		}
		return null;
	}
	
	/*
	 * 传送日志文件
	 */
	private boolean transfer(String filePath) {
		logger.debug("transfer(): start to transfer: " + filePath);
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			FutureTask<Boolean> task = new FutureTask<Boolean>(new FutureTransferTask(getFtpClient(), filePath, getServerPath()));
			executor.execute(task);
			
			try {
				@SuppressWarnings("unused")
				boolean result = task.get(300, TimeUnit.SECONDS);
			}catch(TimeoutException e) {
				task.cancel(true);
				throw new Exception("transfer file timeout");
			}finally {
				executor.shutdown();
				if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
					executor.shutdownNow();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage() + ":" + filePath);
			return false;
		} 

		logger.debug("transfer(): transfer success: " + filePath);
		return true;
	}

	public void setFtpClient(FourmFtpClient ftpClient) {
		this.ftpClient = ftpClient;
	}
	public FourmFtpClient getFtpClient() {
		return ftpClient;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setClientid(String clientid) {
		this.clientId = clientid;
	}
	public String getClientid() {
		return clientId;
	}	
}
