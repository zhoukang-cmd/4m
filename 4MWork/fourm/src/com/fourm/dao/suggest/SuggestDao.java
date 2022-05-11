package com.fourm.dao.suggest;

import java.util.List;
import java.util.Map;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.Suggest;

public class SuggestDao extends BaseDao{
	@SuppressWarnings("unchecked")
	public List<Suggest> getSuggestList(Map<String, Object> pCMap) {
		return getSqlMapClientTemplate().queryForList("suggest.getSuggest", pCMap);
	}
	
	public Integer  getSuggestCount(Map<String, Object> suggestMap){
		return (Integer) getSqlMapClientTemplate().queryForObject("suggest.getSuggCount", suggestMap);
	}

	public Suggest getSuggestById(int detailId) {
		return (Suggest) getSqlMapClientTemplate().queryForObject("suggest.getSuggestById", detailId);
		
	}
}
