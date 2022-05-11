package com.fourm.action.compare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.Field;
import com.fourm.entity.KeyAndValue;
import com.fourm.service.history.HistoryDataService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;
/**
 * 工况对比   不同设备
 * */
@SuppressWarnings("serial")
public class CompareTwoAction extends BaseAction{
	private final String nav="compare";
	
	private  List<KeyAndValue> roomList ;//对比机房列表
	private List<KeyAndValue> equipList;//对比设备列表
	private List<KeyAndValue> fieldList;//公有设备列表
	private List<KeyAndValue> valueList;//公有设备列表
	
	private String selectRoomId;//选中机房的roomId
	private String selectEquipId;//选中设备equipId
	private String fieldNoStr;//选中的参数     格式：当前设备的fieldId:对比设备的fieldId
	private String compareTime;//页面提交对比时间
	private String backTime;//返回页面时间
	private String backValueSeries;//当前设备折线名
	private String backCompareSeries;//对比设备折线名
	private String selectFieldName;
	private String compareTableName;//对比设备的表名   T_L_
	//页面显示信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String goComparetwo() throws ParseException{
		LogUtil.LogInfo("goComparetwo()", "");
		Equip currentSession =   (Equip) getValueByKey("currentSession");
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");
		initData();
		if(fields.get(0).getFieldNo()==null  ){//当前设备没有参数
			return "goComparetwo";
		}
		/*if(fieldNoStr==null && selectEquipId!=null ){//没有参数处理
			return "goComparetwo";
		}*/
		//初始化选择机房和设备的ID
		if("".equals(selectRoomId) || selectRoomId==null){//初始页面     同一设备对比
			selectRoomId =String.valueOf(currentSession.getRoomId());
			selectEquipId = String .valueOf(currentSession.getEquipId());
			fieldNoStr = fields.get(0).getFieldNo()+":"+fields.get(0).getFieldNo();
		}
		
			
			
		backValueSeries = currentSession.getEquipName();
		for(Equip e:allInfo){
			if(e.getEquipId()==Integer.parseInt(selectEquipId)){
				backCompareSeries = e.getEquipName();
				break;
			}
		}
		
		//得到当前设备和对比设备的fieldNo
		String[] arr = fieldNoStr.split(":");
		
