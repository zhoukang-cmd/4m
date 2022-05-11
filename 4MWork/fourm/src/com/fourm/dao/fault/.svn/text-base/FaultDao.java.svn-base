package com.fourm.dao.fault;

import java.util.List;
import java.util.Map;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.Fault;

public class FaultDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public List<Fault> getFaultList(Map<String, Object> pCMap) {
		return getSqlMapClientTemplate().queryForList("fault.getFault", pCMap);
	}
	
	public Integer  getFaultCount(Map<String, Object> suggestMap){
		return (Integer) getSqlMapClientTemplate().queryForObject("fault.getFaultCount", suggestMap);
	}

	public Fault getFaultById(int detailId) {
		return (Fault) getSqlMapClientTemplate().queryForObject("fault.getFaultById", detailId);
		
	}
	/**
	 * 
	 * 查找同一类型的故障信息、便于自动添加故障
	 * 
	 */
	public Fault getFaultByTypeTime(Map map) {
		return (Fault) getSqlMapClientTemplate().queryForObject("fault.getFaultByTypeTime", map);
		
	}

	public void delDault(int id) {
		getSqlMapClientTemplate().delete("fault.delFault", id);
		
	}

	public void addFault(Fault fault) {
		getSqlMapClientTemplate().insert("fault.addFault", fault);
		
	}

	public void modifyFault(Fault fault) {
		getSqlMapClientTemplate().update("fault.modifyFault", fault);
		
	}
}
