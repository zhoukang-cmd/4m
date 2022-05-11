package com.fourm.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.fourm.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 拦截器，每次收到一个请求，都要从session中获取请求的用户信息，如果用户信息不存在，或者用户名为空，密码信息为空，将返回到loginPage页面
 * add by zhangjuncai2022-03-24
 * */
public class LoginInterceptor extends AbstractInterceptor{
	
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User) request.getSession().getAttribute("account");
		if (user!=null && (!StringUtils.isBlank(user.getUserName()))&& (!StringUtils.isBlank(user.getUserPassword()))) {
			return invocation.invoke(); 
		} else {
			return "loginPage";
		}
	}
}
