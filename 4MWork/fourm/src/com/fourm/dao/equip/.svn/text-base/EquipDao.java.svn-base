package com.fourm.dao.equip;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.CEquip;
import com.fourm.entity.CequipType;
import com.fourm.entity.Equip;
import com.fourm.entity.EquipChange;
import com.fourm.entity.Fault;
import com.fourm.entity.Field;

public class EquipDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public Equip getEquipById(int equipId) {
		return (Equip) getSqlMapClientTemplate().queryForObject("equip.getEquip",equipId);
	}

	public List<EquipChange> getEquipChangeById(int equipId) {
		return getSqlMapClientTemplate().queryForList("equip.getEquipChange",equipId);
	}
	
	/**
	 * 
	 * @param equipId
	 * @return 查询子设备信息
	 */
	public List<CEquip> getCEquipById(int equipId){
		return getSqlMapClientTemplate().queryForList("equip.getCEquip", equipId);
	}
	
	public CEquip getCEquipId(int detailId){
		return (CEquip) getSqlMapClientTemplate().queryForObject("equip.getCEquipId", detailId);
	}
	/**
	 * 
	 * @param cequip
	 * 修改子设备信息
	 */
	public void modifyCEquip(CEquip cequip){
		getSqlMapClientTemplate().update("equip.getModifyCEquip", cequip);
	}
	/**
	 * 
	 * @param cequip
	 * 添加子设备信息
	 */
	public void addCEquip(CEquip cequip){
		getSqlMapClientTemplate().insert("equip.getAddCEquip",cequip);
	}
	
    /**
     * 	
     * @param equipChange
     * 添加设备变动
     */
	public void addEquipChange(EquipChange equipChange){
		getSqlMapClientTemplate().insert("equip.getAddEquipChange", equipChange);
	}
	/**
	 * 
	 * @param cequipId
	 * @return 删除子设备信息
	 */
	
	public int delCEquipById(int cequipId){
		return getSqlMapClientTemplate().delete("equip.delCEquip", cequipId);
	}
	
	public int delSelectChange(int changeId){
		return getSqlMapClientTemplate().delete("equip.delChange",changeId);
	}
	/**
	 * 
	 * 得到机房所有设备
	 * 
	 */
	public List<Equip> getEquipByRoom(int roomId){
		return getSqlMapClientTemplate().queryForList("equip.getEquipByRoom", roomId);
	}
	/**
	 * 
	 * 条件查询机房设备
	 * 
	 */
	public List<Equip> selQueryEquip(Map map){
		return getSqlMapClientTemplate().queryForList("equip.selQueryEquip",map);
	}
	/**
	 * 
	 * 条件查询子设备
	 * 
	 */
	public List<CEquip> selQueryCEquip(Map map){
		return getSqlMapClientTemplate().queryForList("equip.selQueryCEquip",map);
	}
	/**
	 * 
	 * 条件查询设备变动
	 * 
	 */
	public List<EquipChange> selQueryChange(Map map){
		return getSqlMapClientTemplate().queryForList("equip.selQueryChange",map);
	}
	/**
	 * 根据ID得到变动信息
	 * 
	 * 
	 */
	public EquipChange getChangeByID(int changeId){
		return (EquipChange) getSqlMapClientTemplate().queryForObject("equip.queryChangeById",changeId);
	}
	/**
	 * 修改设备变动
	 * 
	 * 
	 */
	public void modifyChange(EquipChange equipChange){
		getSqlMapClientTemplate().update("equip.updateEquipChange",equipChange);
		}
	/**
	 * 子设备实时参数
	 * 
	 */
	public List<Field> getDisplayField(Map map){
		return getSqlMapClientTemplate().queryForList("equip.getCequipField",map);
	}
	/**
	 * 子设备实时参数数据
	 * 
	 */
	public String getDisplayValue(HashMap tmp) {
		return  (String) getSqlMapClientTemplate().queryForObject("equip.getDisplayValue",tmp);
	}
	/**
	 * 
	 * 子设备类型
	 */
	public List<CequipType> getCequipType(String equipCode){
		return  getSqlMapClientTemplate().queryForList("equip.getCequipType",equipCode);
	}
	
}
