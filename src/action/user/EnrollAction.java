package action.user;

import service.Interface.IUserinfoService;
import action.BaseAction;


public class EnrollAction extends BaseAction {
	private static final long serialVersionUID = -2990431838075074858L;
	private String username;
	private String password;
	private IUserinfoService userinfoService;
	
	public String enroll() throws Exception {
		if (userinfoService.EnrollUser(username, password)) {
			this.setDynamicUrl("/jsp/user/enroll/success.jsp");
			//return DYNAMIC;		
		} else {
			//this.setDynamicUrl("/jsp/user/enroll/failure.jsp");
			this.setDynamicUrl("enroll.jsp");
			throw new Exception("注册失败:用户名已存在");
		}
		return DYNAMIC;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		System.out.println("username:"+username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("password:"+password);
		this.password = password;
	}
	public IUserinfoService getUserinfoService() {
		return userinfoService;
	}

	public void setUserinfoService(IUserinfoService userService) {
		this.userinfoService = userService;
	}


}
