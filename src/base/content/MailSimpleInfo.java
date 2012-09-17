package base.content;

public class MailSimpleInfo {
	public String subject;
	public String read;
	public String hasAttach;
	public MailSimpleInfo(){
		
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRead() {
		return read;
	}
	public void setRead(String read) {
		this.read = read;
	}
	public String getHasAttach() {
		return hasAttach;
	}
	public void setHasAttach(String hasAttach) {
		this.hasAttach = hasAttach;
	}
	
}
