package cn.itcast.home.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;
import cn.itcast.nsfw.user.entity.User;
import cn.itcast.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	@Resource
	private UserService userService;
	@Resource
	private ComplainService complainService;
	private Map<String, Object> return_map;
	private Complain comp;
	

	@Override
	public String execute() throws Exception {
		return "home";
	}

	public String complainAddUI() {
		return "complainAddUI";
	}

	public void getUserJson() {
		try {
			// 1.获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if (StringUtils.isNotBlank(dept) ) {
				// 2.根据部门查询用户
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept = ?", dept);
				List<User> userList = userService.findObjects(queryHelper);
				JSONObject jon = new JSONObject();
				jon.put("msg", "success");
				jon.accumulate("userList", userList);

				// 3.输出用户列表以Json格式
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(jon.toString().getBytes("utf-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public String getUserJson2() {
			try {
				// 1.获取部门
				String dept = ServletActionContext.getRequest().getParameter("dept");
				if (StringUtils.isNotBlank(dept) ) {
					// 2.根据部门查询用户
					QueryHelper queryHelper = new QueryHelper(User.class, "u");
					queryHelper.addCondition("u.dept like ?", "%"+dept);
					// 3.输出用户列表以Json格式
					return_map =new HashMap<String, Object>();
					return_map.put("msg", "success");
					return_map.put("userList", userService.findObjects(queryHelper));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
	}
		//保存投诉
		public void complainAdd(){
			try {
				if(comp!=null){
					comp.setState(Complain.COMOLAIN_STATE_UNDONE);
					comp.setCompTime(new Timestamp( new Date().getTime()));
					complainService.save(comp);

					// 3.输出投诉信息
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setContentType("text/html");
					ServletOutputStream outputStream = response.getOutputStream();
					outputStream.write("success".getBytes("utf-8"));
					outputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public Map<String, Object> getReturn_map() {
			return return_map;
		}

		public void setReturn_map(Map<String, Object> return_map) {
			this.return_map = return_map;
		}

		public Complain getComp() {
			return comp;
		}

		public void setComp(Complain comp) {
			this.comp = comp;
		}
   
		
   
		
}
