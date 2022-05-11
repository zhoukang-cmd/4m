package com.fourm.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * Web Utils函数集合.
 * 
 */
public class WebUtils {

	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	/**
	 * 设置让浏览器弹出下载对话框的Header.
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setDownloadableHeader(HttpServletResponse response, String fileName) {
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 设置过期时间 Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "max-age=" + expiresSeconds);
	}

	/**
	 * 设置无缓存Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 0);
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 检查浏览器客户端是否支持gzip编码.
	 */
	public static boolean checkAccetptGzip(HttpServletRequest request) {
		// Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");

		if (StringUtils.contains(acceptEncoding, "gzip")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置Gzip Header并返回GZIPOutputStream.
	 */
	public static OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header,计算Etag是否无效.
	 * 
	 * 如果Etag有效,checkIfNoneMatch返回false, 设置304 not modify status.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!headerValue.equals("*")) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 取得带相同前缀的Request Parameters.
	 * 
	 * 返回的结果Parameter名已去除前缀.
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParametersStartingWith(HttpServletRequest request, String prefix) {
		return org.springframework.web.util.WebUtils.getParametersStartingWith(request, prefix);
	}

	
	/**
	 * "X-Forwarded-For"用以跟踪 原有的客户端IP地址和原来客户端请求的服务器地址。
	 * 当我们访问index.jsp/时，其实并不是我们浏览器真正访问到了服务器上的index.jsp 文件，
	 * 而是先由代理服务器去访问index.jsp ，代理服务器再将访问到的结果返回给我们的浏览器，
	 * 因为是代理服务器去访问index.jsp的，所以index.jsp中通过 request.getRemoteAddr（）
	 * 的方法获取的IP实际上是代理服务器的地址，并不是客户端的IP地址。于是可得出获得客户端真实IP地址 的方法一：
	 * @return
	 */
	public static String getRemoteIpAddress() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String rip = request.getRemoteAddr();
		String xff = request.getHeader("X-Forwarded-For");
		String ip;
		
		//如果xff有多个IP地址组成，这是通过代理进行访问，这种情况下，第一个IP地址才是用户的实际IP地址。
		if (xff != null && xff.length() != 0) {
			int px = xff.indexOf(',');
			if (px != -1) {
				ip = xff.substring(0, px);
			} else {
				ip = xff;
			}
		} else {
			ip = rip;
		}
		
		return ip.trim();
	}

	public static String getRemoteIpAddress(HttpServletRequest request) {
		String rip = request.getRemoteAddr();
		String xff = request.getHeader("X-Forwarded-For");
		String ip;
		if (xff != null && xff.length() != 0) {
			int px = xff.indexOf(',');
			if (px != -1) {
				ip = xff.substring(0, px);
			} else {
				ip = xff;
			}
		} else {
			ip = rip;
		}
		return ip.trim();
	}

	/**
	 * 获取用户的url请求
	 * 
	 * @param request
	 * @param invocation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getGoingToURL(HttpServletRequest request, List<String> excludeParams) throws Exception {
		StringBuffer buffer = new StringBuffer();
		Map<String, String[]> param = request.getParameterMap();
		if (param != null && !param.isEmpty()) {
			for (String s : param.keySet()) {
				if (excludeParams != null && !excludeParams.contains(s)) {
					String[] value = param.get(s);
					for (String val : value) {
						if (!s.equals("undefined")) {
							buffer.append(s + "=" + URLEncoder.encode(val, "UTF-8") + "&");
						}
					}
				}
			}
		}
		String str = buffer.toString();
		if (str.endsWith("&")) {
			return request.getRequestURL() + "?" + str.substring(0, str.length() - 1);
		} else {
			return request.getRequestURL().toString();
		}
	}

}
