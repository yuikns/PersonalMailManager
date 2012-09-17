package service.Interface;

public interface IUserinfoService {
	public boolean IsValidUser(String username,String password);
	public boolean EnrollUser(String username,String password);
}
