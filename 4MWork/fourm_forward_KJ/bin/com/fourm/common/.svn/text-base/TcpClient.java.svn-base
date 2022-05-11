package com.fourm.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fourm.common.Utils;

/**
 * 客户端通讯
 * @author zhangtaichao , Mobile Bank System, CSII
 * <p>created on 2012-2-29 </p>
 * modified by wangzhe 20121007 修正FTPClient对象不能用单例否则出现线程死锁
 */
public class TcpClient {
	
	private Logger logger = LoggerFactory.getLogger(TcpClient.class);
    private String host;
    private int port;
    

    public TcpClient(String host, int port) {
    	this.host = host;
    	this.port = port;
    }

    public String submit(String str) {
    	Socket tcpSocket = null;
    	BufferedOutputStream out = null;
    	BufferedInputStream in = null;
    	try {
	    	tcpSocket = new Socket(host, port);
	    	tcpSocket.setSoTimeout(5000);
	        out = new BufferedOutputStream(tcpSocket.getOutputStream());
	        in = new BufferedInputStream(tcpSocket.getInputStream());
	        send(out,str.getBytes());
	        byte[] result = receive(in);
	        String r = new String(result);
	        return r; 
    	} catch(Exception e) {
    		logger.error("submit error:\n" + Utils.printStackTrace(e));
    		return null;
    	} finally {
    		try {
				tcpSocket.close();
    		} catch (Exception e) {
			}
    		try {
				out.close();
	    	} catch (Exception e) {
			}
	    	try {
				in.close();
			} catch (Exception e) {
			}
    	}
    }
    private void send(OutputStream out , byte abyte0[])
        throws IOException
    {
        byte abyte1[] = new byte[4];
        intToNetworkByteOrder(abyte0.length, abyte1, 0, 4);
        out.write(abyte1);
        out.write(abyte0);
        out.flush();
    }

    private byte[] receive(InputStream in)
        throws IOException
    {
        byte abyte0[] = new byte[4];
        int i = readFully(in,abyte0, 4);
        if(i != 4)
        {
            logger.debug(">>>DEBUG: TCPClient could not read length field");
            return null;
        }
        int j = networkByteOrderToInt(abyte0, 0, 4);
        logger.debug((new StringBuilder()).append(">>>DEBUG: TCPClient reading ").append(j).append(" bytes").toString());
        if(j <= 0)
        {
            logger.error((new StringBuilder()).append(">>>DEBUG: TCPClient zero or negative length field: ").append(j).toString());
            return null;
        }
        byte abyte1[] = new byte[j];
        i = readFully(in,abyte1, j);
        if(i != j)
        {
            logger.debug((new StringBuilder()).append(">>>DEBUG: TCPClient could not read complete packet (").append(j).append("/").append(i).append(")").toString());
            return null;
        } else
        {
            return abyte1;
        }
    }

    private int readFully(InputStream in , byte abyte0[], int i)
        throws IOException
    {
        int k = 0;
        int j;
        for(; i > 0; i -= j)
        {
            j = in.read(abyte0, k, i);
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
    
    
    public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}
