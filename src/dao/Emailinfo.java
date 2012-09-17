package dao;

/**
 * Emailinfo entity. @author MyEclipse Persistence Tools
 */

public class Emailinfo implements java.io.Serializable {

	// Fields

	private Integer eid;
	private Integer uid;
	private Integer savePwd;
	private String smtp;
	private String pop3;
	private String euser;
	private String epwd;

	// Constructors

	/** default constructor */
	public Emailinfo() {
	}

	/** full constructor */
	public Emailinfo(Integer uid, Integer savePwd, String smtp, String pop3,
			String euser, String epwd) {
		this.uid = uid;
		this.savePwd = savePwd;
		this.smtp = smtp;
		this.pop3 = pop3;
		this.euser = euser;
		this.epwd = epwd;
	}

	// Property accessors

	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSavePwd() {
		return this.savePwd;
	}

	public void setSavePwd(Integer savePwd) {
		this.savePwd = savePwd;
	}

	public String getSmtp() {
		return this.smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getPop3() {
		return this.pop3;
	}

	public void setPop3(String pop3) {
		this.pop3 = pop3;
	}

	public String getEuser() {
		return this.euser;
	}

	public void setEuser(String euser) {
		this.euser = euser;
	}

	public String getEpwd() {
		return this.epwd;
	}

	public void setEpwd(String epwd) {
		this.epwd = epwd;
	}

}