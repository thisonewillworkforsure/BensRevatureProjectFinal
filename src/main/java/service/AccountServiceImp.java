package service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import dao.DatabaseManager;
import pojo.AccountPojo;

public class AccountServiceImp implements AccountService {

	@Override
	public List<AccountPojo> getAllAccount() {
		// TODO Auto-generated method stub
		List<AccountPojo> accountPojos = new ArrayList<AccountPojo>();
		accountPojos = DatabaseManager.getInstance().getAllAccount();
		return accountPojos;
	}

	@Override
	public AccountPojo createAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		accountPojo = DatabaseManager.getInstance().createAccount(accountPojo);
		return accountPojo;
	}

	@Override
	public AccountPojo updateAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		return DatabaseManager.getInstance().updateAccount(accountPojo);
		
	}

	@Override
	public void deleteAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AccountPojo getOneAccount(AccountPojo accountPojo) {
		// TODO Auto-generated method stub
		return DatabaseManager.getInstance().getOneAccount(accountPojo);
	}

}
