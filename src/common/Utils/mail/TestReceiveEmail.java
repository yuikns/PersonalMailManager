package common.Utils.mail;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

public class TestReceiveEmail {
	public static void main(String[] args) throws MessagingException,
			IOException {
		Collection cl = new ArrayList<String>();
		ArrayList<Folder> folders = new ArrayList<Folder>();
		ReceiveEmail re = new ReceiveEmail();
		//folders.addAll(re.getAllFolderName("imap.126.com","asm_se0901@126.com", "se0901"));
		folders.addAll(re.getAllFolderName("imap.qq.com", "527195277@qq.com","lovelotus"));
		System.out.println("#output 0 (number : " + folders.size() + " )");
		int count = 0;
		/**  
		for (Folder fl : folders) {
			System.out.println("OK");
		}
		/** */
		for (Folder fl : folders) {
			System.out.println("folder name:" + fl.getFullName());
			System.out.println("size:" + fl.getMessageCount());

			// javax.mail.Message ms :fl.getMessages(1,fl.getMessageCount())
			for (javax.mail.Message ms : fl
					.getMessages(1, fl.getMessageCount())) {
				// System.out.println("TO:"+getMailAddress("TO",ms));
				// System.out.println("BCC:"+getMailAddress("BCC",ms));
				// System.out.println("CC:"+getMailAddress("CC",ms));
				// 分析

				System.out.println("------------ Message " + ((count++) + 1)
						+ " ------------");
				Address[] ad = ms.getFrom();
				//String from = .toString(ms.getFrom());
				String from = convertAddress((InternetAddress [])ms.getFrom());
				//String from = InternetAddress.toString(ms.getFrom());
				if (from != null) {
					System.out.println("From: " + from);
				}
			

				String replyTo = InternetAddress.toString(ms.getReplyTo());
				if (replyTo != null) {
					System.out.println("Reply-to: " + replyTo);
				}
				String to = InternetAddress.toString(ms
						.getRecipients(Message.RecipientType.TO));
				if (to != null) {
					System.out.println("To:" + to+"");
				}

				String subject = ms.getSubject();
				if (subject != null) {
					System.out.println("Subject: " + subject );
				}
				Date sent = ms.getSentDate();
				if (sent != null) {
					System.out.println("Sent: " + sent);
				}

				System.out.println();
				System.out.println("Message : " + (ms.getContent() instanceof Multipart));
				
				
				if (ms.getContent() instanceof Multipart)
				{
					anMultipart((Multipart)ms.getContent());
				} else {
					String body = ms.getContent().toString();
					System.out.println(body);
				}
			}
		}
		//*/
		
		System.out.println("#output 1");
		
	}
	
