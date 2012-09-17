package common.Utils.mail;

import java.util.ArrayList;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveEmail {
	private String protocol = "imap"; // 服务协议
	private String mailHost; // 服务器地址
	private String userName; // 用户名
	private String password; // 密码
	// private String port; //port
	private Store store;
	private Folder folder;
	private String folderName;

	// error flag
	boolean otherError = false;
	int mailDownErrorCounter = 0;

	public ReceiveEmail() {
	}

	public ReceiveEmail(String mailHost, String username, String password) {
		this.mailHost = mailHost;
		this.userName = username;
		this.password = password;
	}

	/**
	 * 建立Store连接
	 */
	private Store getStoreFromServer() throws Exception {
		// 创建session
		System.out.println("#1.0.0.0");
		// Session session =
		// Session.getDefaultInstance(System.getProperties(),null);
		Session session = Session.getInstance(System.getProperties());

		// session.setDebug(true);
		// 创建store,建立连接
		System.out.println("#1.0.0.1");
		// Store store = session.getStore(protocol);
		Store store = session.getStore(this.protocol);

		System.out.println("connecting");

		store.connect(mailHost, userName, password);

		System.out.println("connected successfully");
		setStore(store);
		return store;
	}

	public boolean testConnect() {
		return testConnect(this.mailHost, this.userName, this.password);
	}

	public boolean testConnect(String mailHost, String username, String password) {
		try{
			this.getStoreFromServer();
		}catch(Exception e){
			return false;
		}
		return true;
	}

	// services
	public ArrayList<Folder> getAllFolderName(String mailHost, String username,
			String password) {
		ArrayList<Folder> folderNames = new ArrayList<Folder>();
		this.mailHost = mailHost;
		this.userName = username;
		this.password = password;
		this.folderName = "inbox";
		try {
			System.out.println("#1.0");
			this.getStoreFromServer();
			System.out.println("#1.1");
			/*
			 * for (Folder item : store.getPersonalNamespaces()) {
			 * System.out.println("#1.2."+count++); folderNames.add(item);
			 * System.out.println("" + item.getFullName()); }
			 */
			System.out.println("waiting..");

			try {
				/**
				 * Folder[] getFolder = store.getPersonalNamespaces();
				 * for(Folder fl : getFolder){ //fl.open(Folder.READ_WRITE);
				 * folderNames.add(fl); System.out.println("~~"); }
				 */

				Folder fl = store.getFolder("inbox");
				fl.open(Folder.READ_WRITE);
				// System.out.println("full:["+fl.getFullName()+"]name:["+fl.getName()+"]");
				// folderNames.add(getFolderFromStore(fl.getFullName()));
				folderNames.add(fl);
				/*
				 * fl = store.getFolder("sent"); fl.open(Folder.READ_WRITE);
				 * //System
				 * .out.println("full:["+fl.getFullName()+"]name:["+fl.getName
				 * ()+"]");
				 * //folderNames.add(getFolderFromStore(fl.getFullName()));
				 * folderNames.add(fl);
				 */
				fl = store.getFolder("trash");
				fl.open(Folder.READ_WRITE);
				// System.out.println("full:["+fl.getFullName()+"]name:["+fl.getName()+"]");
				// folderNames.add(getFolderFromStore(fl.getFullName()));
				folderNames.add(fl);
				fl = store.getFolder("drafts");
				fl.open(Folder.READ_WRITE);
				// System.out.println("full:["+fl.getFullName()+"]name:["+fl.getName()+"]");
				// folderNames.add(getFolderFromStore(fl.getFullName()));
				folderNames.add(fl);
			} catch (Exception e) {
				System.err.println("获取Folder失败！");
				e.printStackTrace();
				System.err.println("\n++++++++++++++");
			}

			System.out.println("#1.3");
		} catch (Exception e) {
			System.out.println("#1.4 (Exception)");
			otherError = true;
			mailDownErrorCounter++;
			System.out.print("******\n" + e + "\n*******\n");
			// System.out.print("******\n" + e.getLocalizedMessage() +
			// "\n*******\n");
		}
		System.out.println("#1.5 (Success End)");
		return folderNames;
	}

	// setters & getters
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getMailHost() {
		return mailHost;
	}

	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public boolean isOtherError() {
		return otherError;
	}

	public void setOtherError(boolean otherError) {
		this.otherError = otherError;
	}

	public int getMailDownErrorCounter() {
		return mailDownErrorCounter;
	}

	public void setMailDownErrorCounter(int mailDownErrorCounter) {
		this.mailDownErrorCounter = mailDownErrorCounter;
	}

}
