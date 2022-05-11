package com.fourm.entity;

/**
 * 登录访问的用户信息。
 */
public class User {

	private int userId;//用户id
	private String userName;//用户登录名称
	private String userPassword;//用户登录密码
	private String userIp;//允许用户访问的的IP地址，其它地址不得访问
	private int roleId;
	private int powerId;//所属的级别id，省用户是省id，集团用户是集团id
	
	
	
	/**
	 * privId 用于记录：
	 * 省份用户（1）记录ProvinceId，
	 * 集团用户（2）记录GroupId，
	 * 煤矿用户（3）记录MineId，
	 * 机房用户（4）记录RoomId
	 * 不同的privId，看到的设备信息是不同的。
	 *  p.PROV_ID= #privId#
	 *  and c.COMP_ID= #privId#
	 *  m.MINE_ID= #privId
	 *  r.ROOM_ID= #privId#是
	 */
	private String privId;
	
	private int roleLevel;
	
	/**
	 * 
	 * powerLevel用于记录是哪个级别的用户
	 * if (user.getPowerLevel() == 1) {// 省份用户
	 * 		userDao.getPowerProv(user.getPrivId());
	 * 	}else if(user.getPowerLevel()==2){//集团用户
	 * 	  userDao.getPowerComp(user.getPrivId());
	 * 	}else if(user.getPowerLevel()==3){//煤矿用户
	 * 		session =userDao.getPowerMine(user.getPrivId());
	 * 	}else if(user.getPowerLevel()==4){//机房用户
	 * 		session =userDao.getPowerRoom(user.getPrivId());
	 * 	}
	 */
	private int powerLevel;
	private String roleName;
	private String powerName;//同powerid类似

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPowerId() {
		return powerId;
	}

	public void setPowerId(int powerId) {
		this.powerId = powerId;
	}

	public String getPrivId() {
		return privId;
	}

	public void setPrivId(String privId) {
		this.privId = privId;
	}

	public int getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(int roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public int getPowerLevel() {
		return powerLevel;
	}

	public void setPowerLevel(int powerLevel) {
		this.powerLevel = powerLevel;
	}

}
