package base.acount;

import java.util.ArrayList;

import javax.mail.Folder;
import javax.mail.MessagingException;

public class SingleAccount {
	private ArrayList<Folder> folders = new ArrayList<Folder>();
	private String accountName;
	private String eid;
	private int []size;
	private int []unreadSize;
	public SingleAccount(){}
	public void addFolder(Folder folder) throws MessagingException {
		folders.add(folder);
		this.size = new int[folders.size()];
		this.unreadSize = new int[folders.size()];
		for(int i = 0 ; i < folders.size() ; i ++){ 
			this.size[i] = folders.get(i).getMessageCount();
			this.unreadSize[i] = folders.get(i).getUnreadMessageCount();
		}
	}
	public SingleAccount getAccount(){
		return this;
	}
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	public void setFolders(ArrayList<Folder> folders) throws MessagingException {
		this.folders = folders;
		this.size = new int[folders.size()];
		this.unreadSize = new int[folders.size()];
		for(int i = 0 ; i < folders.size() ; i ++){ 
			this.size[i] = folders.get(i).getMessageCount();
			this.unreadSize[i] = folders.get(i).getUnreadMessageCount();
		}
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public int [] getSize() {
		return size;
	}
	public void setSize(int [] size) {
		this.size = size;
	}
	public int [] getUnreadSize() {
		return unreadSize;
	}
	public void setUnreadSize(int [] unreadSize) {
		this.unreadSize = unreadSize;
	}
	

	
	
}
