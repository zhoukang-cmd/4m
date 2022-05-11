package com.fourm.service.camera;

import java.util.List;

import com.fourm.dao.camera.CameraDao;
import com.fourm.entity.CameraInfo;
import com.fourm.entity.PlaybackInfo;
import com.fourm.service.base.BaseService;
/**
 * 视频
 * 
 * */
public class CameraService extends BaseService {

	private CameraDao cameraDao;
	
	//从数据库中获得摄像头信息的列表
	public List<CameraInfo> getCameraInfoList(int deviceId) {
		return cameraDao.getCameraInfoList(deviceId);
	}
	
	//从数据库中获取单个摄像头的信息
	public CameraInfo getSingleCameraInfo(Integer camera_id) {
		return cameraDao.getSingleCameraInfo(camera_id);
	}
	//从数据库中获得摄像头信息的列表
	public List<PlaybackInfo> getPlaybackInfoList(int deviceId){
		return cameraDao.getPlaybackInfoList(deviceId);
	}
	//从数据库中获取单个摄像头的信息
	public PlaybackInfo getSinglePlaybackInfo(Integer camera_id){
		return cameraDao.getSinglePlaybackInfo(camera_id);
	}
	
	public CameraDao getCameraDao() {
		return cameraDao;
	}

	public void setCameraDao(CameraDao cameraDao) {
		this.cameraDao = cameraDao;
	}
}
