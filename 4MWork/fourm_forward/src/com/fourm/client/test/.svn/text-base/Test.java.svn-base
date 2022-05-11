package com.fourm.client.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.fourm.common.TcpClient;
import com.fourm.common.FourmFtpClient;

@SuppressWarnings("unused")
public class Test {
	private String name ;
	private SqlMapClientTemplate sqlMapClient;
	private FourmFtpClient ftpClient;
	private TcpClient tcpClient;
	private static Logger logger = LoggerFactory.getLogger(Test.class);
	public void start() {
		
//		while(true) {
//			getTcpClient().submit("e:/");
//		}
//		String local = "e:/ztc.txt";
//		getFtpClient().loginToFtpServer();
//		getFtpClient().putToServer(local, "/app/mbank/test");
//		getFtpClient().close();
//		while(true) {
//			
//			logger.info(name);t
//			logger.info(getSqlMapClient().toString());
//			List l = getSqlMapClient().queryForList("common.selectUser");
//			logger.info(l.toString());
//			try {
//				Thread.sleep(1000000000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
	}
	
	public TcpClient getTcpClient() {
		return tcpClient;
	}

	public void setTcpClient(TcpClient tcpClient) {
		this.tcpClient = tcpClient;
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
	public FourmFtpClient getFtpClient() {
		return ftpClient;
	}
	public void setFtpClient(FourmFtpClient ftpClient) {
		this.ftpClient = ftpClient;
	}
	public static void main(String[] args) throws IOException {
		int i = 0;
		BufferedReader br = new BufferedReader(new FileReader(new File("d:/num.txt")));
		int pre = 0;
		int aft = 0;
		String l = br.readLine().trim();
		pre = Integer.parseInt(l);
		while(br.ready()) {
			String line = br.readLine().trim();
			aft = Integer.parseInt(line);
			if(aft - pre != 333) {
				System.out.println(i);
			}
			pre = aft;
			i++;
			
		}
		
	}
}
