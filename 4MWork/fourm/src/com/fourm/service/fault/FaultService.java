package com.fourm.service.fault;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fourm.dao.fault.FaultDao;
import com.fourm.entity.Fault;
import com.fourm.entity.PageControl;
import com.fourm.service.base.BaseService;

/**
 * 历史故障
 * 
 * */
public class FaultService extends BaseService {

private FaultDao faultDao;
	
	public List<Fault> getFaultList(HashMap<String, Object>  faultMap) {
		return faultDao.getFaultList(faultMap);
	}
	public PageControl pageFormat(int page, int pageSize ,HashMap<String, Object> faultMap){
       int totalLine = faultDao.getFaultCount(faultMap);
       return new PageControl().pageFormat(page, totalLine, pageSize);
	}
	public Fault getFaultById(int detailId) {
		return faultDao.getFaultById(detailId);
		
	}
	/**
	 * 
	 * 查找同一类型的故障信息、便于自动添加故障
	 * 
	 */
	public Fault getFaultByTypeTime(Map map) {
		return faultDao.getFaultByTypeTime(map);
		
	}

	
	public FaultDao getFaultDao() {
		return faultDao;
	}
	public void setFaultDao(FaultDao faultDao) {
		this.faultDao = faultDao;
	}
	public void delFault(int id) {
		faultDao.delDault(id);
		
	}
	public void addFault(Fault fault) {
		faultDao.addFault(fault);
	}
	public void modifyFault(Fault fault) {
		faultDao.modifyFault(fault);
		
	}
	
}
