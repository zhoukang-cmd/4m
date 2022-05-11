package com.fourm.dao.camera;

import java.util.List;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.CameraInfo;
import com.fourm.entity.PlaybackInfo;

public class CameraDao extends BaseDao {

	//从数据库中获得摄像头信息的列表
	@SuppressWarnings("unchecked")
	public List<CameraInfo> getCameraInfoList(int deviceId) {
		return getSqlMapClientTemplate().queryForList("cameraInfo.getCameraInfoList", deviceId);
	}
	
	//从数据库中获取单个摄像头的信息
	public CameraInfo getSingleCameraInfo(Integer camera_id) {
		return (CameraInfo)getSqlMapClientTemplate().queryForObject("cameraInfo.getSingleCameraInfoList", camera_id);
	}	
	
	//从数据库中获得摄像头信息的列表
	@SuppressWarnings("unchecked")
	public List<PlaybackInfo> getPlaybackInfoList(int deviceId){
		return getSqlMapClientTemplate().queryForList("playbackInfo.getPlaybackInfoList", deviceId);
	}
	
	//从数据库中获取单个摄像头的信息
	public PlaybackInfo getSinglePlaybackInfo(Integer camera_id){
		return (PlaybackInfo)getSqlMapClientTemplate().queryForObject("playbackInfo.getSinglePlaybackInfoList", camera_id);
	}
	
	
	
	
	
	
	
	

}
