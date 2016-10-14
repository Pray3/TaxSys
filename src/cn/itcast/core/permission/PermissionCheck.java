package cn.itcast.core.permission;

import cn.itcast.nsfw.user.entity.User;

public interface PermissionCheck {
  /**
   * 判断用户是否有code对应的权限
   * @param user
   * @param string
   * @return true or false
   */
	public boolean isAccessible(User user, String string);
	
}
