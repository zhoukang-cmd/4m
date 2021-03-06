package com.fourm.action.equip;

import java.text.DecimalFormat;
import java.text.ParseException;
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
import com.fourm.entity.CEquip;
import com.fourm.entity.CequipType;
import com.fourm.entity.Equip;
import com.fourm.entity.EquipChange;
import com.fourm.entity.Field;
import com.fourm.entity.ShowDataView;
import com.fourm.service.display.DisplayService;
import com.fourm.service.equip.EquipService;
import com.fourm.util.LogUtil;
/**
 * 历史故障
 * */

@SuppressWarnings("serial")
public class EquipAction extends BaseAction{
	private Equip equip;
	private List<EquipChange> equipChange;
	private List<CEquip> cequip;
    private String delfaultStr;
    private String delceqStr;
    private CEquip ceq;
    private EquipChange echange;
    private int detailId;
    
	/**
	 * 前台       设备信息页面的action。
	 */
	
	public String goEquip(){
		Equip currentSession = (Equip)getValueByKey("currentSession");
		
		EquipService equipService=(EquipService)getBeanById("equipService");
		int equipId=currentSession.getEquipId();
		String rooomName=currentSession.getRoomName();
		String mineName=currentSession.getMineName();
		equip=equipService.getEquipById(equipId);
		equip.setRoomName(rooomName);
		equip.setMineName(mineName);
		
		
		/**
		 * 子设备信息
		 */
		cequip = equipService.getCEquipById(equipId);
		equipChange=equipService.getEquipChangeById(equipId);
        
		
		return "goEquip";
	}
	/**
	 * 
	 * 条件查询设备
	 */
	public String selQueryEquip(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession = (Equip)getValueByKey("currentSession");
		String  eNum=request.getParameter("equipNum");
		String equipNum;
		String  equipName=request.getParameter("equipName");
		String e[]=eNum.split("_");
		if(e.length==1){
			equipNum=e[0];
		}
		else{
			equipNum=e[1];
		}
		int roomId=currentSession.getRoomId();
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("roomId",roomId);
		map.put("equipName",equipName);
		map.put("equipNum",equipNum);
		System.out.println(equipNum+"@@"+equipName);
		EquipService equipService=(EquipService)getBeanById("equipService");
		List<Equip> ls=equipService.selQueryEquip(map);
		
		Map<String,Object> jsonMap=new HashMap<String, Object>();
		jsonMap.put("allEquip",ls);
		JSONObject jsonObject=JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		return "json";
	}
		/**
		 * 
		 * 条件查询子设备
		 */
	  public String	selQueryCEquip(){
		    String pattern=goEquip();
		    HttpServletRequest request = ServletActionContext.getRequest();
			Equip currentSession = (Equip)getValueByKey("currentSession");
			int equipId=currentSession.getEquipId();
			String  csTime=request.getParameter("csTime");
			String  ceTime=request.getParameter("ceTime");
			String  cEquipName=request.getParameter("cEquipName");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("equipId", equipId);
			map.put("cEquipName", cEquipName);
			map.put("csTime", csTime);
			map.put("ceTime", ceTime);
			EquipService equipService=(EquipService)getBeanById("equipService");
		    cequip=equipService.selQueryCEquip(map);
			return pattern;
		}
		
	  /**
		 * 
		 * 条件查询变动信息
		 */
	public String	selQueryChange(){
		    String pattern=goEquip();
		    HttpServletRequest request = ServletActionContext.getRequest();
			Equip currentSession = (Equip)getValueByKey("currentSession");
			int equipId=currentSession.getEquipId();
			String  csTime=request.getParameter("sTime");
			String  ceTime=request.getParameter("eTime");
			String  state=request.getParameter("state");
			
			if("所有".equals(state)){
				state=null;
			}
			request.setAttribute("state", state);
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("equipId", equipId);
			map.put("state", state);
			map.put("csTime", csTime);
			map.put("ceTime", ceTime);
			EquipService equipService=(EquipService)getBeanById("equipService");
			equipChange=equipService.selQueryChange(map);
			return pattern;
		}
	
