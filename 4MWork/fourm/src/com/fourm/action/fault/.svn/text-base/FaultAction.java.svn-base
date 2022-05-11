package com.fourm.action.fault;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.Fault;
import com.fourm.entity.Field;
import com.fourm.entity.ShowDataView;
import com.fourm.service.display.DisplayService;
import com.fourm.service.fault.FaultService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;
/**
 * 历史故障
 * */

@SuppressWarnings("serial")
public class FaultAction extends BaseAction{

	private final String nav = "equip";
	private List<Fault> faultList;//页面发布结果列表
	private Fault fault;
	private int detailId;
	private String delfaultStr;

	/**
	 * 前台       设备信息页面的action。
	 */
	public String goFault(){
		
		Equip currentSession = (Equip)getValueByKey("currentSession");
		FaultService faultService = (FaultService) getBeanById("faultService");
		
		HashMap<String, Object> faultMap = new HashMap<String, Object>();
		faultMap.put("pageCtrl", pageCtrl);
		
		if(startTime == null && endTime==null){
			endTime = new Date();
			startTime = DateUtil.getIntervalDate(endTime, -30);
		}
		if(startTime != null && endTime==null){
			endTime = DateUtil.getIntervalDate(startTime, 1);
		}
		
		faultMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime));
		faultMap.put("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
		faultMap.put("equipId" , currentSession.getEquipId());
		
		
		if(pageCtrl != null)
		{
			this.pageCtrl = faultService.pageFormat(this.getPageCtrl().getCurrPage(), pageSize , faultMap);
		}
		else
		{
			this.pageCtrl = faultService.pageFormat(1, pageSize ,faultMap);			
		}
		
		faultMap.put("pageCtrl", pageCtrl);
		faultList = faultService.getFaultList(faultMap);
		
		LogUtil.LogInfo("goFault()","提交时间：startTime:"+startTime+"endTime:"+endTime);
		return "goFault";
	}
	
	

	public String goFaultDetail(){
		FaultService faultService = (FaultService) getBeanById("faultService");
		fault= faultService.getFaultById(detailId);
		LogUtil.LogInfo("goFaultDetail()","faultId:"+detailId);
		return "goFaultDetail";
	}
	
	public String goPainter(){
		FaultService faultService = (FaultService) getBeanById("faultService");
		fault=faultService.getFaultById(detailId);
		
		return "goPainter";
	}
	//根据一段时间查出数据绘制图表
	public String goFaultPainter(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip  currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		FaultService faultService = (FaultService) getBeanById("faultService");
		DisplayService displayService=(DisplayService) getBeanById("displayService");                            
		fault= faultService.getFaultById(detailId);
		String faultTime=fault.getFaultTime();//根据此时间点查找前后各四个小时内的监测数据
		String faultType=fault.getFaultField();
		
		HashMap map=new HashMap<String,Object>();
		map.put("equipID",currentSession.getEquipId());
		map.put("fieldDec",faultType);
		
		String num=displayService.getFieldNum(map);
		
		String tableName = "T_L_"+currentSession.getProvCode()+"_"+currentSession.getCompCode()+"_"+currentSession.getMineCode()+"_"+currentSession.getRommCode()+"_"+currentSession.getEquipCode()+"_"+currentSession.getEquipNum();
		//标志时间点
		Date flagTime=DateUtil.formatToDate(faultTime,"yyyy-MM-dd HH:mm:ss");
		
		
		Map<String,Object> extMap=new HashMap<String,Object>();
		List extLs=new ArrayList<ShowDataView>();
		//前四个小时的数据
		String temp=null;
		for(int i=12;i>=0;i--){
	    ShowDataView sd=new ShowDataView();
		Date preDate1=DateUtil.getPrevDate(flagTime,1800*(i-1));
		Date preDate2=DateUtil.getPrevDate(flagTime,1800*i);
		
		HashMap tmp=new HashMap<String,Object>();
		tmp.put("start", preDate2);
		tmp.put("end", preDate1);
		tmp.put("COLUMN", "VALUE"+num);
		tmp.put("TABLENAME", tableName);
		HashMap<String, Object> compareTmp = new HashMap<String, Object>();
		compareTmp =displayService.getValueByProcess(tmp);
		String value=null;
		if(compareTmp!=null && compareTmp.get("VALUE")!=null){
			//value=(String)compareTmp.get("VALUE");
			temp=compareTmp.get("VALUE").toString();
			
		}
		value=temp;
		
		sd.setFieldName(DateUtil.formatDate(preDate2, "HH:mm"));
		if(StringUtils.isEmpty(value)){
			value="0.0";
		}
		System.out.println(value);
		sd.setExtString(value);
		
		extLs.add(sd);
		
		System.out.println("前四个小时的数据");
		}
		//后四个小时的数据
		temp=null;
		for(int i=0;i<12;i++){
	    ShowDataView sd=new ShowDataView();
	    Date preDate1=DateUtil.getNextDate(flagTime,1800*i);
		Date preDate2=DateUtil.getNextDate(flagTime,1800*(i+1));
		
		HashMap tmp=new HashMap<String,Object>();
		tmp.put("start", preDate1);
		tmp.put("end", preDate2);
		tmp.put("COLUMN", "VALUE"+num);
		tmp.put("TABLENAME", tableName);
		
		HashMap<String, Object> compareTmp = new HashMap<String, Object>();
		compareTmp =displayService.getValueByProcess(tmp);
		String value=null;
		if(compareTmp!=null && compareTmp.get("VALUE")!=null){
			//value=(String)compareTmp.get("VALUE");
			temp=compareTmp.get("VALUE").toString();
		
		}
		value=temp;
        
		sd.setFieldName(DateUtil.formatDate(preDate2, "HH:mm"));
		if(StringUtils.isEmpty(value)){
			value="0.0";
		}
		System.out.println(value);
		sd.setExtString(value);
		
		extLs.add(sd);
		System.out.println("后四个小时的数据");
		}
		
		
		
		extMap.put("extLs", extLs);
		
		
		JSONObject jsonObject = JSONObject.fromObject(extMap);
		request.setAttribute("json", jsonObject);
		
		LogUtil.LogInfo("goFaultPainter()","faultId:"+detailId);
		return "json";
	}
	//实时数据图形
	public String showDispalyData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip  currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		FaultService faultService = (FaultService) getBeanById("faultService");
		DisplayService displayService=(DisplayService) getBeanById("displayService");                            
		fault= faultService.getFaultById(detailId);
		String faultType=fault.getFaultField();
		
		HashMap map=new HashMap<String,Object>();
		map.put("equipID",currentSession.getEquipId());
		map.put("fieldDec",faultType);
		
		String num=displayService.getFieldNum(map);
		
		
		String tableName = "T_L_"+currentSession.getProvCode()+"_"+currentSession.getCompCode()+"_"+currentSession.getMineCode()+"_"+currentSession.getRommCode()+"_"+currentSession.getEquipCode()+"_"+currentSession.getEquipNum();
		
		HashMap tmp=new HashMap<String,Object>();
		tmp.put("COLUMN", "VALUE"+num);
		tmp.put("TABLE", tableName);
		String value=displayService.getDisplayValue(tmp);
		System.out.println("@@@@@@@@@@@@@@@@@@@"+value);
		ShowDataView sd=new ShowDataView();
		sd.setExtString(value);
		JSONObject jsonObject = JSONObject.fromObject(sd);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	public String queryData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip  currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		FaultService faultService = (FaultService) getBeanById("faultService");
		DisplayService displayService=(DisplayService) getBeanById("displayService");                            
		fault= faultService.getFaultById(detailId);
		String faultType=fault.getFaultField();
		
		HashMap map=new HashMap<String,Object>();
		map.put("equipID",currentSession.getEquipId());
		map.put("fieldDec",faultType);
		
		String num=displayService.getFieldNum(map);
		
		
		String tableName = "T_L_"+currentSession.getProvCode()+"_"+currentSession.getCompCode()+"_"+currentSession.getMineCode()+"_"+currentSession.getRommCode()+"_"+currentSession.getEquipCode()+"_"+currentSession.getEquipNum();
		
		
		Map<String,Object> extMap=new HashMap<String,Object>();
		List extLs=new ArrayList<ShowDataView>();
		Date flag=new Date();
		//前25秒的数据
		for(int i=15;i>=0;i--){
	    ShowDataView sd=new ShowDataView();
		Date preDate1=DateUtil.getPrevDate(flag,3*(i-1));
		Date preDate2=DateUtil.getPrevDate(flag,3*i);
		
		HashMap tmp=new HashMap<String,Object>();
		tmp.put("start", preDate2);
		tmp.put("end", preDate1);
		tmp.put("COLUMN", "VALUE"+num);
		tmp.put("TABLENAME", tableName);
		
		
		HashMap<String, Object> compareTmp = new HashMap<String, Object>();
		compareTmp =displayService.getValueByProcess(tmp);
		String value=null;
		if(compareTmp!=null && compareTmp.get("VALUE")!=null){
			//value=(String)compareTmp.get("VALUE");
			value=compareTmp.get("VALUE").toString();
		}
		
		if(StringUtils.isEmpty(value)){
			value="0.0";
		}
		sd.setFieldName(DateUtil.formatDate(preDate2, "yyyy-MM-dd HH:mm:ss"));
		sd.setExtString(value);
		extLs.add(sd);
		
		}
		extMap.put("extLs",extLs);
		JSONObject jsonObject = JSONObject.fromObject(extMap);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	
	
	//删除所选
	public String delFault(){
		FaultService faultService = (FaultService) getBeanById("faultService");
		if (StringUtils.isNotBlank(delfaultStr)) {
			String[] arr = delfaultStr.split("%");
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] != "") {
						int id = Integer.parseInt(arr[i]);
						faultService.delFault(id);
					}
				}
			}
		}
		goFault();
		LogUtil.LogInfo("delFault()","删除所选信息:"+detailId);
		return "goFault";
		
	}
	
	public String getExtList()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip  currentSession =   (Equip) getValueByKey("currentSession");
		DisplayService displayService = (DisplayService) getBeanById("displayService");

		List<Field> extList = displayService.getDisplayField(currentSession.getEquipId());

		String imagePath = currentSession.getDisplayPic();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", "1");
		jsonMap.put("extList", extList);
		jsonMap.put("imagePath", imagePath);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	
	
	public String addFault(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession = (Equip) request.getSession().getAttribute("currentSession");
		FaultService faultService = (FaultService) getBeanById("faultService");
		
		fault.setEquipId(currentSession.getEquipId());
		faultService.addFault(fault);
		
		LogUtil.LogInfo("addFault()","添加历史故障信息:"+fault.getFaultLevel()+fault.getFaultPosition()+fault.getFaultTime()+fault.getFaultTrend()+fault.getEquipId());
		return goFault();
		
	}
	//跳转到修改页面
	public String goModifyFault(){
		FaultService faultService = (FaultService) getBeanById("faultService");
		fault= faultService.getFaultById(detailId);
		LogUtil.LogInfo("goModifyFault()","跳转到修改页面faultId:"+detailId);
		return "goModifyFault";
	}
	//修改故障信息
	public String modifyFault(){
		FaultService faultService = (FaultService) getBeanById("faultService");
		faultService.modifyFault(fault);
		goFault();
		LogUtil.LogInfo("modifyFault()","修改故障信息："+fault.getFaultTime()+fault.getFaultLevel()+fault.getFaultPosition()+fault.getFaultTrend()+fault.getFaultId());
		return "goFault";
	}
	
	
	public String goAddFault(){
		return "goAddFault";
	}
	public List<Fault> getFaultList() {
		return faultList;
	}


	public void setFaultList(List<Fault> faultList) {
		this.faultList = faultList;
	}


	public Fault getFault() {
		return fault;
	}


	public void setFault(Fault fault) {
		this.fault = fault;
	}


	public int getDetailId() {
		return detailId;
	}


	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}


	public String getDelfaultStr() {
		return delfaultStr;
	}


	public void setDelfaultStr(String delfaultStr) {
		this.delfaultStr = delfaultStr;
	}

	public String getNav() {
		return nav;
	}
	
	
}




