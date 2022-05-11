package com.fourm.dao.history;

import java.util.HashMap;
import java.util.List;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.Field;

public class HistoryDataDao extends BaseDao{
	@SuppressWarnings("unchecked")
	public List<String> getExistsTable() {
		return getSqlMapClientTemplate().queryForList("field.getExistsTable");
	}

	@SuppressWarnings("unchecked")
	public List<Field> getField(int equipId) {
		return getSqlMapClientTemplate().queryForList("field.getField",equipId);
		
	}

	@SuppressWarnings("rawtypes")
	public HashMap getValue(HashMap<String, Object> tmp) {
		return (HashMap) getSqlMapClientTemplate().queryForObject("field.getValue",tmp);
	}
}
