package com.fourm.action.analyse;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
/**
 * 智能分析
 * */

@SuppressWarnings("serial")
public class AnalyseAction extends BaseAction {
	
	private final String nav = "analyse";
	static private List<String> reportList;
	
	public String goAnalyse() throws Exception{
		reportList = new ArrayList<String>();
		File reportFile = null;
		Equip currentSession = (Equip)getValueByKey("currentSession");
		String equip = currentSession.getProvCode() + "_" + currentSession.getCompCode() + "_" + currentSession.getMineCode()
				+ "_" + currentSession.getRommCode() + "_" + currentSession.getEquipCode() + "_" + currentSession.getEquipNum();
		
		Properties path = new Properties();
		path.load(new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath("WEB-INF/classes/path.properties"))));
		File reportDir = new File((String) path.get("reportPath"));
		File[] files = reportDir.listFiles();
		for (File file: files) {
			String name = file.getName();
			if (name.startsWith(equip) && (reportFile == null || name.compareTo(reportFile.getName()) > 0)) {
				reportList.add(file.getAbsolutePath());
			}
		}
		Collections.sort(reportList);
		Collections.reverse(reportList);
		if (reportList.size() > 100) { //网页上只显示最新的100条报表
			reportList = reportList.subList(0, 100);
		}
		return "goAnalyse";
	}
	
	public String getAnalyse() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String order = request.getParameter("order");
		File source = new File(reportList.get(Integer.parseInt(order)));
		File download = new File(ServletActionContext.getServletContext().getRealPath("/downloads/" + source.getName()));
		FileUtils.copyFile(source, download);
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("fileName", download.getName());
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	
	public String getNav() {
		return nav;
	}
	public List<String> getReportList() {
		return reportList;
	}
	public void setReportList(List<String> reportList) {
		AnalyseAction.reportList = reportList;
	}
}
