package com.fourm.client.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fourm.common.TcpClient;
import com.fourm.common.FourmFtpClient;
import com.fourm.common.Utils;
import com.fourm.common.ZipUtil;

/**
 * 客户端文件处理
 * 1.打包source目录源文件，zip文件存至zip目录，已被打包文件移至hist目录
 * 2.传输zip文件，成功移至success目录，失败移至fail目录
 * @author zhangtaichao , Mobile Bank System, CSII
 * <p>created on 2012-2-29 </p>
 * modified by wangzhe 20121007 修正FTPClient对象不能用单例否则出现线程死锁
 * modified by wangzhe 20121009 修正FTPClient抛出异常没有catch并关闭
 */
public class ProcessFileTask {
	
	private static Logger logger = LoggerFactory.getLogger(ProcessFileTask.class);
	private String sourcePath;
	private String histPath;
	private String zipPath;
	private String successPath;
	private String failPath;
	private String serverPath;
	private FourmFtpClient ftpClient;
	private TcpClient tcpClient;
	private String suffix = "";
	private int maxLine = 3600;//非振动数据文件最大数据行数
		
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
				
				if(name.endsWith(".lvm") && name.startsWith("H") && (name.indexOf(thisHour) != -1)) { //当前小时的振动数据
					logger.info("start to process file:" + f.getAbsolutePath());
					processSingleFile(f.getAbsolutePath());
					logger.info("end process:" + f.getAbsolutePath());
				} else if(name.endsWith(".dat") && name.startsWith("L") && (name.indexOf(thisHour) != -1)) { //当前小时的非振动数据
					logger.info("start to process file:" + f.getAbsolutePath());
					processDatFile(f.getAbsolutePath(),false);
					logger.info("end process:" + f.getAbsolutePath());
				} else {
					//TODO 要把非当前小时的数据(可能是未处理的)移至历史目录，请取消注释下面的代码。
					//move(f.getAbsolutePath(), getHistPath()+"/"+name);
				}
			}
			logger.debug("processFileTask end...");
		} catch (Throwable e) {
			logger.error("Final.. Exception:"+ Utils.printStackTrace(e));
		}
	}
	
	/**
	 * 处理非振动数据文件
	 * @param filePath
	 * @param 是否处理文件的最后一次
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	private boolean processDatFile(String filePath,boolean end)  {
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			//获取line.properties文件，如果不存在则创建该文件
			String lineFileName = "line.properties";
			lineFileName = getSourcePath() + "/" + lineFileName;
			File lineFile = new File(lineFileName);
			if(!lineFile.exists()) {
				lineFile.createNewFile();
			}
			
			//获取该文件上次读取的行号line.properties，如果超过了最大行号，则不处理，直接将其移动到hist文件夹中
			int line = 0;
			Properties prop = new Properties();			
			fin = new FileInputStream(lineFile);
			prop.load(fin);
			File datFile = new File(filePath);
			File tmpFile = new File(filePath + ".4tmp");
			String strLine = prop.getProperty(filePath);
			if(StringUtils.isNotEmpty(strLine)) {
				line = Integer.parseInt(strLine);
			}
			String hist = getHistPath() + "/" + datFile.getName();
			if(line == getMaxLine()) {
				move(filePath,hist);
				return true;//文件已经处理完成
			}
			
			//为了防止在读的过程中，又labview程序又写入数据，这里将其拷贝一份，然后再进行读。
			FileUtils.copyFile(datFile, tmpFile);//将当前文件备份一份至临时文件
			String partName = (line+1) + "_part_" + datFile.getName();
			File toZipFile = new File(getSourcePath() + "/" + partName);
			
			//将临时文件中的数据读取到内存中，如果大于了最大行，则该文件不做处理直接将其移动到hist文件中。
			br = new BufferedReader(new InputStreamReader(new FileInputStream(tmpFile)));
			int tmp = 0;
			while(tmp < line) {
				if(br.ready()) {
					br.readLine();
					tmp++;
				} else {
					line = getMaxLine();
					move(filePath,hist);
					return true;//文件已经处理完成
				}
			}
			
			//读完line.properties的保存的行号以后，开始读取新的数据，并将其保存在临时文件中
			int currentLine = 0;//此次新读取的行数
			do {
				String str = br.readLine();
				if(str == null && !end) {//文件没有新数据进来，又不是最后一次处理
					return true;
				}
				
				if(br.ready()) {//此行后还有数据，非最后一行
					if(bw == null) {
						bw = new BufferedWriter(new FileWriter(toZipFile));
					}
					bw.write(str);
					bw.newLine();
					line++;
					currentLine++;
				} else {//此行为最后一行，若非最后一次处理，此行可能只为一半，不读此行
					if(end) {
						if(str != null) {
							if(bw == null) {
								bw = new BufferedWriter(new FileWriter(toZipFile));
							}
							bw.write(str);
							bw.newLine();
							currentLine++;
						}
						line = getMaxLine();
					}
				}
				
			} while(br.ready());
			if(bw != null) {
				bw.flush();
				bw.close();
			}
			br.close();
			
			//如果本次读取的行数为0，则返回
			if(currentLine == 0) {
				FileUtils.forceDelete(tmpFile);
				return true;
			}
			
			//将临时文件压缩成zip文件
			String zipName = partName + ".zip";			
			boolean result = ZipUtil.zip(getSourcePath() + "/" + partName, getZipPath() + "/" + zipName);
			if(result) {//压缩成功
				logger.debug("file zip success:" + getSourcePath() + "/" + partName);
				FileUtils.forceDelete(toZipFile);
				FileUtils.forceDelete(tmpFile);
				transfer(zipName, getZipPath() + "/" + zipName, false);
			}
			prop.put(filePath, line+"");
			fout = new FileOutputStream(lineFile);
			prop.store(fout, "can not be deleted");
			fin.close();
			fout.close();
		} catch(Exception e) {
			logger.error(Utils.printStackTrace(e));
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
				}
			}
			if(fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
				}
			}
			if(fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
				}
			}
		}
		
		return true;
	}

	/**
	 * 处理.lvm振动数据文件
	 * @param filePath
	 * @return
	 */
	private boolean processSingleFile(String filePath) {
		File file = new File(filePath);
		String fileName = file.getName();
		String zipName = fileName + ".zip";
		boolean result = ZipUtil.zip(file.getAbsolutePath(), getZipPath()+"/"+zipName);
		if(result) {//压缩成功,源文件转至hist目录
			logger.info("file zip success:" + fileName);
			move(file.getAbsolutePath(), getHistPath()+"/"+fileName);
			transfer(zipName, getZipPath() + "/" + zipName, false);
		}
		return result;//zip成功
	}
	
	private void transfer(String zipName,String zipPath,boolean fail) {
		try {
			transferAndMove(zipName, zipPath, false);
		} catch(Exception e) {			
			logger.error("ftp send error:\n" + Utils.printStackTrace(e));
			String failPath = getFailPath() + "/" + zipName;
			move(zipPath,failPath);		
		}
	}
		
	/**
	 * 将zipPath代表的文件上传至服务端目录。并与服务端交互上传结果
	 * 上传成功，将zipPath转移至success目录，失败则转移至fail目录
	 * @param zipName 文件名称
	 * @param zipPath 文件全路径
	 * @param failflag 是否为fail目录内的文件
	 * @return
	 * @throws Exception 
	 */
	private boolean transferAndMove(String zipName,String zipPath,boolean failflag) throws Exception {
		logger.debug("transferAndMove()--> start to transfer ready: " + zipPath);
		String sendDate = getDateStr();
		//向FTP服务上传
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			FutureTask<Boolean> task = new FutureTask<Boolean>(new FutureTransferTask(getFtpClient(), zipPath, getServerPath()));
			executor.execute(task);
			
			try {
				@SuppressWarnings("unused")
				boolean result = task.get(180, TimeUnit.SECONDS);
			}catch(TimeoutException e) {
				task.cancel(true);
				//这里无法正常关闭TCP连接，需测试连接对象析构时是否会断开连接。
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
		String submitResult = getTcpClient().submit(sendDate + "," + zipName);
		if ("success".equals(submitResult)) {
			String successPath = getSuccessPath() + "/" + zipName;
			move(zipPath, successPath);
			logger.info("<SERVER_REPLY>"+ submitResult +"</SERVER_REPLY>");
		} else if ("incomplete".equals(submitResult)) {
			logger.error("<SERVER_REPLY>"+ submitResult +"</SERVER_REPLY>");
		} else {
			if ( Utils.isAK_FOURM_BAT(submitResult) ) {
				//创建文件夹
				new File(getFailPath() + "/duplication").mkdirs();
				String failPath = getFailPath() + "/duplication/" + zipName;
				//是否已经存在
				if ( new File(failPath).exists() ) {
					//直接删
					FileUtils.forceDelete(new File(zipPath));
				} else {
					//移走
					move(zipPath,failPath);
				}
			} else {
				logger.error("<SERVER_REPLY>"+ submitResult +"</SERVER_REPLY>");
				if(!failflag) {
					String failPath = getFailPath() + "/" + zipName;
					move(zipPath,failPath);
				}
			}
		}
		logger.debug("transferAndMove()--> transer success: " + zipPath);
		return true;
	}	
	
	/**
	 * 将src转移至dest
	 * @param src 源文件全路径
	 * @param dest 目标文件全路径
	 */
	public static void move(String src, String dest) {
		File sf = new File(src);
		File df = new File(dest);
		try {
			FileUtils.copyFile(sf, df);
			FileUtils.forceDelete(sf);
			logger.info("file move success:" + src + "->" + dest);
		} catch (IOException e) {
			logger.warn("file move fail:" + src + "->" + dest + "\n" + Utils.printStackTrace(e));
		}
	}
	
	/**
	 * 验证已配置各目录是否符合规则
	 */
	private void pathCheck() {
		if(!pathExistsAndDir(getSourcePath())) {
			throw new IllegalArgumentException("the param 'sourcePath' not exists or not a directory");
		}
		if(!pathExistsAndDir(getHistPath())) {
			throw new IllegalArgumentException("the param 'histPath' not exists or not a directory");
		}
		if(!pathExistsAndDir(getZipPath())) {
			throw new IllegalArgumentException("the param 'zipPath' not exists or not a directory");
		}
		if(!pathExistsAndDir(getSuccessPath())) {
			throw new IllegalArgumentException("the param 'successPath' not exists or not a directory");
		}
		if(!pathExistsAndDir(getFailPath())) {
			throw new IllegalArgumentException("the param 'failPath' not exists or not a directory");
		}
		
	}
	
	/**
	 * 验证单个路径是否存在
	 */
	public boolean pathExistsAndDir(String path) {
		File f = new File(path);
		if(!f.exists() || !f.isDirectory())	{
			return false;
		} else {
			return true;
		}
	}
	

	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getHistPath() {
		return histPath;
	}
	public void setHistPath(String histPath) {
		this.histPath = histPath;
	}
	public String getZipPath() {
		return zipPath;
	}
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	public String getSuccessPath() {
		return successPath;
	}
	public void setSuccessPath(String successPath) {
		this.successPath = successPath;
	}
	public String getFailPath() {
		return failPath;
	}
	public void setFailPath(String failPath) {
		this.failPath = failPath;
	}	
	public FourmFtpClient getFtpClient() {
		return ftpClient;
	}
	public void setFtpClient(FourmFtpClient ftpClient) {
		this.ftpClient = ftpClient;
	}
	public String getServerPath() {
		return serverPath;
	}
	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}
	public TcpClient getTcpClient() {
		return tcpClient;
	}
	public void setTcpClient(TcpClient tcpClient) {
		this.tcpClient = tcpClient;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public int getMaxLine() {
		return maxLine;
	}
	public void setMaxLine(int maxLine) {
		this.maxLine = maxLine;
	}
	public String getDateStr() {
		return fmt.format(new Date());
	} 
}
