package action.user;

import service.Interface.IUserinfoService;
import action.BaseAction;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 918052537773460851L;
	private String username;
	private String password;
	private String check;
	private IUserinfoService userinfoService;

	public String login() throws Exception {
		String code = getSessionMap().get("code").toString();
		//System.out.println("code VS check :"+ code + " VS " + check);
		if(!code.equals(check)){
			this.setDynamicUrl("/login.jsp");
			throw new Exception("error verification code");
		}
		
		if (userinfoService.IsValidUser(username, password)) {
			setUser(username);
			this.setDynamicUrl("/jsp/struct/main.jsp");
			return DYNAMIC;
			// return "success";
		} else {
			this.setDynamicUrl("/login.jsp");
			throw new Exception("Login Fail");
			//return DYNAMIC;
		}
	}
	
	/*
	public void validateLogin(){
		String code = getSessionMap().get("code").toString();
		System.out.println("code VS check :"+ code + " VS " + check);
		if(!code.equals(check)){
			super.addFieldError("username", "error");
		}
	}*/
	
	public String reload() throws Exception {
		this.setDynamicUrl("/jsp/struct/main.jsp");
		return DYNAMIC;
	}
	
	public String forEnroll() throws Exception {
		this.setDynamicUrl("/enroll.jsp");
		return DYNAMIC;
	}
	
	public String forIndex(){
		this.setDynamicUrl("/");
		return DYNAMIC;
	}

	public String logoff() throws Exception {
		removeUser();
		this.setDynamicUrl("/");
		return DYNAMIC;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		System.out.println("username:" + username);
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		System.out.println("password:" + password);
		this.password = password;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public IUserinfoService getUserinfoService() {
		return userinfoService;
	}

	public void setUserinfoService(IUserinfoService userService) {
		this.userinfoService = userService;
	}

}
