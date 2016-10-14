package cn.itcast.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.itcast.core.constant.Constant;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
    @Resource
	private UserService userService;
    private User user;
    private String longinResult;
  public String toLoginUI(){
	  return "LoginUI";
  }
  //登录
  public String login(){
	 if(user!=null ){
		if(StringUtils.isNotBlank(user.getAccount())&& StringUtils.isNoneBlank(user.getPassword())){
			//根据用户的账号和密码查询列表
			List<User> list= userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
			if(list!=null &&list.size()>0){
			//2.1登录成功
				User user=list.get(0);
			    //2.1.1根据用户Id查询该用户的所有角色
				user.setUserRoles(userService.getUserRoleByUserId(user.getId()));
				//2.1.2将用户信息保存到session中
				ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
				//2.1.3将用户登录行为记录到日志中
                Log log=LogFactory.getLog(getClass());
                log.info("用户名称为" + user.getName()+"的用户登录了系统。");
				//2.1.4重定向到首页
                return "home"; 				
			}else{
				longinResult="账号或密码不正确";
			}
				
		}else{
			 longinResult="账号或密码不能为空";
		}
	 }else{
		 longinResult="请输入账号和密码";
	 }
	  //登录失败
	  return toLoginUI();
  }
//退出
  public String logout(){
	  //清除session
	  ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
	  return toLoginUI();
  }
  public String toNoPermissionUI(){
	  return "noPermissionUI";
  }
public UserService getUserService() {
	return userService;
}
public void setUserService(UserService userService) {
	this.userService = userService;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

  
  
}
