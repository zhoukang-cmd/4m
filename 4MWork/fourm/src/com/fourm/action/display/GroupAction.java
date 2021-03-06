package com.fourm.action.display;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.Field;
import com.fourm.entity.ShowDataView;
import com.fourm.service.display.DisplayService;
import com.fourm.service.history.HistoryDataService;
import com.fourm.util.LogUtil;
/**
 * 
 * */

@SuppressWarnings("serial")
public class GroupAction extends BaseAction {
	
	
	/**
	 * 
	 */
	private final String nav = "display";
	
	public String goDisplay(){

		return "goDisplay";
	}

	
	/**
	 * 
	 * @return
	 */
	public String getGroupList()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) getValueByKey("currentSession");
		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		DisplayService displayService = (DisplayService) getBeanById("displayService");
		
		List<String> tables = historydataService.getExistsTable(); 
		String tableName = "T_L_"+currentSession.getProvCode()+"_"+currentSession.getCompCode()+"_"+currentSession.getMineCode()+"_"+currentSession.getRommCode()+"_"+currentSession.getEquipCode()+"_"+currentSession.getEquipNum();
		String flagTableName="1";
		//System.out.println(tableName);
		if(!tables.contains(tableName)){
			flagTableName="0";
		}
		//System.out.println(tableName);
		List<Field> groupList = displayService.getGroupField(currentSession.getEquipId());
		for(Field f:groupList){
			System.out.println(f.getGroupId()+f.getGroupName()+f.getFieldId()+f.getFieldName());
		}
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", "1");
		jsonMap.put("groupList", groupList);
		jsonMap.put("flagTableName", flagTableName);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getGroupList()","??????????????????");
		return "json";
	}

	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public String groupData()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		DisplayService displayService = (DisplayService) getBeanById("displayService");
		HistoryDataService historydataService = (HistoryDataService) getBeanById("historydataService");
		
		List<Field> groupList = displayService.getGroupField(currentSession.getEquipId());//???????????????????????????
		List<ShowDataView> dataList = new ArrayList<ShowDataView>();
		String limit;
		String table;
		String value;
		
		HashMap tmp = new HashMap();
		String tableName = "T_L_"+currentSession.getProvCode()+"_"+currentSession.getCompCode()+"_"+currentSession.getMineCode()+"_"+currentSession.getRommCode()+"_"+currentSession.getEquipCode()+"_"+currentSession.getEquipNum();
		tmp.put("TABLE", tableName);
		
		List<String> tables = historydataService.getExistsTable(); //?????????????????????????????????
		if(tables.contains(tableName)){
			if(groupList!=null && groupList.size()>0){
				for (Field ext : groupList)
				{
					ShowDataView sdv = new ShowDataView();
					
					sdv.setFieldType(ext.getFieldType());
					sdv.setOrderNo(ext.getFieldNo());
					tmp.put("COLUMN", "VALUE"+ext.getFieldNo());
					System.out.println(null==displayService.getDisplayValue(tmp));
					value = displayService.getDisplayValue(tmp);
					//???????????????????????????????????????????????????
					
					DecimalFormat decimalFormat = new DecimalFormat( "##0.00 ");
					String dfValue = decimalFormat.format(Float.parseFloat(value));
					sdv.setExtString(dfValue);
					if("????????????".equals(ext.getFieldName())){
						if(Float.parseFloat(value)<=0){
							sdv.setExtString("??????");
						}else if(Float.parseFloat(value)>=0){
							sdv.setExtString("??????");
						}
					}
					if("??????".equals(ext.getFieldName())){
						if(Float.parseFloat(value)<=0){
							sdv.setExtString("0.00");
						}
					}
					limit = ext.getFieldLimitHigh();
					sdv.setAlarmFlag(false);//??????????????????????????????????????????
					if (StringUtils.isNotBlank( ext.getFieldLimitHigh()) && StringUtils.isNotBlank(ext.getFieldLimitLow()))
					{
						
						if (StringUtils.isNotBlank(ext.getFieldLimitLow()))
						{
							if (Float.parseFloat(ext.getFieldLimitLow()) > Float.parseFloat(value))
							{
								sdv.setAlarmFlag(true);
						
							}
						}
						if (StringUtils.isNotBlank(ext.getFieldLimitHigh()))
						{
							if (Float.parseFloat(ext.getFieldLimitHigh()) < Float.parseFloat(value))
							{
								sdv.setAlarmFlag(true);
						
							}
						}
					}
					dataList.add(sdv);
					
				}
			}
		}
		/*for(ShowDataView sv:dataList){
			System.out.println(sv.getOrderNo()+sv.getExtString());
		}*/
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", "1");
		jsonMap.put("extList", dataList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getExtList()","??????value???");
		return "json";
	}
	
	public String goFullScreen() {
		return "goFullScreen";
	}
	public String getNav() {
		return nav;
	}

	
	
}
