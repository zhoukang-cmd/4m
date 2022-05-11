package com.fourm.client.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fourm.common.FourmFtpClient;

/**
 * 监控程序传输出现假死的情况
 * @author Administrator , Mobile Bank System, CSII
 * <p>created on 2012-10-25 </p>
 */

//TODO 此类已不再使用，防假死功能通过FutureTransferTask类实现
@Deprecated
@SuppressWarnings("unused")
public class ProgramChecker implements Runnable,ApplicationContextAware{
	private Logger logger = LoggerFactory.getLogger(ProgramChecker.class);
	private ApplicationContext a;
	private FourmFtpClient ftpClient;
	public void start() {
		Thread t = new Thread(this);
		t.start();
	}
	public void run() {
		while(true) {
			/*if(this.ftpClient.getStatus() == 0) {
				long now = System.currentTimeMillis();
				long time = this.ftpClient.getTime();
				System.out.println(a.getBean("programChecker"));
				if( now - time > 1000 * 60) {
					ftpClient.setStatus(1);
					ftpClient.setTime(now);
					((ClassPathXmlApplicationContext)a).refresh();
					logger.info("program restarted");
				} 
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}*/
		}
		
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.a = context;
	}
	
	public void setFtpClient(FourmFtpClient ftpClient) {
		this.ftpClient = ftpClient;
	}

}
