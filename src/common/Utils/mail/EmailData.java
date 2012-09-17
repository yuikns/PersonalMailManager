package common.Utils.mail;

public class EmailData {
	public EmailData(){
		this.index = 0;
		this.firstEmail = this;
	}
	
	// data structure
	//number of it
	private int emailNum; 
	//index
	private int index;
	//first
	private EmailData firstEmail;
	//next
	private EmailData nextEmail;

	//entity
	private String fileName;
	private String showName;
	private boolean flag;
	private int errorCode;
	
	
	public EmailData goToHead(){
		return firstEmail;
	}
	
	public EmailData goToIndex(int index){
		if(this.nextEmail == null) index = index % this.index;
		if(this.index == index) return this;
		return this.index < index ? nextEmail.goToIndex(index) : firstEmail.goToIndex(index);
	}
	
	public EmailData add(){
		this.nextEmail = new EmailData();
		nextEmail.setIndex(this.index+1);
		nextEmail.setNextEmail(null);
		nextEmail.setFirstEmail(this.firstEmail);
		return nextEmail;
	}

	public int getEmailNum() {
		return emailNum;
	}

	public void setEmailNum(int emailNum) {
		this.emailNum = emailNum;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public EmailData getFirstEmail() {
		return firstEmail;
	}

	public void setFirstEmail(EmailData firstEmail) {
		this.firstEmail = firstEmail;
	}

	public EmailData getNextEmail() {
		return nextEmail;
	}

	public void setNextEmail(EmailData nextEmail) {
		this.nextEmail = nextEmail;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
