package com.fourm.server.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class Test {
	public static void main(String [] args) {
		/*ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(new ProcessFile("e:/ztc.txt"));
		exec.execute(new ProcessFile("e:/ztc.txt"));*/
		for(int i=0; i<30;i++) {
//			System.out.println("INSERT INTO FOURM_T_FIELD(ORECODE,ROOMCODE,EQUIPCODE,EQUIPNUM,ORDERNO,FIELDTYPE,FIELDNAME,FIELDDESC)" 
//+ "VALUES('SHK','DFJ','ZS','1','" + (i+1) + "','L','VALUE','VALUE');");
			System.out.println("truncate table T_SHK_DFJ_ZS_1_" + i + ";");
		}
	}
	private String name ;
	private SqlMapClientTemplate sqlMapClient;
	private static Logger logger = LoggerFactory.getLogger(Test.class);
	public void start() {
		while(true) {
			
			logger.info(name);
			logger.info(getSqlMapClient().toString());
			List<?> l = getSqlMapClient().queryForList("common.selectUser");
			logger.info(l.toString());
			try {
				Thread.sleep(1000000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SqlMapClientTemplate getSqlMapClient() {
		return sqlMapClient;
	}
	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
}
class ProcessFile implements Runnable {
	private String name ;
	@Override
	public void run() {
		File f = new File(name);
		try {
			FileOutputStream fin = new FileOutputStream(f);
			FileChannel channel = fin.getChannel();
			FileLock lock = channel.tryLock();
			System.out.println(lock.isShared());
			System.out.println(Thread.currentThread());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	ProcessFile(String name) {
		this.name = name;
	}
}