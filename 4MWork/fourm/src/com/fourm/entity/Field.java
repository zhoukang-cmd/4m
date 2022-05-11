package com.fourm.entity;

/**
 * 字段信息，从目前的知识看，这个field实际是参数的意思 
 *  if("风门状态".equals(ext.getFieldName())){
 * if(Float.parseFloat(value)<=0){ sdv.setExtString("关闭"); }else
 * if(Float.parseFloat(value)>=0){ sdv.setExtString("开启"); } }
 * if("风量".equals(ext.getFieldName())){
 */
public class Field {

	private int fieldId;
	private String fieldType; // FIELD_TYPE = 'L' 或者FIELD_TYPE = 'H' 高频或者低频
	private String fieldNo;
	
	/**
	 * if("风门状态".equals(ext.getFieldName())){ if(Float.parseFloat(value)<=0){
	 * sdv.setExtString("关闭"); }else if(Float.parseFloat(value)>=0){
	 * sdv.setExtString("开启"); } } if("风量".equals(ext.getFieldName())){
	 */
	private String fieldName;// 参数字段名称
	private String fieldDesc;// 参数字段名称
	private String fieldLimitLow;// 低阀值
	private String fieldLimitHigh;// 高阀值
	private String fieldDisplayX;
	private String fieldDisplayY;

	private int equipId;

	private int groupId;
	private String groupName;
	private String cequipCode;
	private String typeName;

	public Field() {
	}

	public int getFieldId() {
		return fieldId;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldNo() {
		return fieldNo;
	}

	public void setFieldNo(String fieldNo) {
		this.fieldNo = fieldNo;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldLimitLow() {
		return fieldLimitLow;
	}

	public void setFieldLimitLow(String fieldLimitLow) {
		this.fieldLimitLow = fieldLimitLow;
	}

	public String getFieldLimitHigh() {
		return fieldLimitHigh;
	}

	public void setFieldLimitHigh(String fieldLimitHigh) {
		this.fieldLimitHigh = fieldLimitHigh;
	}

	public String getFieldDisplayX() {
		return fieldDisplayX;
	}

	public void setFieldDisplayX(String fieldDisplayX) {
		this.fieldDisplayX = fieldDisplayX;
	}

	public String getFieldDisplayY() {
		return fieldDisplayY;
	}

	public void setFieldDisplayY(String fieldDisplayY) {
		this.fieldDisplayY = fieldDisplayY;
	}

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCequipCode() {
		return cequipCode;
	}

	public void setCequipCode(String cequipCode) {
		this.cequipCode = cequipCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
