package com.fourm.service.equip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fourm.dao.equip.EquipDao;
import com.fourm.dao.fault.FaultDao;
import com.fourm.entity.CEquip;
import com.fourm.entity.CequipType;
import com.fourm.entity.Equip;
import com.fourm.entity.EquipChange;
import com.fourm.entity.Fault;
import com.fourm.entity.Field;
import com.fourm.entity.PageControl;
import com.fourm.service.base.BaseService;

/**
 * 历史故障
 * 
 * */
public class EquipService extends BaseService {

	private EquipDao equipDao;
	/**
	 * 
	 * 得到设备信息
	 * 
	 */
	public Equip getEquipById(int equipId) {
		return equipDao.getEquipById(equipId);
	}
	/**
	 * 
	 * 得到机房所有设备
	 * 
	 */
	public List<Equip> getEquipByRoom(int roomId){
		return equipDao.getEquipByRoom(roomId);
	}
	/**
	 * 
	 * 条件查询机房设备
	 * 
	 */
	public List<Equip> selQueryEquip(Map map){
		return equipDao.selQueryEquip(map);
	}
	/**
	 * 
	 * 条件查询机房子设备
	 * 
	 */
	public List<CEquip> selQueryCEquip(Map map){
		return equipDao.selQueryCEquip(map);
	}
	/**
	 * 
	 * 条件查询设备变动
	 * 
	 */
	public List<EquipChange> selQueryChange(Map map){
		return equipDao.selQueryChange(map);
	}
	
	/**
	 * 
	 * 得到设备变动信息
	 * 
	 */
	public List<EquipChange> getEquipChangeById(int equipId) {
		return equipDao.getEquipChangeById(equipId);
	}
	
	/**
	 * 
	 * 根据ID得到设备变动
	 * 
	 */
	public EquipChange getChangeByID(int id){
		return equipDao.getChangeByID(id);
	}
	/**
	 * 
	 * 查看子设备信息
	 */
	public List<CEquip> getCEquipById(int equipId){
		return equipDao.getCEquipById(equipId);
	}
	
	public CEquip getCEquipId(int detailId){
		return equipDao.getCEquipId(detailId);
	}
	/**
	 * @param cequip
	 * 修改子设备信息
	 */
	public void modifyCEquip(CEquip cequip){
		equipDao.modifyCEquip(cequip);
	}
	
	/**
	 * 添加子设备信息
	 */
	public void addCEquip(CEquip cequip){
		equipDao.addCEquip(cequip);		
	} 
	/**
	 * 
	 * @param cequipId
	 * @return 删除子设备信息
	 */
	public int delCEquipById(int cequipId){
		return equipDao.delCEquipById(cequipId);
	}
	/**
	 * 
	 * @param equipChange
	 * 添加设备变动信息
	 */
	public void addEquipChange(EquipChange equipChange){
		equipDao.addEquipChange(equipChange);
	}
	/**
	 * 
	 * @param equipChange
	 * 修改设备变动信息
	 */
 public void modifyChange(EquipChange equipChange){
	 equipDao.modifyChange(equipChange);
	}
	/**
	 * 删除选中的变动信息
	 * 
	 */
	public int delSelectChange(int changeId){
		return equipDao.delSelectChange(changeId);
	}
	/**
	 * 子设备实时参数
	 * 
	 */
	
	public List<Field> getDisplayField(Map map){
		return equipDao.getDisplayField(map);
	}
	/**
	 * 子设备实时参数数据
	 * 
	 */
	public String getDisplayValue(HashMap tmp) {
		return equipDao.getDisplayValue(tmp);
	}
	/**
	 * 
	 * 子设备类型
	 */
	public List<CequipType> getCequipType(String equipCode){
		return  equipDao.getCequipType(equipCode);
	}

	public EquipDao getEquipDao() {
		return equipDao;
	}
	public void setEquipDao(EquipDao equipDao) {
		this.equipDao = equipDao;
	}


}
