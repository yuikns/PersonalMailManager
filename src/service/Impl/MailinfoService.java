package service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.apache.struts2.ServletActionContext;

import service.Interface.IMailInfoService;
import base.acount.MailAccount;
import base.acount.SingleAccount;
import base.content.MailDetail;

import common.Utils.Md5.SymmetricEncryption;
import common.Utils.mail.ReceiveEmail;
import common.Utils.mail.tools.ConvertMessage;

import dao.Emailinfo;
import dao.IEmailinfoDAO;
import dao.IUserinfoDAO;

public class MailinfoService implements IMailInfoService {
	private IEmailinfoDAO emailinfoDAO;
	private IUserinfoDAO userinfoDAO;
	private Emailinfo emailinfo;

	// mail account operation 
	
	public boolean addEmailInfo(Emailinfo emailinfo) {
		if (!testConnect(emailinfo)) return false;
		emailinfo.setUid(Integer.parseInt(ServletActionContext.getContext().getSession().get("uid").toString()));
		emailinfo.setSavePwd(1);
		//TODO
		emailinfo.setEpwd(SymmetricEncryption.encryptData(emailinfo.getEpwd()));
		this.emailinfoDAO.save(emailinfo);
		return true;
	}
	
	public boolean deleteEmailInfo(String eid) {
		this.emailinfoDAO.delete(this.emailinfoDAO.findById(Integer.parseInt(eid)));
		return true;
	}	
	
	public boolean updateEmailInfo(Emailinfo emailinfo) {
		if (!testConnect(emailinfo)) return false;
		emailinfo.setUid(Integer.parseInt(ServletActionContext.getContext().getSession().get("uid").toString()));
		emailinfo.setSavePwd(1);
		//TODO
		emailinfo.setEpwd(SymmetricEncryption.encryptData(emailinfo.getEpwd()));
		this.emailinfoDAO.attachDirty(emailinfo); //update?
		return true;
	}

	public Emailinfo getEmailinfo(String eid) {
		Emailinfo ei = this.emailinfoDAO.findById(Integer.parseInt(eid));
		ei.setEpwd(SymmetricEncryption.decryptData(ei.getEpwd()));
		return ei;
	}
	
	//mail itself operation

	@SuppressWarnings("unchecked")
	public MailAccount getAllMailInfo() {
		MailAccount mc = new MailAccount();
		ReceiveEmail rm = new ReceiveEmail();
		List<Emailinfo> acName = this.emailinfoDAO.findByUid(Integer.parseInt(ServletActionContext.getContext().getSession()
						.get("uid").toString()));
		for (int i = 0; i < acName.size(); i++) {
			SingleAccount sa = new SingleAccount();
			Emailinfo mail = acName.get(i);
			ArrayList<Folder> folders = new ArrayList<Folder>();
			sa.setAccountName(mail.getEuser());
			sa.setEid(mail.getEid().toString());
			//TODO
			//folders.addAll(rm.getAllFolderName(mail.getPop3(), mail.getEuser(),mail.getEpwd()));
			folders.addAll(rm.getAllFolderName(mail.getPop3(), mail.getEuser(),SymmetricEncryption.decryptData(mail.getEpwd())));
			
			try {
				sa.setFolders(folders);
			} catch (MessagingException e) {
			}
			// System.out.println("###acname:" + sa.getAccountName()+"###");
			mc.addAccount(sa);

		}
		return mc;
	}

	public SingleAccount getMailInfo(String eid) {
		return getMailInfo(eid, "");
	}

	public SingleAccount getMailInfo(String eid, String folderName) {
		SingleAccount sa = new SingleAccount();
		Emailinfo mail = this.emailinfoDAO.findById(Integer.parseInt(eid));
		ArrayList<Folder> folders = new ArrayList<Folder>();
		ReceiveEmail rm = new ReceiveEmail();
		sa.setAccountName(mail.getEuser());
		sa.setEid(mail.getEid().toString());
		//for (Folder fl : rm.getAllFolderName(mail.getPop3(), mail.getEuser(),mail.getEpwd())) {
		for (Folder fl : rm.getAllFolderName(mail.getPop3(), mail.getEuser(),SymmetricEncryption.decryptData(mail.getEpwd()))) {
			if (folderName == null) {
				folders.add(fl);
			} else if (folderName.equals("")) {
				folders.add(fl);
			} else if (fl.getFullName().equals(folderName)) {
				folders.add(fl);
			}
		}
		try {
			sa.setFolders(folders);
		} catch (MessagingException e) {
		}
		return sa;
	}

	public MailDetail getMailDetail(Message message) {
		ConvertMessage cm = new ConvertMessage(message);
		MailDetail md = cm.getMailDetail();
		return md;
	}

	public boolean testConnect(Emailinfo emailinfo) {
		ReceiveEmail rm = new ReceiveEmail(emailinfo.getPop3(),
				emailinfo.getEuser(), emailinfo.getEpwd());
		return rm.testConnect();
	}

	// setters & getters
	public IEmailinfoDAO getEmailinfoDAO() {
		return emailinfoDAO;
	}

	public void setEmailinfoDAO(IEmailinfoDAO emailinfoDAO) {
		this.emailinfoDAO = emailinfoDAO;
	}

	public IUserinfoDAO getUserinfoDAO() {
		return userinfoDAO;
	}

	public void setUserinfoDAO(IUserinfoDAO userinfoDAO) {
		this.userinfoDAO = userinfoDAO;
	}

	public Emailinfo getEmailinfo() {
		return emailinfo;
	}

	public void setEmailinfo(Emailinfo emailinfo) {
		this.emailinfo = emailinfo;
	}



}
