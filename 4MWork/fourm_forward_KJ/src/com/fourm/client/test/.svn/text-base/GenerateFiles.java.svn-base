package com.fourm.client.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

public class GenerateFiles {
	public static void main(String[] args) throws ParseException, IOException {
		File f = new File("E:/Analyzer/SX+JMJT+SHK+DFJ+ZS+1#+201203041200.lvm");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar c = Calendar.getInstance();
		c.setTime(fmt.parse("20130512120000"));
		for(int i=0;i<2;i++,i++) {
			c.add(Calendar.MINUTE, 2);
			String str = fmt.format(c.getTime());
			str = "E:/Analyzer/source/H+SX+JMJT+SHK+DFJ+ZS+1#+" + str + ".lvm";
			FileUtils.copyFile(f, new File(str));
		}
	}
}
