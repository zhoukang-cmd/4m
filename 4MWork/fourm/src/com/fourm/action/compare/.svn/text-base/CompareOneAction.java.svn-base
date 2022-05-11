package com.fourm.action.compare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Field;
import com.fourm.entity.KeyAndValue;
import com.fourm.service.history.HistoryDataService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;
/**
 * 工况对比    同一设备
 * */

@SuppressWarnings("serial")
public class CompareOneAction extends BaseAction{
	private final String nav="compare";
	private String valueSeries; // 参考线名
	private String compareSeries; // 对比线名
	private String backValueSeries; // 返回参考线名
	private String backCompareSeries; // 返回对比线名
	
	private ArrayList<KeyAndValue> valueList; //所有参考值集合
	private String selectFieldNo;
	private String selectFieldName;
	@SuppressWarnings("rawtypes")
	public String goCompareone() throws ParseException{
		LogUtil.LogInfo("goCompareone()", "");
		initData();
		if(fields.get(0).getFieldNo()==null){
			return "goCompareone";
		}
		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		//初始化参考时间和对比时间
		if(valueSeries==null || "".equals(valueSeries) || compareSeries==null || "".equals(compareSeries)){
			valueSeries=new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getIntervalDate(new Date(), -2));
			compareSeries=new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getIntervalDate(new Date(), -1));
		}
		backValueSeries = valueSeries;
		backCompareSeries = compareSeries;
		valueSeries += " 00:00:00";
		compareSeries += " 00:00:00";
		Date startValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valueSeries);
		Date startCompare = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(compareSeries);
		
		//初始化对比参数
		if((selectFieldNo==null || "".equals(selectFieldNo)) && fields!=null && !fields.isEmpty()){
			selectFieldNo = fields.get(0).getFieldNo();
		}
		//确定对比参数名称
		if(fields!=null && !fields.isEmpty()){
		for(Field f:fields){
			if(selectFieldNo.equals(f.getFieldNo())){
				selectFieldName = f.getFieldName();
			}
		}	
		}
		valueList = new ArrayList<KeyAndValue>();
		
		HashMap<String, Object> valueTmp = new HashMap<String, Object>();
		HashMap<String, Object> compareTmp = new HashMap<String, Object>();
		Map valueMap;
		Map compareMap;
		valueTmp.put("TABLENAME", tableName);
		compareTmp.put("TABLENAME", tableName);
		Date endValue = DateUtil.getNextDate(startValue , 60*60-1);
		Date endCompare = DateUtil.getNextDate(startCompare , 60*60-1);
		for(int i=1;i<=24;i++){
			valueTmp.put("start", startValue);
			valueTmp.put("end", endValue);
			valueTmp.put("COLUMN", "VALUE"+selectFieldNo);
			valueMap = (Map)historydataService.getValue(valueTmp);

			compareTmp.put("start", startCompare);
			compareTmp.put("end", endCompare);
			compareTmp.put("COLUMN", "VALUE"+selectFieldNo);
			compareMap = (Map)historydataService.getValue(compareTmp);
			
			KeyAndValue kv = new KeyAndValue();
			kv.setKeyString( DateUtil.formatDate(startValue, "HH"));
			if(valueMap==null || valueMap.get("VALUE")==null ){//valueMap为空
				if(compareMap==null || compareMap.get("VALUE")==null ){//compareMap为空
					//kv.setValueString(" "+","+" ");
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
			
			startValue = DateUtil.getNextDate(endValue, 1);
			endValue = DateUtil.getNextDate(startValue, 60*60-1);
			
			startCompare = DateUtil.getNextDate(endCompare, 1);
			endCompare = DateUtil.getNextDate(startCompare, 60*60-1);
		}
		LogUtil.LogInfo("goCompareone()","工况对比同一设备  参数："+selectFieldName +"参考时间："+valueSeries+"对比时间："+backCompareSeries);
		return "goCompareone";
	}
	public String getValueSeries() {
		return valueSeries;
	}
	public void setValueSeries(String valueSeries) {
		this.valueSeries = valueSeries;
	}
	public String getCompareSeries() {
		return compareSeries;
	}
	public void setCompareSeries(String compareSeries) {
		this.compareSeries = compareSeries;
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
	
	public ArrayList<KeyAndValue> getValueList() {
		return valueList;
	}
	public void setValueList(ArrayList<KeyAndValue> valueList) {
		this.valueList = valueList;
	}
	public String getSelectFieldNo() {
		return selectFieldNo;
	}
	public void setSelectFieldNo(String selectFieldNo) {
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
