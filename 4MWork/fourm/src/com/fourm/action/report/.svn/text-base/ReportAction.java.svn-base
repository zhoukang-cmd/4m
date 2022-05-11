package com.fourm.action.report;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.Field;
import com.fourm.service.history.HistoryDataService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;
/**
 * 历史报表
 * */
@SuppressWarnings("serial")
public class ReportAction extends BaseAction {
	private final String nav = "history"; 
	private String searchTime;	
	private String timeSpan ;
	//返回页面的信息
	private String roomname;
	private String tableTitle;
	private String backTime;
	
	//保存表头的字段信息
	private String[] strListTitle;
	private String[] strListContent;
	//保存表格的数据信息
	private List<String[]> strListAll = new ArrayList<String []>();
	
	public String goReportSelect() throws ParseException{
		return "reportSelect";
	}
	
	@SuppressWarnings("rawtypes")
	public String goReport() throws ParseException{
		LogUtil.LogInfo("goReport()","历史报表查询开始"+searchTime);
		Equip currentSession =   (Equip) getValueByKey("currentSession");
		String tableName = "T_L_"+currentSession.getProvCode()+"_"+currentSession.getCompCode()+"_"+currentSession.getMineCode()+"_"+
							currentSession.getRommCode()+"_"+currentSession.getEquipCode()+"_"+currentSession.getEquipNum();
		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		List<String> tables = historydataService.getExistsTable(); //获得全部用户表
		if(!tables.contains(tableName)){
			return "reportSelect";
		}
		if(searchTime==null || searchTime==""){
			searchTime = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getIntervalDate(new Date(), -1));
		}
		if(timeSpan=="" || timeSpan==null){
			timeSpan="0";//默认按天查
		}
		backTime=searchTime;		
		roomname = currentSession.getRoomName()+currentSession.getEquipName()+currentSession.getEquipNum();
		System.out.println("timeSpan:"+timeSpan);
		int iTimeSpan = 1000*60*60;
		int iTimeTotal = 24;
		if(timeSpan.equals("0"))
		{	LogUtil.LogInfo("goReport()","历史报表查询开始"+searchTime+"按天");
			iTimeSpan = 1000*60*60;//每小时毫秒数
			iTimeTotal = 24;//一天小时数
			tableTitle = currentSession.getProvName()+currentSession.getCompName()+currentSession.getMineName()+"在线监测每小时（按天）参数历史报表";
			searchTime += " 00:00:00";//获得时间
		}
		else if(timeSpan.equals("1"))
		{	LogUtil.LogInfo("goReport()","历史报表查询开始"+searchTime+"按月");
			iTimeSpan = 1000*60*60*24;//每天毫秒数
			iTimeTotal = 30;//一个月天数
			tableTitle = currentSession.getProvName()+currentSession.getCompName()+currentSession.getMineName()+"在线监测每天（按月）参数历史报表";			
			searchTime += " 00:00:00";//获得时间
		}
		else if(timeSpan.equals("2"))
		{	LogUtil.LogInfo("goReport()","历史报表查询开始"+searchTime+"按小时");
			iTimeSpan = 1000*60;//每分钟毫秒数
			iTimeTotal = 60;//一小时分钟数
			tableTitle = currentSession.getProvName()+currentSession.getCompName()+currentSession.getMineName()+"在线监测每分钟（按小时）参数历史报表";			
		}
		System.out.println("标题："+tableTitle);
		
		List<String> lstSearchTime = new ArrayList<String>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Date searchDate = formatter.parse(searchTime);	
		Date preDate = searchDate;
			for(int i = 0; i <=iTimeTotal; i++)
			{	
				Date tmpDate = new Date();
				if(i==0){
					tmpDate.setTime(preDate.getTime());

				}else{
					tmpDate.setTime(preDate.getTime() + iTimeSpan);
				}
				lstSearchTime.add(formatter.format(tmpDate));
				preDate =  tmpDate;
			}
		

		//获取表信息和字段信息
		//List<String> tables = historydataService.getExistsTable();//得到数据库中表名集合
		List<Field> fields = historydataService.getField(currentSession.getEquipId());//得到field表中字段信息
		
		//首先获取表头
		strListTitle = new String[fields.size()+1];
		strListTitle[0] = "时间";
		int iNum = 1;
		for (Field field : fields) 
		{		
			strListTitle[iNum] = field.getFieldName();
			iNum++;
		}		
		
		//外循环为时间，说明在同一个时间点，取出所有的该时间点的数据
		for(String lst:lstSearchTime)
		{
			//用来保存一行的数据
			strListContent = new String[fields.size()+1];
			if(timeSpan.equals("0")){
				strListContent[0] = lst.substring(11);
			}
			if(timeSpan.equals("1")){
				strListContent[0] = lst.substring(0, 10);
			}
			if(timeSpan.equals("2")){
				strListContent[0] = lst.substring(11);
			}
			//处理日期，获得开始时间和结束时间
			Date start = formatter.parse(lst);				
			HashMap<String ,Object> tmp = new HashMap<String ,Object>();
			tmp.put("TABLENAME", tableName);
			tmp.put("start", start);
			if(timeSpan.equals("0")){//按天   每小时一条数据
				tmp.put("end", DateUtil.getNextDate(start, 60*60-1));
			}else if(timeSpan.equals("1")){//按月   每天一条数据
				tmp.put("end", DateUtil.getNextDate(start, 60*60*24-1));
			}else if(timeSpan.equals("2")){//按小时   每分钟一条数据
				tmp.put("end", DateUtil.getNextDate(start, 60-1));
			}
			//内循环为表名，获取每一个时间点的所有表的参数值
			DecimalFormat decimalFormat = new DecimalFormat( "##0.00 ");
			for(int j=1;j<=fields.size();j++){
				tmp.put("COLUMN", "value"+j);
				HashMap valueMap = historydataService.getValue(tmp);
				if(valueMap!=null){
					strListContent[j] = decimalFormat.format(Float.parseFloat(valueMap.get("VALUE").toString()));
				}
			}
			strListAll.add(strListContent);
		}
		LogUtil.LogInfo("goReport()","历史报表查询结束"+searchTime);
		return "goReport";
	}
	public String getSearchTime() {
		return searchTime;
	}
	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}
	public String getTimeSpan() {
		return timeSpan;
	}
	public void setTimeSpan(String timeSpan) {
		this.timeSpan = timeSpan;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	
	public String getTableTitle() {
		return tableTitle;
	}

	public void setTableTitle(String tableTitle) {
		this.tableTitle = tableTitle;
	}

	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public String[] getStrListTitle() {
		return strListTitle;
	}
	public void setStrListTitle(String[] strListTitle) {
		this.strListTitle = strListTitle;
	}
	public String[] getStrListContent() {
		return strListContent;
	}
	public void setStrListContent(String[] strListContent) {
		this.strListContent = strListContent;
	}
	public List<String[]> getStrListAll() {
		return strListAll;
	}
	public void setStrListAll(List<String[]> strListAll) {
		this.strListAll = strListAll;
	}
	public String getNav() {
		return nav;
	}
	
}
