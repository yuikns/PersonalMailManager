package base.acount;

import java.util.ArrayList;

public class MailAccount {
	ArrayList<SingleAccount> accounts = new ArrayList<SingleAccount>();
	
	public MailAccount() {
	}


	public void addAccount(SingleAccount account) {
		this.accounts.add(account);
	}


	public ArrayList<SingleAccount> getAccounts() {
		return accounts;
	}


	public void setAccounts(ArrayList<SingleAccount> accounts) {
		this.accounts = accounts;
	}



	//Collection

	
	
}
