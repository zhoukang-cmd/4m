package com.fourm.client.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GenerateBatFiles {
	public static void main(String[] args) throws ParseException, IOException {
		render();
	}
	
	public static void render() throws ParseException, IOException {
//		File f = new File(System.getProperty("user.dir") +"/doc/振动数据格式/H+SX+JMJT+SHK+DFJ+ZS+1#+201203041200.lvm");
		File f = new File("E:/项目工作空间/光大手机银行/ceb_mbank/fourm_server/doc/振动数据格式/L+SX+JMJT+SHK+DFJ+ZS+1#+2012030412.dat");
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHH");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 0);
		String str = fmt.format(c.getTime());
		str = "L+SX+JMJT+SHK+DFJ+ZS+1#+" + str + ".dat";
		str = "E:/Analyzer/source/" + str;
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(str)));
		BufferedReader br = new BufferedReader(new FileReader(f));
		while(br.ready()) {
			String line = br.readLine();
			bw.write(line);
			bw.newLine();
			bw.flush();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		br.close();
		bw.close();
	}
}
