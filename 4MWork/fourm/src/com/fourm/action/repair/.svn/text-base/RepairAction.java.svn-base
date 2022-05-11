package com.fourm.action.repair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.Repair;
import com.fourm.service.repair.RepairService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;
/**
 * 历史维护
 * */

@SuppressWarnings("serial")
public class RepairAction extends BaseAction{
	
	private final String nav = "equip";
	private List<Repair> repairList;//页面发布结果列表
	private Repair repair;
	private int detailId;
	private String delrepairStr;

	public String goRepair(){
		
		Equip currentSession =   (Equip) getValueByKey("currentSession");
		RepairService repairService = (RepairService) getBeanById("repairService");
		
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
			this.pageCtrl = repairService.pageFormat(this.getPageCtrl().getCurrPage(), pageSize , faultMap);
		else
			this.pageCtrl = repairService.pageFormat(1, pageSize ,faultMap);			
		
		faultMap.put("pageCtrl", pageCtrl);
		repairList = repairService.getRepairList(faultMap);
		
		LogUtil.LogInfo("goRepair()","提交时间：startTime:"+startTime+"endTime:"+endTime);
		return "goRepair";
	}

	public String goRepairDetail(){
		System.out.println("goRepairDetail()");
		RepairService repairService = (RepairService) getBeanById("repairService");
		repair= repairService.getRepairById(detailId);
		LogUtil.LogInfo("goRepairDetail()","查看详情repairId:"+detailId);
		return "goRepairDetail";
	}
	//删除所选
	public String delRepair(){
		RepairService repairService = (RepairService) getBeanById("repairService");
		if (StringUtils.isNotBlank(delrepairStr)) {
			String[] arr = delrepairStr.split("%");
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] != "") {
						int id = Integer.parseInt(arr[i]);
						repairService.delRepair(id);
					}
				}
			}
		}
		goRepair();
		LogUtil.LogInfo("delRepair()","删除历史维护信息参数repairId:"+delrepairStr);
		return "goRepair";
		
	}
	//添加维护信息
	public String addRepair(){
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession = (Equip) request.getSession().getAttribute("currentSession");
		RepairService repairService = (RepairService) getBeanById("repairService");
		
		repair.setEquipId(currentSession.getEquipId());
		repairService.addRepair(repair);
		
		LogUtil.LogInfo("addRepair()","添加历史维护信息："+repair.getRepairTime()+repair.getRepairObject()+repair.getRepairContent()+repair.getRepairQues()+repair.getRemainQues()+repair.getEquipId());
		return "goAddRepair";
	}
	public String goAddRepair(){
		return "goAddRepair";
	}
	
	//跳转到修改页面
	public String goModifyRepair(){
		RepairService repairService = (RepairService) getBeanById("repairService");
		repair= repairService.getRepairById(detailId);
		
		LogUtil.LogInfo("goModifyRepair():","跳转到修改页面repairId:"+detailId);
		return "goModifyRepair";
	}
	//修改维护信息
	public String modifyRepair(){
		RepairService repairService = (RepairService) getBeanById("repairService");
		System.out.println(repair.getRepairTime()+repair.getRepairContent()+repair.getRepairObject()+repair.getRepairQues()+repair.getRemainQues());
		repairService.modifyRepair(repair);
		goRepair();
		
		LogUtil.LogInfo("modifyRepair()","修改信息："+repair.getRepairTime()+repair.getRepairContent()+repair.getRepairObject()+repair.getRepairQues()+repair.getRemainQues());
		return "goRepair";
		
	}
	public List<Repair> getRepairList() {
		return repairList;
	}

	public void setRepairList(List<Repair> repairList) {
		this.repairList = repairList;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public String getDelrepairStr() {
		return delrepairStr;
	}

	public void setDelrepairStr(String delrepairStr) {
		this.delrepairStr = delrepairStr;
	}

	public String getNav() {
		return nav;
	}
	
}
