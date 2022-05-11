package com.fourm.service.display;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fourm.dao.display.DisplayDao;
import com.fourm.entity.Field;
import com.fourm.service.base.BaseService;
/**
 * 实时数据
 * 
 * */
public class DisplayService extends BaseService {

	private DisplayDao displayDao;
	//得到实时数据 显示参数信息
	public List<Field> getDisplayField(int  equipId) {
		return displayDao.getDisplayField(equipId);
	}
	//实时数据  得到最新值
	@SuppressWarnings("rawtypes")
	public String getDisplayValue(HashMap tmp) {
		return displayDao.getDisplayValue(tmp);
	}
	//一段时间内的第一条数据
	@SuppressWarnings("rawtypes")
	public HashMap getValueByProcess(HashMap tmp) {
		return displayDao.getValueByProcess(tmp);
	}
	//得到参数的编号
	public String getFieldNum(HashMap tmp){
		return  displayDao.getFieldNum(tmp);
	}
	//实时数据  得到最新时间
	@SuppressWarnings("rawtypes")
	public String getDisplayTime(HashMap tmp) {
		return displayDao.getDisplayTime(tmp);
	}
	public DisplayDao getDisplayDao() {
		return displayDao;
	}

	public void setDisplayDao(DisplayDao displayDao) {
		this.displayDao = displayDao;
	}
	public List<Field> getGroupField(int equipId) {
		return displayDao.getGroupField(equipId);
	}
}
