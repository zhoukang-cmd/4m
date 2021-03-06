package com.fourm.action.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fourm.entity.Equip;
import com.fourm.entity.Field;
import com.fourm.entity.PageControl;
import com.fourm.service.history.HistoryDataService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action公共方法
 */

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	protected final int pageSize = 10;// 前台 设置每页显示多少条记录
	protected PageControl pageCtrl;

	protected Date startTime;
	protected Date endTime;

	protected List<Field> fields;
	protected String tableName;// 设备低频表名T_L_

	protected Object getValueByKey(String key) {
		ActionContext actionContext = ActionContext.getContext();
		return actionContext.getSession().get(key);
	}

	protected void setKeyAndValue(String key, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		actionContext.getSession().put(key, value);
	} 

	
	/**
	 * 当 Web 应用集成 Spring 容器后，代表 Spring 容器的WebApplicationContext对象将以
     * WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE 为键存放在ServletContext的属性列表中。
     * 您当然可以直接通过以下语句获取 WebApplicationContext：
     * WebApplicationContext wac = 
     * (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	 * 
	 * 但通过位于 org.springframework.web.context.support 包中的WebApplicationContextUtils 工具类获取 WebApplicationContext 更方便：
	 * ApplicationContext ac1 =WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContext sc);
	 * ApplicationContext ac2 =WebApplicationContextUtils.getWebApplicationContext(ServletContext sc);
	 * ac1.getBean("beanId");
	 * ac2.getBean("beanId");
	 * 
	 * @param beanId
	 * @return
	 */
	protected Object getBeanById(String beanId) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
		return ctx.getBean(beanId);
	}

	protected void initData() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession = (Equip) request.getSession().getAttribute("currentSession");
		tableName = "T_L_" + currentSession.getProvCode() + "_" + currentSession.getCompCode() + "_"
				+ currentSession.getMineCode() + "_" + currentSession.getRommCode() + "_"
				+ currentSession.getEquipCode() + "_" + currentSession.getEquipNum();

		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		List<String> tables = historydataService.getExistsTable(); // 获得全部用户表
		fields = historydataService.getField(currentSession.getEquipId()); // 获得全部参数

		// 无参数处理
		if (!tables.contains(tableName) || fields == null || fields.isEmpty()) {
			fields = new ArrayList<Field>();
			Field f = new Field();
			f.setFieldName("无参数");
			fields.add(f);
		}
		for (Field f : fields) {
			System.out.println(
					f.getFieldId() + f.getFieldName() + f.getFieldNo() + f.getFieldDisplayX() + f.getFieldDisplayY());
		}
	}

	public void setServletRequest(HttpServletRequest arg0) {
	}

	public void setServletResponse(HttpServletResponse arg0) {
	}

	public PageControl getPageCtrl() {
		return pageCtrl;
	}

	public void setPageCtrl(PageControl pageCtrl) {
		this.pageCtrl = pageCtrl;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

}
