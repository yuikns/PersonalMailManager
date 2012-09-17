package service.Interface;

import javax.mail.Message;

import base.acount.MailAccount;
import base.acount.SingleAccount;
import base.content.MailDetail;
import dao.Emailinfo;

public interface IMailInfoService {
	public boolean addEmailInfo(Emailinfo emailinfo);
	
	public boolean updateEmailInfo(Emailinfo emailinfo);
	
	public boolean deleteEmailInfo(String eid);

	public MailAccount getAllMailInfo();

	public SingleAccount getMailInfo(String eid);

	public SingleAccount getMailInfo(String eid, String folderName);

	public MailDetail getMailDetail(Message message);

	public Emailinfo getEmailinfo(String eid);

	public boolean testConnect(Emailinfo emailinfo);
}
