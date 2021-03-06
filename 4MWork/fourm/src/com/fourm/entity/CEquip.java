package com.fourm.entity;

import java.util.Date;


/**
 * CEquip用于记录子设备信息
 * @author liujinhai
 *
 */
public class CEquip {
	/**
	 * 子设备主键
	 */
	private int cequipId;  
	/**
	 * 子设备编码
	 */
	private String cequipCode;      
	/**
	 * 子设备编号
	 */
	private String cequipNum;
	/**
	 * 子设备名称
	 */
	private String cequipName;
	/**
	 * 子设备型号
	 */
	private String cequipModel;
	/**
	 * 厂家信息
	 */
	private String manuInfo;
	/**
	 * 启用时间
	 */
    private String startTime;
    /**
	 * 设备数量
	 */
    private int cequipStore;
    /**
	 * 主设备主键
	 */
    private int equipId;
    
    public CEquip() {

    }
    
	public int getCequipId() {
		return cequipId;
	}
	public void setCequipId(int cequipId) {
		this.cequipId = cequipId;
	}
	public String getCequipCode() {
		return cequipCode;
	}
	public void setCequipCode(String cequipCode) {
		this.cequipCode = cequipCode;
	}
	public String getCequipNum() {
		return cequipNum;
	}
	public void setCequipNum(String cequipNum) {
		this.cequipNum = cequipNum;
	}
	public String getCequipName() {
		return cequipName;
	}
	public void setCequipName(String cequipName) {
		this.cequipName = cequipName;
	}
	public String getCequipModel() {
		return cequipModel;
	}
	public void setCequipModel(String cequipModel) {
		this.cequipModel = cequipModel;
	}
	public String getManuInfo() {
		return manuInfo;
	}
	public void setManuInfo(String manuInfo) {
		this.manuInfo = manuInfo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public int getCequipStore() {
		return cequipStore;
	}
	public void setCequipStore(int cequipStore) {
		this.cequipStore = cequipStore;
	}
	public int getEquipId() {
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}
    
}
