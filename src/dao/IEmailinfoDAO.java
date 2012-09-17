package dao;

import java.util.List;


public interface IEmailinfoDAO {

	// property constants
	public static final String UID = "uid";
	public static final String SAVE_PWD = "savePwd";
	public static final String SMTP = "smtp";
	public static final String POP3 = "pop3";
	public static final String EUSER = "euser";
	public static final String EPWD = "epwd";

	public abstract void save(Emailinfo transientInstance);

	public abstract void delete(Emailinfo persistentInstance);

	public abstract Emailinfo findById(java.lang.Integer id);

	public abstract List findByExample(Emailinfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByUid(Object uid);

	public abstract List findBySavePwd(Object savePwd);

	public abstract List findBySmtp(Object smtp);

	public abstract List findByPop3(Object pop3);

	public abstract List findByEuser(Object euser);

	public abstract List findByEpwd(Object epwd);

	public abstract List findAll();

	public abstract Emailinfo merge(Emailinfo detachedInstance);

	public abstract void attachDirty(Emailinfo instance);

	public abstract void attachClean(Emailinfo instance);

}