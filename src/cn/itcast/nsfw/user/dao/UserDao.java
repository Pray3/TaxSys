package cn.itcast.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	/**
	 * 根据帐号和用户id查询用户
	 * @param id 用户ID
	 * @param account 用户帐号
	 * @return 用户列表
	 */
	public List<User> findUserByAccountAndId(String id, String account);
   //保存用户角色
	public void saveRoleId(UserRole userRole);
	 //根据用户删除用户的所有角色
	public void deleteUserRoleByUserId(Serializable id);
	//根据用户id获取该用户对应的所有用户角色
	public List<UserRole> getUserRoleByUserId(String id);
	//根据用户的账号和密码查询列表
	public List<User> findUserByAccountAndPass(String account, String password);

}
