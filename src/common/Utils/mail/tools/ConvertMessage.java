package common.Utils.mail.tools;

import java.io.IOException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

import base.content.MailDetail;

public class ConvertMessage {
	MailDetail md;
	Message message;
	boolean status;

	public ConvertMessage() {
		status = false;
	}

	public ConvertMessage(Message message) {
		status = true;
		this.message = message;
		this.doConvert();
	}

	public void setMessage(Message message) {
		status = true;
		this.message = message;
		this.doConvert();
	}

	public void doConvert() {
		md = new MailDetail();
		try {
			//md.setFrom(InternetAddress.toString(message.getFrom()));
			//md.setReplyTo(InternetAddress.toString(message.getReplyTo()));
			//md.setTo(InternetAddress.toString(message.getRecipients(Message.RecipientType.TO)));
			md.setFrom(convertAddress((InternetAddress [])message.getFrom(), true));
			md.addFromAddr(convertAddress((InternetAddress [])message.getFrom(), false));
			md.setReplyTo(convertAddress((InternetAddress [])message.getReplyTo(), true));
			md.setTo(convertAddress((InternetAddress [])message.getRecipients(Message.RecipientType.TO), true));
			md.setCc(convertAddress((InternetAddress [])message.getRecipients(Message.RecipientType.CC), true));
			md.setSubject(message.getSubject());
			md.setSentDate(message.getSentDate());

			//System.out.println("Message : "+ (message.getContent() instanceof Multipart));

			if (message.getContent() instanceof Multipart) {
				anMultipart((Multipart) message.getContent());
			} else {
				md.setHtmlContent(message.getContent().toString());
				md.setTextContent(message.getContent().toString());
			}

		} catch (Exception e) {
			status = false;
		}

	}

	public void anMultipart(Multipart multipart) {
		try {
			for (int x = 0; x < multipart.getCount(); x++) {
				BodyPart bodyPart = multipart.getBodyPart(x);

				String disposition = bodyPart.getDisposition();

				if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
					//DataHandler handler = bodyPart.getDataHandler();
					//System.out.println("file name : " + handler.getName());
					md.addDataHandler(bodyPart.getDataHandler());
					System.out.println("wula~~~");
				} else {
				
					if (bodyPart.getContentType().toLowerCase().substring(0, 10).equals("text/plain")) {
						md.setTextContent(bodyPart.getContent().toString());
					} else
						if (bodyPart.getContentType().toLowerCase().substring(0, 8).equals("text/htm")) {
						md.setHtmlContent(bodyPart.getContent().toString());
					} else 
						if (bodyPart.getContentType().toLowerCase().substring(0, 10).equals("multipart/")) {
						anMultipart((Multipart) bodyPart.getContent());
					} else {
						System.out.println("other~~~");
						//md.addOtherContent(bodyPart.getContent().toString());
						md.addDataHandler(bodyPart.getDataHandler());
						//md.addDataHandler(bodyPart.getContent());
					}
				}

			}
		} catch (Exception e) {
			status = false;
		}
	}
	
	public String convertAddress(InternetAddress[] address , boolean hasName){
		String mailaddr = "";
		try{
		if (address != null) {
			for (int i = 0; i < address.length; i++) {
				String email = address[i].getAddress();
				if (email == null)
					email = "";
				else {
					email = MimeUtility.decodeText(email);
				}
				String personal = address[i].getPersonal();
				if (personal == null)
					personal = "";
				else {
					personal = MimeUtility.decodeText(personal);
				}
				String compositeto = personal + "<" + email + ">";
				if(hasName)
					mailaddr += "," + compositeto;
				else{
					mailaddr += "," + email;
				}
			}
			mailaddr = mailaddr.substring(1);
		}
		}catch(Exception e){}
		return mailaddr;
	}
	
	public boolean getStatus(){ return this.status;}
	
	public MailDetail getMailDetail(){ return this.md;}

}
