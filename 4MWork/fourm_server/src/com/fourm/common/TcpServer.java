package com.fourm.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.fourm.common.Utils;

/**
 * 
 * @author zhangtaichao , Mobile Bank System, CSII
 *         <p>
 *         created on 2012-2-29
 *         </p>
 *         modified by wangzhe 20121007 修正ServerSocket对象不能用类对象否则出现线程不安全
 */
public class TcpServer implements Runnable {

	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		executor = Executors.newCachedThreadPool();
		ServerSocket serversocket = null;
		try {
			serversocket = new ServerSocket();
		} catch (IOException e1) {
			logger.error("create serversocket error:\n" + Utils.printStackTrace(e1));
		}
		try {
			serversocket.bind(new InetSocketAddress(getPort()));
			logger.info("tcpserver start ,port:" + getPort());
			while (true) {
				Socket s = serversocket.accept();
				if (s != null) {
					executor.execute(new TcpHandler(s, getSqlMapClient(), getLocalPath()));
				}
			}
		} catch (Exception e) {
			logger.error("TcpServer 异常:\n" + Utils.printStackTrace(e));
		} finally {
			try {
				serversocket.close();
			} catch (IOException e) {
			}
		}

	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public SqlMapClientTemplate getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	private Logger logger = LoggerFactory.getLogger(TcpServer.class);
	private int port;
	private SqlMapClientTemplate sqlMapClient;
	private ExecutorService executor;
	/**
	 * 作为FTP服务端的文件存放路径
	 */
	private String localPath;

}
