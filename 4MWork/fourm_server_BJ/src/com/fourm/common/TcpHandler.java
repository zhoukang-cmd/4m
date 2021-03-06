package com.fourm.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.fourm.common.Utils;
/**
 * 在TCP Server接收到新消息时调用
 * 把接收到的文件信息插入批次表
 * 并返回成功信息
 */
@SuppressWarnings("unused")
class TcpHandler implements Runnable {
	private Socket socket;
	BufferedInputStream bin = null;
	BufferedOutputStream bout = null;
	private Logger logger = LoggerFactory.getLogger(TcpHandler.class);
	private SqlMapClientTemplate sqlMapClient;
	private String localPath;
	TcpHandler(Socket s,SqlMapClientTemplate sql,String localPath) throws IOException {
		socket = s;
		bin = new BufferedInputStream(s.getInputStream());
		bout = new BufferedOutputStream(s.getOutputStream());
		this.sqlMapClient = sql;
		this.localPath = localPath;
	}
	public void run() {
		String str = null;
		try {
			byte[] result = receive();
			str = new String(result);
			String[] ss = str.split(",");//ss[0]=senddate,ss[1]=filepath
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
			String p = this.localPath + "/" + ss[1];
			
			File f = new File(p);
			File fok = new File(p + ".ok");
			if(f.exists() && fok.exists()) {
				if(p.endsWith(".rar")){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("STARTTIME_UP", fmt.parse(ss[0]));
					map.put("ENDTIME_UP", new Date());
					map.put("PAKNAME", f.getName());
					map.put("BAT_INFO", "接收成功,待处理");
					map.put("STATUS", "0");
					map.put("CHANNEL", "F");
					map.put("ZIP_PATH", p);
					this.sqlMapClient.insert("server.insertBAT", map);
					send("success".getBytes());
				
				}
				logger.info("receive file success:" + str);
			
			} else {
				send("incomplete".getBytes());
				logger.debug("receive file incomplete:" + str);
			}
		} catch (Exception e) {
			String errorStack = Utils.printStackTrace(e);
			String returnMsg = null;
			//如果违反唯一约束，报告违反文件名称
			if ((errorStack.indexOf("unique constraint") != -1) || //oracle
				(errorStack.indexOf("唯一索引") != -1)) { //mssql
				returnMsg = str +"重复,违反AK_FOURM_BAT索引";
				logger.error(returnMsg);
			} else {
				returnMsg = e.getMessage();
				logger.error(errorStack);
			}
			try {
				send(returnMsg.getBytes());
			} catch (IOException e1) {
				logger.error(Utils.printStackTrace(e1));
			}
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	public void send(byte abyte0[])
    throws IOException
	{
	    byte abyte1[] = new byte[4];
	    intToNetworkByteOrder(abyte0.length, abyte1, 0, 4);
	    bout.write(abyte1);
	    bout.write(abyte0);
	    bout.flush();
	}
	 public byte[] receive()
     throws IOException
	 {
	     byte abyte0[] = new byte[4];
	     int i = readFully(abyte0, 4);
	     if(i != 4)
	     {
	         logger.debug(">>>DEBUG: TcpServer could not read length field");
	         return null;
	     }
	     int j = networkByteOrderToInt(abyte0, 0, 4);
//	     logger.debug((new StringBuilder()).append(">>>DEBUG: TcpServer reading ").append(j).append(" bytes").toString());
	     if(j <= 0)
	     {
	         logger.error((new StringBuilder()).append(">>>DEBUG: TcpServer zero or negative length field: ").append(j).toString());
	         return null;
	     }
	     byte abyte1[] = new byte[j];
	     i = readFully(abyte1, j);
	     if(i != j)
	     {
	         logger.debug((new StringBuilder()).append(">>>DEBUG: TcpServer could not read complete packet (").append(j).append("/").append(i).append(")").toString());
	         return null;
	     } else
	     {
	         return abyte1;
	     }
	 }
	 private int readFully(byte abyte0[], int i)
     throws IOException
	 {
	     int k = 0;
	     int j;
	     for(; i > 0; i -= j)
	     {
	         j = bin.read(abyte0, k, i);
	         if(j == -1)
	             return k != 0 ? k : -1;
	         k += j;
	     }
	
	     return k;
	 }

	 private static final int networkByteOrderToInt(byte abyte0[], int i, int j)
	 {
	     if(j > 4)
	         throw new IllegalArgumentException("Cannot handle more than 4 bytes");
	     int k = 0;
	     for(int l = 0; l < j; l++)
	     {
	         k <<= 8;
	         k |= abyte0[i + l] & 255;
	     }
	
	     return k;
	 }

	 private static final void intToNetworkByteOrder(int i, byte abyte0[], int j, int k)
	 {
	     if(k > 4)
	         throw new IllegalArgumentException("Cannot handle more than 4 bytes");
	     for(int l = k - 1; l >= 0; l--)
	     {
	         abyte0[j + l] = (byte)(i & 255);
	         i >>>= 8;
	     }
	
	 }
		
}