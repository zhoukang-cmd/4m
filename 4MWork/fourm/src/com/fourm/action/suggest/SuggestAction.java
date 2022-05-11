package com.fourm.action.suggest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.Equip;
import com.fourm.entity.Suggest;
import com.fourm.service.suggest.SuggestService;
import com.fourm.util.DateUtil;
import com.fourm.util.LogUtil;
/**
 * 发布结果
 * */

@SuppressWarnings("serial")
public class SuggestAction extends BaseAction {
	private final String nav = "suggest";
	private List<Suggest> suggList;//页面发布结果集合
	private Suggest sugg;//发布结果对象
	private int detailId;
	//发布结果页面
	public String goSuggest(){
		Equip currentSession =   (Equip) getValueByKey("currentSession");
		 SuggestService suggestService = (SuggestService) getBeanById("suggestService");
		 
		HashMap<String, Object> suggestMap = new HashMap<String, Object>();
		suggestMap.put("pageCtrl", pageCtrl);
		
		if(startTime == null && endTime==null){
			endTime = new Date();
			startTime = DateUtil.getIntervalDate(endTime, -30);
		}
		if(startTime != null && endTime==null){
			endTime = DateUtil.getIntervalDate(startTime, 1);
		}
		
		suggestMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime));
		suggestMap.put("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime));
		suggestMap.put("equipId" , currentSession.getEquipId());
		
		
		if(pageCtrl != null)
		{
			this.pageCtrl = suggestService.pageFormat(this.getPageCtrl().getCurrPage(), pageSize , suggestMap);
		}
		else
		{
			this.pageCtrl = suggestService.pageFormat(1, pageSize ,suggestMap);			
		}
		suggestMap.put("pageCtrl", pageCtrl);
		suggList = suggestService.getSuggestList(suggestMap);
		
		LogUtil.LogInfo("goSuggest()","提交时间：startTime:"+startTime+"endTime:"+endTime);
		return "goSuggest";
	}
	
	//产看详情
	public String goSuggestDetail(){
		SuggestService suggestService = (SuggestService) getBeanById("suggestService");
		sugg= suggestService.getSuggestById(detailId);
		
		LogUtil.LogInfo("goSuggestDetail()","查看详情:"+detailId);
		return "goSuggestDetail";
	}
	

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public List<Suggest> getSuggList() {
		return suggList;
	}

	public void setSuggList(List<Suggest> suggList) {
		this.suggList = suggList;
	}

	public Suggest getSugg() {
		return sugg;
	}

	public void setSugg(Suggest sugg) {
		this.sugg = sugg;
	}

	public String getNav() {
		return nav;
	}
	
}
