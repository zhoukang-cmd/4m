package com.fourm.dao.display;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.Field;

public class DisplayDao extends BaseDao{

	//得到实时数据显示参数信息
	@SuppressWarnings("unchecked")
	public List<Field> getDisplayField(int  equipId) {
		return   getSqlMapClientTemplate().queryForList("field.getDisplayField",equipId);
	}
	//实时数据显示最新数据
	@SuppressWarnings("rawtypes")
	public String getDisplayValue(HashMap tmp) {
		return  (String) getSqlMapClientTemplate().queryForObject("field.getDisplayValue",tmp);
	}
	//一段时间内的第一条数据
	@SuppressWarnings("rawtypes")
	public HashMap getValueByProcess(HashMap tmp) {
		return   (HashMap) getSqlMapClientTemplate().queryForObject("field.getValue",tmp);
	}
	//得到参数的编号
	public String getFieldNum(HashMap tmp){
		return  (String) getSqlMapClientTemplate().queryForObject("field.getFieldNum",tmp);
	}
	//实时数据显示最新时间
	@SuppressWarnings("rawtypes")
	public String getDisplayTime(HashMap tmp) {
		return  (String) getSqlMapClientTemplate().queryForObject("field.getDisplayTime",tmp);
	}
	@SuppressWarnings("unchecked")
	public List<Field> getGroupField(int equipId) {
		return getSqlMapClientTemplate().queryForList("field.getGroupField",equipId);
	}
}
