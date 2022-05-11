package com.fourm.action.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.KeyAndValue;
import com.fourm.util.LogUtil;
/**
 * 级联列表
 * */
@SuppressWarnings("serial")
public class CommonAction extends BaseAction {
	
	//初始化下拉列表
	@SuppressWarnings("unchecked")
	public String getProvList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) getValueByKey("currentSession");
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<KeyAndValue> provList = new ArrayList<KeyAndValue>();
		List<KeyAndValue> compList = new ArrayList<KeyAndValue>();
		List<KeyAndValue> mineList = new ArrayList<KeyAndValue>();
		List<KeyAndValue> roomList = new ArrayList<KeyAndValue>();
		List<KeyAndValue> equipList = new ArrayList<KeyAndValue>();
		
		if(provList==null || provList.isEmpty()){
			String key = String.valueOf(currentSession.getProvId());
			String value = currentSession.getProvName();
			KeyAndValue kv = new KeyAndValue(key, value);
			if(!provList.contains(kv)){
			provList.add(kv);
			}
		}	
		
		//集团信息
		for(Equip info : allInfo){	
			if(info.getProvId()==currentSession.getProvId()){
				String key = String.valueOf(info.getCompId());
				String value = info.getCompName();
				KeyAndValue kv = new KeyAndValue(key, value);
				
				if(compList==null || compList.isEmpty()){
					compList.add(kv);
				}else{
					Boolean flag =false; 
					for(KeyAndValue con:compList){
						if(info.getCompId()==Integer.parseInt(con.getKeyString())){
							flag=true;//表示集合中已存在
							break;
						}
					}
					if(!flag){
						compList.add(kv);
					}
				}
			}
		}
		//煤矿信息
		for(Equip info : allInfo){	
			if(info.getCompId()==currentSession.getCompId()){
				String key = String.valueOf(info.getMineId());
				String value = info.getMineName();
				KeyAndValue kv = new KeyAndValue(key, value);
				
				if(mineList==null ||mineList.isEmpty()){
					mineList.add(kv);
				}else{
					boolean flag = false;
					for(KeyAndValue con:mineList){
						if(info.getMineId()==Integer.parseInt(con.getKeyString())){
							flag =true;
							break;
						}
					}
					if(!flag){
						mineList.add(kv);
					}
				}
			}
		}
		//机房信息
		for(Equip info : allInfo){	
			if(info.getMineId()==currentSession.getMineId()){
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
		
		//设备信息
		for(Equip info : allInfo){	
			if(info.getRoomId()==currentSession.getRoomId()){
				String key = String.valueOf(info.getEquipId());
				String value = info.getEquipName();
				KeyAndValue kv = new KeyAndValue(key, value);
				if(!equipList.contains(kv)){
					equipList.add(kv);
				}
			}
		}
		jsonMap.put("flag", "1");
		jsonMap.put("provList", provList);
		jsonMap.put("compList", compList);
		jsonMap.put("mineList", mineList);
		jsonMap.put("roomList", roomList);
		jsonMap.put("equipList", equipList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getProvList()","初始化下拉列表");

		return "json";
	}
	//得到集团列表
	@SuppressWarnings("unchecked")
	public String getCompList() {
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String selectProvId = request.getParameter("selectProvId");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<KeyAndValue> compList = new ArrayList<KeyAndValue>();
		
		for(Equip info : allInfo){	
			if("".equals(selectProvId) ||(!"".equals(selectProvId) && selectProvId.equals(String.valueOf(info.getProvId())))){
				String key = String.valueOf(info.getCompId());
				String value = info.getCompName();
				KeyAndValue kv = new KeyAndValue(key, value);
				if(compList==null || compList.isEmpty() ){
					compList.add(kv);
				}else {
					Boolean flag =false; 
					for(KeyAndValue con:compList){
						if(info.getCompId()==Integer.parseInt(con.getKeyString())){
							flag=true;//表示集合中已存在
							break;
						}
					}
					if(!flag){
						compList.add(kv);
					}
				}
			}
		}	
		jsonMap.put("flag", "1");
		jsonMap.put("compList", compList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getProvList()","集团列表");
		return "json";
	}
	//得到煤矿列表
	@SuppressWarnings("unchecked")
	public String getMineList() {
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");

		HttpServletRequest request = ServletActionContext.getRequest();
		String selectCompId = request.getParameter("selectCompId");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<KeyAndValue> mineList = new ArrayList<KeyAndValue>();
		
		for(Equip info : allInfo){	
			if("".equals(selectCompId) ||(!"".equals(selectCompId) && selectCompId.equals(String.valueOf(info.getCompId())))){
				String key = String.valueOf(info.getMineId());
				String value = info.getMineName();
				KeyAndValue kv = new KeyAndValue(key, value);
				if(mineList==null || mineList.isEmpty() ){
					mineList.add(kv);
				}else {
					Boolean flag =false; 
					for(KeyAndValue con:mineList){
						if(info.getMineId()==Integer.parseInt(con.getKeyString())){
							flag=true;//表示集合中已存在
							break;
						}
					}
					if(!flag){
						mineList.add(kv);
					}
				}
			}
		}	
		jsonMap.put("flag", "1");
		jsonMap.put("mineList", mineList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getProvList()","煤矿列表");
		return "json";
	}
	//得到机房列表
	@SuppressWarnings("unchecked")
	public String getRoomList() {
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String selectMineId = request.getParameter("selectMineId");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<KeyAndValue> roomList = new ArrayList<KeyAndValue>();
		
		for(Equip info : allInfo){	
			if("".equals(selectMineId) ||(!"".equals(selectMineId) && selectMineId.equals(String.valueOf(info.getMineId())))){
				String key = String.valueOf(info.getRoomId());
				String value = info.getRoomName();
				KeyAndValue kv = new KeyAndValue(key, value);
				if(roomList==null || roomList.isEmpty() ){
					roomList.add(kv);
				}else  {
					Boolean flag =false; 
					for(KeyAndValue con:roomList){
						if(info.getRoomId()==Integer.parseInt(con.getKeyString())){
							flag=true;//表示集合中已存在
							break;
						}
					}
					if(!flag){
						roomList.add(kv);
					}
				}
			}
		}	
		jsonMap.put("flag", "1");
		jsonMap.put("roomList", roomList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getProvList()","机房列表");
		return "json";
	}
	//得到设备列表
	@SuppressWarnings("unchecked")
	public String getEquipList() {
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");

		HttpServletRequest request = ServletActionContext.getRequest();
		String selectRoomId = request.getParameter("selectRoomId");
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<KeyAndValue> equipList = new ArrayList<KeyAndValue>();
		
		for(Equip info : allInfo){	
			if("".equals(selectRoomId) ||(!"".equals(selectRoomId) && selectRoomId.equals(String.valueOf(info.getRoomId())))){
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
		jsonMap.put("flag", "1");
		jsonMap.put("equipList", equipList);
		JSONObject jsonObject = JSONObject.fromObject(jsonMap);
		request.setAttribute("json", jsonObject);
		LogUtil.LogInfo("getProvList()","设备列表");
		return "json";
	}
	//设置当前session
	@SuppressWarnings("unchecked")
	public String setCurrentSession() {
		List<Equip> allInfo =  (List<Equip>) getValueByKey("allInfo");

		HttpServletRequest request = ServletActionContext.getRequest();
		String selectEquipId = request.getParameter("selectEquipId");
		System.out.println("selectEquipId:"+selectEquipId);
		for(Equip info : allInfo){	
			if(selectEquipId.equals(String.valueOf(info.getEquipId()))){
				setKeyAndValue("currentSession", info);
				LogUtil.LogInfo("setCurrentSession()", "重置当前session信息："+info.getEquipId()+info.getEquipName());
				System.out.println("重置当前session信息："+info.getEquipId()+info.getEquipName());
				break;
			}
		}	
		return null;
	}
}
