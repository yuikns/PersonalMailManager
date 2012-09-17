package action.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.activation.DataHandler;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;

import service.Interface.IMailInfoService;
import service.Interface.IUserinfoService;
import action.BaseAction;
import base.acount.MailAccount;
import base.acount.SingleAccount;
import base.content.MailDetail;

import common.Utils.mail.SendEmail;
import common.Utils.mail.tools.ConvertMessage;

import dao.Emailinfo;
import dao.IEmailinfoDAO;

public class MailOperationAction extends BaseAction {
	private static final long serialVersionUID = -1388635433743413752L;
	private IUserinfoService userinfoService;
	private IMailInfoService mailinfoService;
	private IEmailinfoDAO emailinfoDAO;
	private Emailinfo emailinfo;
	private String content;
	private MailAccount ma;
	private SingleAccount sa;
	private String eid;
	private String id;
	private String attachid;
	private ArrayList<Message> mailSimpleList;// MailSimpleInfo
	private MailDetail mailDetail;
	private String folderName;
	private Message mailToSend;
	private String attachment;
	private String sendStatus;

	/**
	 * account operation
	 */
	public String forUpdateMailAccount() {
		content = "";
		emailinfo = mailinfoService.getEmailinfo(eid);
		this.setDynamicUrl("/jsp/mail/account/update.jsp");
		return DYNAMIC;
	}

	public String updateMailAccount() {
		this.mailinfoService.updateEmailInfo(emailinfo);
		this.setDynamicUrl("/jsp/mail/account/update.jsp");
		return DYNAMIC;
	}

	// operations
	public String forAddMailAccount() {
		content = "";
		emailinfo = new Emailinfo();
		this.setDynamicUrl("/jsp/mail/account/add.jsp");
		return DYNAMIC;
	}

	public String addMailAccount() {
		content = mailinfoService.addEmailInfo(emailinfo) ? "success in add mail account"
				: "fail in add mail account";
		// if(content.equals("�˺����ʧ��")){
		// this.setDynamicUrl("/jsp/mail/account/add.jsp");
		// throw new Exception("fail in add mail account");
		// //return DYNAMIC;
		// }else{
		this.setDynamicUrl("/jsp/struct/main.jsp");
		return DYNAMIC;
		// }
	}

	public String deleteMailAccount(){
		System.out.println("To Delete.."+eid);
		content = mailinfoService.deleteEmailInfo(eid) ? 
					"success in delete mail account" : 
						"fail in delete mail account";
		System.out.println("del:"+eid+content);
		this.setDynamicUrl("/jsp/struct/main.jsp");
		return DYNAMIC;
	}