		for(Field f:fields){
			if(f.getFieldNo().equals(arr[0])){
				selectFieldName= f.getFieldName();
			}
			
		}
		if(compareTime==null || "".endsWith(compareTime)){
			compareTime = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getIntervalDate(new Date(), -1));
		}
		backTime = compareTime;
		compareTime +=" 00:00:00";
		
		//机房列表
		getCompareRoomList();
		//设备列表
		equipList(selectRoomId);
		//参数列表
		fieldList(Integer.parseInt(selectEquipId));
		//折线图
		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		valueList = new ArrayList<KeyAndValue>();
		
		
		HashMap<String, Object> valueTmp = new HashMap<String, Object>();
		HashMap<String, Object> compareTmp = new HashMap<String, Object>();
		Map valueMap;
		Map compareMap;
		valueTmp.put("TABLENAME", tableName);
		compareTmp.put("TABLENAME", compareTableName);
		Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(compareTime);
		Date end = DateUtil.getNextDate(start , 60*60-1);
		for(int i=1;i<=24;i++){
			valueTmp.put("start", start);
			valueTmp.put("end", end);
			valueTmp.put("COLUMN", "VALUE"+arr[0]);
			valueMap = (Map)historydataService.getValue(valueTmp);

			compareTmp.put("start", start);
			compareTmp.put("end", end);
			compareTmp.put("COLUMN", "VALUE"+arr[1]);
			compareMap = (Map)historydataService.getValue(compareTmp);
			
			KeyAndValue kv = new KeyAndValue();
			kv.setKeyString( DateUtil.formatDate(start, "HH"));
			if(valueMap==null || valueMap.get("VALUE")==null ){//valueMap为空
				if(compareMap==null || compareMap.get("VALUE")==null ){//compareMap为空
				}else{//compareMap不为空
					kv.setCompareValueString(compareMap.get("VALUE").toString());
				}
			}else{//valueMap不为空
				kv.setValueString(valueMap.get("VALUE").toString());
				if(compareMap==null || compareMap.get("VALUE")==null ){//compareMap为空
				}else{//compareMap不为空
					kv.setCompareValueString(compareMap.get("VALUE").toString());
				}
			}
			valueList.add(kv);
			
			start = DateUtil.getNextDate(end, 1);
			end = DateUtil.getNextDate(start, 60*60-1);
			
		}
		for(KeyAndValue kv:valueList){
			System.out.println(kv.getKeyString()+"当前值："+kv.getValueString()+"对比值："+kv.getCompareValueString());
		}
		LogUtil.LogInfo("goComparetwo()","工况对比不同设备  对比机房："+selectRoomId +"对比设备："+selectEquipId+"参数："+selectFieldName+"对比时间："+compareTime);
		return "goComparetwo";
	}
	//对比机房列表
	@SuppressWarnings("unchecked")
	public void getCompareRoomList(){
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");
		
		//机房信息
		roomList = new ArrayList<KeyAndValue>();
		for(Equip info : allInfo){	
			String key = String.valueOf(info.getRoomId());
			String value = info.getRoomName();
			KeyAndValue kv = new KeyAndValue(key, value);
			
			if(roomList.isEmpty()){
				roomList.add(kv);
			}else{
				boolean flag = false;
				for(KeyAndValue con:roomList){
					if(info.getRoomId()==Integer.parseInt(con.getKeyString())){
						flag = true;
						break;
					}
				}
				if(!flag){
					roomList.add(kv);
				}
			}
		}
	}
	//设备列表
	public String getCompareEquipList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String selectRoomId = request.getParameter("selectRoomId");
		equipList(selectRoomId);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("flag", "1");
		jsonMap.put("equipList", equipList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	//设备列表公共方法
	@SuppressWarnings("unchecked")
	public  void  equipList(String selectRoom){
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");
		equipList = new ArrayList<KeyAndValue>();
		
		for(Equip info : allInfo){	
			if("".equals(selectRoom) ||(!"".equals(selectRoom) && selectRoom.equals(String.valueOf(info.getRoomId())))){
				String key = String.valueOf(info.getEquipId());
				String value = info.getEquipName();
				KeyAndValue kv = new KeyAndValue(key, value);
				if(equipList==null || equipList.isEmpty() ){
					equipList.add(kv);
				}else {
					Boolean flag =false; 
					for(KeyAndValue con:equipList){
						if(info.getEquipId()==Integer.parseInt(con.getKeyString())){
							flag=true;//表示集合中已存在
							break;
						}
					}
					if(!flag){
						equipList.add(kv);
					}
				}
			}
		}	
	}
	//参数列表
	public String getCompareFieldList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String selectEquipId = request.getParameter("selectEquipId");
		fieldList(Integer.parseInt(selectEquipId));
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("flag", "1");
		jsonMap.put("fieldList", fieldList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	//公有参数列表公共方法
	@SuppressWarnings("unchecked")
	public void fieldList(int equipId){
		//当前设备的参数
		initData();
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");
		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		List<String> tables = historydataService.getExistsTable(); //获得全部用户表
		List<Field> compareEquipFieldList = historydataService.getField(equipId); //获得全部参数
		for(Equip equip:allInfo){
			if(equip.getEquipId()==equipId){
				compareTableName ="T_L_"+equip.getProvCode()+"_"+equip.getCompCode()+"_"+equip.getMineCode()+"_"+
				equip.getRommCode()+"_"+equip.getEquipCode()+"_"+equip.getEquipNum();
			}
		}
		//选择公有参数
		fieldList = new ArrayList<KeyAndValue>();
		if(tables.contains(compareTableName) && compareEquipFieldList!=null && !compareEquipFieldList.isEmpty()){
			for(Field f:fields){
				for(Field cf:compareEquipFieldList){
					if(f.getFieldName().equals(cf.getFieldName())){
						fieldList.add(new KeyAndValue(f.getFieldNo()+":"+cf.getFieldNo(), cf.getFieldName()));
						break;
					}
				}
			}
		}
		for(KeyAndValue kv:fieldList){
			System.out.println("共有参数："+kv.getKeyString()+kv.getValueString());
		}
	}
	public String getNav(){
		return nav;
	}

	public List<KeyAndValue> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<KeyAndValue> roomList) {
		this.roomList = roomList;
	}
	public String getSelectRoomId() {
		return selectRoomId;
	}
	public void setSelectRoomId(String selectRoomId) {
		this.selectRoomId = selectRoomId;
	}
	public String getSelectEquipId() {
		return selectEquipId;
	}
	public void setSelectEquipId(String selectEquipId) {
		this.selectEquipId = selectEquipId;
	}
	public List<KeyAndValue> getEquipList() {
		return equipList;
	}
	public void setEquipList(List<KeyAndValue> equipList) {
		this.equipList = equipList;
	}
	public List<KeyAndValue> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<KeyAndValue> fieldList) {
		this.fieldList = fieldList;
	}
	public String getFieldNoStr() {
		return fieldNoStr;
	}
	public void setFieldNoStr(String fieldNoStr) {
		this.fieldNoStr = fieldNoStr;
	}
	public String getCompareTime() {
		return compareTime;
	}
	public void setCompareTime(String compareTime) {
		this.compareTime = compareTime;
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
	public String getBackValueSeries() {
		return backValueSeries;
	}
	public void setBackValueSeries(String backValueSeries) {
		this.backValueSeries = backValueSeries;
	}
	public String getBackCompareSeries() {
		return backCompareSeries;
	}
	public void setBackCompareSeries(String backCompareSeries) {
		this.backCompareSeries = backCompareSeries;
	}
	public String getSelectFieldName() {
		return selectFieldName;
	}
	public void setSelectFieldName(String selectFieldName) {
		this.selectFieldName = selectFieldName;
	}
	
	
}