	public static void anMultipart(Multipart multipart) throws MessagingException, IOException{
		for (int x = 0; x < multipart.getCount(); x++) {
			BodyPart bodyPart = multipart.getBodyPart(x);

			String disposition = bodyPart.getDisposition();

			if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
				System.out.println("=========1===============");
				System.out.println("Mail have some attachment : ");

				DataHandler handler = bodyPart.getDataHandler();
				System.out.println("file name : "
						+ handler.getName());
			} else {
				if(bodyPart.getContentType().toLowerCase().substring(0, 8).equals("text/htm")){
					System.out.println("=========html===============");
					System.out.println(bodyPart.getContent());
				}else if(bodyPart.getContentType().toLowerCase().substring(0, 10).equals("text/plain")){
					System.out.println("=========text===============");
					System.out.println(bodyPart.getContent());
				}else if(bodyPart.getContentType().toLowerCase().substring(0, 10).equals("multipart/")){
					anMultipart((Multipart) bodyPart.getContent());
				}
			}

		}
	}
	
	public void anString(String body){
		//String body = ms.getContent().toString();
		System.out.println(body);
	}
	
	public static String convertAddress(InternetAddress[] address){
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
				mailaddr += "," + compositeto;
			}
			mailaddr = mailaddr.substring(1);
		}
		}catch(Exception e){}
		return mailaddr;
	}

	public static String getMailAddress(String type, Message mimeMessage)
			throws Exception {
		String mailaddr = "";
		String addtype = type.toUpperCase();
		InternetAddress[] address = null;
		if (addtype.equals("TO") || addtype.equals("CC")
				|| addtype.equals("BCC")) {
			if (addtype.equals("TO")) {
				address = (InternetAddress[]) mimeMessage
						.getRecipients(Message.RecipientType.TO);
			} else if (addtype.equals("CC")) {
				address = (InternetAddress[]) mimeMessage
						.getRecipients(Message.RecipientType.CC);
			} else if (addtype.equals("FROM")) {
				address = (InternetAddress[]) mimeMessage
						.getRecipients(Message.RecipientType.BCC);
			} else {
				address = (InternetAddress[]) mimeMessage
						.getRecipients(Message.RecipientType.BCC);
			}
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
					mailaddr += "," + compositeto;
				}
				mailaddr = mailaddr.substring(1);
			}
		} else {
			throw new Exception("Error emailaddr type!");
		}
		return mailaddr;
	}

	/*
	 * 解析邮件
	 */
	public void parseMessage(Message message) throws IOException,
			MessagingException {
		Object content = message.getContent();
		if (content instanceof Multipart) {
			handleMultipart((Multipart) content);
		} else {
			handlePart(message);
		}
	}

	/*
	 * 解析Multipart
	 */
	public void handleMultipart(Multipart multipart) throws MessagingException,
			IOException {
		for (int i = 0, n = multipart.getCount(); i < n; i++)
			handlePart(multipart.getBodyPart(i));
	}

	/*
	 * 解析指定part,从中提取文件
	 */
	public void handlePart(Part part) throws MessagingException, IOException {
		String disposition = part.getDisposition(); // Find attachment
		String contentType = part.getContentType();
		// String str;
		InputStreamReader sbis = new InputStreamReader(part.getInputStream());
		if (disposition == null) { // When just body
			System.out.println("Null: " + contentType);
			// Check if plain
			if ((contentType.length() >= 9)
					&& (contentType.toLowerCase().substring(0, 9)
							.equals("text/plai"))) {

				System.out.println("attach");
				// saveFile(getAttachPath() + getEmlName() + ".txt", sbis);
			} else if ((contentType.length() >= 8) // Check if html
					&& (contentType.toLowerCase().substring(0, 8)
							.equals("text/htm"))) {
				System.out.println("html:" + sbis);
				// saveFile(getAttachPath() + getEmlName() + ".html", sbis);
			} else if ((contentType.length() >= 9) // Check if html
					&& (contentType.toLowerCase().substring(0, 9)
							.equals("image/gif"))) {
				System.out.println("img");
				// saveFile(getAttachPath() + getEmlName() + ".gif", sbis);
			} else if ((contentType.length() >= 10)
					&& contentType.toLowerCase().substring(0, 10)
							.equals("multipart/")) { // Check if multipart
				System.out.println("multipart body: " + contentType);
				Multipart mp = (Multipart) (part.getContent());
				handleMultipart(mp);
			} else { // Unknown type
				System.out.println("Other body: " + contentType);
				// saveFile(getAttachPath() + getEmlName() + ".txt", sbis);
			}
		} else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
			System.out.println("Attachment: " + part.getFileName() + " : "
					+ contentType);
			// outToFile.println("Attachment: " + part.getFileName() + " : "
			// + contentType);

			// saveFile(getAttachPath() + part.getFileName(), sbis);
		} else if (disposition.equalsIgnoreCase(Part.INLINE)) {
			System.out.println("Inline: " + part.getFileName() + " : "
					+ contentType);
			// outToFile.println("Inline: " + part.getFileName() + " : "
			// + contentType);
			// saveFile(getAttachPath() + part.getFileName(), sbis);
		} else { // Should never happen
			System.out.println("Other: " + disposition);
			// outToFile.println("Other: " + disposition);
		}
	}
}
