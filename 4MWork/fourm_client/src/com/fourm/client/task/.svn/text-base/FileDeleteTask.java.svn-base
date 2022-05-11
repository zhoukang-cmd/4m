package com.fourm.client.task;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fourm.common.Utils;

/**
 * 删除指定目录N天前的数据
 * @author Administrator , Mobile Bank System, CSII
 * <p>created on 2012-10-25 </p>
 */
public class FileDeleteTask {
	private Logger logger = LoggerFactory.getLogger(FileDeleteTask.class);
	
	private String sourcePath;
	private String histPath;
	private String zipPath;
	private String failPath;
	private String successPath;
	private int dayBeforeDelete;//删除dayBeforeDelete天前的数据文件
	
	public void process() {
		try {
			deleteFile(getSourcePath(),getHistPath(),getZipPath(),getFailPath(),getSuccessPath());
		} catch(Exception e) {
			logger.error(Utils.printStackTrace(e));
		}
	}
	
	private void deleteFile(String... dir) {
		final Calendar delPoint = Calendar.getInstance();
		delPoint.add(Calendar.DATE, -getDayBeforeDelete());
		if(dir != null) {
			for(String path : dir) {
				File file = new File(path);
				
				if(file.exists() && file.isDirectory()) {
					File[] files = file.listFiles(new FileFilter() {
						
						public boolean accept(File f) {
							Calendar fileDate = Calendar.getInstance();
							Pattern filePattern = Pattern.compile("^[\\w\\+#]+\\+(\\d{10,12})(\\.lvm|\\.dat|\\.4tmp)(\\.zip(\\.ok)?)?$");
							Matcher dateMatcher = filePattern.matcher(f.getName());
							
							if (dateMatcher.find()) {
								String fileDateStr = dateMatcher.group(1);
								fileDate.set(Integer.parseInt(fileDateStr.substring(0,4)),
										Integer.parseInt(fileDateStr.substring(4,6)) - 1, // 0代表一月
										Integer.parseInt(fileDateStr.substring(6,8)));
								if(fileDate.before(delPoint)) {
									return true;
								}
							}
							return false;
						}
					});
					for(File delete : files) {
						try {
							FileUtils.forceDelete(delete);
							logger.info("file delete success:" + delete.getName());
						} catch (IOException e) {
							logger.error(Utils.printStackTrace(e));
						}
					}
				}
			}
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
	public String getFailPath() {
		return failPath;
	}
	public void setFailPath(String failPath) {
		this.failPath = failPath;
	}
	public String getZipPath() {
		return zipPath;
	}
	public void setZipPath(String zipPath) {
		this.zipPath = zipPath;
	}
	public int getDayBeforeDelete() {
		return dayBeforeDelete;
	}
	public void setDayBeforeDelete(int dayBeforeDelete) {
		this.dayBeforeDelete = dayBeforeDelete;
	}
	public void setSuccessPath(String successPath) {
		this.successPath = successPath;
	}
	public String getSuccessPath() {
		return successPath;
	}
}
