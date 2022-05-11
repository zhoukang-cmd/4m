package com.fourm.service.repair;

import java.util.HashMap;
import java.util.List;

import com.fourm.dao.repair.RepairDao;
import com.fourm.entity.PageControl;
import com.fourm.entity.Repair;
import com.fourm.service.base.BaseService;
/**
 * 历史维护
 * 
 * */
public class RepairService extends BaseService {
private RepairDao repairDao;
	
	public List<Repair> getRepairList(HashMap<String, Object>  suggestMap) {
		return repairDao.getRepairList(suggestMap);
	}
	public PageControl pageFormat(int page, int pageSize ,HashMap<String, Object> suggestMap){
       int totalLine = repairDao.getRepairCount(suggestMap);
       return new PageControl().pageFormat(page, totalLine, pageSize);
	}
	public Repair getRepairById(int detailId) {
		return repairDao.getRepairById(detailId);
		
	}
	public void delRepair(int id) {
		repairDao.delRepair(id);	
			
		}
	public void addRepair(Repair repair) {
		repairDao.addRepair(repair);
		
	}
	
	public RepairDao getRepairDao() {
		return repairDao;
	}
	public void setRepairDao(RepairDao repairDao) {
		this.repairDao = repairDao;
	}
	public void modifyRepair(Repair repair) {
		repairDao.modifyRepair(repair);
		
	}
	
	
	
	
}
