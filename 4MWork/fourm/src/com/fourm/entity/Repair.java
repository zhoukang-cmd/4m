package com.fourm.entity;

/**
 * 历史维护
 */
public class Repair {

	private int repairId;
	private String repairStatus;
	private String repairCreateTime;
	private String repairTime;
	private String repairObject;
	private String repairContent;
	private String repairQues;
	private String remainQues;

	private int equipId;

	public Repair() {
	}

	public int getRepairId() {
		return repairId;
	}

	public void setRepairId(int repairId) {
		this.repairId = repairId;
	}

	public String getRepairStatus() {
		return repairStatus;
	}

	public void setRepairStatus(String repairStatus) {
		this.repairStatus = repairStatus;
	}

	public String getRepairCreateTime() {
		return repairCreateTime;
	}

	public void setRepairCreateTime(String repairCreateTime) {
		this.repairCreateTime = repairCreateTime;
	}

	public String getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(String repairTime) {
		this.repairTime = repairTime;
	}

	public String getRepairObject() {
		return repairObject;
	}

	public void setRepairObject(String repairObject) {
		this.repairObject = repairObject;
	}

	public String getRepairContent() {
		return repairContent;
	}

	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}

	public String getRepairQues() {
		return repairQues;
	}

	public void setRepairQues(String repairQues) {
		this.repairQues = repairQues;
	}

	public String getRemainQues() {
		return remainQues;
	}

	public void setRemainQues(String remainQues) {
		this.remainQues = remainQues;
	}

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

}
