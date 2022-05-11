package com.fourm.dao.repair;

import java.util.List;
import java.util.Map;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.Repair;

public class RepairDao  extends BaseDao{
	@SuppressWarnings("unchecked")
	public List<Repair> getRepairList(Map<String, Object> repairMap) {
		return getSqlMapClientTemplate().queryForList("repair.getRepair", repairMap);
	}
	
	public Integer  getRepairCount(Map<String, Object> repairMap){
		return (Integer) getSqlMapClientTemplate().queryForObject("repair.getRepairCount", repairMap);
	}

	public Repair getRepairById(int detailId) {
		return (Repair) getSqlMapClientTemplate().queryForObject("repair.getRepairById", detailId);
		
	}

	public void delRepair(int id) {
		getSqlMapClientTemplate().delete("repair.delRepair", id);
	}

	public void addRepair(Repair repair) {
		getSqlMapClientTemplate().insert("repair.addRepair", repair);
	}

	public void modifyRepair(Repair repair) {
		getSqlMapClientTemplate().update("repair.modifyRepair", repair);
		
	}
}
