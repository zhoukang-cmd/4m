package com.fourm.dao.user;

import java.util.List;

import com.fourm.dao.base.BaseDao;
import com.fourm.entity.Equip;
import com.fourm.entity.User;

/*
 * BaseDao继承了SqlMapClientDaoSupport类。
 */
public class UserDao extends BaseDao {

	public User getUser(User user) {
		return (User) getSqlMapClientTemplate().queryForObject("user.getUser", user);
	}
	@SuppressWarnings("unchecked")
	public List<Equip> getPowerProv(String privId) {
		return getSqlMapClientTemplate().queryForList("user.getPowerProv", privId);
	}
	@SuppressWarnings("unchecked")
	public List<Equip> getPowerComp(String privId) {
		return getSqlMapClientTemplate().queryForList("user.getPowerComp", privId);
	}
	@SuppressWarnings("unchecked")
	public List<Equip> getPowerMine(String privId) {
		return getSqlMapClientTemplate().queryForList("user.getPowerMine", privId);
	}
	@SuppressWarnings("unchecked")
	public List<Equip> getPowerRoom(String privId) {
		return getSqlMapClientTemplate().queryForList("user.getPowerRoom", privId);
	}
}