	/**
	 * 
	 *子设备类型
	 *  
	 */
	public String getCequipType(){
		HttpServletRequest request = ServletActionContext.getRequest();
		EquipService equipService=(EquipService)getBeanById("equipService");
		Equip currentSession = (Equip)getValueByKey("currentSession");
	     
	    List<CequipType> typeLs=equipService.getCequipType(currentSession.getEquipCode()); 
	    
	    Map<String,Object> map=new HashMap<String,Object>();
	    map.put("typeLs", typeLs);
	    JSONObject jsonObject=JSONObject.fromObject(map);
		request.setAttribute("json", jsonObject);
		return "json";
	}

	/**
	 * 
	 *子设备信息
	 *  跳转到修改页面
	 */
	public String goModifyCEquip(){
		HttpServletRequest request = ServletActionContext.getRequest();
		EquipService equipService=(EquipService)getBeanById("equipService");
		System.out.println("detaliId = "+detailId);
	    ceq = equipService.getCEquipId(detailId);
	    JSONObject jsonObject=JSONObject.fromObject(ceq);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	/**
	 * 
	 * @return 修改子设备信息
	 */
	public String modifyCEquip(){
		EquipService equipService=(EquipService)getBeanById("equipService");
		System.out.println(ceq.getCequipId());
		equipService.modifyCEquip(ceq);
		return goEquip();
	}
  
	/**
	 * 添加子设备信息
	 */
	public String addCEquip(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession = (Equip) request.getSession().getAttribute("currentSession");
		EquipService equipService=(EquipService)getBeanById("equipService");
		
		if(ceq!=null){
			ceq.setEquipId(currentSession.getEquipId());
			equipService.addCEquip(ceq);
		}
		return goEquip();
	}
	/**
	 * 
	 * @return 添加变动信息
	 */
	public String addEquipChange(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession = (Equip) request.getSession().getAttribute("currentSession");
		EquipService equipService=(EquipService)getBeanById("equipService");
		
		echange.setEquipId(currentSession.getEquipId());
		equipService.addEquipChange(echange);
		return goEquip();
	}
	/**
	 * 
	 *变动信息
	 *  跳转到修改页面
	 */
	public String goModifyChange(){
		HttpServletRequest request = ServletActionContext.getRequest();
		EquipService equipService=(EquipService)getBeanById("equipService");
		System.out.println("detaliId = "+detailId);
		echange = equipService.getChangeByID(detailId);
	    JSONObject jsonObject=JSONObject.fromObject(echange);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	
	/**
	 * 
	 * @return 修改变动信息
	 */
	public String modifyChange(){
		EquipService equipService=(EquipService)getBeanById("equipService");
        System.out.println(echange.getChangeId());
		equipService.modifyChange(echange);
		return goEquip();
	}
	
	/**
	 * 
	 * @return 删除子设备信息
	 */
	public String delCEquipById(){
		EquipService equipService=(EquipService)getBeanById("equipService");
		String[] idAll=delceqStr.split("%");
		for(String sid:idAll){
			int id=Integer.parseInt(sid);
			equipService.delCEquipById(id);
		}
		return goEquip();
	}
	
	/**
	 * 
	 * 删除选中的变动信息
	 */
	public String delSelectChange(){
		EquipService equipService=(EquipService)getBeanById("equipService");
		String[] idAll=delfaultStr.split("%");
		for(String sid:idAll){
			int id=Integer.parseInt(sid);
			equipService.delSelectChange(id);
		}
		return goEquip();
	}
	
	public String goEquipAll(){
		HttpServletRequest request = ServletActionContext.getRequest();
		EquipService equipService=(EquipService)getBeanById("equipService");
		Equip currentSession = (Equip)getValueByKey("currentSession");
		int roomId=currentSession.getRoomId();
		String roomName=currentSession.getRoomName();
		List<Equip> allEquip=equipService.getEquipByRoom(roomId);
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("room",roomName);
		jsonMap.put("allEquip",allEquip);
		JSONObject jsonObject=JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		return "json";
	}
	
	
	
	
	/**
	 * 
	 * 子设备实时参数展示
	 */
	public String queryData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		EquipService equipService = (EquipService) getBeanById("equipService");
		String cequipCode=request.getParameter("cequipCode");
        Map<String, Object> fieldMap=new HashMap<String, Object>();
        
        fieldMap.put("equipId",currentSession.getEquipId());
        fieldMap.put("cequipCode",cequipCode);
		List<Field> extList = equipService.getDisplayField(fieldMap);//图片上的参数
	
		List<ShowDataView> dataList = new ArrayList<ShowDataView>();
		String limit;
		String table;
		String value;
		String dataInfo = null;
		
		HashMap tmp = new HashMap();
		String tableName = "T_L_"+currentSession.getProvCode()+"_"+currentSession.getCompCode()+"_"+currentSession.getMineCode()+"_"+currentSession.getRommCode()+"_"+currentSession.getEquipCode()+"_"+currentSession.getEquipNum();
		tmp.put("TABLE", tableName);
		System.out.println(tableName);
		if(extList!=null && extList.size()>0){
			for (Field ext : extList)
			{
				ShowDataView sdv = new ShowDataView();
				
				sdv.setFieldType(ext.getFieldType());
				sdv.setOrderNo(ext.getFieldNo());
				sdv.setFieldName(ext.getFieldName());
				tmp.put("COLUMN", "VALUE"+ext.getFieldNo());
				value = equipService.getDisplayValue(tmp);
				//四舍五入，保留两位小数
				
				DecimalFormat decimalFormat = new DecimalFormat( "##0.00 ");
				String dfValue = decimalFormat.format(Float.parseFloat(value));
				System.out.println("VALUE"+ext.getFieldNo()+"====="+value+"==="+dfValue);
				sdv.setExtString(dfValue);
				if("风门状态".equals(ext.getFieldName())){
					if(Float.parseFloat(value)<=0){
						sdv.setExtString("关闭");
					}else if(Float.parseFloat(value)>=0){
						sdv.setExtString("开启");
					}
				}
				if("风量".equals(ext.getFieldName())){
					if(Float.parseFloat(value)<=0){
						sdv.setExtString("0.00");
					}
				}
				limit = ext.getFieldLimitHigh();
				sdv.setAlarmFlag(false);//默认数据在范围之内
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
			
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("flag", "1");
		jsonMap.put("dataList", dataList);
		if (dataInfo != null) {
			jsonMap.put("info", dataInfo);
		} else {
			jsonMap.put("info", "");
		}
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getExtList()","实时数据参数值");
		return "json";
		
	
	}
	

	public Equip getEquip() {
		return equip;
	}

	public void setEquip(Equip equip) {
		this.equip = equip;
	}

	public List<EquipChange> getEquipChange() {
		return equipChange;
	}

	public void setEquipChange(List<EquipChange> equipChange) {
		this.equipChange = equipChange;
	}

	public List<CEquip> getCequip() {
		return cequip;
	}

	public void setCequip(List<CEquip> cequip) {
		this.cequip = cequip;
	}

	public String getDelfaultStr() {
		return delfaultStr;
	}

	public void setDelfaultStr(String delfaultStr) {
		this.delfaultStr = delfaultStr;
	}

	public CEquip getCeq() {
		return ceq;
	}

	public void setCeq(CEquip ceq) {
		this.ceq = ceq;
	}
	
	public EquipChange getEchange() {
		return echange;
	}
	public void setEchange(EquipChange echange) {
		this.echange = echange;
	}
	
	public String getDelceqStr() {
		return delceqStr;
	}
	public void setDelceqStr(String delceqStr) {
		this.delceqStr = delceqStr;
	}
	public int getDetailId() {
		return detailId;
	}
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	
}




