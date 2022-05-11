package com.fourm.action.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.User;
import com.fourm.service.user.UserService;
import com.fourm.util.LogUtil;
import com.fourm.util.WebUtils;
/**
 * 用户登录
 * */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {
	
	private final String nav = "display";
	private User user;
	private String autoLogin;
	private static Logger log=Logger.getLogger(UserAction.class.getName());
	
	/**
	 * 
	 * @return
	 */
	public String login() {
		
		UserService userService = (UserService) getBeanById("userService");
		//用户登录
		String ip = WebUtils.getRemoteIpAddress();
		
		user.setUserIp(ip);
		User temp = null;
		try
		{
			 temp = userService.getUser(user);
		}
		catch(Exception e)
		{
			log.error(this.getClass().getName()+"----["+e.getMessage()+"]");
		}
		
		
		
		//用户不存在
		if (temp == null) {
			return "loginPage"; 
		}
		
		//判断当前用户没有权限,权限共分4级
		if (temp.getPowerLevel()!=1 && temp.getPowerLevel()!=2 && temp.getPowerLevel()!=3 && temp.getPowerLevel()!=4) {
			return "loginPage";
		}
		
		user = temp;
		//将完整的经过验证的用户信息放置到session中。
		this.setKeyAndValue("account", user);
		this.setKeyAndValue("accountId", user.getUserName());//登录用户名
		this.setKeyAndValue("powerLevel", user.getPowerLevel());//所属权限级别
		this.setKeyAndValue("privId", user.getPrivId());
		
		
		//权限信息
		List<Equip> sessionEquip = userService.getPower(user);
		if(sessionEquip == null || sessionEquip.isEmpty()){
			return "loginPage";
		}else{
			setKeyAndValue("allInfo", sessionEquip);
			setKeyAndValue("currentSession", sessionEquip.get(0));
		}
		LogUtil.LogInfo("login()","");
		return "front";
		
	}
	
	/**
	 * 	//前台退出
	 * @return
	 */
	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		LogUtil.LogInfo("logout()","");
		request.getSession().invalidate();
		return "loginPage";
	}
	

	/**
	 * 	//自动登录
	 * @return
	 */
	public String autoLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		setAutoLogin(name + "\t" + passwd);
		return "loginPage";
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String getNav() {
		return nav;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}

}
