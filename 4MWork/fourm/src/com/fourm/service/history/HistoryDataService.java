package com.fourm.service.history;

import java.util.HashMap;
import java.util.List;

import com.fourm.dao.history.HistoryDataDao;
import com.fourm.entity.Field;
import com.fourm.service.base.BaseService;
/**
 * 历史参数     工况对比
 * 
 * */
public class HistoryDataService extends BaseService {

	private HistoryDataDao historydataDao;
	
	public List<String> getExistsTable() {
		return historydataDao.getExistsTable();
	}

	public List<Field> getField(int equipId) {
		return historydataDao.getField(equipId);
	}
	@SuppressWarnings("rawtypes")
	public HashMap getValue(HashMap<String ,Object> tmp) {
		return historydataDao.getValue(tmp);
	}
	
	public HistoryDataDao getHistorydataDao() {
		return historydataDao;
	}

	public void setHistorydataDao(HistoryDataDao historydataDao) {
		this.historydataDao = historydataDao;
	}

	
}
