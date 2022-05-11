package com.fourm.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.User;
import org.apache.log4j.*;

@SuppressWarnings("serial")
public class LogUtil extends BaseAction implements RequestAware{
	
	private static Logger log=Logger.getLogger(LogUtil.class.getName());

	/**
	 * 记录日志
	 * @param action 用户行为标识
	 * @param user 用户名
	 * @param currentSession 当前会话
	 * @param extra 附加信息
	 */
	public static void LogInfo(String action , String extra) {
		try
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			Equip currentSession =   (Equip) request.getSession().getAttribute("currentSession");
			User user = (User) request.getSession().getAttribute("account");
			String equipInfo = currentSession.getProvId()+ currentSession.getProvCode() + currentSession.getProvName() +" "
								+currentSession.getCompId()+ currentSession.getCompCode() +  currentSession.getCompName() +" "
								+currentSession.getMineId()+ currentSession.getMineCode() + currentSession.getRoomId() + " "
								+currentSession.getRoomId()+ currentSession.getRommCode() + currentSession.getRoomName() + " "
								+currentSession.getEquipId()+ currentSession.getEquipCode() + currentSession.getEquipNum() + currentSession.getEquipName();
			String userInfo = user.getUserName() +user.getRoleName() +user.getPowerName();
	 		String message = userInfo + '\t' + action + '\t' + equipInfo;
			
			if (extra != null && extra != ""){ //附加信息
				message += '\t' + extra;
			}
			log.info(message);
		}
		catch(Exception e)
		{
			log.error(LogUtil.class.getName()+" LogInfo()----Exception:"+e.getMessage());
		}
		
		
	}

	public void setRequest(Map<String, Object> arg0) {
		
	}
	
	
}
