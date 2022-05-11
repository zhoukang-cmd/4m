package com.fourm.entity;
/**
 * 视频
 * */
public class CameraInfo {
	
	private int cameraId;	//监控点id
	private String cameraName;	//监控点名称
	private String deviceIp;	//所属设备ip
	private int devicePort;	//设备端口
	private int deviceType;	//设备类型
	private String deviceCode;	//设备用户名
	private String deviceName;	//设备用户名
	private String devicePassword;	//设备密码
	private int channelNum;	//通道号
	private int regionId;	//区域id
	private int controlUnitId;	//控制单元id
	
	
	
	public CameraInfo(){}



	
	
	
	public String getDeviceCode() {
		return deviceCode;
	}






	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}






	public int getCameraId() {
		return cameraId;
	}



	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}



	public String getCameraName() {
		return cameraName;
	}



	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}



	public String getDeviceIp() {
		return deviceIp;
	}



	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}



	public int getDevicePort() {
		return devicePort;
	}



	public void setDevicePort(int devicePort) {
		this.devicePort = devicePort;
	}



	public int getDeviceType() {
		return deviceType;
	}



	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}



	public String getDeviceName() {
		return deviceName;
	}



	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}



	public String getDevicePassword() {
		return devicePassword;
	}



	public void setDevicePassword(String devicePassword) {
		this.devicePassword = devicePassword;
	}



	public int getChannelNum() {
		return channelNum;
	}



	public void setChannelNum(int channelNum) {
		this.channelNum = channelNum;
	}



	public int getRegionId() {
		return regionId;
	}



	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}



	public int getControlUnitId() {
		return controlUnitId;
	}



	public void setControlUnitId(int controlUnitId) {
		this.controlUnitId = controlUnitId;
	}




	
}
