package common.Utils.mail;

import java.util.ArrayList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendEmail {
	private String smtpUrl;
	private String smtpAuth;

	private String mailUserName;
	private String mailPassword;

	private String contentType;

	private String mailSubject;
	private String mailFrom;

	private String mailText;
	private String mailTo;
	
	private String fileName;

	private ArrayList<String> cc = new ArrayList<String>();

	private String errorLog;

	public SendEmail() {
		setSmtpAuth("true");
	}

	public boolean check() {
		boolean flag = true;
		this.setErrorLog("");
		if (smtpUrl == "" || smtpUrl == null) {
			errorLog += "need smtp url\n";
			flag = false;
		}
		if (smtpAuth == "" || smtpAuth == null) {
			errorLog += "need smtp url\n";
			flag = false;
		}
		if (mailUserName == "" || mailUserName == null) {
			errorLog += "need user name\n";
			flag = false;
		}
		if (mailPassword == "" || mailPassword == null) {
			errorLog += "need password\n";
			flag = false;
		}
		if (mailSubject == "" || mailSubject == null) {
			errorLog += "need subject\n";
			flag = false;
		}
		if (mailFrom == "" || mailFrom == null) {
			errorLog += "need from url\n";
			flag = false;
		}
		if (mailText == "" || mailText == null) {
			errorLog += "need context\n";
			flag = false;
		}
		if (mailTo == "" || mailTo == null) {
			errorLog += "need to mail address\n";
			flag = false;
		}
		return flag;
	}

	public boolean send() {
		if (!check())
			return false;
		Properties props = new Properties();
		props.put("mail.smtp.host", this.smtpUrl);
		props.put("mail.smtp.auth", this.smtpAuth);
		Session sendMailSession = Session.getInstance(props, null);

		try {
			Transport transport = sendMailSession.getTransport("smtp");
			transport.connect(this.smtpUrl, this.mailUserName,
					this.mailPassword);
			Message newMessage = new MimeMessage(sendMailSession);

			// set Theme
			String mail_subject = MimeUtility.encodeText(new String(
					this.mailSubject.getBytes(), "utf-8"), "utf-8", "B");
			System.out.println(mail_subject);
			newMessage.setSubject(mail_subject);
			sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
			// newMessage.setSubject("=?utf8?B?"+enc.encode(mail_subject.getBytes())+"?=");

			// set From Mail
			String strFrom = this.mailFrom;
			strFrom = new String(strFrom.getBytes(), "ISO-8859-1");
			newMessage.setFrom(new InternetAddress(strFrom));
			Address addressFrom[] = {
			// new InternetAddress(this.smtpUrl)
			// ,new InternetAddress()
			};

			// change fromer address
			newMessage.addFrom(addressFrom);

			// set receiver address
			Address addressTo[] = new Address[1 + cc.size()];
			addressTo[0] = new InternetAddress(this.mailTo);
			for (int i = 0; i < cc.size(); i++)
				addressTo[i + 1] = new InternetAddress(cc.get(i));

			newMessage.setRecipients(Message.RecipientType.TO, addressTo);

			// set content
			newMessage.setSentDate(new java.util.Date());
			String mail_text = new String(this.mailText.getBytes(),"UTF-8");
			
			//newMessage.setText(mail_text);

			if (contentType == null || contentType.equals("text")) {
				newMessage.setText(mail_text);
			} else if (contentType.equals("html")) {
				// 给消息对象设置内容
				BodyPart bodyPart1 = new MimeBodyPart();
				bodyPart1.setContent(mail_text, "text/html;charset=utf-8");
				
				/*
				// 设置邮件附件
				BodyPart bodyPart2 = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(fileName);
				bodyPart2.setDataHandler(new DataHandler(fileDataSource));
				bodyPart2.setFileName("=?utf8?B?"+ enc.encode(fileName.getBytes()) + "?=");

				Multipart multipart = new MimeMultipart();
			
				multipart.addBodyPart(bodyPart1);
				multipart.addBodyPart(bodyPart2);

				Multipart mmp = new MimeMultipart();
				//mmp.addBodyPart(multipart);// 将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
				mmp.addBodyPart(bodyPart1);
				*/
				Multipart mmp = new MimeMultipart();
				mmp.addBodyPart(bodyPart1);
				newMessage.setContent(mmp);// 把mm作为消息对象的内容
			}

			// save info
			newMessage.saveChanges();
			transport.sendMessage(newMessage,
					newMessage.getRecipients(Message.RecipientType.TO));

			// send
			transport.close();

			// System.out.println("Fine!");
		} catch (Exception e) {
			// System.out.println("Error:"+e);
			this.errorLog += e;
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		SendEmail testEmail = new SendEmail();

		testEmail.setSmtpUrl("smtp.126.com");
		// testEmail.setSmtpAuth("true");

		testEmail.setMailUserName("asm_se0901");
		testEmail.setMailPassword("se0901");
		testEmail.setMailSubject("[Test 1][ 中文]");
		testEmail.setMailFrom("asm_se0901@126.com");
		testEmail.setMailTo("527195277@qq.com");
		testEmail.setMailText("[Test2][中文]");
		testEmail.setContentType("html");
		//testEmail.addCc("hking915@hotmail.com");

		System.out.println(testEmail.send() ? "Fine" : ("Error:\n" + testEmail
				.getErrorLog()));

	}

	// setters & getters

	public String getSmtpUrl() {
		return smtpUrl;
	}

	public void setSmtpUrl(String smtpUrl) {
		this.smtpUrl = smtpUrl;
	}

	public String getSmtpAuth() {
		return smtpAuth;
	}

	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	public String getMailUserName() {
		return mailUserName;
	}

	public void setMailUserName(String mailUserName) {
		this.mailUserName = mailUserName;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailText() {
		return mailText;
	}

	public void setMailText(String mailText) {
		this.mailText = mailText;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public void addCc(String cc) {
		this.cc.add(cc);
	}

	public ArrayList<String> getCc() {
		return cc;
	}

	public void setCc(ArrayList<String> cc) {
		this.cc = cc;
	}

	public String getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(String errorLog) {
		this.errorLog = errorLog;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
