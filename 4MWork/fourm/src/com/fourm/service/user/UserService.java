package com.fourm.service.user;

import java.util.List;
import org.apache.commons.lang.xwork.StringUtils;
import com.fourm.dao.user.UserDao;
import com.fourm.entity.Equip;
import com.fourm.entity.User;
import com.fourm.service.base.BaseService;
import com.fourm.util.SignUtil;
/**
 * 用户登录
 * 
 * */
public class UserService extends BaseService {

	private UserDao userDao;
	
	/**
	 * //判断用户
	 * @param user
	 * @return
	 */
	
	public User getUser(User user) {
		//md5加密 对用户输入的密码信息进行加密，后面将这个加密的信息和数据库中存储的信息进行比对
		user.setUserPassword(SignUtil.getMd5(user.getUserPassword(), "UTF-8"));
		
		//根据用户名查找用户信息
		User temp = userDao.getUser(user);
		
		//用户不存在
		if (temp == null) {
			return null;
		}

		if (StringUtils.isNotBlank(temp.getUserIp())) {
			if (!temp.getUserIp().contains(user.getUserIp())) {
				return null;
			}
		}
		//检查密码。数据库中存放的是加密的信息 
		
		if (!user.getUserPassword().equals(temp.getUserPassword())) {
			return null;
		}

		return temp;
	}
	
	/**
	 * //判断权限，这里所谓的权限就是能看到的设备列表
	 * @param user
	 * @return
	 */
	
	public List<Equip> getPower(User user) {
		//没有权限信息
		if (user.getPrivId()==null ) {
			return null;
		}
		//后台用户
		if ((Integer)user.getPowerLevel()==5  ) {
			return null;
		}
		List<Equip> session=null;
		if (user.getPowerLevel() == 1) {// 省份用户
			session = userDao.getPowerProv(user.getPrivId());
			if (session == null || session.isEmpty()) {
				return null;
			}
		}else if(user.getPowerLevel()==2){//集团用户
			session =userDao.getPowerComp(user.getPrivId());
			if(session == null || session.isEmpty()){
				return null;
			}
			
		}else if(user.getPowerLevel()==3){//煤矿用户
			session =userDao.getPowerMine(user.getPrivId());
			if(session == null || session.isEmpty()){
				return null;
			}
			
		}else if(user.getPowerLevel()==4){//机房用户
			session =userDao.getPowerRoom(user.getPrivId());
			if(session == null || session.isEmpty()){
				return null;
			}
		}
		return session;
	}
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
