package com.fourm.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文本文件压缩
 * @author zhangtaichao 
 * <p>created on 2012-2-28 </p>
 */
public class ZipUtil {
	private static Logger logger = LoggerFactory.getLogger(ZipUtil.class);
	/**
	 * 
	 * @param src 被压缩文件全路径
	 * @param target 目标文件全路径
	 */
	public static boolean zip(String src,String target) {
		List<String> list = new ArrayList<String>();
		list.add(src);
		return zip(list,target);
	}
	/**
	 * 
	 * @param src 被压缩文件全路径列表
	 * @param target 压缩目标文件路径
	 */
	public static boolean zip(List<String> src,String target) {
		if (src == null || src.isEmpty()) {
			return false;
		}
		File toFile = new File(target);
		FileOutputStream fos = null;//目标文件输出流
		ZipOutputStream zous = null;
		FileInputStream fin = null;
		try {
			fos = new FileOutputStream(toFile);
			zous = new ZipOutputStream(fos);
			int read = 0;
			byte[] buf = new byte[1024];
			fin = null;
			ZipEntry entry = null;
			for(Iterator<String> i = src.iterator();i.hasNext();) {
				String tmp = i.next();
				try {
					File ftmp = new File(tmp);
					if(ftmp.exists()) {
						String name = ftmp.getName();
						entry = new ZipEntry(name);
						zous.putNextEntry(entry);
						fin = new FileInputStream(ftmp);
						while((read=fin.read(buf)) != -1) {
							zous.write(buf, 0, read);
						}
						zous.flush();
						zous.closeEntry();
						fin.close();
						fin = null;
					}
				} catch(Exception ee) {
					logger.error("压缩文件失败:" + tmp + "\n" + Utils.printStackTrace(ee));
				} finally {
					if (fin != null) {
						try {
							fin.close();
							fin = null;
						} catch (IOException e) {
							logger.error(Utils.printStackTrace(e));
						}
					}
				}
				
			}
			return true;
		} catch (Exception e) {
			logger.error("压缩文件失败：\n" + Utils.printStackTrace(e));
			return false;
		} finally {
			try {
				zous.close();
			} catch (Exception e) {
				logger.error(Utils.printStackTrace(e));
			}
			try {
				fos.close();
			} catch (Exception e) {
				logger.error(Utils.printStackTrace(e));
			}
		}
	}
	
	/**
	 * 
	 * @param src 源ZIP文件全路径
	 * @param dst 目标解压缩文件夹,必须为文件夹
	 */
	@SuppressWarnings("rawtypes")
	public static void unzip(String src, String dst) {
		File f = new File(src);
		if (!f.exists()) {
			logger.error("src zipfile not exists: " + src);
		}
		File dstf = new File(dst);
		if(!dstf.exists() || !dstf.isDirectory()) {
			logger.error("dst unzip dir not exists:" + dst);
		}
		InputStream in = null;
		FileOutputStream fout = null;
		try {
			byte[] buf = new byte[1024];
			ZipFile zip = new ZipFile(f);
			Enumeration entries = zip.entries();
			while(entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry)entries.nextElement();
				String name = entry.getName();
				in = zip.getInputStream(entry);
				int read = 0;
				File dstFile = new File(dst + "/" + name);
				fout = new FileOutputStream(dstFile);
				while((read = in.read(buf)) != -1) {
					fout.write(buf, 0, read);
				}
				fout.flush();
				in.close();
 			}
 		} catch (Exception e) {
			logger.error("unzip error: " + src + "\n" + Utils.printStackTrace(e));
		} finally {
			try {
				in.close();
				in = null;
			} catch (Exception e) {
				logger.error(Utils.printStackTrace(e));
			}
			try {
				fout.close();
				fout = null;
			} catch (Exception e) {
				logger.error(Utils.printStackTrace(e));
			}			
		}
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public static List<File> unzipForFile(String src, String dst) {
		List<File> fLs=new ArrayList<File>();
		File f = new File(src);
		if (!f.exists()) {
			logger.error("src zipfile not exists: " + src);
		}
		File dstf = new File(dst);
		if(!dstf.exists() || !dstf.isDirectory()) {
			logger.error("dst unzip dir not exists:" + dst);
		}
		InputStream in = null;
		FileOutputStream fout = null;
		try {
			byte[] buf = new byte[1024];
			ZipFile zip = new ZipFile(f);
			Enumeration entries = zip.entries();
			while(entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry)entries.nextElement();
				String name = entry.getName();
				in = zip.getInputStream(entry);
				int read = 0;
				File dstFile = new File(dst + "/" + name);
				fout = new FileOutputStream(dstFile);
				while((read = in.read(buf)) != -1) {
					fout.write(buf, 0, read);
				}
				fout.flush();
				in.close();
				
				fLs.add(dstFile);
 			}
 		} catch (Exception e) {
			logger.error("unzip error: " + src + "\n" + Utils.printStackTrace(e));
		} finally {
			try {
				in.close();
				in = null;
			} catch (Exception e) {
				logger.error(Utils.printStackTrace(e));
			}
			try {
				fout.close();
				fout = null;
			} catch (Exception e) {
				logger.error(Utils.printStackTrace(e));
			}			
		}
	
	
	  return fLs;
	
	
	}
	
	
	
}
