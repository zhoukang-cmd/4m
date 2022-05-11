package com.fourm.action.history;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Field;
import com.fourm.entity.KeyAndValue;
import com.fourm.service.history.HistoryDataService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;
/**
 * 历史参数
 * 
 * */
@SuppressWarnings("serial")
public class HistoryDataAction extends BaseAction  {

	private final String nav="history";
	private List<KeyAndValue> valueList;
	private int timeFlag;
	private String beginTime;
	private String backTime;
	
	private int selectFieldNo;
	private String selectFieldName;
	
	@SuppressWarnings("rawtypes")
	public String goHistoryData() throws ParseException{
		LogUtil.LogInfo("goHistoryData()", "");
		initData();
		//无参数直接返回
		if(fields.get(0).getFieldNo()==null || "".equals(fields.get(0).getFieldNo())){
			return "goHistoryData" ;
		}
		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		valueList = new ArrayList<KeyAndValue>();
		
		int iTimeTotal = 24;//一天小时数
		Date start;
		Date end;
		if(timeFlag==0 || beginTime==null || beginTime==""){
			timeFlag=1;
			beginTime = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getIntervalDate(new Date(), -1));
		}	
			backTime =beginTime;
			beginTime += " 00:00:00";
		if( selectFieldNo==0){
			selectFieldNo=Integer.parseInt(fields.get(0).getFieldNo());
			
		}
		for(Field f:fields){
			
			if(f.getFieldNo()!=null && !"".equals(f.getFieldNo())&& Integer.parseInt(f.getFieldNo())==selectFieldNo){
				selectFieldName = f.getFieldName();
			}
		}
		start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime);
		HashMap<String ,Object> tmp = new HashMap<String ,Object>();
		tmp.put("TABLENAME", tableName);
		
		switch (timeFlag) {
		case 1://一天
			LogUtil.LogInfo("goHistoryData()","历史参数一天"+start);
			iTimeTotal = 24; 
			end = DateUtil.getNextDate(start, 60*60-1);
			for(int i=1;i<=iTimeTotal ;i++){
				tmp.put("start", start);
				tmp.put("end", end);
				tmp.put("COLUMN", "VALUE"+selectFieldNo);
				Map valueMap = (Map)historydataService.getValue(tmp);
				KeyAndValue kv = new KeyAndValue();
				if(valueMap==null || valueMap.get("VALUE")==null ){
					String key = DateUtil.formatDate(start, "HH");
					 kv.setKeyString(key);
				}else{
					String key = DateUtil.formatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueMap.get("REAL_TIME").toString()), "HH");
					String value = valueMap.get("VALUE").toString();
					
					kv .setKeyString(key);
					kv.setValueString(value);
				}
				valueList.add(kv);
				
				start = DateUtil.getNextDate(end, 1);
				end = DateUtil.getNextDate(start, 60*60-1);
			}
			
			break;
		case 2://一周
			LogUtil.LogInfo("goHistoryData()","历史参数一周"+start);
			iTimeTotal = 4*7; 
			end = DateUtil.getNextDate(start, 60*60*6-1);
			for(int i=1;i<=iTimeTotal ;i++){
				tmp.put("start", start);
				tmp.put("end", end);
				tmp.put("COLUMN", "VALUE"+selectFieldNo);
				Map valueMap = (Map)historydataService.getValue(tmp);
				KeyAndValue kv = new KeyAndValue();
				if(valueMap==null || valueMap.get("VALUE")==null ){
					String key = DateUtil.formatDate(start, "dd");
					 kv.setKeyString(key);
				}else{
					String key = DateUtil.formatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueMap.get("REAL_TIME").toString()), "dd");
					String value = valueMap.get("VALUE").toString();
					
					kv .setKeyString(key);
					kv.setValueString(value);
				}
				valueList.add(kv);
				
				start = DateUtil.getNextDate(end, 1);
				end = DateUtil.getNextDate(start, 60*60*6-1);
			}
			break;
		case 3://一月
			LogUtil.LogInfo("goHistoryData()","历史参数一月"+start);
			iTimeTotal = 30; 
			end = DateUtil.getNextDate(start, 60*60*24-1);
			for(int i=1;i<=iTimeTotal ;i++){
				tmp.put("start", start);
				tmp.put("end", end);
				tmp.put("COLUMN", "VALUE"+selectFieldNo);
				Map valueMap = (Map)historydataService.getValue(tmp);
				KeyAndValue kv = new KeyAndValue();
				if(valueMap==null || valueMap.get("VALUE")==null ){
					String key = DateUtil.formatDate(start, "dd");
					 kv.setKeyString(key);
				}else{
					String key = DateUtil.formatDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueMap.get("REAL_TIME").toString()), "dd");
					String value = valueMap.get("VALUE").toString();
					
					kv .setKeyString(key);
					kv.setValueString(value);
				}
				valueList.add(kv);
				
				start = DateUtil.getNextDate(end, 1);
				end = DateUtil.getNextDate(start, 60*60*24-1);
			}
			break;
		}
		return "goHistoryData" ;
	}

	
	public int getTimeFlag() {
		return timeFlag;
	}
	public void setTimeFlag(int timeFlag) {
		this.timeFlag = timeFlag;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	public List<KeyAndValue> getValueList() {
		return valueList;
	}
	public void setValueList(List<KeyAndValue> valueList) {
		this.valueList = valueList;
	}
	public int getSelectFieldNo() {
		return selectFieldNo;
	}
	public void setSelectFieldNo(int selectFieldNo) {
		this.selectFieldNo = selectFieldNo;
	}
	public String getSelectFieldName() {
		return selectFieldName;
	}
	public void setSelectFieldName(String selectFieldName) {
		this.selectFieldName = selectFieldName;
	}
	public String getNav() {
		return nav;
	}
	
	
}
