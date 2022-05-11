package com.fourm.action.camera;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.fourm.action.base.BaseAction;
import com.fourm.entity.CameraInfo;
import com.fourm.entity.Equip;
import com.fourm.entity.PlaybackInfo;
import com.fourm.service.camera.CameraService;
/**
 * 视频
 * */

public class CameraAction extends BaseAction {

	private String nav;
	private static final long serialVersionUID = 1L;
	private int deviceId;
	private String cameraID;
	
	private List<CameraInfo> lstCameraInfo;
	private List<PlaybackInfo> lstPlayBackInfo;	

	private CameraInfo cameraInfo;
	private PlaybackInfo playbackInfo;
	
	private String strStartTime;
	private String strEndTime;
	
	//视频数据
	public String getList()
	{
		//页面显示的配置
		this.setNav("video");
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		CameraService cameraService = (CameraService) getBeanById("cameraService");
		
		//得到FOURM_T_ROOM表中DEVICE_ID字段信息
		deviceId = currentSession.getDeviceId();
		if (deviceId != -1){ //有视频设备
		
			lstCameraInfo = cameraService.getCameraInfoList(deviceId);
			
		}
		else{
			cameraInfo = new CameraInfo();
			cameraInfo.setCameraId(-1);
			cameraInfo.setCameraName("(无视频设备)");
			lstCameraInfo = new ArrayList<CameraInfo>();
			lstCameraInfo.add(cameraInfo);
		}
		return "camera_select";
	}
	//视频数据视频
	public String getVideo()
	{
		//页面显示的配置
		this.setNav("video");
		
		//从session中的deviceID信息
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		deviceId = currentSession.getDeviceId();
		
		//获取所有该deviceID下的camera信息
		CameraService cameraService = (CameraService) getBeanById("cameraService");		
		lstCameraInfo = cameraService.getCameraInfoList(deviceId);
		
		//将所有camera信息的deviceType设置为10002，并获取前台传过来的cameraID对应的camera信息
		for(CameraInfo lst:lstCameraInfo)
		{
			lst.setDeviceType(10001);                                         //改动部分
			if(lst.getCameraId() == Integer.valueOf(cameraID))
			{
				cameraInfo = lst;
			}
		}

		return "camera_video";
	}
	//历史视频
	public String getPlayBackList()
	{
		System.out.println(" getPlayBackList()");
		//页面显示的配置
		this.setNav("history");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		CameraService cameraService = (CameraService) getBeanById("cameraService");	
		
		deviceId = currentSession.getDeviceId();
		
		if (deviceId != -1){ //有视频设备
			lstPlayBackInfo = cameraService.getPlaybackInfoList(deviceId);
		}
		else{
			playbackInfo = new PlaybackInfo();
			playbackInfo.setDeviceId(-1);
			playbackInfo.setCameraId(-1);
			playbackInfo.setCameraName("(无视频设备)");
			lstPlayBackInfo = new ArrayList<PlaybackInfo>();
			lstPlayBackInfo.add(playbackInfo);
		}
		for(PlaybackInfo p:lstPlayBackInfo){
			System.out.println("getPlayBackList()   lstPlayBackInfo:"+p.getCameraId()+p.getCameraName()+p.getDeviceId());
		}
		return "playback_select";
	}
	
	public String getPlayBackVideo()
	{
		System.out.println("getPlayBackVideo()");
		//页面显示的配置
		this.setNav("history");
		
		strStartTime = this.getStrStartTime();
		strEndTime = this.getStrEndTime();		
				
		CameraService cameraService = (CameraService)getBeanById("cameraService");
		playbackInfo = cameraService.getSinglePlaybackInfo(Integer.valueOf(cameraID));
		System.out.println("playbackInfo:"+playbackInfo.getCameraId()+playbackInfo.getCameraName()+playbackInfo.getDeviceId());
		HttpServletRequest request = ServletActionContext.getRequest();
		Equip currentSession =   (Equip) request.getSession().getAttribute("currentSession");
		deviceId = currentSession.getDeviceId();		
		assert(deviceId!= -1);
		lstPlayBackInfo = cameraService.getPlaybackInfoList(deviceId);
		for(PlaybackInfo p:lstPlayBackInfo){
			System.out.println("getPlayBackVideo()lstPlayBackInfo:"+p.getCameraId()+p.getCameraName()+p.getDeviceId());
		}
		return "playback_video";
	}
	public void setCameraID(String cameraID) {
		this.cameraID = cameraID;
	}

	public String getCameraID() {
		return cameraID;
	}

	public String getNav() {
		return nav;
	}

	public void setNav(String nav) {
		this.nav = nav;
	}
	public List<CameraInfo> getLstCameraInfo() {
		return lstCameraInfo;
	}

	public void setLstCameraInfo(List<CameraInfo> lstCameraInfo) {
		this.lstCameraInfo = lstCameraInfo;
	}
	
	public List<PlaybackInfo> getLstPlayBackInfo() {
		return lstPlayBackInfo;
	}

	public void setLstPlayBackInfo(List<PlaybackInfo> lstPlayBackInfo) {
		this.lstPlayBackInfo = lstPlayBackInfo;
	}
	
	public CameraInfo getCameraInfo() {
		return cameraInfo;
	}

	public void setCameraInfo(CameraInfo cameraInfo) {
		this.cameraInfo = cameraInfo;
	}	
	
	public PlaybackInfo getPlaybackInfo() {
		return playbackInfo;
	}
	
	public void setPlaybackInfo(PlaybackInfo playbackInfo) {
		this.playbackInfo = playbackInfo;
	}	
	
	public String getStrStartTime() {
		return strStartTime;
	}

	public void setStrStartTime(String strStartTime) {
		this.strStartTime = strStartTime;
	}

	public String getStrEndTime() {
		return strEndTime;
	}

	public void setStrEndTime(String strEndTime) {
		this.strEndTime = strEndTime;
	}

	
}
