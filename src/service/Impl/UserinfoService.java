package service.Impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import common.Utils.Md5.Md5;

import service.Interface.IUserinfoService;
import dao.IUserinfoDAO;
import dao.Userinfo;


public class UserinfoService implements IUserinfoService {
	private IUserinfoDAO userinfoDAO;
	
	public boolean IsValidUser(String username, String password) {

		List users = this.userinfoDAO.findByUsername(username);
		
		if (users == null || users.isEmpty()) {
			return false;
		}
		
		Userinfo user = (Userinfo) users.get(0);
//		System.out.println("****************************");
//		System.out.println(user.getPwd()+"VS["+Md5.getMd5WithSalt(password)+"]");
//		System.out.println("****************************");
		
		if (Md5.compareMd5(password, user.getPwd())) {
			ServletActionContext.getContext().getSession().put("uid",String.valueOf(user.getId()));
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 注册用户
	 */
	public boolean EnrollUser(String username, String password) {
		Userinfo user = new Userinfo();
		user.setUsername(username);
		user.setPwd(Md5.getMd5WithSalt(password));
		
		//check has
		List users = this.userinfoDAO.findByUsername(username);
		if (!(users == null || users.isEmpty())) return false;
		
		this.userinfoDAO.save(user);
		return true;
	}
		
	public IUserinfoDAO getUserinfoDAO() {
		return userinfoDAO;
	}

	public void setUserinfoDAO(IUserinfoDAO userinfoDAO) {
		this.userinfoDAO = userinfoDAO;
	}

}
