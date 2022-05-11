package com.fourm.service.suggest;

import java.util.HashMap;
import java.util.List;

import com.fourm.dao.suggest.SuggestDao;
import com.fourm.entity.PageControl;
import com.fourm.entity.Suggest;
import com.fourm.service.base.BaseService;
/**
 * 发布结果
 * 
 * */
public class SuggestService extends BaseService {

	private SuggestDao suggestDao;
	
	public List<Suggest> getSuggestList(HashMap<String, Object>  suggestMap) {
		return suggestDao.getSuggestList(suggestMap);
	}
	public PageControl pageFormat(int page, int pageSize ,HashMap<String, Object> suggestMap){
       int totalLine = suggestDao.getSuggestCount(suggestMap);
       return new PageControl().pageFormat(page, totalLine, pageSize);
	}
	public Suggest getSuggestById(int detailId) {
		return suggestDao.getSuggestById(detailId);
		
	}
	
	
	public SuggestDao getSuggestDao() {
		return suggestDao;
	}

	public void setSuggestDao(SuggestDao suggestDao) {
		this.suggestDao = suggestDao;
	}
	
	
}
