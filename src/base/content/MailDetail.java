package base.content;

import java.util.ArrayList;
import java.util.Date;

import javax.activation.DataHandler;

public class MailDetail {
	private String from;
	private ArrayList<String> fromAddr;
	private String replyTo;
	private String to;
	private String cc;
	private String subject;
	private Date sentDate;
	private String stringSentDate;
	
	private String htmlContent;
	private String textContent;
	private ArrayList<String> otherContents = new ArrayList<String>();
	
	private ArrayList<DataHandler> dataHandlers = new ArrayList<DataHandler>();
	
	public MailDetail(){
		fromAddr = new ArrayList<String>();
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		/**
		 * TODO sentDate Convent to String
		 */
		this.stringSentDate = sentDate.toString();
		this.sentDate = sentDate;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public ArrayList<DataHandler> getDataHandlers() {
		return dataHandlers;
	}

	public void setDataHandlers(ArrayList<DataHandler> dataHandlers) {
		this.dataHandlers = dataHandlers;
	}
	
	public void addDataHandler(DataHandler dataHandler){
		this.dataHandlers.add(dataHandler);
	}

	public ArrayList<String> getOtherContents() {
		return otherContents;
	}

	public void setOtherContents(ArrayList<String> otherContents) {
		this.otherContents = otherContents;
	}
	
	public void addOtherContent(String otherContent){
		this.otherContents.add(otherContent);
	}

	public String getStringSentDate() {
		return stringSentDate;
	}

	public void setStringSentDate(String stringSentDate) {
		this.stringSentDate = stringSentDate;
	}
	
	
	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
	
	public void addFromAddr(String fromAddr){
		this.fromAddr.add(fromAddr);
	}

	public ArrayList<String> getFromAddr() {
		return fromAddr;
	}

	public void setFromAddr(ArrayList<String> fromAddr) {
		this.fromAddr = fromAddr;
	}

	public static void main(String []args){
		MailDetail md = new MailDetail();
		md.setSentDate(new Date());
		System.out.println(md.getStringSentDate());
	}
	
}
