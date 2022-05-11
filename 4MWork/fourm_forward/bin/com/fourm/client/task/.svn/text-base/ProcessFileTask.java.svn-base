package com.fourm.client.task;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fourm.common.TcpClient;
import com.fourm.common.FourmFtpClient;
import com.fourm.common.Utils;

/**
 * 服务端文件再次发送
 * 发送到多个目标地址
 * 发送后删除文件
 */
public class ProcessFileTask {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessFileTask.class);
	private Properties properties;
	private String clientid;//该变量暂时没有用
	private String sourcePath;
	private String serverList;
	private String suffix = "";
		
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@SuppressWarnings("unchecked")
	public void process() {
		try {
			logger.debug("processFileTask begin...");
			pathCheck();
			
			File sourceDir = new File(getSourcePath());
			Collection<File> c = FileUtils.listFiles(sourceDir,getSuffix().split(","), false);

			for(Iterator<File> i = c.iterator();i.hasNext();) {
				File f = i.next();
				String name = f.getName();

				Calendar cal = Calendar.getInstance();
				String thisHour = new SimpleDateFormat("yyyyMMddHH").format(cal.getTime());//当前小时
				cal.add(Calendar.HOUR_OF_DAY, -1);
				String preHour = new SimpleDateFormat("yyyyMMddHH").format(cal.getTime());//上一小时(避免丢失小时末的数据)
				cal.add(Calendar.HOUR_OF_DAY, 2);
				String nextHour = new SimpleDateFormat("yyyyMMddHH").format(cal.getTime());//下一小时(为适应不同计算机的时间误差)
				
				if (name.endsWith(".zip") && (name.indexOf(thisHour)!=-1 || name.indexOf(preHour)!=-1 || name.indexOf(nextHour)!=-1)) {
					if (new File(f.getAbsolutePath() + ".ok").exists()) { //.ok文件存在，即已移动完全(server端逻辑是先移动数据文件，再移动.ok文件)
						logger.info("start to process file:" + f.getAbsolutePath());
						transferAll(name, getSourcePath() + "/" + name);
						logger.info("end process:" + f.getAbsolutePath());
					}
				} else { //不处理旧数据
				}
			}
			logger.debug("processFileTask end...");
		} catch (Throwable e) {
			logger.error("Final.. Exception:"+ Utils.printStackTrace(e));
		}
	}
	
	private void transferAll(String zipName, String zipPath) {
		String[] servers = serverList.split(",");
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("conf/sysconfig.properties"));
		} catch (IOException e) {
			logger.error("配置文件不存在或格式错误");
		}
		for(String server: servers){
			FourmFtpClient fourmFtpClient;
			TcpClient tcpClient;
			String serverPath;
			//读取服务器的FTP配置
			try {
				serverPath = properties.getProperty(server + ".ftp.serverPath");
				fourmFtpClient = new FourmFtpClient(
						properties.getProperty(server + ".ftp.serverUrl"),
						properties.getProperty(server + ".ftp.username"),
						properties.getProperty(server + ".ftp.password"),
						Integer.parseInt((properties.getProperty(server + ".ftp.serverPort"))));
			} catch(Exception e){
				logger.error("配置文件FTP服务器有误：" + server);
				continue;
			}

			//读取服务器TCP配置
			try {
				tcpClient = new TcpClient(
						properties.getProperty(server + ".tcpserver.host"),
						Integer.parseInt((properties.getProperty(server + ".tcpserver.port"))));
			} catch(Exception e){
				logger.error("配置文件TCP服务器有误：" + server);
				continue;
			}
			
			//向当前服务器传送
			try {
				transfer(fourmFtpClient, tcpClient, serverPath, zipName, zipPath);
			} catch(Exception e) {			
				logger.error("("+server+")transfer error:\n"+Utils.printStackTrace(e));
			}
		}
		//向所有服务器传送成功后，删除文件
		try {
			FileUtils.forceDelete(new File(zipPath));
			if(new File(zipPath + ".ok").exists()) {
				FileUtils.forceDelete(new File(zipPath + ".ok"));
			}
		} catch (IOException e) {
			logger.info("error deleting file:" + zipName);
		}
	}
		
	
	/**
	 * 将zipPath代表的文件上传至服务端目录。并与服务端交互上传结果
	 * @param zipName 文件名称
	 * @param zipPath 文件全路径
	 * @return
	 * @throws Exception 
	 */
	private boolean transfer(FourmFtpClient fourmFtpClient, TcpClient tcpClient, String serverPath,
			String zipName, String zipPath) throws Exception {
		logger.debug("transfer()--> start to transfer ready: " + zipPath);
		String sendDate = getDateStr();
		//向FTP服务上传
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			FutureTask<Boolean> task = new FutureTask<Boolean>(new FutureTransferTask(fourmFtpClient, zipPath, serverPath));
			executor.execute(task);
			
			try {
				@SuppressWarnings("unused")
				boolean result = task.get(180, TimeUnit.SECONDS);
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
			logger.error(e.getMessage() + ":" + zipName);
			throw e;
		} 

		//向服务端验证是否接受成功
		String submitResult = tcpClient.submit(sendDate + "," + zipName);
		if ("success".equals(submitResult)) {
			logger.info("<SERVER_REPLY>"+ submitResult +"</SERVER_REPLY>");
			return true;
		} else if ("incomplete".equals(submitResult)) {
			logger.error("<SERVER_REPLY>"+ submitResult +"</SERVER_REPLY>");
		} else {
			logger.error("<SERVER_REPLY>"+ submitResult +"</SERVER_REPLY>");
		}
		return false;
	}	
	
	
	/**
	 * 验证已配置各目录是否符合规则
	 */
	private void pathCheck() {
		File f = new File(getSourcePath());
		if(!f.exists() || !f.isDirectory())	{
			throw new IllegalArgumentException("the param 'sourcePath' not exists or not a directory");
		}
	}
	

	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public void setServerList(String serverList) {
		this.serverList = serverList;
	}
	public String getServerList() {
		return serverList;
	}
	public String getDateStr() {
		return fmt.format(new Date());
	} 
}
