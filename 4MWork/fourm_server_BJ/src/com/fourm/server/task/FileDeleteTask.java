package com.fourm.server.task;

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
 * ɾ��ָ��Ŀ¼N��ǰ������
 * @author Administrator , Mobile Bank System, CSII
 * <p>created on 2012-10-25 </p>
 */
public class FileDeleteTask {
	private Logger logger = LoggerFactory.getLogger(FileDeleteTask.class);
	
	private String dataPath;
	private String histPath;
	private int dayBeforeDelete;//ɾ��dayBeforeDelete��ǰ�������ļ�
	
	public void process() {
		try {
			deleteFile(getDataPath(),getHistPath());
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
							Pattern filePattern = Pattern.compile("^[\\w\\+#]+\\+(\\d{10,14})(\\.lvm|\\.dat)(\\.zip(\\.ok)?)?$");
							Matcher dateMatcher = filePattern.matcher(f.getName());
							
							if (dateMatcher.find()) {
								String fileDateStr = dateMatcher.group(1);
								fileDate.set(Integer.parseInt(fileDateStr.substring(0,4)),
										Integer.parseInt(fileDateStr.substring(4,6)) - 1, // 0����һ��
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
	

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	public String getDataPath() {
		return dataPath;
	}
	public String getHistPath() {
		return histPath;
	}
	public void setHistPath(String histPath) {
		this.histPath = histPath;
	}
	public int getDayBeforeDelete() {
		return dayBeforeDelete;
	}
	public void setDayBeforeDelete(int dayBeforeDelete) {
		this.dayBeforeDelete = dayBeforeDelete;
	}
}