	/**
	 * get mail list from left frame to top frame
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public String getMailList() throws MessagingException {
		mailSimpleList = new ArrayList<Message>();
		System.out.println("get eid:" + eid + "\nFolderName:" + folderName);
		for (Message ms : mailinfoService.getMailInfo(eid, folderName)
				.getFolders().get(0).getMessages()) {
			mailSimpleList.add(ms);
		}

		this.setDynamicUrl("/jsp/mail/list.jsp");
		// this.setDynamicUrl("/jsp/user/login/success.jsp");
		return DYNAMIC;
	}

	/**
	 * Delete One Message
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public String deleteMail(){
		int myId = Integer.valueOf(id);
		myId = myId < 0 ? 0 : myId >= mailSimpleList.size() ? mailSimpleList
				.size() - 1 : myId;
		if (myId != Integer.valueOf(id))
			id = "" + myId;

		System.out.println(id + "1 size"+mailSimpleList.size());
		try {
			mailSimpleList.get(myId).setFlag(Flags.Flag.DELETED, true);
			mailSimpleList.get(myId).saveChanges();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("error occaled");
		}
		
		System.out.println("OK size"+mailSimpleList.size());
		
		mailSimpleList.remove(myId);
		this.setDynamicUrl("/jsp/mail/list.jsp");
		return DYNAMIC;
		/**
		// ˢ��List
		mailSimpleList = new ArrayList<Message>();
		System.out.println("get eid:" + eid + "\nFolderName:" + folderName);
		for (Message ms : mailinfoService.getMailInfo(eid, folderName)
				.getFolders().get(0).getMessages()) {
			mailSimpleList.add(ms);
		}
		this.setDynamicUrl("/jsp/mail/list.jsp");
		// this.setDynamicUrl("/jsp/user/login/success.jsp");
		return DYNAMIC;*/
	}

	//
	public String getMailDetailInformaiton() throws Exception {
		int myId = Integer.valueOf(id);
		myId = myId < 0 ? 0 : myId >= mailSimpleList.size() ? mailSimpleList
				.size() - 1 : myId;
		if (myId != Integer.valueOf(id))
			id = "" + myId;

		ConvertMessage cm = new ConvertMessage(mailSimpleList.get(myId));
		this.setMailDetail(cm.getMailDetail());
		for (DataHandler dh : mailDetail.getDataHandlers()) {
			System.out.println("name:" + dh.getName());
			System.out.println(dh.getContent().toString());
		}
		this.setDynamicUrl("/jsp/mail/detail.jsp");
		// this.setDynamicUrl("/jsp/user/login/success.jsp");
		return DYNAMIC;
	}

	// TODO ATTACH
	public String getAttach() throws IOException {
		DataHandler dh = mailDetail.getDataHandlers().get(
				Integer.valueOf(attachid));
		System.out.println("name=" + dh.getContent().toString());
		// content = dh.getContent().toString();

		// InputStreamReader isr = new InputStreamReader((InputStream)
		// .getContent());
		// content = isr.;
		String filename = dh.getName();
		/*
		 * BufferedInputStream is = new
		 * BufferedInputStream(dh.getInputStream()); BufferedOutputStream baos =
		 * new BufferedOutputStream( new ByteArrayOutputStream()); int size =
		 * -1; do { byte[] bs = new byte[8192]; size = is.read(bs); if (size >
		 * 0) { baos.write(bs, 0, size); baos.flush(); } attach = bs; } while
		 * (size > 0); // myPart.setContent(baos); parts.add(myPart);
		 * is.close(); baos.close();
		 */
		/**
		 * try{ filename =
		 * URLEncoder.encode(URLEncoder.encode(filename,"UTF-8"),"UTF-8");
		 * }catch(Exception e){}
		 */
		this.getResponse().addHeader("Content-Disposition",
				"attachment;filename=" + filename);

		this.setDynamicUrl("/jsp/mail/attach.jsp");
		// this.setDynamicUrl("/jsp/user/login/success.jsp");
		return DYNAMIC;
	}

	/**
	 * TODO Send Email Start
	 * 
	 * @return
	 */
	public String newMailToSend() {
		mailDetail = new MailDetail();
		emailinfo = mailinfoService.getEmailinfo(eid);		
		String strFrom = emailinfo.getEuser();
		if(!strFrom.contains("@")){
			strFrom += "@" + emailinfo.getSmtp().substring(5);
		}
		mailDetail.setFrom(strFrom);
		this.setDynamicUrl("/jsp/mail/send.jsp");
		// this.setDynamicUrl("/jsp/user/login/success.jsp");
		return DYNAMIC;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public String reply() {
		int myId = Integer.valueOf(id);
		myId = myId < 0 ? 0 : myId >= mailSimpleList.size() ? mailSimpleList .size() - 1 : myId;
		if (myId != Integer.valueOf(id))
			id = "" + myId;

		ConvertMessage cm = new ConvertMessage(mailSimpleList.get(myId));
		this.setMailDetail(cm.getMailDetail());


		String strTo = mailDetail.getFromAddr().get(0);
		emailinfo = mailinfoService.getEmailinfo(eid);
		
		// TODO
		String strFrom = emailinfo.getEuser();
		if(!strFrom.contains("@")){
			strFrom += "@" + emailinfo.getSmtp().substring(5);
		}
		mailDetail.setSubject("RE:"+mailDetail.getSubject());
		mailDetail.setFrom(strFrom);
		mailDetail.setTo(strTo);
		mailDetail.setSentDate(new Date());
		mailDetail.setHtmlContent("<br><br><p>=================================</p>"
						+ mailDetail.getHtmlContent());
		mailDetail.setTextContent("\n\n=================================\n"
				+ mailDetail.getTextContent());
		this.setDynamicUrl("/jsp/mail/send.jsp");
		return DYNAMIC;
	}

	/**
	 * TODO Send email
	 * 
	 * @return
	 */
	public String sendEmail() {
		// email content
		SendEmail sm = new SendEmail();
		sm.setMailFrom(mailDetail.getFrom());
		sm.setMailSubject(mailDetail.getSubject());
		sm.setMailText(mailDetail.getTextContent());// content
		sm.setMailTo(mailDetail.getTo());

		// account content
		emailinfo = mailinfoService.getEmailinfo(eid);
		sm.setSmtpUrl(emailinfo.getSmtp());
		sm.setSmtpAuth("true");
		sm.setMailUserName(emailinfo.getEuser());
		sm.setMailPassword(emailinfo.getEpwd());

		// send and get status
		sendStatus = sm.send() ? "Send success !" : "Send Error :<br>"
				+ sm.getErrorLog();
		this.setDynamicUrl("/jsp/mail/sendStatus.jsp");
		return DYNAMIC;
	}

	/**
	 * Send Email end
	 * 
	 * @return
	 */

	/**
	 * TODO single account information
	 */
	public String showSingleAccountInformation() {
		sa = mailinfoService.getMailInfo(eid);
		emailinfo = mailinfoService.getEmailinfo(eid);
		this.setDynamicUrl("/jsp/mail/account/detail.jsp");
		// this.setDynamicUrl("/jsp/user/login/success.jsp");
		return DYNAMIC;
	}

	/**
	 * when login init three frame
	 * 
	 * @return
	 */

	public String forNavigation() {
		ma = mailinfoService.getAllMailInfo();
		this.setDynamicUrl("/jsp/struct/navigation.jsp");
		return DYNAMIC;
	}

	public String forTop() {
		this.setDynamicUrl("/jsp/struct/empty.jsp");
		return DYNAMIC;
	}

	public String forMain() {
		ma = mailinfoService.getAllMailInfo();
		this.setDynamicUrl("/jsp/struct/emptyDetail.jsp");
		return DYNAMIC;
	}

	// setters & getters

	public IUserinfoService getUserinfoService() {
		return userinfoService;
	}

	public void setUserinfoService(IUserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}

	public IMailInfoService getMailinfoService() {
		return mailinfoService;
	}

	public void setMailinfoService(IMailInfoService mailinfoService) {
		this.mailinfoService = mailinfoService;
	}

	public IEmailinfoDAO getEmailinfoDAO() {
		return emailinfoDAO;
	}

	public void setEmailinfoDAO(IEmailinfoDAO emailinfoDAO) {
		this.emailinfoDAO = emailinfoDAO;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MailAccount getMa() {
		return ma;
	}

	public void setMa(MailAccount ma) {
		this.ma = ma;
	}

	public SingleAccount getSa() {
		return sa;
	}

	public void setSa(SingleAccount sa) {
		this.sa = sa;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList<Message> getMailSimpleList() {
		return mailSimpleList;
	}

	public void setMailSimpleList(ArrayList<Message> mailSimpleList) {
		this.mailSimpleList = mailSimpleList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MailDetail getMailDetail() {
		return mailDetail;
	}

	public void setMailDetail(MailDetail mailDetail) {
		this.mailDetail = mailDetail;
	}

	public String getAttachid() {
		return attachid;
	}

	public void setAttachid(String attachid) {
		this.attachid = attachid;
	}

	public Message getMailToSend() {
		return mailToSend;
	}

	public void setMailToSend(Message mailToSend) {
		this.mailToSend = mailToSend;
	}

	/**
	 * TODO Make sure
	 * 
	 * @return
	 */
	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Emailinfo getEmailinfo() {
		return emailinfo;
	}

	public void setEmailinfo(Emailinfo emailinfo) {
		this.emailinfo = emailinfo;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

}
