package com.fourm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUtil {
	
	/**
	 * 对数据进行加密  add by zjc  20220329
	 * @param msgs
	 * @param encoding
	 * @return
	 */
	public static String getMd5(String msgs, String encoding) {
		byte abyte0[] = null;
		MessageDigest messagedigest;
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nosuchalgorithmexception) {
			throw new IllegalArgumentException("no md5 support");
		}
		try {
			messagedigest.update(msgs.getBytes(encoding));
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		abyte0 = messagedigest.digest();
		return byte2hex(abyte0);
	}

	/**
	 * 将bytes转换成十进制的字符串 add by zjc  20220329
	 * 
	 * @param abyte0
	 * @return
	 */
	public static String byte2hex(byte abyte0[]) {
		StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
		for (int i = 0; i < abyte0.length; i++) {
			if ((abyte0[i] & 0xff) < 16) {
				stringbuffer.append("0");
			}
			stringbuffer.append(Long.toString((long) abyte0[i] & (long) 255, 16));
		}

		return stringbuffer.toString().toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(getMd5("123456", "UTF-8"));
	}
}
