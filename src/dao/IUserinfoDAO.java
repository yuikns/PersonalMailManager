package dao;

import java.util.List;


public interface IUserinfoDAO {

	// property constants
	public static final String USERNAME = "username";
	public static final String PWD = "pwd";

	public abstract void save(Userinfo transientInstance);

	public abstract void delete(Userinfo persistentInstance);

	public abstract Userinfo findById(java.lang.Integer id);

	public abstract List findByExample(Userinfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByUsername(Object username);

	public abstract List findByPwd(Object pwd);

	public abstract List findAll();

	public abstract Userinfo merge(Userinfo detachedInstance);

	public abstract void attachDirty(Userinfo instance);

	public abstract void attachClean(Userinfo instance);

}